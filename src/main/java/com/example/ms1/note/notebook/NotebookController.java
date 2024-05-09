package com.example.ms1.note.notebook;

import com.example.ms1.global.DefaultParamDto;
import com.example.ms1.global.UrlManager;
import com.example.ms1.note.MainService;
import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
public class NotebookController {

    private final NotebookService notebookService;
    private final MainService mainService;
    private final UrlManager urlManager;

    @PostMapping("/books/write")
    public String write() {
        mainService.saveDefaultNotebook();
        return urlManager.redirectMainParamUrl();

    }

    @PostMapping("/groups/{notebookId}/books/write")
    public String groupWrite(@PathVariable("notebookId") Long notebookId) {
        mainService.saveGroupNotebook(notebookId);
        return urlManager.redirectMainParamUrl();
    }

    @GetMapping("/books/{id}")
    public String detail(@PathVariable("id") Long id, @ModelAttribute DefaultParamDto defaultParamDto) {
        Notebook notebook = notebookService.getNotebook(id);
        Note note = notebook.getNoteList().get(0);

        urlManager.setDefaultParamDto(defaultParamDto);
        return urlManager.redirectNoteParamUrl(notebook.getId(), note.getId());
    }

    @PostMapping("/books/{id}/delete")
    public String delete(@PathVariable("id") Long id,@ModelAttribute DefaultParamDto defaultParamDto) {
        notebookService.delete(id);
        return urlManager.redirectMainParamUrl();
    }

    @PostMapping("/books/{id}/update")
    public String update(@PathVariable("id") Long id, Long targetNoteId, String name, @ModelAttribute DefaultParamDto defaultParamDto) {
        notebookService.updateName(id, name);
        urlManager.setDefaultParamDto(defaultParamDto);
        return urlManager.redirectNoteParamUrl(id, targetNoteId);
    }

    @PostMapping("/books/{id}/move")
    public String move(@PathVariable("id") Long id, Long destinationId, Long targetNoteId, @ModelAttribute DefaultParamDto defaultParamDto) {
        notebookService.move(id, destinationId);

        urlManager.setDefaultParamDto(defaultParamDto);
        return urlManager.redirectNoteParamUrl(id, targetNoteId);
    }
}
