import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../model/book';
import { AuthService } from '../service/auth.service';
import { BookServiceService } from '../service/book-service.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  model=new Book();
  submitted=false;
  constructor(public bookService:BookServiceService,public auth:AuthService,public router:Router) { 
    if(!this.auth.isAdmin){
      this.router.navigate(['app-get-all-books']);
    }
  }

  ngOnInit(): void {
  }

  

  onSubmit(){
    this.bookService.addBook(this.model)
    .subscribe(resp=>{
      console.log(this.model.price);
      this.submitted=true;
    })
  }

  public onFileChanged(event:any) {
    //Select File
    this.bookService.selectedFile = event.target.files[0];
    this.bookService.onUpload();
  }

  newBook() {
    

  }

}
