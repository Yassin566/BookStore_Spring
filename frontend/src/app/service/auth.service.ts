import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // BASE_PATH: 'http://localhost:8080'
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
  USER_TOKEN_SESSION_ATTRIBUTE_NAME ='token'
  USER_ID_SESSION_ATTRIBUTE_NAME='id'
  public username: string='';
  public password: string='';
  public user:User=new User();
  public finalUer:any|User;
  public isAdmin:boolean=false;
 


  constructor(private http: HttpClient,public router: Router) {

  }
  

  async authenticationService(username: string, password: string) {
    this.user.userName=username;
    this.user.password=password;
    let result =null;
    let authorization=this.createBasicAuthToken(username, password);
    await this.http.post("http://127.0.0.1:8080/user/login",this.user,{ headers : new HttpHeaders( { Authorization: authorization } )})
    .toPromise().then((resp)=>{
      this.finalUer=resp;
      console.log("resp :",this.finalUer.roles);
      this.user.roles=this.finalUer.roles;
      this.user.id=this.finalUer.id;
    },(e)=>{
      if (e.status === 401) {
        return null;
      }else{
        return null;
      }
    })
    
    
    if(this.finalUer!=undefined) {
        this.username = username;
        this.password = password;
        if(this.user.roles.includes("ADMIN")){
          this.isAdmin=true;
          sessionStorage.setItem('admin', 'true');
        }
        sessionStorage.setItem(this.USER_ID_SESSION_ATTRIBUTE_NAME, this.user.id.toString());
        this.registerSuccessfulLogin(username, authorization);
        this.router.navigate([this.router.url]);
      }
  }

  createBasicAuthToken(username: string, password: string) {
    return 'Basic ' + window.btoa(username + ":" + password)
  }

  registerSuccessfulLogin(username: string, authorization: string) {
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username);
    sessionStorage.setItem(this.USER_TOKEN_SESSION_ATTRIBUTE_NAME, authorization);
  }

  logout() {
    this.http.get("http://127.0.0.1:8080/logout");
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    sessionStorage.removeItem(this.USER_TOKEN_SESSION_ATTRIBUTE_NAME);
    sessionStorage.removeItem(this.USER_ID_SESSION_ATTRIBUTE_NAME);
    sessionStorage.removeItem('admin');
    this.username = '';
    this.password = '';
    this.isAdmin=false;
  }

  isUserLoggedIn() {
    if (sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME) === null) return false
    return true
  }

  getLoggedInUserName() {
    if (sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME) === null) return ''
    return sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME) ;
  }
  getLoggedInToken() {
    if (sessionStorage.getItem(this.USER_TOKEN_SESSION_ATTRIBUTE_NAME) === null) return ''
    return JSON.parse(JSON.stringify(sessionStorage.getItem(this.USER_TOKEN_SESSION_ATTRIBUTE_NAME))) ;
  }

  getLoggedInId(){
    if (sessionStorage.getItem(this.USER_ID_SESSION_ATTRIBUTE_NAME) === null) return ''
    return JSON.parse(JSON.stringify(sessionStorage.getItem(this.USER_ID_SESSION_ATTRIBUTE_NAME))) ;
  }

  checkAdmin(){
    if (sessionStorage.getItem('admin') === null) return ''
    return JSON.parse(JSON.stringify(sessionStorage.getItem('admin'))) ;
  }
}
