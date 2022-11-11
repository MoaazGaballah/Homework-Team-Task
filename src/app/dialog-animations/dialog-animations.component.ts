import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-dialog-animations',
  templateUrl: './dialog-animations.component.html',
  styleUrls: ['./dialog-animations.component.css']
})
export class DialogAnimationsComponent implements OnInit {

  @Output() okButtonSelected = new EventEmitter<string>();
  constructor(private dialogRef: MatDialogRef<DialogAnimationsComponent>) { }

  ngOnInit(): void {
  }

  closeDialog(){
    this.dialogRef.close();
  }

  okSelected(){
    this.okButtonSelected.emit('okSelected');
    this.closeDialog();
  }

}
