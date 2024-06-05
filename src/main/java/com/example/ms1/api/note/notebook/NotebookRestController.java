package com.example.ms1.api.note.notebook;

import com.example.ms1.note.notebook.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
@CrossOrigin(origins = "http://localhost:3000")
public class NotebookRestController {
    private final NotebookService notebookService;

    @GetMapping
    public List<NotebookDto> books() {
        System.out.println("hihi");
        List<NotebookDto> tree = notebookService.buildTree();
        return tree;
    }
}
