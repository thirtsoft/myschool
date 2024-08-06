package com.myschool.sn.admin.mapping;

import com.myschool.sn.admin.entity.Action;
import com.myschool.sn.admin.entity.Profil;
import com.myschool.sn.utils.UtilString;
import com.myschool.sn.utils.dtos.admin.ActionDTO;
import com.myschool.sn.utils.dtos.admin.ProfilDTO;

import javax.inject.Named;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named("modelFactory")
public class ModelFactory {

    public Action createAction(ActionDTO actionDTO) {
        if (actionDTO == null)
            return null;
        Action action = new Action();
        action.setCode(actionDTO.getCode());
        action.setId(actionDTO.getId());
        action.setLibelle(actionDTO.getLibelle());
        action.setModule(actionDTO.getModule());
        action.setTypeCompte(actionDTO.getTypeCompte());
        return action;
    }

    public Set<Action> createSetAction(List<ActionDTO> actionDTOs) {
        if (actionDTOs == null)
            return null;
        Set<Action> actions = new HashSet<>();
        for (ActionDTO dto : actionDTOs)
            actions.add(createAction(dto));
        return actions;
    }

    public Profil createProfil(ProfilDTO profilDTO) {
        if (profilDTO == null)
            return null;
        Profil profil = new Profil();
        profil.setActif(true);
        profil.setAction(createSetAction(profilDTO.getActionDTOs()));
        profil.setId(profilDTO.getId());
        profil.setLibelle(UtilString.capitalizeFirst(profilDTO.getLibelle()));
        profil.setTypeCompte(profilDTO.getTypeCompte());
        return profil;
    }

}
