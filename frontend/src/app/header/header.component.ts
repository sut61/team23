import { Observable } from 'rxjs/Observable';
import { AuthService } from '../auth/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

navLinks = [
     {path: 'goldcardregister', label: 'GoldCardRegister'},
   ];

  isLoggedIn$: Observable<boolean>;                  // {1}
check: String;
    constructor(private authService: AuthService) { }

    ngOnInit() {
      this.isLoggedIn$ = this.authService.isLoggedIn; // {2}

    }

    onLogout(){
      this.authService.logout();                      // {3}
    }

}
