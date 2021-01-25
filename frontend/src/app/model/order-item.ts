import { Book } from "./book";
import { Order } from "./order";

export class OrderItem {
    id: any;
    book: Book = new Book();
    quantity: number = 0;
    price: number = 0;
    order: Order = new Order();
}
