import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-preview',
  templateUrl: './preview.component.html',
  styleUrls: ['./preview.component.css']
})
export class PreviewComponent implements OnInit {


  constructor(
    public activatedRoute: ActivatedRoute,
    private router: Router) { }

  headers: Array<string> = [];
  dataGrid : Array<object> = [];

  ngOnInit(): void {
    if (history.state.dataLoaded)
      this.setData(history.state.data, history.state.dataModel);
    else this.navigateToFormComponent();
  }

  goBack(){
    this.navigateToFormComponent();
  }

  navigateToFormComponent(){
    this.router.navigateByUrl('/export-data')
  }

  download(){
    console.log('download')
  }

  setData(data : string, dataModel : {delimiter: {value: string}}){
    let lines = this.splitDataByLines(data);
    // setting delimiter from the previous component(FormComponent)
    let delimiterChar = this.getDelimiterChar(dataModel.delimiter);
    // setting headers
    this.headers = this.getHeaders(lines[0], delimiterChar);
    // removing headers from data
    lines.shift();
    // removing last line (because it's empty)
    lines.pop();
    // setting dataGrid
    let dataGridTableFormat = this.fillDataGrid(lines, delimiterChar);
     // this.dataGrid = this.fillDataGrid(lines, delimiterChar);
    this.dataGrid = this.getDatagridJSONFormat(dataGridTableFormat, this.headers);
  }

  test(record: string){
    console.log('record: ', record);
  }

  test1(record: object, header: string){
    // console.log('record: ', record[header]);
    console.log('getValue: ', this.getValue(record, header));
  }

  // const getValue = (part, o) => Object.entries(o).find(([k, v]) => k.startsWith(part))?.[1]

  getValue (object: object, header: string){
    return Object.entries(object).find(([key, value]) => key === header)?.[1]
  }

  getDatagridJSONFormat(rows: Array<Array<string>>, columns: Array<string>){
    const result = rows.map(row =>
      row.reduce(
        (result, field, index) => ({ ...result, [columns[index]]: field }),
        {}
      )
    )
    return result;
  }

  getHeaders(line : string, delimiter: string): Array<string>{
    return this.splitLineByDelimiter(line, delimiter);
  }

  fillDataGrid(lines: Array<string>, delimiter: string): Array<Array<string>>{
    let dataGrid = new Array();
    for (let i = 0; i < lines.length; i++) {
      // console.log('lines[i]: ', lines[i])
      let formattedRow = this.splitLineByDelimiter(lines[i], delimiter);
      // console.log('formattedLine: ', formattedLine);
      // dataGrid.push(formattedRow.join(' '));
      dataGrid.push(formattedRow);
    }
    return dataGrid;
  }

  splitLineByDelimiter(line: string, delimiter: string){
    return line.split(delimiter);
  }

  getDelimiterChar(delimiter: {value: string}): string{
    switch (delimiter.value) {
      case 'SEMICOMMA':
        return ';';
      case 'COMMA':
        return ',';
      case 'PIPE':
        return '|';
    }
    return ';';
  }

  splitDataByLines(data: string){
    return data.split(/\r?\n/);
  }


}
