package com.plataforma.cursos_online.dto.dtocategorias;

import lombok.Data;

@Data
public class CategoriaResponseDTO {

    private Long idcat;
    private String nombre;
    private String descripcion;
    private Boolean activo;
}
