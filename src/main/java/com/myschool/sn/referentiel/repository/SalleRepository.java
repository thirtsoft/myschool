package com.myschool.sn.referentiel.repository;

import com.myschool.sn.referentiel.entity.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalleRepository extends JpaRepository<Salle, Long> {

    @Query("SELECT DISTINCT sal from Salle sal where sal.id=:id and sal.actif=1 ")
    Salle findSalleById(@Param("id") Long id);

    @Query("SELECT DISTINCT sal from  Salle sal where sal.actif=1 and sal.libelle=:libelle")
    Salle findByLibelle(@Param("libelle") String libelle);

    @Query("SELECT DISTINCT sal from Salle sal where sal.actif=1")
    List<Salle> findAllActiveSalles();

    @Query("SELECT DISTINCT sal from  Salle sal where sal.actif=1 and sal.batiment.id=:batimentId")
    List<Salle> findSallesByBatiment(@Param("batimentId") Long batimentId);
}
