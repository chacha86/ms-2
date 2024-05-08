package com.example.ms1.note.note.tag;

import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.tag.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteTagRepository extends JpaRepository<NoteTag, Long> {
    List<NoteTag> findByNoteOrderByCreateDate(Note targetNote);

    List<NoteTag> findByTag(Tag tag);
}
