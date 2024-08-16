package com.myschool.sn.admin.controller;

import com.myschool.sn.admin.controller.api.AuthentificationApi;
import com.myschool.sn.admin.service.AuthenticationService;
import com.myschool.sn.utils.dtos.admin.login.JwtResponse;
import com.myschool.sn.utils.dtos.admin.login.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthentificationController implements AuthentificationApi {

    private final AuthenticationService authenticationService;

    public AuthentificationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public ResponseEntity<JwtResponse> authenticateUser(LoginRequest loginRequest) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticateUser(loginRequest));
    }
}
