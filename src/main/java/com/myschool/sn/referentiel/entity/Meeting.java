package com.myschool.sn.referentiel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Table(name = "myschool_meeting")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Meeting extends ReferencetielEntity {

    @Column(name = "date_meeting")
    private Date dateMeeting;

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
