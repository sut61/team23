import { TestBed } from '@angular/core/testing';

import { MedicalsuppliesService } from './medicalsupplies.service';

describe('MedicalsuppliesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MedicalsuppliesService = TestBed.get(MedicalsuppliesService);
    expect(service).toBeTruthy();
  });
});
