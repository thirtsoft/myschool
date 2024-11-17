package com.myschool.sn.admin.service.Impl;

import com.myschool.sn.admin.entity.Profil;
import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.admin.exception.ForbiddenActionException;
import com.myschool.sn.admin.exception.ResourceNotFoundException;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_ALREADY_EXIST = "Un utilisateur avec de mail existe dèjà.";
    private static final String USER_NOT_FOUND = "User with id {0} not found";
    private static final String PASSWORD_DID_NOT_MATCHED = "Le mot de passe courant est incorrect.";

    private final UtilisateurRepository utilisateurRepository;
    private final ProfilRepository profilRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final DTOFactory dtoFactory;

    private final ModelFactory modelFactory;

    private final AuthenticationManager authenticationManager;


    private final UtilsServiceCustom utilsRepositoryCustom;

    private final PasswordEncoder passwordEncoder;


    RestTemplate restTemplate = new RestTemplate();

    public UserServiceImpl(UtilisateurRepository utilisateurRepository,
                           ProfilRepository profilRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           DTOFactory dtoFactory,
                           ModelFactory modelFactory,
                           AuthenticationManager authenticationManager,
                           UtilsServiceCustom utilsRepositoryCustom,
                           PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.profilRepository = profilRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dtoFactory = dtoFactory;
        this.modelFactory = modelFactory;
        this.authenticationManager = authenticationManager;
        this.utilsRepositoryCustom = utilsRepositoryCustom;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void createUser(UtilisateurDTO utilisateurDTO) throws Exception {
        String newPassword = RandomStringUtils.randomAlphanumeric(8);
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByUsername(utilisateurDTO.getUsername());
        if ((utilisateurDTO.getId() == null && utilisateur != null) || (utilisateur != null
                && utilisateurDTO.getId() != null && !utilisateur.getId().equals(utilisateurDTO.getId())))
            throw new ForbiddenActionException(USER_ALREADY_EXIST);
        //     UtilisateurDetails details = modelFactory.createUtilisateurDetails(utilisateurDTO.getUtilisateurDetailsDTO());
        //     details = utilisateurDetailsRepository.saveAndFlush(details);
        utilisateur = modelFactory.createUtilisateur(utilisateurDTO);
        //    utilisateur.setUtilisateurDetails(details);
        utilisateur.setMotdepasse(passwordEncoder.encode(newPassword));
        //    utilisateur.getUtilisateurDetails().setDateCreation(new Date());
        utilisateur.setActif(true);
        do {
            utilisateur.setActivation(RandomStringUtils.randomAlphanumeric(15));
        } while (utilisateurRepository.findUtilisateurByActivation(utilisateur.getActivation()) != null);
        Profil profil = profilRepository.findProfilById(utilisateur.getProfil().getId());
        utilisateur.setProfil(profil);
        utilisateurRepository.save(utilisateur);
        utilsRepositoryCustom.sendMailCreateUser(utilisateur);
    }

    @Override
    public UtilisateurDTO getMe() throws Exception {
        Authentication userAuthenticated = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur user = utilisateurRepository.findByUsername(userAuthenticated.getName()).orElse(null);
        if (user == null) {
            throw new Exception("La session est associée a un utilisateur non reconnue");
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
        utilisateur.setMotdepasse(bCryptPasswordEncoder.encode(newPassword));
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
        if (utilisateur == null)
            throw new ForbiddenActionException("Cet utilisateur est désactivé ou n'existe pas");
        if (!bCryptPasswordEncoder.matches(changePasswordDTO.getCurrentPassword(), utilisateur.getMotdepasse())) {
            throw new ForbiddenActionException("Le mot de passe entré est différent du mot de passe actuel");
        }
        utilisateur.setMotdepasse(bCryptPasswordEncoder.encode(changePasswordDTO.getNewPassword()));
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

    public Utilisateur getUserById(Long userId) {
        return utilisateurRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format(USER_NOT_FOUND, userId)));
    }
}