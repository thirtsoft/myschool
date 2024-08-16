package com.myschool.sn.utils.dtos.admin.register;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myschool.sn.utils.dtos.admin.ProfilDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AgentDTO {

    @JsonIgnore
    private String motdepasse;

    private String username;

    private String email;

    private String telephone;

    private String typeCompte;

    private ProfilDTO profilDTO;
}
