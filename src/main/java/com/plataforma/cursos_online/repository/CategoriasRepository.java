package com.plataforma.cursos_online.repository;


import com.plataforma.cursos_online.entity.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
}
