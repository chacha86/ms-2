package com.example.ms1.note.note;

import com.example.ms1.note.notebook.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByNotebook(Notebook notebook);

    List<Note> findByTitleContaining(String keyword);

    // 날짜로 정렬된 노트 리스트
    List<Note> findByNotebookOrderByCreateDateDesc(Notebook notebook);

    // 제목으로 정렬된 노트 리스트
    List<Note> findByNotebookOrderByTitleAsc(Notebook notebook);
    List<Note> findByNotebookOrderByTitleDesc(Notebook notebook);

    // 제목으로 정렬된 특정 노트북의 노트 리스트

}
