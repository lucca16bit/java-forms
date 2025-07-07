package br.com.lucca16bit.forms.dto.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateFormDTO(
        @NotBlank
        @Size(min = 2, max = 100)
        String name,

        @NotBlank
        @Email
        @Size(min = 2, max = 150)
        String email,

        @NotBlank
        @Size(min = 2, max = 2000)
        String description
) {
}
