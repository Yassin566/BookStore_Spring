import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GetAllBooksComponent } from './get-all-books/get-all-books.component';
import { BookServiceService } from './service/book-service.service';
import { AddBookComponent } from './add-book/add-book.component';
import { FormsModule } from '@angular/forms';
import { UpdateBookComponent } from './update-book/update-book.component';
import { CartComponent } from './cart/cart.component';
import { CartService } from './service/cart.service';
import { NavbarComponent } from './navbar/navbar.component';

@NgModule({
  declarations: [
    AppComponent,
    GetAllBooksComponent,
    AddBookComponent,
    UpdateBookComponent,
    CartComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [BookServiceService,CartService],
  bootstrap: [AppComponent]
})
export class AppModule { }
