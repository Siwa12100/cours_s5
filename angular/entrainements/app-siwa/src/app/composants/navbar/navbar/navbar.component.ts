import { Component } from '@angular/core';
import { AdiuService } from '../../../services/adiu.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  message : string = "";

  constructor(public service : AdiuService) {}

  ngOnInit(): void {
    this.message = this.service.direAdiu();    
  }
}
