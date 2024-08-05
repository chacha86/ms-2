package com.example.ms1.api.note.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class ResultData<T> {
    private String msg;
    private T data;

    public ResultData(T data) {
        this.msg = "success";
        this.data = data;
    }

    public ResultData() {
        this((T) new Empty());
    }
}
