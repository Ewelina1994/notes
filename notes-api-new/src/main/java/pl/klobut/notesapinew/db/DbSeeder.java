package pl.klobut.notesapinew.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import pl.klobut.notesapinew.model.Note;
import pl.klobut.notesapinew.model.Notebook;

import java.util.Date;

@Component
@ConditionalOnProperty(name = "noteit.db.recreate", havingValue = "true")
public class DbSeeder implements CommandLineRunner {
    private NotebookRepository notebookRepository;
    private NoteRepository noteRepository;

    public DbSeeder(NotebookRepository notebookRepository,
                    NoteRepository noteRepository) {
        this.notebookRepository = notebookRepository;
        this.noteRepository = noteRepository;
    }


    @Override
    public void run(String... args) {
        // Remove all existing entities
        this.notebookRepository.deleteAll();
        this.noteRepository.deleteAll();


        // Save a default notebook
        Notebook defaultNotebook = new Notebook("Monday");
        this.notebookRepository.save(defaultNotebook);

        Notebook quotesNotebook = new Notebook("Tuesday");
        this.notebookRepository.save(quotesNotebook);

        // Save the welcome note
        Note note = new Note("Cleaning", "Clean the bathroom\n", defaultNotebook);
        this.noteRepository.save(note);

        // Save a quote note
        Note quoteNote = new Note("Dinner", "Minced meat", quotesNotebook);
        this.noteRepository.save(quoteNote);

        System.out.println("Initialized database");
    }
}
