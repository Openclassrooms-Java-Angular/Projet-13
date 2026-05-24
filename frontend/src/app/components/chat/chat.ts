import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IMessage } from '@stomp/stompjs';
import { ChatApiService } from '../../services/chat-api.service';
import { ChatWebsocketService } from '../../services/chat-websocket.service';
import { MessageResponse } from '../../dto/message-response';
import { UserResponse } from '../../dto/user-response';

@Component({
  selector: 'app-chat',
  imports: [CommonModule, FormsModule],
  templateUrl: './chat.html',
  styleUrl: './chat.css',
})
export class ChatComponent implements OnInit, OnDestroy {
  private apiService = inject(ChatApiService);
  private websocketService = inject(ChatWebsocketService);

  conversationId = 1;
  senderId = 1;
  content = '';
  messages: MessageResponse[] = [];
  users: UserResponse[] = [];

  ngOnInit(): void {
    this.loadUsers();
    this.loadMessages();

    this.websocketService.connect(() => {
      this.websocketService.subscribe(
        `/topic/conversations/${this.conversationId}`,
        (message: IMessage) => {
          const body = JSON.parse(message.body) as MessageResponse;
          this.messages.push(body);
        }
      );
    });
  }

  loadUsers(): void {
    this.apiService.getUsers().subscribe({
      next: (users) => {
        this.users = users;
        if (users.length > 0 && !users.find(u => u.id === this.senderId)) {
          this.senderId = users[0].id;
        }
      },
      error: (error) => {
        console.error('Erreur chargement utilisateurs', error);
      }
    });
  }

  loadMessages(): void {
    this.apiService.getMessages(this.conversationId).subscribe({
      next: (messages) => {
        this.messages = messages;
      },
      error: (error) => {
        console.error('Erreur chargement messages', error);
      }
    });
  }

  sendMessage(): void {
    const trimmed = this.content.trim();
    if (!trimmed) {
      return;
    }

    this.websocketService.send('/app/chat.send', {
      conversationId: this.conversationId,
      senderId: this.senderId,
      content: trimmed
    });

    this.content = '';
  }

  getUserLabel(senderId: number): string {
    const user = this.users.find(u => u.id === senderId);
    return user ? `${user.firstName} ${user.lastName}` : `User ${senderId}`;
  }

  ngOnDestroy(): void {
    this.websocketService.disconnect();
  }
}