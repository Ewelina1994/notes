import {Component, Inject, OnInit} from '@angular/core';
import {Notebook} from './model/notebook';
import {ApiService} from '../shared/api.service';
import {Note} from './model/note';
import {AlertService} from '../_alert';
import {BsModalService} from 'ngx-bootstrap/modal';
import {NewNotebookModalComponent} from '../model-dialog-window/new-notebook-modal/new-notebook-modal.component';
import {ConfirmDeleteService} from '../model-dialog-window/confirm-delete-modal/confirm-delete.service';
import {NewNoteModalComponent} from '../model-dialog-window/new-note-modal/new-note-modal.component';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {

   notebooks: Notebook[] = [];
   notes: Note[] = [];
   selectedNotebook: Notebook;
  newNote2: Note = {
    id: null,
    lastModifieddOn: null,
    notebook: null,
    text: null,
    title: null,
  };
   newNotebook: string;
   serchText: string;
  allDataFetched = false;
  options = {
    autoClose: true,
    keepAfterRouteChange: false
  };

  constructor(private apiService: ApiService, protected alertService: AlertService, private confirmModal: ConfirmDeleteService, private modalService: BsModalService) {
  }

  ngOnInit(): void {
    this.getAllNotebooks();
    this.getAllNotes();
  }

  public getAllNotebooks() {
    this.apiService.getAllNotebooks().subscribe(
      res => {
        this.notebooks = res;
      },
      err => {
        this.alertService.error('Error while retrieving data', this.options);
      }
    );
  }

  getAllNotes() {
    this.apiService.getAllNotes().subscribe(
      res => {
        this.notes = res;
        console.log(this.notes);
        this.allDataFetched = true;
      },
      err => {
        this.alertService.error('Error while retrieving data', this.options);
      }
    );
  }

  addNotebok(nameNotebook: string) {
    const newN: Notebook = {
      name: nameNotebook,
      id: null,
      nbOfNotes: 0
    };
    this.apiService.postNotebook(newN).subscribe(
      res => {
        newN.id = res.id;
        this.notebooks.push(newN);
        this.alertService.success('Sucess added notebook', this.options);
      },
      err => {
        this.alertService.error('Failed added notebook', this.options);
        console.log(err);
      }
    );
    this.newNotebook = '';
  }

  updateNotebook(updateNotebook: Notebook) {
    this.apiService.postNotebook(updateNotebook).subscribe(
      res => {

      },
      error => {
        this.alertService.error('Error while changing data', this.options);
      }
    );
  }

  deleteNotebook(notebook: Notebook) {
    this.confirmModal.confirm('Please confirm', 'Do you really want to remove ' + notebook.name + '?')
      .then((confirm) => {
        if (confirm) {
          this.apiService.deleteNotebook(notebook.id).subscribe(
            res => {
              const indexOfNotebook = this.notebooks.indexOf(notebook);
              this.notebooks.splice(indexOfNotebook, 1);
              this.alertService.success('Notebook was successfully deleted', this.options);
            },
            error => {
              this.alertService.error('Error while delete notebook', this.options);
            }
          );
        }
      })
      .catch(() => console.log('User dismissed the dialog (e.g., by using ESC, clicking the cross icon, or clicking outside the dialog)'));
  }

  deleteNote(note: Note) {
    this.confirmModal.confirm('Please confirm', 'Do you really want to remove ' + note.title + '?')
      .then((confirm) => {
        if (confirm) {
          this.apiService.deleteNote(note.id).subscribe(
            res => {
              const indexOfNote = this.notes.indexOf(note);
              this.notes.splice(indexOfNote, 1);
              console.log(this.notes);
              this.alertService.success('Note was successfully deleted', this.options);
            },
            err => {
              this.alertService.error('Error to delete Note', this.options);
            }
          );
        }
      })
      .catch(() => console.log('User dismissed the dialog (e.g., by using ESC, clicking the cross icon, or clicking outside the dialog)'));
  }

  addNote() {
    this.apiService.saveNote(this.newNote2).subscribe(
      res => {
        this.newNote2.id = res.id;
        this.newNote2.lastModifieddOn = new Date();
        this.notes.push(this.newNote2);
        console.log(this.newNote2);
        console.log(res.lastModifieddOn);
        this.alertService.success('Sucess added note', this.options);
      },
      err => {
        console.log(err);
        this.alertService.error('Failed added note', this.options);
      }
    );
  }

  selectNotebook(notebook: Notebook) {
    this.selectedNotebook = notebook;
    this.apiService.getNotesByNootebok(notebook).subscribe(
      res => {
        this.notes = res;
      },
      err => {
        this.alertService.error('Error to read notes of notebook', this.options);
      }
    );
  }

  updateNote(note: Note) {
    this.apiService.saveNote(note).subscribe(
      res => {
      }
    );
  }

  selectedAllNotes() {
    this.selectedNotebook = null;
    this.getAllNotes();
  }

  openModalNewNotebook() {
    let nameNotebook: string;
    const modalRef = this.modalService.show(NewNotebookModalComponent);
    modalRef.content.event.subscribe(res => {
      if (!res.isNotNullOrUndefined) {
        nameNotebook = res.data;
        this.addNotebok(nameNotebook);
      }
    });
  }

  openModalNewNote(notebook: Notebook) {
    this.newNote2.notebook = notebook;
    const modalRef = this.modalService.show(NewNoteModalComponent);
    modalRef.content.event.subscribe(res => {
      console.log(res);
      if (!res.isNotNullOrUndefined) {
        this.newNote2.title = res.title;
        this.newNote2.text = res.text;
        this.addNote();
      }
    });
  }

}
