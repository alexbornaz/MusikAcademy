package com.grandProject.eLearn.securityConfig.oauth2;

import javax.naming.AuthenticationException;
import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) throws AuthenticationException {
        if(registrationId.equalsIgnoreCase("google")) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new AuthenticationException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
