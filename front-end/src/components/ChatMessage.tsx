
interface ChatMessage {
  id: number; // Custom type alias for ID
  chatId?: string;
  senderId: number;
  recipientId: number;
  content: string;
  timestamp: Date;
}

export default ChatMessage;