package com.myschool.sn.admin.service.Impl;

import com.myschool.sn.admin.entity.Profil;
import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.admin.exception.ForbiddenActionException;
import com.myschool.sn.admin.mapping.ModelFactory;
import com.myschool.sn.admin.repository.ProfilRepository;
import com.myschool.sn.admin.repository.UtilisateurRepository;
import com.myschool.sn.admin.service.RegisterService;
import com.myschool.sn.utils.ConstantSigs;
import com.myschool.sn.utils.dtos.admin.register.AgentDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UtilisateurRepository utilisateurRepository;

    private final ProfilRepository profilRepository;

    private final ModelFactory modelFactory;

    private final PasswordEncoder passwordEncoder;

    private static final String USER_ALREADY_EXIST = "Un utilisateur avec de mail existe dèjà.";
    private static final String USER_NOT_FOUND = "User with id {0} not found";
    @Override
    public void registerUser(AgentDTO agentDTO) {
        String newPassword = RandomStringUtils.randomAlphanumeric(8);
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByUsername(agentDTO.getUsername());
        if (utilisateur != null)
            throw new ForbiddenActionException(USER_ALREADY_EXIST);
        utilisateur = modelFactory.createUtilisateur(agentDTO);
        utilisateur.setMotdepasse(passwordEncoder.encode(newPassword));
        utilisateur.setActif(true);
        do {
            utilisateur.setActivation(RandomStringUtils.randomAlphanumeric(15));
        } while (utilisateurRepository.findByEmailOrTel(utilisateur.getEmail()) != null);
        if(Objects.equals(agentDTO.getTypeCompte(), ConstantSigs.TYPE_COMPTE_ADM)) {
            Profil profil = profilRepository.findProfilById(ConstantSigs.PROFIL_AGENT);
            utilisateur.setProfil(profil);
        }
        else if(Objects.equals(agentDTO.getTypeCompte(), ConstantSigs.TYPE_COMPTE_PARA)) {
            Profil profil = profilRepository.findProfilById(ConstantSigs.PROFIL_PARENT);
            utilisateur.setProfil(profil);
        }
        utilisateurRepository.save(utilisateur);

    }
}
