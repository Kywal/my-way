package br.ufrn.myway.model.mapper;

import org.mapstruct.Mapper;

import br.ufrn.myway.model.DTO.ConcursoDTO;
import br.ufrn.myway.model.entities.Concurso.AbstractConcurso;
import br.ufrn.myway.model.entities.Concurso.ConcursoBase;
import br.ufrn.myway.model.entities.Concurso.ConcursoPoliciaCivil;
import br.ufrn.myway.model.entities.Concurso.ConcursoProfessor;
import br.ufrn.myway.model.entities.Concurso.ConcursoGeneralista;
import br.ufrn.myway.model.enums.Nivel;

@Mapper(componentModel = "spring")
public abstract class ConcursoMapper {

    public ConcursoDTO toDTO(AbstractConcurso concurso) {
        String type = switch (concurso) {
            case ConcursoPoliciaCivil ignored ->
                "POLICIA_CIVIL";
            case ConcursoProfessor ignored ->
                "PROFESSOR";
            default ->
                "OUTRO";
        };

        ConcursoBase base = (ConcursoBase) concurso;

        return new ConcursoDTO(
                base.getId(),
                type,
                base.getOrgao(),
                base.getCargo(),
                base.getNivel().name()
        );
    }

    public ConcursoBase toEntity(ConcursoDTO dto) {
        ConcursoBase concurso;
        switch (dto.type().toUpperCase()) {
            case "POLICIA_CIVIL" ->
                concurso = new ConcursoPoliciaCivil();
            case "PROFESSOR" ->
                concurso = new ConcursoProfessor();
            default ->
                concurso = new ConcursoGeneralista();
        }

        concurso.setOrgao(dto.orgao());
        concurso.setCargo(dto.cargo());
        concurso.setNivel(Nivel.valueOf(dto.nivel()));
        return concurso;
    }
}
