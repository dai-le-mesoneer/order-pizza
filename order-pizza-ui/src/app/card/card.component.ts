import {Component, Input} from '@angular/core';
import {Product} from './product';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {NgOptimizedImage} from '@angular/common';
import {ProductRequest} from './product.request';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [MatCardModule, MatButtonModule, NgOptimizedImage],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent {
  @Input() product!: Product;
  @Input() productRequest!: ProductRequest;
  increment() {
    this.productRequest.quantity++

  }
  decrement() {
    if (this.productRequest.quantity > 0) {
      this.productRequest.quantity--
    }
  }
}
