import { Component, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from './../auth/auth.service';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-userview',
  templateUrl: './userview.component.html',
  styleUrls: ['./userview.component.css']
})
export class UserviewComponent implements OnInit {
  private loggedIn = new BehaviorSubject<boolean>(false); // {1}
  isLoggedIn$: Observable<boolean>;
  username : string;
  constructor(private authService: AuthService) { }

   get isLoggedIn() {
    return this.loggedIn.asObservable(); // {2}
  }

  ngOnInit() {
      this.isLoggedIn$ = this.authService.isLoggedIn;
      this.username = localStorage.getItem('currentUser')
  }
    onLogout(){
          alert("Good Bye\n"+localStorage.getItem('currentUser'));
          this.authService.logout();                      // {3}
    }
}
