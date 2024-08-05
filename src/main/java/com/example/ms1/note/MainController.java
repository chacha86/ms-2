package com.example.ms1.note;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final NoteServiceOrchestrator noteServiceOrchestrator;

    @RequestMapping("/")
    public String main(Model model, ParamHandler paramHandler) {

        MainDataDto mainDataDto = noteServiceOrchestrator.getDefaultMainData(paramHandler.getKeyword());
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
