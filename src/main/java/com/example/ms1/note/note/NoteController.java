package com.example.ms1.note.note;

import com.example.ms1.api.note.note.NoteDto;
import com.example.ms1.note.MainDataDto;
import com.example.ms1.note.NoteServiceOrchestrator;
import com.example.ms1.note.ParamHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books/{notebookId}/notes")
public class NoteController {

    private final NoteService noteService;
    private final NoteServiceOrchestrator noteServiceOrchestrator;

    @PostMapping("/write")
    public String write(@PathVariable("notebookId") Long notebookId, ParamHandler paramHandler) {

        noteServiceOrchestrator.addToNotebook(notebookId);
        return paramHandler.getRedirectUrl("/");
    }

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable("notebookId") Long notebookId, @PathVariable("id") Long id,
                         ParamHandler paramHandler) {

        MainDataDto mainDataDto = noteServiceOrchestrator.getMainData(notebookId, id, paramHandler.getKeyword(), paramHandler.getSort());
        model.addAttribute("mainDataDto", mainDataDto);

        return "main";
    }
    @PostMapping("/{id}/update")
    public String update(@PathVariable("notebookId") Long notebookId, NoteDto noteDto, ParamHandler paramHandler){
        noteService.update(noteDto);
        return paramHandler.getRedirectUrl("/books/%d/notes/%d".formatted(notebookId, noteDto.getId()));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("notebookId") Long notebookId, @PathVariable("id") Long id, ParamHandler paramHandler) {

        noteService.delete(id);
        return paramHandler.getRedirectUrl("/");
    }


}
