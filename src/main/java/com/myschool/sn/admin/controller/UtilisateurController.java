package com.myschool.sn.admin.controller;

import com.myschool.sn.admin.controller.api.UtilisateurApi;
import com.myschool.sn.admin.service.ProfilServiceCustom;
import com.myschool.sn.admin.service.UserService;
import com.myschool.sn.utils.dtos.admin.ActionDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurProfilDTO;
import com.myschool.sn.utils.dtos.admin.login.JwtResponse;
import com.myschool.sn.utils.dtos.admin.login.LoginRequest;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.admin.login.UserCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class UtilisateurController implements UtilisateurApi {

    private final UserService userService;

    private final ProfilServiceCustom profilServiceCustom;

    public UtilisateurController(UserService userService, ProfilServiceCustom profilServiceCustom) {
        this.userService = userService;
        this.profilServiceCustom = profilServiceCustom;
    }


    @Override
    public ReponseMessageDTO createUserInternal(UtilisateurDTO user) {
        try {
            userService.createUser(user);
            return new ReponseMessageDTO("OK", "Utilisateur enregistré avec succès");
        } catch (Exception e) {
            return new ReponseMessageDTO("FAILED", e.getMessage());
        }
    }

    @Override
    public ResponseEntity<UtilisateurDTO> getMe() throws Exception {
        return ResponseEntity.ok(userService.getMe());
    }

    @Override
    public ResponseEntity<String> getUserNameById(Long userId) {
        return ResponseEntity.ok(userService.getUserName(userId));
    }

    @Override
    public ResponseEntity<UtilisateurDTO> getUserById(Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @Override
    public ResponseEntity<UtilisateurDTO> getUserById(String email) {
        return ResponseEntity.ok(userService.getUserByUsername(email));
    }

    @Override
    public ReponseMessageDTO updateUserCredentials(Long userId, UserCredentials userCredentials) {
        try {
            userService.updateUserCredentials(userId, userCredentials);
            return new ReponseMessageDTO("OK", "Utilisateur modifié avec succès");
        } catch (Exception e) {
            return new ReponseMessageDTO("FAILED", e.getMessage());

        }
    }

    @Override
    public void deleteUser(Long userId) {
        userService.deleteUser(userId);
    }

    @Override
    public List<ActionDTO> canDo(Long userId) {
        return null;
    }

    @Override
    public ReponseMessageDTO resetPassword(String email) {
        try {
            userService.resetPass(email);
            return new ReponseMessageDTO("OK", "Mot de passe modifié avec succès");

        } catch (Exception e) {
            return new ReponseMessageDTO("FAILED", e.getMessage());
        }
    }

    @Override
    public UtilisateurProfilDTO getUserDetails(Long userId) throws Exception {
        return userService.getUserDetails(userId);
    }
}