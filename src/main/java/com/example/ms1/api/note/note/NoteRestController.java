package com.example.ms1.api.note.note;

import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notes")
@CrossOrigin(origins = "http://localhost:3000")
public class NoteRestController {
    private final NoteService noteService;

    @GetMapping
    public List<NoteDto> notes(Long bookId) {
        List<NoteDto> noteDtoList = noteService.convertToDtoList(noteService.getNoteListByNotebook(bookId));
        return noteDtoList;
    }
}
