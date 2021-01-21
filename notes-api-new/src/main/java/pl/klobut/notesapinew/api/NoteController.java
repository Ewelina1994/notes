package pl.klobut.notesapinew.api;

import javassist.NotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import pl.klobut.notesapinew.Mapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.klobut.notesapinew.api.viewmodel.NoteViewModel;
import pl.klobut.notesapinew.db.NoteRepository;
import pl.klobut.notesapinew.db.NotebookRepository;
import pl.klobut.notesapinew.manager.NoteMenager;
import pl.klobut.notesapinew.manager.NotebookMenager;
import pl.klobut.notesapinew.model.Note;
import pl.klobut.notesapinew.model.Notebook;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NoteController {
    private NoteMenager noteMenager;
    private NotebookMenager notebookMenager;
    private Mapper mapper;

    public NoteController(NoteMenager noteMenager, NotebookMenager notebookMenager, Mapper mapper) {
        this.noteMenager = noteMenager;
        this.notebookMenager = notebookMenager;
        this.mapper = mapper;
    }

    @GetMapping("/hej")
    public String hej() {
        return "hej";
    }

    @GetMapping()
    public Iterable<Note> getAll() {
        for (Note n : noteMenager.findAll()
        ) {
            System.out.println(n.getLastModifiedOn());
        }
        return noteMenager.findAll();
    }

    @GetMapping("/byId/{id}")
    public Note getById(@PathVariable Long id) {
        Note note = this.noteMenager.findById(id);

        if (note == null) {
            throw new EntityNotFoundException();
        }

        return note;
    }

    //do poprawy ta metoda
    @GetMapping("/byNotebook/{notebookId}")
    public List<Note> byNotebook(@PathVariable Long notebookId) {
        List<Note> notes = new ArrayList<>();

        Optional<Notebook> notebook = this.notebookMenager.findById(notebookId);
        if (notebook != null) {
            notes = (List<Note>) this.noteMenager.findAllByNotebook(notebook.get());
        }
        // map to note view model

        return notes;
    }

    @PostMapping
    public Note save(@RequestBody Note noteCreateViewModel, BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Blad dodania note");
        }

        if (noteCreateViewModel.getNotebookId() != null) {
            // save note instance to db
            this.noteMenager.save(noteCreateViewModel);
        }
        return noteCreateViewModel;
    }

    @PutMapping("/{id}")
    public void delete(@PathVariable Long id) throws NotFoundException {
        try{
            this.noteMenager.setRemoveNote(id);
        }
        catch (EmptyResultDataAccessException ex) {
            // either do nothing to return a 204, or
            throw new NotFoundException("No Found book id");
        }
    }

    @GetMapping("/deleted")
    public Iterable<Note> showRemoveNotes() {
        return noteMenager.showRemoveNotes();
    }
}
