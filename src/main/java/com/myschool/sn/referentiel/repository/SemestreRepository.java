package com.myschool.sn.referentiel.repository;

import com.myschool.sn.referentiel.entity.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SemestreRepository extends JpaRepository<Semestre, Long> {

    @Query("Select DISTINCT el from  Semestre el where el.actif=1 order by id")
    List<Semestre> findAllSemestres();

    @Query("Select DISTINCT el from  Semestre el where el.actif=1 and el.code=:code")
    Semestre findByCode(@Param("code") String code);

    @Query("Select DISTINCT el from  Semestre el where el.actif=1 and el.libelle=:libelle")
    Semestre findByLibelle(@Param("libelle") String libelle);

    @Query("Select DISTINCT el from  Semestre el where el.id=:id and actif=1")
    Semestre findSemestreById(@Param("id") Long id);
}
