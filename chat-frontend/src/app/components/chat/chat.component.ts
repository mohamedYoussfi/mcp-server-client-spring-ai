import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ChatService, ChatMessage } from '../../services/chat.service';
import { marked } from 'marked';

@Component({
  selector: 'app-chat',
  imports: [CommonModule, FormsModule],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent {
  messages: ChatMessage[] = [];
  newMessage: string = '';
  isLoading: boolean = false;
  isTyping: boolean = false;

  constructor(private chatService: ChatService) {
    this.messages.push({
      content: 'Hello! I\'m your AI assistant. How can I help you today?',
      isUser: false,
      timestamp: new Date()
    });
  }

  sendMessage(): void {
    if (!this.newMessage.trim() || this.isLoading) {
      return;
    }

    const userMessage: ChatMessage = {
      content: this.newMessage,
      isUser: true,
      timestamp: new Date()
    };

    this.messages.push(userMessage);
    const messageToSend = this.newMessage;
    this.newMessage = '';
    this.isLoading = true;
    this.isTyping = true;

    this.chatService.sendMessage(messageToSend).subscribe({
      next: (response) => {
        this.isTyping = false;
        const aiMessage: ChatMessage = {
          content: response,
          isUser: false,
          timestamp: new Date()
        };
        this.messages.push(aiMessage);
        this.isLoading = false;
      },
      error: (error) => {
        this.isTyping = false;
        console.error('Error:', error);
        const errorMessage: ChatMessage = {
          content: 'Sorry, there was an error processing your request. Please try again.',
          isUser: false,
          timestamp: new Date()
        };
        this.messages.push(errorMessage);
        this.isLoading = false;
      }
    });
  }

  onKeyDown(event: KeyboardEvent): void {
    if (event.key === 'Enter' && !event.shiftKey) {
      event.preventDefault();
      this.sendMessage();
    }
  }

  parseMarkdown(content: string): string {
    return marked.parse(content) as string;
  }
}