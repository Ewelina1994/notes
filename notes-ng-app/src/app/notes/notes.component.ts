import {Component, Inject, OnInit} from '@angular/core';
import {Notebook} from './model/notebook';
import {ApiService} from '../shared/api.service';
import {Note} from './model/note';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AlertService} from '../_alert';
import {MatDialog} from '@angular/material';
import {DeleteWindowComponent} from '../model-dialog-window/delete-window/delete-window.component';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {

   closeResult = '';
   notebooks: Notebook[] = [];
   notes: Note[];
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
  allDataFetched: boolean = false;
  options = {
    autoClose: true,
    keepAfterRouteChange: false
  };

  constructor(private apiService: ApiService, public modalService: NgbModal, protected alertService: AlertService, public dialog: MatDialog) {
    this.notes = [];
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
        this.allDataFetched = true;
      },
      err => {
        this.alertService.error('Error while retrieving data', this.options);
      }
    );
  }

  // input.addEventListener("keyup", function (event) {
  //   if(event.keyCode === 13){
  //     event.preventDefault();
  //     document.getElementById("saveNotebook").click();
  //   }
  // };

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  addNotebok() {
    console.log(this.newNotebook);
    // @ts-ignore
    const newN: Notebook = {
      name: this.newNotebook,
      id: null,
      nbOfNotes: 0
    };
    this.apiService.postNotebook(newN).subscribe(
      res => {
        newN.id = res.id;
        this.notebooks.push(newN);
       // this.isAddeNewNotebook = true;
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
    var areYouSure: boolean = false;
    this.openDialogDelete(notebook, areYouSure);
    if (confirm('Are you shure you want to delete this notebook?')) {
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
  }

  deleteNote(note: Note) {
    var areYouSure: boolean = false;
    this.openDialogDelete(note, areYouSure);
    if (confirm('Are you shure you want to delete this note?')) {
      this.apiService.deleteNote('1').subscribe(
        res => {
          const indexOfNote = this.notes.indexOf(note);
          this.notes.splice(indexOfNote, 1);
          this.alertService.success('Note was successfully deleted', this.options);
        },
        err => {
          this.alertService.error('Error to delete Note', this.options);
        }
      );
    }

  }

  addNote(notebook: Notebook) {
    this.newNote2.notebook = notebook;
    // console.log(this.newNote2);
    this.apiService.saveNote(this.newNote2).subscribe(
      res => {
        this.newNote2.lastModifieddOn = res.lastModifieddOn;
        this.notes.push(this.newNote2);
        console.log(this.newNote2);
        this.alertService.success('Sucess added note', this.options);
      },
      err => {
        console.log(err);
        this.alertService.error('Failed added note', this.options);
      }
    );
    console.log(this.newNote2.lastModifieddOn);
  }

  selectNotebook(notebook: Notebook) {
    this.selectedNotebook = notebook;
    this.apiService.getNotesByNootebok(notebook).subscribe(
      res => {
        this.notes = res;
        console.log(this.notes);
      },
      err => {
        this.alertService.error('Error to read notes of notebook', this.options);
      }
    );
  }

  updateNote(note: Note) {
    this.apiService.saveNote(note).subscribe(
      res => {
        this;
      }
    );
  }

  selectedAllNotes() {
    this.selectedNotebook = null;
    this.getAllNotes();
  }

  openDialogDelete(item: any, areYouSure: boolean): void {
    const dialogRef = this.dialog.open(DeleteWindowComponent, {
      width: '250px',
      data: {item, areYouSure}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      areYouSure = result;
    });
  }
}
