package br.ufrn.myway.coldspots.Roadmap.service;

public interface AbstractAiRoadmapService<RoadmapType, RoadmapDTOType> {

    RoadmapDTOType generateRoadmap(
            String mainGoal,
            String description,
            String aditionalInfo,
            String tipoConcurso
    );

    RoadmapType saveGeneratedRoadmap(
            RoadmapType roadmap,
            Long userId
    );

    RoadmapType generateAndSaveRoadmap(
            String mainGoal,
            String description,
            Long userId
    );
}
