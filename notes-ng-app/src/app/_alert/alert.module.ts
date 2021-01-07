import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AlertComponent } from './alert.component';
import { ControlMessagesFormComponent } from './validForms/control-messages-form/control-messages-form.component';

@NgModule({
    imports: [CommonModule],
    declarations: [AlertComponent, ControlMessagesFormComponent],
  exports: [AlertComponent, ControlMessagesFormComponent]
})
export class AlertModule { }
