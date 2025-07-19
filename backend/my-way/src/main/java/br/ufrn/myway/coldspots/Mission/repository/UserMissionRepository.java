package br.ufrn.myway.coldspots.Mission.repository;

import java.time.LocalDateTime;
import java.util.List;

import br.ufrn.myway.coldspots.Common.repository.SoftDeletableRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufrn.myway.coldspots.model.entities.User;
import br.ufrn.myway.coldspots.Mission.model.UserMission;

@Repository
public interface UserMissionRepository extends SoftDeletableRepository<UserMission> {

    @Query("""
    SELECT COUNT(um) > 0
    FROM UserMission um
    WHERE um.user = :user
      AND um.startDate >= :startOfDay
      AND um.startDate <= :endOfDay
    """)
    boolean existsByUserAndDateRange(
            @Param("user") User user,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay);

    List<UserMission> findByStartDate(LocalDateTime startDate);

    @Query("""
    SELECT um
    FROM UserMission um
    WHERE um.user = :user
      AND um.startDate >= :startOfDay
      AND um.startDate <= :endOfDay
    """)
    UserMission findByUserAndDateRange(@Param("user") User user,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay);
}
