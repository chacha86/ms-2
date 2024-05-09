package com.example.ms1.note;

import com.example.ms1.global.DefaultParamDto;
import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.NoteRepository;
import com.example.ms1.note.note.NoteService;
import com.example.ms1.note.notebook.Notebook;
import com.example.ms1.note.notebook.NotebookRepository;
import com.example.ms1.note.notebook.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping("/")
    public String main(Model model, @RequestAttribute DefaultParamDto defaultParamDto) {

        MainDataDto mainDataDto = mainService.getDefaultMainData(defaultParamDto.getKeyword(), defaultParamDto.getSort());
        model.addAttribute("mainDataDto", mainDataDto);

        return "main";
    }

    @GetMapping("test")
    @ResponseBody
    public String test(String fruits) {
        return "test";
    }
}
