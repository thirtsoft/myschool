package com.myschool.sn.referentiel.repository;

import com.myschool.sn.referentiel.entity.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {

    @Query("SELECT DISTINCT mat from Matiere mat where mat.id=:id and mat.actif=1")
    Matiere findMatiereById(@Param("id") Long id);

    @Query("SELECT DISTINCT mat from Matiere mat where mat.code=:code and mat.actif=1")
    Matiere findMatiereByCode(@Param("code") String code);

    @Query("SELECT DISTINCT mat from Matiere mat where mat.libelle=:libelle and mat.actif=1")
    Matiere findMatiereByLibelle(@Param("libelle") String libelle);

    @Query("SELECT DISTINCT mat from Matiere mat where mat.actif=1")
    List<Matiere> findAllActiveMatieres();
}
