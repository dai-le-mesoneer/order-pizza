import {NgModule} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {provideHttpClient} from '@angular/common/http';
import {AppComponent} from './app.component';
import {TabComponent} from './tab/tab.component';
import {CardComponent} from './card/card.component';
import {BrowserModule} from '@angular/platform-browser';
import {RouterOutlet} from '@angular/router';
import {AppRoutingModule} from './app-routing.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LoginComponent} from './login/login.component';
import {AuthorizationDirective} from './authorization.directive';

@NgModule({
  declarations: [AppComponent],
  imports: [
    ReactiveFormsModule,
    TabComponent,
    CardComponent,
    BrowserModule,
    RouterOutlet,
    AppRoutingModule,
    BrowserAnimationsModule,
    AuthorizationDirective
  ],
  providers: [
    provideHttpClient()
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
