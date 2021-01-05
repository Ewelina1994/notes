import {Component, OnInit, Output} from '@angular/core';
import {BsModalRef} from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-new-notebook-modal',
  templateUrl: './new-notebook-modal.component.html',
  styleUrls: ['./new-notebook-modal.component.css']
})
export class NewNotebookModalComponent implements OnInit {

  @Output()
  public nameNotebook: string;
  constructor() {
  }

  ngOnInit(): void {
  }

  addNotebok() {
  }
}
