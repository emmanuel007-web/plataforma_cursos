package com.plataforma.cursos_online.repository;

import com.plataforma.cursos_online.entity.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursosRepository extends JpaRepository<Cursos, Long> {
}
