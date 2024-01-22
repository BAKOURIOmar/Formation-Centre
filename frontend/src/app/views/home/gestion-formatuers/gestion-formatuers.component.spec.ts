import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionFormatuersComponent } from './gestion-formatuers.component';

describe('GestionFormatuersComponent', () => {
  let component: GestionFormatuersComponent;
  let fixture: ComponentFixture<GestionFormatuersComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GestionFormatuersComponent]
    });
    fixture = TestBed.createComponent(GestionFormatuersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
