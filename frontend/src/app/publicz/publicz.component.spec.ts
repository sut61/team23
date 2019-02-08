import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PubliczComponent } from './publicz.component';

describe('PubliczComponent', () => {
  let component: PubliczComponent;
  let fixture: ComponentFixture<PubliczComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PubliczComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PubliczComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
