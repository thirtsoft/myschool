package com.myschool.sn.admin.controller;

import com.myschool.sn.admin.controller.api.AgentApi;
import com.myschool.sn.admin.service.RegisterService;
import com.myschool.sn.utils.dtos.admin.register.AgentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AgentController implements AgentApi {

    private final RegisterService registerService;
    @Override
    public ResponseEntity<String> saveAgent(AgentDTO agentDTO) {
        try {
            registerService.registerUser(agentDTO);
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
