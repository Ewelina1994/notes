import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {ValidationFormService} from './validation-form.service';

@Component({
  selector: 'app-control-messages-form',
  templateUrl: './control-messages-form.component.html',
  styleUrls: []
})
export class ControlMessagesFormComponent implements OnInit {
  @Input()
  control: FormControl;

  constructor() {}

  ngOnInit(): void {
  }

  get errorMessageInput() {
    for (const propertyName in this.control.errors) {
      if (
        this.control.errors.hasOwnProperty(propertyName) &&
        this.control.touched
      ) {
        return ValidationFormService.getValidatorErrorMessage(
          propertyName,
          this.control.errors[propertyName]
        );
      }
    }

    return null;
  }}
