[![Java CI](https://github.com/yacosta738/ecommerce-demo/actions/workflows/ci-cd.yml/badge.svg)](https://github.com/yacosta738/ecommerce-demo/actions/workflows/ci-cd.yml)
![coverage](.github/badges/jacoco.svg)
![branches coverage](.github/badges/branches.svg)

# ecommerce-demo

## Running the demo

```shell
./mvnw spring-boot:run
```

### Prerequisites
- Java 17 or higher
- Maven 3.8.1 or higher

## Running the tests

```shell
./mvnw test
```

## Running the tests with coverage

```shell
./mvnw clean test jacoco:report
```
