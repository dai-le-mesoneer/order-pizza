import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LoginRequest} from './request/login.request';
import {Observable} from 'rxjs';
import {ResponseDTO} from './response/response';
import {UserResponse} from './response/user.response';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private apiUrl = 'http://localhost:8080/login';

  constructor(private http: HttpClient) { }

  login(request: LoginRequest): Observable<ResponseDTO<UserResponse>> {
    return this.http.post<ResponseDTO<UserResponse>>(this.apiUrl, request);
  }
}
