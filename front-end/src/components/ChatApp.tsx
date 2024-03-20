import React, { useState, useEffect } from 'react';
import ChatList from './ChatList';
import ChatInput from './ChatInput';
import UserList from './UserList'; 
import ChatMessage from './ChatMessage';
import User from './User';

const ChatApp: React.FC = () => {
  const [chats, setChats] = useState<ChatMessage[]>([]);
  const [socket, setSocket] = useState<WebSocket | null>(null);
  const [connectedUsers, setConnectedUsers] = useState<User[]>([]); // Optional

  useEffect(() => {
    const newSocket = new WebSocket('ws://localhost:8080/chat'); // Replace with your backend WebSocket endpoint
    newSocket.onopen = () => setSocket(newSocket);
    newSocket.onmessage = (event) => {
      const message = JSON.parse(event.data);
      // Handle incoming messages (update chats, connected users)
      setChats((prevChats) => [...prevChats, message.chatMessage]); // Update chats
      setConnectedUsers(message.connectedUsers); // Update connected users (optional)
    };
    return () => newSocket.close(); // Cleanup socket on unmount
  }, []);

  const sendMessage = (message: string, chatId?: number) => {
    if (socket) {
      socket.send(JSON.stringify({ content: message, chatId }));
    }
  };

  return (
    <div className="chat-app">
      <ChatList chats={chats} />
      <ChatInput onSendMessage={sendMessage} />
      {connectedUsers.length > 0 && <UserList users={connectedUsers} />} {/* Optional */}
    </div>
  );
};

export default ChatApp;