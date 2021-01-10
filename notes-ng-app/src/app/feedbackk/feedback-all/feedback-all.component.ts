import { Component, OnInit } from '@angular/core';
import {Feedback} from '../../notes/model/feedback';
import {ApiService} from '../../shared/api.service';

@Component({
  selector: 'app-feedback-all',
  templateUrl: './feedback-all.component.html',
  styleUrls: ['./feedback-all.component.css']
})
export class FeedbackAllComponent implements OnInit {

  feedbacks: Feedback[] = [];
  numbers: number[] = [];
  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.getAllFeedbacks();
  }

  getAllFeedbacks(): void {
    this.apiService.getAllFeedbacks().subscribe(
      res => {
        this.feedbacks = res;
        for (let i = 1; i < this.feedbacks.length; i++) {
          this.numbers[i - 1] = 1;
        }
        console.log(this.numbers);
      }
    );
  }
}
