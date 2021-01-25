import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../model/book';
import { OrderBook } from '../model/order-form';
import { BookServiceService } from '../service/book-service.service';
import { CartService } from '../service/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(public cart: CartService, public router: Router, public book: BookServiceService) {
    this.getItems();
    this.getTotal();

  }
  currentQuantity = 0;
  currentSubTotal = 0;
  bookResult: Book = new Book();
  BookItems: Book[] = new Array<Book>();
  listResult: OrderBook[] = new Array<OrderBook>();
  counter = 0;
  total: any;
  ngOnInit(): void {

    console.log("thisBookItemes :", this.BookItems);
  }

  setSubTotal(currentQuantity: any, currentPrice: any) {
    this.currentSubTotal = currentPrice * currentQuantity;
  }

  incCount() {
    this.counter++;
  }
  resCount() {
    this.counter = 0;
  }
  resCurreQuantity() {
    this.currentQuantity = 0;
  }
  resCurrentSubTotal() {
    this.currentSubTotal = 0;
  }
  resTotal() {
    this.total = 0;
  }

  addTotal(price: any) {
    this.total += price;
  }

  getItems() {
    this.listResult = this.cart.getOrders();
    let numberOfBooks = this.listResult.length;
    for (let element = 0; element < numberOfBooks; element++) {
      let result: any;
      this.book.getBook(this.listResult[element].id).toPromise().then((resp) => {
        console.log("response ", resp);
        result = resp;
        this.BookItems.push(result);
      });
    }
    console.log("BookItems", this.BookItems)
  }

  onSubmit() {
    this.cart.saveCart()
      .subscribe(resp => {
        alert("Pannier added ")
        console.log(resp);
        this.router.navigate(["app-get-all-books"])
      })
      this.cart.checkout()
  }

  getImage(imagePic: any) {
    if (imagePic != null)
      return 'data:image/jpeg;base64,' + imagePic.pic;
    else
      return "../assets/book.jpg";

  }

  getTotal() {
    this.cart.getTotal()
      .subscribe(resp => {
        this.total = resp;
        console.log("Totale = ", resp);
      })
  }

}
