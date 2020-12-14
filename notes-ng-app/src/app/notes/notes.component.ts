import {Component, Inject, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Notebook} from "./model/notebook";
import {ApiService} from "../shared/api.service";
import {Note} from "./model/note";
import {MatDialog, MatDialogConfig} from '@angular/material';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';

interface DialogData {
  email: string;
}

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {

  closeResult = '';
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
  newNote: string;

  //input = document.getElementById("name");

  constructor(private apiService: ApiService, public modalService: NgbModal) {
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
        alert("An error has occured;")
      }
    );
  }

  getAllNotes() {
    this.apiService.getAllNotes().subscribe(
      res => {
        this.notes = res;
      },
      err => {
        alert("Error downloading the notes");
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
    let newN: Notebook = {
      name: this.newNotebook,
      id: null,
      nbOfNotes: 0
    };
    this.apiService.postNotebook(newN).subscribe(
      res => {
        newN.id = res.id;
        this.notebooks.push(newN);
      },
      err => {
        alert("An error has occurred while saving the notebook");
      }
    );
  }

  updateNotebook(updateNotebook: Notebook) {
    this.apiService.postNotebook(updateNotebook).subscribe(
      res => {

      },
      error => {
        alert("An error has occurence while saving the notebook");
      }
    );
  }

  deleteNotebook(notebook: Notebook) {
    if (confirm("Are you shure you want to delete this notebook?")) {
      this.apiService.deleteNotebook(notebook.id).subscribe(
        res => {
          let indexOfNotebook = this.notebooks.indexOf(notebook);
          this.notebooks.splice(indexOfNotebook, 1);
        },
        error => {
          alert("Eror in update note");
        }
      );
    }
  }

  deleteNote(note: Note) {
    console.log(note.id);
    if (confirm("Are you shure you want to delete this note?")) {
      this.apiService.deleteNote('1').subscribe(
        res => {
          let indexOfNote = this.notes.indexOf(note);
          this.notes.splice(indexOfNote, 1);
        },
        err => {
          alert("Error to delete Note");
        }
      );
    }

  }

  addNote(notebook: Notebook) {
    this.newNote2.notebook = notebook;
    // console.log(this.newNote2);
    this.apiService.saveNote(this.newNote2).subscribe(
      res => {
        this.newNote2.id = res.id;
        this.newNote2.lastModifieddOn = res.lastModifieddOn;
        this.notes.push(this.newNote2);
      },
      err => {
        alert("Error to add new note")
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
        alert("Error to read notes of notebook");
      }
    );
  }

  updateNote(note: Note) {
    this.apiService.saveNote(note).subscribe(
      res => {
        this
      }
    );
  }

  selectedAllNotes() {
    this.selectedNotebook = null;
    this.getAllNotes();
  }
}
