package com.example.ms1.api.note.notebook;

import com.example.ms1.note.note.Note;
import com.example.ms1.note.notebook.Notebook;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Schema(description = "Notebook entity")
public class NotebookDto {

    @Schema(description = "Notebook ID", example = "1")
    private Long id;

    @Schema(description = "Notebook name", example = "backend notebook")
    private String title;

//    @Schema(description = "sub notebooks", example = "child notebooks")
    @ArraySchema(arraySchema = @Schema(description = "List of sub notebooks"), schema = @Schema(implementation = NotebookDto.class))
    private List<NotebookDto> children = new ArrayList<>();
}
