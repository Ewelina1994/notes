import {Component, EventEmitter, OnInit} from '@angular/core';
import {BsModalRef} from 'ngx-bootstrap/modal';
import {FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-new-notebook-modal',
  templateUrl: './new-notebook-modal.component.html',
  styleUrls: ['./new-notebook-modal.component.css']
})
export class NewNotebookModalComponent implements OnInit {
  itemNotebookForm;
  public event: EventEmitter<any> = new EventEmitter();
  constructor(private formBuilder: FormBuilder, public bsModalRef: BsModalRef) {
    this.itemNotebookForm = formBuilder.group({
      name: [' ', [Validators.required, Validators.minLength(3)]]
    });
  }

  ngOnInit(): void {
  }

  saveToList(itemform: any) {
    if (this.itemNotebookForm.dirty && this.itemNotebookForm.valid) {
      this.event.emit({data: itemform.value.name, res: 200});
      this.bsModalRef.hide();
    }
  }
}
