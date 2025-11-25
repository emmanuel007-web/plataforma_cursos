package com.plataforma.cursos_online.controller;

import com.plataforma.cursos_online.dto.dtoestudiantes.EstudiantesRequestDTO;
import com.plataforma.cursos_online.dto.dtoestudiantes.EstudiantesResponseDTO;
import com.plataforma.cursos_online.service.EstudiantesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estudiante")
public class EstudiantesController {

    private final EstudiantesService estudiantesService;


    @PostMapping
    public ResponseEntity<EstudiantesResponseDTO> crearUsuario(@RequestBody EstudiantesRequestDTO estudiantesRequestDTO) {
        EstudiantesResponseDTO response = estudiantesService.crearEstudiante(estudiantesRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EstudiantesResponseDTO>> listarUsuarios() {
        List<EstudiantesResponseDTO> response = estudiantesService.listarEstudiantes();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudiantesResponseDTO> obtenerIntercambio(@PathVariable Long id) {
        EstudiantesResponseDTO estudiantesResponseDTO = estudiantesService.obtenerPorId(id).orElse(null);
        if (estudiantesResponseDTO != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(estudiantesResponseDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (estudiantesService.eliminarEstudiante(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudiantesResponseDTO> actualizarEstudiante(@PathVariable Long id, @RequestBody EstudiantesRequestDTO estudiantesRequestDTO) {
        EstudiantesResponseDTO estudianteActualizado = estudiantesService.actualizarEstudiante(id, estudiantesRequestDTO).orElse(null);
        if ( estudianteActualizado != null) {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteActualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
