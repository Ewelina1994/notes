import {Component, OnInit} from '@angular/core';
import {ApiService} from '../../shared/api.service';
import {AlertService} from '../../_alert';
import {Feedback} from '../../model/feedback';
import {FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {

  modelNewFeedback: Feedback = {
    id: null,
    name: '',
    email: '',
    feedback: '',
    date: ''
  };
  formFeedback;
  options = {
    autoClose: true,
    keepAfterRouteChange: false
  };

  constructor(private apiService: ApiService, protected alertService: AlertService, private formBuilder: FormBuilder) {
    this.formFeedback = this.formBuilder.group({
      id: null,
      name: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      feedback: ['', [Validators.required, Validators.minLength(5)]],
      date: null
    }
  );
  }

  ngOnInit(): void {

  }

  sendFeedback(formFeedback: any): void {
    this.modelNewFeedback = {...this.modelNewFeedback, ...this.formFeedback.value}
    this.apiService.postFeedback(this.modelNewFeedback).subscribe(
      res => {
        this.saveFeedback();
        this.alertService.success('Message sent successfully', this.options);
        location.reload();

      },
      err => {
        console.error(err);
        this.alertService.error('The message could not be sent', this.options);
      }
    );
  }

  saveFeedback(): void {
    this.apiService.saveFeedback(this.modelNewFeedback).subscribe();
  }

}
