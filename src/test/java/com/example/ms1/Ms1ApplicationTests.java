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

    enum Weather {
        SUNNY(0), RAINY(1), CLOUDY(2);

        private int value;

        Weather(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Weather valueOf(int value) {
            switch (value) {
                case 0:
                    return SUNNY;
                case 1:
                    return RAINY;
                case 2:
                    return CLOUDY;
                default:
                    throw new AssertionError("Unknown value: " + value);
            }
        }


    }

    @Test
    void t1() {
        int data = 0;

        Weather weather = Weather.valueOf(data);
        System.out.println(weather);

    }

}
