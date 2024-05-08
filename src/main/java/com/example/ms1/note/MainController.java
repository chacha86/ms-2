package com.example.ms1.note;

import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.NoteRepository;
import com.example.ms1.note.note.NoteService;
import com.example.ms1.note.notebook.Notebook;
import com.example.ms1.note.notebook.NotebookRepository;
import com.example.ms1.note.notebook.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @RequestMapping("/")
    public String main(Model model) {

        MainDataDto mainDataDto = mainService.getDefaultMainData("title");
        model.addAttribute("mainDataDto", mainDataDto);
        return "main";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword,
                         @RequestParam(value = "isSearch", defaultValue = "false") boolean isSearch,
                         @RequestParam(value = "sort", defaultValue = "title") String sort,
                         Model model) {

        List<Notebook> notebookList = mainService.getSearchedNotebookList(keyword);
        List<Note> noteList = mainService.getSearchedNoteList(keyword);

        MainDataDto mainDataDto = mainService.getDefaultMainData(sort);

        model.addAttribute("mainDataDto", mainDataDto);
        model.addAttribute("searchedNotebookList", notebookList);
        model.addAttribute("searchedNoteList", noteList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("isSearch", isSearch);
        model.addAttribute("sort", sort);

        return "main";
    }

    @GetMapping("test")
    @ResponseBody
    public String test(String fruits) {
        return fruits;
    }
}
