import {Component, OnInit, ViewChild} from '@angular/core';
import {ThemePalette} from "@angular/material/core";
import * as moment from 'moment';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {CableService} from "../cable.service";
import {NgxMatDatetimePicker} from "@angular-material-components/datetime-picker";
import {ActivatedRoute, Params, Router} from "@angular/router";

interface StatusMap {
  value: string;
  name: string;
}

@Component({
  selector: 'app-cable-edit',
  templateUrl: './cable-edit.component.html',
  styleUrls: ['./cable-edit.component.css']
})
export class CableEditComponent implements OnInit{

  constructor(
    private fb: FormBuilder,
    private cableService: CableService,
    private router: Router,
    private route: ActivatedRoute) {

  }

  @ViewChild('picker') picker!: NgxMatDatetimePicker<moment.Moment>;

  public date: moment.Moment = moment(new Date(2021,9,4,5,6,7));
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
  public dateControl = new FormControl(new Date(2021,9,4,5,6,7));
  disableSelect = new FormControl(false);
  statuses: StatusMap[] = [
    {value: 'Sıfır', name: 'NEW'},
    {value: '2. El', name: 'USED'},
    {value: 'Tükendi', name: 'FINISHED'},
  ];
  selectedValue: string = '';

  title = 'Add/Edit Cable Details';
  cableForm: FormGroup = this.createForm();

  public images?: FileList;

  createForm() {
    this.cableForm = this.fb.group({
      cableName: ['', Validators.required ],
      cableType: ['', Validators.required ],
      length: ['', Validators.required ],
      price: ['', Validators.required ],
      thickness: ['', Validators.required ],
      statusMap: [{name: '', value: ''}, Validators.required ],
      activatedDate: ['', Validators.required ],
      images:[[{id: null, file: null}], Validators.required],
      status: ['', Validators.required],
      cableStatusValue: ['', Validators.required]
    });
    return this.cableForm;
  }

  onSave(){
    if (this.cableForm.valid)
    this.cableService.addCable(this.cableForm.value)
      .subscribe( () => {
        this.router.navigate(['/cables']);
      })
    else
      console.log('not valid');
  }

  ngOnInit(){
    this.route.queryParams.subscribe( (params: Params) => {
      let test = {};
      // test['statusMap'] = {
      //   value: test['cableStatusValue'],
      //   name: test['status']
      // }
      // console.log("test: ", test);

      // let newData = cable.map((item) =>
      //   Object.assign(test, item, {selected:false})
      // )
      // console.log("statusMap: ", JSON.stringify(cable['statusMap']));
      console.log("cable: ", JSON.parse(params['cable']));
      this.cableForm = this.fb.group(JSON.parse(params['cable']));
    } )
  }

}
