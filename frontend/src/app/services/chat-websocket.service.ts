import { Injectable } from '@angular/core';
import { Client, IMessage } from '@stomp/stompjs';

@Injectable({
  providedIn: 'root'
})
export class ChatWebsocketService {
  private client: Client | null = null;

  connect(onConnected?: () => void): void {
    if (this.client?.active) {
      return;
    }

    this.client = new Client({
      brokerURL: 'ws://localhost:8080/ws',
      reconnectDelay: 5000,
      debug: (str) => console.log(str)
    });

    this.client.onConnect = () => {
      console.log('WebSocket connecté');
      if (onConnected) {
        onConnected();
      }
    };

    this.client.onStompError = (frame) => {
      console.error('Erreur STOMP', frame);
    };

    this.client.activate();
  }

  subscribe(topic: string, callback: (message: IMessage) => void): void {
    if (!this.client || !this.client.connected) {
      return;
    }

    this.client.subscribe(topic, callback);
  }

  send(destination: string, body: unknown): void {
    if (!this.client || !this.client.connected) {
      return;
    }

    this.client.publish({
      destination,
      body: JSON.stringify(body)
    });
  }

  disconnect(): void {
    this.client?.deactivate();
    this.client = null;
  }
}
