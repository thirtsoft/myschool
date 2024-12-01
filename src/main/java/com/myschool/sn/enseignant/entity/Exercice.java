package com.myschool.sn.enseignant.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.referentiel.entity.Classe;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.LocalDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "myschool_exercice")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exercice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String libelle;

    private String description;

    private String url;

    private String piece_jointe;

    @ManyToOne
    @JoinColumn(name = "enseignant_uid", referencedColumnName = "id")
    private Utilisateur enseignant;

    @ManyToOne
    @JoinColumn(name = "classe_uid", referencedColumnName = "id", nullable = false)
    private Classe classe;

    @Column(name = "date_debut")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateDebut;

    @Column(name = "date_fin")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateFin;

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
