import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptToUserComponent } from './accept-to-user.component';

describe('AcceptToUserComponent', () => {
  let component: AcceptToUserComponent;
  let fixture: ComponentFixture<AcceptToUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AcceptToUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AcceptToUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
