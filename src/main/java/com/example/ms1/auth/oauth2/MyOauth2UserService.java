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

    enum SocialType {
        GOOGLE("google"),
        KAKAO("kakao"),
        NAVER("naver");

        private String name;

        SocialType(String name) {
            this.name = name;
        }

        public String getValue() {
            return name;
        }
    }
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        SocialType socialType = SocialType.valueOf(registrationId.toUpperCase());

        MySocialUser mySocialUser;

        switch (socialType) {
            case GOOGLE ->
                mySocialUser = googleService(user);
            case KAKAO ->
                mySocialUser = kakaoService(user);
            case NAVER ->
                mySocialUser = naverService(user);
            default -> {
                throw new IllegalArgumentException("지원하지 않는 소셜 로그인입니다.");
            }
        }

        Member member = memberRepository.findByLoginId(mySocialUser.getSub()).orElse(null);

        if (member == null) {
            member = new Member();
            member.setLoginId(mySocialUser.getSub());
            member.setEmail(mySocialUser.getEmail());
            member.setNickname(mySocialUser.getRegistrationId() + "_" + mySocialUser.getSub());
            member.setPassword("");
            member.setCreateDate(LocalDateTime.now());

            memberRepository.save(member);
        }

        return super.loadUser(userRequest);
    }

    private MySocialUser googleService(OAuth2User user) {

        String registrationId = SocialType.GOOGLE.getValue();
        String sub = user.getAttribute("sub");
        String email = user.getAttribute("email");

        return new MySocialUser(registrationId, sub, email);
    }

    private MySocialUser kakaoService(OAuth2User user) {
        String registrationId = SocialType.KAKAO.getValue();
        Long sub = user.getAttribute("id");
        String email = null;

        return new MySocialUser(registrationId, sub.toString(), email);
    }

    private MySocialUser naverService(OAuth2User user) {
        String registrationId = SocialType.NAVER.getValue();
        Map<String, String> response = user.getAttribute("response");
        String sub = response.get("id");
        String email = response.get("email");

        return new MySocialUser(registrationId, sub, email);
    }
}
