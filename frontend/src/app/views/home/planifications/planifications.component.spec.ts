import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlanificationsComponent } from './planifications.component';

describe('PlanificationsComponent', () => {
  let component: PlanificationsComponent;
  let fixture: ComponentFixture<PlanificationsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PlanificationsComponent]
    });
    fixture = TestBed.createComponent(PlanificationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
