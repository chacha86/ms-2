package com.example.ms1.note;

import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.NoteDto;
import com.example.ms1.note.notebook.Notebook;
import com.example.ms1.note.notebook.NotebookDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDataDto {
    List<NotebookDto> searchedNotebookList = new ArrayList<>();
    List<NoteDto> searchedNoteList = new ArrayList<>();
}
