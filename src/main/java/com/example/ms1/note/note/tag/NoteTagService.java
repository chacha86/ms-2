package com.example.ms1.note.note.tag;

import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.NoteService;
import com.example.ms1.note.note.tag.tag.Tag;
import com.example.ms1.note.note.tag.tag.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteTagService {

    private final NoteTagRepository noteTagRepository;
    private final TagService tagService;
    private final NoteService noteService;

    public NoteTag createTag(Long noteId, String name) {

        Note note = noteService.getNote(noteId);
        Tag tag = tagService.getTagOrNull(name);

        if(tag == null) {
            tag = tagService.createTag(name);
        }

        NoteTag noteTag = new NoteTag();
        noteTag.setNote(note);
        noteTag.setTag(tag);

        return noteTagRepository.save(noteTag);
    }

    public List<NoteTag> getNoteTagList(Note targetNote) {
        return noteTagRepository.findByNoteOrderByCreateDate(targetNote);
    }

    public void delete(Long id) {
        noteTagRepository.deleteById(id);
    }

    public NoteTag getNoteTag(Long id) {
        return noteTagRepository.findById(id).orElseThrow();
    }

    public List<NoteTag> getNoteTagListByTag(Tag tag) {
        return noteTagRepository.findByTag(tag);
    }
}
