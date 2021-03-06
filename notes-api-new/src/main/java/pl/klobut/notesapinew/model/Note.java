package pl.klobut.notesapinew.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Entity
@DynamicUpdate
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 20, min = 3, message = "This title is too small")
    @NotEmpty(message = "Please enter title")
    private String title;
    @NotBlank
    private String text;
    @ManyToOne
    private Notebook notebook;

    private Date lastModifiedOn;
    private boolean isRemove;

    protected Note() {
        this.lastModifiedOn = new Date();
    }

    public Note(String title, String text, Notebook notebook) {
        this();
        this.title = title;
        this.text = text;
        this.notebook = notebook;
    }

    public Note(Long id, String title, String text, Notebook notebook) {
        this(title, text, notebook);
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public String getNotebookId() {
        return this.notebook.getId().toString();
    }

    public Date getLastModifiedOn() {

        return lastModifiedOn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public boolean isRemove() {
        return isRemove;
    }

    public void setRemove(boolean remove) {
        isRemove = remove;
    }
}
