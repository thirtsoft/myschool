package com.myschool.sn.admin.controller;

import com.myschool.sn.admin.controller.api.ProfilApi;
import com.myschool.sn.admin.service.ActionService;
import com.myschool.sn.admin.service.ProfilServiceCustom;
import com.myschool.sn.utils.dtos.admin.ActionDTO;
import com.myschool.sn.utils.dtos.admin.ProfilDTO;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.myschool.sn.utils.MessageValueResponse.EDIT_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.FAILED_MESSAGE;
import static com.myschool.sn.utils.MessageValueResponse.SAVED_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.SUCCESS_MESSAGE;

@RestController
public class ProfilController implements ProfilApi {

    private final ProfilServiceCustom profilServiceCustom;

    private final ActionService actionService;

    public ProfilController(ProfilServiceCustom profilServiceCustom, ActionService actionService) {
        this.profilServiceCustom = profilServiceCustom;
        this.actionService = actionService;
    }

    @Override
    public List<ProfilDTO> getProfils() {
        return profilServiceCustom.findAllActives();
    }

    @Override
    public ProfilDTO getProfil(Long profileId) {
        return profilServiceCustom.findProfilById(profileId);
    }

    @Override
    public ReponseMessageDTO createOrUpdateProfil(ProfilDTO profilDTO) {
        try {
            profilServiceCustom.saveProfil(profilDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, e.getMessage());
        }
    }

    @Override
    public ReponseMessageDTO updateProfile(Long profileId, ProfilDTO profilDTO) {
        try {
            profilServiceCustom.updateProfile(profileId, profilDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, e.getMessage());
        }
    }

    @Override
    public ReponseMessageDTO deleteProfil(Long profilId) {
        try {
            profilServiceCustom.deleteProfil(profilId);
            return new ReponseMessageDTO("OK", "Profil supprimé avec succès");
        } catch (Exception e) {
            return new ReponseMessageDTO("FAILED", "Erreur lors de la suppression du profil");
        }
    }

    @Override
    public List<ProfilDTO> getProfilByType(String type) {
        return profilServiceCustom.findProfilByTypeCompte(type);
    }

    @Override
    public List<ActionDTO> gtActions() {
        return actionService.findAll();
    }

    @Override
    public List<ActionDTO> getActions(String type) {
        return profilServiceCustom.getListeActionByTypeCompte(type);
    }

    @Override
    public ActionDTO getActionById(Long actionId) {
        return actionService.findById(actionId);
    }

    @Override
    public ReponseMessageDTO createOrUpdateAction(ActionDTO actionDTO) {
        try {
            actionService.saveAction(actionDTO);
            return new ReponseMessageDTO("OK", "Action enregistré avec succès");
        } catch (Exception e) {
            return new ReponseMessageDTO("FAILED", "Erreur lors de l'enregistrement de l'action");
        }
    }

    @Override
    public ReponseMessageDTO updateAction(Long actionId, ActionDTO actionDTO) {
        try {
            actionService.updateAction(actionId, actionDTO);
            return new ReponseMessageDTO("OK", "Action modifié avec succès");
        } catch (Exception e) {
            return new ReponseMessageDTO("FAILED", "Erreur lors de la modification de l'action");
        }
    }

    @Override
    public ReponseMessageDTO deleteAction(Long actionId) {
        try {
            actionService.deleteAction(actionId);
            return new ReponseMessageDTO("OK", "Action supprimé avec succès");
        } catch (Exception e) {
            return new ReponseMessageDTO("FAILED", "Erreur lors de la suppression de l'action");
        }
    }
}