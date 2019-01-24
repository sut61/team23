import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReloadpageComponent } from './reloadpage.component';

describe('ReloadpageComponent', () => {
  let component: ReloadpageComponent;
  let fixture: ComponentFixture<ReloadpageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReloadpageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReloadpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
