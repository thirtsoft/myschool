package com.myschool.sn.admin.mapping;

import com.myschool.sn.admin.entity.Action;
import com.myschool.sn.admin.entity.Profil;
import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.dossiereleve.mapping.DTOFactoryDossierEl;
import com.myschool.sn.utils.dtos.admin.ActionDTO;
import com.myschool.sn.utils.dtos.admin.ProfilDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurListDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurProfilDTO;
import com.myschool.sn.utils.dtos.admin.register.AgentDTO;
import com.myschool.sn.utils.dtos.parent.ParentDetailsDTO;
import com.myschool.sn.utils.dtos.parent.ParentListeDTO;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;

@Named("dtoFactory")
@RequiredArgsConstructor
public class DTOFactory implements Serializable {

    private final DTOFactoryDossierEl dtoFactoryDossierEl;

    public ActionDTO createActionDTO(Action action) {
        if (action == null)
            return null;
        ActionDTO dto = new ActionDTO();
        dto.setCode(action.getCode());
        dto.setId(action.getId());
        dto.setLibelle(action.getLibelle());
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
            return emptyList();
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
            return emptyList();
        List<ProfilDTO> dtos = new ArrayList<>();
        for (Profil profil : profils) {
            dtos.add(createProfilDTO(profil));
        }
        return dtos;
    }

    public UtilisateurDTO createUtilisateurDTO(Utilisateur model) {
        if (model == null) return null;
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(model.getId());
        dto.setUsername(model.getUsername());
        dto.setPrenom(model.getPrenom());
        dto.setNom(model.getNom());
        dto.setAddress(model.getAddress());
        dto.setTelephone(model.getTelephone());
        dto.setEmail(model.getEmail());
        dto.setCivility(model.getCivility());
        dto.setProfession(model.getProfession());
        dto.setProfilDTO(createProfilDTO(model.getProfil()));
        dto.setActif(model.isActive());
        dto.setMotdepasse(model.getMotdepasse());
        return dto;
    }

    public List<UtilisateurDTO> createListeUtilisateurDTO(List<Utilisateur> utilisateurs) {
        if (utilisateurs == null)
            return null;
        List<UtilisateurDTO> dtos = new ArrayList<>();
        for (Utilisateur utilisateur : utilisateurs) {
            dtos.add(createUtilisateurDTO(utilisateur));
        }
        return dtos;
    }

    public UtilisateurProfilDTO createUtilisateurProfilDTO(Utilisateur model) {
        if (model == null)
            return null;
        UtilisateurProfilDTO dto = new UtilisateurProfilDTO();
        dto.setEmail(model.getUsername());
        dto.setTypeProfil(model.getProfil().getTypeCompte());
        dto.setProfil(model.getProfil().getLibelle());
        dto.setActive(model.isActive());
        return dto;
    }

    public UtilisateurListDTO createUtilisateurListDTO(Utilisateur model) {
        if (model == null)
            return null;
        UtilisateurListDTO dto = new UtilisateurListDTO();
        dto.setId(model.getId());
        dto.setUsername(model.getUsername());
        dto.setCivility(model.getCivility());
        dto.setAddress(model.getAddress());
        dto.setNomComplet(model.getPrenom() + ' ' + model.getNom());
        dto.setTelephone(model.getTelephone());
        dto.setEmail(model.getEmail());
        dto.setProfession(model.getProfession());
        dto.setActif(model.isActif());
        dto.setProfil(model.getProfil().getLibelle());
        dto.setActive(model.isActive());
        return dto;
    }
    
    public AgentDTO createAgent(Utilisateur utilisateur) {
        if (utilisateur == null)
            return null;
        AgentDTO agentDTO = new AgentDTO();
        agentDTO.setUsername(utilisateur.getUsername());
        agentDTO.setEmail(utilisateur.getEmail());
        agentDTO.setTelephone(utilisateur.getTelephone());
        agentDTO.setProfilDTO(createProfilDTO(utilisateur.getProfil()));
        agentDTO.setTypeCompte(utilisateur.getTypeCompte());
        agentDTO.setActive(utilisateur.isActive());
        return agentDTO;
    }

}
