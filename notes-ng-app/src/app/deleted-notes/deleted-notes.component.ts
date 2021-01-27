import { Component, OnInit } from '@angular/core';
import {Note} from '../model/note';
import {ApiService} from '../shared/api.service';
import {AlertService} from '../_alert';

@Component({
  selector: 'app-deleted-notes',
  templateUrl: './deleted-notes.component.html',
  styleUrls: ['./deleted-notes.component.css']
})
export class DeletedNotesComponent implements OnInit {
  serchText: any;
  deletedNotes: Note[];
  options = {
    autoClose: true,
    keepAfterRouteChange: false
  };

  constructor(private apiService: ApiService, private alertService: AlertService) { }

  ngOnInit(): void {
    this.apiService.getAllDeletedNotes().subscribe(
      res => {
        this.deletedNotes = res;
      },
      err => {
        this.alertService.error('Error while retrieving data', this.options);
      }
    );
  }

}
