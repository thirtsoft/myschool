package com.myschool.sn.admin.service;

import com.myschool.sn.admin.exception.ProfilException;
import com.myschool.sn.utils.dtos.admin.ActionDTO;
import com.myschool.sn.utils.dtos.admin.ProfilDTO;

import java.util.List;

public interface ProfilServiceCustom {
    void saveProfil(ProfilDTO profilDTO) throws ProfilException;

    void deleteProfil(Long profilId);

    ProfilDTO findProfilByLibelle(String libelle);

    ProfilDTO findProfilById(Long profilId);

    List<ProfilDTO> findAllActives();

    List<ActionDTO> getListeAction();

    List<ActionDTO> getListeActionByTypeCompte(String typeProfil);

    List<ActionDTO> getListActionByRoleId(Long roleId);

    List<ProfilDTO> findProfilByTypeCompte(String type);

    void updateProfile(Long profileId, ProfilDTO profilDTO);
}
