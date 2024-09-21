package com.myschool.sn.referentiel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Table(name = "myschool_evenement")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "libelle_evenement")
    private String libelle;

    @Column(name = "date_evenement")
    private Date dateEvenement;

    @Column(name = "heure_debut")
    private String heureDebut;

    @Column(name = "heure_Fin")
    private String heureFin;

    @Column(name = "description")
    private String description;

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
