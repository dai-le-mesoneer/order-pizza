import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {OrderRequest} from '../components/card/order.request';
import {Observable} from 'rxjs';
import {ResponseDTO} from '../response/response';
import {IDResponse} from '../response/id.response';
import {List} from '../response/list';
import {OrderDTO} from '../response/order.response';
import {ListOrdersRequest} from '../request/list-orders.request';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private apiUrl = 'http://localhost:8080/orders';

  constructor(private http: HttpClient) { }

  createOrder(request: OrderRequest): Observable<ResponseDTO<IDResponse>> {
    return this.http.post<ResponseDTO<IDResponse>>("http://localhost:8080/public/orders", request);
  }

  listOrders(request: ListOrdersRequest): Observable<ResponseDTO<List<OrderDTO>>> {
    return this.http.get<ResponseDTO<List<OrderDTO>>>(this.apiUrl, {params: {...request}});
  }

  changeOrderStatus(orderId: number, status: number): Observable<ResponseDTO<IDResponse>> {
    return this.http.put<ResponseDTO<IDResponse>>(`${this.apiUrl}/${orderId}`, {status: status});
  }
}
