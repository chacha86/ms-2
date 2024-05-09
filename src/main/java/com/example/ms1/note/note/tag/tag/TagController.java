package com.example.ms1.note.note.tag.tag;

import com.example.ms1.global.DefaultParamDto;
import com.example.ms1.global.UrlManager;
import com.example.ms1.note.MainDataDto;
import com.example.ms1.note.MainService;
import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.tag.NoteTag;
import com.example.ms1.note.notebook.Notebook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;
    private final MainService mainService;
    private final UrlManager urlManager;

    @GetMapping("{id}/notes")
    public String tagList(
            @PathVariable("id") Long tagId, Model model, @ModelAttribute("defaultParamDto") DefaultParamDto defaultParamDto) {

        Tag tag = tagService.getTag(tagId);
        MainDataDto mainDataDto = mainService.getDefaultMainData(defaultParamDto.getKeyword(), defaultParamDto.getSort());
        List<NoteTag> list = tag.getNoteTagList();

        model.addAttribute("mainDataDto", mainDataDto);
        model.addAttribute("targetTag", tag);
        model.addAttribute("noteTagList", list);

        return "main";
    }
}
