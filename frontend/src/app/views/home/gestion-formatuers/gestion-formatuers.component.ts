import { Component } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
@Component({
  selector: 'app-gestion-formatuers',
  templateUrl: './gestion-formatuers.component.html',
  styleUrls: ['./gestion-formatuers.component.css']
})
export class GestionFormatuersComponent {


  Events: any[] = [];
  calendarOptions: CalendarOptions = {
    plugins: [dayGridPlugin, interactionPlugin],
    initialView: 'dayGridMonth',
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek',
    },
    weekends: true,
    editable: true,
    selectable: true,
    selectMirror: true,
    dayMaxEvents: true,
  };
  constructort() {}
  onDateClick(res: any) {
    alert('Clicked on date : ' );
  }
  ngOnInit() {
  }
}
