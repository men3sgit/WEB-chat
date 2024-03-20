import React from 'react';

interface User {
  id: number; // Replace with appropriate user ID type (number, string)
  username: string;
  // Optional properties
  firstName?: string;
  lastName?: string;
  profileImageUrl?: string;
  // Add more properties as needed (e.g., online status)
}


const UserList: React.FC<{ users: User[] }> = ({ users }) => {
  return (
    <ul className="user-list">
      {users.map((user) => (
        <User key={user.id} user={user} />
      ))}
    </ul>
  );
};

export default UserList;
