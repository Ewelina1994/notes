import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Notebook} from '../notes/model/notebook';
import {LocalNgModuleData} from '@angular/compiler-cli/src/ngtsc/scope';
import {Note} from '../notes/model/note';
import {not} from 'rxjs/internal-compatibility';
import {User} from '../notes/model/user';
import {Feedback} from '../notes/model/feedback';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  // private BASE_URL = window["cfgApiBaseUrl"]+"/api";
  private BASE_URL = 'http://localhost:8080/api';
  private ALL_NOTEBOOKS_URL = `${this.BASE_URL}\\notebooks\\all`;
  private SEND_FEDBACK_URL = `${this.BASE_URL}\\feedback`;
  private SAVE_FEEDBACK = `${this.BASE_URL}\\feedback\\add`;
  private ALL_FEEDBACKS = `${this.BASE_URL}\\feedback\\all`;
  private SAVE_UPDATE_NOTEBOOK = `${this.BASE_URL}\\notebooks`;
  private DELETE_NOTEBOK_URL = `${this.BASE_URL}\\notebooks\\`;
  private ALL_NOTES_URL = `${this.BASE_URL}\\notes\\all`;
  private NOTES_BY_NOTEBOOK_URL = `${this.BASE_URL}\\notes\\byNotebook\\`;
  private ALL_DELETED_NOTES = `${this.BASE_URL}\\notes\\deleted\\`;
  private SAVE_UPDATE_NOTE_URL = `${this.BASE_URL}\\notes`;
  private DELETE_NOTE_URL = `${this.BASE_URL}\\notes\\`;
  private LOGIN = `${this.BASE_URL}\\login\\check`;

  constructor(private http: HttpClient) {
  }

  getAllNotebooks(): Observable<Notebook[]> {
    return this.http.get<Notebook[]>(this.ALL_NOTEBOOKS_URL);
  }

  postFeedback(fedback: Feedback): Observable<any> {
    return this.http.post(this.SEND_FEDBACK_URL, fedback);
  }

  saveFeedback(fedback: Feedback): Observable<any> {
    return this.http.post(this.SAVE_FEEDBACK, fedback);
  }

  getAllFeedbacks(): Observable<Feedback[]>{
    return this.http.get<Feedback[]>(this.ALL_FEEDBACKS);
  }

  postNotebook(notebook: Notebook): Observable<Notebook> {
    return this.http.post<Notebook>(this.SAVE_UPDATE_NOTEBOOK, notebook);
  }

  // tslint:disable-next-line:ban-types
  deleteNotebook(id: String): Observable<any> {
    return this.http.delete(this.DELETE_NOTEBOK_URL + id);
  }

  getAllNotes(): Observable<Note[]> {
    return this.http.get<Note[]>(this.ALL_NOTES_URL);
  }

  getNotesByNootebok(notebook: Notebook): Observable<Note[]> {
    return this.http.get<Note[]>(this.NOTES_BY_NOTEBOOK_URL + notebook.id);
  }

  getAllDeletedNotes(): Observable<Note[]>{
    return this.http.get<Note[]>(this.ALL_DELETED_NOTES);
  }

  saveNote(note: Note): Observable<Note> {
    return this.http.post<Note>(this.SAVE_UPDATE_NOTE_URL, note);
  }

  // tslint:disable-next-line:ban-types
  deleteNote(id: String): Observable<any> {
    return this.http.delete(this.DELETE_NOTE_URL + id);
  }

  login(user: User): Observable<any> {
    return this.http.post(this.LOGIN, user);
  }

}
