package com.example.ms1.note.note;

import com.example.ms1.note.notebook.NotebookUrlHandler;
import org.springframework.stereotype.Component;

@Component
public class NoteUrlHandler {
    public static final String DOMAIN = "/notes";
    public static final String ID = "noteId";
    public static final String ID_PATH_VAR = "{" + ID + "}";
    public static final String ID_PATH = "/" + ID_PATH_VAR;
    public static final String NOTE = DOMAIN + ID_PATH;
    public static final String BASE = NotebookUrlHandler.BOOK + DOMAIN;
    public static final String BOOK_NOTE = BASE + ID_PATH;
    public static final String WRITE = BASE + "/write";
    public static final String UPDATE = BOOK_NOTE + "/update";
    public static final String DELETE = BOOK_NOTE + "/delete";

    public static String write(Long notebookId) {
        return WRITE.replace(NotebookUrlHandler.ID_PATH_VAR, notebookId.toString());
    }

    public static String detail(Long notebookId, Long noteId) {
        return replaceIdPathVar(BOOK_NOTE, notebookId, noteId);
    }

    public static String update(Long notebookId, Long noteId) {
        return replaceIdPathVar(UPDATE, notebookId, noteId);
    }

    public static String delete(Long notebookId, Long noteId) {
        return replaceIdPathVar(DELETE, notebookId, noteId);
    }

    private static String replaceIdPathVar(String url, Long notebookId, Long noteId) {
        return url
                .replace(NotebookUrlHandler.ID_PATH_VAR, notebookId.toString())
                .replace(ID_PATH_VAR, noteId.toString());
    }
}
