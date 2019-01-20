import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { User } from './user';
import {Component, OnInit,Inject} from '@angular/core';

@Injectable()
export class AuthService{

  private loggedIn = new BehaviorSubject<boolean>(false); // {1}

  get isLoggedIn() {
    return this.loggedIn.asObservable(); // {2}
  }

  constructor(private router: Router) {}

  login(user: string){

    localStorage.setItem('currentUser', user);
    localStorage.setItem('currentAdmin', "admin");
    this.loggedIn.next(true);
    this.router.navigate(['/home']);
// ไว้ดึง user localStorage.getItem('currentUser')

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
