package vn.edu.nlu.fit.web.chat.security.oauth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

public class GoogleConfig {

//    @Value("${spring.security.oauth2.client.registration.google.client-id}")
//    private String clientId;
//
//    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
//    private String clientSecret;
//    @Value("${app.baseURL}")
//    private String baseUrl;
//
//    @Bean
//    public ClientRegistration googleClientRegistration() {
//        return ClientRegistration.withRegistrationId("google")
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .redirectUri(baseUrl + "/login/oauth2/callback")
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .scope("openid", "profile", "email") // Request profile and email scopes
//                .build();
//    }

}
