DevConnect Backend
==================

- **DevConnect** is a full-featured backend for a collaborative project management platform. It provides APIs to manage **users**, **projects**, and **project collaborators**, built using **Spring Boot** and **PostgreSQL**.

* * * * *

Table of Contents
-----------------

-   [Features](#features)

-   [Tech Stack](#tech-stack)

-   [Getting Started](#getting-started)

-   [API Endpoints](#api-endpoints)

-   [Database Configuration](#database-configuration)

-   [Docker Setup](#docker-setup)

-   [Environment Variables](#environment-variables)

-   [Contributing](#contributing)

-   [License](#license)

* * * * *

Features
--------

-   CRUD operations for **Users**

-   CRUD operations for **Projects**

-   Add or remove collaborators from a project

-   Validation to prevent duplicate users or collaborators

-   Clean and consistent API response structure

* * * * *

Tech Stack
----------

-   **Backend:** Java, Spring Boot

-   **Database:** PostgreSQL

-   **Build Tool:** Maven

-   **Containerization:** Docker

-   **Version Control:** Git / GitHub

* * * * *

Getting Started
---------------

### Prerequisites

-   Java 21 installed

-   Maven

-   Docker (optional, for containerization)

  -   PostgreSQL database (can use cloud service like Aiven `https://aiven.io`)

## Running Locally

### 1.  Clone the repository:

- `git clone https://github.com/mr-dev-prince/devconnect.git`
- `cd devconnect`

### 2. Configure environment variables in `.env.local` or `application.properties`:

- 1. `DB_URL=jdbc:<url>`
- 2. `DB_USER=<username>`
- 3. `DB_PASSWORD=<password>`

### 3. Build the project:

`mvn clean install`

### 4. Run the application:

`mvn spring-boot:run`

* * * * *

API Endpoints
-------------

### Users

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/users` | Fetch all users |
| GET | `/users/{id}` | Fetch user by ID |
| POST | `/users` | Create a new user |
| PUT | `/users/{id}` | Update user details |
| DELETE | `/users/{id}` | Delete a user |

### Projects

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/projects` | Fetch all projects |
| GET | `/projects/{id}` | Fetch project by ID |
| POST | `/projects` | Create a new project |
| PUT | `/projects/{id}` | Update project details |
| DELETE | `/projects/{id}` | Delete a project |
| POST | `/projects/{projectId}/add-collaborator/{collaboratorId}` | Add a collaborator |
| PUT | `/projects/{projectId}/remove-collaborator/{collaboratorId}` | Remove a collaborator |

* * * * *

Database Configuration
----------------------

-   Using **PostgreSQL** (cloud or local)

-   Hibernate automatically manages schema via `spring.jpa.hibernate.ddl-auto=update`

-   Default dialect: `org.hibernate.dialect.PostgreSQLDialect`

* * * * *

Docker Setup
------------

Build and run using Docker:

### Build the Docker image
`docker build -t devconnect-backend .`

### Run the container
`docker run -p 8080:8080 devconnect-backend`

* * * * *

Environment Variables
---------------------

Store secrets like database credentials in environment variables:

- `export DB_URL=...`
- `export DB_USER=...`
- `export DB_PASSWORD=...`

**Never commit sensitive information to GitHub.**

* * * * *

Contributing
------------

- Fork the repository

- Create a new branch for your feature or bug fix

- Make changes and commit

- Open a Pull Request

* * * * *

License
-------

This project is licensed under the MIT License.