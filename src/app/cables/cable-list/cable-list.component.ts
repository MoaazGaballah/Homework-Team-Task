import {AfterViewInit, Component, ViewChild, ViewEncapsulation} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {MatSort, Sort} from "@angular/material/sort";
import {CableService} from "../cable.service";
import {ChangeDetectorRef} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {DialogAnimationsComponent} from "../../dialog-animations/dialog-animations.component";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";


export interface CableData {
  id: number;
  cableName: string;
  cableType: string;
  thickness: string;
  length: string;
  price: string;
  status: string;
  activatedDate: string;
  cableStatusValue: string;
  statusMap: object;
}

@Component({
  selector: 'app-cable-list',
  templateUrl: './cable-list.component.html',
  styleUrls: ['./cable-list.component.css']
})
export class CableListComponent {

  displayedColumns: string[] = [
    'cableName',
    'cableType',
    'thickness',
    'length',
    'price',
    'status',
    'activatedDate',
    'actions'
  ];
  dataSource: MatTableDataSource<CableData>;
  showData: boolean = false;
  responseData: any = [];
  pageEvent!: PageEvent;
  filterValue: string = '';
  durationInSeconds = 5;

  @ViewChild('paginator') paginator!: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort!: MatSort;

  constructor(
    private cableService: CableService,
    private ref: ChangeDetectorRef,
    public dialog: MatDialog,
    private _snackBar: MatSnackBar,
    private router: Router) {
    this.dataSource = new MatTableDataSource();
  }

  ngOnInit() {
    this.cableService.getCables(this.filterValue, 5, 0, 'id', 'asc').subscribe(async response => {
      this.responseData = await <MatTableDataSource<any>>response;
      this.dataSource = this.responseData.content;

      if (response && Array.isArray(response)) {
        this.showData = true;
        this.dataSource = new MatTableDataSource(response);
        this.ref.detectChanges();
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      } else {
        this.showData = false;
      }
    });
    this.sort.sortChange.subscribe(() => {
      if (this.pageEvent == null) {
        this.pageEvent = new PageEvent();
        this.pageEvent.pageSize = 5;
        this.pageEvent.pageIndex = 0;
      }
      this.cableService.getCables(this.filterValue, this.pageEvent.pageSize, this.pageEvent.pageIndex, this.getSortingColumnName(this.sort.active), this.sort.direction).subscribe(async response => {
        this.responseData = await <MatTableDataSource<any>>response;
        this.dataSource = this.responseData.content;

        if (response && Array.isArray(response)) {
          this.showData = true;
          this.dataSource = new MatTableDataSource(response);
          this.ref.detectChanges();
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        } else {
          this.showData = false;
        }
      });
    })
  }

  getSortingColumnName(sortColumn: string = '') {
    switch (sortColumn) {
      case 'cableName':
        return 'cable_name';
      case 'cableType':
        return 'cable_type';
      case 'thickness':
        return 'thickness';
      case 'length':
        return 'length';
      case 'price':
        return 'price';
      case 'status':
        return 'status';
      case 'activatedDate':
        return 'activated_date';
    }
    return '';
  }

  getTableStatusValue(status: string) {
    switch (status) {
      case 'NEW':
        return 'Sıfır';
      case 'USED':
        return '2. El';
      case 'FINISHED':
        return 'Tükendi';
    }
    return '';
  }

  getTableStatusName(status: string) {
    switch (status) {
      case 'Sıfır':
        return 'NEW';
      case '2. El':
        return 'USED';
      case 'Tükendi':
        return 'FINISHED';
    }
    return '';
  }

  getColor(status: string) {
    switch (status) {
      case 'NEW':
        return '#3BB0D4';
      case 'FINISHED':
        return '#C9302C';
      case 'USED':
        return '#71c454';
    }
    return '';
  }

  onPaginateChange(event: PageEvent) {
    let page = event.pageIndex;
    let size = event.pageSize;
    this.cableService.getCables(this.filterValue, size, page, this.getSortingColumnName(this.sort.active), this.sort.direction).subscribe(async response => {
      this.responseData = await <MatTableDataSource<any>>response;
      this.dataSource = this.responseData.content;
      this.ref.detectChanges();
    })
  }

  onEdit(rowDetails: CableData) {
    // rowDetails['statusMap'] = {
    //   value: rowDetails.cableStatusValue,
    //   name: rowDetails.status
    // }
    rowDetails = Object.assign(rowDetails, {
      statusMap: {
        value: rowDetails.cableStatusValue,
        name: rowDetails.status
      }

    })
    console.log("edit", rowDetails)
    this.router.navigate(['/cables', 'new'], {queryParams: {cable: JSON.stringify(rowDetails)}})
  }

  onDelete(rowDetails: CableData) {
    if (rowDetails.id != null)
      this.cableService.deleteCable(
        rowDetails.id,
        this.pageEvent.pageSize,
        this.pageEvent.pageIndex,
        this.getSortingColumnName(this.sort.active),
        this.sort.direction)
        .subscribe(async response => {
          this.responseData = await <MatTableDataSource<any>>response;
          this.dataSource = this.responseData.content;
          this.ref.detectChanges();
          this.openSnackBar('That row has been deleted!');
        })
  }

  openDialog(enterAnimationDuration: string, exitAnimationDuration: string, rowDetails: CableData): void {
    this.dialog.open(DialogAnimationsComponent, {
      width: '250px',
      enterAnimationDuration,
      exitAnimationDuration,
    }).componentInstance.okButtonSelected.subscribe(() => {
      this.onDelete(rowDetails);
    });
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, undefined, {
      duration: this.durationInSeconds * 1000,
    });
  }
}

