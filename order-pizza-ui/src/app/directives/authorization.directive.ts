import {Directive, Input, TemplateRef, ViewContainerRef} from '@angular/core';
import {AuthorizationService} from '../services/authorization.service';

@Directive({
  selector: '[authorization]',
  standalone: true
})
export class AuthorizationDirective {
  private hasView = false;

  @Input() set authorization(roles: string[]) {
    this.updateView(roles);
  }

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef,
    private authorizationService: AuthorizationService,
  ) { }

  private updateView(roles: string[]): void {
    if (this.authorizationService.hasRole(roles) && !this.hasView) {
      this.viewContainer.createEmbeddedView(this.templateRef);
      this.hasView = true;
    } else if (!this.authorizationService.hasRole(roles) && this.hasView) {
      this.viewContainer.clear();
      this.hasView = false;
    }
  }
}
