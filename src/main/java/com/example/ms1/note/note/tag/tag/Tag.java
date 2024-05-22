package com.example.ms1.note.note.tag.tag;

import com.example.ms1.note.note.tag.NoteTag;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "tag")
    @JsonBackReference
    private List<NoteTag> noteTagList = new ArrayList<>();


}
