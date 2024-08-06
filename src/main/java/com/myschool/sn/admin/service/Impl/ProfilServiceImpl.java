package com.myschool.sn.admin.service.Impl;

import com.myschool.sn.admin.entity.Action;
import com.myschool.sn.admin.entity.Profil;
import com.myschool.sn.admin.exception.ProfilException;
import com.myschool.sn.admin.mapping.DTOFactory;
import com.myschool.sn.admin.mapping.ModelFactory;
import com.myschool.sn.admin.repository.ActionRepository;
import com.myschool.sn.admin.repository.ProfilRepository;
import com.myschool.sn.admin.service.ProfilServiceCustom;
import com.myschool.sn.utils.dtos.admin.ActionDTO;
import com.myschool.sn.utils.dtos.admin.ProfilDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfilServiceImpl implements ProfilServiceCustom {

    private final ProfilRepository profilRepository;

    private final ActionRepository actionRepository;

    private final DTOFactory dtoFactory;

    private final ModelFactory modelFactory;

    @Override
    public void saveProfil(ProfilDTO profilDTO) throws ProfilException {
        if (profilDTO == null)
            throw new ProfilException("L'object à enregistrer est null");
        if (profilDTO.getLibelle() == null || profilDTO.getLibelle().equals(""))
            throw new ProfilException("Le libellé est obligatoire");
        Profil pr = profilRepository.findByLibelle(profilDTO.getLibelle());
        if (profilDTO.getId() == null && pr != null || pr != null && !pr.getId().equals(profilDTO.getId()))
            throw new ProfilException("Ce libellé est déjà utilisé");
        if (profilDTO.getActionDTOs() == null)
            throw new ProfilException("Une action au moins est necessaire");
        Profil profil = modelFactory.createProfil(profilDTO);
        profilRepository.save(profil);
    }

    @Override
    public void deleteProfil(Long profilId) {
        Profil profil = profilRepository.findProfilById(profilId);
        profil.setActif(false);
        profilRepository.save(profil);
    }

    @Override
    public ProfilDTO findProfilByLibelle(String libelle) {
        if (libelle != null && "".equals(libelle))
            return null;
        return dtoFactory.createProfilDTO(profilRepository.findByLibelle(libelle));
    }

    @Override
    public ProfilDTO findProfilById(Long profilId) {
        if (profilId == null)
            return null;
        return dtoFactory.createProfilDTO(profilRepository.findProfilById(profilId));
    }

    @Override
    public List<ProfilDTO> findAllActives() {
        return dtoFactory.createListeProfilDTO(profilRepository.findAllActive());
    }

    @Override
    public List<ActionDTO> getListeAction() {
        List<Action> list = actionRepository.findAll();
        return dtoFactory.createListeActionDTO(list);
    }

    @Override
    public List<ActionDTO> getListeActionByTypeCompte(String typeProfil) {
        List<Action> list = actionRepository.findByTypeCompte(typeProfil);
        return dtoFactory.createListeActionDTO(list);
    }

    @Override
    public List<ActionDTO> getListActionByRoleId(Long roleId) {
        Profil profil = profilRepository.findProfilById(roleId);
        return profil.getAction().stream().
                map(dtoFactory::createActionDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProfilDTO> findProfilByTypeCompte(String type) {
        List<Profil> list = profilRepository.findByTypeCompte(type);
        return dtoFactory.createListeProfilDTO(list);
    }
}