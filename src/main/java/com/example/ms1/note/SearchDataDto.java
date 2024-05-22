package com.example.ms1.note;

import com.example.ms1.note.note.NoteDto;
import com.example.ms1.note.notebook.NotebookDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchDataDto {
    List<NotebookDto> searchedNotebookList = new ArrayList<>();
    List<NoteDto> searchedNoteList = new ArrayList<>();
}
