package com.plataforma.cursos_online.controller;

import com.plataforma.cursos_online.dto.dtocategorias.CategoriaResponseDTO;
import com.plataforma.cursos_online.dto.dtocategorias.CategoriasRequestDTO;
import com.plataforma.cursos_online.service.CategoriasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoria")
public class CategoriasController {
    private final CategoriasService categoriasService;


    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> crearUsuario(@RequestBody CategoriasRequestDTO categoriasRequestDTO) {
        CategoriaResponseDTO response = categoriasService.crearCategoria(categoriasRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarUsuarios() {
        List<CategoriaResponseDTO> response = categoriasService.listarCategorias();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> obtenerIntercambio(@PathVariable Long idcat) {
        CategoriaResponseDTO categoriaResponseDTO = categoriasService.obtenerPorId(idcat).orElse(null);
        if (categoriaResponseDTO != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(categoriaResponseDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long idcat) {
        if (categoriasService.eliminarUsuario(idcat)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> actualizarCategoria(@PathVariable Long id_cat, @RequestBody CategoriasRequestDTO usuarioRequestDTO) {
        CategoriaResponseDTO categoriaActualizada = categoriasService.actualizarCategoria(id_cat, usuarioRequestDTO).orElse(null);
        if ( categoriaActualizada != null) {
            return ResponseEntity.status(HttpStatus.OK).body(categoriaActualizada);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}




