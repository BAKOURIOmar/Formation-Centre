import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormationAccueilComponent } from './formation-accueil.component';

describe('FormationAccueilComponent', () => {
  let component: FormationAccueilComponent;
  let fixture: ComponentFixture<FormationAccueilComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormationAccueilComponent]
    });
    fixture = TestBed.createComponent(FormationAccueilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
