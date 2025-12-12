# Datamart_Management_System_Using_Java_OOPS_Concept

**Short description**

A small-to-medium scale Datamart Management System implemented in Java using object-oriented principles. This repository contains the core backend modules, a simple CLI/demo UI, sample data loaders, and unit tests to demonstrate how to model, load, query, and manage datamart entities (cubes, dimensions, measures, ETL jobs) using clean OOP design.

---

## Table of contents

* [Features](#features)
* [Architecture & Design](#architecture--design)
* [Tech stack](#tech-stack)
* [Project structure](#project-structure)
* [Installation](#installation)
* [Usage](#usage)
* [Sample code](#sample-code)
* [Testing](#testing)
* [Contributing](#contributing)
* [License](#license)
* [Contact](#contact)

---

## Features

* Define and manage **Cubes**, **Dimensions**, **Measures**, and **Datamart Schemas** using OOP models.
* ETL job simulation: load CSV/JSON files into datamart structures.
* Simple query API to aggregate measures across dimensions (group-by style).
* Pluggable persistence (in-memory by default; easy to swap for JDBC/ORM).
* Unit tests and sample dataset for quick exploration.
* Clean separation of concerns (model, repository, service, etl, cli).

---

## Architecture & Design

This project demonstrates OOP best practices:

* **Encapsulation**: Each domain entity (e.g., `Cube`, `Dimension`, `Measure`) encapsulates its state and provides clear behavior.
* **Inheritance & Polymorphism**: `DimensionAttribute` and specialized `Measure` types use inheritance where appropriate.
* **SOLID principles**: Services and repositories follow single responsibility and dependency inversion to make the code testable and swappable.

### High-level components

* `model` — POJOs representing datamart concepts.
* `repository` — interfaces + in-memory implementations for storing models.
* `service` — business logic (create, update, dispose, query).
* `etl` — extract-transform-load utilities to ingest CSV/JSON.
* `cli` — a small command-line demonstration harness.
* `api` — (optional) REST endpoints (if you want to add a Spring Boot wrapper).

---

## Tech stack

* Java 17+ (recommended)
* Maven or Gradle build
* JUnit 5 for tests
* Jackson for JSON parsing
* (Optional) Spring Boot for REST API wrapper

---

## Project structure

```
datamart-java/
├─ src/main/java/
│  ├─ com.example.datamart.model/
│  │  ├─ Cube.java
│  │  ├─ Dimension.java
│  │  ├─ Measure.java
│  ├─ com.example.datamart.repository/
│  │  ├─ DatamartRepository.java
│  │  ├─ InMemoryDatamartRepository.java
│  ├─ com.example.datamart.service/
│  │  ├─ DatamartService.java
│  │  ├─ ETLService.java
│  ├─ com.example.datamart.cli/
│     ├─ Main.java
└─ src/test/java/
   ├─ com.example.datamart.tests/
```

---

## Installation

1. Clone the repository:

```bash
git clone https://github.com/<your-username>/datamart-java.git
cd datamart-java
```

2. Build (Maven):

```bash
mvn clean package
```

Or with Gradle:

```bash
./gradlew build
```

3. Run the demo CLI:

```bash
java -jar target/datamart-java-1.0.0.jar
```

(If us

