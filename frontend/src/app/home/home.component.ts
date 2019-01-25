import { Component, OnInit } from '@angular/core';
import { AlertService } from '../alert.service';

import { Router, ActivatedRoute, ParamMap } from '@angular/router';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
selectedIndex: number;
transform: number;
  constructor(private alertService: AlertService,
  private route: ActivatedRoute,
   private router: Router ) {

     this.selectedIndex = 0;
    this.transform = 100;
    }
  username:String;

sliderArray = [
  {img: '../../assets/1.jpg', alt: '', text: 'Suranaree University of Technology'},
  {img: '../../assets/2.jpg', alt: '', text: 'Suranaree University of Technology'},
  {img: '../../assets/3.jpg', alt: '', text: 'Suranaree University of Technology'}

  ];
  ngOnInit() {


        this.username = localStorage.getItem('currentUser');
  }

  success(message: string) {
          this.alertService.success(message);
      }

      error(message: string) {
          this.alertService.error(message);
      }

      info(message: string) {
          this.alertService.info(message);
      }

      warn(message: string) {
          this.alertService.warn(message);
      }

      clear() {
          this.alertService.clear();
      }


  selected(x) {
    this.downSelected(x);
    this.selectedIndex = x;

   }

   keySelected(x) {
     this.downSelected(x);
     this.selectedIndex = x;
   }
   downSelected(i) {
    this.transform =  100 - (i) * 50;
      this.selectedIndex = this.selectedIndex + 1;
      if(this.selectedIndex > 4) {
        this.selectedIndex = 0;
      }
   }
}
