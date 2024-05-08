package com.example.ms1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Ms1ApplicationTests {

    @Test
    void contextLoads() {
//        hi();
        hiReccursive(5);
    }

    void hiReccursive(int n) {
        // 재귀 종료 조건
        if(n == 0) {
            return;
        }

        System.out.println("hi");
        hiReccursive(n - 1);
    }

    void hi() {
        for(int i = 0; i < 5; i++) {
            System.out.println("hi");
        }
    }

}
