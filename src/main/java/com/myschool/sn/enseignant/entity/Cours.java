package com.myschool.sn.enseignant.entity;

import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.referentiel.entity.Classe;
import com.myschool.sn.referentiel.entity.Matiere;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "myschool_cours")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Cours implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String libelle;

    @ManyToOne
    @JoinColumn(name = "enseignant_uid", referencedColumnName = "id")
    private Utilisateur enseignant;

    @ManyToOne
    @JoinColumn(name = "classe_uid", referencedColumnName = "id", nullable = false)
    private Classe classe;

    @ManyToOne
    @JoinColumn(name = "matiere_uid", referencedColumnName = "id", nullable = false)
    private Matiere matiere;

    @Column(name = "date_cours")
    private Date dateDebut;

    private String heureDebut;

    private String heureFin;

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
