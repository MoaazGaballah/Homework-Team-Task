import {AfterViewInit, Component, ViewChild, ViewEncapsulation} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {MatSort, Sort} from "@angular/material/sort";
import {CableService} from "../../services/cable.service";
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

  initializePageEvent() {
    this.pageEvent = new PageEvent();
    this.pageEvent.pageSize = 5;
    this.pageEvent.pageIndex = 0;
  }

  initializeSort() {
    this.sort = new MatSort();
    this.sort.direction = 'asc';
    this.sort.active = 'id';
  }

  ngOnInit() {
    this.cableService.getCables(
      this.filterValue,
      5,
      0,
      this.getSortingColumnName(this.sort.active),
      this.sort.direction).subscribe(async response => {
      this.responseData = await <MatTableDataSource<any>>response;
      this.dataSource = this.responseData.content;
      if (response && Array.isArray(response)) {
        this.dataSource = new MatTableDataSource(response);
        this.ref.detectChanges();
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }
    });
    this.sort.sortChange.subscribe(() => {
      if (this.pageEvent == null) {
        this.initializePageEvent();
      }
      this.cableService.getCables(
        this.filterValue,
        this.pageEvent.pageSize,
        this.pageEvent.pageIndex,
        this.getSortingColumnName(this.sort.active),
        this.sort.direction).subscribe(async response => {
        this.responseData = await <MatTableDataSource<any>>response;
        this.dataSource = this.responseData.content;
        if (response && Array.isArray(response)) {
          this.dataSource = new MatTableDataSource(response);
          this.ref.detectChanges();
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        }
      });
    })
  }

  applyFilter(event: Event) {
    if (this.pageEvent == null) {
      this.initializePageEvent();
    }
    this.filterValue = (event.target as HTMLInputElement).value
    this.cableService.getCables(
      this.filterValue,
      this.pageEvent.pageSize,
      this.pageEvent.pageIndex,
      this.getSortingColumnName(this.sort.active),
      this.sort.direction
    ).subscribe(async response => {
      this.responseData = await <MatTableDataSource<any>>response;
      this.dataSource = this.responseData.content;
      if (response && Array.isArray(response)) {
        this.dataSource = new MatTableDataSource(response);
        this.ref.detectChanges();
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }
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
        return '#31B0D5';
      case 'FINISHED':
        return '#D9534F';
      case 'USED':
        return '#5CB85C';
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
    rowDetails = Object.assign(rowDetails, {
      statusMap: {
        value: rowDetails.cableStatusValue,
        name: rowDetails.status
      }
    })
    this.router.navigate(['/cables', 'new'], {queryParams: {cable: JSON.stringify(rowDetails)}})
  }

  onDelete(rowDetails: CableData) {
    if (rowDetails.id != null){
      if (this.pageEvent == null) {
        this.initializePageEvent();
      }
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
          this.openSnackBar('Deleted successfully!', 'blue-snackbar');
        })
    }
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

  openSnackBar(message: string, cssClass: string) {
    this._snackBar.open(message, undefined, {
      duration: this.durationInSeconds * 1000,
      panelClass: [cssClass]
    });
  }
}

