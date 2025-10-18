# Catelog Demo

Last updated: 2025-10-18

Simple Spring Boot application demonstrating product search backed by Elasticsearch.

Overview

- Java | Spring Boot | Maven
- Provides a small domain model for products and an Elasticsearch-backed search repository (`ProductSearchRepository`)
  for `ProductDocument` entities.

Quick status

- Project root: `pom.xml`
- Main application: `src/main/java/com/catelog/demo/DemoApplication.java`
- Search-enabled entities: `src/main/java/com/catelog/demo/entity/ProductDocument.java`
- REST controller: `src/main/java/com/catelog/demo/controller/ProductController.java`

Prerequisites

- Java 17+ (or the JDK configured for this project)
- Maven 3.6+
- Docker (optional, recommended for running Elasticsearch locally)

Run Elasticsearch for development (Docker)

Run a single-node Elasticsearch suitable for development/testing:

```bash
docker run -p 9200:9200 -e "discovery.type=single-node" --name es docker.elastic.co/elasticsearch/elasticsearch:8.8.1
```

Verify Elasticsearch is reachable:

```bash
curl http://localhost:9200/
```

Build & Run the application

From the project root:

```bash
mvn clean package
java -jar target/*.jar
```

By default Spring Boot listens on port 8080 unless overridden in `src/main/resources/application.properties` or
environment variables.

Run tests

```bash
mvn test
```

API / Usage examples

The project exposes a REST controller for products (see `ProductController`). Example requests:

- Get all products (example):

```bash
curl http://localhost:8080/products
```

- Create an index document directly in Elasticsearch (for quick testing):

```bash
curl -X POST "http://localhost:9200/products/_doc/1" -H 'Content-Type: application/json' -d'
{
  "id": 1,
  "name": "Sample Product",
  "description": "Example product"
}
'
```

- Search using repository-level methods (Java snippet):

```java
List<ProductDocument> results = productSearchRepository.findByNameContaining("Sample");
```

Troubleshooting

- Elasticsearch compatibility: ensure the running Elasticsearch version is compatible with the Spring Data Elasticsearch
  version used in `pom.xml`.
- Port conflicts: if port 9200 or 8080 are in use, stop the occupying service or change the application port via
  `application.properties` (server.port).
- Build failures: run `mvn -X package` for verbose output and inspect stack traces.

Project structure

- `src/main/java` - application source
- `src/main/resources` - configuration and templates/static assets
- `src/test/java` - tests
- `pom.xml` - Maven configuration

Contributing / Next steps

- Add more robust example data and integration tests that run against a test Elasticsearch container (Testcontainers).
- Add README sections for environment-specific configuration if you plan to deploy to cloud or Kubernetes.

