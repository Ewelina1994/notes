import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavigationComponent} from './navigation/navigation.component';
import {FeedbackComponent} from './feedbackk/feedbackSend/feedback.component';
import {NotesComponent} from './notes/notes.component';
import {Routes, RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
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
import { FeedbackAllComponent } from './feedbackk/feedback-all/feedback-all.component';
import {MatTableModule} from '@angular/material/table';
import { DeletedNotesComponent } from './deleted-notes/deleted-notes.component';
import { LogoutComponent } from './logout/logout.component';
import {HttpInterceptorService} from './shared/http-interceptor.service';

const appRoutes: Routes = [
  {
    path: 'notesDelete',
    component: DeletedNotesComponent
  },
  {
    path: 'feedback',
    component: FeedbackComponent
  },
  {
    path: 'feedback/all',
    component: FeedbackAllComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'logout',
    component: LogoutComponent
  },
  {
    path: 'note',
    component: NotesComponent
  },
  {
    path: '',
    component: LoginComponent,
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
    FeedbackAllComponent,
    DeletedNotesComponent,
    LogoutComponent,
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
    ReactiveFormsModule,
    MatTableModule
  ],
  entryComponents: [NewNotebookModalComponent, ConfirmDeleteComponent, NewNoteModalComponent],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    }
    ],
  bootstrap: [AppComponent],
  exports: [FormsModule, MatFormFieldModule, MatButtonModule, MatInputModule]
})
export class AppModule {
}
