package com.myschool.sn.utils.dtos.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDTO {

    private Long id;

    @JsonIgnore
    private String motdepasse;

    private String username;

    @JsonIgnore
    private String activation;

    private ProfilDTO profilDTO;

}
