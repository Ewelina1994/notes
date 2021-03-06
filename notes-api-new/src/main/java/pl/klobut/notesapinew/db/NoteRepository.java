package pl.klobut.notesapinew.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.klobut.notesapinew.model.Note;
import pl.klobut.notesapinew.model.Notebook;

import java.util.List;
import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
   // Iterable<Note> findByNotebook();
    @Query(value = "SELECT * FROM Note WHERE notebook_id= ?1", nativeQuery = true)
    Iterable<Note> findAllByNotebook(Long notebookId);

    @Query(value = "SELECT * FROM Note WHERE is_remove = true", nativeQuery = true)
    Iterable<Note> showRemoveNotes();

   @Modifying
    @Query(value = "UPDATE Note SET is_remove = true WHERE id = ?1", nativeQuery = true)
    void setRemoveNote(Long noteId);
}
