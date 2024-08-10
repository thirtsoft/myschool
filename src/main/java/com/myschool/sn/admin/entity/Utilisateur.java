package com.myschool.sn.admin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "utilisateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur implements UserDetails {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

//    @SequenceGenerator(
//            name = "MAT_SEG_GEN",
//            sequenceName = "matriculeGenerator"
//    )
//    @GeneratedValue(generator = "matriculeGenerator", strategy = GenerationType.SEQUENCE)
//    @Column(unique = true)
//    private String matricule;

    @Column(name = "motdepasse")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@#$%^&+=]).{8,}$")
    private String motdepasse;

    private boolean actif;

    @Column(unique = true)
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @Column(unique = true)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Column(unique = true)
    @NotEmpty(message = "Telephone cannot be empty")
    private String telephone;

    @Column(unique = true)
    private String activation;

    private String typeCompte;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profil_uid", referencedColumnName = "id", nullable = false)
    private Profil profil;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getProfil()
                .getAction().stream()
                .map(Action::getLibelle)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.motdepasse;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.actif;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.actif;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.actif;
    }

    @Override
    public boolean isEnabled() {
        return this.actif;
    }
}
