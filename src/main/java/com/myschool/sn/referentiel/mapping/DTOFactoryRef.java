package com.myschool.sn.referentiel.mapping;

import com.myschool.sn.dossierEleve.entity.Eleve;
import com.myschool.sn.referentiel.entity.AnneeScolaire;
import com.myschool.sn.referentiel.entity.Batiment;
import com.myschool.sn.referentiel.entity.Classe;
import com.myschool.sn.referentiel.entity.Matiere;
import com.myschool.sn.referentiel.entity.Salle;
import com.myschool.sn.utils.dtos.dossierEleve.EleveDTO;
import com.myschool.sn.utils.dtos.referentiel.AnneeScolaireDTO;
import com.myschool.sn.utils.dtos.referentiel.BatimentDTO;
import com.myschool.sn.utils.dtos.referentiel.ClasseDTO;
import com.myschool.sn.utils.dtos.referentiel.MatiereDTO;
import com.myschool.sn.utils.dtos.referentiel.SalleDTO;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("dtoFactoryRef")
public class DTOFactoryRef {

    /**************  AnneeScolaire  ****************/
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

    /*************** Batiment ******************/
    public BatimentDTO createBatimentDTO(Batiment batiment) {
        if (batiment == null)
            return null;
        BatimentDTO dto = new BatimentDTO();
        dto.setId(batiment.getId());
        dto.setLibelle(batiment.getLibelle());
        dto.setActif(batiment.isActif());
        return dto;
    }

    public List<BatimentDTO> createListeBatimentDTO(List<Batiment> batiments) {
        if (batiments == null)
            return new ArrayList<>();
        List<BatimentDTO> dtos = new ArrayList<>();
        for (Batiment batiment: batiments) {
            dtos.add(createBatimentDTO(batiment));
        }
        return dtos;
    }

    /****************** Classe *******************/
    public ClasseDTO createClasseDTO(Classe model) {
        if (model == null)
            return null;
        ClasseDTO dto = new ClasseDTO();
        dto.setId(model.getId());
        dto.setLibelle(model.getLibelle());
        dto.setActif(model.isActif());
        return dto;
    }

    public List<ClasseDTO> createListeClasseDTO(List<Classe> classes) {
        if (classes == null)
            return new ArrayList<>();
        List<ClasseDTO> dtos = new ArrayList<>();
        for (Classe classe: classes) {
            dtos.add(createClasseDTO(classe));
        }
        return dtos;
    }

    /****************** Matiere *******************/
    public MatiereDTO createMatiereDTO(Matiere model) {
        if (model == null)
            return null;
        MatiereDTO dto = new MatiereDTO();
        dto.setId(model.getId());
        dto.setCode(model.getCode());
        dto.setLibelle(model.getLibelle());
        dto.setActif(model.isActif());
        return dto;
    }

    public List<MatiereDTO> createListeMatiereDTO(List<Matiere> matieres) {
        if (matieres == null)
            return new ArrayList<>();
        List<MatiereDTO> dtos = new ArrayList<>();
        for (Matiere matiere: matieres) {
            dtos.add(createMatiereDTO(matiere));
        }
        return dtos;
    }

    /****************** Salle *******************/
    public SalleDTO createSalleDTO(Salle model) {
        if (model == null)
            return null;
        SalleDTO dto = new SalleDTO();
        dto.setId(model.getId());
        dto.setLibelle(model.getLibelle());
        dto.setBatimentDTO(createBatimentDTO(model.getBatiment()));
        dto.setActif(model.isActif());
        return dto;
    }

    public List<SalleDTO> createListeSalleDTO(List<Salle> salles) {
        if (salles == null)
            return new ArrayList<>();
        List<SalleDTO> dtos = new ArrayList<>();
        for (Salle salle: salles) {
            dtos.add(createSalleDTO(salle));
        }
        return dtos;
    }
}
