package pl.klobut.notesapinew;

import org.springframework.stereotype.Component;
import pl.klobut.notesapinew.api.viewmodel.NoteViewModel;
import pl.klobut.notesapinew.api.viewmodel.NotebookViewModel;
import pl.klobut.notesapinew.db.NotebookRepository;
import pl.klobut.notesapinew.model.Note;
import pl.klobut.notesapinew.model.Notebook;


import java.util.UUID;

/**
 * Component that handles all mappings in this project
 * - entity to view model
 * - view model to entity
 * <p>
 * All mappings are handled here, but in production code this is not the
 * best approach. You can take a look at ModelMapper project or at least split mapping classes
 * across many files.
 */

@Component
public class Mapper {
    private NotebookRepository notebookRepository;

    public Mapper(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }

    public NoteViewModel convertToNoteViewModel(Note entity) {
        NoteViewModel viewModel = new NoteViewModel();
        viewModel.setTitle(entity.getTitle());
        viewModel.setId(entity.getId());
        viewModel.setLastModifiedOn(entity.getLastModifiedOn());
        viewModel.setText(entity.getText());
        viewModel.setNotebookId(entity.getNotebook().getId());

        return viewModel;
    }

    public Note convertToNoteEntity(NoteViewModel viewModel) {
        NotebookRepository notebook = (NotebookRepository) this.notebookRepository.findById(viewModel.getNotebookId()).get();
        Note entity = new Note(viewModel.getId(), viewModel.getTitle(), viewModel.getText(), (Notebook) notebook);

        return entity;
    }

    public NotebookViewModel convertToNotebookViewModel(Notebook entity) {
        NotebookViewModel viewModel = new NotebookViewModel();
        viewModel.setId(entity.getId());
        viewModel.setName(entity.getName());
        viewModel.setNbNotes(entity.getNotes().size());

        return viewModel;
    }

    public Notebook convertToNotebookEntity(NotebookViewModel viewModel) {
        Notebook entity = new Notebook(viewModel.getId(), viewModel.getName());

        return entity;
    }
}
