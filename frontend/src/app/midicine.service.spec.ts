import { TestBed } from '@angular/core/testing';

import { MidicineService } from './midicine.service';

describe('MidicineService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MidicineService = TestBed.get(MidicineService);
    expect(service).toBeTruthy();
  });
});
