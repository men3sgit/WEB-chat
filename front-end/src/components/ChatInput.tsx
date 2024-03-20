import React from 'react';
import styled from '@emotion/styled';

const StyledInput = styled.input`
  // Add styles for the chat input field
`;

interface SendMessage {
  onSendMessage: (message: string, chatId?: number) => void;
}

const ChatInput: React.FC<SendMessage> = ({ onSendMessage }) => {
  const [message, setMessage] = useState('');
  const [chatId, setChatId] = useState<number | null>(null); // Optional for group chat

  const handleSendMessage = () => {
    onSendMessage(message, chatId); // Include chatId if applicable
    setMessage('');
  };

  return (
    <div className="chat-input">
      <StyledInput
        value={message}
        onChange={(e) => setMessage(e.target.value)}
        onKeyPress={(e) => {
          if (e.key === 'Enter') {
            handleSendMessage();
          }
        }}
      />
      {/* Optional: Add button or functionality to select chat ID for group chat */}
    </div>
  );
};

export default ChatInput;
