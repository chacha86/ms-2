package com.example.ms1.note.note;

import com.example.ms1.note.notebook.Notebook;
import com.example.ms1.note.notebook.NotebookDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoteDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private NotebookDto notebook;
}
