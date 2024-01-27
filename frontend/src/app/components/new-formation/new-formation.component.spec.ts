import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewFormationComponent } from './new-formation.component';

describe('NewFormationComponent', () => {
  let component: NewFormationComponent;
  let fixture: ComponentFixture<NewFormationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewFormationComponent]
    });
    fixture = TestBed.createComponent(NewFormationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
