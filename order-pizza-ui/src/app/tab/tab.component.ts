import {Component, OnInit} from '@angular/core';
import {MatTabsModule} from '@angular/material/tabs';
import {NgFor} from '@angular/common';
import {CardComponent} from '../card/card.component';
import {Product} from '../card/product';
import {ProductService} from '../product.service';
import {OrderRequest} from '../card/order.request';
import {ProductRequest} from '../card/product.request';

@Component({
  selector: 'app-tab',
  standalone: true,
  imports: [
    MatTabsModule,
    NgFor,
    CardComponent
  ],
  templateUrl: './tab.component.html',
  styleUrl: './tab.component.css'
})
export class TabComponent implements OnInit {
  data!: Product[];
  pizzas!: Product[];
  drinks!: Product[];
  request!: OrderRequest;
  constructor(private productService: ProductService) {}

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.productService.listProduct().subscribe(
      res => {
        if (res.success) {
          this.data = res.data.items
          this.pizzas = this.data.filter(p => p.type === 'PIZZA');
          this.drinks = this.data.filter(p => p.type === 'DRINK');
          this.request = {
            address: '',
            customerName: '',
            phone: '',
            products : this.data.map(i => this.map(i))
          }
        }
      }
    )
  }

  private map(product: Product): ProductRequest {
    return {
      id: product.id,
      quantity: 0
    }
  }

  createDefaultProductRequest(product: Product): ProductRequest {
   return this.request.products.filter(p => p.id === product.id)[0]
  }

  getAllProductSelected(): ProductRequest[] {
    return this.request != undefined ? this.request.products.filter(p => p.quantity > 0) : [];
  }

  getProductFromProductId(productId: number): Product {
    return this.data.filter(p => p.id === productId)[0];
  }

  headerTitle = 'Le Pizza Store';
}
