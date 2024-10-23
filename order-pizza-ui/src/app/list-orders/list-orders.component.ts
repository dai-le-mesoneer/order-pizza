import {Component, OnInit} from '@angular/core';
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef,
  MatTable
} from '@angular/material/table';
import {MatButton} from '@angular/material/button';
import {OrderDTO} from '../response/order.response';
import {NgForOf} from '@angular/common';
import {OrderService} from '../order.service';
import {AuthorizationDirective} from '../authorization.directive';
import {AuthorizationService} from '../authorization.service';
import {ListOrdersRequest} from '../request/list-orders.request';
import {OrderStatus} from '../enums/order-status.enum';

@Component({
  selector: 'app-list-orders',
  standalone: true,
  imports: [
    MatTable,
    MatHeaderCell,
    MatColumnDef,
    MatCell,
    MatHeaderRow,
    MatCellDef,
    MatHeaderCellDef,
    MatHeaderRowDef,
    MatRowDef,
    MatRow,
    MatButton,
    NgForOf,
    AuthorizationDirective
  ],
  templateUrl: './list-orders.component.html',
  styleUrl: './list-orders.component.css'
})
export class ListOrdersComponent implements OnInit{
  displayedColumns: string[] = ['orderId', 'createdDate', 'items', 'name', "phoneNumber", "price", "actions"];
  dataSource!: OrderDTO[];

  constructor(
    private orderService: OrderService,
    private authorizationService: AuthorizationService,
  ) { }

  confirm(element: OrderDTO) {
    this.orderService.changeOrderStatus(element.orderId, OrderStatus.CONFIRMED).subscribe(
      result => {
        if (result.success) {
          this.loadOrder()
        }
      }
    )
  }

  cancel(element: OrderDTO) {
    this.orderService.changeOrderStatus(element.orderId, OrderStatus.CANCELLED).subscribe(
      result => {
        if (result.success) {
          this.loadOrder()
        }
      }
    )
  }

  ngOnInit(): void {
    this.loadOrder()
  }

  chefDone(element: OrderDTO) {
    this.orderService.changeOrderStatus(element.orderId, OrderStatus.COMPLETED).subscribe(
      result => {
        if (result.success) {
          this.loadOrder()
        }
      }
    )
  }

  delivered(element: OrderDTO) {
    this.orderService.changeOrderStatus(element.orderId, OrderStatus.DELIVERED).subscribe(
      result => {
        if (result.success) {
          this.loadOrder()
        }
      }
    )
  }

  private createListOrderRequest(): ListOrdersRequest {
    const status = this.getOrderStatus();
    return { status: status };
  }

  private getOrderStatus(): OrderStatus {
    if (this.authorizationService.isReceptionist()) {
      return OrderStatus.PENDING;
    }
    if (this.authorizationService.isChef()) {
      return OrderStatus.CONFIRMED;
    }
    return OrderStatus.COMPLETED; // Default case
  }

  private loadOrder(): void {
    this.orderService.listOrders(this.createListOrderRequest()).subscribe(
      response => {
        if (response.success) {
          this.dataSource = response.data.items;
        }
      }
    )
  }
}
