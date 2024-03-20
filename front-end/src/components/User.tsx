interface User {
    id: number; // Replace with appropriate user ID type (number, string)
    username: string;
    // Optional properties
    firstName?: string;
    lastName?: string;
    profileImageUrl?: string;
    // Add more properties as needed (e.g., online status)
  }
  export default User;