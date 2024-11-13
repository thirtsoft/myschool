package com.myschool.sn;

import com.myschool.sn.admin.repository.ActionRepository;
import com.myschool.sn.admin.repository.ProfilRepository;
import com.myschool.sn.admin.repository.UtilisateurRepository;
import com.myschool.sn.admin.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class MyschoolApplication implements CommandLineRunner {

    private final ActionRepository actionRepository;

    private final ProfilRepository profilRepository;

    private final UtilisateurRepository utilisateurRepository;

    private final AuthenticationService authenticationService;

    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(MyschoolApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        /*

        Action action1 = new Action(1L, "ADD_EL", "Ajouter/Modifier/Supprimer un élève", "AGENT", 1);
        Action action2 = new Action(2L, "ADD_PAR", "Ajouter/Modifier/Supprimer un parent d'élève", "AGENT", 1);
        Action action3 = new Action(3L, "ADD_ENS", "Ajouter/Modifier/Supprimer un(e) enseignant(e)", "AGENT", 1);
        Action action4 = new Action(4L, "LST_EL", "Lister les élèves", "AGENT", 1);
        Action action5 = new Action(5L, "LST_PAR", "Lister les parents d'élèves", "AGENT", 1);
        Action action6 = new Action(6L, "LST_ENS", "Lister les enseignant(e)s", "AGENT", 1);
        Action action7 = new Action(7L, "ADD_INSC", "Ajouter/Modifier/Supprimer l'inscription d'un élève", "AGENT", 1);
        Action action8 = new Action(8L, "ADD_SALL", "Ajouter/Modifier/Supprimer une salle de classe", "AGENT", 1);

        Profil profileParent = Profil
                .builder()
                .id(3L)
                .libelle("Parent")
                .actif(1)
                .build();


        Profil profileAdmin = new Profil(1L, "Administrateur", 1, Set.of(action1, action2, action3, action4, action5, action6), "ADMIN");
        Profil profileAgent = new Profil(2L, "Agent", 1, Set.of(action1, action2, action3, action4, action5, action6, action7, action8), "AGENT");

        actionRepository.saveAll(of(action1, action2, action3, action4, action5, action6, action7, action8));
        profilRepository.saveAll(of(profileAdmin, profileAgent, profileParent));

        String motDePasse = passwordEncoder.encode("root");
        Utilisateur tairou = Utilisateur.builder()
                .email("thirdiallo@gmail.com")
                .username("tairou")
                .telephone("779440310")
                .motdepasse(motDePasse)
                .profil(profileAdmin)
                .typeCompte("Administrateur")
                .actif(true)
                .build();
        TimeUnit.SECONDS.sleep(2);
        Utilisateur bertin = Utilisateur.builder()
                .email("bertin@gmail.com")
                .username("bertin")
                .telephone("784645316")
                .motdepasse(motDePasse)
                .typeCompte("Agent")
                .profil(profileAgent)
                .actif(true)
                .build();
        TimeUnit.SECONDS.sleep(2);

        utilisateurRepository.saveAll(of(tairou, bertin));

        */

	/*	var admin = Utilisateur.builder().username("Admin").telephone("779440310").email("admin@mail.com").motdepasse("password").profil(profileAdmin).build();
		System.out.println("Admin token: " + authenticationService.register(admin).getAccessToken());

		var manager = RegisterRequest.builder().firstname("User").prenom("User").email("manager@mail.com").password("password").profilCode("USER").build();
		System.out.println("Manager token: " + authenticationService.register(manager).getAccessToken());

*/


    }
}
