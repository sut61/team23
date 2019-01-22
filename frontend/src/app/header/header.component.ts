import { Observable } from 'rxjs/Observable';
import { AuthService } from '../auth/auth.service';
import { Component, OnInit } from '@angular/core';
import {MatTooltipModule} from '@angular/material/tooltip';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


  isLoggedIn$: Observable<boolean>;                  // {1}
check: String;
    constructor(private authService: AuthService) { }
    hidden : string;
    ngOnInit() {

        this.isLoggedIn$ = this.authService.isLoggedIn;
    }

    onLogout(){
      alert("Good Bye\n"+localStorage.getItem('currentUser'));
      this.authService.logout();                      // {3}
    }

}
