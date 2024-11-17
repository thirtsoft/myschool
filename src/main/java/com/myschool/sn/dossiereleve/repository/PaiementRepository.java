package com.myschool.sn.dossiereleve.repository;

import com.myschool.sn.dossiereleve.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {

    @Query("Select DISTINCT pay from  Paiement pay where pay.actif=1 order by pay.id desc")
    List<Paiement> findAllPaiements();

    @Query("Select DISTINCT pay from  Paiement pay where pay.actif=1 and pay.code=:code")
    Paiement findByCode(@Param("code") String code);

    @Query("Select DISTINCT pay from  Paiement pay where pay.id=:id and pay.actif=1")
    Paiement findPaiementById(@Param("id") Long id);

    @Query("Select DISTINCT pay from  Paiement pay where pay.actif=1 and pay.mois=:mois order by pay.id asc")
    List<Paiement> findPaiementsByMois(@Param("mois") String mois);

    @Query("Select DISTINCT pay from Paiement pay where pay.actif=1 and pay.eleve.id=:eleveId order by pay.id desc")
    List<Paiement> findPaiementsByEleve(@Param("eleveId") Long eleveId);

}
