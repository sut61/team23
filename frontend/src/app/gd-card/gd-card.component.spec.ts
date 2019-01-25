import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GDCardComponent } from './gd-card.component';

describe('GDCardComponent', () => {
  let component: GDCardComponent;
  let fixture: ComponentFixture<GDCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GDCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GDCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
