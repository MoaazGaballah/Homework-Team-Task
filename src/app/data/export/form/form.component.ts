import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {DataService} from "../../../services/data.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {IdRange} from "../../../models/data/id-range.model";
import {Router} from "@angular/router";
import {SelectOptionInterface} from "../../../models/data/SelectOption.interface";


@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {


  constructor(
    private dataService: DataService,
    private ref: ChangeDetectorRef,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.getAllClasses();
  }



  dataModel: FormGroup = this.createExportForm(
    '',
    new IdRange(1, 2),
    '',
    false,
    {name: 'SEMICOMMA', value: 'Semicomma (;)'},
    ''
  );

  javaClasses: Array<string> = [];
  filetypes: Array<SelectOptionInterface> = [
    {name: 'CSV', value: 'Csv'},
    {name: 'EXCEL', value: 'Excel'},
    {name: 'INSERT_SCRIPT', value: 'Insert Script (PostgrelSQL)'}
  ];
  delimiters: Array<SelectOptionInterface> = [
    {name: 'SEMICOMMA', value: 'Semiecomma (;)'},
    {name: 'COMMA', value: 'Comma (,)'},
    {name: 'PIPE', value: 'Pipe (|)'}
  ];
  dateTimeFormats: Array<SelectOptionInterface> = [
    {name: 'YYYY-MM-DD_HH:MM:SS', value: 'YYYY-MM-DD HH:mm:SS'},
    {name: 'DD-MM-YYYY_HH:MM:SS', value: 'DD-MM-YYYY HH:mm:SS'},
    {name: 'YYYY-MM-DD', value: 'YYYY-MM-DD'}
  ];

  getAllClasses() {
    this.dataService.getAllModelClasses().subscribe({
      next: (response) => {
        this.javaClasses = response;
      },
      error: (err) => console.error(err),
      complete: () => console.info('')
    })
  }

  javaClassesSelectToggeled() {
    if (this.dataModel.value['javaClass'].length === 0) {
      this.getAllClasses();
    }
  }

  gotoPreview(data: object) {
    this.router.navigateByUrl('/export-data/preview', {state: {data, dataModel: this.dataModel.value, dataLoaded: true}});
  }

  getDataPreview() {
    if (this.dataModel.valid)
      this.dataService.getPreviewData(this.dataModel.value).subscribe(data => {
        this.gotoPreview(data);
      })
  }

  createExportForm(
    javaClass: string,
    idRange: IdRange,
    fileType: string,
    databaseFieldNamesToSecondRow: boolean,
    delimiter: object,
    dateTimeFormat: string
  ) {
    this.dataModel = this.formBuilder.group({
      javaClass: [javaClass, Validators.required],
      idRange: this.createIdRangeGroup(new IdRange(1, 2)),
      fileType: [fileType, Validators.required],
      databaseFieldNamesToSecondRow:
        [databaseFieldNamesToSecondRow, !Validators.required],
      delimiter: [delimiter, this.isRequired(fileType) ? Validators.required : !Validators.required],
      dateTimeFormat: [dateTimeFormat, Validators.required],
    })
    return this.dataModel;
  }

  isRequired(fileType: string){
    if (fileType === 'CSV') return true;
    return false;
  }

  createIdRangeGroup(idRange: IdRange) {
    return this.formBuilder.group({
      start: [idRange.start, Validators.required],
      end: [idRange.end, Validators.required]
    })
  }

  compareTwoOptions(option1: SelectOptionInterface, option2: SelectOptionInterface) {
    return option1.name === option2.name;
  }

}
