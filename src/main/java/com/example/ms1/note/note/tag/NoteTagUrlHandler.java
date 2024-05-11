package com.example.ms1.note.note.tag;

import com.example.ms1.note.note.NoteUrlHandler;
import com.example.ms1.note.note.tag.tag.TagUrlHandler;
import org.springframework.stereotype.Component;

@Component
public class NoteTagUrlHandler {

    public static final String DOMAIN = "/noteTags";
    public static final String ID = "noteTagId";
    public static final String ID_PATH_VAR = "{" + ID + "}";
    public static final String ID_PATH = "/" + ID_PATH_VAR;
    public static final String BASE = NoteUrlHandler.NOTE + TagUrlHandler.DOMAIN;
    public static final String NOTE_TAG = BASE + ID_PATH;
    public static final String CREATE = BASE + "/create";
    public static final String DELETE = NOTE_TAG + "/delete";

    public static String create(Long noteId) {
        return CREATE.replace(NoteUrlHandler.ID_PATH_VAR, noteId.toString());
    }

    public static String delete(Long noteId, Long noteTagId) {
        return DELETE
                .replace(NoteUrlHandler.ID_PATH_VAR, noteId.toString())
                .replace(ID_PATH_VAR, noteTagId.toString());
    }

}
