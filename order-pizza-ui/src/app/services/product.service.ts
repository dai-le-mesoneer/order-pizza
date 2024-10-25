import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ResponseDTO} from '../response/response';
import {List} from '../response/list';
import {Product} from '../request/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

    private apiUrl = 'http://localhost:8080/public/products';

    constructor(private http: HttpClient) { }

  listProduct(): Observable<ResponseDTO<List<Product>>> {
    return this.http.get<ResponseDTO<List<Product>>>(this.apiUrl);
  }
}
