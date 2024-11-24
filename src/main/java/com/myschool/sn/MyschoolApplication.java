package com.myschool.sn;

import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.admin.repository.ActionRepository;
import com.myschool.sn.admin.repository.ProfilRepository;
import com.myschool.sn.admin.repository.UtilisateurRepository;
import com.myschool.sn.admin.service.AuthenticationService;
import com.myschool.sn.dossiereleve.entity.Eleve;
import com.myschool.sn.dossiereleve.entity.Note;
import com.myschool.sn.dossiereleve.repository.NoteRepository;
import com.myschool.sn.enseignant.entity.Conges;
import com.myschool.sn.enseignant.entity.Exercice;
import com.myschool.sn.enseignant.repository.CongesRepository;
import com.myschool.sn.enseignant.repository.ExerciceRepository;
import com.myschool.sn.referentiel.entity.Classe;
import com.myschool.sn.referentiel.entity.Matiere;
import com.myschool.sn.referentiel.entity.Semestre;
import lombok.RequiredArgsConstructor;
import org.joda.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class MyschoolApplication implements CommandLineRunner {

    private final ActionRepository actionRepository;

    private final ProfilRepository profilRepository;

    private final UtilisateurRepository utilisateurRepository;

    private final AuthenticationService authenticationService;

    private final PasswordEncoder passwordEncoder;

    private final NoteRepository noteRepository;

    private final CongesRepository congesRepository;

    private final ExerciceRepository exerciceRepository;

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


        Note n1 = Note.builder().eleve(Eleve.builder().id(1L).build())
                .semestre(Semestre.builder().id(1L).build())
                .matiere(Matiere.builder().id(1L).build()).actif(1)
                .note(19.5).type("Controle continue 01").build();
        Note n2 = Note.builder().eleve(Eleve.builder().id(1L).build())
                .semestre(Semestre.builder().id(1L).build())
                .matiere(Matiere.builder().id(1L).build()).actif(1)
                .note(15.5).type("Controle continue 02").build();
        Note n3 = Note.builder().eleve(Eleve.builder().id(3L).build()).actif(1)
                .semestre(Semestre.builder().id(2L).build())
                .matiere(Matiere.builder().id(2L).build())
                .note(12.5).type("Controle continue 01").build();
        Note n4 = Note.builder().eleve(Eleve.builder().id(3L).build()).actif(1)
                .semestre(Semestre.builder().id(3L).build())
                .matiere(Matiere.builder().id(3L).build())
                .note(16.5).type("Controle continue 01").build();
        Conges c1 = Conges.builder().actif(1).enseignant(Utilisateur.builder().id(1L).build())
                .objet("Conge 01").motif("Motif o1").etat(1).dateDebut(LocalDate.now())
                .dateFin(LocalDate.now()).build();
        Exercice e1 = Exercice.builder().actif(1).classe(Classe.builder().id(1L).build())
                .description("Description").libelle("Exercice 01").build();

        noteRepository.saveAll(List.of(n1, n1, n3, n4));
        congesRepository.save(c1);
        exerciceRepository.save(e1);
*/

    }
}
