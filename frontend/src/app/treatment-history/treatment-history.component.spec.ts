import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TreatmentHistoryComponent } from './treatment-history.component';

describe('TreatmentHistoryComponent', () => {
  let component: TreatmentHistoryComponent;
  let fixture: ComponentFixture<TreatmentHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TreatmentHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TreatmentHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
