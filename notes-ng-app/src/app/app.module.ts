import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavigationComponent} from './navigation/navigation.component';
import {FeedbackComponent} from './feedback/feedback.component';
import {NotesComponent} from './notes/notes.component';
import {Routes, RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {NoteComponent} from './notes/note/note.component';
import {NoteTextFilterPipe} from './shared/note-text-filter.pipe';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {LoginComponent} from './login/login.component';
import {AlertModule} from './_alert';
import {CommonModule} from '@angular/common';
import {ModalModule} from 'ngx-bootstrap/modal';
import {NewNotebookModalComponent} from './model-dialog-window/new-notebook-modal/new-notebook-modal.component';
// @ts-ignore
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatCommonModule} from '@angular/material/core';
import { ConfirmDeleteComponent } from './model-dialog-window/confirm-delete-modal/confirm-delete/confirm-delete.component';
// @ts-ignore
import { MatDialogModule } from '@angular/material/dialog';
import { NewNoteModalComponent } from './model-dialog-window/new-note-modal/new-note-modal.component';

const appRoutes: Routes = [
  {
    path: 'notes',
    component: NotesComponent
  },
  {
    path: 'feedback',
    component: FeedbackComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: '',
    component: NotesComponent,
    pathMatch: 'full'
  }
];

// @ts-ignore
@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    FeedbackComponent,
    NotesComponent,
    NoteComponent,
    NoteTextFilterPipe,
    LoginComponent,
    NewNotebookModalComponent,
    ConfirmDeleteComponent,
    NewNoteModalComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes, {enableTracing: true}),
    MatDialogModule,
    NgbModule,
    AlertModule,
    MatProgressSpinnerModule,
    CommonModule,
    MatButtonModule,
    MatCommonModule,
    MatFormFieldModule,
    MatInputModule,
    ModalModule.forRoot(),
    ReactiveFormsModule
  ],
  entryComponents: [NewNotebookModalComponent, ConfirmDeleteComponent, NewNoteModalComponent],
  providers: [],
  bootstrap: [AppComponent],
  exports: [FormsModule, MatFormFieldModule, MatButtonModule, MatInputModule]
})
export class AppModule {
}
