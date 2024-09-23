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
@Table(name = "myschool_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Menu extends ReferencetielEntity {

    @ManyToOne
    @JoinColumn(name = "category_menu_uid", referencedColumnName = "id", nullable = false)
    private CategoryMenu categoryMenu;

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
