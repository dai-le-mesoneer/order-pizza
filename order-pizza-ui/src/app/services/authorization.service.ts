import { Injectable } from '@angular/core';
import {UserResponse} from '../response/user.response';
import {Role} from '../enums/role.enum';
import {ACCESS_TOKEN, CURRENT_USER} from '../common/constants';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {
  hasRole(roles: string[]): boolean {
    if (this.isAuthenticated()) {
      let currentUser: UserResponse = JSON.parse(<string>localStorage.getItem(CURRENT_USER));
      return roles.includes(currentUser.role)
    }
    return false;
  }

  isAuthenticated(): boolean {
    let user: string | null = localStorage.getItem(CURRENT_USER);
    return user != null;

  }

  getToken(): string | null {
    return localStorage.getItem(ACCESS_TOKEN);
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
