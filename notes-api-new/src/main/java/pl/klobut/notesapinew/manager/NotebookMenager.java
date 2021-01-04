package pl.klobut.notesapinew.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klobut.notesapinew.db.NotebookRepository;
import pl.klobut.notesapinew.model.Notebook;

import java.util.Optional;

@Service
public class NotebookMenager {
    private NotebookRepository notebookRepository;

    @Autowired
    public NotebookMenager(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }

    public Optional<Notebook> findById(Long id) {

        return notebookRepository.findById(id);
    }

    public Iterable<Notebook> findAll() {
        return notebookRepository.findAll();
    }

    public Notebook save(Notebook notebook) {
        return notebookRepository.save(notebook);
    }

    public void deleteById(Long id) {
        notebookRepository.deleteById(id);
    }
}
