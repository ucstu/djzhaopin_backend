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
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

        @Resource
        private JwtAuthenticationProvider jwtAuthenticationProvider;

        @Resource
        private PermissionEvaluator permissionEvaluator;

        private static final SessionCreationPolicy STATELESS = SessionCreationPolicy.STATELESS;

        private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
                        new AntPathRequestMatcher("/accountInfos/**"),
                        new AntPathRequestMatcher("/verificationCode"),
                        new AntPathRequestMatcher("/**", "OPTIONS"));

        private static final RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(PUBLIC_URLS);

        class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {

                @Override
                public void configure(HttpSecurity http) throws Exception {
                        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
                        http
                                        .authenticationProvider(jwtAuthenticationProvider)
                                        .addFilterBefore(jwtAuthenticationFilter(authenticationManager),
                                                        AnonymousAuthenticationFilter.class);
                }

                private JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationManager authenticationManager) {
                        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(PROTECTED_URLS);
                        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
                        successHandler.setRedirectStrategy(new RedirectStrategy() {
                                @Override
                                public void sendRedirect(HttpServletRequest request, HttpServletResponse response,
                                                String url) throws IOException {
                                }
                        });
                        filter.setAuthenticationManager(authenticationManager);
                        filter.setAuthenticationSuccessHandler(successHandler);
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
                configuration.setAllowedOrigins(Arrays.asList("*"));
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
