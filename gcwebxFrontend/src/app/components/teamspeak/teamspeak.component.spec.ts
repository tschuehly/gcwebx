import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamspeakComponent } from './teamspeak.component';

describe('TeamspeakComponent', () => {
  let component: TeamspeakComponent;
  let fixture: ComponentFixture<TeamspeakComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeamspeakComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeamspeakComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
