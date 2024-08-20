package com.myschool.sn.referentiel.repository;

import com.myschool.sn.referentiel.entity.Evenement;
import com.myschool.sn.referentiel.entity.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {

    @Query("Select DISTINCT el from  Evenement el where el.actif=1 order by id")
    List<Evenement> findAllEvenements();

    @Query("Select DISTINCT el from  Evenement el where el.actif=1 and el.libelle=:libelle")
    Evenement findByLibelle(@Param("libelle") String libelle);

    @Query("Select DISTINCT el from  Evenement el where el.id=:id and actif=1")
    Evenement findEvenementById(@Param("id") Long id);
}
