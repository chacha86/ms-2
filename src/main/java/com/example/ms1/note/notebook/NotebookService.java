package com.example.ms1.note.notebook;

import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotebookService {
    private final NotebookRepository notebookRepository;

    public Notebook getNotebook(Long notebookId) {
        return notebookRepository.findById(notebookId).orElseThrow();
    }

    public List<Notebook> getNotebookList() {
        return notebookRepository.findAll();
    }

    public Notebook save(Notebook notebook) {
        return notebookRepository.save(notebook);
    }

    public void delete(Long id) {
        notebookRepository.deleteById(id);
    }

    public Notebook updateName(Long id, String name) {
        Notebook notebook = getNotebook(id);
        notebook.setName(name);
        return notebookRepository.save(notebook);
    }

    public List<Notebook> getTopDepthList() {
        return notebookRepository.findByParentIsNull();
    }

    public void move(Long id, Long moveTargetId) {

        Notebook notebook = getNotebook(id);

        if (moveTargetId == -1) {
            notebook.setParent(null);
        } else {
            Notebook moveTarget = getNotebook(moveTargetId);
            notebook.setParent(moveTarget);
        }

        notebookRepository.save(notebook);
    }
}
