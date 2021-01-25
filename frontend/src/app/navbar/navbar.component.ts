import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../model/user';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  user: User = new User();
  errorMessage = 'Invalid Credentials';
  successMessage: string = '';
  invalidLogin = false;
  loginSuccess = false;
  isLoggedIn = false;

  constructor(
    public route: ActivatedRoute,
    public router: Router,
    public authenticationService: AuthService) { }



  ngOnInit() {

    this.isLoggedIn = this.authenticationService.isUserLoggedIn();
    this.authenticationService.isAdmin=this.authenticationService.checkAdmin();
    console.log('menu ->' + this.isLoggedIn);
  }

  handleLogout() {
    this.authenticationService.logout();
    this.ngOnInit();
  }
  async handleLogin() {
    await this.authenticationService.authenticationService(this.user.userName, this.user.password);
    if (this.authenticationService.isUserLoggedIn()) {
      this.invalidLogin = false;
      this.successMessage = 'Login Successful.';
      this.ngOnInit();
    } else {
      this.invalidLogin = true;
      this.user.password = '';

    }


  }

}
