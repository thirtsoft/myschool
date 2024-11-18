package com.myschool.sn.admin.service;

import com.myschool.sn.admin.exception.UtilisateurException;
import com.myschool.sn.admin.exception.UtilsException;
import com.myschool.sn.utils.dtos.admin.UtilisateurDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurListDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurProfilDTO;
import com.myschool.sn.utils.dtos.admin.login.ChangePasswordDTO;
import com.myschool.sn.utils.dtos.admin.login.UserCredentials;

import java.util.List;

public interface UserService {

    void createUser(UtilisateurDTO utilisateurDTO) throws UtilisateurException;

    UtilisateurDTO getMe() throws UtilisateurException;

    UtilisateurDTO getUser(Long userId);

    void updateUserCredentials(Long userId, UserCredentials userCredentials);

    void deleteUser(Long userId);

    void resetPass(String email);

    void updateUserPassword(ChangePasswordDTO changePasswordDTO);

    String getUserName(Long userId);

    UtilisateurProfilDTO getUserDetails(Long userId);

    UtilisateurDTO getUserByUsername(String username);

    UtilisateurDTO findUtilisateurByEleveId(Long eleveId);

    List<UtilisateurListDTO> findAllUtilisateur();


    void activatedAccount(Long userId);

    void deactivatedAccount(Long userId);

    void updateUser(Long userId, UtilisateurDTO utilisateurDTO) throws UtilisateurException;
}
