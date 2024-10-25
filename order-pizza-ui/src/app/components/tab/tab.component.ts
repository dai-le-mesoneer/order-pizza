import {Component, OnInit, signal} from '@angular/core';
import {MatTabsModule} from '@angular/material/tabs';
import {NgFor, NgIf} from '@angular/common';
import {CardComponent} from '../card/card.component';
import {Product} from '../../request/product';
import {ProductService} from '../../services/product.service';
import {OrderRequest} from '../../request/order.request';
import {ProductRequest} from '../../request/product.request';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatFormField} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
import {OrderService} from '../../services/order.service';

@Component({
  selector: 'app-tab',
  standalone: true,
  imports: [
    MatTabsModule,
    NgFor,
    CardComponent,
    ReactiveFormsModule,
    MatFormField,
    MatInputModule,
    MatButton,
    NgIf
  ],
  templateUrl: './tab.component.html',
  styleUrl: './tab.component.css'
})
export class TabComponent implements OnInit {
  informationForm = new FormGroup({
    customerName: new FormControl('', Validators.required),
    phoneNumber: new FormControl('', [Validators.required, Validators.pattern('^[- +()0-9]+$')]),
    address: new FormControl('', Validators.required),
    products: new FormControl(''),
  });
  data!: Product[];
  pizzas!: Product[];
  drinks!: Product[];
  request!: OrderRequest;
  selectedTabIndex = 0;
  nameError = signal('');
  phoneError = signal('');
  addressError = signal('');

  constructor(
    private productService: ProductService,
    private orderService: OrderService
  ) {}

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
          this.request = this.createDefaultOrder()
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

  onSubmit(){
      this.request.customerName = this.informationForm.controls.customerName.value || '';
      this.request.address = this.informationForm.controls.address.value || '';
      this.request.phone = this.informationForm.controls.phoneNumber.value || '';
      this.request.products = this.getAllProductSelected();
      this.orderService.createOrder(this.request).subscribe(
        res => {
          if (res.success) {
            this.request = this.createDefaultOrder()
            this.resetForm()
            alert("Create order successfully");
          } else {
            alert("Failed to create order");
          }
        }
      );
  }

  validateName() {
    if (this.informationForm.controls.customerName.hasError('required')) {
      this.nameError.set('You must enter name')
    } else {
      this.nameError.set('')
    }
  }

  validatePhoneNumber() {
    if (this.informationForm.controls.phoneNumber.hasError('required')) {
      this.phoneError.set('You must enter phone number')
    } else if (this.informationForm.controls.phoneNumber.hasError('pattern')) {
      this.phoneError.set('Your phone number invalid')
    } else {
      this.phoneError.set('')
    }
  }

  validateAddress() {
    if (this.informationForm.controls.address.hasError('required')) {
      this.addressError.set('You must enter address')
    } else {
      this.addressError.set('')
    }
  }

  private createDefaultOrder(): OrderRequest {
    return {
      address: '',
      customerName: '',
      phone: '',
      products : this.data.map(i => this.map(i))
    }

  }

  private resetForm(): void {
    this.informationForm.reset();
    Object.keys(this.informationForm.controls).forEach(key => {
      this.informationForm.get(key)?.setErrors(null) ;
    });
    this.selectedTabIndex = 0
  }
}
