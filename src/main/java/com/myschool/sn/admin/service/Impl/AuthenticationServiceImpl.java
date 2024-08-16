package com.myschool.sn.admin.service.Impl;

import com.myschool.sn.admin.entity.Profil;
import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.admin.exception.BadRequestException;
import com.myschool.sn.admin.exception.ForbiddenActionException;
import com.myschool.sn.admin.mapping.DTOFactory;
import com.myschool.sn.admin.mapping.ModelFactory;
import com.myschool.sn.admin.repository.ProfilRepository;
import com.myschool.sn.admin.repository.UtilisateurRepository;
import com.myschool.sn.admin.security.service.JwtService;
import com.myschool.sn.admin.service.AuthenticationService;
import com.myschool.sn.admin.service.UtilsServiceCustom;
import com.myschool.sn.utils.dtos.admin.login.ActionListResponse;
import com.myschool.sn.utils.dtos.admin.login.JwtResponse;
import com.myschool.sn.utils.dtos.admin.login.LoginRequest;
import com.myschool.sn.utils.dtos.admin.login.ProfilReponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(
            UtilisateurRepository utilisateurRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        var utilisateur = utilisateurRepository.findUtilisateurByUsername(loginRequest.getUsername());
        if (utilisateur == null)
            throw new ForbiddenActionException("Cet utilisateur est désactivé ou n'existe pas");
        loginRequest.setUsername(utilisateur.getUsername());
        if(!passwordEncoder.matches(loginRequest.getPassword(), utilisateur.getMotdepasse()))
            throw new BadRequestException("Le mot de passe entré est incorrect");
        var authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        if (!authenticate.isAuthenticated()) {
            return null;
        }
    //    var utilisateur = utilisateurRepository.findUtilisateurByUsername(loginRequest.getUsername());
        var jwtToken = jwtService.generateToken(utilisateur);
        jwtService.generateRefreshToken(utilisateur);
    //    revokeAllUserTokens(utilisateur);
    //    saveUserToken(utilisateur, jwtToken);
        return JwtResponse.builder()
                .token(jwtToken)
                .id(utilisateur.getId())
                .email(utilisateur.getEmail())
                .name(utilisateur.getUsername())
                .typeCompte(utilisateur.getTypeCompte())
                .profilReponse(getProfile(utilisateur.getProfil()))
                .build();
    }

    private ProfilReponse getProfile(Profil profil) {
        return new ProfilReponse(getActionReponse(profil));
    }

    private List<ActionListResponse> getActionReponse(Profil profil) {
        return profil.getAction().stream()
                .map(action -> new ActionListResponse(action.getCode(), action.getLibelle()))
                .toList();
    }

}
