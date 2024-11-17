package com.myschool.sn.enseignant.mapping;

import com.myschool.sn.enseignant.entity.Exercice;
import com.myschool.sn.enseignant.service.EnseignantService;
import com.myschool.sn.referentiel.mapping.DTOFactoryRef;
import com.myschool.sn.referentiel.mapping.ModelFactoryRef;
import com.myschool.sn.referentiel.service.ReferentielService;
import com.myschool.sn.utils.dtos.enseignant.DetailsExerciceDTO;
import com.myschool.sn.utils.dtos.enseignant.ExerciceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciceMapper {

    private final EnseignantService enseignantService;
    private final ModelFactoryEns modelFactoryEns;
    private final DTOFactoryEns dtoFactoryEns;
    private final ReferentielService referentielService;
    private final ModelFactoryRef modelFactoryRef;
    private final DTOFactoryRef dtoFactoryRef;

    public Exercice toExercieDTO(ExerciceDTO exerciceDTO) {
        return Exercice.builder()
                .id(exerciceDTO.getId())
                .libelle(exerciceDTO.getLibelle())
                .url(exerciceDTO.getUrl())
                .description(exerciceDTO.getDescription())
                .piece_jointe(exerciceDTO.getPiece_jointe())
                .actif(exerciceDTO.isActif())
                .enseignant(modelFactoryEns.createEnseignant(
                        enseignantService.findEnseignantDTOById(exerciceDTO.getEnseignantId())))
                .classe(modelFactoryRef.createClasse(
                        referentielService.findClasseById(exerciceDTO.getClasseId())
                ))
                .dateDebut(exerciceDTO.getDateDebut())
                .dateFin(exerciceDTO.getDateFin())
                .build();
    }

    public ExerciceDTO toExercie(Exercice exercice) {
        return ExerciceDTO.builder()
                .id(exercice.getId())
                .libelle(exercice.getLibelle())
                .url(exercice.getUrl())
                .description(exercice.getDescription())
                .piece_jointe(exercice.getPiece_jointe())
                .actif(exercice.isActif())
                .enseignantId(exercice.getEnseignant().getId())
                .classeId(exercice.getClasse().getId())
                .dateDebut(exercice.getDateDebut())
                .dateFin(exercice.getDateFin())
                .build();
    }

    public DetailsExerciceDTO fromExercice(Exercice exercice) {
        return DetailsExerciceDTO.builder()
                .id(exercice.getId())
                .libelle(exercice.getLibelle())
                .description(exercice.getDescription())
                .url(exercice.getUrl())
                .piece_jointe(exercice.getPiece_jointe())
                .actif(exercice.isActif())
                .dateDebut(exercice.getDateDebut())
                .dateFin(exercice.getDateFin())
                .enseignantDTO(
                        dtoFactoryEns.createEnseignantDTO(exercice.getEnseignant()))
                .classeDTO(
                        dtoFactoryRef.createClasseDTO(exercice.getClasse())
                )
                .build();
    }
}
