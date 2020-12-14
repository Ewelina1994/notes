package pl.klobut.notesapinew.manager;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klobut.notesapinew.api.viewmodel.NotebookViewModel;
import pl.klobut.notesapinew.db.NotebookRepository;
import pl.klobut.notesapinew.model.Notebook;

import java.util.Collections;
import java.util.UUID;

@Service
public class NotebookMenager {
    private NotebookRepository notebookRepository;

    @Autowired
    public NotebookMenager(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }

    public Notebook findById(Long id) {
        return notebookRepository.getOne(id);
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
