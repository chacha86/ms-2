package com.example.ms1.global;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UrlHandler {

    public static final String MAIN_URL = "/";
}
