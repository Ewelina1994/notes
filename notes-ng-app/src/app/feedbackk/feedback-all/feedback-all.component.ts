import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {Feedback} from '../../model/feedback';
import {ApiService} from '../../shared/api.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';

@Component({
  selector: 'app-feedback-all',
  templateUrl: './feedback-all.component.html',
  styleUrls: ['./feedback-all.component.css']
})
export class FeedbackAllComponent implements AfterViewInit {

  feedbacks: Feedback[] = [];
  dataSource;
  @ViewChild(MatSort) sort: MatSort;

  columndefs: any[] = ['id', 'name', 'email', 'feedback', 'date'];

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
   // this.getAllFeedbacks();
    this.apiService.getAllFeedbacks().subscribe(
      res => {
        this.feedbacks = res;
        this.dataSource = new MatTableDataSource(this.feedbacks);

        setTimeout(() => {
          this.dataSource.sort = this.sort;
          console.log(this.dataSource.sort);
        });
      }
    );
  }

  getAllFeedbacks(): void {
    this.apiService.getAllFeedbacks().subscribe(
      res => {
        this.feedbacks = res;
        console.log(this.feedbacks);
        this.dataSource.sort = this.sort;
      }
    );
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
  }

  sortData($event) {
    this.dataSource.sort = this.sort;
    console.log(this.dataSource.sort);
  }
}
