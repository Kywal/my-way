package br.ufrn.myway.Model.DTO;

import br.ufrn.myway.Model.Enums.RoadMapStatus;

public record RoadMapSimpleDTO(Long id, String name, RoadMapStatus status)  {
}
