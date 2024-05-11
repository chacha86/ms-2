package com.example.ms1.note.note.tag;

import com.example.ms1.global.ParamHandler;
import com.example.ms1.note.note.NoteUrlHandler;
import com.example.ms1.note.notebook.Notebook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class NoteTagController {

    private final NoteTagService noteTagService;

    @PostMapping(NoteTagUrlHandler.CREATE)
    public String createTag(@PathVariable(NoteUrlHandler.ID) Long noteId, String name,
        @ModelAttribute ParamHandler paramHandler) {

        NoteTag noteTag = noteTagService.createTag(noteId, name);
        Notebook notebook = noteTag.getNote().getNotebook();

        return paramHandler.getRedirectParamUrl(NoteUrlHandler.detail(notebook.getId(), noteId));
    }

    @PostMapping(NoteTagUrlHandler.DELETE)
    public String delete(@PathVariable(NoteUrlHandler.ID) Long noteId, @PathVariable(NoteTagUrlHandler.ID) Long id,
                         @ModelAttribute ParamHandler paramHandler) {

        NoteTag noteTag = noteTagService.getNoteTag(id);
        Long notebookId = noteTag.getNote().getNotebook().getId();
        noteTagService.delete(noteTag.getId());

        return paramHandler.getRedirectParamUrl(NoteUrlHandler.detail(notebookId, noteId));
    }
}
