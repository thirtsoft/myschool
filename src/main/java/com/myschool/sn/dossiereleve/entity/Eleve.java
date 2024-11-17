package com.myschool.sn.dossiereleve.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.parent.entity.Parent;
import com.myschool.sn.person.Personne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "myschool_eleve")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Eleve extends Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, name = "matricule_eleve")
    private String matricule;

    @Column(name = "sexe_eleve")
    private String sexe;

    @Column(name = "lieu_naissance")
    private String lieuNaissance;

    @Column(name = "Date_naissance")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date DateNaissance;

    private String nationalite;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "alergies_par_eleve", joinColumns = @JoinColumn(name = "eleve_uid"))
    @Column(name = "allergie_uid")
    private Set<String> allergies;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private MedecinTraitant medecinTraitant;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "myschool_eleves_parents",
            joinColumns = {
                    @JoinColumn(name = "eleves_uid")},
            inverseJoinColumns = {
                    @JoinColumn(name = "parents_uid")})
    private Set<Utilisateur> utilisateurs = new HashSet<>();

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
