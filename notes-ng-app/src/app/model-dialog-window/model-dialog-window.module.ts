import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DeleteWindowComponent } from './delete-window/delete-window.component';
import {FormsModule} from '@angular/forms';
import {MatButtonModule, MatCommonModule, MatDialogModule, MatFormFieldModule, MatInputModule} from '@angular/material';



@NgModule({
  declarations: [DeleteWindowComponent],
  entryComponents: [DeleteWindowComponent],
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatCommonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
  ]
})
export class ModelDialogWindowModule { }
