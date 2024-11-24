package com.myschool.sn.enseignant.entity;

import com.myschool.sn.admin.entity.Utilisateur;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

import java.util.Date;

@Entity
@Table(name = "myschool_conges")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conges {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "objet_conges")
    private String objet;

    @Column(name = "motif_conges")
    private String motif;

    @ManyToOne
    @JoinColumn(name = "enseignant_uid", referencedColumnName = "id", nullable = false)
    private Utilisateur enseignant;

    private int etat;

    @Column(name = "date_debut_conges")
    private Date dateDebut;

    @Column(name = "date_fin_conges")
    private Date dateFin;

    @Enumerated(EnumType.STRING)
    private EtatConges etatConges;

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
