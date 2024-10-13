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
public class ExerciceDTO {

    private Long id;

    private String libelle;

    private String description;

    private String url;

    private String piece_jointe;

    private Long enseignantId;

    private Long classeId;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private boolean actif;
}
