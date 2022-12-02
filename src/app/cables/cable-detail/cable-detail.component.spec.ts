import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CableDetailComponent } from './cable-detail.component';

describe('CableDetailComponent', () => {
  let component: CableDetailComponent;
  let fixture: ComponentFixture<CableDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CableDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CableDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
