import {Component, OnInit, ViewChild} from '@angular/core';
import {ThemePalette} from "@angular/material/core";
import * as moment from 'moment';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {CableService} from "../../services/cable.service";
import {NgxMatDatetimePicker} from "@angular-material-components/datetime-picker";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

interface StatusMap {
  value: string;
  name: string;
}

@Component({
  selector: 'app-cable-edit',
  templateUrl: './cable-edit.component.html',
  styleUrls: ['./cable-edit.component.css']
})
export class CableEditComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    private cableService: CableService,
    private router: Router,
    private route: ActivatedRoute,
    private _snackBar: MatSnackBar) {}

  @ViewChild('picker') picker!: NgxMatDatetimePicker<moment.Moment>;

  public date: moment.Moment = moment(new Date(2021, 9, 4, 5, 6, 7));
  public disabled = false;
  public showSpinners = true;
  public showSeconds = false;
  public touchUi = false;
  public enableMeridian = false;
  public minDate: moment.Moment = moment();
  public maxDate: moment.Moment = moment();
  public stepHour = 1;
  public stepMinute = 1;
  public stepSecond = 1;
  public color: ThemePalette = 'primary';
  public dateControl = new FormControl(new Date(2021, 9, 4, 5, 6, 7));
  disableSelect = new FormControl(false);
  statuses: StatusMap[] = [
    {value: 'Sıfır', name: 'NEW'},
    {value: '2. El', name: 'USED'},
    {value: 'Tükendi', name: 'FINISHED'},
  ];
  selectedValue: string = '';
  durationInSeconds = 5;

  title = 'Add/Edit Cable Details';
  cableForm: FormGroup = this.createForm(
    '',
    '',
    {name: '', value: ''},
    undefined,
    undefined,
    undefined,
    undefined,
    new Date(),
    [{id: null, file: null}]
  );

  public images?: FileList;

  createForm(
    cableName: string,
    cableType: string,
    statusMap : {name: '', value: ''},
    id ?: number,
    length ?: number,
    price ?: number,
    thickness ?: number,
    activatedDate ?: Date,
    images ?:[{id: null, file: null}]
    ) {
    this.cableForm = this.fb.group({
      cableName: [cableName, Validators.required],
      cableType: [cableType, Validators.required],
      statusMap: [statusMap, Validators.required],
      id: [id, !Validators.required],
      length: [length, Validators.required],
      price: [price, Validators.required],
      thickness: [thickness, Validators.required],
      activatedDate: [activatedDate, Validators.required],
      images: [images, !Validators.required]
    });
    return this.cableForm;
  }

  onSave() {
    if (this.cableForm.valid)
      this.cableService.addCable(this.cableForm.value)
        .subscribe(() => {
          this.router.navigate(['/cables']);
          this.openSnackBar('Saved successfully!', 'blue-snackbar');
        })
    else
      this.openSnackBar('Please fill all required fields!', 'red-snackbar');
  }

  openSnackBar(message: string, cssClass: string) {
    this._snackBar.open(message, undefined, {
      duration: this.durationInSeconds * 1000,
      panelClass: [cssClass]
    });
  }

  ngOnInit() {
    this.route.queryParams.subscribe((params: Params) => {
      if (params['cable'] !== undefined) {
        let cableWillBeEdited = JSON.parse(params['cable'])
        this.cableForm = this.createForm(
          cableWillBeEdited.cableName,
          cableWillBeEdited.cableType,
          cableWillBeEdited.statusMap,
          cableWillBeEdited.id,
          cableWillBeEdited.length,
          cableWillBeEdited.price,
          cableWillBeEdited.thickness,
          cableWillBeEdited.activatedDate,
          cableWillBeEdited.images
        )
      }
    })
  }

  compareTwoObjects(option1: {name: '', value:''} , option2: {name: '', value:''}){
    return option1.name === option2.name;
  }
}


