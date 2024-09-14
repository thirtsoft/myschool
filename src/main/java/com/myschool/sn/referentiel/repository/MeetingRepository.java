package com.myschool.sn.referentiel.repository;

import com.myschool.sn.referentiel.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    @Query("SELECT DISTINCT mat from Meeting mat where mat.id=:id and mat.actif=1")
    Meeting findMeetingById(@Param("id") Long id);

    @Query("SELECT DISTINCT mat from Meeting mat where mat.libelle=:libelle and mat.actif=1")
    Meeting findMeetingByLibelle(@Param("libelle") String libelle);

    @Query("SELECT DISTINCT mat from Meeting mat where mat.actif=1")
    List<Meeting> findAllActiveMeetings();
}
