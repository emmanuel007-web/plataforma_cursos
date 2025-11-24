package com.plataforma.cursos_online.dto.dtocategorias;


import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriasRequestDTO {
    @Size(min = 10, max = 50)
    private String nombre;

    @Size(min=40, max=100)
    private String descripcion;


    private Boolean activo;
}
