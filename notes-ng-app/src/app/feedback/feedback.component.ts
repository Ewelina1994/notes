import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from '../shared/api.service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {
  model: FeedbackViewModel = {
    name: '',
    email: '',
    feedback: ''
  };

  isSendMail: boolean;

  constructor(private apiService: ApiService) {
    this.isSendMail = null;
  }

  ngOnInit(): void {
  }

  sendFeedback(): void {
    this.apiService.postFeedback(this.model).subscribe(
      res => {
        location.reload();
        this.isSendMail = true;
      },
      err => {
        console.error(err);
        this.isSendMail = false;
      }
    );
  }
}

export interface FeedbackViewModel {
  name: string;
  email: string;
  feedback: string;
}
