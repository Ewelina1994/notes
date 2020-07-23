import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Note} from "../model/note";

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})
export class NoteComponent implements OnInit {

  @Input()
  note:Note;
  @Output()
  noteUpdate: EventEmitter<Note>= new EventEmitter<Note>();
  @Output()
  noteDelete: EventEmitter<Note>= new EventEmitter<Note>();
  constructor() { }

  ngOnInit(): void {
    //console.log(this.note.lastModifieddOn);
  }

  updateNote() {
    this.noteUpdate.emit(this.note);
  }

  deleteNote() {
    this.noteDelete.emit(this.note);
  }
}
