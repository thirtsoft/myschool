package com.myschool.sn.dossierEleve.entity;

import com.myschool.sn.admin.entity.Profil;
import com.myschool.sn.referentiel.entity.AnneeScolaire;
import com.myschool.sn.referentiel.entity.Classe;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "myschool_inscription")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, name = "code_inscription")
    private String code;

    @ManyToOne
    @JoinColumn(name = "eleve_uid", referencedColumnName = "id", nullable = false)
    private Eleve eleve;

    @ManyToOne
    @JoinColumn(name = "annee_scolaire_debut_uid", referencedColumnName = "id", nullable = false)
    private AnneeScolaire anneeScolaire_debut;

    @ManyToOne
    @JoinColumn(name = "annee_scolaire_fin_uid", referencedColumnName = "id", nullable = false)
    private AnneeScolaire anneeScolaire_fin;

    @ManyToOne
    @JoinColumn(name = "classe_uid", referencedColumnName = "id")
    private Classe classe;

    @Column(name = "montant_inscription")
    private Double montantInscription;

    @Column(name = "date_inscription")
    private Date dateInscription;

    private Long createdBy;

    private int actif;

    public void setActif(boolean actif) {
        if (actif)
            this.actif = 1;
        else
            this.actif = 0;
    }
    public boolean isActif() {
        return actif==1;
    }

}
