import {OrderItem} from './order-item.response';

export interface OrderDTO {
  orderId: number,
  createdDate: string,
  name: string,
  phoneNumber: string,
  price: number,
  items: OrderItem[],
}
