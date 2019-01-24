import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditMidicineComponent } from './edit-midicine.component';

describe('EditMidicineComponent', () => {
  let component: EditMidicineComponent;
  let fixture: ComponentFixture<EditMidicineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditMidicineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditMidicineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
