package com.myschool.sn.admin.controller;

import com.myschool.sn.admin.controller.api.UtilisateurApi;
import com.myschool.sn.admin.service.UserService;
import com.myschool.sn.utils.dtos.admin.ActionDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurListDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurProfilDTO;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.admin.login.UserCredentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurApi {

    private final UserService userService;

    public UtilisateurController(UserService userService) {
        this.userService = userService;
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
    public ResponseEntity<UtilisateurDTO> getUserById(Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
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
    public ReponseMessageDTO updateUserInternal(Long userId, UtilisateurDTO utilisateurDTO) {
        try {
            userService.updateUser(userId, utilisateurDTO);
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
    public UtilisateurProfilDTO getUserDetails(Long userId) throws Exception {
        return userService.getUserDetails(userId);
    }

    @Override
    public ResponseEntity<List<UtilisateurListDTO>> getUtilisateursList() {
        return new ResponseEntity<>(userService.findAllUtilisateur(), HttpStatus.OK);
    }

    @Override
    public ReponseMessageDTO activatedAccount(Long userId) {
        try {
            userService.activatedAccount(userId);
            return new ReponseMessageDTO("OK", "Compte utlisateur activé avec succès");
        } catch (Exception e) {
            return new ReponseMessageDTO("FAILED", e.getMessage());

        }
    }

    @Override
    public ReponseMessageDTO deactivatedAccount(Long userId) {
        try {
            userService.deactivatedAccount(userId);
            return new ReponseMessageDTO("OK", "Compte utlisateur desactivé avec succès");
        } catch (Exception e) {
            return new ReponseMessageDTO("FAILED", e.getMessage());

        }
    }
}