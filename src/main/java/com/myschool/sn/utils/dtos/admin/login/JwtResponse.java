package com.myschool.sn.utils.dtos.admin.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private Long id;

    private String token;

    private String name;

    private String email;

    private String typeCompte;

    private ProfilReponse profilReponse;
}
