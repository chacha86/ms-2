package com.example.ms1.note.notebook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotebookDto {
    private Long id;
    private String name;
    private LocalDateTime createDate;
}
