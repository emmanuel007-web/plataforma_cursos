package com.plataforma.cursos_online.dto.dtoestudiantes;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EstudiantesResponseDTO {
  private Long id;

  private String nombre;

  private String email;
}
