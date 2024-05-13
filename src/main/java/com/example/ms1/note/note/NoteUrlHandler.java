package com.example.ms1.note.note;

import com.example.ms1.note.notebook.NotebookUrlHandler;
import org.springframework.stereotype.Component;

@Component
public class NoteUrlHandler {
    public static final String DOMAIN = "/notes";
    public static final String ID = "noteId";
    public static final String ID_VAR = "{" + ID + "}";
    public static final String ID_PATH = "/" + ID_VAR;
    public static final String BASE = NotebookUrlHandler.BOOK + DOMAIN;
    public static final String NOTE = DOMAIN + ID_PATH;
    public static final String BOOK_NOTE = NotebookUrlHandler.BOOK + NOTE;
    public static final String WRITE = BASE + "/write";
    public static final String DELETE = BOOK_NOTE + "/delete";
    public static final String UPDATE = BOOK_NOTE + "/update";

    public static String bindIdToUrl(String url, Long noteId) {
        return url.replace(ID_VAR, noteId.toString());
    }

    public static String book(Long notebookId) {
        return NotebookUrlHandler.book(notebookId);
    }

    public static String note(Long noteId) {
        return bindIdToUrl(NOTE, noteId);
    }

    public static String update(Long notebookId, Long noteId) {
        return bindIdToUrl(NotebookUrlHandler.bindIdToUrl(UPDATE, notebookId), noteId);
    }

    public static String delete(Long notebookId, Long noteId) {
        return bindIdToUrl(NotebookUrlHandler.bindIdToUrl(DELETE, notebookId), noteId);
    }

    public static String write() {
        return WRITE;
    }

    public static String bookNote(Long bookId, Long noteId) {
        return bindIdToUrl(NotebookUrlHandler.bindIdToUrl(BOOK_NOTE, bookId), noteId);
    }

}
