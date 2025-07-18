package br.ufrn.myway.coldspots.Roadmap.service;

public interface AbstractAiRoadmapService<RoadmapType> {

    RoadmapType generateRoadmap(
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
