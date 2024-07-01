package com.example.ms1.note;

import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.NoteRepository;
import com.example.ms1.note.note.NoteService;
import com.example.ms1.note.notebook.Notebook;
import com.example.ms1.note.notebook.NotebookRepository;
import com.example.ms1.note.notebook.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @RequestMapping("/")
    public String main(Model model, ParamHandler paramHandler) {

        MainDataDto mainDataDto = mainService.getDefaultMainData(paramHandler.getKeyword());
        model.addAttribute("mainDataDto", mainDataDto);
        return "main";
    }

    @GetMapping("test")
    @ResponseBody
    public String test(String fruits) {

        ClassPathResource resource = new ClassPathResource("com/example/ms1/files/test.txt");
        String content = "";
        try {
            System.out.println(resource.getURI());
            content = new String(Files.readAllBytes(Paths.get(resource.getURI())));

            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    @GetMapping("test2")
    public String test2() {
        return "test";
    }
}
