package com.example.ms1.api.note.notebook;

import com.example.ms1.api.note.note.NoteDto;
import com.example.ms1.global.RsData;
import com.example.ms1.note.NoteServiceOrchestrator;
import com.example.ms1.note.notebook.NotebookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/books")
@Tag(name = "Notebook API", description = "Notebook management APIs")
public class NotebookRestController {
    private final NotebookService notebookService;
    private final NoteServiceOrchestrator noteServiceOrchestrator;

    @Operation(summary = "Get book tree", description = "Returns book tree structure")
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE )
    public RsData<List<NotebookDto>> books() {
        List<NotebookDto> tree = notebookService.buildTree();
        return new RsData<>(tree);
    }

    @GetMapping("/{id}/notes")
    @Operation(summary = "Get notes in book", description = "Returns note list", security = @SecurityRequirement(name = "bearerAuth"))
    public RsData<List<NoteDto>> notes(@PathVariable("id") Long bookId) {
        List<NoteDto> noteList = noteServiceOrchestrator.getNoteList(bookId);
        return new RsData<>(noteList);
    }
}
