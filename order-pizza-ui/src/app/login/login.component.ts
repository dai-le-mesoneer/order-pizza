import {Component, OnInit, signal} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatFormField} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatIcon} from '@angular/material/icon';
import {MatButton, MatIconButton} from '@angular/material/button';
import {LoginRequest} from '../request/login.request';
import {AuthenticationService} from '../services/authentication.service';
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
  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  })
  showError = signal(false)
  hide = signal(true);

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
  ) {
  }


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
        console.log(result);
        if (result.success && result.data) {
          localStorage.setItem('current_user', JSON.stringify(result.data));
          localStorage.setItem('access_token', result.data.accessToken)
          localStorage.setItem('role', result.data.role)
          this.router.navigate(['orders']).then(_ => {});
        }
      },
      error => {
        if (error.status === 401) {
          this.showError.set(true)
          this.loginForm.reset();
        } else {
          console.error('An unexpected error occurred.');
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
