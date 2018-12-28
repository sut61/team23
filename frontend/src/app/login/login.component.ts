import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from './../auth/auth.service';
import { Observable } from 'rxjs/Observable';

import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: FormGroup;                    // {1}
  private formSubmitAttempt: boolean; // {2}
  private loggedIn = new BehaviorSubject<boolean>(false); // {1}

  navLinks = [
     {path: 'goldCardRegister', label: 'GoldCardRegister'},
   ];

  isLoggedIn$: Observable<boolean>;
  constructor(
    private fb: FormBuilder,         // {3}
    private authService: AuthService, // {4}
     private router: Router
  ) {}

  ngOnInit() {
    this.form = this.fb.group({     // {5}
      userName: ['', Validators.required],
      password: ['', Validators.required]
    });


             this.isLoggedIn$ = this.authService.isLoggedIn;

  }

  isFieldInvalid(field: string) { // {6}
    return (
      (!this.form.get(field).valid && this.form.get(field).touched) ||
      (this.form.get(field).untouched && this.formSubmitAttempt)
    );
  }

  onSubmit() {
      if (this.form.valid) {
        this.authService.login(this.form.value); // {7}

      }
      this.formSubmitAttempt = true;             // {8}
    }
    regis(){
                 this.loggedIn.next(true);

    }
}

@Component({
  selector: 'error',
  templateUrl: './error.html'
})
export class ErrorComponent{

}
