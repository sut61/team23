import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMidicineComponent } from './add-midicine.component';

describe('AddMidicineComponent', () => {
  let component: AddMidicineComponent;
  let fixture: ComponentFixture<AddMidicineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddMidicineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMidicineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
