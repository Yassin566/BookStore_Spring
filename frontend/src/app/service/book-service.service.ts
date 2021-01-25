import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from '../model/book';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class BookServiceService {
  hostUrl="http://127.0.0.1:8080/books";

  httpOptions={
    headers : new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'my-auth-token'
    })
  };

  selectedFile: any;
  message: string="";
  responseImage:any;
  

  constructor(public http: HttpClient,public auth:AuthService) { }

  getAll(){
  return this.http.get(this.hostUrl,this.httpOptions);
  }

  async onUpload() {
    console.log(this.selectedFile);
    
    //FormData API provides methods and properties to allow us easily prepare form data to be sent with POST HTTP requests.
    const uploadImageData = new FormData();
    uploadImageData.append('myFile', this.selectedFile, this.selectedFile.name);
    console.log(this.selectedFile.name);
     //Make a call to the Spring Boot Application to save the image
     await this.http.post(this.hostUrl+"/upload", uploadImageData, { observe: 'response' })
     .toPromise().then((response) => {
       if (response.status === 200) {
         this.message = 'Image uploaded successfully';
         this.responseImage=response.body;
       } else {
         this.message = 'Image not uploaded successfully';
       }
     }
     );
  }

  getBook(id:any){
    return this.http.get(this.hostUrl+"/find",{params:{
      id:id
    }})
  }

  addBook(book:Book){
    book.image=this.responseImage;
    return this.http.post(this.hostUrl+"/add",JSON.stringify(book),{ headers : new HttpHeaders( {'Content-Type':  'application/json', Authorization: this.auth.getLoggedInToken() } )});
  }

  getImage(id:any){
    return this.http.get(this.hostUrl+"/getImage/"+ id,this.httpOptions)
  }

  deleteBook(id:any){
    return this.http.delete(this.hostUrl+"/remove",{params:{
      id:id
    },headers : new HttpHeaders( {'Content-Type':  'application/json', Authorization: this.auth.getLoggedInToken() } )})
  }

  updateBook(book:Book){
    book.image=this.responseImage;
    this.message="";
    return this.http.put(this.hostUrl+"/update",book)
  }
}
