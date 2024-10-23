import { Injectable } from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent} from '@angular/common/http';
import { Observable} from 'rxjs';
import {AuthorizationService} from '../services/authorization.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthorizationService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("aa")
    const accessToken = this.authService.getToken();
    const authReq = accessToken ? req.clone({
      setHeaders: {
        Authorization: `Bearer ${accessToken}`
      }
    }) : req;

    return next.handle(authReq);
  }
}
