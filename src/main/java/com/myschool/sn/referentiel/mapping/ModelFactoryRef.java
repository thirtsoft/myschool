package com.myschool.sn.referentiel.mapping;

import com.myschool.sn.referentiel.entity.AnneeScolaire;
import com.myschool.sn.utils.dtos.referentiel.AnneeScolaireDTO;

import javax.inject.Named;

@Named("modelFactoryRef")
public class ModelFactoryRef {

    public AnneeScolaire createAnneeScolaire(AnneeScolaireDTO dto) {
        if (dto == null)
            return null;
        AnneeScolaire model = new AnneeScolaire();
        model.setId(dto.getId());
        model.setActif(true);
        model.setCode(dto.getCode());
        model.setLibelle(dto.getLibelle());
        return model;
    }
}
