package com.myschool.sn.admin.service;

import com.myschool.sn.utils.dtos.admin.login.JwtResponse;
import com.myschool.sn.utils.dtos.admin.login.LoginRequest;

public interface AuthenticationService {

    JwtResponse authenticateUser(LoginRequest loginRequest);
}
