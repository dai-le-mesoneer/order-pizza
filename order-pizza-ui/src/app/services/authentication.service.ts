import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {LoginRequest} from '../request/login.request';
import {Observable} from 'rxjs';
import {ResponseDTO} from '../response/response';
import {UserResponse} from '../response/user.response';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private apiUrl = 'http://localhost:8080/login';

  constructor(private http: HttpClient) { }

  login(request: LoginRequest): Observable<ResponseDTO<UserResponse>> {
    let body = new URLSearchParams();
    body.set('username', request.username);
    body.set('password', request.password);

    let options = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
    };
    return this.http.post<ResponseDTO<UserResponse>>(this.apiUrl, body.toString(), options);
  }
}
