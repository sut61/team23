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

    localStorage.setItem('currentUser', "user");
    localStorage.setItem('currentAdmin', "admin");
   if (user.userName == localStorage.getItem('currentUser') && user.password == localStorage.getItem('currentUser'))  { // {3}
      this.loggedIn.next(true);
      this.router.navigate(['/home']);
    }
    else if (user.userName == localStorage.getItem('currentAdmin') && user.password == localStorage.getItem('currentAdmin'))  { // {3}
          localStorage.setItem('currentUser',localStorage.getItem('currentAdmin'));

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
  register(){
    localStorage.setItem('currentRegis', "test");
    this.loggedIn.next(true);
    this.router.navigate(['/goldCardRegister']);
  }
}
