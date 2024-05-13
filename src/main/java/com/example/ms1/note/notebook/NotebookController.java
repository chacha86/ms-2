package com.example.ms1.note.notebook;

import com.example.ms1.note.MainService;
import com.example.ms1.note.MainUrlHandler;
import com.example.ms1.note.ParamHandler;
import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.NoteService;
import com.example.ms1.note.note.NoteUrlHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URLEncoder;

@Controller
@RequiredArgsConstructor
public class NotebookController {

    private final NotebookService notebookService;
    private final MainService mainService;

    @PostMapping(NotebookUrlHandler.WRITE)
    public String write(ParamHandler paramHandler) {
        mainService.saveDefaultNotebook();
        return paramHandler.getRedirectUrl(MainUrlHandler.BASE);
    }

    @PostMapping(NotebookUrlHandler.GROUP_WRITE)
    public String groupWrite(@PathVariable(NotebookUrlHandler.ID) Long notebookId, ParamHandler paramHandler) {

        mainService.saveGroupNotebook(notebookId);
        return paramHandler.getRedirectUrl(MainUrlHandler.BASE);
    }

    @GetMapping(NotebookUrlHandler.BOOK)
    public String detail(@PathVariable(NotebookUrlHandler.ID) Long id, ParamHandler paramHandler) {
        Notebook notebook = notebookService.getNotebook(id);
        Note note = notebook.getNoteList().get(0);

        return paramHandler.getRedirectUrl(NoteUrlHandler.bookNote(id, note.getId()));
    }

    @PostMapping(NotebookUrlHandler.DELETE)
    public String delete(@PathVariable(NotebookUrlHandler.ID) Long id, ParamHandler paramHandler) {
        notebookService.delete(id);
        return paramHandler.getRedirectUrl(MainUrlHandler.BASE);
    }

    @PostMapping(NotebookUrlHandler.UPDATE)
    public String update(@PathVariable(NotebookUrlHandler.ID) Long id, Long targetNoteId, String name, ParamHandler paramHandler) {
        notebookService.updateName(id, name);
        return paramHandler.getRedirectUrl(NoteUrlHandler.bookNote(id, targetNoteId));
    }

    @PostMapping(NotebookUrlHandler.MOVE)
    public String move(@PathVariable(NotebookUrlHandler.ID) Long id, Long destinationId, Long targetNoteId, ParamHandler paramHandler) {
        notebookService.move(id, destinationId);

        return paramHandler.getRedirectUrl(NoteUrlHandler.bookNote(id, destinationId));
    }
}
