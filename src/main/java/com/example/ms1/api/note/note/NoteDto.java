package com.example.ms1.api.note.note;

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
}
