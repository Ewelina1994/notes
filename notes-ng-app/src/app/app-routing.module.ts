import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {DeletedNotesComponent} from './deleted-notes/deleted-notes.component';
import {FeedbackComponent} from './feedbackk/feedbackSend/feedback.component';
import {FeedbackAllComponent} from './feedbackk/feedback-all/feedback-all.component';
import {LoginComponent} from './login/login.component';
import {NotesComponent} from './notes/notes.component';


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
    component: LoginComponent
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

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
