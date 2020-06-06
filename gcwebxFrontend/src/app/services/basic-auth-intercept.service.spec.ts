import { TestBed } from '@angular/core/testing';

import { BasicAuthInterceptService } from './basic-auth-intercept.service';

describe('BasicAuthInterceptService', () => {
  let service: BasicAuthInterceptService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BasicAuthInterceptService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
