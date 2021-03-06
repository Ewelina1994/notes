package pl.klobut.notesapinew.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Notebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 20, min = 3, message = "This name is too small")
    @NotEmpty(message = "Please enter name")
    private String name;

    @OneToMany(mappedBy = "notebook", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Note> notes;


    protected Notebook() {
        this.notes = new ArrayList<>();
    }

    public Notebook(String name) {
        this();
        this.name = name;
    }

    public Notebook(Long id, String name) {
        this.id=id;
        this.name=name;
    }
//
//    public Notebook(Long id, String name) {
//        this();
//        this.name = name;
//    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }


    @Override
    public String toString() {
        return name + " notes: " + notes.size();
    }
}
