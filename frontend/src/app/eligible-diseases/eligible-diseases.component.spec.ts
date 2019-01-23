import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EligibleDiseasesComponent } from './eligible-diseases.component';

describe('EligibleDiseasesComponent', () => {
  let component: EligibleDiseasesComponent;
  let fixture: ComponentFixture<EligibleDiseasesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EligibleDiseasesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EligibleDiseasesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
