import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GoldCardRegisterComponent } from './gold-card-register.component';

describe('GoldCardRegisterComponent', () => {
  let component: GoldCardRegisterComponent;
  let fixture: ComponentFixture<GoldCardRegisterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GoldCardRegisterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GoldCardRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
