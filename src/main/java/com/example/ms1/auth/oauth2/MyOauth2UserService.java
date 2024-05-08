package com.example.ms1.auth.oauth2;

import com.example.ms1.note.member.Member;
import com.example.ms1.note.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MyOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2User user = super.loadUser(userRequest);

        String sub = user.getAttribute("sub");
        String email = user.getAttribute("email");

        System.out.println("registrationId = " + registrationId);
        System.out.println("sub = " + sub);
        System.out.println("email = " + email);

        Member member = memberRepository.findByLoginId(sub).orElse(null);

        if(member == null) {
            member = new Member();
            member.setLoginId(sub);
            member.setEmail(email);
            member.setNickname(registrationId + "_" + sub);
            member.setPassword("");
            member.setCreateDate(LocalDateTime.now());

            memberRepository.save(member);
        }

        return super.loadUser(userRequest);
    }
}
