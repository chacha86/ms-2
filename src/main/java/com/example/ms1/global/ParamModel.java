package com.example.ms1.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParamModel {
    public static final String KEYWORD_KEY = "keyword";
    public static final String IS_SEARCH_KEY = "isSearch";
    public static final String IS_TAG_MODAL_KEY = "isTagModal";
    public static final String SORT_KEY = "sort";

    private String keyword;
    private Boolean isSearch;
    private Boolean isTagModal;
    private String sort;

    public ParamModel() {
        this.keyword = "";
        this.isSearch = false;
        this.isTagModal = false;
        this.sort = "title";
    }

    public String getQueryParam() {
        return String.format("keyword=%s&isSearch=%s&isTagModal=%s&sort=%s", keyword, isSearch, isTagModal, sort);
    }
    public String getParamUrl(String url) {
        return url + "?" + getQueryParam();
    }
}
