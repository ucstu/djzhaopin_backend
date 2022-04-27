package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountAuthority;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountGroup;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.repository.AccountInformationRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private AccountInformationRepository accountInformationRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AccountInformation> accountInformationOptional = accountInformationRepository.findByUserName(username);
        if (!accountInformationOptional.isPresent()) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                Collection<GrantedAuthority> authorities = new HashSet<>();
                Set<AccountAuthority> accountAuthorities = accountInformationOptional.get().getAuthorities();
                Set<AccountGroup> accountGroups = accountInformationOptional.get().getGroups();
                for (AccountAuthority accountAuthority : accountAuthorities) {
                    authorities.add(new SimpleGrantedAuthority(accountAuthority.getAuthorityName()));
                }
                for (AccountGroup accountGroup : accountGroups) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + accountGroup.getGroupName()));
                }
                return authorities;
            }

            @Override
            public String getPassword() {
                return accountInformationOptional.get().getPassword();
            }

            @Override
            public String getUsername() {
                return accountInformationOptional.get().getUserName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return !accountInformationOptional.get().getExpired();
            }

            @Override
            public boolean isAccountNonLocked() {
                return !accountInformationOptional.get().getLocked();
            }

            @Override
            public boolean isCredentialsNonExpired() {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public boolean isEnabled() {
                return accountInformationOptional.get().getEnabled();
            }
        };
    }

}
