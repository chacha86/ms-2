package com.example.ms1.api.note.note;

import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="api/v1/notes")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Note API", description = "Note management APIs")
public class NoteRestController {
    private final NoteService noteService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Get notes in book", description = "Returns note list", security = @SecurityRequirement(name = "bearerAuth"))
    public List<NoteDto> notes(Long id) {
        List<NoteDto> noteDtoList = noteService.convertToDtoList(noteService.getNoteListByNotebook(id));
        return noteDtoList;
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Get note", description = "Returns note", security = @SecurityRequirement(name = "bearerAuth"))
    public NoteDto note(@PathVariable Long id) {
        return noteService.getNoteDto(id);
    }
}
