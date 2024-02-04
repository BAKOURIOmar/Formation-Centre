import { DatePipe } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CalendarOptions, DateSelectArg } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import timeGridPlugin from '@fullcalendar/timegrid';
import { NewPlanificationComponent } from 'src/app/components/new-planification/new-planification.component';
import { SendPlanification } from 'src/app/shared/interfaces/sendPlanification.interface';
import { PlanificationService } from 'src/app/shared/services/planification.service';
import frLocale from '@fullcalendar/core/locales/fr';

@Component({
  selector: 'app-planification',
  templateUrl: './planification.component.html',
  styleUrls: ['./planification.component.css']
})
export class PlanificationComponent implements OnInit{
  public dialog = inject(MatDialog);
  private datePipe= inject(DatePipe);
  public planificationService = inject(PlanificationService);
  events: SendPlanification[]=[];

  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
    editable: true,
    selectable: true,
    select: this.handleDateSelect.bind(this),
    eventClick: this.handleEventClick.bind(this),
    // eventClick: function(event){
    //     console.log(event.event);

    // },
    themeSystem: 'bootstrap',
    contentHeight: 'auto',
    eventBackgroundColor: '#2ecc71',
    locale: frLocale
  };

  ngOnInit(): void {
    this.loadEvents();
  }
  handleDateSelect(selectInfo: DateSelectArg) {
    console.log("Dialog open: ");
    console.log(selectInfo);
    console.log('Selection Start: ' + selectInfo.startStr);
    console.log('Selection End: ' + selectInfo.endStr);

    const dialogRef = this.dialog.open(NewPlanificationComponent, {
      width: '400px',
      data: {
        start: this.datePipe.transform(selectInfo.startStr,'yyyy-MM-dd'),
        end: this.datePipe.transform(selectInfo.endStr,'yyyy-MM-dd'),
        mode: 'add'
      }
    });
  }
  handleEventClick(event:any){
    console.log('Datos personalizados del evento:', event);
    const eventData = event.event.extendedProps.myCustomData;
    console.log('Datos personalizados del evento:', eventData);
    const dialogRef = this.dialog.open(NewPlanificationComponent, {
      width: '400px',
      data: {
        eventData,
        mode: 'update'
      }
    });

  }



  loadEvents(): void {
    this.planificationService.getallPlanifications().subscribe(
      (response: SendPlanification[]) => {
        this.events = response;
        this.calendarOptions.events = this.events.map(event => ({
          title: event.title,
          start: new Date(event.datedebut),
          end: new Date(event.datefin),
          extendedProps: { myCustomData: event }
        }));
      },
      error => {
        console.error('Erreur lors du chargement des événements :', error);
      }
    );
  }
}
