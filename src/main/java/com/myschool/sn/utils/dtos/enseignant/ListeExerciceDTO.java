package com.myschool.sn.utils.dtos.enseignant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListeExerciceDTO {

    private Long id;

    private String libelle;

    private String enseignant;

    private String classe;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private boolean actif;
}
