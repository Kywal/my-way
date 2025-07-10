package br.ufrn.myway.Model.Mapper;

import org.mapstruct.Mapper;

import br.ufrn.myway.Model.DTO.ConcursoDTO;
import br.ufrn.myway.Model.Entities.Concurso.AbstractConcurso;
import br.ufrn.myway.Model.Entities.Concurso.ConcursoBase;
import br.ufrn.myway.Model.Entities.Concurso.ConcursoPoliciaCivil;
import br.ufrn.myway.Model.Entities.Concurso.ConcursoProfessor;
import br.ufrn.myway.Model.Entities.Concurso.GeneralConcurso;
import br.ufrn.myway.Model.Enums.Nivel;

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
                concurso = new GeneralConcurso();
        }

        concurso.setOrgao(dto.orgao());
        concurso.setCargo(dto.cargo());
        concurso.setNivel(Nivel.valueOf(dto.nivel()));
        return concurso;
    }
}
