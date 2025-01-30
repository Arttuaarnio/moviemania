# MovieMania Backend

This is the backend for **MovieMania**, a community where users can register, log in, and manage a shared list of favorite movies. 

## Features

- User authentication and registration with JWT-based authentication.
- Ability to view a list of movies and add new movies.
- CORS configuration to allow frontend communication from a specific domain.
- Security configuration with Spring Security for JWT validation.
- Simple movie model with title, release year, description, and photo URL (TMDb).

## Technologies Used

- **Spring Boot** - For building the backend application.
- **Spring Security** - For user authentication and authorization.
- **JWT** - For managing user sessions securely.
- **JPA** - For interacting with the database using entities like `AppUser` and `Movie`.
- **PostgreSQL** - To store user and movie data.

