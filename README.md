# E-Bank API with CQRS Architecture

This repository contains a Spring Boot-based API for an e-bank application that follows the CQRS (Command Query Responsibility Segregation) architecture.

## Introduction

The E-Bank API with CQRS architecture allows users to perform account-related operations, such as creating accounts, crediting funds, and debiting funds. The API separates the write (command) and read (query) operations to optimize performance and scalability.

## Architecture

The E-Bank API follows the CQRS architecture, which separates the responsibilities of handling commands (write operations) and queries (read operations). This separation allows for better scalability, performance, and maintenance of the application.

The API utilizes Axon Framework, a framework that provides the necessary infrastructure for implementing CQRS and event sourcing.

## Dependencies

The E-Bank API with CQRS architecture utilizes the following dependencies:

- [springdoc-openapi-ui](https://github.com/springdoc/springdoc-openapi): Provides OpenAPI documentation and UI for the API.
- [spring-boot-starter-data-jpa](https://spring.io/projects/spring-data-jpa): Offers data access and persistence capabilities using JPA.
- [spring-boot-starter-web](https://spring.io/guides/gs/spring-boot/): Enables building web applications using Spring MVC.
- [mysql-connector-j](https://dev.mysql.com/doc/connector-j/): Connects the API to a MySQL database.
- [lombok](https://projectlombok.org/): Simplifies Java code by generating boilerplate code.
- [spring-boot-starter-test](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-testing): Provides testing support for Spring Boot applications.
- [axon-spring-boot-starter](https://axoniq.io/product-overview/axon-framework): Integrates Axon Framework into Spring Boot applications for event sourcing and CQRS.

## Prerequisites

To run the E-Bank API with CQRS architecture, ensure you have the following:

- JDK 17 installed on your machine.
- A MySQL database set up and running.
- The database connection settings configured in the `application.properties` file located in the `src/main/resources` directory.

## Installation

To run the E-Bank API with CQRS architecture, follow these steps:

1. Clone this repository to your local machine.

2. Build and run the application using your preferred Java IDE or by executing the following command in the project root directory:

   ```shell
   ./mvnw spring-boot:run
   ```

3. Access the API documentation and explore the available endpoints by visiting [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) in your browser.

## Usage

The E-Bank API with CQRS architecture provides the following functionalities:

- Create account: Allows creating a new bank account.
- Credit account: Credits funds to a specific account.
- Debit account: Debits funds from a specific account.

Please refer to the API documentation for detailed information on the available endpoints, request/response formats, and authentication requirements.

## Contribution

Contributions to the E-Bank API with CQRS architecture are welcome. If you encounter any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.

Please ensure that your contributions adhere to the established coding conventions and best practices.

## License

This project is licensed under the [MIT License](LICENSE).
