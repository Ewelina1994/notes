import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Notebook} from "../notes/model/notebook";
import {FeedbackViewModel} from "../feedback/feedback.component";
import {LocalNgModuleData} from "@angular/compiler-cli/src/ngtsc/scope";
import {Note} from "../notes/model/note";
import {not} from "rxjs/internal-compatibility";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  // private BASE_URL = window["cfgApiBaseUrl"]+"/api";
  private BASE_URL = "http://localhost:8082/api";
  private ALL_NOTEBOOKS_URL = `${this.BASE_URL}\\notebooks\\all`;
  private SEND_FEDBACK_URL = `${this.BASE_URL}\\feedback`;
  private SAVE_UPDATE_NOTEBOOK=`${this.BASE_URL}\\notebooks`;
  private DELETE_NOTEBOK_URL=`${this.BASE_URL}\\notebooks\\`;
  private ALL_NOTES_URL = `${this.BASE_URL}\\notes\\all`;
  private NOTES_BY_NOTEBOOK_URL = `${this.BASE_URL}\\notes\\byNotebook\\`;
  private SAVE_UPDATE_NOTE_URL = `${this.BASE_URL}\\notes`;
  private DELETE_NOTE_URL=`${this.BASE_URL}\\notes\\`;
  constructor(private http: HttpClient) {
  }

  getAllNotebooks(): Observable<Notebook[]>{
    return this.http.get<Notebook[]>(this.ALL_NOTEBOOKS_URL);
  }

  postFeedback(fedback: FeedbackViewModel): Observable<any>{
      return this.http.post(this.SEND_FEDBACK_URL, fedback);
  }

  postNotebook(notebook: Notebook): Observable<Notebook>{
    return this.http.post<Notebook>(this.SAVE_UPDATE_NOTEBOOK, notebook);
  }

  deleteNotebook(id:String): Observable<any>{
    return this.http.delete(this.DELETE_NOTEBOK_URL + id);
  }

  getAllNotes():Observable<Note[]>{
    return  this.http.get<Note[]>(this.ALL_NOTES_URL);
  }

  getNotesByNootebok(notebook:Notebook):Observable<Note[]>{
    return this.http.get<Note[]>(this.NOTES_BY_NOTEBOOK_URL+notebook.id);
  }

  saveNote(note:Note):Observable<Note>{
    return this.http.post<Note>(this.SAVE_UPDATE_NOTE_URL, note);
  }

  deleteNote(id:String): Observable<any> {
    return this.http.delete(this.DELETE_NOTE_URL + id);
  }

}
