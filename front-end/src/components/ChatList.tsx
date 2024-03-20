import React from 'react';
import ChatMessage from './ChatMessage';

interface Chat {
  id: string;
  chatId?: string; // Optional for group chat
  senderId: number;
  recipientId: number;
  content: string;
  timestamp: Date;
}

const ChatList: React.FC<{ chats: Chat[] }> = ({ chats }) => {
  return (
    <ul className="chat-list">
      {chats.map((chat) => (
        <ChatMessage key={chat.id} chat={chat} />
      ))}
    </ul>
  );
};

export default ChatList;
