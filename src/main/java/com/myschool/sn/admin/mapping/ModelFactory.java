package com.myschool.sn.admin.mapping;

import com.myschool.sn.admin.entity.Action;
import com.myschool.sn.admin.entity.Profil;
import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.utils.UtilString;
import com.myschool.sn.utils.dtos.admin.ActionDTO;
import com.myschool.sn.utils.dtos.admin.ProfilDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurDTO;
import com.myschool.sn.utils.dtos.admin.register.AgentDTO;

import javax.inject.Named;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptySet;

@Named("modelFactory")
public class ModelFactory {

    public Action createAction(ActionDTO actionDTO) {
        if (actionDTO == null)
            return null;
        Action action = new Action();
        action.setCode(actionDTO.getCode());
        action.setId(actionDTO.getId());
        action.setLibelle(actionDTO.getLibelle());
        action.setTypeCompte(actionDTO.getTypeCompte());
        return action;
    }

    public Set<Action> createSetAction(List<ActionDTO> actionDTOs) {
        if (actionDTOs == null)
            return emptySet();
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

    /*
    public UtilisateurDetails createUtilisateurDetails(UtilisateurDetailsDTO dto) {
        if (dto == null)
            return null;
        UtilisateurDetails model = new UtilisateurDetails();
        model.setAgree(dto.getAgree());
        model.setCreatedBy(dto.getCreatedBy());
        model.setDateCreation(dto.getDateCreation());
        model.setFonction(UtilString.capitalizeFirst(dto.getFonction()));
        model.setId(dto.getId());
        model.setNom(dto.getNom().toUpperCase());
        model.setPrenom(UtilString.capitalizeWords(dto.getPrenom()));
        model.setService(modelFactoryRef.createService(dto.getServiceDTO()));
        model.setTelephone(dto.getTelephone());
        return model;
    }*/

    public Utilisateur createUtilisateur(UtilisateurDTO dto) {
        if (dto == null)
            return null;
        Utilisateur model = new Utilisateur();
        model.setActif(true);
        model.setActivation(dto.getActivation());
        model.setUsername(dto.getUsername().toLowerCase());
        model.setId(dto.getId());
        model.setMotdepasse(dto.getMotdepasse());
        model.setProfil(createProfil(dto.getProfilDTO()));
        return model;
    }

    public Set<Utilisateur> createSetUtilisateur(List<UtilisateurDTO> dtos) {
        if (dtos == null)
            return emptySet();
        Set<Utilisateur> list = new HashSet<>();
        for (UtilisateurDTO dto : dtos)
            list.add(createUtilisateur(dto));
        return list;
    }

    public Utilisateur createUtilisateur(AgentDTO dto) {
        if (dto == null)
            return null;
        Utilisateur model = new Utilisateur();
        model.setActif(true);
        model.setUsername(dto.getUsername().toLowerCase());
        model.setMotdepasse(dto.getMotdepasse());
        model.setTypeCompte(dto.getTypeCompte());
        model.setEmail(dto.getEmail());
        return model;
    }


}
