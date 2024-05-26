package com.example.ms1.api.note;

import com.example.ms1.api.note.notebook.NotebookDto;
import com.example.ms1.note.notebook.Notebook;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookTreeDto {
    private NotebookDto notebook;
    private List<BookTreeDto> children;
}
