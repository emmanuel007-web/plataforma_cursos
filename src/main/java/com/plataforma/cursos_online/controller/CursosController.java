package com.plataforma.cursos_online.controller;

import com.plataforma.cursos_online.dto.dtocursos.CursosRequestDTO;
import com.plataforma.cursos_online.dto.dtocursos.CursosResponseDTO;
import com.plataforma.cursos_online.service.CursosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cursos")
public class CursosController{
    private final CursosService cursosService;


    @PostMapping
    public ResponseEntity<CursosResponseDTO> crearCurso(@RequestBody CursosRequestDTO cursosRequestDTO) {
        CursosResponseDTO response = cursosService.crearCurso(cursosRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CursosResponseDTO>> listarCursos() {
        List<CursosResponseDTO> response = cursosService.listarCursos();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursosResponseDTO> obtenerIntercambio(@PathVariable Long id) {
        CursosResponseDTO cursosResponseDTO = cursosService.obtenerPorId(id).orElse(null);
        if (cursosResponseDTO != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(cursosResponseDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        if (cursosService.eliminarCurso(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursosResponseDTO> actualizarCurso(@PathVariable Long id, @RequestBody CursosRequestDTO cursosRequestDTO) {
        CursosResponseDTO cursosActualizados = cursosService.actualizarCurso(id, cursosRequestDTO).orElse(null);
        if ( cursosActualizados != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cursosActualizados);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }



}
