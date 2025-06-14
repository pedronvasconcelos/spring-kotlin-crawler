# Crawler API Documentation

This document provides information about accessing and using the Crawler API documentation.

## Swagger UI Access

Once the application is running, you can access the interactive API documentation through the following URLs:

### Swagger UI
- **URL**: `http://localhost:8080/swagger-ui.html`
- **Description**: Interactive web interface for exploring and testing the API endpoints

### OpenAPI JSON
- **URL**: `http://localhost:8080/api-docs`
- **Description**: Raw OpenAPI 3.0 specification in JSON format

## Available Endpoints

### Health Check
- **GET** `/api` - Check application health status

### Crawl Management
- **POST** `/api/crawls` - Create a new web crawling task

## API Features

The Swagger UI provides the following features:

1. **Interactive Testing**: Test API endpoints directly from the browser
2. **Request/Response Examples**: View sample requests and responses
3. **Schema Documentation**: Detailed information about request and response models
4. **Authentication**: Support for API authentication (if configured)

## Getting Started

1. Start the application:
   ```bash
   ./gradlew bootRun
   ```

2. Open your browser and navigate to:
   ```
   http://localhost:8080/swagger-ui.html
   ```

3. Explore the available endpoints and try making requests using the "Try it out" feature

## Configuration

The Swagger configuration can be customized in `application.properties`:

```properties
# Swagger/OpenAPI Configuration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
springdoc.swagger-ui.tryItOutEnabled=true
```

## API Information

- **Title**: Crawler API
- **Version**: 1.0.0
- **Description**: API for web crawling operations
- **Contact**: crawler@nantes.com 