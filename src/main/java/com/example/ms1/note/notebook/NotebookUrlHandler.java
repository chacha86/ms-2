package com.example.ms1.note.notebook;

import com.example.ms1.global.UrlHandler;
import org.springframework.stereotype.Component;

@Component
public class NotebookUrlHandler extends UrlHandler {
    public static final String PREFIX = "/books";
    public static final String BOOK_ID = "bookId";
    public static final String PATH_ID = "{" + BOOK_ID + "}";
    public static final String BOOK_ID_URL = "/" + PATH_ID;
    public static final String WRITE_URL = PREFIX + "/write";
    public static final String TARGET_BOOK = PREFIX + BOOK_ID_URL;
    public static final String SUB_WRITE_URL = TARGET_BOOK + "/sub-books/write";
    public static final String MOVE_URL = TARGET_BOOK + "/parent-books";
    public static final String DETAIL_URL = TARGET_BOOK;
    public static final String DELETE_URL = TARGET_BOOK + "/delete";
    public static final String UPDATE_URL = TARGET_BOOK + "/update";

    public static String getMoveUrl(Long bookId) {
        return MOVE_URL.replace(PATH_ID, bookId.toString());
    }

    public static String getSubWriteUrl(Long bookId) {
        return SUB_WRITE_URL.replace(PATH_ID, bookId.toString());
    }

    public static String getDetailUrl(Long bookId) {
        return DETAIL_URL.replace(PATH_ID, bookId.toString());
    }

    public static String getDeleteUrl(Long bookId) {
        return DELETE_URL.replace(PATH_ID, bookId.toString());
    }

    public static String getUpdateUrl(Long bookId) {
        return UPDATE_URL.replace(PATH_ID, bookId.toString());
    }

    public static String getWriteUrl() {
        return WRITE_URL;
    }
}
