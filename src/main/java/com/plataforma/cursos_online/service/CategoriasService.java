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
        categorias.setDescripcion(categoriasRequestDTO.getDescripcion());
        categorias.setActivo(categoriasRequestDTO.getActivo());

        categoriasRepository.save(categorias);

        response.setIdcat(categorias.getIdcat());
        response.setNombre(categorias.getNombre());
        response.setDescripcion(categorias.getDescripcion());
        response.setActivo(categorias.getActivo());

        return response;
    }


    @Transactional
    public List<CategoriaResponseDTO> listarCategorias() {
        List<Categorias> categorias = categoriasRepository.findAll();
        List<CategoriaResponseDTO> response = new ArrayList<>();
        for (Categorias categoria : categorias) {
            CategoriaResponseDTO responseDTO = new CategoriaResponseDTO();
            responseDTO.setIdcat(categoria.getIdcat());
            responseDTO.setNombre(categoria.getNombre());
            responseDTO.setDescripcion(categoria.getDescripcion());
            responseDTO.setActivo(categoria.getActivo());

            response.add(responseDTO);
        }
        return response;
    }


    @Transactional
    public Optional<CategoriaResponseDTO> obtenerPorId(Long id_cat) {
        Optional<Categorias> categoriaEncontrado = categoriasRepository.findById(id_cat);

        if (categoriaEncontrado.isPresent()) {
            Categorias categoria = categoriaEncontrado.get();
            CategoriaResponseDTO response = new CategoriaResponseDTO();
            response.setIdcat(categoria.getIdcat());
            response.setNombre(categoria.getNombre());
            response.setDescripcion(categoria.getDescripcion());
            response.setActivo(categoria.getActivo());

            return Optional.of(response);
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public Boolean eliminarUsuario(Long idcat) {
        if (categoriasRepository.existsById(idcat)) {
            categoriasRepository.deleteById(idcat);
            return true;
        }
        return false;
    }


    @Transactional
    public Optional<CategoriaResponseDTO> actualizarCategoria(Long id_cat, CategoriasRequestDTO categoriasRequestDTO) {
        Optional<Categorias> CategoriaEncontrado = categoriasRepository.findById(id_cat);
        if (CategoriaEncontrado.isPresent()) {
            Categorias categorias = CategoriaEncontrado.get(); // Sobreescrbiendo los campos
            categorias.setNombre(categoriasRequestDTO.getNombre());
            categorias.setDescripcion(categoriasRequestDTO.getDescripcion());
            categorias.setActivo(categoriasRequestDTO.getActivo());
            Categorias categoriasActualizado = categoriasRepository.save(categorias);

            CategoriaResponseDTO response = new CategoriaResponseDTO();
            response.setIdcat(categoriasActualizado.getIdcat());
            response.setNombre(categoriasActualizado.getNombre());
            response.setDescripcion(categoriasActualizado.getDescripcion());
            response.setActivo(categoriasActualizado.getActivo());

            return Optional.of(response);
        } else {
            return Optional.empty();
        }
    }



}


