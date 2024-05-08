package com.example.ms1.note.note.tag.tag;

import com.example.ms1.note.note.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Tag createTag(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        tag.setCreateDate(LocalDateTime.now());

        return tagRepository.save(tag);
    }

    public List<Tag> getTagList() {
        return tagRepository.findAll();
    }

    public Tag getTag(Long tagId) {
        return tagRepository.findById(tagId).orElseThrow();
    }

    public Tag getTagOrNull(String name) {
        return tagRepository.findByName(name).orElse(null);
    }
}
