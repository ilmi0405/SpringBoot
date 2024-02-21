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

## API Endpoints
### 1. User Registration
- **URL:** `/api/users`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "email": "example@example.com",
    "password": "password123"
  }
  ```
- **Response:**
  - HTTP Status: `201 Created`
  - Response Body:
    ```json
    {
      "id": 1,
      "password": "hashed",
      "email": "example@example.com"
    }
    ```

### 2. User Authentication
- **URL:** `/api/users/authenticate`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "email": "example@example.com",
    "password": "password123"
  }
  ```
- **Response:**
  - HTTP Status: `200 OK`
  - Response Body:
    ```json
    {
      "Authentication successful"
    }
    ```

### 3. Users Retrieval
- **URL:** `/api/users`
- **Method:** `GET`
- **Response:**
  - HTTP Status: `200 OK`
  - Response Body:
    ```json
    {
      "id": 1,
      "password": "hash",
      "email": "example@example.com"
    }
    ```

### 4. Password Update
- **URL:** `/api/users/{email}`
- **Method:** `PATCH`
- **Request Body:**
  ```json
  {
    "oldPassword": "oldpassword123",
    "newPassword": "newpassword123"
  }
  ```
- **Response:**
  - HTTP Status: `200 OK`
  - Response Body:
    ```json
    {
      "Password updated successfully"
    }
    ```
    
### 5. User Update
- **URL:** `/api/users/{email}`
- **Method:** `PUT`
- **Request Body:**
  ```json
  {
    "oldPassword": "oldpassword123",
    "newEmail":"newEmail@hotmail.com",
    "newPassword": "newpassword123"
  }
  ```
- **Response:**
  - HTTP Status: `200 OK`
  - Response Body:
    ```json
    {
      "User updated successfully"
    }
    ```

### 6. User Deletion
- **URL:** `/api/users/{email}`
- **Method:** `DELETE`
- **Request Body:**
  ```json
  {
    "password":"123"
  }
  ```
- **Response:**
  - HTTP Status: `200 OK`
  - Response Body:
    ```json
    {
      "User deleted successfully"
    }
    ```
    
### 7. Domain count 
- **URL:** `/api/users/domainCounts`
- **Method:** `GET`
- **Response:**
  - HTTP Status: `200 OK`
  - Response Body:
    ```json
    {
      "gmail.com": 2,
      "hotmail.com": 1
    }
    ```

## Note
I have removed authentication for request so there is no need for username or password
The response form the requests will depend if you are providing correct password or not, or if you are trying to create existing users
