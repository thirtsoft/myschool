package com.myschool.sn.enseignant.mapping;

import com.myschool.sn.admin.mapping.ModelFactory;
import com.myschool.sn.enseignant.entity.Conges;
import com.myschool.sn.enseignant.entity.Enseignant;
import com.myschool.sn.utils.dtos.enseignant.CongesDTO;
import com.myschool.sn.utils.dtos.enseignant.EnseignantDTO;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

@Named("modelFactoryEns")
@RequiredArgsConstructor
public class ModelFactoryEns {

    private final ModelFactory modelFactory;

    public Enseignant createEnseignant(EnseignantDTO dto) {
        if (dto == null)
            return null;
        Enseignant model = new Enseignant();
        model.setId(dto.getId());
        model.setActif(true);
        model.setMatricule(dto.getMatricule());
        model.setPrenom(dto.getPrenom());
        model.setNom(dto.getNom());
        model.setCivilite(dto.getCivilite());
        model.setCni(dto.getCni());
        model.setAddress(dto.getAdresse());
        model.setTelephone(dto.getTelephone());
        model.setEmail(dto.getEmail());
        return model;
    }

    public Conges createConges(CongesDTO dto) {
        if (dto == null) return null;
        return Conges.builder()
                .id(dto.getId())
                .motif(dto.getMotif())
                .etat(dto.getEtat())
                .enseignant(modelFactory.createUtilisateur(dto.getEnseignantDTO()))
                .dateDebut(dto.getDateDebut())
                .dateFin(dto.getDateFin())
                .actif(dto.getActif())
                .build();


    }
}
