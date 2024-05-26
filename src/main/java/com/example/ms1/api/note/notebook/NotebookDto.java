package com.example.ms1.api.note.notebook;

import com.example.ms1.note.note.Note;
import com.example.ms1.note.notebook.Notebook;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NotebookDto {
    private Long id;
    private String title;
    private List<NotebookDto> children = new ArrayList<>();
}
