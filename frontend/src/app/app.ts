import { Component } from '@angular/core';
import { ChatComponent } from './components/chat/chat';

@Component({
  selector: 'app-root',
  imports: [ChatComponent],
  template: `<app-chat></app-chat>`
})
export class AppComponent {
}