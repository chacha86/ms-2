package com.example.ms1.note.note;

import com.example.ms1.global.ParamHandler;
import com.example.ms1.note.MainDataDto;
import com.example.ms1.note.MainService;
import com.example.ms1.note.MainUrlHandler;
import com.example.ms1.note.notebook.NotebookUrlHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    private final MainService mainService;

    @PostMapping(NoteUrlHandler.WRITE)
    public String write(@PathVariable(NotebookUrlHandler.ID) Long notebookId, @ModelAttribute ParamHandler paramHandler) {

        mainService.addToNotebook(notebookId);
        return paramHandler.getRedirectParamUrl(MainUrlHandler.MAIN_URL);
    }

    @GetMapping(NoteUrlHandler.BOOK_NOTE)
    public String detail(Model model, @PathVariable(NotebookUrlHandler.ID) Long notebookId,
                         @PathVariable(NoteUrlHandler.ID) Long id,
                         @ModelAttribute ParamHandler defaultParamDto) {

        MainDataDto mainDataDto = mainService.getMainData(notebookId, id, defaultParamDto.getKeyword(), defaultParamDto.getSort());
        model.addAttribute("mainDataDto", mainDataDto);
        return "main";
    }

    @PostMapping(NoteUrlHandler.UPDATE)
    public String update(@PathVariable(NotebookUrlHandler.ID) Long notebookId,
                         @PathVariable(NoteUrlHandler.ID) Long id, String title, String content,
                         @ModelAttribute ParamHandler paramHandler) {
        Note note = noteService.getNote(id);

        if (title.trim().length() == 0) {
            title = "제목 없음";
        }

        note.setTitle(title);
        note.setContent(content);

        noteService.save(note);
        return paramHandler.getRedirectParamUrl(NoteUrlHandler.detail(notebookId, id));
    }

    @PostMapping(NoteUrlHandler.DELETE)
    public String delete(@PathVariable(NotebookUrlHandler.ID) Long notebookId,
                         @PathVariable(NoteUrlHandler.ID) Long id,
                         @ModelAttribute ParamHandler paramHandler) {

        noteService.delete(id);
        return paramHandler.getRedirectParamUrl(MainUrlHandler.MAIN_URL);
    }
}
