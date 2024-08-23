package com.myschool.sn.referentiel.controller.api;

import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.referentiel.AnneeScolaireDTO;
import com.myschool.sn.utils.dtos.referentiel.BatimentDTO;
import com.myschool.sn.utils.dtos.referentiel.CategoryMenuDTO;
import com.myschool.sn.utils.dtos.referentiel.ClasseDTO;
import com.myschool.sn.utils.dtos.referentiel.EvenementDTO;
import com.myschool.sn.utils.dtos.referentiel.MatiereDTO;
import com.myschool.sn.utils.dtos.referentiel.MenuDTO;
import com.myschool.sn.utils.dtos.referentiel.NiveauEducationDTO;
import com.myschool.sn.utils.dtos.referentiel.SalleDTO;
import com.myschool.sn.utils.dtos.referentiel.SemestreDTO;
import com.myschool.sn.utils.dtos.referentiel.TypeDocumentDTO;
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

    /************* Evenement *************************/
    @GetMapping(value = "/evenement", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<EvenementDTO> getEvenements();

    @GetMapping(value = "/evenement/{evenementId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    EvenementDTO getEvenement(@PathVariable @NotNull Long evenementId);

    @PostMapping(value = "/evenement/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createEvenement(@RequestBody EvenementDTO evenementDTO);

    @PutMapping(value = "/evenement/update/{evenementId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateEvenement(@PathVariable Long evenementId, @RequestBody EvenementDTO evenementDTO);

    @GetMapping(value = "/evenement/by-libelle/{libelle}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    EvenementDTO getEvenementByLibelle(@PathVariable String libelle);

    @DeleteMapping(value = "/evenement/delete/{evenementId}")
    ReponseMessageDTO deleteEvenement(@PathVariable Long evenementId);

    /************* Semestre *************************/
    @GetMapping(value = "/semestre", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<SemestreDTO> getSemestres();

    @GetMapping(value = "/semestre/{semestreId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    SemestreDTO getSemestre(@PathVariable @NotNull Long semestreId);

    @PostMapping(value = "/semestre/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createSemestre(@RequestBody SemestreDTO semestreDTO);

    @PutMapping(value = "/semestre/update/{semestreId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateSemestre(@PathVariable Long semestreId, @RequestBody SemestreDTO semestreDTO);

    @GetMapping(value = "/semestre/by-code/{code}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    SemestreDTO getSemestreByCode(@PathVariable String code);

    @GetMapping(value = "/semestre/by-libelle/{libelle}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    SemestreDTO getSemestreByLibelle(@PathVariable String libelle);

    @DeleteMapping(value = "/semestre/delete/{semestreId}")
    ReponseMessageDTO deleteSemestre(@PathVariable Long semestreId);

    /************* TypeDocument *************************/
    @GetMapping(value = "/typeDocument", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<TypeDocumentDTO> getTypeDocuments();

    @GetMapping(value = "/typeDocument/{typeDocumentId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    TypeDocumentDTO getTypeDocument(@PathVariable @NotNull Long typeDocumentId);

    @PostMapping(value = "/typeDocument/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createTypeDocument(@RequestBody TypeDocumentDTO typeDocumentDTO);

    @PutMapping(value = "/typeDocument/update/{typeDocumentId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateTypeDocument(@PathVariable Long typeDocumentId, @RequestBody TypeDocumentDTO typeDocumentDTO);

    @GetMapping(value = "/typeDocument/by-code/{code}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    SemestreDTO getTypeDocumentByCode(@PathVariable String code);

    @GetMapping(value = "/typeDocument/by-libelle/{libelle}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    SemestreDTO getTypeDocumentByLibelle(@PathVariable String libelle);

    @DeleteMapping(value = "/typeDocument/delete/{typeDocumentId}")
    ReponseMessageDTO deleteTypeDocument(@PathVariable Long typeDocumentId);

    /************* NiveauEducation *************************/
    @GetMapping(value = "/niveauEducation", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<NiveauEducationDTO> getNiveauEducations();

    @GetMapping(value = "/niveauEducation/{niveauEducationId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    NiveauEducationDTO getNiveauEducation(@PathVariable @NotNull Long niveauEducationId);

    @PostMapping(value = "/niveauEducation/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createNiveauEducation(@RequestBody NiveauEducationDTO niveauEducationDTO);

    @PutMapping(value = "/niveauEducation/update/{niveauEducationId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateNiveauEducation(@PathVariable Long niveauEducationId, @RequestBody NiveauEducationDTO niveauEducationDTO);

    @GetMapping(value = "/niveauEducation/by-code/{code}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    NiveauEducationDTO getNiveauEducationByCode(@PathVariable String code);

    @GetMapping(value = "/niveauEducation/by-libelle/{libelle}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    NiveauEducationDTO getNiveauEducationByLibelle(@PathVariable String libelle);

    @DeleteMapping(value = "/niveauEducation/delete/{niveauEducationId}")
    ReponseMessageDTO deleteNiveauEducation(@PathVariable Long niveauEducationId);

    /*************   CategoryMenu  *************************/
    @GetMapping(value = "/categoryMenu", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<CategoryMenuDTO> getCategoryMenus();

    @GetMapping(value = "/categoryMenu/{categoryMenuId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    CategoryMenuDTO getCategoryMenu(@PathVariable @NotNull Long categoryMenuId);

    @PostMapping(value = "/categoryMenu/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createCategoryMenu(@RequestBody CategoryMenuDTO categoryMenuDTO);

    @PutMapping(value = "/categoryMenu/update/{categoryMenuId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateCategoryMenu(@PathVariable Long categoryMenuId, @RequestBody CategoryMenuDTO categoryMenuDTO);

    @GetMapping(value = "/categoryMenu/by-libelle/{libelle}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    CategoryMenuDTO getCategoryMenuByLibelle(@PathVariable String libelle);

    @DeleteMapping(value = "/categoryMenu/delete/{categoryMenuId}")
    ReponseMessageDTO deleteCategoryMenu(@PathVariable Long categoryMenuId);

    /*************      Menu  *************************/
    @GetMapping(value = "/menu", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<MenuDTO> getMenus();

    @GetMapping(value = "/menu/by-category-menu/{menuId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<MenuDTO> findMenusByCategoryMenu(@PathVariable Long catMenuId);

    @GetMapping(value = "/menu/{menuId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    MenuDTO getMenu(@PathVariable @NotNull Long menuId);

    @PostMapping(value = "/menu/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ReponseMessageDTO createMenu(@RequestBody MenuDTO menuDTO);

    @PutMapping(value = "/menu/update/{menuId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReponseMessageDTO updateMenu(@PathVariable Long menuId, @RequestBody MenuDTO menuDTO);

    @GetMapping(value = "/menu/by-libelle/{libelle}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    MenuDTO getMenuByLibelle(@PathVariable String libelle);

    @DeleteMapping(value = "/menu/delete/{menuId}")
    ReponseMessageDTO deleteMenu(@PathVariable Long menuId);


}