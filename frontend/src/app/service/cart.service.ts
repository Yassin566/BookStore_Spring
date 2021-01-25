import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../model/book';
import { Order } from '../model/order';
import { OrderBook, OrderForm } from '../model/order-form';
import { User } from '../model/user';
import { AuthService } from './auth.service';
import { BookServiceService } from './book-service.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  hostUrl = "http://127.0.0.1:8080/orders";
  getTotalUrl = "http://127.0.0.1:8080/getTotal"

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'my-auth-token'
    })
  };
  books: number[] = [];
  quantity: number[] = [];
  userTest = new User();
  orderFrom = new OrderForm();
  order = new OrderBook();
  orderList: OrderBook[] = new Array<OrderBook>();



  constructor(public bookService: BookServiceService, public http: HttpClient, public auth: AuthService) { }

  getAllBooks(): number[] {
    return JSON.parse(JSON.stringify(localStorage.getItem('books')).replace('[', '').replace(']', '')).split(",");
  }

  getAllQuantites(): number[] {
    return JSON.parse(JSON.stringify(localStorage.getItem('quantity')).replace('[', '').replace(']', '')).split(",");
  }

  getAllOrders(): OrderBook[] {
    return JSON.parse(JSON.stringify(localStorage.getItem('order')).replace('[', '').replace(']', '')).split(",");
  }
  checkout(){
    localStorage.removeItem('quantity');
    localStorage.removeItem('books');
    localStorage.removeItem('order');
  }

  getOrders(): OrderBook[] {
    let orders: OrderBook[] = new Array<OrderBook>();
    for (let i = 0; i < this.getAllBooks().length; i++) {
      let order: OrderBook = new OrderBook();
      order.id = this.getAllBooks()[i];
      order.quantity = this.getAllQuantites()[i];
      orders.push(order)
    }
    return orders;
  }


  addBooks(id: number, quantity: number) {
    this.order.quantity = quantity;
    this.order.id = id;
    this.orderList.push(this.order);
    console.log(this.orderFrom);
    this.books.push(id);
    this.quantity.push(quantity);
    localStorage.setItem('books', JSON.stringify(this.books));
    localStorage.setItem('quantity', JSON.stringify(this.quantity));
    localStorage.setItem('order', JSON.stringify(this.orderList));

  }
  saveCart() {
    this.orderFrom.user.id = this.auth.getLoggedInId();
    console.log(this.orderFrom.user)
    this.orderFrom.books = this.getOrders();
    console.log(this.orderFrom);
    return this.http.post(this.hostUrl, this.orderFrom, this.httpOptions);


  }

  getTotal() {
    this.orderFrom.user.id = this.auth.getLoggedInId();
    console.log(this.orderFrom.user)
    this.orderFrom.books = this.getOrders();
    console.log(this.orderFrom);
    return this.http.post(this.getTotalUrl, this.orderFrom, this.httpOptions);
  }


}
