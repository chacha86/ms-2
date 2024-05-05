package com.example.ms1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Ms1ApplicationTests {

    @Test
    void contextLoads() {
//        recur(0, 0, 10);
//        hanoi("A", "B", "C", 3);
//        fibonacci(0, 12, 0, 1);
        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
        int rst = fibonacci(45);
        System.out.println(rst);
        System.out.println(cnt);
        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);

        beforeTime = System.currentTimeMillis();
        int rst2 = fibonacciTail(0, 45, 0, 1);
        System.out.println(rst2);
        System.out.println(cnt2);
        afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        System.out.println("시간차이(m) : "+(afterTime - beforeTime));

        beforeTime = System.currentTimeMillis();
        int rst3 = fibonacci2(45);
        System.out.println(rst3);
        System.out.println(cnt3);
        afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        System.out.println("시간차이(m) : "+(afterTime - beforeTime));
    }
    // 0 1 1 2 3 5 8 13 21 34 55 89 144 ...
    //memoization

    boolean[] memo = new boolean[100];
    int[] memo2 = new int[100];

    int cnt = 0;
    int cnt2 = 0;
    int cnt3 = 0;

    int fibonacciTail(int n, int limit, int prev, int current) {
        cnt2 += 1;
        if (n == limit-1) return current;
        return fibonacciTail(n + 1, limit, current, prev + current);
    }

    int fibonacci2(int n) {
        cnt3 += 1;

        if (n == 0) return 0;
        if (n == 1) return 1;

        return fibonacci2(n - 1) + fibonacci2(n - 2);
    }

    int fibonacci(int n) {
        cnt += 1;
        if (n == 0) return 0;
        if (n == 1) return 1;

        if (memo[n] != true) {
            memo[n] = true;
            memo2[n] = fibonacci(n - 1) + fibonacci(n - 2);
        }
        return memo2[n];
    }


    // normal
    void fibonacci(int n, int limit, int prev, int current) {

        if (n == limit - 1) {
            System.out.println(current + " ");
            return;
        }
        if (n == 0) System.out.print(prev + " ");
        System.out.print(current + " ");
        fibonacci(n + 1, limit, current, prev + current);
    }

    void hanoi(String start, String tmp, String end, int n) {
        if (n == 1) {
            System.out.println(n + "번 원반을 " + start + "에서 " + end + "으로 옮깁니다.");
            return;
        }
        hanoi(start, end, tmp, n - 1);
        System.out.println(n + "번 원반을 " + start + "에서 " + end + "으로 옮깁니다.");
        hanoi(tmp, start, end, n - 1);
    }

    void recur(int n, int sum, int limit) {

        if (n > limit) {
            System.out.println(sum);
            return;
        }
        recur(n + 1, sum + n, limit);

    }
}
