package com.myschool.sn.referentiel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "myschool_salle")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Salle extends ReferencetielEntity {

    @ManyToOne
    @JoinColumn(name = "batiment_uid", referencedColumnName = "id", nullable = false)
    private Batiment batiment;

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
