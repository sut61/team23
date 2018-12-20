import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { User } from './user';

@Injectable()
export class AuthService {
  private loggedIn = new BehaviorSubject<boolean>(false); // {1}

  get isLoggedIn() {
    return this.loggedIn.asObservable(); // {2}
  }

  constructor(
    private router: Router
  ) {}

  login(user: User){

      localStorage.setItem('currentUser', "admin");
    if (user.userName == localStorage.getItem('currentUser') && user.password == localStorage.getItem('currentUser'))  { // {3}
      this.loggedIn.next(true);
      this.router.navigate(['/home']);
    }
    else{
      alert("Error Username or password is wrong\nPlease try again");
    }
  }


  logout() {                            // {4}
    this.loggedIn.next(false);
    localStorage.removeItem('currentUser');
    this.router.navigate(['/login']);
  }
}
