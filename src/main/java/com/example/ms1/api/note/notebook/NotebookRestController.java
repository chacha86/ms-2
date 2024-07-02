package com.example.ms1.api.note.notebook;

import com.example.ms1.note.notebook.Notebook;
import com.example.ms1.note.notebook.NotebookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Notebook API", description = "Notebook management APIs")
public class NotebookRestController {
    private final NotebookService notebookService;

    @Operation(summary = "Get book tree", description = "Returns book tree structure")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<NotebookDto> books() {
        List<NotebookDto> tree = notebookService.buildTree();
        return tree;
    }
}
