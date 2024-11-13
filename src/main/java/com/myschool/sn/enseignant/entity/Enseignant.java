package com.myschool.sn.enseignant.entity;

import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.person.Identification;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "myschool_enseignant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Enseignant extends Identification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, name = "matricule_enseignant")
    private String matricule;

    @Column(name = "cni_enseignant")
    private String cni;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "utilisateur_uid", referencedColumnName = "id", nullable = false)
    private Utilisateur utilisateur;

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
