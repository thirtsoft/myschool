package com.myschool.sn.admin.mapping;


import com.myschool.sn.admin.entity.Action;
import com.myschool.sn.admin.entity.Profil;
import com.myschool.sn.utils.dtos.admin.ActionDTO;
import com.myschool.sn.utils.dtos.admin.ProfilDTO;

import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Named("dtoFactory")
public class DTOFactory implements Serializable {

    public ActionDTO createActionDTO(Action action) {
        if (action == null)
            return null;
        ActionDTO dto = new ActionDTO();
        dto.setCode(action.getCode());
        dto.setId(action.getId());
        dto.setLibelle(action.getLibelle());
        dto.setModule(action.getModule());
        dto.setTypeCompte(action.getTypeCompte());
        return dto;
    }

    public List<ActionDTO> createListeActionDTO(List<Action> actions) {
        if (actions == null)
            return new ArrayList<>();
        List<ActionDTO> dtos = new ArrayList<>();
        for (Action action : actions) {
            dtos.add(createActionDTO(action));
        }
        return dtos;
    }

    public List<ActionDTO> createListeActionDTO(Set<Action> actions) {
        if (actions == null)
            return null;
        List<ActionDTO> dtos = new ArrayList<>();
        for (Action action : actions) {
            dtos.add(createActionDTO(action));
        }
        return dtos;
    }

    public ProfilDTO createProfilDTO(Profil profil) {
        if (profil == null)
            return null;
        ProfilDTO dto = new ProfilDTO();
        dto.setTypeCompte(profil.getTypeCompte());
        dto.setId(profil.getId());
        dto.setLibelle(profil.getLibelle());
        dto.setActionDTOs(createListeActionDTO(profil.getAction()));
        return dto;
    }

    public List<ProfilDTO> createListeProfilDTO(List<Profil> profils) {
        if (profils == null)
            return null;
        List<ProfilDTO> dtos = new ArrayList<>();
        for (Profil profil : profils) {
            dtos.add(createProfilDTO(profil));
        }
        return dtos;
    }
}
