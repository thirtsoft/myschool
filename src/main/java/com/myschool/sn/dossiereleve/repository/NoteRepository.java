package com.myschool.sn.dossiereleve.repository;

import com.myschool.sn.dossiereleve.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Note findByActif(Long noteId);

    List<Note> findAllByActif();
}
