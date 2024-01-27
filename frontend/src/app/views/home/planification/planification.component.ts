import { Component } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import timeGridPlugin from '@fullcalendar/timegrid';
@Component({
  selector: 'app-planification',
  templateUrl: './planification.component.html',
  styleUrls: ['./planification.component.css']
})
export class PlanificationComponent {


  calendarOptions: CalendarOptions = {
    plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay,',
    },
    initialView: 'dayGridMonth',
    editable: true,
    selectable: true,
    dateClick: this.handleDateClick.bind(this),
    events: [
      // Puedes agregar eventos aquí
      { title: 'Evento 1', date: '2024-01-20' },
      { title: 'Evento 2', date: '2024-01-22' },
    ],
  };

  handleDateClick(arg:any) {

  }
}
