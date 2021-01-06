import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {BsModalRef} from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-new-notebook-modal',
  templateUrl: './new-notebook-modal.component.html',
  styleUrls: ['./new-notebook-modal.component.css']
})
export class NewNotebookModalComponent implements OnInit {

  @Output()
  nameNotebook = new EventEmitter<string>();
  constructor(public bsModalRef: BsModalRef) {
  }

  ngOnInit(): void {
  }

  addNotebok() {
    // @ts-ignore
    this.nameNotebook.emit(this.nameNotebook);
    this.bsModalRef.hide();
  }
}
