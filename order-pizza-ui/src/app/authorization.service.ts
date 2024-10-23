import { Injectable } from '@angular/core';
import {UserResponse} from './response/user.response';
import {Role} from './enums/role.enum';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {
  hasRole(roles: string[]): boolean {
    let user: string | null = localStorage.getItem('currentUser');
    if (user == null) {
      return false;
    }
    let currentUser: UserResponse = JSON.parse(<string>localStorage.getItem('currentUser'));
    return roles.includes(currentUser.role)
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
