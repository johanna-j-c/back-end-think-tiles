# Welcome to Think Tiles

## About the Application
Think Tiles is a intuitive web application 
designed to expand math education in digital 
spaces. My platform brings together the power 
of visual learning and interactive problem-solving, 
enabling educators to create engaging "think tiles" 
that foster a deeper understanding of mathematical concepts.

My web application enables teachers to save and 
organize their unique "think tiles" directly to 
their profiles. This feature facilitates easy 
access and promotes the reuse of meticulously 
designed workspaces, streamlining the lesson 
planning process and enhancing overall teaching 
efficiency.

## About the Backend

The Think Tiles backend is developed using Java 
and Spring Boot, offering a robust foundation 
for managing data, handling requests, and 
interacting with the frontend. The backend 
provides essential CRUD routes to create, read, 
update, and delete "think tiles" – enabling 
seamless interaction between educators and 
their curated workspaces.

## Dependencies

The Think Tiles backend utilizes the following key dependencies:

- **Spring Boot Web:** Framework for building web applications.
- **Spring Boot Test:** Support for testing Spring Boot applications.
- **PostgreSQL Driver:** Connector for PostgreSQL databases.
- **Spring Boot Data JPA:** Simplified data access using the Java Persistence API.
- **Spring Boot:** Core Spring Boot features.


## Installation

Follow these steps to set up the Think Tiles backend on your local machine:

1. Clone this repository.
2. Navigate to the project directory: `cd think-tiles-backend`
3. Install dependencies: `mvn install`

## Usage

To run the backend server:

1. Ensure you have a local PostgreSQL server running.
2. Configure the database connection in `src/main/resources/application.yml`.
3. Run the application: `mvn spring-boot:run`

## API Endpoints

The Think Tiles backend exposes the following API endpoints:

Teacher Controller
- `GET /teachers`: Retrieve a list of all teachers.
- `GET /teachers/{teacherId}`: Retrieve a specific teacher by their ID.
- `POST /teachers`: Add a new teacher.
- `PUT /teachers/{teacherId}`: Update an existing teacher's information.
- `DELETE /teachers/{teacherId}`: Delete a teacher by their ID.

Question Controller
- `GET /teachers/{teacherId}/questions`: Retrieve questions for a specific teacher.
- `GET /questions/{id}`: Retrieve a specific question by its ID.
- `POST /teachers/{teacherId}/questions`: Create a new question for a teacher.
- `PUT /questions/{id}`: Update an existing question's information.
- `DELETE /questions/{questionId}`: Delete a question by its ID.

Tile Controller
- `GET /questions/{questionId}/tiles`: Retrieve tiles for a specific question.
- `POST /questions/{questionId}/tiles`: Create a new tile for a question.
- `PUT /tiles/{id}`: Update an existing tile's information.
- `DELETE /tiles/{tileId}`: Delete a tile by its ID.
