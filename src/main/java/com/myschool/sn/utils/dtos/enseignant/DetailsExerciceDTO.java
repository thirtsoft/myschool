package com.myschool.sn.utils.dtos.enseignant;

import com.myschool.sn.utils.dtos.referentiel.ClasseDTO;
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
public class DetailsExerciceDTO {

    private Long id;

    private String libelle;

    private String description;

    private String url;

    private String piece_jointe;

    private EnseignantDTO enseignantDTO;

    private ClasseDTO classeDTO;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private boolean actif;
}
