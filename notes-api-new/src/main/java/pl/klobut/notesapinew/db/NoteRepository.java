package pl.klobut.notesapinew.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.klobut.notesapinew.model.Note;
import pl.klobut.notesapinew.model.Notebook;

import java.util.List;
import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    Iterable<Note> findAllByNotebook(Notebook notebook);
}
