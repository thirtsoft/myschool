package com.myschool.sn.referentiel.repository;

import com.myschool.sn.referentiel.entity.Batiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BatimentRepository extends JpaRepository<Batiment, Long> {

    @Query("SELECT DISTINCT bat from Batiment bat where bat.actif=1")
    List<Batiment> findAllActivesBatiment();

    @Query("SELECT DISTINCT bat from Batiment bat where bat.id=:id and bat.actif=1 ")
    Batiment findBatimentById(@Param("id") Long id);

    @Query("SELECT DISTINCT bat from  Batiment bat where bat.actif=1 and bat.libelle=:libelle")
    Batiment findByCode(@Param("libelle") String libelle);
}
