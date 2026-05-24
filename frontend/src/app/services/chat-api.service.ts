import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MessageResponse } from '../dto/message-response';
import { UserResponse } from '../dto/user-response';

@Injectable({
  providedIn: 'root'
})
export class ChatApiService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api';

  getMessages(conversationId: number): Observable<MessageResponse[]> {
    return this.http.get<MessageResponse[]>(`${this.apiUrl}/conversations/${conversationId}/messages`);
  }

  getUsers(): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(`${this.apiUrl}/users`);
  }
}
