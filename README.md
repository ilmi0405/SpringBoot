# SpringBoot

### Technologies Used
- Spring Boot
- Lombok
- Spring Data JPA
- Spring Security
- H2
- Postman (for API testing)

### Functionality
The User Management API offers the following functionality:

- User Registration: Allows users to create new accounts by providing their email and password.
- User Authentication: Supports authentication using email and password.
- User Update: Allows users to update their email and password.
- User Deletion: Supports deletion of user accounts.
- Domain occurrences counting

### Thought Process
- Security: Implemented authentication and password hashing using Spring Security to ensure secure user management.
- Error Handling: Incorporated exception handling to provide meaningful error messages for various scenarios, such as invalid credentials and duplicate email addresses.
- Response Structure: Designed API responses to include appropriate HTTP status codes and response bodies for better client interaction and error handling.

### Usage
To use the User Management API:

1. Start the application.
2. Access the API endpoints using tools like Postman.
3. Follow the endpoint documentation provided below for specific API usage instructions.

### API Endpoints
1. User Registration
- URL: /api/users
- Method: POST
- Request Body:

    {
  "email": "example@example.com",
  "password": "password123"
}

- Response:
    HTTP Status: 201 Created
      Response Body:
          {
          "id": 1,
          "email": "example@example.com"
        }
