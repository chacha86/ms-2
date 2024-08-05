package com.example.ms1.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

enum ResultCode {
    SUCCESS("success"),
    FAIL("fail");

    private final String code;

    ResultCode(String code) {
        this.code = code;
    }
}

@Getter
@AllArgsConstructor
public class RsData<T> {

    private final ResultCode resultCode;
    private final String message;
    private final T body;

    public RsData() {
        this.resultCode = ResultCode.SUCCESS;
        this.message = "success";
        this.body = (T) new Empty();
    }

    public RsData(T body) {
        this.resultCode = ResultCode.SUCCESS;
        this.message = "success";
        this.body = body;
    }
}
