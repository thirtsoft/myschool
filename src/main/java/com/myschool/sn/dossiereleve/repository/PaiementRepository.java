package com.myschool.sn.dossiereleve.repository;

import com.myschool.sn.dossiereleve.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {

    @Query("Select DISTINCT el from  Paiement el where el.actif=1 order by id")
    List<Paiement> findAllPaiements();

    @Query("Select DISTINCT el from  Paiement el where el.actif=1 and el.code=:code order by id")
    Paiement findByCode(@Param("code") String code);

    @Query("Select DISTINCT el from  Paiement el where el.id=:id and actif=1")
    Paiement findPaiementById(@Param("id") Long id);

    @Query("Select DISTINCT el from  Paiement el where el.actif=1 and el.mois=:mois order by id")
    List<Paiement> findPaiementsByMois(@Param("mois") String mois);

}
