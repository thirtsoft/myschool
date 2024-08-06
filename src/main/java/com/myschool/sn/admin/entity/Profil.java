package com.myschool.sn.admin.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "profil")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String libelle;

    private int actif;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "actions_par_profil", joinColumns = @JoinColumn(name = "profil_uid"),
            inverseJoinColumns = @JoinColumn(name = "action_uid"))
    private Set<Action> action;

    private String typeCompte;

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
