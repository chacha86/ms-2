package com.example.ms1.note.note.tag;

import com.example.ms1.note.notebook.Notebook;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notes/{noteId}/tags")
public class NoteTagController {

    private final NoteTagService noteTagService;

    @PostMapping("/create")
    public String createTag(@PathVariable("noteId") Long noteId, String name,
                          @RequestParam(value = "keyword", defaultValue = "") String keyword,
                          @RequestParam(value = "sort", defaultValue = "title") String sort,
                          @RequestParam(value = "isSearch", defaultValue = "false") boolean isSearch) {
        NoteTag noteTag = noteTagService.createTag(noteId, name);
        Notebook notebook = noteTag.getNote().getNotebook();

        return "redirect:/books/%d/notes/%d?keyword=%s&isSearch=%s&sort=%s".formatted(notebook.getId(), noteId, URLEncoder.encode(keyword, StandardCharsets.UTF_8),isSearch, sort);
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("noteId") Long noteId, @PathVariable("id") Long id,
                         @RequestParam(value = "keyword", defaultValue = "") String keyword,
                         @RequestParam(value = "sort", defaultValue = "title") String sort,
                         @RequestParam(value = "isSearch", defaultValue = "false") boolean isSearch) {
        NoteTag noteTag = noteTagService.getNoteTag(id);

        Long notebookId = noteTag.getNote().getNotebook().getId();
        noteTagService.delete(noteTag.getId());

        return "redirect:/books/%d/notes/%d?keyword=%s&isSearch=%s&sort=%s".formatted(notebookId, noteId, URLEncoder.encode(keyword, StandardCharsets.UTF_8),isSearch, sort);
    }
}
