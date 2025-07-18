package br.ufrn.myway.Controller.RoadmapController;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AbstractRoadmapController<
        RequestRoadmapDTO,
        RequestFullRoadmapDTO,
        ResponseRoadmapDTO
> {

    public ResponseEntity<ResponseRoadmapDTO> save(RequestRoadmapDTO roadmapDTO, Long userId);

    public ResponseEntity<ResponseRoadmapDTO> saveDiff(RequestFullRoadmapDTO roadmapDTO, Long userId);

    public List<ResponseRoadmapDTO> findAll();

    public ResponseEntity<ResponseRoadmapDTO> findById(Long id);

    public ResponseEntity<String> delete(Long id);

    public List<ResponseRoadmapDTO> findByUser( Long userId);

    public ResponseEntity<ResponseRoadmapDTO> finish(Long id);

    public ResponseEntity<ResponseRoadmapDTO> cancel(Long id);

    public ResponseEntity<ResponseRoadmapDTO> findActiveRoadmap(Long userId);

}
