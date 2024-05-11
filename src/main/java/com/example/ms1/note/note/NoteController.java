package com.example.ms1.note.note;

import com.example.ms1.global.DefaultParamDto;
import com.example.ms1.global.ParamModel;
import com.example.ms1.global.UrlHandler;
import com.example.ms1.note.MainDataDto;
import com.example.ms1.note.MainService;
import com.example.ms1.note.notebook.Notebook;
import com.example.ms1.note.notebook.NotebookRepository;
import com.example.ms1.note.notebook.NotebookService;
import com.example.ms1.note.notebook.NotebookUrlHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.Param;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(NoteUrlHandler.BOOK_NOTE_URL)
public class NoteController {

    private final NoteService noteService;
    private final MainService mainService;

    @PostMapping(NoteUrlHandler.WRITE_URL)
    public String write(@PathVariable(NotebookUrlHandler.BOOK_ID) Long notebookId, @ModelAttribute ParamModel paramModel) {

        mainService.addToNotebook(notebookId);
        return "redirect:" + paramModel.getParamUrl(NoteUrlHandler.MAIN_URL);
    }

    @GetMapping(NoteUrlHandler.DETAIL_URL)
    public String detail(Model model, @PathVariable(NotebookUrlHandler.BOOK_ID) Long notebookId,
                         @PathVariable(NoteUrlHandler.NOTE_ID) Long id,
                         @ModelAttribute ParamModel defaultParamDto) {

        System.out.println(defaultParamDto.getKeyword());
        System.out.println(defaultParamDto.getSort());
        MainDataDto mainDataDto = mainService.getMainData(notebookId, id, defaultParamDto.getKeyword(), defaultParamDto.getSort());
        model.addAttribute("mainDataDto", mainDataDto);
        return "main";
    }

    @PostMapping(NoteUrlHandler.UPDATE_URL)
    public String update(@PathVariable(NotebookUrlHandler.BOOK_ID) Long notebookId,
                         @PathVariable(NoteUrlHandler.NOTE_ID) Long id, String title, String content,
                         @ModelAttribute ParamModel paramModel) {
        Note note = noteService.getNote(id);

        if (title.trim().length() == 0) {
            title = "제목 없음";
        }

        note.setTitle(title);
        note.setContent(content);

        noteService.save(note);
        return "redirect:" + paramModel.getParamUrl(NoteUrlHandler.getDetailUrl(notebookId, id));
    }

    @PostMapping(NoteUrlHandler.DELETE_URL)
    public String delete(@PathVariable(NotebookUrlHandler.BOOK_ID) Long notebookId,
                         @PathVariable(NoteUrlHandler.NOTE_ID) Long id,
                         @ModelAttribute ParamModel paramModel) {

        noteService.delete(id);
        return "redirect:" + paramModel.getParamUrl(NoteUrlHandler.MAIN_URL);
    }
}
