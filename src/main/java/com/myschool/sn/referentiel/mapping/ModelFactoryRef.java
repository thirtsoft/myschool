package com.myschool.sn.referentiel.mapping;

import com.myschool.sn.referentiel.entity.AnneeScolaire;
import com.myschool.sn.referentiel.entity.Batiment;
import com.myschool.sn.referentiel.entity.Classe;
import com.myschool.sn.referentiel.entity.Matiere;
import com.myschool.sn.referentiel.entity.Salle;
import com.myschool.sn.utils.dtos.referentiel.AnneeScolaireDTO;
import com.myschool.sn.utils.dtos.referentiel.BatimentDTO;
import com.myschool.sn.utils.dtos.referentiel.ClasseDTO;
import com.myschool.sn.utils.dtos.referentiel.MatiereDTO;
import com.myschool.sn.utils.dtos.referentiel.SalleDTO;

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

    public Batiment createBatiment(BatimentDTO batimentDTO) {
        if (batimentDTO == null)
            return null;
        Batiment model = new Batiment();
        model.setId(batimentDTO.getId());
        model.setActif(batimentDTO.isActif());
        model.setLibelle(batimentDTO.getLibelle());
        return model;
    }

    public Classe createClasse(ClasseDTO dto) {
        if (dto == null)
            return null;
        Classe model = new Classe();
        model.setId(dto.getId());
        model.setActif(dto.isActif());
        model.setLibelle(dto.getLibelle());
        return model;
    }

    public Matiere createMatiere(MatiereDTO dto) {
        if (dto == null)
            return null;
        Matiere model = new Matiere();
        model.setId(dto.getId());
        model.setCode(dto.getCode());
        model.setLibelle(dto.getLibelle());
        model.setActif(dto.isActif());
        return model;
    }

    public Salle createSalle(SalleDTO dto) {
        if (dto == null)
            return null;
        Salle model = new Salle();
        model.setId(dto.getId());
        model.setLibelle(dto.getLibelle());
        model.setBatiment(createBatiment(dto.getBatimentDTO()));
        model.setActif(dto.isActif());
        return model;
    }
}
