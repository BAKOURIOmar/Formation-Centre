import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionAssistantsComponent } from './gestion-assistants.component';

describe('GestionAssistantsComponent', () => {
  let component: GestionAssistantsComponent;
  let fixture: ComponentFixture<GestionAssistantsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GestionAssistantsComponent]
    });
    fixture = TestBed.createComponent(GestionAssistantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
