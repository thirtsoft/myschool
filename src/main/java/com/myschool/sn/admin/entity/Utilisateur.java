package com.myschool.sn.admin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "utilisateur")
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "motdepasse")
    private String motdepasse;

    private int actif = 1;

    private String email;

    @Column(unique = true)
    private String activation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profil_id", referencedColumnName = "id", nullable = false)
    private Profil profil;

}
