package com.myschool.sn.referentiel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "myschool_classe")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classe extends ReferencetielEntity {

    @JoinColumn(name = "batiment_uid", referencedColumnName = "id", nullable = false)
    private Long batimentId;

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
