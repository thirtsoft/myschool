package com.myschool.sn.admin.service.Impl;

import com.myschool.sn.admin.entity.Profil;
import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.admin.exception.ForbiddenActionException;
import com.myschool.sn.admin.exception.ResourceNotFoundException;
import com.myschool.sn.admin.exception.UtilisateurException;
import com.myschool.sn.admin.mapping.DTOFactory;
import com.myschool.sn.admin.mapping.ModelFactory;
import com.myschool.sn.admin.repository.ProfilRepository;
import com.myschool.sn.admin.repository.UtilisateurRepository;
import com.myschool.sn.admin.service.UserService;
import com.myschool.sn.admin.service.UtilsServiceCustom;
import com.myschool.sn.utils.dtos.admin.UtilisateurDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurListDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurProfilDTO;
import com.myschool.sn.utils.dtos.admin.login.ChangePasswordDTO;
import com.myschool.sn.utils.dtos.admin.login.UserCredentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.List;

import static com.myschool.sn.utils.ConstantSigs.PASSWORD_DID_NOT_MATCHED;
import static com.myschool.sn.utils.ConstantSigs.PASSWORD_PAR_DEFAULT;
import static com.myschool.sn.utils.ConstantSigs.USER_ALREADY_EXIST;
import static com.myschool.sn.utils.ConstantSigs.USER_NOT_FOUND;

@Service
public class UserServiceImpl implements UserService {

    private final UtilisateurRepository utilisateurRepository;
    private final ProfilRepository profilRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final DTOFactory dtoFactory;

    private final ModelFactory modelFactory;

    //   private final AuthenticationManager authenticationManager;


    private final UtilsServiceCustom utilsRepositoryCustom;

    private final BCryptPasswordEncoder passwordEncoder;


    RestTemplate restTemplate = new RestTemplate();

