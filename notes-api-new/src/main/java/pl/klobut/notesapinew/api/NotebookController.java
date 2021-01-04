package pl.klobut.notesapinew.api;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.klobut.notesapinew.api.viewmodel.NotebookViewModel;
import pl.klobut.notesapinew.manager.NotebookMenager;
import pl.klobut.notesapinew.model.Notebook;

import javax.xml.bind.ValidationException;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.DoubleToIntFunction;

/*
Requests can be tested using the built in HTTP Rest Client. Use the
examples found in 'noteit.http' file.
 */

@RestController
@RequestMapping("/api/notebooks")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotebookController {
    private NotebookMenager notebookMenager;
    // private Mapper mapper;

    public NotebookController(NotebookMenager notebookMenager) {
        this.notebookMenager = notebookMenager;
        //this.mapper = mapper;
    }

    @GetMapping("/all")
    public Iterable<Notebook> getAll() {
        return notebookMenager.findAll();
    }

    @PostMapping
    public Notebook addNotebook(@RequestBody Notebook notebook,
                                BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Blad dodania");
        }

        return notebookMenager.save(notebook);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.notebookMenager.deleteById(id);
    }
}
