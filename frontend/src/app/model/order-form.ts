import { Book } from "./book";
import { User } from "./user";

export class OrderForm {
    user: User = new User();
    books: Array<OrderBook> = new Array<OrderBook>();
}

export class OrderBook {
    id: number | undefined;
    quantity: number | undefined;
    

}
