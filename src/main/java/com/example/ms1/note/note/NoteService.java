package com.example.ms1.note.note;

import com.example.ms1.api.note.note.NoteDto;
import com.example.ms1.api.note.notebook.NotebookDto;
import com.example.ms1.note.notebook.Notebook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public Note saveDefault() {
        Note note = new Note();
        note.setTitle("new title..");
        note.setContent("");
        note.setCreateDate(LocalDateTime.now());

        return noteRepository.save(note);
    }

    public Note getNote(Long id) {
        return noteRepository.findById(id).orElseThrow();
    }

    public List<Note> getNoteListByNotebook(Long targetNotebookId) {
        return noteRepository.findByNotebookId(targetNotebookId);
    }

    public void save(Note note) {
        noteRepository.save(note);
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    public List<Note> getSearchedNoteList(String keyword) {
        return noteRepository.findByTitleContaining(keyword);
    }

    public List<Note> getSortedListByCreateDate(Notebook targetNotebook) {
        return noteRepository.findByNotebookOrderByCreateDateDesc(targetNotebook);
    }

    public List<Note> getSortedListByTitle(Notebook targetNotebook) {
        return noteRepository.findByNotebookOrderByTitle(targetNotebook);
    }

    public List<NoteDto> convertToDtoList(List<Note> noteList) {
        return noteList.stream().map((note) -> {
            NoteDto noteDto = new NoteDto();
            noteDto.setId(note.getId());
            noteDto.setTitle(note.getTitle());
            noteDto.setContent(note.getContent());
            return noteDto;
        }).toList();
    }
}
