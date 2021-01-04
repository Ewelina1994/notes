import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-delete-window',
  templateUrl: './delete-window.component.html',
  styleUrls: ['./delete-window.component.css']
})
export class DeleteWindowComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DeleteWindowComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {}
  onCancel(): void {
    this.dialogRef.close();
  }

  confirm() {
    this.data.areYouSure = true;
  }
}
