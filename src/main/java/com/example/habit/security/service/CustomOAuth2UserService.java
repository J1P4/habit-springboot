package com.example.habit.security.service;


import com.example.habit.domain.User;
import com.example.habit.repository.UserRepository;
import com.example.habit.security.info.OAuth2UserInfo;
import com.example.habit.security.info.OAuth2UserInfoFactory;
import com.example.habit.security.info.UserPrincipal;
import com.example.habit.type.EProvider;
import com.example.habit.type.EProviderFactory;
import com.example.habit.type.ERole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Locale;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
         try {
             return process(userRequest, super.loadUser(userRequest));
        }catch (Exception ex){
             ex.printStackTrace();
             throw ex;
         }
    }
    public OAuth2User process(OAuth2UserRequest userRequest, OAuth2User oAuth2User){
        EProvider provider = EProviderFactory.of(userRequest.getClientRegistration().getRegistrationId().toUpperCase(Locale.ROOT));
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(provider,oAuth2User.getAttributes());

        UserRepository.UserSecurityForm userSecurityForm = userRepository.findBySocialIdAndEProvider(userInfo.getId(),provider)
                .orElseGet(()->
                {
                    User user = userRepository.save(
                            new User(
                                    userInfo.getId(),
                                    provider,
                                    ERole.GUEST
                            )
                    );
                    return UserRepository.UserSecurityForm.invoke(user);
                });

        return UserPrincipal.create(userSecurityForm,oAuth2User.getAttributes());
    }
}
