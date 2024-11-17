package com.myschool.sn.parent.entity;

import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.dossiereleve.entity.Eleve;
import com.myschool.sn.person.Identification;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "myschool_parent")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Parent extends Identification {

    private String profession;

    @ManyToOne
    @JoinColumn(name = "utilisateur_uid", referencedColumnName = "id")
    private Utilisateur utilisateur;

    private String typeParent;

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
