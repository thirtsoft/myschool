package com.myschool.sn.enseignant.entity;

import com.myschool.sn.referentiel.entity.Classe;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import org.joda.time.LocalDate;

@Entity
@Table(name = "myschool_exercice")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exercice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String libelle;

    private String description;

    private String url;

    private String piece_jointe;

    @ManyToOne
    @JoinColumn(name = "enseignant_uid", referencedColumnName = "id", nullable = false)
    private Enseignant enseignant;

    @ManyToOne
    @JoinColumn(name = "classe_uid", referencedColumnName = "id", nullable = false)
    private Classe classe;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    private boolean actif;

}
