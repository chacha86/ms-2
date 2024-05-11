package com.example.ms1.note.note.tag.tag;

import com.example.ms1.note.note.NoteUrlHandler;
import org.springframework.stereotype.Component;

@Component
public class TagUrlHandler {

    public static final String DOMAIN = "/tags";
    public static final String BASE = DOMAIN;
    public static final String ID = "tagId";
    public static final String ID_PATH_VAR = "{" + ID + "}";
    public static final String ID_PATH = "/" + ID_PATH_VAR;
    public static final String TAG_NOTES = BASE + ID_PATH + NoteUrlHandler.DOMAIN;

    public static String tagNotes(Long tagId) {
        return TAG_NOTES.replace(ID_PATH_VAR, tagId.toString());
    }

}
