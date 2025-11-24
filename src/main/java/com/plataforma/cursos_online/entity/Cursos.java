package com.plataforma.cursos_online.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="curso")
@NoArgsConstructor
public class Cursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String descripcion;


    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categorias categoria;
}
