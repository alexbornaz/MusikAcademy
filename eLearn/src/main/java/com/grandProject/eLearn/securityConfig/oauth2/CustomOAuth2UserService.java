package com.grandProject.eLearn.securityConfig.oauth2;

import com.grandProject.eLearn.model.User;
import com.grandProject.eLearn.repository.UserRepository;
import com.grandProject.eLearn.securityConfig.CustomUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.naming.AuthenticationException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return processOAuth2User(oAuth2UserRequest,oAuth2User);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

    private CustomUserDetails processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) throws AuthenticationException {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new AuthenticationException("Email not found from OAuth2 provider");
        }

        Optional<User> userOptional = userRepository.findByEmailAddress(oAuth2UserInfo.getEmail());
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setId(user.getId());
        customUserDetails.setUsername(oAuth2UserInfo.getEmail());
        customUserDetails.setFirstName(oAuth2UserInfo.getName().split(" ")[0]);
        customUserDetails.setLastName(oAuth2UserInfo.getName().split(" ")[1]);
        customUserDetails.setEmailAddress(oAuth2UserInfo.getEmail());
        customUserDetails.setPassword("12345");
        customUserDetails.setAuthorities(user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        return customUserDetails;
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();
        user.setUsername(oAuth2UserInfo.getEmail());
        user.setEmailAddress(oAuth2UserInfo.getEmail());
        user.setFirstName(oAuth2UserInfo.getName().split(" ")[0]);
        user.setLastName(oAuth2UserInfo.getName().split(" ")[1]);
        user.setPassword("12345");
        user.addRole("USER");
        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setEmailAddress(oAuth2UserInfo.getEmail());
        existingUser.setFirstName(oAuth2UserInfo.getName().split(" ")[0]);
        existingUser.setLastName(oAuth2UserInfo.getName().split(" ")[1]);
        return userRepository.save(existingUser);
    }
}
