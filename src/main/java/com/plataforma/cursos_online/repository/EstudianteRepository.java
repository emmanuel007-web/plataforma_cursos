package com.plataforma.cursos_online.repository;

import com.plataforma.cursos_online.entity.Estudiantes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiantes, Long> {
}
