package com.example.ms1.note.note.tag;

import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.tag.tag.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NoteTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tag tag;

    @ManyToOne
    private Note note;
}
