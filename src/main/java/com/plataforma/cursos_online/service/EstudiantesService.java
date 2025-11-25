package com.plataforma.cursos_online.service;


import com.plataforma.cursos_online.dto.dtocategorias.CategoriaResponseDTO;
import com.plataforma.cursos_online.dto.dtocategorias.CategoriasRequestDTO;
import com.plataforma.cursos_online.dto.dtoestudiantes.EstudiantesRequestDTO;
import com.plataforma.cursos_online.dto.dtoestudiantes.EstudiantesResponseDTO;
import com.plataforma.cursos_online.entity.Categorias;
import com.plataforma.cursos_online.entity.Estudiantes;
import com.plataforma.cursos_online.repository.EstudianteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EstudiantesService {
    private final EstudianteRepository estudianteRepository;

    @Transactional
    public EstudiantesResponseDTO crearEstudiante(EstudiantesRequestDTO estudiantesRequestDTO) {
        EstudiantesResponseDTO response = new EstudiantesResponseDTO();
        Estudiantes estudiante = new Estudiantes();
        estudiante.setNombre(estudiantesRequestDTO.getNombre());
        estudiante.setEmail(estudiantesRequestDTO.getEmail());

        estudianteRepository.save(estudiante);

        response.setId(estudiante.getId());
        response.setNombre(estudiante.getNombre());
        response.setEmail(estudiante.getEmail());

        return response;
    }


    @Transactional
    public List<EstudiantesResponseDTO> listarEstudiantes() {
        List<Estudiantes> estudiantes = estudianteRepository.findAll();
        List<EstudiantesResponseDTO> response = new ArrayList<>();
        for (Estudiantes estudiantes1 : estudiantes) {
            EstudiantesResponseDTO responseDTO = new EstudiantesResponseDTO();
            responseDTO.setId(estudiantes1.getId());
            responseDTO.setNombre(estudiantes1.getNombre());
            responseDTO.setEmail(estudiantes1.getEmail());

            response.add(responseDTO);
        }
        return response;
    }


    @Transactional
    public Optional<EstudiantesResponseDTO> obtenerPorId(Long id) {
        Optional<Estudiantes> estudianteEncontrado = estudianteRepository.findById(id);

        if (estudianteEncontrado.isPresent()) {
            Estudiantes estudiante = estudianteEncontrado.get();
            EstudiantesResponseDTO response = new EstudiantesResponseDTO();
            response.setId(estudiante.getId());
            response.setNombre(estudiante.getNombre());
            response.setEmail(estudiante.getEmail());

            return Optional.of(response);
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public Boolean eliminarEstudiante(Long id) {
        if (estudianteRepository.existsById(id)) {
            estudianteRepository.deleteById(id);
            return true;
        }
        return false;
    }


    @Transactional
    public Optional<EstudiantesResponseDTO> actualizarEstudiante(Long id, EstudiantesRequestDTO estudiantesRequestDTO) {
        Optional<Estudiantes> EstudianteEncontrado = estudianteRepository.findById(id);
        if (EstudianteEncontrado.isPresent()) {
            Estudiantes estudiantes = EstudianteEncontrado.get(); // Sobreescrbiendo los campos
            estudiantes.setNombre(estudiantesRequestDTO.getNombre());
            estudiantes.setEmail(estudiantesRequestDTO.getEmail());
            Estudiantes actualizarEstudiante = estudianteRepository.save(estudiantes);

            EstudiantesResponseDTO response = new EstudiantesResponseDTO();
            response.setId(actualizarEstudiante.getId());
            response.setNombre(actualizarEstudiante.getNombre());
            response.setEmail(actualizarEstudiante.getEmail());

            return Optional.of(response);
        } else {
            return Optional.empty();
        }
    }




}
