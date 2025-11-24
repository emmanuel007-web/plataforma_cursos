package com.plataforma.cursos_online.service;

import com.plataforma.cursos_online.dto.dtocursos.CursosRequestDTO;
import com.plataforma.cursos_online.dto.dtocursos.CursosResponseDTO;
import com.plataforma.cursos_online.entity.Categorias;
import com.plataforma.cursos_online.entity.Cursos;
import com.plataforma.cursos_online.repository.CategoriasRepository;
import com.plataforma.cursos_online.repository.CursosRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CursosService {

    private final CursosRepository cursosRepository;

    private final CategoriasRepository categoriasRepository;

    @Transactional
    public CursosResponseDTO crearCurso(CursosRequestDTO cursosRequestDTO) {

        CursosResponseDTO response = new CursosResponseDTO();
        Cursos cursos = new Cursos();

        Optional<Categorias> categoriaOptional = categoriasRepository.findById(cursosRequestDTO.getCategoria());

        if(categoriaOptional.isPresent()){

            Categorias categoria = categoriaOptional.get();

            cursos.setCategoria(categoria);
        }


        cursos.setTitulo(cursosRequestDTO.getTitulo());
        cursos.setDescripcion(cursosRequestDTO.getDescripcion());

        Cursos save = cursosRepository.save(cursos);

        response.setId(save.getId());
        response.setTitulo(cursos.getTitulo());
        response.setDescripcion(cursos.getDescripcion());
        response.setCategoria(cursos.getCategoria());

        return response;
    }

    @Transactional
    public List<CursosResponseDTO> listarCursos() {

        List<Cursos> cursos = cursosRepository.findAll();
        List<CursosResponseDTO> response = new ArrayList<>();

        for (Cursos cursos1 : cursos) {

            CursosResponseDTO responseDTO = new CursosResponseDTO();

            responseDTO.setId(cursos1.getId());
            responseDTO.setTitulo(cursos1.getTitulo());
            responseDTO.setDescripcion(cursos1.getDescripcion());
            responseDTO.setCategoria(cursos1.getCategoria());

            response.add(responseDTO);
        }

        return response;
    }

    @Transactional
    public Optional<CursosResponseDTO> obtenerPorId(Long id) {

        Optional<Cursos> cursosEncontrado = cursosRepository.findById(id);

        if (cursosEncontrado.isPresent()) {

            Cursos cursos = cursosEncontrado.get();
            CursosResponseDTO response = new CursosResponseDTO();

            response.setId(cursos.getId());
            response.setTitulo(cursos.getTitulo());
            response.setDescripcion(cursos.getDescripcion());
            response.setCategoria(cursos.getCategoria());

            return Optional.of(response);
        } else {

            return Optional.empty();
        }
    }

    @Transactional
    public Boolean eliminarCurso(Long id) {

        if (cursosRepository.existsById(id)) {
            cursosRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Transactional
    public Optional<CursosResponseDTO> actualizarCurso(Long id, CursosRequestDTO cursosRequestDTO) {

        Optional<Cursos> CursosEncontrados = cursosRepository.findById(id);

        Optional<Cursos>  CursoActualizar = cursosRepository.findById(id);



        if (CursosEncontrados.isPresent()) {

            Cursos cursos = CursosEncontrados.get();

            cursos.setTitulo(cursosRequestDTO.getTitulo());
            cursos.setDescripcion(cursosRequestDTO.getDescripcion());
            if (CursoActualizar.isPresent()){
                Cursos cursos1 = CursoActualizar.get();

                cursos.setCategoria(cursos1.getCategoria());
            }


            Cursos cursosActualizados = cursosRepository.save(cursos);

            CursosResponseDTO response = new CursosResponseDTO();
            response.setId(cursosActualizados.getId());
            response.setTitulo(cursosActualizados.getTitulo());
            response.setDescripcion(cursosActualizados.getDescripcion());
            response.setCategoria(cursosActualizados.getCategoria());

            return Optional.of(response);

        } else {

            return Optional.empty();
        }
    }
}