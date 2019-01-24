import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute, ParamMap } from '@angular/router';
@Component({
  selector: 'app-reloadpage',
  templateUrl: './reloadpage.component.html',
  styleUrls: ['./reloadpage.component.css']
})
export class ReloadpageComponent implements OnInit {

    page  : any;


  constructor( private route: ActivatedRoute,
   private router: Router) {
      this.page = this.route.snapshot.params.page
    }
  ngOnInit() {


this.router.navigate(['/'+this.page]);
  }

}
