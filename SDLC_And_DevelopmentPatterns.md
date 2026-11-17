# SDLC & Development Patterns — Complete Interview Preparation Guide

**Target Experience:** 5+ Years  
**Target Roles:** Senior Software Engineer | Java Full Stack Developer | Backend Developer

---

# Table of Contents

1. [Introduction to SDLC](#1-introduction-to-sdlc)
2. [SDLC Phases](#2-sdlc-phases)
3. [SDLC Models](#3-sdlc-models)
4. [Agile Methodology](#4-agile-methodology)
5. [Scrum Framework](#5-scrum-framework)
6. [Development Workflow](#6-development-workflow)
7. [Requirement Management](#7-requirement-management)
8. [Software Design and Development Practices](#8-software-design-and-development-practices)
9. [Development Patterns and Practices](#9-development-patterns-and-practices)
10. [Git and Branching Strategies](#10-git-and-branching-strategies)
11. [Code Review and Quality Practices](#11-code-review-and-quality-practices)
12. [Testing Methodologies](#12-testing-methodologies)
13. [CI/CD Pipeline](#13-cicd-pipeline)
14. [Build and Dependency Management](#14-build-and-dependency-management)
15. [Deployment and Release Management](#15-deployment-and-release-management)
16. [DevOps and Environment Management](#16-devops-and-environment-management)
17. [Production Support and Maintenance](#17-production-support-and-maintenance)
18. [Documentation and Collaboration](#18-documentation-and-collaboration)
19. [Metrics and Engineering Practices](#19-metrics-and-engineering-practices)
20. [Real-World Development Scenarios](#20-real-world-development-scenarios)
21. [SDLC Interview Questions](#21-sdlc-interview-questions)
22. [Final Interview Preparation Checklist](#22-final-interview-preparation-checklist)

---

# 1. Introduction to SDLC

## 1.1 What is SDLC? ⭐⭐⭐⭐⭐

Software Development Life Cycle (SDLC) is a structured process followed by software engineering teams to develop, test, deploy, and maintain software applications.

It defines how software moves from an initial business requirement to a production-ready application.

**Main objectives:**

- Deliver high-quality software.
- Meet business requirements.
- Reduce development risks.
- Improve team collaboration.
- Maintain predictable delivery timelines.
- Ensure application reliability and maintainability.

## 1.2 Why is SDLC Required?

Without a defined development process:

- Requirements may be misunderstood.
- Development work may be duplicated.
- Testing may happen too late.
- Releases may become unpredictable.
- Production defects may increase.
- Maintenance becomes difficult.

## 1.3 Typical SDLC Workflow

```text
Requirement Gathering
        |
        v
Requirement Analysis
        |
        v
System Design
        |
        v
Development
        |
        v
Testing
        |
        v
Deployment
        |
        v
Maintenance
        |
        v
Continuous Improvement
```

## 1.4 SDLC Roles and Responsibilities

| Role | Responsibility |
|---|---|
| Product Manager | Defines product vision and priorities |
| Business Analyst | Collects and clarifies requirements |
| Product Owner | Manages and prioritizes product backlog |
| Solution Architect | Defines technical architecture |
| Technical Lead | Guides implementation and technical decisions |
| Developer | Implements application features |
| QA Engineer | Validates application functionality |
| DevOps Engineer | Manages build, deployment and infrastructure |
| Scrum Master | Facilitates Scrum and removes process impediments |

---

# 2. SDLC Phases

## 2.1 Requirement Gathering ⭐⭐⭐⭐⭐

Understand what the customer or business expects from the application.

Activities:

- Gather functional requirements.
- Gather non-functional requirements.
- Identify stakeholders.
- Understand business workflows.
- Identify constraints and dependencies.

**Functional requirements:**

Describe what the system should do.

Examples:
- User registration.
- Login and authentication.
- Order creation.
- Payment processing.
- Employee management.

**Non-functional requirements:**

Describe how the system should behave.

Examples:

| Requirement | Example |
|---|---|
| Performance | API response under 300 ms for a defined workload |
| Scalability | Support increasing concurrent users |
| Security | OAuth 2.0 authentication |
| Availability | 99.9% service availability |
| Reliability | Recover from application failures |
| Maintainability | Modular and testable code |

## 2.2 Requirement Analysis ⭐⭐⭐⭐⭐

Evaluate requirements for feasibility, clarity, complexity, and dependencies.

Activities:

- Identify missing requirements.
- Clarify business rules.
- Identify technical risks.
- Estimate development effort.
- Define acceptance criteria.

**Example:**

Requirement: Develop an employee search API.

Clarifications:

- Search by employee ID or name?
- Should partial matching be supported?
- Should results be paginated?
- What authorization is required?
- What should happen if no employee is found?

## 2.3 System Design ⭐⭐⭐⭐⭐

Convert business requirements into a technical solution.

Two important design levels:

| Design Type | Description |
|---|---|
| HLD (High-Level Design) | Defines architecture, services, databases and integrations |
| LLD (Low-Level Design) | Defines classes, methods, interfaces and detailed workflows |

Example architecture:

```text
Angular Frontend
       |
       v
Spring Boot REST API
       |
       v
Service Layer
       |
       v
Repository Layer
       |
       v
PostgreSQL Database
```

Design considerations:

- Application architecture.
- API contracts.
- Database schema.
- Authentication and authorization.
- Error handling.
- Logging and monitoring.
- Scalability and performance.

## 2.4 Development / Implementation ⭐⭐⭐⭐⭐

Convert the design into working code.

Typical activities:

- Create feature branches.
- Implement backend APIs.
- Develop frontend components.
- Integrate databases.
- Implement validations.
- Handle exceptions.
- Write unit tests.
- Perform local testing.

**Example Spring Boot structure:**

```text
employee-service/
 ├── controller/
 │    └── EmployeeController.java
 ├── service/
 │    ├── EmployeeService.java
 │    └── EmployeeServiceImpl.java
 ├── repository/
 │    └── EmployeeRepository.java
 ├── entity/
 │    └── Employee.java
 ├── dto/
 │    └── EmployeeRequest.java
 ├── exception/
 │    └── GlobalExceptionHandler.java
 └── EmployeeApplication.java
```

## 2.5 Testing ⭐⭐⭐⭐⭐

Verify that the application works according to requirements.

Testing includes:

- Unit testing.
- Integration testing.
- API testing.
- System testing.
- Regression testing.
- Performance testing.
- Security testing.
- User acceptance testing.

Detailed testing concepts are covered in Section 12.

## 2.6 Deployment ⭐⭐⭐⭐⭐

Move tested software into the target environment.

Typical environments:

```text
Local
  |
  v
Development
  |
  v
QA / Testing
  |
  v
UAT / Staging
  |
  v
Production
```

Deployment activities:

- Build application artifacts.
- Run automated tests.
- Deploy application.
- Apply database migrations.
- Verify health checks.
- Monitor application logs and metrics.

## 2.7 Maintenance ⭐⭐⭐⭐⭐

Maintain the application after production release.

Types:

| Type | Description | Example |
|---|---|---|
| Corrective | Fix defects | Fix incorrect calculation |
| Adaptive | Adapt to environmental changes | Upgrade Java version |
| Perfective | Improve existing features | Optimize API performance |
| Preventive | Reduce future problems | Refactor legacy code |

---

# 3. SDLC Models

## 3.1 Waterfall Model ⭐⭐⭐⭐⭐

A sequential development approach where one phase is generally completed before the next phase begins.

```text
Requirements
     |
     v
Design
     |
     v
Development
     |
     v
Testing
     |
     v
Deployment
     |
     v
Maintenance
```

Characteristics:

- Sequential process.
- Requirements are defined upfront.
- Changes can be expensive later.
- Testing generally happens after implementation.
- Suitable when requirements are stable.

Advantages:

- Easy to understand.
- Clear documentation.
- Predictable phase structure.

Disadvantages:

- Limited flexibility.
- Late feedback.
- Risk of discovering problems late.

## 3.2 Agile Model ⭐⭐⭐⭐⭐

Agile is an iterative and incremental approach to software development.

Instead of delivering the entire application at once, teams deliver smaller increments frequently.

```text
Product Backlog
      |
      v
Sprint Planning
      |
      v
Development
      |
      v
Testing
      |
      v
Sprint Review
      |
      v
Retrospective
      |
      v
Next Sprint
```

Advantages:

- Faster feedback.
- Continuous improvement.
- Flexible requirement changes.
- Frequent delivery.
- Better collaboration.

Disadvantages:

- Requires active stakeholder participation.
- Scope changes can affect planning.
- Poorly managed Agile teams may lose focus.

## 3.3 Spiral Model ⭐⭐⭐⭐

Combines iterative development with risk analysis.

Each iteration includes:

1. Planning.
2. Risk analysis.
3. Engineering.
4. Evaluation.

Suitable for large, complex and high-risk projects.

## 3.4 V-Model ⭐⭐⭐⭐

An extension of the Waterfall approach where each development phase has a corresponding testing phase.

| Development Phase | Corresponding Testing |
|---|---|
| Requirement Analysis | Acceptance Testing |
| System Design | System Testing |
| Architecture Design | Integration Testing |
| Module Design | Unit Testing |
| Implementation | Code Execution |

Testing is planned early rather than being treated as a final activity.

## 3.5 Iterative vs Incremental Development ⭐⭐⭐⭐

| Iterative | Incremental |
|---|---|
| Improves an existing solution through repeated cycles | Adds functionality in small pieces |
| Focuses on refinement | Focuses on feature delivery |
| Example: Improve search performance over iterations | Example: Deliver login, then search, then reporting |

## 3.6 Waterfall vs Agile ⭐⭐⭐⭐⭐

| Feature | Waterfall | Agile |
|---|---|---|
| Approach | Sequential | Iterative and incremental |
| Requirements | Mostly fixed upfront | Evolve through feedback |
| Delivery | Usually later | Frequent increments |
| Testing | Often after development | Continuous throughout development |
| Customer feedback | At defined milestones | Frequent |
| Flexibility | Lower | Higher |
| Change cost | Often higher later | Generally easier to accommodate |
| Best suited for | Stable requirements | Evolving requirements |

**Interview point:** Agile is not the absence of planning or documentation. It emphasizes adapting plans based on feedback and delivering value frequently.

---

# 4. Agile Methodology

## 4.1 What is Agile? ⭐⭐⭐⭐⭐

Agile is a set of values and principles that encourages iterative development, customer collaboration, working software and continuous adaptation.

The Agile Manifesto has four core values:

1. Individuals and interactions over processes and tools.
2. Working software over comprehensive documentation.
3. Customer collaboration over contract negotiation.
4. Responding to change over following a plan.

The items on the right still have value; Agile emphasizes the items on the left more.

## 4.2 Agile Principles

Important principles:

- Deliver working software frequently.
- Welcome changing requirements.
- Maintain continuous collaboration.
- Build around motivated individuals.
- Prefer sustainable development.
- Focus on technical excellence.
- Regularly reflect and improve.

## 4.3 Agile vs Scrum vs Kanban

| Concept | Description |
|---|---|
| Agile | Broad philosophy and set of principles |
| Scrum | Framework for delivering work in time-boxed Sprints |
| Kanban | Method focused on visualizing and managing continuous workflow |

**Important:** Scrum and Kanban are not programming methodologies. They help organize and manage development work.

---

# 5. Scrum Framework

## 5.1 What is Scrum? ⭐⭐⭐⭐⭐

Scrum is an Agile framework that helps teams deliver value through short, fixed-length iterations called Sprints.

A Sprint is generally one to four weeks.

## 5.2 Scrum Roles

| Role | Responsibility |
|---|---|
| Product Owner | Maximizes product value and manages backlog ordering |
| Scrum Master | Helps establish Scrum and removes impediments |
| Developers | Build and deliver the product increment |

A Scrum team is cross-functional and self-managing.

## 5.3 Scrum Events ⭐⭐⭐⭐⭐

| Event | Purpose | Typical Duration |
|---|---|---|
| Sprint Planning | Define Sprint goal and select work | Up to 8 hours for a one-month Sprint |
| Daily Scrum | Inspect progress and adapt the plan | 15 minutes |
| Sprint Review | Inspect the increment and gather feedback | Up to 4 hours for a one-month Sprint |
| Sprint Retrospective | Improve team effectiveness | Up to 3 hours for a one-month Sprint |
| Sprint | Time-box for delivering an increment | One month or less |

The durations above are Scrum Guide maximums for a one-month Sprint; shorter Sprints generally have shorter events.

## 5.4 Scrum Artifacts

| Artifact | Description | Commitment |
|---|---|---|
| Product Backlog | Ordered list of product work | Product Goal |
| Sprint Backlog | Selected Sprint work and delivery plan | Sprint Goal |
| Increment | Usable product output | Definition of Done |

## 5.5 Sprint Workflow

```text
Product Backlog
      |
      v
Sprint Planning
      |
      v
Sprint Backlog
      |
      v
Development + Testing
      |
      v
Daily Scrum
      |
      v
Working Increment
      |
      v
Sprint Review
      |
      v
Sprint Retrospective
```

## 5.6 User Stories ⭐⭐⭐⭐⭐

A user story describes a feature from the perspective of the user.

Typical format:

```text
As a <user>,
I want <functionality>,
So that <business value>.
```

Example:

```text
As an employee,
I want to view my salary details,
So that I can understand my monthly compensation.
```

## 5.7 Acceptance Criteria

Define conditions that must be satisfied for a story to be accepted.

Example:

```text
Feature: Employee Login

Acceptance Criteria:

1. User should enter a valid username and password.
2. Valid credentials should allow login.
3. Invalid credentials should return an appropriate error.
4. Locked accounts should not be allowed to log in.
5. Authentication failures should be logged safely.
```

## 5.8 Definition of Ready vs Definition of Done

| Definition of Ready | Definition of Done |
|---|---|
| Conditions before work starts | Conditions before work is considered complete |
| Requirements are understood | Implementation is complete |
| Acceptance criteria are available | Tests pass |
| Dependencies are identified | Code review is complete |
| Work is sufficiently estimated | Documentation and required checks are complete |

A team may define its own working agreements for these concepts.

## 5.9 Story Points and Estimation ⭐⭐⭐⭐

Story points estimate relative effort, complexity and uncertainty.

Example:

| Story | Complexity | Story Points |
|---|---|---:|
| Add a simple validation | Low | 1 |
| Develop a CRUD API | Medium | 3 |
| Implement authentication | High | 5 |
| Build a complex integration | Very High | 8 |

Common estimation techniques:

- Planning Poker.
- T-shirt sizing.
- Relative estimation.
- Three-point estimation.

**Important:** Story points are not equivalent to hours.

## 5.10 Sprint Velocity

Velocity is the amount of work completed in a Sprint, commonly measured in completed story points.

Example:

| Sprint | Completed Points |
|---|---:|
| Sprint 1 | 25 |
| Sprint 2 | 30 |
| Sprint 3 | 28 |
| Sprint 4 | 29 |

Average velocity = 28 points.

Velocity is useful for team-level forecasting, not as an individual performance ranking.

---

# 6. Development Workflow

## 6.1 Typical Feature Development Workflow ⭐⭐⭐⭐⭐

```text
Requirement / User Story
          |
          v
Understand Acceptance Criteria
          |
          v
Technical Analysis
          |
          v
Create Feature Branch
          |
          v
Implement Changes
          |
          v
Write Unit Tests
          |
          v
Run Local Validation
          |
          v
Create Pull Request
          |
          v
Code Review
          |
          v
CI Pipeline
          |
          v
Merge Changes
          |
          v
Deploy to QA
          |
          v
QA Validation
          |
          v
Release to Production
```

## 6.2 What Does a Developer Do in a Sprint?

Typical responsibilities:

1. Understand assigned user stories.
2. Clarify business requirements.
3. Identify dependencies and technical risks.
4. Discuss implementation with the team.
5. Estimate effort.
6. Develop and test the feature.
7. Raise pull requests.
8. Address code review feedback.
9. Support QA and resolve defects.
10. Participate in release activities.

## 6.3 Bug Fix Workflow

```text
Bug Report
    |
    v
Reproduce Issue
    |
    v
Analyze Root Cause
    |
    v
Implement Fix
    |
    v
Add Regression Test
    |
    v
Code Review
    |
    v
QA Validation
    |
    v
Deploy Fix
    |
    v
Monitor Production
```

## 6.4 Feature vs Bug vs Technical Debt

| Work Type | Meaning | Example |
|---|---|---|
| Feature | New functionality | Add employee search |
| Bug | Existing functionality behaves incorrectly | Incorrect salary calculation |
| Technical Debt | Existing implementation creates future maintenance cost | Duplicate business logic |
| Enhancement | Improve existing functionality | Improve search response time |
| Spike | Time-boxed investigation | Evaluate Redis caching |

---

# 7. Requirement Management

## 7.1 Functional vs Non-Functional Requirements

See Section 2.1 for definitions.

Examples for a Spring Boot application:

| Functional | Non-Functional |
|---|---|
| Create employee | API response time |
| Update employee | Availability |
| Search employees | Scalability |
| Generate reports | Security |
| Delete employee | Reliability |

## 7.2 Epic, Feature, User Story and Task ⭐⭐⭐⭐

| Item | Meaning | Example |
|---|---|---|
| Epic | Large business capability | Employee Management |
| Feature | Specific functionality | Employee Search |
| User Story | User-focused requirement | Search employee by ID |
| Task | Technical implementation activity | Create repository query |

Hierarchy:

```text
Epic
  |
  +-- Feature
        |
        +-- User Story
              |
              +-- Development Task
              +-- Testing Task
```

The exact hierarchy varies by organization and project management tool.

## 7.3 Requirement Traceability Matrix

Maps requirements to design, implementation and test cases.

| Requirement ID | Requirement | Development | Test Case |
|---|---|---|---|
| REQ-01 | User login | Auth API | TC-01 |
| REQ-02 | Employee search | Search API | TC-02 |
| REQ-03 | Create employee | Employee API | TC-03 |

Benefits:

- Ensures requirements are implemented.
- Identifies missing test coverage.
- Helps with audits and impact analysis.

## 7.4 Requirement Change Management

When a requirement changes:

1. Understand the change.
2. Identify affected modules.
3. Analyze technical impact.
4. Estimate additional effort.
5. Discuss priority with Product Owner.
6. Update backlog and acceptance criteria.
7. Implement and test the change.

---

# 8. Software Design and Development Practices

## 8.1 Separation of Concerns ⭐⭐⭐⭐⭐

Each module should have a clearly defined responsibility.

Example:

```text
Controller
    |
    v
Service
    |
    v
Repository
    |
    v
Database
```

- Controller handles HTTP requests and responses.
- Service handles business logic.
- Repository handles data access.
- Entity represents persisted data.
- DTO represents data exchanged through APIs.

## 8.2 Layered Architecture

Common layers:

| Layer | Responsibility |
|---|---|
| Presentation | UI and API entry points |
| Application | Coordinates use cases |
| Domain | Core business rules |
| Infrastructure | Database, messaging and external systems |

## 8.3 SOLID Principles

| Principle | Meaning | Example |
|---|---|---|
| S - Single Responsibility | One reason to change | Separate email and payment services |
| O - Open/Closed | Open for extension, closed for modification | Strategy pattern |
| L - Liskov Substitution | Subtypes should preserve expected behavior | Correct interface implementations |
| I - Interface Segregation | Prefer smaller focused interfaces | Separate read and write interfaces |
| D - Dependency Inversion | Depend on abstractions | Inject repository interface |

## 8.4 DRY, KISS and YAGNI ⭐⭐⭐⭐

| Principle | Meaning |
|---|---|
| DRY | Don't Repeat Yourself |
| KISS | Keep It Simple |
| YAGNI | You Aren't Gonna Need It |

Examples:

- DRY: Extract repeated validation logic.
- KISS: Prefer a straightforward implementation over unnecessary abstraction.
- YAGNI: Do not build speculative features without a real requirement.

## 8.5 Clean Code Practices

- Use meaningful variable and method names.
- Keep methods focused.
- Avoid deeply nested conditions.
- Avoid duplicate logic.
- Handle exceptions appropriately.
- Use immutable objects where practical.
- Keep classes cohesive.
- Avoid unnecessary abstractions.
- Write tests for business-critical behavior.

---

# 9. Development Patterns and Practices

These are engineering practices and development approaches that improve software delivery. They are different from GoF design patterns such as Factory, Strategy and Observer.

## 9.1 Test-Driven Development (TDD) ⭐⭐⭐⭐⭐

TDD is a development approach where tests are written before production code.

### Red → Green → Refactor

```text
Write Failing Test
        |
        v
       RED
        |
        v
Write Minimum Code
        |
        v
      GREEN
        |
        v
Refactor Code
        |
        v
     REPEAT
```

Example:

**Step 1: Write a test**

```java
@Test
void shouldReturnTotalPrice() {
    PriceCalculator calculator = new PriceCalculator();

    double result = calculator.calculate(100, 10);

    assertEquals(110, result);
}
```

**Step 2: Implement the code**

```java
public class PriceCalculator {

    public double calculate(double price, double tax) {
        return price + (price * tax / 100);
    }
}
```

**Step 3: Refactor**

Improve readability and design without changing behavior.

Benefits:

- Early defect detection.
- Better test coverage.
- Safer refactoring.
- Clearer expected behavior.

## 9.2 Behavior-Driven Development (BDD) ⭐⭐⭐⭐

BDD describes application behavior using business-readable scenarios.

Common format: Given – When – Then.

Example:

```gherkin
Feature: Employee Login

Scenario: Login with valid credentials

Given the employee has a registered account
When the employee enters valid credentials
Then the employee should be redirected to the dashboard
```

Popular tools:

- Cucumber.
- SpecFlow.
- Behave.

## 9.3 Acceptance Test-Driven Development (ATDD) ⭐⭐⭐⭐

Acceptance criteria and acceptance tests are defined before implementation.

Focuses on shared understanding between:

- Business stakeholders.
- Developers.
- Testers.

**TDD vs BDD vs ATDD**

| Approach | Focus | Typical Test |
|---|---|---|
| TDD | Code behavior and design | Unit test |
| BDD | Business behavior | Given-When-Then scenario |
| ATDD | Acceptance criteria | Acceptance test |

## 9.4 Pair Programming ⭐⭐⭐⭐

Two developers work together on the same task.

| Role | Responsibility |
|---|---|
| Driver | Writes the code |
| Navigator | Reviews, guides and thinks ahead |

Benefits:

- Knowledge sharing.
- Early defect detection.
- Improved collaboration.
- Faster onboarding in complex areas.

## 9.5 Mob Programming

The entire team works together on one problem using a shared development environment.

Useful for complex problems, knowledge sharing and critical design decisions.

## 9.6 Trunk-Based Development ⭐⭐⭐⭐⭐

Developers integrate small changes frequently into a shared main branch.

Characteristics:

- Short-lived branches.
- Frequent integration.
- Automated tests.
- Feature flags for incomplete functionality.

Benefits:

- Reduced merge conflicts.
- Faster integration.
- Continuous delivery.

## 9.7 Continuous Integration (CI) ⭐⭐⭐⭐⭐

Developers frequently integrate code changes into a shared repository.

Each integration triggers automated checks such as:

- Compilation.
- Unit tests.
- Static code analysis.
- Security checks.
- Build validation.

## 9.8 Continuous Delivery vs Continuous Deployment ⭐⭐⭐⭐⭐

| Continuous Delivery | Continuous Deployment |
|---|---|
| Software is always ready for release | Validated changes are automatically deployed |
| Production release may require approval | No manual production release approval in the normal path |
| Human-controlled release decision | Automated release pipeline |

Both depend on reliable automation and testing.

## 9.9 Feature Flags ⭐⭐⭐⭐⭐

Feature flags allow teams to enable or disable functionality without deploying a new application version.

Example:

```java
if (featureFlagService.isEnabled("NEW_SEARCH")) {
    return newSearchService.search(query);
}

return oldSearchService.search(query);
```

Use cases:

- Gradual feature rollout.
- A/B testing.
- Canary releases.
- Emergency feature disablement.

Important: Feature flags need ownership, monitoring and cleanup. Old flags can become technical debt.

## 9.10 Refactoring ⭐⭐⭐⭐⭐

Refactoring improves internal code structure without changing externally observable behavior.

Examples:

- Extract method.
- Rename variables.
- Remove duplicate logic.
- Split large classes.
- Introduce interfaces.
- Simplify conditional logic.

**Refactoring vs rewriting:**

| Refactoring | Rewriting |
|---|---|
| Improves existing implementation incrementally | Replaces implementation |
| Preserves expected behavior | Can introduce broader behavioral changes |
| Usually lower risk | Often higher risk |

## 9.11 Boy Scout Rule

Always leave the code cleaner than you found it.

Examples:

- Fix misleading names.
- Remove unused code.
- Improve a confusing method.
- Add missing tests when changing behavior.

## 9.12 Defensive Programming

Write code that validates assumptions and handles unexpected input safely.

Examples:

- Validate API input.
- Check null values.
- Enforce authorization.
- Handle external service failures.
- Apply timeouts.
- Validate database constraints.

## 9.13 Contract-First Development

Define API contracts before implementing the producer and consumer.

Common tools and standards:

- OpenAPI / Swagger.
- JSON Schema.
- Protocol Buffers.

Example:

```yaml
paths:
  /employees/{id}:
    get:
      summary: Get employee by ID
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: integer
      responses:
        "200":
          description: Employee found
        "404":
          description: Employee not found
```

Benefits:

- Frontend and backend can work in parallel.
- API expectations are clear.
- Easier integration testing.
- Better API documentation.

## 9.14 Development Practice Comparison

| Practice | Main Objective |
|---|---|
| TDD | Test before implementation |
| BDD | Describe behavior in business language |
| ATDD | Define acceptance tests before development |
| Pair Programming | Improve collaboration and code quality |
| Mob Programming | Collaborative problem-solving |
| Trunk-Based Development | Frequent integration |
| CI | Validate integrated changes |
| Continuous Delivery | Keep software release-ready |
| Continuous Deployment | Automatically release validated changes |
| Feature Flags | Control feature availability |
| Refactoring | Improve internal code quality |
| Contract-First | Agree on interfaces before implementation |

---

# 10. Git and Branching Strategies

## 10.1 What is Version Control? ⭐⭐⭐⭐⭐

Version control tracks changes to source code over time.

Popular tools:

- Git.
- GitHub.
- GitLab.
- Bitbucket.
- Azure Repos.

Benefits:

- Collaboration.
- Change history.
- Rollback.
- Branching.
- Code review.

## 10.2 Common Git Commands

```bash
# Clone repository
git clone <repository-url>

# Check status
git status

# Create branch
git checkout -b feature/employee-search

# Stage changes
git add .

# Commit
git commit -m "Add employee search API"

# Push branch
git push origin feature/employee-search

# Pull latest changes
git pull origin main

# Fetch remote changes
git fetch origin

# Merge branch
git merge main

# View history
git log --oneline

# Stash changes
git stash

# Apply stashed changes
git stash pop

# Rebase
git rebase main
```

## 10.3 Git Branching Strategies ⭐⭐⭐⭐⭐

### GitFlow

Common branches:

| Branch | Purpose |
|---|---|
| main | Production-ready code |
| develop | Integration branch |
| feature/* | New functionality |
| release/* | Release preparation |
| hotfix/* | Urgent production fixes |

Workflow:

```text
main
  |
  v
develop
  |
  +---- feature/login
  |
  +---- feature/search
  |
  v
release
  |
  v
main
```

### GitHub Flow

Simpler workflow:

```text
main
  |
  v
Create Feature Branch
  |
  v
Implement Changes
  |
  v
Pull Request
  |
  v
Code Review + CI
  |
  v
Merge to Main
  |
  v
Deploy
```

### Trunk-Based Development

- Developers integrate frequently into the main branch.
- Branches are short-lived.
- Automated validation is essential.
- Feature flags manage incomplete functionality.

## 10.4 Merge vs Rebase ⭐⭐⭐⭐⭐

| Merge | Rebase |
|---|---|
| Combines branch histories | Replays commits on top of another base |
| May create merge commits | Can create a linear history |
| Preserves branch topology | Rewrites commit history |
| Safer for shared branch history | Avoid rebasing shared commits without coordination |

Example:

```bash
# Merge
git checkout feature/search
git merge main

# Rebase
git checkout feature/search
git rebase main
```

## 10.5 Merge Conflict

Occurs when Git cannot automatically reconcile conflicting changes.

Resolution:

1. Identify conflicting files.
2. Open conflict markers.
3. Understand both changes.
4. Resolve the intended implementation.
5. Run tests.
6. Stage resolved files.
7. Complete merge or rebase.

## 10.6 Pull Request / Merge Request

A pull request proposes merging changes into another branch.

A good PR should include:

- Clear title.
- Business context.
- Summary of changes.
- Related ticket.
- Screenshots or API examples when relevant.
- Testing details.
- Known risks.

---

# 11. Code Review and Quality Practices

## 11.1 What is Code Review? ⭐⭐⭐⭐⭐

Code review is the process of examining code changes before integration.

Objectives:

- Identify defects.
- Improve readability.
- Ensure coding standards.
- Validate business logic.
- Identify security issues.
- Share knowledge.

## 11.2 Code Review Checklist

| Category | What to Verify |
|---|---|
| Correctness | Does it satisfy the requirement? |
| Readability | Are names and methods clear? |
| Design | Is responsibility separated correctly? |
| Performance | Are there unnecessary database calls? |
| Security | Is input validated? |
| Error Handling | Are exceptions handled correctly? |
| Testing | Are important scenarios covered? |
| Compatibility | Are existing APIs affected? |
| Maintainability | Is the solution easy to extend? |

## 11.3 Static Code Analysis

Analyzes source code without executing the application.

Popular tools:

- SonarQube.
- Checkstyle.
- PMD.
- SpotBugs.
- ESLint for JavaScript/TypeScript.

Typical findings:

- Code smells.
- Bugs.
- Security vulnerabilities.
- Duplicate code.
- Complexity.
- Style violations.

## 11.4 Code Coverage

Measures how much code is executed by tests.

Common metrics:

| Metric | Meaning |
|---|---|
| Line Coverage | Percentage of executed lines |
| Branch Coverage | Percentage of executed branches |
| Method Coverage | Percentage of executed methods |
| Instruction Coverage | Percentage of executed bytecode instructions |

**Important:** High code coverage does not guarantee good tests. Assertions and meaningful scenarios matter.

## 11.5 Technical Debt

Technical debt is the future cost created by choosing a less-than-ideal implementation.

Examples:

- Duplicate code.
- Missing tests.
- Outdated dependencies.
- Poor architecture.
- Temporary workarounds.
- Missing documentation.

Managing technical debt:

1. Identify it.
2. Assess business and engineering impact.
3. Prioritize.
4. Refactor incrementally.
5. Add regression tests.

---

# 12. Testing Methodologies

## 12.1 Software Testing ⭐⭐⭐⭐⭐

Software testing validates that the application behaves as expected and helps identify defects before release.

## 12.2 Testing Pyramid ⭐⭐⭐⭐⭐

```text
             /\
            /  \
           / UI \
          /------\
         /  API   \
        /----------\
       /   UNIT     \
      /--------------\
```

| Level | Description | Typical Tools |
|---|---|---|
| Unit | Tests individual methods/classes | JUnit, Mockito |
| Integration | Tests interactions between components | Spring Boot Test, Testcontainers |
| API | Tests API behavior and contracts | REST Assured, Postman |
| UI / E2E | Tests complete user workflows | Selenium, Playwright, Cypress |

Generally, teams aim for many fast unit tests, a meaningful set of integration tests and fewer slower end-to-end tests.

## 12.3 Unit Testing ⭐⭐⭐⭐⭐

Tests a small unit of code in isolation.

Example:

```java
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void shouldReturnEmployee() {
        Employee employee =
            new Employee(1L, "Naren");

        when(repository.findById(1L))
            .thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployee(1L);

        assertEquals("Naren", result.getName());
    }
}
```

## 12.4 Integration Testing ⭐⭐⭐⭐⭐

Validates interaction between multiple components.

Example:

- Spring Boot service and repository.
- Application and database.
- Application and Kafka.
- Application and external API.

## 12.5 Functional vs Non-Functional Testing

| Functional Testing | Non-Functional Testing |
|---|---|
| Validates what the application does | Validates how the application behaves |
| Login validation | Login response time |
| Order creation | Application scalability |
| Payment processing | Payment service availability |
| Search functionality | Search performance |

## 12.6 Regression Testing

Ensures that new changes have not broken previously working functionality.

Example:

A change in employee salary calculation should not break:

- Employee search.
- Payroll reports.
- Salary history.
- Tax calculations.

## 12.7 Smoke vs Sanity Testing

| Smoke Testing | Sanity Testing |
|---|---|
| Broad, shallow validation | Narrower validation of changed functionality |
| Checks basic build stability | Checks specific changes or fixes |
| Usually after a new build/deployment | Usually after a focused change |

## 12.8 Performance Testing

| Type | Purpose |
|---|---|
| Load Testing | Tests expected workload |
| Stress Testing | Tests beyond expected capacity |
| Spike Testing | Tests sudden workload changes |
| Endurance Testing | Tests sustained workload |
| Volume Testing | Tests large data volumes |

Tools:

- JMeter.
- Gatling.
- k6.
- LoadRunner.

## 12.9 Security Testing

Common checks:

- Authentication.
- Authorization.
- SQL injection.
- Cross-site scripting.
- CSRF.
- Sensitive data exposure.
- Dependency vulnerabilities.
- Access control failures.

Tools:

- OWASP ZAP.
- Burp Suite.
- Snyk.
- Dependency-Check.

## 12.10 Test Environment Management

| Environment | Purpose |
|---|---|
| Local | Developer testing |
| Development | Team integration |
| QA | Functional and regression testing |
| UAT | Business acceptance |
| Staging | Production-like validation |
| Production | Live customer traffic |

---

# 13. CI/CD Pipeline

## 13.1 What is CI/CD? ⭐⭐⭐⭐⭐

CI/CD automates software integration, validation, delivery and deployment.

- CI: Continuous Integration.
- CD: Continuous Delivery or Continuous Deployment.

## 13.2 Typical CI/CD Pipeline

```text
Developer Push
      |
      v
Source Control
      |
      v
Build
      |
      v
Unit Tests
      |
      v
Static Code Analysis
      |
      v
Integration Tests
      |
      v
Package Artifact
      |
      v
Security Checks
      |
      v
Deploy to QA
      |
      v
Acceptance Tests
      |
      v
Production Release
      |
      v
Monitoring
```

## 13.3 Popular CI/CD Tools

| Tool | Purpose |
|---|---|
| Jenkins | Automation server |
| GitHub Actions | Repository-based automation |
| GitLab CI/CD | Integrated CI/CD pipelines |
| Azure DevOps | Build and release pipelines |
| Argo CD | Kubernetes GitOps delivery |
| TeamCity | Build and deployment automation |

## 13.4 Example Jenkins Pipeline

```groovy
pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://example.com/repository.git'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }

        stage('Unit Tests') {
            steps {
                sh './mvnw test'
            }
        }

        stage('Static Analysis') {
            steps {
                sh './mvnw sonar:sonar'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying application'
            }
        }
    }
}
```

This is an illustrative pipeline. Actual SonarQube configuration, credentials, test gates and deployment steps must be configured for the project.

## 13.5 Build Artifact

A build artifact is the packaged output produced by the build process.

Examples:

- JAR.
- WAR.
- Docker image.
- Frontend build bundle.

Artifacts should be versioned and traceable to the source revision that produced them.

## 13.6 CI vs CD

| CI | Continuous Delivery | Continuous Deployment |
|---|---|---|
| Integrate and validate code frequently | Keep software ready for release | Automatically release validated changes |
| Build and test automation | Release automation with possible approval | Automated production release |

## 13.7 Pipeline Failure Troubleshooting

| Failure | Investigation |
|---|---|
| Compilation failure | Code errors, Java version, dependencies |
| Unit test failure | Assertions, mocks, changed behavior |
| Integration failure | Database, network, service dependencies |
| Sonar failure | Quality gate and code issues |
| Docker build failure | Dockerfile, base image, build context |
| Deployment failure | Configuration, credentials, infrastructure |
| Health check failure | Startup errors, dependencies, ports |

---

# 14. Build and Dependency Management

## 14.1 Maven ⭐⭐⭐⭐⭐

Maven manages Java project builds and dependencies.

Important lifecycle phases:

```text
validate
   |
   v
compile
   |
   v
test
   |
   v
package
   |
   v
verify
   |
   v
install
   |
   v
deploy
```

Common commands:

```bash
mvn clean
mvn compile
mvn test
mvn package
mvn verify
mvn install
mvn deploy
```

## 14.2 Gradle

Gradle is a build automation tool used in Java and other ecosystems.

Example:

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.4.0'
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

## 14.3 Maven vs Gradle

| Maven | Gradle |
|---|---|
| XML configuration | Groovy or Kotlin DSL |
| Convention-based lifecycle | Flexible task-based build |
| Commonly uses pom.xml | Commonly uses build.gradle |
| Mature and widely used | Supports incremental and build-cache optimizations |

## 14.4 Dependency Management

Important concepts:

- Direct dependencies.
- Transitive dependencies.
- Dependency versions.
- Dependency scopes.
- Version conflicts.
- Dependency exclusions.
- Vulnerability scanning.

Useful commands:

```bash
mvn dependency:tree
mvn dependency:analyze
```

## 14.5 Semantic Versioning

Typical version format:

```text
MAJOR.MINOR.PATCH
```

Example: `2.4.1`

- MAJOR: Breaking changes.
- MINOR: Backward-compatible functionality.
- PATCH: Backward-compatible bug fixes.

---

# 15. Deployment and Release Management

## 15.1 Deployment Strategies ⭐⭐⭐⭐⭐

### Rolling Deployment

Gradually replaces old application instances with new instances.

```text
Instance 1: V1 -> V2
Instance 2: V1 -> V2
Instance 3: V1 -> V2
```

Advantages:

- No need to replace all instances simultaneously.
- Gradual rollout.

Risk:

- Old and new versions may run simultaneously, so compatibility is important.

### Blue-Green Deployment

Maintain two production-like environments.

```text
Blue Environment (V1)
          |
          v
       Traffic
          |
          v
Green Environment (V2)
```

After validation, switch traffic to the new environment.

Advantages:

- Fast rollback through traffic switching.
- Reduced deployment downtime.

### Canary Deployment

Release new software to a small percentage of traffic first.

```text
             Traffic
                |
         +------+------+
         |             |
      95% V1         5% V2
         |             |
         +------+------+
                |
          Monitor Results
```

If metrics are healthy, increase traffic gradually.

## 15.2 Deployment Strategy Comparison

| Strategy | Main Idea | Rollback |
|---|---|---|
| Rolling | Replace instances gradually | Redeploy previous version |
| Blue-Green | Switch traffic between environments | Switch back |
| Canary | Expose a small traffic percentage first | Stop rollout and route back |

## 15.3 Rollback Strategy

Rollback restores a previously stable application version or configuration.

Consider:

- Application compatibility.
- Database schema compatibility.
- Configuration changes.
- Message format compatibility.
- Data migration safety.

**Important:** Application rollback does not automatically reverse database changes or data migrations.

## 15.4 Database Migration Management

Tools:

- Flyway.
- Liquibase.

Example Flyway migration:

```sql
-- V2__create_employee_table.sql

CREATE TABLE employees (
    employee_id BIGINT PRIMARY KEY,
    employee_name VARCHAR(100) NOT NULL,
    salary DECIMAL(12, 2)
);
```

Migration best practices:

- Version migrations.
- Test migrations before release.
- Back up critical data.
- Prefer backward-compatible changes.
- Avoid destructive changes without a migration plan.

## 15.5 Zero-Downtime Deployment

A deployment approach designed to keep a service available during release.

Important considerations:

- Load balancers.
- Health checks.
- Graceful shutdown.
- Rolling or blue-green deployment.
- Backward-compatible database changes.
- Readiness checks.

---

# 16. DevOps and Environment Management

## 16.1 What is DevOps? ⭐⭐⭐⭐⭐

DevOps combines development and operations practices to improve collaboration and automate software delivery.

Key areas:

- CI/CD.
- Infrastructure automation.
- Monitoring.
- Logging.
- Configuration management.
- Release automation.
- Reliability.

## 16.2 Infrastructure as Code (IaC)

Manage infrastructure through version-controlled configuration.

Tools:

- Terraform.
- Ansible.
- AWS CloudFormation.
- Pulumi.

Benefits:

- Repeatable environments.
- Reduced manual errors.
- Version-controlled infrastructure.
- Easier recovery.

## 16.3 Containerization

Docker packages an application and its dependencies into a container image.

Example Dockerfile:

```dockerfile
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/employee-service.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

## 16.4 Kubernetes

Kubernetes orchestrates containerized applications.

Important concepts:

- Pod.
- Deployment.
- Service.
- ConfigMap.
- Secret.
- Ingress.
- Horizontal Pod Autoscaler.
- Liveness Probe.
- Readiness Probe.

## 16.5 Environment Configuration

Application configuration should be externalized.

Spring Boot example:

```yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

server:
  port: ${SERVER_PORT:8080}
```

Never hardcode production credentials in source code.

---

# 17. Production Support and Maintenance

## 17.1 Production Issue Handling ⭐⭐⭐⭐⭐

Typical production troubleshooting workflow:

```text
Incident Detected
       |
       v
Assess Impact and Severity
       |
       v
Check Logs and Metrics
       |
       v
Reproduce / Isolate
       |
       v
Identify Root Cause
       |
       v
Apply Mitigation or Fix
       |
       v
Validate Recovery
       |
       v
Monitor Application
       |
       v
Post-Incident Review
```

## 17.2 Incident vs Problem vs Change

| Concept | Meaning |
|---|---|
| Incident | Unplanned interruption or degradation |
| Problem | Underlying cause of one or more incidents |
| Change | Addition, modification or removal that may affect a service |

## 17.3 Severity vs Priority

| Severity | Priority |
|---|---|
| Measures technical/business impact | Measures urgency of resolution |
| Often based on impact and scope | Based on urgency and business needs |
| Example: Payment service unavailable | Example: Fix immediately due to active revenue impact |

## 17.4 Root Cause Analysis (RCA)

RCA identifies why an issue occurred rather than only addressing symptoms.

Example:

**Problem:** API response time increased.

Possible investigation:

1. Check API latency metrics.
2. Inspect database query duration.
3. Review recent code changes.
4. Check connection pool utilization.
5. Identify slow query.
6. Optimize the query.
7. Add monitoring and regression tests.

## 17.5 Five Whys Technique

Ask why repeatedly to identify the underlying cause.

Example:

```text
Why did the API fail?
Because database connections were exhausted.

Why were connections exhausted?
Because requests were holding connections too long.

Why were connections held too long?
Because a slow query was executed.

Why was the query slow?
Because the filtering column lacked a suitable index.

Why was the missing index not identified?
Because the query was not covered by performance testing.
```

Root cause: Insufficient performance validation for the query workload.

## 17.6 Observability

Three important pillars:

| Pillar | Purpose | Tools |
|---|---|---|
| Logs | Understand individual events | ELK, Loki |
| Metrics | Measure application behavior | Prometheus, Grafana |
| Traces | Follow requests across services | Jaeger, OpenTelemetry |

## 17.7 SLA, SLO and SLI

| Term | Meaning |
|---|---|
| SLI | Service Level Indicator: actual measurement |
| SLO | Service Level Objective: target for the measurement |
| SLA | Service Level Agreement: commitment with consequences |

Example:

- SLI: API availability measured over a month.
- SLO: Target availability of 99.9%.
- SLA: Contractual availability commitment.

---

# 18. Documentation and Collaboration

## 18.1 Important Project Documents

| Document | Purpose |
|---|---|
| BRD | Business Requirement Document |
| SRS | Software Requirement Specification |
| HLD | High-Level Design |
| LLD | Low-Level Design |
| API Documentation | Describes API contracts |
| Test Plan | Defines testing scope and approach |
| Release Notes | Describes changes in a release |
| RCA | Documents root cause and corrective actions |
| Runbook | Operational troubleshooting instructions |

## 18.2 Technical Documentation Best Practices

- Document important architecture decisions.
- Keep API contracts updated.
- Document setup instructions.
- Explain complex business logic.
- Maintain operational runbooks.
- Include deployment and rollback procedures.

## 18.3 Architecture Decision Records (ADR)

An ADR records important architectural decisions.

Typical structure:

```text
Title:
Use Redis for Frequently Accessed Data

Status:
Accepted

Context:
Frequently accessed employee data causes repeated database reads.

Decision:
Introduce Redis caching for selected read operations.

Consequences:
Lower database load and improved response times.
Additional cache invalidation and consistency responsibilities.
```

## 18.4 Cross-Team Collaboration

Developers frequently collaborate with:

- Product Owners.
- Business Analysts.
- QA Engineers.
- Architects.
- DevOps Engineers.
- Security Teams.
- Other engineering teams.

Important skills:

- Clear communication.
- Requirement clarification.
- Technical documentation.
- Code review.
- Knowledge sharing.
- Ownership and accountability.

---

# 19. Metrics and Engineering Practices

## 19.1 Agile Metrics

| Metric | Meaning |
|---|---|
| Velocity | Completed story points per Sprint |
| Burndown Chart | Remaining Sprint work over time |
| Burnup Chart | Completed work against total scope |
| Cycle Time | Time from work starting to completion |
| Lead Time | Time from request to delivery |
| Throughput | Number of work items completed |

## 19.2 DORA Metrics ⭐⭐⭐⭐⭐

Common software delivery performance metrics:

| Metric | Meaning |
|---|---|
| Deployment Frequency | How frequently changes are deployed |
| Lead Time for Changes | Time from code commit to production |
| Change Failure Rate | Percentage of deployments causing failures |
| Failed Deployment Recovery Time | Time needed to recover from a failed deployment |

These metrics should be interpreted in context and used to improve systems, not to punish individual developers.

## 19.3 Engineering Quality Metrics

- Defect escape rate.
- Production incident frequency.
- Mean Time to Restore (MTTR).
- Test effectiveness.
- Code review turnaround time.
- Build failure rate.
- Application availability.
- API latency.

## 19.4 Lead Time vs Cycle Time

| Lead Time | Cycle Time |
|---|---|
| Time from request to delivery | Time from work start to completion |
| Includes waiting before work begins | Focuses on active workflow duration |

---

# 20. Real-World Development Scenarios

## 20.1 How Do You Develop a New Feature?

**Scenario:** Develop an employee search feature.

Approach:

1. Understand the business requirement.
2. Clarify search criteria and acceptance criteria.
3. Review existing architecture.
4. Design API contract.
5. Identify database query and indexes.
6. Implement backend service.
7. Implement frontend integration.
8. Write unit and integration tests.
9. Raise PR and address review comments.
10. Deploy to QA.
11. Validate with testers.
12. Release through CI/CD.
13. Monitor production behavior.

## 20.2 What Do You Do When Requirements Change During a Sprint?

1. Understand the requested change.
2. Assess impact on current work.
3. Discuss priority with Product Owner.
4. Identify scope and dependency changes.
5. Re-negotiate Sprint scope where appropriate.
6. Update acceptance criteria and backlog.
7. Communicate delivery impact.

Avoid silently adding substantial work without revisiting priorities.

## 20.3 What If Your Pull Request Is Rejected?

1. Understand the review feedback.
2. Ask for clarification when necessary.
3. Evaluate technical correctness.
4. Make the required changes.
5. Add or update tests.
6. Push updates.
7. Request another review.

Treat code review as a collaborative quality process.

## 20.4 What If a Production Bug Is Found?

1. Understand impact and affected users.
2. Follow incident escalation procedures.
3. Reproduce or isolate the problem.
4. Inspect logs, metrics and recent changes.
5. Identify the root cause.
6. Apply a safe mitigation.
7. Develop a permanent fix.
8. Test regression scenarios.
9. Deploy and monitor.
10. Document RCA and preventive actions.

## 20.5 How Do You Handle Technical Debt?

- Identify areas causing recurring defects or slow development.
- Assess business impact and engineering risk.
- Prioritize with stakeholders.
- Add targeted refactoring to the backlog.
- Introduce tests before changing critical code.
- Refactor incrementally.
- Track whether maintainability improves.

## 20.6 How Do You Ensure Code Quality?

- Follow coding standards.
- Apply SOLID and clean-code principles.
- Write unit and integration tests.
- Use static code analysis.
- Perform peer reviews.
- Validate performance and security.
- Monitor production defects.
- Refactor problematic areas.

## 20.7 How Do You Handle a Failed Deployment?

1. Identify deployment stage and failure reason.
2. Assess whether production traffic is affected.
3. Follow rollback or mitigation procedures.
4. Verify application health.
5. Review logs and pipeline output.
6. Fix the root cause.
7. Re-test and re-deploy safely.
8. Document the incident and preventive action.

---

# 21. SDLC Interview Questions

## 21.1 Basic Questions

1. What is SDLC?
2. What are the different phases of SDLC?
3. Why is SDLC important?
4. What is the difference between Waterfall and Agile?
5. What is the Spiral Model?
6. What is the V-Model?
7. What is iterative development?
8. What is incremental development?
9. What are functional and non-functional requirements?
10. What is HLD vs LLD?

## 21.2 Agile and Scrum Questions

11. What is Agile methodology?
12. What is Scrum?
13. What are Scrum roles?
14. What is a Sprint?
15. What is Sprint Planning?
16. What is a Daily Scrum?
17. What is Sprint Review?
18. What is Sprint Retrospective?
19. What is Product Backlog?
20. What is Sprint Backlog?
21. What is a user story?
22. What are acceptance criteria?
23. What is Definition of Done?
24. What is Definition of Ready?
25. What are story points?
26. What is Sprint velocity?
27. What is a burndown chart?
28. What is the difference between Scrum and Kanban?

## 21.3 Development Practice Questions

29. What is TDD?
30. Explain Red-Green-Refactor.
31. What is BDD?
32. What is ATDD?
33. TDD vs BDD vs ATDD?
34. What is pair programming?
35. What is trunk-based development?
36. What is feature flagging?
37. What is refactoring?
38. What is technical debt?
39. What is contract-first development?
40. What are DRY, KISS and YAGNI?

## 21.4 Git and Code Review Questions

41. What is Git?
42. What is GitFlow?
43. What is GitHub Flow?
44. Merge vs Rebase?
45. How do you resolve merge conflicts?
46. What is a Pull Request?
47. What do you check during code review?
48. What is static code analysis?
49. What is SonarQube?
50. What is code coverage?

## 21.5 Testing Questions

51. What is unit testing?
52. What is integration testing?
53. What is regression testing?
54. Smoke vs Sanity testing?
55. Functional vs Non-functional testing?
56. What is performance testing?
57. Load vs Stress testing?
58. What is security testing?
59. Explain the testing pyramid.
60. What is UAT?

## 21.6 CI/CD and Deployment Questions

61. What is CI/CD?
62. Explain a typical CI/CD pipeline.
63. What is Jenkins?
64. What is a build artifact?
65. Maven vs Gradle?
66. Continuous Delivery vs Continuous Deployment?
67. Rolling vs Blue-Green deployment?
68. What is Canary deployment?
69. What is zero-downtime deployment?
70. How do you handle rollback?
71. How do you manage database migrations?
72. What is Infrastructure as Code?

## 21.7 Production and Scenario-Based Questions

73. How do you troubleshoot a production issue?
74. How do you perform Root Cause Analysis?
75. Incident vs Problem vs Change?
76. What are SLA, SLO and SLI?
77. What is observability?
78. What are logs, metrics and traces?
79. How do you handle changing requirements?
80. How do you handle a failed deployment?
81. How do you reduce production defects?
82. How do you manage technical debt?
83. How do you improve application delivery speed?
84. How do you coordinate with QA and DevOps?
85. How do you ensure backward compatibility?

---

# 22. Final Interview Preparation Checklist

## 22.1 SDLC Fundamentals

- [ ] Understand SDLC phases.
- [ ] Waterfall, Agile, Spiral and V-Model.
- [ ] HLD vs LLD.
- [ ] Functional vs Non-functional requirements.
- [ ] Requirement traceability.

## 22.2 Agile and Scrum

- [ ] Agile principles.
- [ ] Scrum roles and responsibilities.
- [ ] Sprint events.
- [ ] Product and Sprint Backlog.
- [ ] User stories and acceptance criteria.
- [ ] Definition of Ready and Done.
- [ ] Story points and velocity.
- [ ] Scrum vs Kanban.

## 22.3 Development Practices

- [ ] TDD, BDD and ATDD.
- [ ] Pair programming.
- [ ] Trunk-based development.
- [ ] Feature flags.
- [ ] Refactoring.
- [ ] SOLID, DRY, KISS and YAGNI.
- [ ] Contract-first development.
- [ ] Technical debt management.

## 22.4 Git and Code Quality

- [ ] Git commands.
- [ ] GitFlow and GitHub Flow.
- [ ] Merge vs Rebase.
- [ ] Merge conflict resolution.
- [ ] Pull request workflow.
- [ ] Code review.
- [ ] SonarQube and static analysis.
- [ ] Code coverage.

## 22.5 Testing

- [ ] Unit testing with JUnit and Mockito.
- [ ] Integration testing.
- [ ] API testing.
- [ ] Regression testing.
- [ ] Smoke and sanity testing.
- [ ] Performance and security testing.
- [ ] Testing pyramid.

## 22.6 CI/CD and Deployment

- [ ] CI/CD workflow.
- [ ] Jenkins or GitHub Actions.
- [ ] Maven and Gradle.
- [ ] Build artifacts.
- [ ] Docker basics.
- [ ] Kubernetes basics.
- [ ] Rolling deployment.
- [ ] Blue-Green deployment.
- [ ] Canary deployment.
- [ ] Rollback and database migrations.

## 22.7 Production Engineering

- [ ] Production issue troubleshooting.
- [ ] Root Cause Analysis.
- [ ] Logs, metrics and traces.
- [ ] SLA, SLO and SLI.
- [ ] Incident management.
- [ ] Backward compatibility.
- [ ] Release management.

## 22.8 Priority for 5+ Years Experience

| Priority | Topics | Expected Knowledge |
|---|---|---|
| Must Master | SDLC, Agile, Scrum | Explain the complete development lifecycle |
| Must Master | Feature development workflow | Explain your end-to-end ownership |
| Must Master | Git, PR and code review | Explain real collaboration workflows |
| Must Master | TDD, testing and quality | Explain how you prevent defects |
| Must Master | CI/CD | Explain build, validation and deployment |
| Must Master | Production support and RCA | Explain real troubleshooting scenarios |
| Important | Deployment strategies | Compare rollout and rollback approaches |
| Important | DevOps and Docker | Explain development-to-production workflow |
| Important | DORA and engineering metrics | Explain delivery and reliability metrics |
| Good to Know | Spiral, V-Model, Mob Programming | Understand concepts and use cases |

---

# Final Goal

For a 5+ years Java Full Stack Developer interview, you should be able to explain more than definitions.

You should confidently describe:

1. How a requirement becomes a user story.
2. How you estimate and plan development work.
3. How you design and implement a feature.
4. How you write tests and validate functionality.
5. How Git, PRs and code reviews work in your team.
6. How CI/CD pipelines build and deploy applications.
7. How you handle production defects and rollbacks.
8. How you improve code quality, performance and maintainability.

**Interview tip:** Prepare at least two real project examples: one end-to-end feature delivery and one production issue you investigated and resolved. These examples make SDLC answers much stronger than memorized definitions.