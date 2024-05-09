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

    private final String DEFAULT_SORT = "title";
    private final String DEFAULT_KEYWORD = "";
    private final boolean DEFAULT_IS_SEARCH = false;
    private final boolean DEFAULT_IS_TAG_MODAL = false;

    private String keyword;
    private Boolean isSearch;
    private Boolean isTagModal;
    private String sort;

    public DefaultParamDto(String keyword, Boolean isSearch, Boolean isTagModal, String sort) {
        this.keyword = keyword == null ? DEFAULT_KEYWORD : keyword;
        this.isSearch = isSearch == null ? DEFAULT_IS_SEARCH : isSearch;
        this.isTagModal = isTagModal == null ? DEFAULT_IS_TAG_MODAL : isTagModal;
        this.sort = sort == null ? DEFAULT_SORT : sort;
    }

    public DefaultParamDto() {
        this.keyword = DEFAULT_KEYWORD;
        this.isSearch = DEFAULT_IS_SEARCH;
        this.isTagModal = DEFAULT_IS_TAG_MODAL;
        this.sort = DEFAULT_SORT;
    }

    public String getQueryParam() {
        return String.format("keyword=%s&isSearch=%s&isTagModal=%s&sort=%s", URLEncoder.encode(keyword, StandardCharsets.UTF_8), isSearch, isTagModal, sort);
    }

    public String getQueryParam(String keyword, Boolean isSearch, Boolean isTagModal, String sort) {
        return String.format("keyword=%s&isSearch=%s&isTagModal=%s&sort=%s", URLEncoder.encode(keyword, StandardCharsets.UTF_8), isSearch, isTagModal, sort);
    }
}
