import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionEntrepriseComponent } from './gestion-entreprise.component';

describe('GestionEntrepriseComponent', () => {
  let component: GestionEntrepriseComponent;
  let fixture: ComponentFixture<GestionEntrepriseComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GestionEntrepriseComponent]
    });
    fixture = TestBed.createComponent(GestionEntrepriseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
