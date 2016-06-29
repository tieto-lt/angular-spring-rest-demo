
# Description

This demo single page application (SPA) project shows an example how Spring Boot application can be integrated with AngularJS frontend framework.
Simple CRUD Item implementation was chosen as showcase.
This project will be used for **Month with IT** Tieto program to demonstrate an example application and introduce participants to the tooling that will be used.
    
- Backend
    - Spring Boot
    - H2 in memory database which saves state to ./h2/database-dev
    - Liquibase for database migrations
    - Spring Data CRUD repository JDBC implementation: cz.jirutka.spring:spring-data-jdbc-repository
    - Lombok for less Java boilerplate
    - Java Bean validation for server side validation
- Frontend
    - Using Maven com.github.eirslett:frontend-maven-plugin to have separate frontend build
    - NPM for library management
    - Webpack to package libraries + custom code into single JS/CSS file.
    - AngularJS
    - Angular UI Router (not the default one)
    - Angular Messages for front end validation
    - Bootstrap CSS components
    - SASS for custom styles

# Running

Application is started on localhost:8080 by default.

## Command line

`mvn spring-boot:run` or `./run run`

To autosync frontend code `./run sync-assets`

## IDE

    Simply run Application class `main` method.
    
# Architecture

## Frontend

Single server side view `/src/main/resources/static/index.html` is served by Spring. When it is loaded Angular 
takes care of client side routing part and backend is used as a REST Api.
  
### Build Structure
  
`app.bundle.js` and `styles.bundle.css` are bundled using Webpack. Its configuration is in `webpack.config.js`.
`app.bundle.js` contains libraries, custom code, angular templates.
`styles.bundle.css` contains bootstrap css and custom SASS compiled into CSS.

All frontend code is at `src/main/app`.
Consider an example when we are building some kind of user management page.
Each folder in `app` should represent a business unit e.g `user-management`, `news-feed` etc.
`app/user-management/components` should contain separate components for that page. 
E.g `app/user-management/components/list`, `app/user-management/components/new`, `app/user-management/components/update` etc.
`app/user-management/services` should be used for API calls and maybe some business logic that should be present in UI and shared accross components.

## Backend

Backend Java code is at `src/main/java/lt/tieto/angular_spring_rest_demo`
`Application.java` is an entry point for Spring Boot application.
`lt.tieto.angular_spring_rest_demo.core` package is for common things like base implementation of repository, controller, some utils etc.
`lt.tieto.angular_spring_rest_demo.item` package is for implementing `item` domain.
If we had more domain objects we could create packages like `lt.tieto.angular_spring_rest_demo.user`, `lt.tieto.angular_spring_rest_demo.account` 

Explanation of `lt.tieto.angular_spring_rest_demo.item` packages:

- `controllers` are classes that should have minimal implementation. 
  They are used to join services with the representation layer. In this case - REST JSON Api.
  They are responsible for routes mapping and invocation of services that does all the work.
- `models` are classes that represent resources of API. They are just data holders that are returned by controllers.
  Models can have some `javax.validation` annotations
- `services` are classes that performs all the business logic. In our case there is not much logic, 
  but in real life scenario more complex calculations or data aggregation should be done in service.
  Most of the time services also perform mapping from repository models to API models however in some cases it might be useful to defined separate service models..
- `repositories` are classes that interact with databases. It is a good practice to separate database model `DbModel` from API models
  because in more complex scenarios they tend to differ. SQL queries are implemented here. 
  Most commons queries like `findAll`, `findOne` are inherited.
  For custom queries `JdbcOperations` should be injected and used with already implemented `RowMapper`s
- `mappers` are classes with static methods that perform data class mappings from one type to another.