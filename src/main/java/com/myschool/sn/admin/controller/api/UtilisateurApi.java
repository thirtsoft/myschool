package com.myschool.sn.admin.controller.api;

import com.myschool.sn.utils.dtos.admin.ActionDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurListDTO;
import com.myschool.sn.utils.dtos.admin.UtilisateurProfilDTO;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.admin.login.UserCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/utilisateur")
public interface UtilisateurApi {

    @PostMapping("/users-internal")
    ReponseMessageDTO createUserInternal(@Valid @RequestBody UtilisateurDTO user);

    @GetMapping("/get-me")
    ResponseEntity<UtilisateurDTO> getMe() throws Exception;

    @GetMapping("/name/{userId}")
    ResponseEntity<String> getUserNameById(@PathVariable @NotNull Long userId);

    @GetMapping("/{userId}")
    ResponseEntity<UtilisateurDTO> getUserById(@PathVariable @NotNull Long userId);

    @GetMapping("/email/{email}")
    ResponseEntity<UtilisateurDTO> getUserById(@PathVariable @NotNull String email);

    @PutMapping("/{userId}/credentials")
    ReponseMessageDTO updateUserCredentials(@PathVariable @NotNull Long userId, @Valid @RequestBody UserCredentials userCredentials);

    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable @NotNull Long userId);

    @GetMapping(value = "/{userId}/actions")
    List<ActionDTO> canDo(@PathVariable Long userId);

    @PostMapping(value = "/reset")
    ReponseMessageDTO resetPassword(@RequestParam String email);

    @GetMapping(value = "/monprofil/{userId}")
    UtilisateurProfilDTO getUserDetails(@PathVariable Long userId) throws Exception;

    @GetMapping(value = "/list")
    ResponseEntity<List<UtilisateurListDTO>> getUtilisateursList();

}
