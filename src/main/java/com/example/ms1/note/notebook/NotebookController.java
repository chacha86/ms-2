package com.example.ms1.note.notebook;

import com.example.ms1.global.DefaultParamDto;
import com.example.ms1.global.ParamModel;
import com.example.ms1.global.UrlHandler;
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

    @PostMapping(NotebookUrlHandler.WRITE_URL)
    public String write(@ModelAttribute ParamModel paramModel) {
        mainService.saveDefaultNotebook();
        return "redirect:" + paramModel.getParamUrl(NotebookUrlHandler.MAIN_URL);
    }

    @PostMapping(NotebookUrlHandler.SUB_WRITE_URL)
    public String groupWrite(@PathVariable(NotebookUrlHandler.BOOK_ID) Long notebookId, @ModelAttribute ParamModel paramModel) {
        mainService.saveGroupNotebook(notebookId);
        return "redirect:" + paramModel.getParamUrl(NotebookUrlHandler.MAIN_URL);
    }

    @GetMapping(NotebookUrlHandler.DETAIL_URL)
    public String detail(@PathVariable(NotebookUrlHandler.BOOK_ID) Long id, @ModelAttribute ParamModel paramModel) {
        Notebook notebook = notebookService.getNotebook(id);
        Note note = notebook.getNoteList().get(0);

        return "redirect:" + paramModel.getParamUrl(NoteUrlHandler.getDetailUrl(notebook.getId(), note.getId()));
    }

    @PostMapping(NotebookUrlHandler.DELETE_URL)
    public String delete(@PathVariable(NotebookUrlHandler.BOOK_ID) Long id, @ModelAttribute ParamModel paramModel) {
        notebookService.delete(id);
        return "redirect:" + paramModel.getParamUrl(NotebookUrlHandler.MAIN_URL);
    }

    @PostMapping(NotebookUrlHandler.UPDATE_URL)
    public String update(@PathVariable(NotebookUrlHandler.BOOK_ID) Long id, Long targetNoteId, String name, @ModelAttribute ParamModel paramModel) {
        notebookService.updateName(id, name);
        return "redirect:" + paramModel.getParamUrl(NoteUrlHandler.getDetailUrl(id, targetNoteId));
    }

    @PostMapping(NotebookUrlHandler.MOVE_URL)
    public String move(@PathVariable(NotebookUrlHandler.BOOK_ID) Long id, Long destinationId, Long targetNoteId, @ModelAttribute ParamModel paramModel) {
        notebookService.move(id, destinationId);
        return "redirect:" + paramModel.getParamUrl(NoteUrlHandler.getDetailUrl(id, targetNoteId));
    }

}
