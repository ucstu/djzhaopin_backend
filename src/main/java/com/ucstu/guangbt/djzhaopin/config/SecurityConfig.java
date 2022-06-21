package com.ucstu.guangbt.djzhaopin.config;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity // 启用 Spring Security。
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true) // 启用 Spring Security
                                                                                                // 的注解。
public class SecurityConfig {

        @Resource
        private JwtAuthenticationProvider jwtAuthenticationProvider;

        @Resource
        private PermissionEvaluator permissionEvaluator;

        // 用于配置会话创建策略的常量。
        private static final SessionCreationPolicy STATELESS = SessionCreationPolicy.STATELESS;

        // 不需要身份验证的公共 url 列表。
        private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
                        new AntPathRequestMatcher("/accountInfos/**"), // 获取账户信息。
                        new AntPathRequestMatcher("/verificationCode"), // 获取验证码。
                        new AntPathRequestMatcher("/ws/**"), // 获取 websocket 信息。
                        new AntPathRequestMatcher("/**", "OPTIONS"), // 获取跨域信息。
                        new AntPathRequestMatcher("/error/**")); // 获取错误信息。

        // 需要身份验证的 url 列表。
        private static final RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(PUBLIC_URLS);

        // 创建一个自定义的身份验证过滤器。
        class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {

                @Override
                // 配置身份验证过滤器。
                public void configure(HttpSecurity http) throws Exception {
                        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
                        http
                                        .authenticationProvider(jwtAuthenticationProvider) // 配置身份验证者。
                                        .addFilterBefore(jwtAuthenticationFilter(authenticationManager), // 配置过滤器。
                                                        AnonymousAuthenticationFilter.class);
                }

                // 创建身份验证过滤器。
                private JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationManager authenticationManager) {
                        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(PROTECTED_URLS); // 创建过滤器。
                        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler(); // 创建成功处理器。
                        successHandler.setRedirectStrategy(new RedirectStrategy() { // 配置重定向策略。
                                @Override
                                // 重定向到 /accountInfos/me。
                                public void sendRedirect(HttpServletRequest request, HttpServletResponse response,
                                                String url) throws IOException {
                                }
                        });
                        filter.setAuthenticationManager(authenticationManager); // 配置身份验证管理器。
                        filter.setAuthenticationSuccessHandler(successHandler); // 配置成功处理器。
                        return filter;
                }
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOriginPatterns(Arrays.asList("*"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .sessionManagement()
                                .sessionCreationPolicy(STATELESS);
                http
                                .exceptionHandling()
                                .defaultAuthenticationEntryPointFor(new HttpStatusEntryPoint(HttpStatus.FORBIDDEN),
                                                PROTECTED_URLS);
                http
                                .apply(new CustomDsl());
                http
                                .authorizeRequests()
                                .expressionHandler(
                                                defaultWebSecurityExpressionHandler())
                                .requestMatchers(PUBLIC_URLS)
                                .permitAll()
                                .requestMatchers(PROTECTED_URLS)
                                .authenticated();
                http
                                .csrf().disable()
                                .formLogin().disable()
                                .httpBasic().disable()
                                .logout().disable();
                return http.build();
        }

        private DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler() {
                DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
                defaultWebSecurityExpressionHandler.setPermissionEvaluator(permissionEvaluator);
                return defaultWebSecurityExpressionHandler;
        }

}
