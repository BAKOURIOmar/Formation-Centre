import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionFormateursExterneComponent } from './gestion-formateurs-externe.component';

describe('GestionFormateursExterneComponent', () => {
  let component: GestionFormateursExterneComponent;
  let fixture: ComponentFixture<GestionFormateursExterneComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GestionFormateursExterneComponent]
    });
    fixture = TestBed.createComponent(GestionFormateursExterneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
