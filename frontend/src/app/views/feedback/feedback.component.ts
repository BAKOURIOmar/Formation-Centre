import { FeedbackService } from './../../shared/services/feedback.service';
import { Feedback } from './../../shared/interfaces/Feedback.interface';
import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent {

  rating:number = 3;
  starCount:number = 5;
  // starColor:StarRatingColor = StarRatingColor.accent;
  // starColor:StarRatingColor = StarRatingColor.primary;
  starColor:StarRatingColor = StarRatingColor.warn;



  public formateurForm!: FormGroup;
  private fb = inject(FormBuilder);

  private individualId!: number;
  private formateurId !: number;

  constructor(private route: ActivatedRoute,
              private feedbackService :FeedbackService  ,
              private snackBar: MatSnackBar,
    ) {}

  ngOnInit() {
    this.formateurForm = this.fb.group( {
      description: ['', Validators.required],
    })



      this.route.queryParamMap.subscribe(params => {
        const individualId = params.get('individualId');
        this.individualId = Number(individualId);
        const formateurId = params.get('formateurId');
        this.formateurId = Number(formateurId);
      });



  }
    onRatingChanged(rating:number){
    console.log(rating);
    this.rating = rating;
  }


  onSubmit() {
    console.log('rating is ', this.rating);
    console.log('description is : ', this.formateurForm.value.description);

    let feedback : Feedback = {
      rating : this.rating,
      description : this.formateurForm.value.description,
      individualId : this.individualId,
      formateurId : this.formateurId
    }
    console.log('feedback is : ', feedback);

    this.feedbackService.addFeedback(feedback).subscribe(
      (data) => {

        console.log('feedback added successfully');
      },
      (error) => {
         this.snackBar.open('feedback added successfully', 'close', {
          duration: 2000,
        });
        console.log('error in adding feedback'+error);
      }
    )

  }
}

export enum StarRatingColor {
  primary = "primary",
  accent = "accent",
  warn = "warn"
}
