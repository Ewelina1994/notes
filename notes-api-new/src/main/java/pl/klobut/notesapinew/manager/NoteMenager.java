package pl.klobut.notesapinew.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.klobut.notesapinew.db.NoteRepository;
import pl.klobut.notesapinew.db.NotebookRepository;
import pl.klobut.notesapinew.model.Note;
import pl.klobut.notesapinew.model.Notebook;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class NoteMenager {
    private NoteRepository noteRepository;
    private NotebookRepository notebookRepository;

    @Autowired
    public NoteMenager(NoteRepository noteRepository, NotebookRepository notebookRepository) {
        this.noteRepository = noteRepository;
        this.notebookRepository = notebookRepository;
    }

    public Note findById(Long id) {

        return noteRepository.getOne(id);
    }

    public Iterable<Note> findAll() {
        return noteRepository.findAll();
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public void deleteById(Long id) {

        noteRepository.deleteById(id);
    }

    public Iterable<Note> findAllByNotebook(Notebook notebook) {
        return noteRepository.findAllByNotebook(notebook.getId());
    }

    public Iterable<Note> showRemoveNotes() {
        return noteRepository.showRemoveNotes();
    }

    @Transactional
    public void setRemoveNote(Long idNote) {
        noteRepository.setRemoveNote(idNote);
    }
}
