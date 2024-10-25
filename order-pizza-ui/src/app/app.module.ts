import {NgModule} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi} from '@angular/common/http';
import {AppComponent} from './app.component';
import {TabComponent} from './components/tab/tab.component';
import {CardComponent} from './components/card/card.component';
import {BrowserModule} from '@angular/platform-browser';
import {RouterOutlet} from '@angular/router';
import {AppRoutingModule} from './app-routing.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LoginComponent} from './components/login/login.component';
import {AuthorizationDirective} from './directives/authorization.directive';
import {AuthInterceptor} from './interceptors/auth';

@NgModule({
  declarations: [AppComponent],
  imports: [
    ReactiveFormsModule,
    TabComponent,
    CardComponent,
    BrowserModule,
    LoginComponent,
    RouterOutlet,
    AppRoutingModule,
    BrowserAnimationsModule,
    AuthorizationDirective,
  ],
  providers: [
    provideHttpClient(
      withInterceptorsFromDi()
    ),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true  // Allows multiple interceptors if needed
    }

  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
