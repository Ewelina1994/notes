package pl.klobut.notesapinew.api.viewmodel;


import java.util.Date;

public class NoteViewModel {
    private Long id;


    private String title;


    private String text;


    private Long notebookId;

    private Date lastModifiedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Long getNotebookId() {
        return notebookId;
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public void setNotebookId(Long notebookId) {
        this.notebookId = notebookId;
    }
}
