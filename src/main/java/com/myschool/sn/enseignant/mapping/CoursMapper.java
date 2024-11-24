package com.myschool.sn.enseignant.mapping;

import com.myschool.sn.admin.mapping.DTOFactory;
import com.myschool.sn.admin.mapping.ModelFactory;
import com.myschool.sn.enseignant.entity.Cours;
import com.myschool.sn.referentiel.mapping.DTOFactoryRef;
import com.myschool.sn.referentiel.mapping.ModelFactoryRef;
import com.myschool.sn.utils.dtos.enseignant.CoursDTO;
import com.myschool.sn.utils.dtos.enseignant.ListeCoursDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoursMapper {

    private final ModelFactory modelFactoryEns;
    private final DTOFactory dtoFactoryEns;
    private final ModelFactoryRef modelFactoryRef;
    private final DTOFactoryRef dtoFactoryRef;

    public Cours fromCoursDTO(CoursDTO coursDTO) {
        return Cours.builder().id(coursDTO.getId()).actif(1).libelle(coursDTO.getLibelle())
                .dateDebut(coursDTO.getDateDebut()).heureDebut(coursDTO.getHeureDebut())
                .heureFin(coursDTO.getHeureFin())
                .classe(modelFactoryRef.createClasse(coursDTO.getClasse()))
                .matiere(modelFactoryRef.createMatiere(coursDTO.getMatiere()))
                .enseignant(modelFactoryEns.createUtilisateur(coursDTO.getEnseignant())).build();
    }

    public CoursDTO fromCoursDTO(Cours coursDTO) {
        return CoursDTO.builder().id(coursDTO.getId()).actif(1).libelle(coursDTO.getLibelle())
                .dateDebut(coursDTO.getDateDebut()).heureDebut(coursDTO.getHeureDebut())
                .heureFin(coursDTO.getHeureFin())
                .classe(dtoFactoryRef.createClasseDTO(coursDTO.getClasse()))
                .matiere(dtoFactoryRef.createMatiereDTO(coursDTO.getMatiere()))
                .enseignant(dtoFactoryEns.createUtilisateurDTO(coursDTO.getEnseignant())).build();
    }

    public ListeCoursDTO fromListCoursDTO(Cours coursDTO) {
        return ListeCoursDTO.builder().id(coursDTO.getId()).actif(1).libelle(coursDTO.getLibelle())
                .dateDebut(coursDTO.getDateDebut()).heureDebut(coursDTO.getHeureDebut())
                .heureFin(coursDTO.getHeureFin())
                .classe(coursDTO.getClasse().getLibelle())
                .matiere(coursDTO.getMatiere().getLibelle())
                .enseignant(coursDTO.getEnseignant().getPrenom() + ' ' + coursDTO.getEnseignant().getNom()).build();
    }


}
