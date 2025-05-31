# Selenium 4.33.0 UI Testing Project

This project demonstrates automated UI testing for the demo site [https://seleniumbase.io/demo_page/](https://seleniumbase.io/demo_page/) using **pure Selenium**, **JUnit 5**, **AssertJ**, **Allure Report**, and **AspectJ**.

## 🧪 Project Overview

The test suite covers:

- Element visibility and presence
- Drag and drop actions
- Checkbox and radio button interactions
- iFrame content verification
- Page navigation and title/URL validation

The tests are designed to ensure UI behavior consistency across common user interactions.

## ⚙️ Technology Stack

| Tool/Library       | Description                                                         | Version       |
|--------------------|----------------------------------------------------------------------|---------------|
| **Selenium**        | Browser automation library                                           | `4.33.0`      |
| **JUnit 5**         | Modern unit testing framework                                        | Managed by Allure BOM |
| **Allure Report**   | Generates rich HTML test reports                                     | `2.29.1`      |
| **AssertJ**         | Fluent assertion library for Java                                    | `4.0.0-M1`    |
| **AspectJ Weaver**  | Enables Allure annotations via bytecode instrumentation              | `1.9.21`      |
| **Maven Surefire**  | Runs test cases during Maven build                                   | `3.5.3`       |

## 🚀 How to Run Tests and View the Report

1. **Run the tests:**

```bash
mvn clean test
```
2. **Serve the Allure report:**

```bash
allure serve target/allure-results
```

Note: Make sure you have Allure CLI installed. [Installation guide](https://allurereport.org/docs/install/)

## Additional Notes
All tests are annotated with @Story, @Description, and @DisplayName to enhance Allure reporting.

SoftAssertions from AssertJ are used to collect multiple verification points without stopping the test at the first failure.

The project uses only pure Selenium (no wrappers), making it lightweight and flexible for custom setups.