package br.ufrn.myway.model.DTO;

public record ConcursoDTO(
        Long id,
        String type, // "POLICIA_CIVIL", "PROFESSOR", "OUTRO"
        String orgao,
        String cargo,
        String nivel // MEDIO, TECNICO, SUPERIOR
        ) {
}
