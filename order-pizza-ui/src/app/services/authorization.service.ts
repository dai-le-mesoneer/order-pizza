import { Injectable } from '@angular/core';
import {UserResponse} from '../response/user.response';
import {Role} from '../enums/role.enum';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {
  hasRole(roles: string[]): boolean {
    if (this.isAuthenticated()) {
      let currentUser: UserResponse = JSON.parse(<string>localStorage.getItem('current_user'));
      return roles.includes(currentUser.role)
    }
    return false;
  }

  isAuthenticated(): boolean {
    let user: string | null = localStorage.getItem('current_user');
    return user != null;

  }

  getToken(): string | null {
    return localStorage.getItem("access_token");
  }

  isReceptionist(): boolean {
    return this.hasRole([Role.RECEPTIONIST.toString()])
  }

  isChef(): boolean {
    return this.hasRole([Role.CHEF.toString()])
  }

  isDelivery(): boolean {
    return this.hasRole([Role.DELIVERY.toString()])
  }
}
