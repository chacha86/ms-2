package com.example.ms1.note.notebook;

import com.example.ms1.global.ParamHandler;
import com.example.ms1.note.MainUrlHandler;
import com.example.ms1.note.MainService;
import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.NoteUrlHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class NotebookController {
    private final NotebookService notebookService;
    private final MainService mainService;

    @PostMapping(NotebookUrlHandler.WRITE)
    public String write(@ModelAttribute ParamHandler paramHandler) {
        mainService.saveDefaultNotebook();
        return "redirect:" + paramHandler.getParamUrl(MainUrlHandler.MAIN_URL);
    }

    @PostMapping(NotebookUrlHandler.SUB_WRITE)
    public String groupWrite(@PathVariable(NotebookUrlHandler.ID) Long notebookId, @ModelAttribute ParamHandler paramHandler) {
        mainService.saveGroupNotebook(notebookId);
        return "redirect:" + paramHandler.getParamUrl(MainUrlHandler.MAIN_URL);
    }

    @GetMapping(NotebookUrlHandler.BOOK)
    public String detail(@PathVariable(NotebookUrlHandler.ID) Long notebookId, @ModelAttribute ParamHandler paramHandler) {
        Notebook notebook = notebookService.getNotebook(notebookId);
        Note note = notebook.getNoteList().get(0);

        return "redirect:" + paramHandler.getParamUrl(NoteUrlHandler.detail(notebook.getId(), note.getId()));
    }

    @PostMapping(NotebookUrlHandler.DELETE)
    public String delete(@PathVariable(NotebookUrlHandler.ID) Long notebookId, @ModelAttribute ParamHandler paramHandler) {
        notebookService.delete(notebookId);
        return paramHandler.getRedirectParamUrl(MainUrlHandler.MAIN_URL);
    }

    @PostMapping(NotebookUrlHandler.UPDATE)
    public String update(@PathVariable(NotebookUrlHandler.ID) Long notebookId, Long targetNoteId, String name, @ModelAttribute ParamHandler paramHandler) {
        notebookService.updateName(notebookId, name);
        return paramHandler.getRedirectParamUrl(NoteUrlHandler.detail(notebookId, targetNoteId));
    }

    @PostMapping(NotebookUrlHandler.MOVE)
    public String move(@PathVariable(NotebookUrlHandler.ID) Long targetNotebookId, Long destinationId, Long targetNoteId, @ModelAttribute ParamHandler paramHandler) {
        notebookService.move(targetNotebookId, destinationId);
        return paramHandler.getRedirectParamUrl(NoteUrlHandler.detail(targetNotebookId, targetNoteId));
    }

}
