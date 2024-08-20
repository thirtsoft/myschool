package com.myschool.sn.enseignant.mapping;

import com.myschool.sn.enseignant.entity.Conges;
import com.myschool.sn.enseignant.entity.Enseignant;
import com.myschool.sn.utils.dtos.enseignant.CongesDTO;
import com.myschool.sn.utils.dtos.enseignant.EnseignantDTO;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("dtoFactoryEns")
public class DTOFactoryEns {

    public EnseignantDTO createEnseignantDTO(Enseignant model) {
        if (model == null)
            return null;
        EnseignantDTO dto = new EnseignantDTO();
        dto.setId(model.getId());
        dto.setMatricule(model.getMatricule());
        dto.setNom(model.getNom());
        dto.setPrenom(model.getPrenom());
        dto.setCivilite(model.getCivilite());
        dto.setCni(model.getCni());
        dto.setAdresse(model.getAdresse());
        dto.setTelephone(model.getTelephone());
        dto.setEmail(model.getEmail());
        dto.setActif(model.isActif());
        return dto;
    }

    public List<EnseignantDTO> createListeEnseignantDTO(List<Enseignant> enseignants) {
        if (enseignants == null)
            return new ArrayList<>();
        List<EnseignantDTO> dtos = new ArrayList<>();
        for (Enseignant enseignant : enseignants) {
            dtos.add(createEnseignantDTO(enseignant));
        }
        return dtos;
    }

    public CongesDTO createCongesDTO(Conges model) {
        if (model == null)
            return null;
        CongesDTO dto = new CongesDTO();
        dto.setId(model.getId());
        dto.setMotif(model.getMotif());
        dto.setEnseignantDTO(createEnseignantDTO(model.getEnseignant()));
        dto.setEtat(model.getEtat());
        dto.setDateDebut(model.getDateDebut());
        dto.setDateFin(model.getDateFin());
        dto.setActif(model.isActif());
        return dto;
    }

    public List<CongesDTO> createListeCongesDTO(List<Conges> conges) {
        if (conges == null)
            return new ArrayList<>();
        List<CongesDTO> dtos = new ArrayList<>();
        for (Conges conge: conges) {
            dtos.add(createCongesDTO(conge));
        }
        return dtos;
    }
}
