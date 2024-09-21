package com.myschool.sn.referentiel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "myschool_batiment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Batiment extends ReferencetielEntity {

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
