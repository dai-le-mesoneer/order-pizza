import {Component, OnInit, signal} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatFormField} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatIcon} from '@angular/material/icon';
import {MatButton, MatIconButton} from '@angular/material/button';
import {LoginRequest} from '../request/login.request';
import {AuthenticationService} from '../authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormField,
    MatInputModule,
    MatIcon,
    MatIconButton,
    MatButton
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  headerTitle = 'Le Pizza Store';
  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  })
  showError = signal(false)

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
  ) {
  }

  hide = signal(true);
  clickEvent(event: MouseEvent) {
    this.hide.set(!this.hide());
    event.stopPropagation();
  }

  onSubmit(): void {
    let request: LoginRequest = {
      username : this.loginForm.controls.username.value || '' ,
      password : this.loginForm.controls.password.value || ''
    }

    this.authenticationService.login(request).subscribe(
      result => {
        if (result.success && result.data) {
          localStorage.setItem('currentUser', JSON.stringify(result.data));
          this.router.navigate(['orders']).then(_ => {});
        } else {
          this.showError.set(true)
          this.loginForm.reset();
        }
      }
    )
  }

  handleFocus(): void {
    this.showError.set(false);
  }

  ngOnInit(): void {
    localStorage.clear()
  }
}
