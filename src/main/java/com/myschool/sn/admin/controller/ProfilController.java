package com.myschool.sn.admin.controller;

import com.myschool.sn.admin.controller.api.ProfilApi;
import com.myschool.sn.admin.service.ProfilServiceCustom;
import com.myschool.sn.utils.dtos.admin.ActionDTO;
import com.myschool.sn.utils.dtos.admin.ProfilDTO;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfilController implements ProfilApi {

    private final ProfilServiceCustom profilServiceCustom;

    public ProfilController(ProfilServiceCustom profilServiceCustom) {
        this.profilServiceCustom = profilServiceCustom;
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
            return new ReponseMessageDTO("OK", "Profil ajouté avec succès");
        } catch (Exception e) {
            return new ReponseMessageDTO("FAILED",  e.getMessage());
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
    public List<ActionDTO> getActions() {
        return profilServiceCustom.getListeAction();
    }

    @Override
    public List<ActionDTO> getActions(String type) {
        return profilServiceCustom.getListeActionByTypeCompte(type);
    }
}