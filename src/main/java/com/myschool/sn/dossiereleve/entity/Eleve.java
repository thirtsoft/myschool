package com.myschool.sn.dossiereleve.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "myschool_eleve")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Eleve {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, name = "matricule_eleve")
    private String matricule;

    @Column(name = "nom_eleve")
    private String nom;

    @Column(name = "prenom_eleve")
    private String prenom;

    @Column(name = "sexe_eleve")
    private String sexe;

    @Column(name = "lieu_naissance")
    private String lieuNaissance;

    @Column(name = "Date_naissance")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date DateNaissance;

    @Column(name = "adresse_eleve")
    private String adresse;

    private String nationalite;

    private int actif;

    public void setActif(boolean actif) {
        if (actif)
            this.actif = 1;
        else
            this.actif = 0;
    }

    public boolean isActif() {
        return actif == 1;
    }


}
