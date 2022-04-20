package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountAuthority;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.repository.AccountInformationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AccountInformationRepository accountInformationRepository;

    private Collection<GrantedAuthority> authorities;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountInformation accountInformation = accountInformationRepository.findByUserName(username);
        if (accountInformation == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                if (authorities == null) {
                    authorities = new HashSet<>();
                    List<AccountAuthority> accountAuthorities = accountInformation.getAuthorities();
                    for (AccountAuthority accountAuthority : accountAuthorities) {
                        authorities.add(new GrantedAuthority() {
                            @Override
                            public String getAuthority() {
                                return accountAuthority.getAuthorityName();
                            }
                        });
                    }
                }
                return authorities;
            }

            @Override
            public String getPassword() {
                return accountInformation.getPassword();
            }

            @Override
            public String getUsername() {
                return accountInformation.getUserName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return !accountInformation.getExpired();
            }

            @Override
            public boolean isAccountNonLocked() {
                return !accountInformation.getLocked();
            }

            @Override
            public boolean isCredentialsNonExpired() {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public boolean isEnabled() {
                return accountInformation.getEnabled();
            }
        };
    }
}
