package com.myschool.sn.enseignant.mapping;

import com.myschool.sn.admin.mapping.DTOFactory;
import com.myschool.sn.admin.mapping.ModelFactory;
import com.myschool.sn.enseignant.entity.Exercice;
import com.myschool.sn.referentiel.mapping.DTOFactoryRef;
import com.myschool.sn.referentiel.mapping.ModelFactoryRef;
import com.myschool.sn.utils.dtos.enseignant.DetailsExerciceDTO;
import com.myschool.sn.utils.dtos.enseignant.ExerciceDTO;
import com.myschool.sn.utils.dtos.enseignant.ListeExerciceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciceMapper {
    private final ModelFactory modelFactoryEns;
    private final DTOFactory dtoFactoryEns;
    private final ModelFactoryRef modelFactoryRef;
    private final DTOFactoryRef dtoFactoryRef;

    public Exercice toExercieDTO(ExerciceDTO exerciceDTO) {
        return Exercice.builder()
                .id(exerciceDTO.getId())
                .libelle(exerciceDTO.getLibelle())
                .url(exerciceDTO.getUrl())
                .description(exerciceDTO.getDescription())
                .piece_jointe(exerciceDTO.getPiece_jointe())
                .actif(exerciceDTO.getActif())
                .enseignant(modelFactoryEns.createUtilisateur(exerciceDTO.getEnseignant()))
                .classe(modelFactoryRef.createClasse(exerciceDTO.getClasse()))
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
                .actif(exercice.getActif())
                .enseignant(dtoFactoryEns.createUtilisateurDTO(exercice.getEnseignant()))
                .classe(dtoFactoryRef.createClasseDTO(exercice.getClasse()))
                .dateDebut(exercice.getDateDebut())
                .dateFin(exercice.getDateFin())
                .build();
    }

    public ListeExerciceDTO toListExercice(Exercice exercice) {
        return ListeExerciceDTO.builder()
                .id(exercice.getId())
                .libelle(exercice.getLibelle())
                .enseignant(exercice.getEnseignant().getPrenom() + ' ' + exercice.getEnseignant().getNom())
                .classe(exercice.getClasse().getLibelle())
                .dateDebut(exercice.getDateDebut())
                .dateFin(exercice.getDateFin())
                .actif(exercice.getActif())
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
                        dtoFactoryEns.createUtilisateurDTO(exercice.getEnseignant()))
                .classeDTO(
                        dtoFactoryRef.createClasseDTO(exercice.getClasse())
                )
                .build();
    }
}
