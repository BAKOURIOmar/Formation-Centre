import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewAssistantComponent } from './new-assistant.component';

describe('NewAssistantComponent', () => {
  let component: NewAssistantComponent;
  let fixture: ComponentFixture<NewAssistantComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewAssistantComponent]
    });
    fixture = TestBed.createComponent(NewAssistantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
