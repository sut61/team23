import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditMedicalSuppliesComponent } from './edit-medical-supplies.component';

describe('EditMedicalSuppliesComponent', () => {
  let component: EditMedicalSuppliesComponent;
  let fixture: ComponentFixture<EditMedicalSuppliesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditMedicalSuppliesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditMedicalSuppliesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
