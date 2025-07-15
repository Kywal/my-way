package br.ufrn.myway.Model.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record ProvaDTO(String nome,
                       LocalDateTime firstDay,
                       LocalDateTime lastDay,
                       List<String> etapa)
{
}
