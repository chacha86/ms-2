package com.example.ms1.note.note;

import com.example.ms1.global.UrlHandler;
import com.example.ms1.note.notebook.NotebookUrlHandler;
import org.springframework.stereotype.Component;

@Component
public class NoteUrlHandler extends UrlHandler {
    public static final String PREFIX = "/notes";
    public static final String NOTE_ID = "noteId";
    public static final String PATH_ID = "{" + NOTE_ID + "}";
    public static final String NOTE_ID_URL = "/" + PATH_ID;
    public static final String BOOK_DETAIL = NotebookUrlHandler.DETAIL_URL;
    public static final String BOOK_NOTE_URL = BOOK_DETAIL + PREFIX;
    public static final String WRITE_URL = "/write";
    public static final String DETAIL_URL =  NOTE_ID_URL;
    public static final String UPDATE_URL = NOTE_ID_URL + "/update";
    public static final String DELETE_URL = NOTE_ID_URL + "/delete";

    public static String getBookUrl(Long notebookId) {
        return NotebookUrlHandler.getDetailUrl(notebookId);
    }
    public static String getPrefix(Long notebookId) {
        return getBookUrl(notebookId) + PREFIX;
    }
    public static String getWriteUrl(Long notebookId) {
        return getPrefix(notebookId) + WRITE_URL;
    }

    public static String getDetailUrl(Long notebookId, Long noteId) {
        return getPrefix(notebookId) + NOTE_ID_URL.replace(PATH_ID, noteId.toString());
    }

    public static String getUpdateUrl(Long notebookId, Long noteId) {
        return getPrefix(notebookId) + UPDATE_URL.replace(PATH_ID, noteId.toString());
    }

    public static String getDeleteUrl(Long notebookId, Long noteId) {
        return getPrefix(notebookId) + DELETE_URL.replace(PATH_ID, noteId.toString());
    }
}
