import { Observable } from 'rxjs/Observable';
import { AuthService } from '../auth/auth.service';
import { Component, OnInit } from '@angular/core';
import {MatTooltipModule} from '@angular/material/tooltip';
import { GoldcardService } from '../goldcard.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
username: String;
  isLoggedIn$: Observable<boolean>;                  // {1}
check: String;
accs: Array<any>;
    constructor(private authService: AuthService,private goldcardService: GoldcardService) { }
    hidden : string;
    ngOnInit() {
            this.goldcardService.getAcceptToUser().subscribe(data =>{
                  this.accs = data;
                  console.log(this.accs);
            });
        this.isLoggedIn$ = this.authService.isLoggedIn;
        this.username = localStorage.getItem('currentUser');
    }

    onLogout(){
      alert("Good Bye\n"+localStorage.getItem('currentUser'));
      this.authService.logout();                      // {3}
    }

}
