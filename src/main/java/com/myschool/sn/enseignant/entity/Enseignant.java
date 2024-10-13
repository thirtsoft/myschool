package com.myschool.sn.enseignant.entity;

import com.myschool.sn.admin.entity.Utilisateur;
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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "myschool_enseignant")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, name = "matricule_enseignant")
    private String matricule;

    @Column(name = "nom_enseignant")
    private String nom;

    @Column(name = "prenom_enseignant")
    private String prenom;

    @Column(name = "cni_enseignant")
    private String cni;

    @Column(name = "civilite_enseignant")
    private String civilite;

    @Column(name = "adresse_enseignant")
    private String adresse;

    @Column(name = "email_enseignant")
    private String email;

    @Column(name = "telephone_enseignant")
    private String telephone;

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
