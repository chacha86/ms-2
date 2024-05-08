package com.example.ms1.note.note.tag.tag;

import com.example.ms1.note.MainDataDto;
import com.example.ms1.note.MainService;
import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.tag.NoteTag;
import com.example.ms1.note.notebook.Notebook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;
    private final MainService mainService;

    @GetMapping("{id}/notes")
    public String tagList(
            @PathVariable("id") Long tagId,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "sort", defaultValue = "title") String sort,
            @RequestParam(value = "isSearch", defaultValue = "false") boolean isSearch,
            @RequestParam(value = "isTagModal", defaultValue = "false") boolean isTagModal,
            Model model) {

        List<Notebook> notebookList = mainService.getSearchedNotebookList(keyword);
        List<Note> noteList = mainService.getSearchedNoteList(keyword);
        Tag tag = tagService.getTag(tagId);

        List<Note> noteListByTag = new ArrayList<>();
        for(NoteTag noteTag : tag.getNoteTagList()) {
            noteListByTag.add(noteTag.getNote());
        }

        MainDataDto mainDataDto = mainService.getDefaultMainData(sort);

        model.addAttribute("mainDataDto", mainDataDto);
        model.addAttribute("searchedNotebookList", notebookList);
        model.addAttribute("searchedNoteList", noteList);
        model.addAttribute("noteListByTag", noteListByTag);
        model.addAttribute("keyword", keyword);
        model.addAttribute("isSearch", isSearch);
        model.addAttribute("isTagModal", isTagModal);
        model.addAttribute("sort", sort);

        return "main";

    }
}
