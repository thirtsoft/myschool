package com.myschool.sn.referentiel.repository;

import com.myschool.sn.referentiel.entity.NiveauEducation;
import com.myschool.sn.referentiel.entity.NiveauEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NiveauEducationRepository extends JpaRepository<NiveauEducation, Long> {

    @Query("SELECT DISTINCT mat from NiveauEducation mat where mat.id=:id and mat.actif=1")
    NiveauEducation findNiveauEducationById(@Param("id") Long id);

    @Query("SELECT DISTINCT mat from NiveauEducation mat where mat.code=:code and mat.actif=1")
    NiveauEducation findNiveauEducationByCode(@Param("code") String code);

    @Query("SELECT DISTINCT mat from NiveauEducation mat where mat.libelle=:libelle and mat.actif=1")
    NiveauEducation findNiveauEducationByLibelle(@Param("libelle") String libelle);

    @Query("SELECT DISTINCT mat from NiveauEducation mat where mat.actif=1")
    List<NiveauEducation> findAllActiveNiveauEducations();
}
