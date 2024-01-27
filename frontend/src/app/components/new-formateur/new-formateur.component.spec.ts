import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewFormateurComponent } from './new-formateur.component';

describe('NewFormateurComponent', () => {
  let component: NewFormateurComponent;
  let fixture: ComponentFixture<NewFormateurComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewFormateurComponent]
    });
    fixture = TestBed.createComponent(NewFormateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
