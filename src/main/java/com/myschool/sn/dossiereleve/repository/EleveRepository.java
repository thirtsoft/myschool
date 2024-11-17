package com.myschool.sn.dossiereleve.repository;

import com.myschool.sn.dossiereleve.entity.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EleveRepository extends JpaRepository<Eleve, Long> {

    @Query("Select DISTINCT el from  Eleve el where el.actif=1 order by id desc")
    List<Eleve> findAllEleves();

    @Query("Select DISTINCT el from  Eleve el where el.actif=1 and el.matricule=:matricule order by id")
    Eleve findByMatricule(@Param("matricule") String matricule);

    @Query("Select DISTINCT el from  Eleve el where el.id=:id and el.actif=1")
    Eleve findEleveById(@Param("id") Long id);

    @Query("Select DISTINCT el from  Eleve el where el.actif=1 and lower(el.nom)=lower(:nom) and lower(el.prenom)=lower(:prenom)")
    Eleve findByNomOrPrenom(@Param("nom") String nom, @Param("prenom") String prenom);

    @Query("SELECT count(el) from  Eleve el where el.actif=1")
    long countNombreEleve();

}
