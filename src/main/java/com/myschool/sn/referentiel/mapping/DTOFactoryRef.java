package com.myschool.sn.referentiel.mapping;

import com.myschool.sn.dossierEleve.entity.Eleve;
import com.myschool.sn.referentiel.entity.AnneeScolaire;
import com.myschool.sn.utils.dtos.dossierEleve.EleveDTO;
import com.myschool.sn.utils.dtos.referentiel.AnneeScolaireDTO;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("dtoFactoryRef")
public class DTOFactoryRef {

    public AnneeScolaireDTO createAnneeScolaireDTO(AnneeScolaire anneeScolaire) {
        if (anneeScolaire == null)
            return null;
        AnneeScolaireDTO dto = new AnneeScolaireDTO();
        dto.setId(anneeScolaire.getId());
        dto.setCode(anneeScolaire.getCode());
        dto.setLibelle(anneeScolaire.getLibelle());
        dto.setActif(anneeScolaire.isActif());
        return dto;
    }

    public List<AnneeScolaireDTO> createListeAnneeScolaireDTO(List<AnneeScolaire> anneeScolaires) {
        if (anneeScolaires == null)
            return new ArrayList<>();
        List<AnneeScolaireDTO> dtos = new ArrayList<>();
        for (AnneeScolaire anneeScolaire : anneeScolaires) {
            dtos.add(createAnneeScolaireDTO(anneeScolaire));
        }
        return dtos;
    }
}
