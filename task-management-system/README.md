# Task Management System

This is a Spring Boot-based Task Management System. Follow the steps below to set up the project and get it running on your local machine.

## Prerequisites

Before running the project, ensure you have the following installed on your machine:

- **Java** (JDK 17 or higher recommended)
- **Maven** (to build and manage dependencies)
- **MySQL** (or another database if configured differently)
- **IDE** (like IntelliJ IDEA or Eclipse for easier development)

## Steps to Run the Project Locally

### 1. Clone the Repository

Clone the repository to your local machine:

git clone <your Repository>

### 2. Install Dependencies

Make sure **Maven** is installed on your system. Then, install the project dependencies by running the following command:

mvn clean install

### 3. Set Up the Database

the project uses **MySQL**, create a new database  with name (`bmtm`), and then configure the connection in the `application.properties` file.

Example:

spring.application.name=com-banquemisr-challenge05-taskMangager
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url= jdbc:mysql://localhost:3306/bmtm
spring.datasource.username=root
spring.datasource.password=`yourpassword`
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

**Note:** Replace `yourpassword` with your actual MySQL password.

### 4. Run the Application

Once the dependencies are installed and the database is set up, run the Spring Boot application you can run the `TaskManagemerApplication` class from your IDE (e.g., IntelliJ IDEA or Eclipse).

### 5. Access the Application

After the application starts, you can access the API via:

http://localhost:8080

#### View Exposed Endpoints

To view all the available API endpoints, you can use Swagger UI at:

http://localhost:8080/swagger-ui/index.html

This will give you a graphical interface to explore and interact with all the API endpoints exposed by the application.
