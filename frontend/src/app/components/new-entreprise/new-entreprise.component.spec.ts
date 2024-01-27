import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewEntrepriseComponent } from './new-entreprise.component';

describe('NewEntrepriseComponent', () => {
  let component: NewEntrepriseComponent;
  let fixture: ComponentFixture<NewEntrepriseComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewEntrepriseComponent]
    });
    fixture = TestBed.createComponent(NewEntrepriseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
