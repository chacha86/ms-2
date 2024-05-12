package com.example.ms1.note;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParamHandler {
    private String keyword;
    private Boolean isSearchModal;

    public ParamHandler() {
        this.keyword = "";
        this.isSearchModal = false;
    }

    public String getQueryParam() {
        return String.format("keyword=%s&isSearchModal=%s", keyword, isSearchModal.toString());
    }

    public String getParamUrl(String url) {
        return url + "?" + getQueryParam();
    }

    public String getRedirectUrl(String url) {
        return "redirect:" + getParamUrl(url);
    }
}
