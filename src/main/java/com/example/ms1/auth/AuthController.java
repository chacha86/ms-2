package com.example.ms1.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    @GetMapping("/check")
    public String check(@RequestParam Boolean flag) {
        if(flag)
            return "{\"result\" : \"success\"}";
        else
            return "{\"result\" : \"fail\"}";
    }
}
