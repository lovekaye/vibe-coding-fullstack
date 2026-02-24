package com.example.vibeapp.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostUpdateDto(
        @NotBlank(message = "제목은 필수입니다.") @Size(max = 100, message = "제목은 최대 100자까지 입력 가능합니다.") String title,

        @NotBlank(message = "내용은 필수입니다.") String content) {
}
