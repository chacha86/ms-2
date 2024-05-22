package com.example.ms1.note.note;

import com.example.ms1.note.notebook.NotebookDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {
    private Long id;
    private String title;
    private String content;
    private NotebookDto notebook;
    private LocalDateTime createDate;
}
