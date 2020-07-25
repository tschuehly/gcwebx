import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EsportComponent } from './esport.component';

describe('EsportComponent', () => {
  let component: EsportComponent;
  let fixture: ComponentFixture<EsportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EsportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EsportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
