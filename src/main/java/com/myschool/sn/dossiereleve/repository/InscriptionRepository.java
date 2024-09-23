package com.myschool.sn.dossiereleve.repository;

import com.myschool.sn.dossiereleve.entity.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {

    @Query("Select DISTINCT el from  Inscription el where el.actif=1 and el.code=:code order by id")
    Inscription findByCode(@Param("code") String code);

    @Query("Select DISTINCT el from  Inscription el where el.id=:id and actif=1")
    Inscription findInscriptionById(@Param("id") Long id);

    @Query("Select DISTINCT el from  Inscription el where el.actif=1 and lower(el.eleve.nom)=lower(:nom) and lower(el.eleve.prenom)=lower(:prenom)")
    Inscription findInscriptionByEleve(@Param("nom") String nom, @Param("prenom") String prenom);

    @Query("Select DISTINCT el from  Inscription el where el.actif=1 order by id")
    List<Inscription> findAllInscriptions();

    @Query("Select DISTINCT el from  Inscription el where el.actif=1 and el.anneeScolaire_debut.libelle=:anneeScolaire")
    List<Inscription> findInscriptionByAnneeScolaire(@Param("anneeScolaire") String anneeScolaire);
}
