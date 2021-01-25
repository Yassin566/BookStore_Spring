import { isNull } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { Book } from '../model/book';
import { ImageModel } from '../model/image-model';
import { AuthService } from '../service/auth.service';
import { BookServiceService } from '../service/book-service.service';
import { CartService } from '../service/cart.service';

@Component({
  selector: 'app-get-all-books',
  templateUrl: './get-all-books.component.html',
  styleUrls: ['./get-all-books.component.css']
})
export class GetAllBooksComponent implements OnInit {
  listBooks: any;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  constructor(public bookService: BookServiceService,public cart:CartService, public router: Router,public auth:AuthService) { }

  ngOnInit(): void {
    this.getBooks();
  }

  async getBooks() {
    await this.bookService.getAll()
      .subscribe(resp => {
        this.listBooks = resp;
      })
  }

  getImage(imagePic: any) {
    if (imagePic != null)
      return 'data:image/jpeg;base64,' + imagePic.pic;
    else
      return "../assets/book.jpg";

  }
  async deleteBook(id: any) {
    await this.bookService.deleteBook(id)
      .subscribe(resp => {
        console.log("item deleted :" + resp)
      });
      this.router.navigateByUrl(this.router.url);

  }

  update(id: number) {
    this.router.navigate(['app-update-book/' + id])

  }

  addToCart(id: number, quantity: any):void {
    if(this.auth.isUserLoggedIn())
      this.cart.addBooks(id,parseInt(quantity))
      else
      alert("You need to be logged In !!")
      // this.router.navigate(['/shopping-cart']);
      
  }

  

}
