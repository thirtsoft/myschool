package com.myschool.sn.referentiel.controller.api;

import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.referentiel.AnneeScolaireDTO;
import com.myschool.sn.utils.dtos.referentiel.BatimentDTO;
import com.myschool.sn.utils.dtos.referentiel.ClasseDTO;
import com.myschool.sn.utils.dtos.referentiel.MatiereDTO;
import com.myschool.sn.utils.dtos.referentiel.SalleDTO;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping(value = "/api/referentiel")
public interface ReferentielApi {

    @GetMapping(value = "/anneescolaire", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<AnneeScolaireDTO> getAnneeScolaires();

    @GetMapping(value = "/anneescolaire/{anneeScolaireId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    AnneeScolaireDTO getAnneeScolaire(@PathVariable @NotNull Long anneeScolaireId);

    @PostMapping(value = "/anneescolaire/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createAnneeScolaire(@RequestBody AnneeScolaireDTO anneeScolaireDTO);

    @PutMapping(value = "/anneescolaire/update/{anneeScolaireId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateAnneeScolaire(@PathVariable Long anneeScolaireId, @RequestBody AnneeScolaireDTO anneeScolaireDTO);

    @GetMapping(value = "/anneescolaire/by-code/{code}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    AnneeScolaireDTO getAnneeScolaireByCode(@PathVariable String code);

    @DeleteMapping(value = "/anneescolaire/delete/{eleveId}")
    ReponseMessageDTO deleteAnneeScolaire(@PathVariable Long anneeScolaireId);

    /************* Batiment *************************/
    @GetMapping(value = "/batiment", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<BatimentDTO> getBatiments();

    @GetMapping(value = "/batiment/{batimentId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    BatimentDTO getBatiment(@PathVariable @NotNull Long batimentId);

    @PostMapping(value = "/batiment/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createBatiment(@RequestBody BatimentDTO batimentDTO);

    @PutMapping(value = "/batiment/update/{batimentId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateBatiment(@PathVariable Long batimentId, @RequestBody BatimentDTO batimentDTO);

    @GetMapping(value = "/batiment/by-code/{code}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    BatimentDTO getBatimentByCode(@PathVariable String code);

    @DeleteMapping(value = "/batiment/delete/{batimentId}")
    ReponseMessageDTO deleteBatiment(@PathVariable Long batimentId);

    /************* Classe *************************/
    @GetMapping(value = "/classe", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<ClasseDTO> getClasses();

    @GetMapping(value = "/classe/{classeId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    ClasseDTO getClasse(@PathVariable @NotNull Long classeId);

    @PostMapping(value = "/classe/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createClasse(@RequestBody ClasseDTO classeDTO);

    @PutMapping(value = "/classe/update/{classeId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateClasse(@PathVariable Long classeId, @RequestBody ClasseDTO classeDTO);

    @GetMapping(value = "/classe/by-libelle/{libelle}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    ClasseDTO getClasseByLibelle(@PathVariable String libelle);
    @DeleteMapping(value = "/classe/delete/{classeId}")
    ReponseMessageDTO deleteClasse(@PathVariable Long classeId);

    /************* Matiere *************************/
    @GetMapping(value = "/classe", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<MatiereDTO> getMatieres();

    @GetMapping(value = "/matiere/{matiereId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    MatiereDTO getMatiere(@PathVariable @NotNull Long matiereId);

    @PostMapping(value = "/matiere/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createMatiere(@RequestBody MatiereDTO matiereDTO);

    @PutMapping(value = "/matiere/update/{matiereId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateMatiere(@PathVariable Long matiereId, @RequestBody MatiereDTO matiereDTO);

    @GetMapping(value = "/matiere/by-code/{code}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    MatiereDTO getMatiereByCode(@PathVariable String code);

    @GetMapping(value = "/matiere/by-libelle/{libelle}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    MatiereDTO getMatiereByLibelle(@PathVariable String libelle);

    @DeleteMapping(value = "/matiere/delete/{matiereId}")
    ReponseMessageDTO deleteMatiere(@PathVariable Long matiereId);

    /************* Salle *************************/
    @GetMapping(value = "/salle", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<SalleDTO> getSalles();

    @GetMapping(value = "/salle/by-batiment/{batimentId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<SalleDTO> getSallesByBatiment(@PathVariable Long batimentId);

    @GetMapping(value = "/salle/{salleId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    SalleDTO getSalle(@PathVariable @NotNull Long salleId);

    @PostMapping(value = "/salle/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createSalle(@RequestBody SalleDTO salleDTO);

    @PutMapping(value = "/salle/update/{matiereId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateSalle(@PathVariable Long salleId, @RequestBody SalleDTO salleDTO);

    @GetMapping(value = "/salle/by-code/{code}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    SalleDTO getSalleByCode(@PathVariable String code);

    @DeleteMapping(value = "/salle/delete/{salleId}")
    ReponseMessageDTO deleteSalle(@PathVariable Long salleId);




}
