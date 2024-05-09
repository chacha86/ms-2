package com.example.ms1.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UrlManager {

    public static final String MAIN = "/";
    public static final String BOOKS = "/books";
    public static final String NOTES = "/notes";
    public static final String TAGS = "/tags";

    private DefaultParamDto defaultParamDto;

    public String mainUrl() {
        return MAIN;
    }

    public String bookUrl(Long id) {
        return BOOKS + "/" + id;
    }

    public String noteUrl(Long notebookId, Long noteId) {
        return BOOKS + "/" + notebookId + NOTES + "/" + noteId;
    }

    public String redirectMainParamUrl() {
        return "redirect:" + mainParamUrl();
    }

    private String mainParamUrl() {
        return MAIN + "?" + defaultParamDto.getQueryParam();
    }

    public String redirectNoteParamUrl(Long notebookId, Long noteId) {
        return "redirect:" + noteParamUrl(notebookId, noteId);
    }

    public String bookParamUrl(Long notebookId) {
        return bookUrl(notebookId) + "?" + defaultParamDto.getQueryParam();
    }

    public String bookParamUrl(Long notebookId, String keyword, Boolean isSearch, Boolean isTagModal, String sort) {
        return bookUrl(notebookId) + "?" + defaultParamDto.getQueryParam(keyword, isSearch, isTagModal, sort);
    }

    public String noteParamUrl(Long notebookId, Long noteId) {
        return noteUrl(notebookId, noteId) + "?" + defaultParamDto.getQueryParam();
    }

    public String noteParamUrl(Long notebookId, Long noteId, String keyword, Boolean isSearch, Boolean isTagModal, String sort) {
        return noteUrl(notebookId, noteId) + "?" + defaultParamDto.getQueryParam(keyword, isSearch, isTagModal, sort);
    }

    public String tagParamUrl(Long tagId) {
        return TAGS + "/" + tagId + NOTES + "?" + defaultParamDto.getQueryParam();
    }

    public String tagParamUrl(Long tagId, String keyword, Boolean isSearch, Boolean isTagModal, String sort) {
        return TAGS + "/" + tagId + NOTES + "?" + defaultParamDto.getQueryParam(keyword, isSearch, isTagModal, sort);
    }
}
