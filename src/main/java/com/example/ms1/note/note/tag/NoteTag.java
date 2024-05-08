package com.example.ms1.note.note.tag;

import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.tag.tag.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class NoteTag {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Note note;

    @ManyToOne
    private Tag tag;

    private LocalDateTime createDate;

}
