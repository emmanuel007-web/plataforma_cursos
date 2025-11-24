package com.plataforma.cursos_online.dto.dtocursos;


import com.plataforma.cursos_online.entity.Categorias;
import lombok.Data;

@Data
public class CursosRequestDTO {
    private String titulo;

    private String descripcion;

    private Long categoria;
}
