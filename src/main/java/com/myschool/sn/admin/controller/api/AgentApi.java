package com.myschool.sn.admin.controller.api;

import com.myschool.sn.utils.dtos.admin.login.JwtResponse;
import com.myschool.sn.utils.dtos.admin.login.LoginRequest;
import com.myschool.sn.utils.dtos.admin.register.AgentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/api/v1")
public interface AgentApi {

    @PostMapping("/agent")
    ResponseEntity<String> saveAgent(@Valid @RequestBody AgentDTO agentDTO);

}
