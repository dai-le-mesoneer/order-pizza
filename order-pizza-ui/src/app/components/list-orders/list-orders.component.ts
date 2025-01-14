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
import {OrderDTO} from '../../response/order.response';
import {NgForOf} from '@angular/common';
import {OrderService} from '../../services/order.service';
import {AuthorizationDirective} from '../../directives/authorization.directive';
import {AuthorizationService} from '../../services/authorization.service';
import {OrderStatus} from '../../enums/order-status.enum';
import {Role} from '../../enums/role.enum';
import {Router} from '@angular/router';
import {MatFormField} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {FormControl, ReactiveFormsModule} from '@angular/forms';
import {debounceTime} from 'rxjs';

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
    AuthorizationDirective,
    MatFormField,
    MatInputModule,
    ReactiveFormsModule
  ],
  templateUrl: './list-orders.component.html',
  styleUrl: './list-orders.component.css'
})
export class ListOrdersComponent implements OnInit{
  displayedColumns: string[] = ['orderId', 'createdDate', 'items', 'name', "phoneNumber", "price", "actions"];
  dataSource!: OrderDTO[];
  searchTerm: FormControl = new FormControl('');

  constructor(
    private orderService: OrderService,
    private authorizationService: AuthorizationService,
    private router: Router,
  ) {
    this.searchTerm.valueChanges
      .pipe(
        debounceTime(400)
      )
      .subscribe(_ => {
        this.loadOrder()
      })
  }

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
    if (!this.authorizationService.isAuthenticated()) {
      this.router.navigate(['access-denied']).then(_ => {})
    }
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

  private loadOrder(): void {
    this.orderService.listOrders({searchTerm: this.searchTerm.value}).subscribe(
      response => {
        if (response.success) {
          this.dataSource = response.data.items;
        }
      }
    )
  }

  protected readonly Role = Role;
}
