package com.example.ms1.note.notebook;

import com.example.ms1.note.MainService;
import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
public class NotebookController {

    private final NotebookService notebookService;
    private final MainService mainService;

    @PostMapping("/books/write")
    public String write() {
        mainService.saveDefaultNotebook();
        return "redirect:/";

    }

    @PostMapping("/groups/{notebookId}/books/write")
    public String groupWrite(@PathVariable("notebookId") Long notebookId) {

        mainService.saveGroupNotebook(notebookId);
        return "redirect:/";
    }

    @GetMapping("/books/{id}")
    public String detail(@PathVariable("id") Long id,
                         @RequestParam(value = "keyword", defaultValue = "") String keyword,
                         @RequestParam(value = "isSearch", defaultValue = "false") boolean isSearch,
                         @RequestParam(value = "sort", defaultValue = "title") String sort) {
        Notebook notebook = notebookService.getNotebook(id);
        Note note = notebook.getNoteList().get(0);

        return "redirect:/books/%d/notes/%d?keyword=%s&isSearch=%s&sort=%d".formatted(id, note.getId(), URLEncoder.encode(keyword, StandardCharsets.UTF_8),isSearch, sort);
    }

    @PostMapping("/books/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
//        mainService.delete(id);
        notebookService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/books/{id}/update")
    public String update(@PathVariable("id") Long id, Long targetNoteId, String name) {
        notebookService.updateName(id, name);
        return "redirect:/books/%d/notes/%d".formatted(id, targetNoteId);
    }

    @PostMapping("/books/{id}/move")
    public String move(@PathVariable("id") Long id, Long destinationId, Long targetNoteId) {
        notebookService.move(id, destinationId);

        return "redirect:/books/%d/notes/%d".formatted(destinationId, targetNoteId);
    }
}
