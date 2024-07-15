package com.example.ms1.api.note.note;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "노트 데이터 전송 객체")
public class NoteDto {

    @Schema(description = "노트 식별 코드", example = "1")
    private Long id;

    @Schema(description = "노트 제목", example = "스프링부트 핵심요약")
    private String title;

    @Schema(description = "노트 내용", example = "열심히 하면 됩니다.")
    @NotNull
    private String content;

    @Schema(description = "노트 작성 날짜", example = "2024년 09월 11일")
    private LocalDateTime createDate;
}
