package com.myschool.sn.admin.service;

import com.myschool.sn.utils.dtos.admin.UtilisateurDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurProfilDTO;
import com.myschool.sn.utils.dtos.admin.login.ChangePasswordDTO;
import com.myschool.sn.utils.dtos.admin.login.JwtResponse;
import com.myschool.sn.utils.dtos.admin.login.LoginRequest;
import com.myschool.sn.utils.dtos.admin.login.UserCredentials;

public interface UserService {

    void createUser(UtilisateurDTO utilisateurDTO) throws Exception;

    UtilisateurDTO getMe() throws Exception;

    UtilisateurDTO getUser(Long userId);

    void updateUserCredentials(Long userId, UserCredentials userCredentials);

    void deleteUser(Long userId);

    void resetPass(String email);

    void updateUserPassword(ChangePasswordDTO changePasswordDTO);

    String getUserName(Long userId);

    UtilisateurProfilDTO getUserDetails(Long userId);

    UtilisateurDTO getUserByUsername(String username);

}
