package com.example.ms1.note.notebook;

import org.springframework.stereotype.Component;

@Component
public class NotebookUrlHandler {
    public static final String DOMAIN = "/books";
    public static final String BASE = DOMAIN;
    public static final String ID = "bookId";
    public static final String ID_VAR = "{" + ID + "}";
    public static final String ID_PATH = "/" + ID_VAR;
    public static final String BOOK = BASE + ID_PATH;
    public static final String WRITE = BASE + "/write";
    public static final String GROUP_WRITE = BOOK + "/sub-books/write";
    public static final String DELETE = BOOK + "/delete";
    public static final String UPDATE = BOOK + "/update";
    public static final String MOVE = BOOK + "/parent-book";

    public static String bindIdToUrl(String url, Long bookId) {
        return url.replace(ID_VAR, bookId.toString());
    }

    public static String book(Long bookId) {
        return bindIdToUrl(BOOK, bookId);
    }

    public static String update(Long bookId) {
        return bindIdToUrl(UPDATE, bookId);
    }

    public static String delete(Long bookId) {
        return bindIdToUrl(DELETE, bookId);
    }

    public static String move(Long bookId) {
        return bindIdToUrl(MOVE, bookId);
    }

    public static String write() {
        return WRITE;
    }

    public static String groupWrite(Long bookId) {
        return bindIdToUrl(GROUP_WRITE, bookId);
    }

}
