package com.plataforma.cursos_online.dto.dtoestudiantes;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EstudiantesRequestDTO {
    @Size(min = 30, max = 70)
    private String nombre;
    @Email
    private String email;
}
