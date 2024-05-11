package com.example.ms1.global;

import lombok.Getter;
import lombok.Setter;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Getter
@Setter
public class ParamHandler {
    public static final String KEYWORD_KEY = "keyword";
    public static final String IS_SEARCH_KEY = "isSearch";
    public static final String IS_TAG_MODAL_KEY = "isTagModal";
    public static final String SORT_KEY = "sort";

    private String keyword;
    private Boolean isSearch;
    private Boolean isTagModal;
    private String sort;

    public ParamHandler() {
        this.keyword = "";
        this.isSearch = false;
        this.isTagModal = false;
        this.sort = "title";
    }

    public String getQueryParam() {
        return String.format("keyword=%s&isSearch=%s&isTagModal=%s&sort=%s", URLEncoder.encode(keyword, StandardCharsets.UTF_8), isSearch, isTagModal, sort);
    }
    public String getParamUrl(String url) {
        return url + "?" + getQueryParam();
    }

    public String getRedirectParamUrl(String url) {
        return "redirect:" + getParamUrl(url);
    }
}
