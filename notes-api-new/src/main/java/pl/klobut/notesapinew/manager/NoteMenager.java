package pl.klobut.notesapinew.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    //    public Iterable<Note> findById(Long id){
//        return noteRepository.findAllById(Collections.singleton(id));
//    }
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
        return noteRepository.findAllByNotebook(notebook);
    }
}