    public UserServiceImpl(UtilisateurRepository utilisateurRepository,
                           ProfilRepository profilRepository,
                           DTOFactory dtoFactory,
                           ModelFactory modelFactory,
                           UtilsServiceCustom utilsRepositoryCustom,
                           BCryptPasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.profilRepository = profilRepository;
        this.dtoFactory = dtoFactory;
        this.modelFactory = modelFactory;
        this.utilsRepositoryCustom = utilsRepositoryCustom;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void createUser(UtilisateurDTO utilisateurDTO) throws UtilisateurException {
        if (utilisateurDTO.getNom() == null || utilisateurDTO.getNom().isEmpty()) {
            throw new UtilisateurException("Le nom d'utilisateur est obligatoire");
        }
        if (utilisateurDTO.getPrenom() == null || utilisateurDTO.getPrenom().isEmpty()) {
            throw new UtilisateurException("Le prénom d'utilisateur est obligatoire");
        }
        if (utilisateurDTO.getTelephone() == null || utilisateurDTO.getTelephone().isEmpty()) {
            throw new UtilisateurException("Le téléphone d'utilisateur est obligatoire");
        }
        if (utilisateurDTO.getEmail() == null || utilisateurDTO.getEmail().isEmpty()) {
            throw new UtilisateurException("L'email d'utilisateur est obligatoire");
        }
        if (utilisateurDTO.getUsername() == null || utilisateurDTO.getUsername().isEmpty()) {
            throw new UtilisateurException("Le nom d'utilisateur est obligatoire");
        }
        if (utilisateurDTO.getProfilDTO() == null || utilisateurDTO.getProfilDTO().getId() == null) {
            throw new UtilisateurException("Le profile de l'utilisateur est obligatoire");
        }
        var utilisateur = utilisateurRepository.findUtilisateurByUsername(utilisateurDTO.getUsername());
        if (utilisateurDTO.getId() == null && utilisateur != null || utilisateur != null && !utilisateur.getId().equals(utilisateurDTO.getId()))
            throw new ForbiddenActionException(USER_ALREADY_EXIST);
        utilisateur = utilisateurRepository.findUtilisateurByTelephone(utilisateurDTO.getTelephone());
        if (utilisateurDTO.getId() == null && utilisateur != null || utilisateur != null && !utilisateur.getId().equals(utilisateurDTO.getId()))
            throw new ForbiddenActionException(USER_ALREADY_EXIST);
        utilisateur = modelFactory.createUtilisateur(utilisateurDTO);
        utilisateur.setMotdepasse(passwordEncoder.encode(PASSWORD_PAR_DEFAULT));
        utilisateur.setActif(true);
        utilisateur.setActive(true);
        do {
            utilisateur.setActivation(RandomStringUtils.randomAlphanumeric(15));
        } while (utilisateurRepository.findUtilisateurByActivation(utilisateur.getActivation()) != null);
        Profil profil = profilRepository.findProfilById(utilisateur.getProfil().getId());
        utilisateur.setProfil(profil);
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public UtilisateurDTO getMe() throws UtilisateurException {
        Authentication userAuthenticated = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur user = utilisateurRepository.findByUsername(userAuthenticated.getName()).orElse(null);
        if (user == null) {
            throw new UtilisateurException("La session est associée a un utilisateur non reconnue");
        }
        return dtoFactory.createUtilisateurDTO(user);
    }

    @Override
    public UtilisateurDTO getUser(Long userId) {
        Utilisateur utilisateur = getUserById(userId);
        return dtoFactory.createUtilisateurDTO(utilisateur);
    }

    @Override
    public void updateUserCredentials(Long userId, UserCredentials userCredentials) {
        Utilisateur utilisateur = getUserById(userId);
        if (!passwordEncoder.matches(userCredentials.getCurrentPassword(), utilisateur.getMotdepasse())) {
            throw new ForbiddenActionException(PASSWORD_DID_NOT_MATCHED);
        }
        utilisateur.setMotdepasse(passwordEncoder.encode(userCredentials.getNewPassword()));
        utilisateur.setActif(true);
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public void deleteUser(Long userId) {
        Utilisateur utilisateur = getUserById(userId);
        utilisateur.setActif(false);
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public void resetPass(String email) {
        Authentication userAuthenticated = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(userAuthenticated.getName()).orElse(null);
        if (utilisateur == null)
            throw new ForbiddenActionException("Cet utilisateur est désactivé ou n'existe pas");
        String newPassword = RandomStringUtils.randomAlphanumeric(8);
        utilisateur.setMotdepasse(passwordEncoder.encode(newPassword));
        utilisateurRepository.save(utilisateur);
        try {
            utilsRepositoryCustom.sendMailForgotPass(utilisateur, newPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserPassword(ChangePasswordDTO changePasswordDTO) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(changePasswordDTO.getUsername()).orElseThrow();
        if (!passwordEncoder.matches(changePasswordDTO.getCurrentPassword(), utilisateur.getMotdepasse())) {
            throw new ForbiddenActionException("Le mot de passe entré est différent du mot de passe actuel");
        }
        utilisateur.setMotdepasse(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public String getUserName(Long userId) {
        Utilisateur utilisateur = getUserById(userId);
        return utilisateur.getUsername();
        //    return utilisateur.getUtilisateurDetails().getPrenom() + " " +utilisateur.getUtilisateurDetails().getNom();
    }

    @Override
    public UtilisateurProfilDTO getUserDetails(Long userId) {
        Utilisateur model = utilisateurRepository.findUtilisateurById(userId);
        return dtoFactory.createUtilisateurProfilDTO(model);
    }

    @Override
    public UtilisateurDTO getUserByUsername(String username) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username).orElseThrow();
        return dtoFactory.createUtilisateurDTO(utilisateur);
    }

    @Override
    public UtilisateurDTO findUtilisateurByEleveId(Long eleveId) {
        return null;
    }

    @Override
    public List<UtilisateurListDTO> findAllUtilisateur() {
        return utilisateurRepository.findAllActive().stream()
                .map(dtoFactory::createUtilisateurListDTO)
                .toList();
    }

    @Override
    public void activatedAccount(Long userId) {
        if (userId == null) {
            throw new ResourceNotFoundException(MessageFormat.format(USER_NOT_FOUND, userId));
        }
        var foundUser = getUserById(userId);
        foundUser.setActive(true);
        utilisateurRepository.save(foundUser);
    }

    @Override
    public void deactivatedAccount(Long userId) {
        if (userId == null) {
            throw new ResourceNotFoundException(MessageFormat.format(USER_NOT_FOUND, userId));
        }
        var foundUser = getUserById(userId);
        foundUser.setActive(false);
        utilisateurRepository.save(foundUser);
    }

    @Override
    public void updateUser(Long userId, UtilisateurDTO utilisateurDTO) throws UtilisateurException {
        if (userId == null) {
            throw new UtilisateurException(MessageFormat.format(USER_NOT_FOUND, userId));
        }
        utilisateurDTO.setId(userId);
        createUser(utilisateurDTO);
    }

    public Utilisateur getUserById(Long userId) {
        return utilisateurRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format(USER_NOT_FOUND, userId)));
    }
}