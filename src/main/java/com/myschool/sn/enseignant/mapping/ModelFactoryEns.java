package com.myschool.sn.enseignant.mapping;

import com.myschool.sn.dossierEleve.entity.Eleve;
import com.myschool.sn.enseignant.entity.Enseignant;
import com.myschool.sn.utils.dtos.dossierEleve.EleveDTO;
import com.myschool.sn.utils.dtos.enseignant.EnseignantDTO;

import javax.inject.Named;

@Named("modelFactoryEns")
public class ModelFactoryEns {

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
        model.setAdresse(dto.getAdresse());
        model.setTelephone(dto.getTelephone());
        model.setEmail(dto.getEmail());
        return model;
    }
}
