package com.example.ms1.note.notebook;

import org.springframework.stereotype.Component;

@Component
public class NotebookUrlHandler {

    public static final String DOMAIN = "/books";
    public static final String ID = "bookId";
    public static final String ID_PATH_VAR = "{" + ID + "}";
    public static final String ID_PATH = "/" + ID_PATH_VAR;
    public static final String BASE = DOMAIN;
    public static final String BOOK = BASE + ID_PATH;
    public static final String WRITE = BASE + "/write";
    public static final String SUB_WRITE = BOOK + "/sub-books/write";
    public static final String MOVE = BOOK + "/parent-books";
    public static final String DELETE = BOOK + "/delete";
    public static final String UPDATE = BOOK + "/update";

    public static String move(Long bookId) {
        return MOVE.replace(ID_PATH_VAR, bookId.toString());
    }

    public static String subWrite(Long bookId) {
        return SUB_WRITE.replace(ID_PATH_VAR, bookId.toString());
    }

    public static String detail(Long bookId) {
        return BOOK.replace(ID_PATH_VAR, bookId.toString());
    }

    public static String delete(Long bookId) {
        return DELETE.replace(ID_PATH_VAR, bookId.toString());
    }

    public static String update(Long bookId) {
        return UPDATE.replace(ID_PATH_VAR, bookId.toString());
    }

    public static String write() {
        return WRITE;
    }
}
