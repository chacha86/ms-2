package com.example.ms1.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.buf.Utf8Encoder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Getter
@Setter
public class DefaultParamDto {

    private static final String DEFAULT_SORT = "title";
    private static final String DEFAULT_KEYWORD = "";
    private static final boolean DEFAULT_IS_SEARCH = false;
    private static final boolean DEFAULT_IS_TAG_MODAL = false;

    private static DefaultParamDto instance;
    private String keyword;
    private Boolean isSearch;
    private Boolean isTagModal;
    private String sort;

    private DefaultParamDto() {
    }

    public static DefaultParamDto getInstance() {
        if (instance == null) {
            instance = new DefaultParamDto();
            instance.setParams(DEFAULT_KEYWORD, DEFAULT_IS_SEARCH, DEFAULT_IS_TAG_MODAL, DEFAULT_SORT);
        }
        return instance;
    }

    public String getQueryParam() {
        return String.format("keyword=%s&isSearch=%s&isTagModal=%s&sort=%s", URLEncoder.encode(keyword, StandardCharsets.UTF_8), isSearch, isTagModal, sort);
    }

    public String getQueryParam(String keyword, Boolean isSearch, Boolean isTagModal, String sort) {
        return String.format("keyword=%s&isSearch=%s&isTagModal=%s&sort=%s", URLEncoder.encode(keyword, StandardCharsets.UTF_8), isSearch, isTagModal, sort);
    }

    public void setParams(String keyword, Boolean isSearch, Boolean isTagModal, String sort) {
        this.keyword = keyword == null ? DEFAULT_KEYWORD : keyword;
        this.isSearch = isSearch == null ? DEFAULT_IS_SEARCH : isSearch;
        this.isTagModal = isTagModal == null ? DEFAULT_IS_TAG_MODAL : isTagModal;
        this.sort = sort == null ? DEFAULT_SORT : sort;
    }
}
