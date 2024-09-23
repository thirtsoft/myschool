package com.myschool.sn.referentiel.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(name = "myschool_batiment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Batiment extends ReferencetielEntity {

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "classe_par_batiment",
            joinColumns = @JoinColumn(name = "batiment_uid"),
            inverseJoinColumns = @JoinColumn(name = "classe_uid"))
    private Set<Classe> classes;

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
