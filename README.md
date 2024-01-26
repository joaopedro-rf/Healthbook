# Healthbook

HealthBook API is an application developed for managing patient records, appointments, and doctors. It allows patients to schedule their own appointments easily and conveniently.

## Features
  * Patient registration and management.
  * Appointment scheduling by patients.
  * Doctor registration and management.
  * Querying available appointment times.

## Installation

To run this project locally, you will need to have Maven installed on your machine. If you don't have Maven installed yet, follow these steps:

### Installing Maven

1. **Download Maven:**

   You can find it on Apache's official site: [Apache Maven](https://maven.apache.org/download.cgi)

2. **Follow the steps to install it:**

   After downloading, follow the specific instructions for your operating system to install Maven. This typically involves extracting the downloaded Maven archive to a desired location on your system and configuring the necessary environment variables, such as M2_HOME and PATH, to ensure that Maven commands can be executed from any directory in your terminal or command prompt.

3. **Verify installation:**

  To verify if Maven was correctly installed, run this command:

   ```sh
   mvn -version
   ```

## How to execute

1. **Clone the repository:**

   ```bash
   git clone https://github.com/joaopedro-rf/Healthbook.git
   cd Healthbook 
    ```
2. 
    `mvn clean install`
   

## Endpoints

Listed bellow are the endpoints you can use:

## Users

#### Login
- **Method:** `POST`
- **Endpoint:** `/api/users/login`
- **Description:** Authenticate the user.
- **Request body (JSON):**
  ```json
  {
    "username": "example",
    "password": "password"
  }
    ```
#### Register

- **Method:** `POST`
- **Endpoint:** `/api/users/register`
- **Description:** Register a new user.
- **Request body (JSON):**

  ```json
  {
    "username": "example",
    "password": "password",
    "email": "example@example.com"
  }
    ```
#### Get a user by username

- **Method:** `GET`
- **Endpoint:** `/api/users/{username}`
- **Description:** Returns information about a specific user based on the provided username.

  Example:
`GET /api/users/johndoe`


#### Get all users

- **Method:** `GET`
- **Endpoint:** `/api/users/allusers`
- **Description:** Returns a list of all registered users in the system.

Example:
`GET /api/users/allusers`

## Calendar

#### Get events by ID

- **Method:** `GET`
- **Endpoint:** `/api/calendar/{id}`
- **Description:** Returns information about a specific event based on the provided ID.

  Example:
`GET /api/calendar/123`

#### Get all events

- **Method:** `GET`
- **Endpoint:** `/api/calendar/all`
- **Description:** Returns a list of all events in the calendar.


#### Add a new event

- **Method:** `POST`
- **Endpoint:** `/api/calendar`
- **Description:** Add a new event to the calendar
- **Request body (JSON):**

  ```json
  {
    "title": "Exemplo de Evento",
    "start": "2024-01-27T10:00:00",
    "end": "2024-01-27T12:00:00",
    "description": "Descrição do evento"
  } 
    ```
  
#### Update event

- **Method:** `PUT`
- **Endpoint:** `/api/calendar/{id}`
- **Description:** Updates an existing event based on the provided ID.
- **Request body (JSON):**

  ```json
  {
    "title": "Novo Título do Evento",
    "start": "2024-01-27T09:00:00",
    "end": "2024-01-27T11:00:00",
    "description": "Nova descrição do evento"
  }
    ```
  
#### Delete event

- **Method:** `DELETE`
- **Endpoint:** `/api/calendar/{id}`
- **Description:** Deletes an event based on the provided ID.

  Example:
`DELETE /api/calendar/123`
- 
## Pacients

#### Get all pacients

- **Method:** `GET`
- **Endpoint:** `/api/pacients`
- **Description:** Returns a paginated list of pacients

#### Get pacient by name

- **Method:** `GET`
- **Endpoint:** `/api/pacients/find`
- **Description:** Returns a paginated list of patients whose name matches the provided search term.

  Example:
`GET /api/pacients/find?name=termo-de-pesquisa`
- 
#### Get pacient by ID

- **Method:** `GET`
- **Endpoint:** `/api/pacients/{id}`
- **Description:** Returns information about a specific patient based on the provided ID.

Example:
`GET /api/pacients/123`

#### Add a new pacient

- **Method:** `POST`
- **Endpoint:** `/api/pacients`
- **Description:** Adds a new pacient
- **Request body (JSON):**

  ```json
  {
    "name": "Nome do Paciente",
    "birthdate": "1990-01-01",
    "address": "Endereço do Paciente",
    "email": "paciente@example.com",
    "phone": "123456789"
  }
    ```

#### Update pacient

- **Method:** `PUT`
- **Endpoint:** `/api/pacients/{id}`
- **Description:** Atualiza as informações de um paciente existente com base no ID fornecido.
- **Request body (JSON):**

  ```json
  {
    "name": "Novo Nome do Paciente",
    "birthdate": "1990-01-01",
    "address": "Novo Endereço do Paciente",
    "email": "novopaciente@example.com",
    "phone": "987654321"
  }
    ```

## Delete pacient

- **Method:** `DELETE`
- **Endpoint:** `/api/pacients/{id}`
- **Description:** Updates the information of an existing patient based on the provided ID.

  Example:
`DELETE /api/pacients/123`