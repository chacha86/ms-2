package com.example.ms1.api.note.note;

import com.example.ms1.global.RsData;
import com.example.ms1.note.NoteServiceOrchestrator;
import com.example.ms1.note.note.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="api/v1/notes", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Note API", description = "Note management APIs")
public class NoteRestController {
    private final NoteService noteService;
    private final NoteServiceOrchestrator noteServiceOrchestrator;

//    @Operation(summary = "Get notes in book", description = "Returns note list", security = @SecurityRequirement(name = "bearerAuth"))
//    public RData<List<NoteDto>> notes(Long bookId) {
//        List<NoteDto> noteList = noteService.getNoteListByBookId(bookId);
//        return new RData<>(noteList);
//    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get note", description = "Returns note", security = @SecurityRequirement(name = "bearerAuth"))
    public RsData<NoteDto> note(@PathVariable("id") Long noteId) {
        NoteDto note = noteService.getNoteDto(noteId);
        return new RsData<>(note);
    }
}
