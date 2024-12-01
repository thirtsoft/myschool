package com.myschool.sn.dossiereleve.repository;

import com.myschool.sn.dossiereleve.entity.Eleve;
import com.myschool.sn.dossiereleve.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Note findByActif(Long noteId);

    @Query("Select DISTINCT el from  Note el where el.actif=1 order by id desc")
    List<Note> findAllNotes();

    @Query("Select DISTINCT el from  Note el where el.actif=1 and el.eleve.id=:eleveId order by id")
    List<Note> findAllNotesByEleve(@Param("eleveId") Long eleveId);

    @Query("Select DISTINCT el from  Note el where el.actif=1 and el.matiere.id=:matiereId order by id")
    List<Note> findAllNotesByMatiere(@Param("matiereId") Long matiereId);

    @Query("Select DISTINCT el from  Note el where el.actif=1 and el.semestre.id=:semestreId order by id")
    List<Note> findAllNotesBySemestre(@Param("semestreId") Long semestreId);
}
