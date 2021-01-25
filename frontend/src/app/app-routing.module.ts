import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddBookComponent } from './add-book/add-book.component';
import { CartComponent } from './cart/cart.component';
import { GetAllBooksComponent } from './get-all-books/get-all-books.component';
import { NavbarComponent } from './navbar/navbar.component';
import { UpdateBookComponent } from './update-book/update-book.component';

const routes: Routes = [
  {path:'app-get-all-books',component:GetAllBooksComponent},
  {path:'app-add-book',component:AddBookComponent},
  {path:'app-update-book/:id',component:UpdateBookComponent},
  {path:'app-cart',component:CartComponent},
  {path:'app-navbar',component:NavbarComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
