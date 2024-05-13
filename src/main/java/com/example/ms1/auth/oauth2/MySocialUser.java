package com.example.ms1.auth.oauth2;

import com.example.ms1.note.member.Member;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MySocialUser extends Member {
    private String registrationId;
    private String sub;
    private String email;

    public MySocialUser(String registrationId, String sub, String email) {
        this.registrationId = registrationId;
        this.sub = sub;
        this.email = email;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public String getSub() {
        return sub;
    }

    public String getEmail() {
        return email;
    }
}
