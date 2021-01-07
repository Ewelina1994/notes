import {Component, EventEmitter, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {BsModalRef} from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-new-note-modal',
  templateUrl: './new-note-modal.component.html',
  styleUrls: ['./new-note-modal.component.css']
})
export class NewNoteModalComponent implements OnInit {
  itemsNoteForm;
  public event: EventEmitter<any> = new EventEmitter();
  constructor(formBuilder: FormBuilder, public bsModalRef: BsModalRef) {
    this.itemsNoteForm = formBuilder.group({
      title: ['', [Validators.required, Validators.minLength(3)]],
      text: ['', [Validators.required, Validators.minLength(10)]]
    });
  }

  public saveToList(itemForm: any) {
    this.event.emit(itemForm.value);
    this.bsModalRef.hide();
  }

  ngOnInit(): void {
  }

}
