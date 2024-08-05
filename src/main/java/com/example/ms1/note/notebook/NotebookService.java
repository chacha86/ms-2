package com.example.ms1.note.notebook;

import com.example.ms1.api.note.notebook.NotebookDto;
import jakarta.transaction.Transactional;
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

    public List<Notebook> getTopNotebookList() {
        return notebookRepository.findByParentIsNull();
    }

    public void move(Long id, Long destinationId) {
        Notebook target = getNotebook(id);
        Notebook destination = getNotebook(destinationId);

        target.setParent(destination);
        notebookRepository.save(target);
    }

    public List<Notebook> getSearchedNotebookList(String keyword) {
        return notebookRepository.findByNameContaining(keyword);
    }

    public List<NotebookDto> convertToDtoList(List<Notebook> notebookList) {
        return notebookList.stream().map((notebook) -> {
            NotebookDto notebookDto = new NotebookDto();
            notebookDto.setId(notebook.getId());
            notebookDto.setTitle(notebook.getName());
            return notebookDto;
        }).toList();
    }

    public List<NotebookDto> getNotebookDtoList(NotebookDto parent) {
        List<Notebook> notebookList = notebookRepository.findByParentId(parent.getId());
        return convertToDtoList(notebookList);
    }

    public NotebookDto buildChildTree(NotebookDto parent) {

        List<NotebookDto> childList = getNotebookDtoList(parent).stream().map((notebookDto -> {
            return buildChildTree(notebookDto);
        })).toList();

        parent.setChildren(childList);
        return parent;
    }

    @Transactional
    public List<NotebookDto> buildTree() {
        List<NotebookDto> topNotebookList = convertToDtoList(getTopNotebookList());
        return topNotebookList.stream().map((notebookDto) -> {
            return buildChildTree(notebookDto);
        }).toList();
    }

}
