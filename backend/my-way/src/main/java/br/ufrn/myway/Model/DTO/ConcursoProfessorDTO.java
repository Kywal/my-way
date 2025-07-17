package br.ufrn.myway.Model.DTO;

public record ConcursoProfessorDTO
        (    String edital,
             String nivelConcurso, // Recebe como String ("SUPERIOR")
             String disciplina)
    {
}
