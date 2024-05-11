package com.example.ms1.note;

import com.example.ms1.global.ParamHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping("/")
    public String main(Model model, @ModelAttribute ParamHandler paramHandler) {

        MainDataDto mainDataDto = mainService.getDefaultMainData(paramHandler.getKeyword(), paramHandler.getSort());
        model.addAttribute("mainDataDto", mainDataDto);

        return "main";
    }

    @GetMapping("test")
    @ResponseBody
    public String test(String fruits) {
        return "test";
    }
}
