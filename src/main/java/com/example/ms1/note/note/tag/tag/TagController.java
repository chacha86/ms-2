package com.example.ms1.note.note.tag.tag;

import com.example.ms1.global.ParamHandler;
import com.example.ms1.note.MainDataDto;
import com.example.ms1.note.MainService;
import com.example.ms1.note.note.tag.NoteTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final MainService mainService;

    @GetMapping(TagUrlHandler.TAG_NOTES)
    public String tagList(
            @PathVariable(TagUrlHandler.ID) Long tagId, Model model,
            Long notebookId, Long noteId, @ModelAttribute ParamHandler paramHandler) {

        Tag targetTag = tagService.getTag(tagId);
        MainDataDto mainDataDto = mainService.getMainData(notebookId, noteId, paramHandler.getKeyword(), paramHandler.getSort());
        List<NoteTag> list = targetTag.getNoteTagList();

        model.addAttribute("mainDataDto", mainDataDto);
        model.addAttribute("targetTag", targetTag);
        model.addAttribute("noteTagList", list);

        return "main";
    }
}
