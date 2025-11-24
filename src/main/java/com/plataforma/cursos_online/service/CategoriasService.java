package com.plataforma.cursos_online.service;


import com.plataforma.cursos_online.dto.dtocategorias.CategoriaResponseDTO;
import com.plataforma.cursos_online.dto.dtocategorias.CategoriasRequestDTO;
import com.plataforma.cursos_online.entity.Categorias;
import com.plataforma.cursos_online.repository.CategoriasRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriasService {
    private final CategoriasRepository categoriasRepository;

    @Transactional
    public CategoriaResponseDTO crearCategoria(CategoriasRequestDTO categoriasRequestDTO) {
        CategoriaResponseDTO response = new CategoriaResponseDTO();
        Categorias categorias = new Categorias();
        categorias.setNombre(categoriasRequestDTO.getNombre());

        categoriasRepository.save(categorias);

        response.setId(categorias.getId());
        response.setNombre(categorias.getNombre());

        return response;
    }


    @Transactional
    public List<CategoriaResponseDTO> listarCategorias() {
        List<Categorias> categorias = categoriasRepository.findAll();
        List<CategoriaResponseDTO> response = new ArrayList<>();
        for (Categorias categoria : categorias) {
            CategoriaResponseDTO responseDTO = new CategoriaResponseDTO();
            responseDTO.setId(categoria.getId());
            responseDTO.setNombre(categoria.getNombre());

            response.add(responseDTO);
        }
        return response;
    }


    @Transactional
    public Optional<CategoriaResponseDTO> obtenerPorId(Long id) {
        Optional<Categorias> categoriaEncontrado = categoriasRepository.findById(id);

        if (categoriaEncontrado.isPresent()) {
            Categorias categoria = categoriaEncontrado.get();
            CategoriaResponseDTO response = new CategoriaResponseDTO();
            response.setId(categoria.getId());
            response.setNombre(categoria.getNombre());

            return Optional.of(response);
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public Boolean eliminarUsuario(Long id) {
        if (categoriasRepository.existsById(id)) {
            categoriasRepository.deleteById(id);
            return true;
        }
        return false;
    }


    @Transactional
    public Optional<CategoriaResponseDTO> actualizarCategoria(Long id, CategoriasRequestDTO categoriasRequestDTO) {
        Optional<Categorias> CategoriaEncontrado = categoriasRepository.findById(id);
        if (CategoriaEncontrado.isPresent()) {
            Categorias categorias = CategoriaEncontrado.get(); // Sobreescrbiendo los campos
            categorias.setNombre(categoriasRequestDTO.getNombre());
            Categorias categoriasActualizado = categoriasRepository.save(categorias);

            CategoriaResponseDTO response = new CategoriaResponseDTO();
            response.setId(categoriasActualizado.getId());
            response.setNombre(categoriasActualizado.getNombre());

            return Optional.of(response);
        } else {
            return Optional.empty();
        }
    }



}


