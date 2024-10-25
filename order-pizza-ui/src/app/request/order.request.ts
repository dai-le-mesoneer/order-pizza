import {ProductRequest} from './product.request';

export interface OrderRequest {
  customerName: string;
  phone: string;
  address: string;
  products: ProductRequest[];
}
