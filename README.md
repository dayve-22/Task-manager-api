# Spring Boot Task Management API

This is a simple RESTful API for managing tasks, built with Spring Boot, Spring Data JPA, and H2 (in-memory) database.

---

## 🚀 Features

* **Create** a new task
* **Retrieve** a single task by its ID
* **Retrieve** all tasks
* **Update** an existing task
* **Delete** a task
* **Input validation** for all create/update operations
* **Global exception handling** for 404 (Not Found) and 400 (Bad Request) errors

---

## 🛠️ Built With

* **Java 17**
* **Spring Boot 3.x**
* **Spring Data JPA** (for database access)
* **Spring Web** (for REST endpoints)
* **Spring Validation** (for request validation)
* **H2 Database** (in-memory)
* **Lombok** (to reduce boilerplate)
* **Maven** (for dependency management)

---

## ⚙️ Getting Started

### Prerequisites

* JDK 17 or later
* Maven 3.8 or later

### 1. Setup and Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/taskmanager.git](https://github.com/your-username/taskmanager.git)
    cd taskmanager
    ```

2.  **Build the project:**
    ```bash
    mvn clean install
    ```

3.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

The application will start on `http://localhost:8080`.

### 2. Accessing the H2 Console

This project uses an in-memory H2 database. You can access its web console to view the data directly:

* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:testdb`
* **Username:** `sa`
* **Password:** `password`

---

## API Documentation

Here are the available endpoints, with examples using `curl`.

### 1. Create a Task

Creates a new task.

* **Endpoint:** `POST /api/tasks`
* **HTTP Status:** `201 Created`

**Request Body (`application/json`):**
```json
{
    "title": "Complete Spring Boot Assignment",
    "description": "Build a task management API",
    "status": "PENDING",
    "priority": "HIGH",
    "dueDate": "2024-02-15"
}

Here is the complete README.md file. You can copy this content directly into the README.md file in the root of your project.

Markdown

# Spring Boot Task Management API

This is a simple RESTful API for managing tasks, built with Spring Boot, Spring Data JPA, and H2 (in-memory) database.

---

## 🚀 Features

* **Create** a new task
* **Retrieve** a single task by its ID
* **Retrieve** all tasks
* **Update** an existing task
* **Delete** a task
* **Input validation** for all create/update operations
* **Global exception handling** for 404 (Not Found) and 400 (Bad Request) errors

---

## 🛠️ Built With

* **Java 17**
* **Spring Boot 3.x**
* **Spring Data JPA** (for database access)
* **Spring Web** (for REST endpoints)
* **Spring Validation** (for request validation)
* **H2 Database** (in-memory)
* **Lombok** (to reduce boilerplate)
* **Maven** (for dependency management)

---

## ⚙️ Getting Started

### Prerequisites

* JDK 17 or later
* Maven 3.8 or later

### 1. Setup and Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/taskmanager.git](https://github.com/your-username/taskmanager.git)
    cd taskmanager
    ```

2.  **Build the project:**
    ```bash
    mvn clean install
    ```

3.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

The application will start on `http://localhost:8080`.

### 2. Accessing the H2 Console

This project uses an in-memory H2 database. You can access its web console to view the data directly:

* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:testdb`
* **Username:** `sa`
* **Password:** `password`

---

## API Documentation

Here are the available endpoints, with examples using `curl`.

### 1. Create a Task

Creates a new task.

* **Endpoint:** `POST /api/tasks`
* **HTTP Status:** `201 Created`

**Request Body (`application/json`):**
```json
{
    "title": "Complete Spring Boot Assignment",
    "description": "Build a task management API",
    "status": "PENDING",
    "priority": "HIGH",
    "dueDate": "2024-02-15"
}
curl command:

Bash

curl -X POST http://localhost:8080/api/tasks \
-H "Content-Type: application/json" \
-d '{
    "title": "Complete Spring Boot Assignment",
    "description": "Build a task management API",
    "status": "PENDING",
    "priority": "HIGH",
    "dueDate": "2024-02-15"
}'
Success Response (201 Created):

JSON

{
    "id": 1,
    "title": "Complete Spring Boot Assignment",
    "description": "Build a task management API",
    "status": "PENDING",
    "priority": "HIGH",
    "dueDate": "2024-02-15"
}
2. Get All Tasks
Retrieves a list of all tasks.

Endpoint: GET /api/tasks

HTTP Status: 200 OK

curl command:

Bash

curl -X GET http://localhost:8080/api/tasks
Success Response (200 OK):

JSON

[
    {
        "id": 1,
        "title": "Complete Spring Boot Assignment",
        "description": "Build a task management API",
        "status": "PENDING",
        "priority": "HIGH",
        "dueDate": "2024-02-15"
    },
    {
        "id": 2,
        "title": "Push code to GitHub",
        "description": "Don't forget the README",
        "status": "PENDING",
        "priority": "MEDIUM",
        "dueDate": "2024-02-16"
    }
]
3. Get Task by ID
Retrieves a single task by its unique ID.

Endpoint: GET /api/tasks/{id}

HTTP Status: 200 OK

curl command (for task with ID 1):

Bash

curl -X GET http://localhost:8080/api/tasks/1
Success Response (200 OK):

JSON

{
    "id": 1,
    "title": "Complete Spring Boot Assignment",
    "description": "Build a task management API",
    "status": "PENDING",
    "priority": "HIGH",
    "dueDate": "2024-02-15"
}
4. Update a Task
Updates an existing task. You must provide all fields.

Endpoint: PUT /api/tasks/{id}

HTTP Status: 200 OK

Request Body (application/json):

JSON

{
    "title": "Complete and Review Assignment",
    "description": "Build a task management API and add tests",
    "status": "IN_PROGRESS",
    "priority": "HIGH",
    "dueDate": "2024-02-16"
}
curl command (to update task 1):

Bash

curl -X PUT http://localhost:8080/api/tasks/1 \
-H "Content-Type: application/json" \
-d '{
    "title": "Complete and Review Assignment",
    "description": "Build a task management API and add tests",
    "status": "IN_PROGRESS",
    "priority": "HIGH",
    "dueDate": "2024-02-16"
}'
Success Response (200 OK):

JSON

{
    "id": 1,
    "title": "Complete and Review Assignment",
    "description": "Build a task management API and add tests",
    "status": "IN_PROGRESS",
    "priority": "HIGH",
    "dueDate": "2024-02-16"
}
5. Delete a Task
Deletes a task by its ID.

Endpoint: DELETE /api/tasks/{id}

HTTP Status: 204 No Content

curl command (to delete task 1):

Bash

curl -X DELETE http://localhost:8080/api/tasks/1
Success Response: (No content is returned, just an HTTP 204 status code)

🧪 Test Cases & Error Handling
Here is how the API responds to bad requests or non-existent data, as required by the assignment.

Test Case: Validation Error (400)
Action: POST /api/tasks with a short title and missing status.

curl command:

Bash

curl -X POST http://localhost:8080/api/tasks \
-H "Content-Type: application/json" \
-d '{
    "title": "Hi",
    "priority": "LOW"
}'
Response (400 Bad Request):

JSON

{
    "status": 400,
    "errors": {
        "title": "Title must be between 3 and 100 characters",
        "status": "Status is required"
    }
}
Test Case: Get Non-existent Task (404)
Action: GET /api/tasks/999 (assuming 999 does not exist).

curl command:

Bash

curl -X GET http://localhost:8080/api/tasks/999
Response (404 Not Found):

JSON

{
    "message": "Task not found with id: 999",
    "status": 404
}
