package com.grandProject.eLearn.securityConfig;

import com.grandProject.eLearn.model.user.User;
import com.grandProject.eLearn.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username)
                .orElseThrow((()->new UsernameNotFoundException(String.format("Username %s not found",username))));
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
        return mapUserToCUSD(user,authorities);
    }
    private CustomUserDetails mapUserToCUSD(User user, List<SimpleGrantedAuthority> authorities){
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setId(user.getId());
        customUserDetails.setUsername(user.getUsername());
        customUserDetails.setEmailAddress(user.getEmailAddress());
        customUserDetails.setPassword(user.getPassword());
        customUserDetails.setFirstName(user.getFirstName());
        customUserDetails.setLastName(user.getLastName());
        customUserDetails.setAuthorities(authorities);
        return customUserDetails;
    }
}
