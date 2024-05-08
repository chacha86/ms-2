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
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MyOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        switch (registrationId) {
            case "google":
                return googleService(user);
            case "kakao":
                return kakaoService(user);
            case "naver":
                return naverService(user);
        }

        return super.loadUser(userRequest);
    }

    private OAuth2User googleService(OAuth2User user) {

        String registrationId = "google";
        String sub = user.getAttribute("sub");
        String email = user.getAttribute("email");

        Member member = memberRepository.findByLoginId(sub).orElse(null);

        if(member == null) {
            saveMember(registrationId, sub, email);
        }

        return user;
    }

    private OAuth2User kakaoService(OAuth2User user) {
        String registrationId = "kakao";
        Long sub = user.getAttribute("id");
        String email = null;

        Member member = memberRepository.findByLoginId(String.valueOf(sub)).orElse(null);

        if(member == null) {
            saveMember(registrationId, String.valueOf(sub), email);
        }

        return user;
    }

    private OAuth2User naverService(OAuth2User user) {
        String registrationId = "naver";
        Map<String, String> response = user.getAttribute("response");
        String sub = response.get("id");
        String email = response.get("email");

        Member member = memberRepository.findByLoginId(sub).orElse(null);

        if(member == null) {
            saveMember(registrationId, sub, email);
        }

        return user;
    }

    private Member saveMember(String registrationId, String sub, String email) {
        Member member = new Member();
        member.setLoginId(sub);
        member.setEmail(email);
        member.setNickname(registrationId + "_" + sub);
        member.setPassword("");
        member.setCreateDate(LocalDateTime.now());

        return memberRepository.save(member);
    }
}
