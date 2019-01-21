import { TestBed } from '@angular/core/testing';

import { GoldcardService } from './goldcard.service';

describe('GoldcardService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GoldcardService = TestBed.get(GoldcardService);
    expect(service).toBeTruthy();
  });
});
