package com.example.ms1.note.note.tag.tag;

import com.example.ms1.note.note.tag.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Tag getTag(Long tagId) {
        return tagRepository.findById(tagId).orElseThrow();
    }
    public Tag getTagOrNull(String name) {
        return tagRepository.findByName(name).orElse(null);
    }

    public Tag create(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        return tagRepository.save(tag);
    }

    public List<Tag> getTagList() {
        return tagRepository.findAll();
    }
}
