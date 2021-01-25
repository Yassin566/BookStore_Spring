import { OrderItem } from "./order-item";
import { User } from "./user";

export class Order {

    id: number = 0;
    date: Date | undefined;
    orderItems: Array<OrderItem> = [];
    user: User = new User();
    totalAmount: number = 0;

}
