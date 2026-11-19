# Angular 18 — Complete Learning & Interview Preparation Guide

**Target:** 5+ Years Experience | Full Stack Developer | Product-Based Companies  
**Framework:** Angular 18  
**Example Application:** Employee Management Portal  
**Technology Stack:** Angular 18, TypeScript, RxJS, HTML5, CSS3, REST APIs, Spring Boot

---

# TABLE OF CONTENTS

## Part 1 — Fundamentals

1. [Angular Fundamentals and Architecture](#module-1--angular-fundamentals-and-architecture)
2. [Angular CLI and Project Structure](#module-2--angular-cli-and-project-structure)
3. [TypeScript and JavaScript Essentials](#module-3--typescript-and-javascript-essentials)
4. [Components and Component Metadata](#module-4--components-and-component-metadata)
5. [Templates and Data Binding](#module-5--templates-and-data-binding)
6. [Directives](#module-6--directives)
7. [Pipes](#module-7--pipes)
8. [Styling and View Encapsulation](#module-8--styling-and-view-encapsulation)

## Part 2 — Component Architecture

9. [Component Communication](#module-9--component-communication)
10. [Content Projection and Template Rendering](#module-10--content-projection-and-template-rendering)
11. [ViewChild, ViewChildren and Content Queries](#module-11--viewchild-viewchildren-and-content-queries)
12. [Angular Lifecycle Hooks](#module-12--angular-lifecycle-hooks)
13. [Services and Dependency Injection](#module-13--services-and-dependency-injection)
14. [Advanced Dependency Injection](#module-14--advanced-dependency-injection)

## Part 3 — Reactive Programming and Application Features

15. [Change Detection and Zone.js](#module-15--change-detection-and-zonejs)
16. [Angular Signals](#module-16--angular-signals)
17. [RxJS and Reactive Programming](#module-17--rxjs-and-reactive-programming)
18. [Routing and Navigation](#module-18--routing-and-navigation)
19. [Route Guards and Resolvers](#module-19--route-guards-and-resolvers)
20. [HTTP Client and Interceptors](#module-20--http-client-and-interceptors)
21. [Forms — Template-Driven and Reactive](#module-21--forms--template-driven-and-reactive)
22. [Custom Form Controls and Validation](#module-22--custom-form-controls-and-validation)
23. [State Management](#module-23--state-management)

## Part 4 — Angular 18 and Advanced Development

24. [Angular 18 Built-in Control Flow](#module-24--angular-18-built-in-control-flow)
25. [Lazy Loading and Code Splitting](#module-25--lazy-loading-and-code-splitting)
26. [Performance Optimization](#module-26--performance-optimization)
27. [Angular Security](#module-27--angular-security)
28. [Application Architecture and Reusable Components](#module-28--application-architecture-and-reusable-components)
29. [Angular 18 SSR, Hydration and Event Replay](#module-29--angular-18-ssr-hydration-and-event-replay)
30. [Zoneless Change Detection](#module-30--zoneless-change-detection)
31. [Application Initialization and Environment Configuration](#module-31--application-initialization-and-environment-configuration)
32. [Location Service and Browser Navigation](#module-32--location-service-and-browser-navigation)
33. [Dynamic Components and Directives](#module-33--dynamic-components-and-directives)
34. [Angular Material, CDK and UI Architecture](#module-34--angular-material-cdk-and-ui-architecture)
35. [Testing and Debugging](#module-35--testing-and-debugging)
36. [Build, Deployment and CI/CD](#module-36--build-deployment-and-cicd)
37. [Advanced Angular Concepts](#module-37--advanced-angular-concepts)

## Part 5 — Interview and Practical Preparation

38. [Real-World Scenarios and Coding Problems](#module-38--real-world-scenarios-and-coding-problems)
39. [Angular 18 Interview Questions and Answers](#module-39--angular-18-interview-questions-and-answers)
40. [5+ Years Experience Preparation Checklist](#module-40--5-years-experience-preparation-checklist)

---

# PART 1 — FUNDAMENTALS

# MODULE 1 — Angular Fundamentals and Architecture

## 1.1 What is Angular?

Angular is a TypeScript-based frontend framework developed by Google for building scalable, maintainable, single-page applications.

### Important features

- Component-based architecture.
- TypeScript support.
- Dependency Injection.
- Routing and navigation.
- RxJS and reactive programming.
- Reactive and template-driven forms.
- Built-in directives and pipes.
- HTTP Client.
- Change detection.
- Server-side rendering.
- Lazy loading.
- Signals.
- Standalone components.
- Built-in template control flow.
- Deferred loading.

## 1.2 What is a Single Page Application?

A Single Page Application (SPA) loads the application shell once and updates the displayed content dynamically without reloading the entire browser page.

### Traditional application

1. Browser requests a page.
2. Server processes the request.
3. Server returns complete HTML.
4. Browser reloads the page.

### Angular SPA

1. Browser loads the Angular application.
2. Angular initializes the application.
3. Angular Router handles navigation.
4. Components render the requested content.
5. HTTP Client communicates with backend APIs.

## 1.3 Angular Architecture

| Concept | Purpose |
|---|---|
| Component | Controls a portion of the UI |
| Template | Defines HTML presentation |
| Metadata | Configures Angular classes |
| NgModule | Groups declarations and providers in module-based applications |
| Directive | Changes DOM structure or behavior |
| Pipe | Transforms displayed data |
| Service | Contains reusable business logic |
| Dependency Injection | Provides dependencies |
| Injector | Resolves dependencies |
| Router | Handles navigation |
| Observable | Represents asynchronous streams |
| Signal | Holds reactive state |

## 1.4 Angular Application Architecture

```text
Angular Application
│
├── App Component
│   ├── Header Component
│   ├── Sidebar Component
│   └── Router Outlet
│       ├── Dashboard Component
│       ├── Employee Component
│       └── Department Component
│
├── Core
│   ├── Authentication Service
│   ├── HTTP Interceptors
│   ├── Route Guards
│   └── Application Configuration
│
├── Features
│   ├── Employee
│   ├── Department
│   └── Dashboard
│
├── Shared
│   ├── Components
│   ├── Directives
│   └── Pipes
│
├── Models
├── State Management
└── Utilities
```

## 1.5 Angular 17 vs Angular 18

| Feature | Angular 17 | Angular 18 |
|---|---|---|
| Standalone APIs | Supported | Supported |
| Built-in control flow | Stable | Stable |
| @defer | Stable | Stable |
| Signals | Stable | Stable |
| Signal inputs | Developer Preview | Developer Preview |
| Zoneless change detection | Experimental APIs evolving | Developer Preview API |
| Event Replay | Not generally available | Developer Preview |
| SSR | Hydration supported | Improved SSR and hydration |
| i18n hydration | Limited | Developer Preview support |
| Material 3 | Supported | Stable |
| Build system | esbuild-based application builder | Continued improvements |
| Template @let | Not available in Angular 17 | Developer Preview in Angular 18 |

### Important Angular 18 features

**1. Zoneless Change Detection**

Allows Angular applications to run without relying on Zone.js to schedule change detection.

**2. Event Replay**

Captures user events that happen before hydration completes and replays them after hydration.

**3. Improved SSR**

Improves server rendering, hydration and internationalization support.

**4. Stable Built-in Control Flow**

The `@if`, `@for` and `@switch` syntax is production-ready.

**5. Stable Deferrable Views**

The `@defer` syntax is production-ready.

**6. Material 3**

Angular Material 3 support is stable.

---

# MODULE 2 — Angular CLI and Project Structure

## 2.1 Prerequisites

- Node.js
- npm
- TypeScript
- Angular CLI
- IDE such as VS Code

## 2.2 Install Angular CLI 18

```bash
npm install -g @angular/cli@18

ng version
```

Create a project:

```bash
ng new employee-portal
cd employee-portal
ng serve
```

Open:

```text
http://localhost:4200
```

### Create an NgModule-based project

```bash
ng new employee-portal --standalone=false
```

This is useful when learning or maintaining legacy module-based applications.

## 2.3 Important Angular CLI Commands

| Command | Purpose |
|---|---|
| ng new project-name | Create project |
| ng serve | Run development server |
| ng build | Build application |
| ng generate component name | Generate component |
| ng generate service name | Generate service |
| ng generate directive name | Generate directive |
| ng generate pipe name | Generate pipe |
| ng generate guard name | Generate guard |
| ng generate interceptor name | Generate interceptor |
| ng generate module name | Generate NgModule |
| ng generate library name | Generate library |
| ng test | Execute unit tests |
| ng lint | Run configured linting |
| ng update | Update dependencies |

Short commands:

```bash
ng g c employee
ng g s employee
ng g d highlight
ng g p salary
ng g guard auth
ng g interceptor auth
ng g m employee
ng g library shared-ui
```

## 2.4 Angular Project Structure

```text
employee-portal/
│
├── src/
│   ├── app/
│   │   ├── core/
│   │   │   ├── services/
│   │   │   ├── guards/
│   │   │   └── interceptors/
│   │   │
│   │   ├── features/
│   │   │   ├── employees/
│   │   │   ├── departments/
│   │   │   └── dashboard/
│   │   │
│   │   ├── shared/
│   │   │   ├── components/
│   │   │   ├── directives/
│   │   │   └── pipes/
│   │   │
│   │   ├── app.component.ts
│   │   ├── app.config.ts
│   │   └── app.routes.ts
│   │
│   ├── assets/
│   ├── environments/
│   ├── index.html
│   ├── main.ts
│   └── styles.scss
│
├── angular.json
├── package.json
├── package-lock.json
├── tsconfig.json
└── tsconfig.app.json
```

## 2.5 Important Configuration Files

| File | Responsibility |
|---|---|
| main.ts | Application bootstrap |
| app.config.ts | Application-level providers |
| app.routes.ts | Route configuration |
| app.component.ts | Root component |
| angular.json | CLI configuration |
| package.json | Dependencies and scripts |
| tsconfig.json | TypeScript configuration |
| environment.ts | Environment-specific configuration |

## 2.6 Standalone Application Bootstrap

**main.ts**

```typescript
import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';

bootstrapApplication(AppComponent, appConfig)
  .catch(error => console.error(error));
```

**app.config.ts**

```typescript
import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient()
  ]
};
```

## 2.7 NgModule-Based Bootstrap

**app.module.ts**

```typescript
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {}
```

**main.ts**

```typescript
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';

platformBrowserDynamic()
  .bootstrapModule(AppModule)
  .catch(error => console.error(error));
```

### Interview question: bootstrapApplication vs bootstrapModule

| bootstrapApplication | bootstrapModule |
|---|---|
| Standalone bootstrap | NgModule-based bootstrap |
| Bootstraps a root component | Bootstraps an NgModule |
| Uses ApplicationConfig | Uses NgModule providers/imports |
| Less module boilerplate | Suitable for legacy module architecture |

---

# MODULE 3 — TypeScript and JavaScript Essentials

Angular relies heavily on TypeScript.

## 3.1 Basic Types

```typescript
let employeeName: string = 'Naren';
let employeeId: number = 101;
let isActive: boolean = true;

let skills: string[] = [
  'Java',
  'Angular',
  'Spring Boot'
];
```

## 3.2 Interface

```typescript
export interface Employee {
  id: number;
  name: string;
  email: string;
  salary: number;
  department?: string;
}
```

## 3.3 Type vs Interface

| Interface | Type |
|---|---|
| Defines object contracts | Defines aliases for different types |
| Supports declaration merging | Does not support declaration merging |
| Supports extends | Supports intersections using & |
| Commonly used for API models | Useful for unions, tuples and complex aliases |

```typescript
interface Employee {
  id: number;
  name: string;
}

type Status = 'ACTIVE' | 'INACTIVE';

type EmployeeResponse = Employee & {
  status: Status;
};
```

## 3.4 Classes and Inheritance

```typescript
class Person {

  constructor(
    public name: string,
    public age: number
  ) {}

  display(): void {
    console.log(this.name);
  }
}

class Employee extends Person {

  constructor(
    name: string,
    age: number,
    public salary: number
  ) {
    super(name, age);
  }
}
```

## 3.5 Access Modifiers

| Modifier | Accessibility |
|---|---|
| public | Anywhere |
| private | Within declaring class |
| protected | Class and subclasses |
| readonly | Cannot be reassigned after initialization |

## 3.6 Generics

```typescript
function getData<T>(data: T): T {
  return data;
}

const name = getData<string>('Angular');
```

Reusable API response:

```typescript
export interface ApiResponse<T> {
  data: T;
  message: string;
  success: boolean;
}
```

## 3.7 Optional Chaining and Nullish Coalescing

```typescript
const department = employee?.department;

const salary = employee.salary ?? 0;
```

Difference:

- `?.` prevents null/undefined property-access errors.
- `??` uses fallback only for null or undefined.
- `||` uses fallback for all falsy values.

## 3.8 JavaScript Concepts

Important interview topics:

- var, let and const
- Hoisting
- Closures
- Scope
- Arrow functions
- this keyword
- Promises
- async/await
- Event loop
- Call stack
- Microtask and macrotask queues
- Destructuring
- Spread and rest operators
- Shallow copy vs deep copy
- map, filter, reduce, find, some and every

Example:

```typescript
const employees = [
  { id: 1, name: 'John', salary: 50000 },
  { id: 2, name: 'David', salary: 70000 },
  { id: 3, name: 'Smith', salary: 60000 }
];

const highSalaryEmployees = employees.filter(
  employee => employee.salary > 55000
);

const totalSalary = employees.reduce(
  (total, employee) => total + employee.salary,
  0
);
```

---

# MODULE 4 — Components and Component Metadata

## 4.1 What is a Component?

A component controls a particular section of the UI.

It consists of:

1. TypeScript class
2. HTML template
3. CSS/SCSS styles
4. Component metadata

## 4.2 Component Metadata

The `@Component` decorator configures the component.

Important properties:

| Property | Purpose |
|---|---|
| selector | HTML selector used to render component |
| template | Inline HTML |
| templateUrl | External HTML file |
| styles | Inline CSS |
| styleUrls | External CSS/SCSS files |
| imports | Dependencies used in standalone component |
| providers | Component-level DI providers |
| standalone | Declares standalone status |
| encapsulation | Controls style encapsulation |
| changeDetection | Configures change detection strategy |
| host | Host element bindings and listeners |

## 4.3 Create Component

```bash
ng generate component employee
```

**employee.component.ts**

```typescript
import { Component } from '@angular/core';

@Component({
  selector: 'app-employee',
  standalone: true,
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss'
  ]
})
export class EmployeeComponent {

  employeeName = 'John';

  displayMessage(): void {
    console.log('Employee component');
  }
}
```

**employee.component.html**

```html
<h2>Employee Details</h2>

<p>{{ employeeName }}</p>

<button (click)="displayMessage()">
  Click Me
</button>
```

## 4.4 Inline Template and Styles

```typescript
@Component({
  selector: 'app-employee',
  standalone: true,

  template: `
    <h2>Employee Details</h2>
    <p>{{ employeeName }}</p>
  `,

  styles: [`
    h2 {
      color: blue;
    }
  `]
})
export class EmployeeComponent {
  employeeName = 'John';
}
```

## 4.5 Component Registration

In a standalone application:

```typescript
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [EmployeeComponent],
  template: `<app-employee />`
})
export class AppComponent {}
```

In an NgModule-based application:

```typescript
@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent
  ],
  imports: [
    BrowserModule
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {}
```

**Important Angular 18 version detail:** Angular CLI can generate standalone applications by default, but the `standalone` decorator metadata still defaults to `false` in Angular 18. Explicitly use `standalone: true` for standalone declarations.

## 4.6 Component vs Directive

| Component | Directive |
|---|---|
| Has a template | Does not require a template |
| Controls a view | Changes behavior or structure |
| Uses @Component | Uses @Directive |
| Used to render UI | Used to enhance existing UI |

---

# MODULE 5 — Templates and Data Binding

## 5.1 Interpolation

Interpolation displays component data in HTML.

```typescript
employeeName = 'Narendar';
```

```html
<h2>{{ employeeName }}</h2>
```

The expression inside `{{ }}` is evaluated by Angular.

## 5.2 Template Expression vs Template Statement

**Template expression:**

```html
<h2>{{ employeeName }}</h2>
```

**Template statement:**

```html
<button (click)="saveEmployee()">
  Save
</button>
```

| Template Expression | Template Statement |
|---|---|
| Reads/calculates values | Performs actions |
| Used in interpolation and bindings | Used in event bindings |
| Does not allow assignments as ordinary expression logic | Can perform permitted assignments and calls |

## 5.3 Property Binding

```typescript
isDisabled = true;
```

```html
<button [disabled]="isDisabled">
  Submit
</button>
```

Important rule:

```html
<app-user [username]="username" />
```

Passes the component variable.

```html
<app-user [username]="'Narendar'" />
```

Passes a string literal.

```html
<app-user username="Narendar" />
```

Passes a static string attribute value, which Angular uses as an input value when applicable.

## 5.4 Event Binding

```html
<button (click)="saveEmployee()">
  Save
</button>
```

Angular also supports the `on-` event syntax:

```html
<button on-click="saveEmployee()">
  Save
</button>
```

The conventional syntax is `(click)`.

## 5.5 Two-Way Data Binding

Two-way binding combines property binding and event binding.

Syntax:

```html
[(ngModel)]="employeeName"
```

Import FormsModule:

```typescript
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [FormsModule],
  template: `
    <input [(ngModel)]="employeeName">
    <p>{{ employeeName }}</p>
  `
})
export class EmployeeComponent {
  employeeName = '';
}
```

## 5.6 Understanding ngModel and ngModelChange

Two-way binding is conceptually equivalent to:

```html
<input
  [ngModel]="firstName"
  (ngModelChange)="firstName = $event">
```

You can also invoke a method:

```html
<input
  [ngModel]="firstName"
  (ngModelChange)="firstNameChanged($event)">
```

Component:

```typescript
firstName = '';

firstNameChanged(value: string): void {
  this.firstName = value;
  console.log('Updated:', value);
}
```

When multiple event handlers are written, their declared template order matters.

## 5.7 Template Reference Variables

A template reference variable provides access to an element, directive, component or template reference.

```html
<input #employeeInput>

<button (click)="employeeInput.focus()">
  Focus Input
</button>
```

Access a form:

```html
<form #employeeForm="ngForm">
  ...
</form>
```

## 5.8 Attribute Binding vs Property Binding

**Property binding:**

```html
<button [disabled]="isDisabled">
  Submit
</button>
```

**Attribute binding:**

```html
<td [attr.colspan]="columnSpan">
  Employee
</td>
```

Important distinction:

- `[disabled]` binds the DOM property.
- `[attr.disabled]` binds the HTML attribute.
- `[attr.aria-label]` is useful for accessibility attributes.
- `[innerText]` and `[innerHTML]` are property bindings.

Example:

```html
<div [innerText]="employeeName"></div>

<div [innerHTML]="employeeDescription"></div>
```

Angular sanitizes HTML in applicable binding contexts. Never trust untrusted HTML.

## 5.9 ngNonBindable

`ngNonBindable` prevents Angular from evaluating bindings and directives inside an element.

```html
<div ngNonBindable>
  {{ employeeName }}
</div>
```

Output:

```text
{{ employeeName }}
```

Useful for displaying Angular syntax in documentation or tutorials.

---

# MODULE 6 — Directives

Directives are used to change DOM structure, appearance or behavior.

## 6.1 Types of Directives

| Directive | Purpose | Example |
|---|---|---|
| Component | Renders a view | EmployeeComponent |
| Structural | Changes DOM structure | ngIf, ngFor |
| Attribute | Changes appearance/behavior | ngClass, ngStyle |
| Custom | Developer-defined functionality | HighlightDirective |

## 6.2 Structural Directives

### ngIf

```html
<p *ngIf="isActive">
  Employee is active
</p>
```

### ngIf with else

```html
<p *ngIf="isActive; else inactiveBlock">
  Active Employee
</p>

<ng-template #inactiveBlock>
  <p>Inactive Employee</p>
</ng-template>
```

### ngIf with then and else

```html
<div *ngIf="isDone; then thenBlock else elseBlock">
</div>

<ng-template #thenBlock>
  <p>Completed</p>
</ng-template>

<ng-template #elseBlock>
  <p>Pending</p>
</ng-template>
```

### Important ng-template clarification

An `ng-template` is not rendered automatically.

This is valid:

```html
<ng-template [ngIf]="isActive">
  <p>Active</p>
</ng-template>
```

However, the following is not the usual valid shorthand pattern:

```html
<ng-template *ngIf="isActive">
```

Use `[ngIf]` on `ng-template`, or the usual `*ngIf` syntax on a regular element.

## 6.3 ngFor

```typescript
employees = [
  { id: 1, name: 'John' },
  { id: 2, name: 'David' },
  { id: 3, name: 'Smith' }
];
```

```html
<div *ngFor="let employee of employees">
  {{ employee.name }}
</div>
```

### ngFor Local Variables

| Variable | Purpose |
|---|---|
| index | Current index |
| first | First item |
| last | Last item |
| even | Even index |
| odd | Odd index |
| count | Total number of items |

```html
<div
  *ngFor="
    let employee of employees;
    let i = index;
    let first = first;
    let last = last;
    let even = even;
    let odd = odd;
    let count = count
  ">

  {{ i + 1 }} - {{ employee.name }}

</div>
```

### trackBy

```typescript
trackByEmployeeId(index: number, employee: Employee): number {
  return employee.id;
}
```

```html
<div
  *ngFor="
    let employee of employees;
    trackBy: trackByEmployeeId
  ">
  {{ employee.name }}
</div>
```

trackBy helps Angular reuse existing DOM nodes when items are added, removed or reordered.

## 6.4 ngSwitch

```html
<div [ngSwitch]="status">

  <p *ngSwitchCase="'ACTIVE'">
    Employee Active
  </p>

  <p *ngSwitchCase="'INACTIVE'">
    Employee Inactive
  </p>

  <p *ngSwitchDefault>
    Unknown Status
  </p>

</div>
```

## 6.5 Attribute Directives

### ngClass

Supports string, array and object expressions.

```html
<div [ngClass]="'active'">
  Employee
</div>
```

```html
<div [ngClass]="['active', 'highlight']">
  Employee
</div>
```

```html
<div [ngClass]="{
  active: isActive,
  disabled: !isActive
}">
  Employee
</div>
```

Using a method:

```html
<div [ngClass]="getClass()">
  Employee
</div>
```

### ngStyle

```html
<p [ngStyle]="{
  color: 'purple',
  fontSize: '20px',
  fontWeight: 'bold'
}">
  Multiple Styles
</p>
```

Conditional styling:

```html
<p [ngStyle]="{
  color: isActive ? 'green' : 'red'
}">
  Employee Status
</p>
```

## 6.6 Custom Structural Directive

A structural directive manipulates the DOM by controlling embedded views.

The `*` syntax tells Angular to transform the directive into an `ng-template` and provide a TemplateRef.

Example:

```typescript
import {
  Directive,
  Input,
  TemplateRef,
  ViewContainerRef
} from '@angular/core';

@Directive({
  selector: '[appIf]',
  standalone: true
})
export class AppIfDirective {

  constructor(
    private templateRef: TemplateRef<unknown>,
    private viewContainer: ViewContainerRef
  ) {}

  @Input()
  set appIf(condition: boolean) {

    this.viewContainer.clear();

    if (condition) {
      this.viewContainer.createEmbeddedView(
        this.templateRef
      );
    }
  }
}
```

Usage:

```html
<div *appIf="isActive">
  Employee is active
</div>
```

The directive receives:

- TemplateRef: Reference to the embedded template.
- ViewContainerRef: Container where the embedded view is created.

## 6.7 Custom Attribute Directive

```typescript
import {
  Directive,
  ElementRef,
  HostListener,
  HostBinding
} from '@angular/core';

@Directive({
  selector: '[appHighlight]',
  standalone: true
})
export class HighlightDirective {

  @HostBinding('style.border')
  border = '';

  constructor(private elementRef: ElementRef) {}

  @HostListener('mouseover')
  onMouseOver(): void {
    this.border = '5px solid green';
  }

  @HostListener('mouseleave')
  onMouseLeave(): void {
    this.border = '';
  }
}
```

Usage:

```html
<p appHighlight>
  Employee Name
</p>
```

### HostListener and HostBinding

| API | Purpose |
|---|---|
| HostListener | Listens to host element events |
| HostBinding | Binds a property or attribute on host element |

For more complex styling, prefer Renderer2 or appropriate host bindings instead of unsafe direct DOM manipulation.

## 6.8 ng-container

`ng-container` groups elements without creating an actual DOM element.

Example: Multiple structural conditions.

```html
<ng-container *ngIf="isLoggedIn">

  <div *ngIf="hasPermission">
    Employee Details
  </div>

</ng-container>
```

You generally cannot apply multiple structural directive shorthand expressions to the same element.

Use `ng-container` to create separate structural layers.

## 6.9 Structural vs Attribute Directives

| Structural | Attribute |
|---|---|
| Changes DOM structure | Changes element behavior or appearance |
| Creates/removes views | Modifies existing element |
| ngIf, ngFor | ngClass, ngStyle |
| Often uses TemplateRef | Often uses ElementRef/Renderer2 |

---

# MODULE 7 — Pipes

Pipes transform data before displaying it.

## 7.1 Built-in Pipes

| Pipe | Purpose |
|---|---|
| DatePipe | Formats dates |
| CurrencyPipe | Formats currency |
| DecimalPipe | Formats numbers |
| PercentPipe | Converts numbers to percentages |
| UpperCasePipe | Uppercase |
| LowerCasePipe | Lowercase |
| TitleCasePipe | Title case |
| JsonPipe | JSON representation |
| SlicePipe | Extracts a portion of a string or array |
| AsyncPipe | Handles Observable or Promise values |

## 7.2 Pipe Examples

```typescript
employeeName = 'narendar';

salary = 75000;

joiningDate = new Date();

employeeSkills = [
  'Java',
  'Angular',
  'Spring Boot',
  'SQL'
];
```

```html
<p>{{ employeeName | uppercase }}</p>

<p>{{ employeeName | lowercase }}</p>

<p>{{ employeeName | titlecase }}</p>

<p>{{ salary | currency:'INR' }}</p>

<p>{{ salary | number:'1.2-2' }}</p>

<p>{{ joiningDate | date:'dd-MM-yyyy' }}</p>

<p>{{ employeeSkills | slice:1:3 }}</p>

<p>{{ employeeSkills | json }}</p>
```

## 7.3 Pipe Chaining

Multiple pipes can be chained.

```html
<p>
  {{ employeeName | lowercase | titlecase }}
</p>
```

## 7.4 Custom Pipe

```typescript
import {
  Pipe,
  PipeTransform
} from '@angular/core';

@Pipe({
  name: 'employeeFormat',
  standalone: true
})
export class EmployeeFormatPipe implements PipeTransform {

  transform(value: string): string {
    return value
      ? value.toUpperCase()
      : '';
  }
}
```

Usage:

```html
{{ employeeName | employeeFormat }}
```

## 7.5 Pure vs Impure Pipes

| Pure Pipe | Impure Pipe |
|---|---|
| Default behavior | pure: false |
| Executes when primitive value or object reference changes | Can execute during every change detection cycle |
| Better performance | Potentially expensive |
| Recommended | Use only when necessary |

Example:

```typescript
@Pipe({
  name: 'employeeCount',
  standalone: true,
  pure: false
})
export class EmployeeCountPipe implements PipeTransform {

  transform(employees: Employee[]): number {
    return employees.length;
  }
}
```

Prefer immutable array updates instead of making a pipe impure.

```typescript
this.employees = [
  ...this.employees,
  newEmployee
];
```

## 7.6 AsyncPipe

```typescript
employees$ = this.employeeService.getEmployees();
```

```html
<div *ngFor="let employee of employees$ | async">
  {{ employee.name }}
</div>
```

AsyncPipe automatically subscribes, updates the view and unsubscribes when the view is destroyed.

---

# MODULE 8 — Styling and View Encapsulation

## 8.1 Component Styling

Angular supports:

- CSS
- SCSS
- Sass
- Less
- Inline styles

Example:

```bash
ng new employee-portal --style=scss
```

Component:

```typescript
@Component({
  selector: 'app-employee',
  standalone: true,
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent {}
```

## 8.2 View Encapsulation

View encapsulation controls how component styles affect elements inside and outside the component.

Angular supports three strategies.

### 1. Emulated

Default strategy.

Angular adds generated attributes to elements and styles to simulate component-level CSS isolation.

```typescript
@Component({
  selector: 'app-employee',
  standalone: true,
  encapsulation: ViewEncapsulation.Emulated,
  template: `<h2>Employee</h2>`,
  styles: [`h2 { color: blue; }`]
})
export class EmployeeComponent {}
```

### 2. ShadowDom

Uses the browser's native Shadow DOM.

```typescript
@Component({
  selector: 'app-employee',
  standalone: true,
  encapsulation: ViewEncapsulation.ShadowDom,
  template: `<h2>Employee</h2>`,
  styles: [`h2 { color: blue; }`]
})
export class EmployeeComponent {}
```

Shadow DOM provides actual DOM and style encapsulation boundaries.

### 3. None

Disables Angular's style encapsulation.

```typescript
@Component({
  selector: 'app-employee',
  standalone: true,
  encapsulation: ViewEncapsulation.None,
  template: `<h2>Employee</h2>`,
  styles: [`h2 { color: blue; }`]
})
export class EmployeeComponent {}
```

Styles can affect elements outside the component.

## 8.3 View Encapsulation Comparison

| Strategy | Behavior |
|---|---|
| Emulated | Simulated style encapsulation |
| ShadowDom | Native browser Shadow DOM |
| None | Global style behavior |

## 8.4 Shadow DOM Architecture

```text
Browser DOM
│
├── Main Document DOM
│   ├── Header
│   ├── Sidebar
│   └── Employee Component Host
│
└── Shadow Root
    ├── Component HTML
    └── Component CSS
```

The browser renders Shadow DOM and document DOM while maintaining their encapsulation boundaries.

---

# PART 2 — COMPONENT ARCHITECTURE

# MODULE 9 — Component Communication

## 9.1 Parent to Child — @Input

```typescript
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-employee-card',
  standalone: true,
  template: `
    <h3>{{ employee.name }}</h3>
    <p>{{ employee.salary }}</p>
  `
})
export class EmployeeCardComponent {

  @Input() employee!: Employee;
}
```

Parent:

```typescript
@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [EmployeeCardComponent],
  template: `
    <app-employee-card
      [employee]="selectedEmployee">
    </app-employee-card>
  `
})
export class EmployeeListComponent {

  selectedEmployee: Employee = {
    id: 101,
    name: 'John',
    email: 'john@example.com',
    salary: 75000
  };
}
```

## 9.2 Child to Parent — @Output

```typescript
import {
  Component,
  EventEmitter,
  Input,
  Output
} from '@angular/core';

@Component({
  selector: 'app-employee-card',
  standalone: true,
  template: `
    <button (click)="deleteEmployee()">
      Delete
    </button>
  `
})
export class EmployeeCardComponent {

  @Input() employeeId!: number;

  @Output() onSelection =
    new EventEmitter<void>();

  @Output() delete =
    new EventEmitter<number>();

  deleteEmployee(): void {
    this.delete.emit(this.employeeId);
    this.onSelection.emit();
  }
}
```

Parent:

```html
<app-employee-card
  [employeeId]="101"
  (delete)="deleteEmployee($event)"
  (onSelection)="handleSelection()">
</app-employee-card>
```

## 9.3 @Input Setter and Getter

Useful when input data requires transformation or validation.

```typescript
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-employee',
  standalone: true,
  template: `{{ employeeName }}`
})
export class EmployeeComponent {

  private _employeeName = '';

  @Input()
  set employeeName(value: string) {
    this._employeeName = value
      ? value.toUpperCase()
      : '';
  }

  get employeeName(): string {
    return this._employeeName;
  }
}
```

## 9.4 ngOnChanges

Runs when Angular detects changes to data-bound inputs.

```typescript
import {
  Component,
  Input,
  OnChanges,
  SimpleChanges
} from '@angular/core';

@Component({
  selector: 'app-employee',
  standalone: true,
  template: `Employee`
})
export class EmployeeComponent implements OnChanges {

  @Input() employeeId!: number;

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['employeeId']) {
      console.log(
        changes['employeeId'].previousValue,
        changes['employeeId'].currentValue
      );
    }
  }
}
```

## 9.5 Communication Between Unrelated Components

Use a shared service with RxJS.

```typescript
@Injectable({
  providedIn: 'root'
})
export class EmployeeCommunicationService {

  private employeeSubject =
    new BehaviorSubject<Employee | null>(null);

  employee$ = this.employeeSubject.asObservable();

  updateEmployee(employee: Employee): void {
    this.employeeSubject.next(employee);
  }
}
```

Component A:

```typescript
this.communicationService.updateEmployee(employee);
```

Component B:

```typescript
this.communicationService.employee$.subscribe(employee => {
  console.log(employee);
});
```

For simple shared state, Angular Signals are another option.

---

# MODULE 10 — Content Projection and Template Rendering

## 10.1 What is Content Projection?

Content projection allows a parent component to pass HTML content into a child component.

Angular uses `ng-content`.

**card.component.ts**

```typescript
@Component({
  selector: 'app-card',
  standalone: true,
  template: `
    <div class="card">
      <ng-content></ng-content>
    </div>
  `
})
export class CardComponent {}
```

Usage:

```html
<app-card>
  <h2>Employee Details</h2>
  <p>Employee information</p>
</app-card>
```

## 10.2 Multiple Content Projection Slots

```typescript
@Component({
  selector: 'app-card',
  standalone: true,
  template: `
    <header>
      <ng-content select="[card-header]"></ng-content>
    </header>

    <main>
      <ng-content></ng-content>
    </main>

    <footer>
      <ng-content select="[card-footer]"></ng-content>
    </footer>
  `
})
export class CardComponent {}
```

Usage:

```html
<app-card>

  <h2 card-header>
    Employee Header
  </h2>

  <p>
    Employee Details
  </p>

  <button card-footer>
    Save
  </button>

</app-card>
```

## 10.3 ngProjectAs

`ngProjectAs` allows an element to match a projection selector even when the element itself does not naturally match it.

```html
<app-card>

  <ng-container ngProjectAs="[card-header]">
    <h2>Employee Header</h2>
  </ng-container>

  <p>Employee Details</p>

</app-card>
```

Useful when wrapping content with `ng-container`.

## 10.4 ng-template

`ng-template` defines a template that is not rendered automatically.

```html
<ng-template #employeeTemplate>
  <h2>Employee Details</h2>
</ng-template>
```

To render it, use:

- ngTemplateOutlet
- ViewContainerRef.createEmbeddedView()

## 10.5 ngTemplateOutlet

```html
<ng-template #employeeTemplate>
  <h2>Employee Details</h2>
</ng-template>

<ng-container
  [ngTemplateOutlet]="employeeTemplate">
</ng-container>
```

## 10.6 Passing Data to ngTemplateOutlet

```html
<ng-template
  #messageTemplate
  let-value="value">

  <p>Value received: {{ value }}</p>

</ng-template>

<ng-container
  [ngTemplateOutlet]="messageTemplate"
  [ngTemplateOutletContext]="{
    value: '1000'
  }">
</ng-container>
```

## 10.7 Passing Multiple Values

```html
<ng-template
  #template3
  let-name="nameVar"
  let-message="messageVar">

  <p>Dear {{ name }}, {{ message }}</p>

</ng-template>

<ng-container
  [ngTemplateOutlet]="template3"
  [ngTemplateOutletContext]="{
    nameVar: 'Guest',
    messageVar: 'Welcome to our site'
  }">
</ng-container>
```

## 10.8 TemplateRef and Embedded Views

```typescript
import {
  Component,
  TemplateRef,
  ViewChild,
  ViewContainerRef
} from '@angular/core';

@Component({
  selector: 'app-employee',
  standalone: true,
  template: `
    <ng-template #sayHelloTemplate>
      <h2>Hello Employee</h2>
    </ng-template>

    <button (click)="loadTemplate()">
      Load Template
    </button>
  `
})
export class EmployeeComponent {

  @ViewChild('sayHelloTemplate', {
    read: TemplateRef
  })
  sayHelloTemplate!: TemplateRef<unknown>;

  @ViewChild('container', {
    read: ViewContainerRef
  })
  container!: ViewContainerRef;

  loadTemplate(): void {
    this.container.createEmbeddedView(
      this.sayHelloTemplate
    );
  }
}
```

The container should be defined in the template:

```html
<ng-container #container></ng-container>
```

---

# MODULE 11 — ViewChild, ViewChildren and Content Queries

## 11.1 ViewChild

ViewChild accesses an element, directive, component or template in the component's own view.

```typescript
import {
  Component,
  ElementRef,
  ViewChild,
  AfterViewInit
} from '@angular/core';

@Component({
  selector: 'app-search',
  standalone: true,
  template: `
    <input #searchInput>
  `
})
export class SearchComponent implements AfterViewInit {

  @ViewChild('searchInput')
  searchInput!: ElementRef<HTMLInputElement>;

  ngAfterViewInit(): void {
    this.searchInput.nativeElement.focus();
  }
}
```

## 11.2 ViewChild static

```typescript
@ViewChild('hello', { static: true })
hello!: ElementRef;
```

| static | Behavior |
|---|---|
| true | Query available before ngOnInit; does not update for later view changes |
| false | Query resolves before ngAfterViewInit and updates with view changes |

Use `static: false` for elements inside conditional or dynamically rendered views.

## 11.3 ViewChildren

ViewChildren returns a QueryList containing matching elements, directives or components.

```typescript
@ViewChildren(EmployeeCardComponent)
employeeCards!: QueryList<EmployeeCardComponent>;
```

```typescript
ngAfterViewInit(): void {
  this.employeeCards.forEach(card => {
    console.log(card);
  });
}
```

## 11.4 ContentChild

ContentChild accesses projected content inside `ng-content`.

```typescript
@ContentChild('projectedContent')
projectedContent!: ElementRef;
```

## 11.5 ContentChildren

```typescript
@ContentChildren(EmployeeCardComponent)
employeeCards!: QueryList<EmployeeCardComponent>;
```

## 11.6 QueryList

QueryList represents a collection of queried elements and provides methods such as:

- forEach()
- map()
- filter()
- find()
- first
- last
- length
- changes Observable

## 11.7 ViewChild vs ContentChild

| ViewChild | ContentChild |
|---|---|
| Queries own component view | Queries projected content |
| Uses ViewChild decorator | Uses ContentChild decorator |
| Accesses internal elements | Accesses parent-provided content |

## 11.8 Query Options

Important concepts:

- static
- read
- descendants
- QueryList
- ElementRef
- TemplateRef
- ViewContainerRef

Example:

```typescript
@ViewChild('container', {
  read: ViewContainerRef
})
container!: ViewContainerRef;
```

The `read` option tells Angular which token to retrieve from the matching element.

---

# MODULE 12 — Angular Lifecycle Hooks

## 12.1 Lifecycle Hook Execution

| Hook | Purpose |
|---|---|
| constructor | Class initialization and DI |
| ngOnChanges | Input changes |
| ngOnInit | Initial component initialization |
| ngDoCheck | Custom change detection |
| ngAfterContentInit | Projected content initialization |
| ngAfterContentChecked | Projected content check |
| ngAfterViewInit | Component view initialization |
| ngAfterViewChecked | Component view check |
| ngOnDestroy | Component destruction |

## 12.2 Lifecycle Flow

```text
Constructor
    |
    v
ngOnChanges
    |
    v
ngOnInit
    |
    v
ngDoCheck
    |
    v
ngAfterContentInit
    |
    v
ngAfterContentChecked
    |
    v
ngAfterViewInit
    |
    v
ngAfterViewChecked
    |
    v
ngOnDestroy
```

`ngOnChanges` may execute multiple times. The initial call happens before `ngOnInit` when Angular initializes input bindings.

## 12.3 Constructor vs ngOnInit

| Constructor | ngOnInit |
|---|---|
| TypeScript class constructor | Angular lifecycle hook |
| Used for DI and basic initialization | Used for component initialization |
| Runs during object construction | Runs after initial inputs are set |
| Not an Angular lifecycle hook | Angular lifecycle hook |

## 12.4 Subscription Cleanup

```typescript
private subscription = new Subscription();

ngOnDestroy(): void {
  this.subscription.unsubscribe();
}
```

Angular 16+ supports `takeUntilDestroyed`.

```typescript
import {
  Component,
  DestroyRef,
  inject
} from '@angular/core';

import {
  takeUntilDestroyed
} from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-employee',
  standalone: true,
  template: `Employee`
})
export class EmployeeComponent {

  private destroyRef = inject(DestroyRef);

  loadEmployees(): void {
    this.employeeService.getEmployees()
      .pipe(
        takeUntilDestroyed(this.destroyRef)
      )
      .subscribe(data => console.log(data));
  }
}
```

---

# MODULE 13 — Services and Dependency Injection

## 13.1 What is a Service?

A service is a reusable class that contains shared business logic and functionality.

Use cases:

- API communication
- Authentication
- Business logic
- Shared state
- Logging
- Notifications
- Caching

## 13.2 Create Service

```bash
ng generate service employee
```

**employee.service.ts**

```typescript
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private apiUrl =
    'https://api.example.com/employees';

  constructor(private http: HttpClient) {}

  getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.apiUrl);
  }

  getEmployeeById(id: number): Observable<Employee> {
    return this.http.get<Employee>(
      `${this.apiUrl}/${id}`
    );
  }

  createEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(
      this.apiUrl,
      employee
    );
  }

  updateEmployee(
    id: number,
    employee: Employee
  ): Observable<Employee> {

    return this.http.put<Employee>(
      `${this.apiUrl}/${id}`,
      employee
    );
  }

  deleteEmployee(id: number): Observable<void> {
    return this.http.delete<void>(
      `${this.apiUrl}/${id}`
    );
  }
}
```

## 13.3 Dependency Injection

Dependency Injection is a design pattern where dependencies are supplied to a class by an external injector instead of creating them manually.

### Without DI

```typescript
class EmployeeComponent {

  service = new EmployeeService();
}
```

Problems:

- Tight coupling
- Difficult unit testing
- Difficult to replace dependencies
- Manual object creation

### With DI

```typescript
@Component({
  selector: 'app-employee',
  standalone: true,
  template: `Employee`
})
export class EmployeeComponent {

  private employeeService =
    inject(EmployeeService);
}
```

## 13.4 inject() vs Constructor Injection

Constructor injection:

```typescript
constructor(
  private employeeService: EmployeeService
) {}
```

Functional injection:

```typescript
private employeeService =
  inject(EmployeeService);
```

Both use Angular DI.

`inject()` is allowed only in an injection context, such as constructors, field initializers and provider factories.

## 13.5 Is @Injectable Required?

`@Injectable()` is generally required for services that need Angular DI metadata or dependencies.

Angular-decorated classes such as components, directives and pipes already have their own Angular metadata.

However, do not assume every ordinary class can automatically be injected without proper provider registration and token configuration.

---

# MODULE 14 — Advanced Dependency Injection

This is a particularly important module for experienced Angular developers.

## 14.1 Angular Injector Hierarchy

Angular uses hierarchical dependency injection.

There are two main injector hierarchies:

1. EnvironmentInjector hierarchy.
2. ElementInjector hierarchy.

For module-based applications, ModuleInjector is also relevant.

Simplified architecture:

```text
Platform Injector
       |
       v
Root EnvironmentInjector
       |
       v
Route EnvironmentInjector
       |
       v
ElementInjector
       |
       v
Component / Directive
```

The exact lookup path depends on the component, route and injector configuration.

## 14.2 providedIn: root

```typescript
@Injectable({
  providedIn: 'root'
})
export class EmployeeService {}
```

- Provided through the root injector.
- Normally shared application-wide.
- Tree-shakable when unused.
- Recommended for application-level services.

## 14.3 Component-Level Providers

```typescript
@Component({
  selector: 'app-employee',
  standalone: true,
  providers: [EmployeeService],
  template: `Employee`
})
export class EmployeeComponent {}
```

A provider at component level creates a service instance in that component's injector scope.

Different component instances can receive different service instances.

## 14.4 providedIn Values

| Value | Purpose |
|---|---|
| root | Root injector |
| platform | Platform injector |
| any | Legacy behavior with separate instances in lazy module injector hierarchies |
| none | No automatic provider registration |

**Important:** `providedIn: 'any'` is deprecated. Understand it for legacy interview discussions, but prefer explicit provider scopes.

## 14.5 useClass

Uses a different implementation for an injection token.

```typescript
export abstract class Logger {
  abstract log(message: string): void;
}

@Injectable()
export class ConsoleLogger extends Logger {

  log(message: string): void {
    console.log(message);
  }
}
```

Provider:

```typescript
providers: [
  {
    provide: Logger,
    useClass: ConsoleLogger
  }
]
```

## 14.6 useValue

Provides a fixed value.

```typescript
export const APP_CONFIG = {
  apiUrl: 'https://api.example.com',
  production: false
};
```

```typescript
providers: [
  {
    provide: APP_CONFIG,
    useValue: APP_CONFIG
  }
]
```

For stronger typing and safer tokens, use InjectionToken.

## 14.7 InjectionToken

Useful when injecting objects, strings or configuration values.

```typescript
import { InjectionToken } from '@angular/core';

export const API_URL =
  new InjectionToken<string>('API_URL');
```

Register:

```typescript
providers: [
  {
    provide: API_URL,
    useValue: 'https://api.example.com'
  }
]
```

Inject:

```typescript
private apiUrl = inject(API_URL);
```

## 14.8 useFactory

Creates a dependency using a factory function.

```typescript
export function apiUrlFactory(): string {
  return 'https://api.example.com';
}
```

```typescript
providers: [
  {
    provide: API_URL,
    useFactory: apiUrlFactory
  }
]
```

## 14.9 useExisting

Creates an alias for an existing provider.

```typescript
providers: [
  ConsoleLogger,
  {
    provide: Logger,
    useExisting: ConsoleLogger
  }
]
```

Difference:

- useClass creates or resolves an implementation through a class provider.
- useExisting aliases an already registered provider.

## 14.10 Provider Strategies Summary

| Provider | Purpose |
|---|---|
| useClass | Maps token to class |
| useValue | Maps token to fixed value |
| useFactory | Creates value through function |
| useExisting | Aliases another provider |
| multi: true | Collects multiple providers for one token |

## 14.11 Multi Providers

```typescript
export const APP_PLUGINS =
  new InjectionToken<string[]>('APP_PLUGINS');
```

```typescript
providers: [
  {
    provide: APP_PLUGINS,
    useValue: 'Plugin A',
    multi: true
  },
  {
    provide: APP_PLUGINS,
    useValue: 'Plugin B',
    multi: true
  }
]
```

Inject:

```typescript
private plugins = inject(APP_PLUGINS);
```

Result:

```typescript
[
  'Plugin A',
  'Plugin B'
]
```

## 14.12 Provider Registration and Duplicate Tokens

When multiple non-multi providers register the same token in the same injector, the later registration takes precedence.

However, Angular resolves dependencies through an injector hierarchy. A nearer provider can shadow a provider from a parent injector.

Do not confuse these two behaviors.

## 14.13 DI Resolution Modifiers

| Modifier | Purpose |
|---|---|
| @Self | Search only current injector |
| @SkipSelf | Start searching from parent |
| @Optional | Return null when unavailable |
| @Host | Limit resolution to host boundary |

Example:

```typescript
constructor(
  @Optional()
  @SkipSelf()
  private employeeService: EmployeeService | null
) {}
```

Modern inject() equivalents:

```typescript
const service = inject(EmployeeService, {
  optional: true,
  skipSelf: true
});
```

## 14.14 providers vs viewProviders

```typescript
@Component({
  selector: 'app-parent',
  standalone: true,
  providers: [EmployeeService],
  viewProviders: [DepartmentService],
  template: `
    <ng-content></ng-content>
    <app-child />
  `
})
export class ParentComponent {}
```

Important distinction:

| providers | viewProviders |
|---|---|
| Available to component and eligible descendants | Available to the component's view descendants |
| Can be resolved by projected content descendants | Not exposed to projected content descendants |
| Broader component provider scope | Restricted view provider scope |

## 14.15 Lazy Module Service Scope

A service provided in a lazy-loaded module's injector can have a separate instance from the root injector.

```typescript
@NgModule({
  providers: [
    LazyEmployeeService
  ]
})
export class EmployeeModule {}
```

This service is available to the lazy module's injector subtree.

For a shared application-wide singleton, use:

```typescript
@Injectable({
  providedIn: 'root'
})
export class LazyEmployeeService {}
```

---

# PART 3 — REACTIVE PROGRAMMING AND APPLICATION FEATURES

# MODULE 15 — Change Detection and Zone.js

## 15.1 What is Change Detection?

Change detection is the process Angular uses to determine which component views need updating and synchronize the DOM with application state.

## 15.2 Default Change Detection

Angular schedules checks based on framework notifications and application activity.

```typescript
@Component({
  selector: 'app-employee',
  standalone: true,
  template: `{{ employeeName }}`
})
export class EmployeeComponent {
  employeeName = 'John';
}
```

## 15.3 OnPush Change Detection

```typescript
@Component({
  selector: 'app-employee',
  standalone: true,
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `{{ employee.name }}`
})
export class EmployeeComponent {

  @Input() employee!: Employee;
}
```

Typical notifications include:

- New input values.
- Events handled within the component subtree.
- Signal changes read by the template.
- AsyncPipe notifications.
- Explicit change detection notifications.

## 15.4 ChangeDetectorRef

```typescript
private cdr = inject(ChangeDetectorRef);
```

Important methods:

| Method | Purpose |
|---|---|
| markForCheck() | Marks component for checking |
| detectChanges() | Performs local checking |
| detach() | Detaches view from normal checking |
| reattach() | Reattaches view |
| checkNoChanges() | Development checking API |

Example:

```typescript
updateEmployee(): void {
  this.employee.name = 'David';
  this.cdr.markForCheck();
}
```

Prefer immutable updates when possible.

## 15.5 Zone.js

Zone.js tracks asynchronous activities and traditionally helps Angular schedule change detection.

Examples:

- setTimeout
- DOM events
- Promise callbacks
- HTTP operations

## 15.6 runOutsideAngular

Useful when executing high-frequency tasks that do not require Angular change detection.

```typescript
constructor(private ngZone: NgZone) {}

startTimer(): void {

  this.ngZone.runOutsideAngular(() => {

    setInterval(() => {
      console.log('Background operation');
    }, 1000);

  });
}
```

When a UI update is required:

```typescript
this.ngZone.run(() => {
  this.message = 'Updated';
});
```

---

# MODULE 16 — Angular Signals

Signals provide reactive state management.

## 16.1 signal()

```typescript
import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-counter',
  standalone: true,
  template: `
    <h2>{{ count() }}</h2>

    <button (click)="increment()">
      Increment
    </button>
  `
})
export class CounterComponent {

  count = signal(0);

  increment(): void {
    this.count.update(value => value + 1);
  }
}
```

## 16.2 set() and update()

```typescript
count.set(10);

count.update(value => value + 1);
```

## 16.3 computed()

```typescript
basicSalary = signal(50000);

bonus = signal(10000);

totalSalary = computed(() =>
  this.basicSalary() + this.bonus()
);
```

Computed signals are lazy and memoized.

## 16.4 effect()

```typescript
count = signal(0);

constructor() {

  effect(() => {
    console.log(
      'Count changed:',
      this.count()
    );
  });

}
```

Effects are used for side effects, such as logging and synchronizing with external systems.

Avoid using effects merely to copy one signal's value into another. Prefer computed signals for derived state.

## 16.5 asReadonly()

```typescript
private employeeCount = signal(0);

count = this.employeeCount.asReadonly();
```

External consumers can read the signal but cannot directly call set or update.

## 16.6 Angular Signal Inputs

Angular 18 introduced signal-based component inputs in Developer Preview.

```typescript
import {
  Component,
  input
} from '@angular/core';

@Component({
  selector: 'app-employee',
  standalone: true,
  template: `{{ employeeName() }}`
})
export class EmployeeComponent {

  employeeName = input<string>();
}
```

Required input:

```typescript
employeeId = input.required<number>();
```

**Version note:** Do not assume every signal-based component API is production-stable in Angular 18. Check the API status for the exact version.

## 16.7 Signals vs RxJS

| Signals | RxJS |
|---|---|
| Reactive state | Asynchronous streams |
| Synchronous value access | Subscription-based |
| signal() | Observable |
| computed() | Derived state |
| effect() | Side effects |
| Suitable for component/application state | Suitable for HTTP, events and complex async workflows |

## 16.8 RxJS and Signal Interoperability

Observable to Signal:

```typescript
employees = toSignal(
  this.employeeService.getEmployees(),
  { initialValue: [] }
);
```

Signal to Observable:

```typescript
employeeName$ = toObservable(this.employeeName);
```

Important utilities:

- toSignal()
- toObservable()
- takeUntilDestroyed()

---

# MODULE 17 — RxJS and Reactive Programming

## 17.1 What is RxJS?

RxJS is a reactive programming library used to manage asynchronous data streams.

Angular uses RxJS extensively for:

- HTTP communication
- Reactive forms
- Router events
- User interactions
- Event communication
- Async workflows

## 17.2 Observable

```typescript
const observable = new Observable<number>(
  subscriber => {

    subscriber.next(10);
    subscriber.next(20);
    subscriber.next(30);

    subscriber.complete();
  }
);

observable.subscribe(value => {
  console.log(value);
});
```

## 17.3 Observable vs Promise

| Observable | Promise |
|---|---|
| Multiple values | One settled result |
| Lazy by default | Starts when created |
| Can unsubscribe | No built-in cancellation |
| Supports RxJS operators | then/catch/finally |
| Sync or async | Asynchronous result |

## 17.4 Subject Types

| Subject | Behavior |
|---|---|
| Subject | Multicasts new values |
| BehaviorSubject | Stores and emits latest value |
| ReplaySubject | Replays previous values |
| AsyncSubject | Emits final value upon completion |

Example:

```typescript
private employeeSubject =
  new BehaviorSubject<string>('John');

employee$ =
  this.employeeSubject.asObservable();

updateEmployee(name: string): void {
  this.employeeSubject.next(name);
}
```

## 17.5 Important RxJS Operators

### Transformation

| Operator | Purpose |
|---|---|
| map | Transform emitted value |
| scan | Accumulate values |
| switchMap | Switch to latest inner Observable |
| mergeMap | Concurrent inner Observables |
| concatMap | Sequential inner Observables |
| exhaustMap | Ignore new source emissions while busy |

### Filtering

| Operator | Purpose |
|---|---|
| filter | Filter values |
| distinctUntilChanged | Ignore consecutive duplicates |
| debounceTime | Wait for inactivity |
| throttleTime | Limit frequency |
| take | Take a fixed number |
| first | Take first matching value |
| skip | Skip initial values |

### Combination

| Operator | Purpose |
|---|---|
| combineLatest | Latest values of all sources |
| forkJoin | Final values after all complete |
| merge | Merge source emissions |
| concat | Sequential subscriptions |
| zip | Combine corresponding emissions |
| withLatestFrom | Combine source with latest other values |

### Error Handling

| Operator | Purpose |
|---|---|
| catchError | Handle errors |
| retry | Retry failures |
| finalize | Run cleanup on termination/unsubscribe |
| timeout | Limit waiting time |

## 17.6 switchMap Search Example

```typescript
searchControl.valueChanges.pipe(
  debounceTime(300),
  distinctUntilChanged(),
  switchMap(value =>
    this.employeeService.searchEmployees(value)
  )
).subscribe(result => {
  this.employees = result;
});
```

Why switchMap?

When the user types a newer search value, the previous inner subscription is cancelled. This is useful for search requests.

## 17.7 switchMap vs mergeMap vs concatMap vs exhaustMap

| Operator | Behavior | Example |
|---|---|---|
| switchMap | Latest request wins | Search |
| mergeMap | Concurrent execution | Independent API calls |
| concatMap | Sequential execution | Ordered updates |
| exhaustMap | Ignore while processing | Prevent duplicate submissions |

## 17.8 forkJoin

```typescript
forkJoin({
  employees: this.employeeService.getEmployees(),
  departments: this.departmentService.getDepartments()
}).subscribe(result => {

  console.log(result.employees);
  console.log(result.departments);

});
```

Use forkJoin for finite Observables such as ordinary HTTP requests.

## 17.9 combineLatest

```typescript
combineLatest([
  this.employeeService.getEmployees(),
  this.departmentService.getDepartments()
]).subscribe(([employees, departments]) => {

  console.log(employees);
  console.log(departments);

});
```

Unlike forkJoin, combineLatest can emit repeatedly as source values change.

## 17.10 AsyncPipe

```typescript
employees$ = this.employeeService.getEmployees();
```

```html
<div *ngFor="let employee of employees$ | async">
  {{ employee.name }}
</div>
```

## 17.11 Error Handling and Subscription Cleanup

```typescript
this.employeeService.getEmployees().pipe(
  retry(2),
  catchError(error => {
    console.error(error);
    return throwError(() => error);
  }),
  finalize(() => {
    console.log('Request completed or unsubscribed');
  }),
  takeUntilDestroyed(this.destroyRef)
).subscribe();
```

---

# MODULE 18 — Routing and Navigation

## 18.1 Configure Routes

**app.routes.ts**

```typescript
import { Routes } from '@angular/router';

export const routes: Routes = [

  {
    path: '',
    component: DashboardComponent
  },

  {
    path: 'employees',
    component: EmployeeListComponent
  },

  {
    path: 'employees/:id',
    component: EmployeeDetailsComponent
  },

  {
    path: '**',
    redirectTo: ''
  }

];
```

## 18.2 Router Outlet

RouterOutlet is a placeholder where Angular renders the component associated with the current route.

```typescript
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterLink
  ],
  template: `
    <nav>
      <a routerLink="/">Dashboard</a>
      <a routerLink="/employees">Employees</a>
    </nav>

    <router-outlet />
  `
})
export class AppComponent {}
```

## 18.3 Route Parameters

URL:

```text
/employees/101
```

Read parameter:

```typescript
private route = inject(ActivatedRoute);

ngOnInit(): void {

  const id =
    this.route.snapshot.paramMap.get('id');

  console.log(id);

}
```

Reactive parameter:

```typescript
this.route.paramMap.subscribe(params => {
  const id = params.get('id');
});
```

## 18.4 Query Parameters

URL:

```text
/employees?page=2&status=ACTIVE
```

Read:

```typescript
this.route.queryParamMap.subscribe(params => {

  const page = params.get('page');
  const status = params.get('status');

});
```

Navigate:

```typescript
this.router.navigate(['/employees'], {
  queryParams: {
    page: 2,
    status: 'ACTIVE'
  }
});
```

## 18.5 queryParamsHandling

| Value | Purpose |
|---|---|
| merge | Merge existing and new query parameters |
| preserve | Preserve existing query parameters |
| omitted | Replace with provided query parameters |

```typescript
this.router.navigate(['/employees'], {
  queryParams: {
    page: 3
  },
  queryParamsHandling: 'merge'
});
```

## 18.6 Child Routes

```typescript
export const routes: Routes = [
  {
    path: 'employees',
    component: EmployeeLayoutComponent,
    children: [

      {
        path: '',
        component: EmployeeListComponent
      },

      {
        path: ':id',
        component: EmployeeDetailsComponent
      }

    ]
  }
];
```

The parent component must contain its own RouterOutlet.

## 18.7 Router Events

Important events:

- NavigationStart
- NavigationEnd
- NavigationCancel
- NavigationError
- RoutesRecognized

Use cases:

- Loading indicators
- Navigation logging
- Analytics
- Error tracking

---

# MODULE 19 — Route Guards and Resolvers

## 19.1 Route Guards

| Guard | Purpose |
|---|---|
| CanActivate | Controls route activation |
| CanActivateChild | Controls child route activation |
| CanDeactivate | Controls leaving route |
| CanMatch | Controls whether route matches |

## 19.2 Functional Auth Guard

```typescript
import { inject } from '@angular/core';
import {
  CanActivateFn,
  Router
} from '@angular/router';

export const authGuard: CanActivateFn = () => {

  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.isLoggedIn()) {
    return true;
  }

  return router.createUrlTree(['/login']);
};
```

Apply:

```typescript
{
  path: 'employees',
  component: EmployeeListComponent,
  canActivate: [authGuard]
}
```

## 19.3 CanDeactivate Example

Useful when preventing users from leaving a page with unsaved changes.

```typescript
export const unsavedChangesGuard:
  CanDeactivateFn<EmployeeFormComponent> =
  component => {

    return component.hasUnsavedChanges()
      ? confirm('Discard unsaved changes?')
      : true;

  };
```

## 19.4 Route Resolver

Fetches required data before activating a route.

```typescript
export const employeeResolver:
  ResolveFn<Employee> = route => {

    const id =
      Number(route.paramMap.get('id'));

    return inject(EmployeeService)
      .getEmployeeById(id);

  };
```

Route:

```typescript
{
  path: 'employees/:id',
  component: EmployeeDetailsComponent,
  resolve: {
    employee: employeeResolver
  }
}
```

Read:

```typescript
const employee =
  this.route.snapshot.data['employee'];
```

**Security:** Guards are client-side navigation controls. Backend authentication and authorization remain mandatory.

---

# MODULE 20 — HTTP Client and Interceptors

## 20.1 Register HttpClient

```typescript
import {
  provideHttpClient
} from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient()
  ]
};
```

## 20.2 HTTP Methods

| Method | Purpose |
|---|---|
| GET | Retrieve |
| POST | Create |
| PUT | Replace/update |
| PATCH | Partially update |
| DELETE | Delete |

## 20.3 Functional HTTP Interceptor

```typescript
import {
  HttpInterceptorFn
} from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn =
  (req, next) => {

    const token =
      inject(AuthService).getToken();

    const clonedRequest = req.clone({
      setHeaders: token
        ? {
            Authorization: `Bearer ${token}`
          }
        : {}
    });

    return next(clonedRequest);
  };
```

Register:

```typescript
provideHttpClient(
  withInterceptors([
    authInterceptor
  ])
)
```

Only attach tokens to trusted API destinations.

## 20.4 Global Error Interceptor

```typescript
export const errorInterceptor: HttpInterceptorFn =
  (req, next) => {

    return next(req).pipe(
      catchError(error => {

        console.error('HTTP Error:', error);

        return throwError(() => error);

      })
    );

  };
```

## 20.5 HttpParams

```typescript
const params = new HttpParams()
  .set('page', 1)
  .set('size', 10)
  .set('status', 'ACTIVE');

this.http.get('/api/employees', {
  params
});
```

HttpParams is immutable.

## 20.6 Retry and Timeout

```typescript
this.http.get('/api/employees').pipe(
  timeout(5000),
  retry(2),
  catchError(error => throwError(() => error))
);
```

Use retry carefully, especially for non-idempotent POST requests.

---

# MODULE 21 — Forms: Template-Driven and Reactive

## 21.1 Types of Forms

| Template-Driven | Reactive |
|---|---|
| Template-oriented | TypeScript-oriented |
| FormsModule | ReactiveFormsModule |
| Suitable for simple forms | Suitable for complex forms |
| ngModel | FormControl |
| Less explicit structure | Explicit form model |
| Template-based validation | Programmatic validation |

## 21.2 Template-Driven Forms

```typescript
@Component({
  selector: 'app-employee-form',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule
  ],
  templateUrl: './employee-form.component.html'
})
export class EmployeeFormComponent {

  employee = {
    name: '',
    email: ''
  };

  submitForm(): void {
    console.log(this.employee);
  }
}
```

HTML:

```html
<form
  #employeeForm="ngForm"
  (ngSubmit)="submitForm()">

  <input
    name="name"
    [(ngModel)]="employee.name"
    required
    minlength="3"
    #name="ngModel">

  <div *ngIf="name.invalid && name.touched">
    Name is required.
  </div>

  <input
    name="email"
    [(ngModel)]="employee.email"
    required
    email
    #email="ngModel">

  <button
    type="submit"
    [disabled]="employeeForm.invalid">

    Submit

  </button>

</form>
```

## 21.3 Reactive Forms

```typescript
employeeForm = new FormGroup({

  name: new FormControl('', [
    Validators.required,
    Validators.minLength(3)
  ]),

  email: new FormControl('', [
    Validators.required,
    Validators.email
  ]),

  salary: new FormControl(0, [
    Validators.required,
    Validators.min(10000)
  ])

});
```

HTML:

```html
<form
  [formGroup]="employeeForm"
  (ngSubmit)="submitForm()">

  <input formControlName="name">

  <input formControlName="email">

  <input
    type="number"
    formControlName="salary">

  <button type="submit">
    Save
  </button>

</form>
```

## 21.4 FormBuilder

```typescript
private fb = inject(FormBuilder);

employeeForm = this.fb.group({

  name: ['', Validators.required],

  email: [
    '',
    [
      Validators.required,
      Validators.email
    ]
  ],

  salary: [
    0,
    [
      Validators.required,
      Validators.min(10000)
    ]
  ]

});
```

## 21.5 FormGroup vs FormControl vs FormArray

| Concept | Purpose |
|---|---|
| FormControl | Single form field |
| FormGroup | Group of controls |
| FormArray | Dynamic collection of controls |

## 21.6 FormArray

```typescript
form = this.fb.group({
  skills: this.fb.array([])
});

get skills(): FormArray {
  return this.form.get('skills') as FormArray;
}

addSkill(): void {
  this.skills.push(
    this.fb.control('')
  );
}

removeSkill(index: number): void {
  this.skills.removeAt(index);
}
```

HTML:

```html
<div formArrayName="skills">

  @for (
    skill of skills.controls;
    track $index;
    let i = $index
  ) {

    <input [formControlName]="i">

    <button
      type="button"
      (click)="removeSkill(i)">

      Remove

    </button>

  }

</div>

<button
  type="button"
  (click)="addSkill()">

  Add Skill

</button>
```

## 21.7 Custom Validators

```typescript
export function noSpaceValidator(
  control: AbstractControl
): ValidationErrors | null {

  const value = control.value;

  if (
    typeof value === 'string' &&
    value.includes(' ')
  ) {
    return {
      noSpace: true
    };
  }

  return null;
}
```

## 21.8 Cross-Field Validation

Password and confirm password:

```typescript
export function passwordMatchValidator(
  group: AbstractControl
): ValidationErrors | null {

  const password =
    group.get('password')?.value;

  const confirmPassword =
    group.get('confirmPassword')?.value;

  return password === confirmPassword
    ? null
    : { passwordMismatch: true };

}
```

## 21.9 Form Methods

| Method | Purpose |
|---|---|
| setValue | Update all required controls |
| patchValue | Update selected controls |
| reset | Reset form |
| markAsTouched | Mark control touched |
| markAllAsTouched | Mark all controls touched |
| disable | Disable control |
| enable | Enable control |
| updateValueAndValidity | Recalculate validation |

---

# MODULE 22 — Custom Form Controls and Validation

## 22.1 ControlValueAccessor

ControlValueAccessor connects a custom component with Angular Forms.

Important methods:

| Method | Purpose |
|---|---|
| writeValue | Receives form value |
| registerOnChange | Registers change callback |
| registerOnTouched | Registers touched callback |
| setDisabledState | Handles disabled state |

Example:

```typescript
@Component({
  selector: 'app-custom-input',
  standalone: true,
  template: `
    <input
      [value]="value"
      [disabled]="disabled"
      (input)="onInput($event)"
      (blur)="onTouched()"
    >
  `,
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(
        () => CustomInputComponent
      ),
      multi: true
    }
  ]
})
export class CustomInputComponent
  implements ControlValueAccessor {

  value = '';
  disabled = false;

  onChange = (value: string) => {};
  onTouched = () => {};

  writeValue(value: string): void {
    this.value = value ?? '';
  }

  registerOnChange(
    fn: (value: string) => void
  ): void {
    this.onChange = fn;
  }

  registerOnTouched(
    fn: () => void
  ): void {
    this.onTouched = fn;
  }

  setDisabledState(
    disabled: boolean
  ): void {
    this.disabled = disabled;
  }

  onInput(event: Event): void {

    const value =
      (event.target as HTMLInputElement).value;

    this.value = value;
    this.onChange(value);

  }
}
```

Usage:

```html
<app-custom-input
  formControlName="name"
/>
```

## 22.2 Async Validators

Async validators are used for server-side validation.

Example: Check whether email already exists.

```typescript
function emailExistsValidator(
  employeeService: EmployeeService
): AsyncValidatorFn {

  return control => {

    return employeeService
      .checkEmailExists(control.value)
      .pipe(
        map(exists =>
          exists
            ? { emailExists: true }
            : null
        ),
        catchError(() => of(null))
      );

  };
}
```

---

# MODULE 23 — State Management

## 23.1 Types of State

| State | Example |
|---|---|
| Local state | Modal visibility |
| Shared UI state | Selected theme |
| Server state | Employee list |
| Authentication state | Logged-in user |
| Form state | Form values |

## 23.2 BehaviorSubject State Service

```typescript
@Injectable({
  providedIn: 'root'
})
export class EmployeeStateService {

  private employeesSubject =
    new BehaviorSubject<Employee[]>([]);

  employees$ =
    this.employeesSubject.asObservable();

  setEmployees(employees: Employee[]): void {
    this.employeesSubject.next(employees);
  }

}
```

## 23.3 Signal-Based State

```typescript
@Injectable({
  providedIn: 'root'
})
export class EmployeeStateService {

  private employeesSignal =
    signal<Employee[]>([]);

  employees =
    this.employeesSignal.asReadonly();

  setEmployees(employees: Employee[]): void {
    this.employeesSignal.set(employees);
  }

  addEmployee(employee: Employee): void {

    this.employeesSignal.update(
      employees => [
        ...employees,
        employee
      ]
    );

  }
}
```

## 23.4 NgRx

NgRx is a Redux-inspired state management library.

| Concept | Purpose |
|---|---|
| Store | Holds application state |
| Action | Describes event |
| Reducer | Calculates next state |
| Selector | Reads derived state |
| Effect | Handles side effects |
| Entity | Manages collections |

Flow:

```text
Component
   |
   v
Dispatch Action
   |
   v
Reducer / Effect
   |
   v
Store
   |
   v
Selector
   |
   v
Component
```

Use NgRx when complex shared state, predictable state transitions, side effects and debugging requirements justify the additional architecture.

---

# PART 4 — ANGULAR 18 AND ADVANCED DEVELOPMENT

# MODULE 24 — Angular 18 Built-in Control Flow

Angular 17 introduced built-in control flow, which is stable in Angular 18.

## 24.1 @if

```html
@if (isLoggedIn) {

  <h2>Welcome User</h2>

} @else {

  <h2>Please Login</h2>

}
```

## 24.2 @for

```html
@for (
  employee of employees;
  track employee.id;
  let i = $index
) {

  <div>
    {{ i + 1 }} - {{ employee.name }}
  </div>

} @empty {

  <p>No employees found</p>

}
```

Important variables:

- $index
- $first
- $last
- $even
- $odd
- $count

## 24.3 @switch

```html
@switch (status) {

  @case ('ACTIVE') {
    <p>Employee Active</p>
  }

  @case ('INACTIVE') {
    <p>Employee Inactive</p>
  }

  @default {
    <p>Unknown Status</p>
  }

}
```

## 24.4 @defer

```html
@defer (on viewport) {

  <app-employee-analytics />

} @placeholder {

  <p>Analytics will load when visible.</p>

} @loading (minimum 500ms) {

  <p>Loading analytics...</p>

} @error {

  <p>Unable to load analytics.</p>

}
```

Triggers:

| Trigger | Purpose |
|---|---|
| on idle | Load when browser is idle |
| on viewport | Load when visible |
| on interaction | Load after interaction |
| on hover | Load on hover |
| on immediate | Load immediately |
| on timer | Load after a delay |

Example:

```html
@defer (on interaction) {

  <app-heavy-chart />

} @placeholder {

  <button>
    Load Chart
  </button>

}
```

Important: Deferred dependencies need to meet Angular's defer eligibility rules. Standalone components, directives and pipes can be deferred; NgModule-based dependencies cannot be deferred independently.

## 24.5 @let — Angular 18 Developer Preview

Angular 18 introduced the `@let` template syntax in Developer Preview.

It declares a local template variable.

```html
@let employee = selectedEmployee;

<h2>{{ employee.name }}</h2>

<p>{{ employee.salary }}</p>
```

Using an Observable:

```html
@let employees = employees$ | async;

@if (employees) {

  @for (
    employee of employees;
    track employee.id
  ) {

    <p>{{ employee.name }}</p>

  }

}
```

Important points:

- Must end with a semicolon.
- Cannot be reassigned like a JavaScript variable.
- Values remain synchronized with the expression.
- Variables are scoped to their view and descendant views.
- Developer Preview in Angular 18.

## 24.6 Structural Directives vs Built-in Control Flow

| Structural directives | Built-in control flow |
|---|---|
| *ngIf | @if |
| *ngFor | @for |
| *ngSwitch | @switch |
| CommonModule needed for these directives | No CommonModule required for built-in blocks |
| Structural directive syntax | Built-in template syntax |

---

# MODULE 25 — Lazy Loading and Code Splitting

## 25.1 Lazy Loading

Lazy loading loads application code only when required.

## 25.2 Lazy Load Component

```typescript
export const routes: Routes = [

  {
    path: 'dashboard/general',

    loadComponent: () =>
      import('./dashboard/general/dashboard.component')
        .then(m => m.DashboardComponent)

  }

];
```

## 25.3 Lazy Load Module

```typescript
export const routes: Routes = [

  {
    path: 'dashboard/general',

    loadChildren: () =>
      import('./dashboard/general/dashboard.module')
        .then(m => m.DashboardModule)

  }

];
```

## 25.4 Eager vs Lazy Loading

| Eager | Lazy |
|---|---|
| Included in initial loading path | Loaded when required |
| Can increase initial bundle | Reduces initial bundle |
| Suitable for common features | Suitable for large features |

## 25.5 Tree Shaking

Tree shaking removes unused code from production bundles.

Angular's compiler and modern bundlers analyze application dependencies to remove unused exports where possible.

Standalone components help make dependencies explicit, but tree shaking is not exclusive to standalone components.

Benefits:

- Smaller bundle
- Faster downloads
- Less JavaScript parsing
- Better initial loading performance

---

# MODULE 26 — Performance Optimization

## 26.1 OnPush

Use OnPush to reduce unnecessary component checks.

## 26.2 Track

```html
@for (
  employee of employees;
  track employee.id
) {
  <div>{{ employee.name }}</div>
}
```

## 26.3 Avoid Methods in Templates

Avoid:

```html
<p>{{ calculateSalary() }}</p>
```

Prefer computed values or precomputed state.

## 26.4 Lazy Loading

Split large feature modules and route components.

## 26.5 Deferred Loading

Use @defer for heavy, non-critical components.

## 26.6 Virtual Scrolling

Angular CDK virtual scrolling helps render large datasets efficiently.

```typescript
@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [ScrollingModule],
  template: `
    <cdk-virtual-scroll-viewport
      itemSize="50"
      class="viewport">

      <div
        *cdkVirtualFor="let employee of employees">

        {{ employee.name }}

      </div>

    </cdk-virtual-scroll-viewport>
  `
})
export class EmployeeListComponent {

  employees = Array.from(
    { length: 10000 },
    (_, i) => ({
      id: i + 1,
      name: `Employee ${i + 1}`
    })
  );

}
```

## 26.7 Other Optimization Techniques

- Server-side pagination.
- Server-side sorting and filtering.
- Debouncing search.
- Caching API responses.
- AsyncPipe.
- takeUntilDestroyed.
- Bundle analysis.
- Optimized images.
- Web Workers.
- Avoiding unnecessary subscriptions.
- Profiling with Angular DevTools.

---

# MODULE 27 — Angular Security

## 27.1 XSS

Cross-Site Scripting occurs when malicious scripts are injected into a page and executed in another user's browser.

Angular sanitizes values in many template binding contexts.

## 27.2 DomSanitizer

```typescript
private sanitizer = inject(DomSanitizer);

safeContent =
  this.sanitizer.bypassSecurityTrustHtml(
    '<b>Employee</b>'
  );
```

**Important:** Never use bypassSecurityTrustHtml with untrusted user input.

## 27.3 Security Concepts

| Concept | Explanation |
|---|---|
| XSS | Malicious script injection |
| CSRF | Unwanted authenticated browser requests |
| CORS | Browser cross-origin policy |
| CSP | Restricts permitted resources |
| JWT | Token format |
| HTTPS | Encrypts communication |
| Authentication | Verifies identity |
| Authorization | Determines access |

## 27.4 Security Best Practices

- Enforce authorization on the backend.
- Use HTTPS.
- Avoid exposing secrets in frontend code.
- Understand secure cookie authentication.
- Configure CSRF protection where applicable.
- Validate input on the backend.
- Avoid unsafe DOM manipulation.
- Keep dependencies updated.
- Configure CSP.

---

# MODULE 28 — Application Architecture and Reusable Components

## 28.1 Feature-Based Architecture

```text
src/app/
│
├── core/
│   ├── auth/
│   ├── interceptors/
│   └── guards/
│
├── features/
│   ├── employee/
│   │   ├── components/
│   │   ├── services/
│   │   ├── models/
│   │   └── employee.routes.ts
│   │
│   ├── department/
│   └── dashboard/
│
├── shared/
│   ├── components/
│   ├── directives/
│   └── pipes/
│
├── app.component.ts
├── app.config.ts
└── app.routes.ts
```

## 28.2 Smart vs Presentational Components

| Smart | Presentational |
|---|---|
| Handles business logic | Focuses on UI |
| Calls services | Receives inputs |
| Manages state | Emits events |
| Coordinates features | Reusable |

## 28.3 Reusable Table Component

Should support:

- Dynamic columns
- Sorting
- Pagination
- Row selection
- Loading state
- Empty state
- Custom cell templates

## 28.4 Core vs Shared Modules

In legacy NgModule applications:

- CoreModule contains singleton services and application-wide infrastructure.
- SharedModule contains reusable components, directives and pipes.

In standalone applications, these concepts can be represented using feature folders, shared declarations and provider configuration without requiring these modules.

---

# MODULE 29 — Angular 18 SSR, Hydration and Event Replay

## 29.1 Server-Side Rendering

SSR renders Angular application HTML on the server.

Benefits:

- Faster initial content visibility in suitable cases.
- Improved search engine discoverability.
- Better link previews.
- Better experience in some low-powered environments.

## 29.2 CSR vs SSR

| CSR | SSR |
|---|---|
| Browser renders application | Server renders initial HTML |
| Client JavaScript is important for rendering | HTML can be delivered before client bootstrap |
| Common SPA approach | Useful for SEO and initial rendering |

## 29.3 Configure SSR

```bash
ng add @angular/ssr@18
```

Angular CLI configures server-side rendering support.

## 29.4 Hydration

Hydration attaches Angular behavior to server-rendered HTML rather than recreating the entire DOM.

```typescript
import {
  provideClientHydration
} from '@angular/platform-browser';

export const appConfig: ApplicationConfig = {
  providers: [
    provideClientHydration()
  ]
};
```

## 29.5 Event Replay — Angular 18

Angular 18 introduced Event Replay in Developer Preview.

Problem:

1. Server renders HTML.
2. Browser displays the HTML.
3. User clicks a button.
4. Angular has not finished hydration.
5. The click may otherwise be lost.

Event Replay captures eligible events and replays them after hydration.

Configuration:

```typescript
import {
  provideClientHydration,
  withEventReplay
} from '@angular/platform-browser';

export const appConfig: ApplicationConfig = {
  providers: [
    provideClientHydration(
      withEventReplay()
    )
  ]
};
```

## 29.6 Incremental Hydration

Incremental hydration allows parts of an SSR application to hydrate at different times using hydration triggers associated with deferred views.

Angular 18 introduced this capability as an experimental/developer-preview area of SSR evolution. Verify exact API availability and stability before using it in an Angular 18 production application.

## 29.7 SSR Considerations

- Avoid accessing window/document during server execution.
- Avoid browser-only libraries on the server.
- Avoid hydration mismatches.
- Understand DOM manipulation restrictions.
- Consider server resource consumption.
- Use platform-specific logic when needed.

---

# MODULE 30 — Zoneless Change Detection

## 30.1 What is Zoneless Angular?

Traditional Angular uses Zone.js to track asynchronous operations and schedule change detection.

Zoneless Angular removes the dependency on Zone.js for scheduling updates.

## 30.2 Angular 18 Zoneless API

Angular 18 introduced zoneless change detection as Developer Preview.

```typescript
import {
  provideExperimentalZonelessChangeDetection
} from '@angular/core';

export const appConfig: ApplicationConfig = {
  providers: [
    provideExperimentalZonelessChangeDetection()
  ]
};
```

## 30.3 How Does Angular Know When to Update?

Zoneless applications rely on Angular notifications, including:

- Signal updates read by templates.
- Component input changes.
- Events handled by Angular.
- AsyncPipe notifications.
- Explicit change detection notifications.

## 30.4 Zone-Based vs Zoneless

| Zone-Based | Zoneless |
|---|---|
| Uses Zone.js | Does not require Zone.js for scheduling |
| Tracks async activity | Relies on Angular notifications |
| Traditional Angular approach | Modern reactive approach |
| More implicit scheduling | More explicit reactive notifications |

## 30.5 When to Use Zoneless?

- Applications using signals extensively.
- Applications requiring optimized change detection.
- Projects evaluating modern Angular architecture.

Because Angular 18 support was Developer Preview, evaluate compatibility and stability before production adoption.

---

# MODULE 31 — Application Initialization and Environment Configuration

## 31.1 APP_INITIALIZER

APP_INITIALIZER is an Angular injection token used to execute initialization logic before application initialization completes.

Common use cases:

- Fetch application configuration.
- Load feature flags.
- Retrieve user settings.
- Initialize authentication configuration.

Example:

```typescript
import {
  APP_INITIALIZER,
  Injectable
} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {

  constructor(private http: HttpClient) {}

  loadConfig(): Promise<void> {

    return firstValueFrom(
      this.http.get('/assets/config.json')
        .pipe(
          tap(config => {
            console.log(config);
          }),
          map(() => undefined)
        )
    );

  }
}
```

Factory:

```typescript
export function initializeApp(
  configService: AppConfigService
) {

  return () => configService.loadConfig();

}
```

Register:

```typescript
providers: [

  {
    provide: APP_INITIALIZER,
    useFactory: initializeApp,
    deps: [AppConfigService],
    multi: true
  }

]
```

APP_INITIALIZER is an Angular 18 API. Newer Angular releases introduce a more direct provider-based initialization API, so check migration guidance when upgrading.

## 31.2 Environment Configuration

Example development configuration:

```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};
```

Production configuration:

```typescript
export const environment = {
  production: true,
  apiUrl: 'https://api.example.com'
};
```

Usage:

```typescript
import { environment } from '../environments/environment';

private apiUrl = environment.apiUrl;
```

Commands:

```bash
ng serve

ng serve --configuration=production

ng build --configuration=production
```

Important: Environment values are compiled into frontend assets. Never store passwords, secrets or private API keys in them.

---

# MODULE 32 — Location Service and Browser Navigation

Angular provides Location from @angular/common.

## 32.1 Location Service

```typescript
import { Location } from '@angular/common';

constructor(private location: Location) {}
```

## 32.2 location.back()

Navigates to the previous browser history entry.

```typescript
goBack(): void {
  this.location.back();
}
```

## 32.3 location.forward()

Navigates to the next browser history entry.

```typescript
goForward(): void {
  this.location.forward();
}
```

## 32.4 location.go()

Adds a URL to browser history and updates Angular's location state.

```typescript
this.location.go('/employees');
```

## 32.5 location.replaceState()

Replaces the current history entry.

```typescript
this.location.replaceState('/employees/101');
```

## 32.6 Location vs Router

| Location | Router |
|---|---|
| Works with browser URL/history | Handles Angular route navigation |
| Useful for low-level URL operations | Preferred for application navigation |
| back(), forward(), go() | navigate(), navigateByUrl() |

Prefer Angular Router for normal application navigation.

---

# MODULE 33 — Dynamic Components and Directives

## 33.1 Dynamic Component Creation

Use `createComponent()` when a complete component needs to be instantiated dynamically.

```typescript
import {
  Component,
  ViewContainerRef,
  inject
} from '@angular/core';

@Component({
  selector: 'app-host',
  standalone: true,
  template: `
    <button (click)="load()">
      Load Component
    </button>
  `
})
export class HostComponent {

  private viewContainer =
    inject(ViewContainerRef);

  async load(): Promise<void> {

    const {
      EmployeeComponent
    } = await import('./employee.component');

    this.viewContainer.clear();

    const componentRef =
      this.viewContainer.createComponent(
        EmployeeComponent
      );

  }
}
```

## 33.2 Setting Dynamic Inputs

```typescript
const componentRef =
  this.viewContainer.createComponent(
    EmployeeComponent
  );

componentRef.setInput(
  'employeeId',
  101
);
```

## 33.3 createComponent vs createEmbeddedView

| createComponent | createEmbeddedView |
|---|---|
| Creates component instance | Creates embedded template view |
| Has component lifecycle | Uses template view lifecycle |
| Supports DI and component inputs | Uses template context |
| For interactive reusable UI | For lightweight dynamic HTML |

## 33.4 ViewContainerRef

ViewContainerRef is used to create, insert, move and remove views.

Important methods:

- createComponent()
- createEmbeddedView()
- clear()
- remove()
- insert()
- detach()
- indexOf()

## 33.5 TemplateRef

TemplateRef represents an embedded template.

```typescript
constructor(
  private templateRef: TemplateRef<unknown>,
  private viewContainer: ViewContainerRef
) {}
```

## 33.6 ElementRef

ElementRef provides access to the underlying DOM element.

```typescript
constructor(
  private elementRef: ElementRef
) {}
```

Use carefully and prefer Angular abstractions or Renderer2 for DOM changes.

## 33.7 Renderer2

```typescript
constructor(
  private renderer: Renderer2,
  private elementRef: ElementRef
) {}

changeColor(): void {

  this.renderer.setStyle(
    this.elementRef.nativeElement,
    'color',
    'red'
  );

}
```

---

# MODULE 34 — Angular Material, CDK and UI Architecture

## 34.1 Angular Material

Angular Material provides reusable UI components.

Important components:

- MatTable
- MatPaginator
- MatSort
- MatDialog
- MatFormField
- MatInput
- MatSelect
- MatAutocomplete
- MatMenu
- MatSnackBar
- MatTabs
- MatDatepicker

## 34.2 Angular CDK

CDK provides low-level UI behavior and accessibility utilities.

Important areas:

- Overlay
- Portal
- Drag and Drop
- Virtual Scrolling
- Accessibility
- Clipboard
- Layout
- Scrolling

## 34.3 Angular Material 3

Material 3 support became stable in Angular 18.

Understand:

- Theme configuration
- Typography
- Color palettes
- Component styling
- Design tokens
- Responsive design

---

# MODULE 35 — Testing and Debugging

## 35.1 Types of Testing

| Type | Purpose |
|---|---|
| Unit testing | Tests individual functions/classes |
| Component testing | Tests UI and component behavior |
| Integration testing | Tests interactions |
| E2E testing | Tests full user workflows |

## 35.2 Component Testing

```typescript
describe('EmployeeComponent', () => {

  let component: EmployeeComponent;
  let fixture: ComponentFixture<EmployeeComponent>;

  beforeEach(async () => {

    await TestBed.configureTestingModule({
      imports: [
        EmployeeComponent
      ]
    }).compileComponents();

    fixture =
      TestBed.createComponent(EmployeeComponent);

    component =
      fixture.componentInstance;

    fixture.detectChanges();

  });

  it('should create component', () => {
    expect(component).toBeTruthy();
  });

});
```

## 35.3 Service Testing

Use HttpTestingController to mock HTTP requests.

```typescript
const request = httpMock.expectOne(
  'https://api.example.com/employees'
);

expect(request.request.method).toBe('GET');

request.flush(mockEmployees);
```

## 35.4 Important Testing Concepts

- TestBed
- ComponentFixture
- DebugElement
- fakeAsync
- tick
- waitForAsync
- HttpTestingController
- Mock services
- Spies
- DOM assertions
- Async Observable testing

Angular 18 CLI projects commonly use Jasmine and Karma by default, depending on configuration. Jest and other runners can also be configured.

## 35.5 Angular DevTools

Use Angular DevTools for:

- Component inspection
- Dependency inspection
- Change detection profiling
- Performance investigation
- Component tree analysis

---

# MODULE 36 — Build, Deployment and CI/CD

## 36.1 Production Build

```bash
ng build --configuration production
```

## 36.2 Deployment Options

- Nginx
- Apache
- AWS S3 and CloudFront
- Azure Static Web Apps
- Google Cloud Storage and CDN
- Docker containers
- Node.js server for SSR

## 36.3 CI/CD Pipeline

```text
Developer Push
      |
      v
Git Repository
      |
      v
Install Dependencies
      |
      v
Lint and Unit Tests
      |
      v
Production Build
      |
      v
Artifact Creation
      |
      v
Deployment
      |
      v
Smoke Tests
```

Important concepts:

- npm
- package-lock.json
- Build configurations
- Environment configuration
- Git branching
- Docker
- CI/CD
- Rollback strategy

---

# MODULE 37 — Advanced Angular Concepts

## 37.1 Standalone Components vs NgModules

| Standalone | NgModule |
|---|---|
| Explicit component imports | Declarations and module imports |
| Less module boilerplate | Module-based architecture |
| Recommended modern approach | Supported for legacy applications |
| bootstrapApplication | bootstrapModule |

## 37.2 Web Workers

Web Workers execute CPU-intensive work in background threads.

Use cases:

- Large data processing
- Complex calculations
- Parsing
- Computationally intensive operations

They cannot directly manipulate the DOM.

## 37.3 Micro Frontends

Micro frontends split a large frontend application into independently developed and deployed parts.

Important concepts:

- Module Federation
- Shell application
- Remote application
- Shared dependencies
- Independent deployment
- Version compatibility
- Routing integration

## 37.4 Internationalization

Important concepts:

- Translation extraction
- Locale configuration
- Date formatting
- Currency formatting
- Number formatting
- Translation workflows

## 37.5 Accessibility

Important topics:

- Semantic HTML
- Keyboard navigation
- ARIA attributes
- Focus management
- Screen readers
- Color contrast
- Angular CDK accessibility utilities

## 37.6 Tree Shaking

Removing unused code during production builds.

## 37.7 Change Detection Scheduling

Understand:

- Zone-based scheduling
- Zoneless scheduling
- Signal notifications
- OnPush
- AsyncPipe
- ChangeDetectorRef

---

# PART 5 — INTERVIEW AND PRACTICAL PREPARATION

# MODULE 38 — Real-World Scenarios and Coding Problems

## Scenario 1 — Implement Employee CRUD

Requirements:

- List employees
- Add employee
- Update employee
- Delete employee
- View details
- Search and filter

Expected concepts:

- Components
- Services
- HttpClient
- Routing
- Reactive forms
- RxJS
- Interceptors
- Error handling

## Scenario 2 — Search with Debouncing

```typescript
this.searchControl.valueChanges.pipe(
  debounceTime(300),
  distinctUntilChanged(),
  switchMap(value =>
    this.employeeService.searchEmployees(value)
  )
).subscribe(result => {
  this.employees = result;
});
```

Explain why switchMap is useful for search.

## Scenario 3 — Prevent Duplicate Submission

```typescript
this.submitClicks.pipe(
  exhaustMap(() =>
    this.employeeService.createEmployee(
      this.form.value
    )
  )
).subscribe();
```

Also disable the submit button while a request is pending.

## Scenario 4 — Handle Global API Errors

Implement an HTTP interceptor using catchError and a centralized notification service.

## Scenario 5 — Prevent Memory Leaks

Use:

- AsyncPipe
- takeUntilDestroyed
- Explicit unsubscription
- Timer cleanup
- Event listener cleanup

## Scenario 6 — Optimize 50,000 Records

Possible approach:

1. Server-side pagination.
2. Server-side sorting.
3. Server-side filtering.
4. Virtual scrolling when appropriate.
5. OnPush.
6. Stable tracking IDs.
7. Avoid unnecessary rendering.
8. Profile the application.

## Scenario 7 — Share State Between Unrelated Components

Use:

- BehaviorSubject
- Signal-based state service
- NgRx for complex state

## Scenario 8 — Role-Based Navigation

Use:

- Authentication service
- Route guards
- Role-aware navigation
- Backend authorization

## Scenario 9 — Reusable Form Component

Use:

- ControlValueAccessor
- Custom validators
- Reactive forms
- Input configuration
- Accessibility

## Scenario 10 — Fix a Memory Leak

Investigate:

- Subscriptions
- Event listeners
- Timers
- Long-lived Subjects
- Component destruction
- Detached views

## Scenario 11 — Dependency Injection Resolution

Explain:

- Root provider
- Component provider
- Route provider
- Hierarchical lookup
- useClass
- useValue
- useFactory
- useExisting
- Optional
- Self
- SkipSelf
- ViewProviders

## Scenario 12 — Dynamic Template Rendering

Implement:

- TemplateRef
- ViewContainerRef
- ngTemplateOutlet
- createEmbeddedView
- createComponent

---

# MODULE 39 — Angular 18 Interview Questions and Answers

## A. Fundamentals and Architecture

**1. What is Angular?**

Angular is a TypeScript-based frontend framework used to develop scalable web applications.

**2. What is the difference between AngularJS and Angular?**

AngularJS is the older JavaScript-based framework. Modern Angular uses TypeScript and component-based architecture.

**3. What is a SPA?**

An application that dynamically updates views without reloading the complete page.

**4. What are Angular building blocks?**

Components, templates, directives, pipes, services, dependency injection and routing.

**5. What is a standalone component?**

A component that declares its dependencies directly without needing to be declared in an NgModule.

**6. What are the major Angular 18 features?**

Zoneless change detection preview, Event Replay preview, SSR improvements, stable control flow, stable deferrable views and stable Material 3 support.

## B. Components and Communication

**7. What is component metadata?**

Metadata configures component properties such as selector, template, styles, providers, imports and change detection.

**8. What is @Input?**

It receives data from a parent component.

**9. What is @Output?**

It emits events from a child component to a parent.

**10. What is EventEmitter?**

An Angular class commonly used with @Output to emit component events.

**11. What is ViewChild?**

It provides access to an element, component, directive or template in the component's own view.

**12. What is ViewChildren?**

It returns a QueryList of matching elements or components.

**13. What is ContentChild?**

It accesses projected content.

**14. What is ContentChildren?**

It accesses multiple projected content elements.

**15. What is Content Projection?**

Rendering parent-provided content inside a child component using ng-content.

**16. What is ngProjectAs?**

It allows an element to match a specific content projection selector.

**17. What is ng-container?**

A logical grouping element that does not create an actual DOM element.

**18. What is ng-template?**

A template definition that is not rendered automatically.

**19. What is TemplateRef?**

An Angular reference to an embedded template.

**20. What is ViewContainerRef?**

An API for managing dynamically created component and embedded views.

## C. Templates and Directives

**21. What are types of data binding?**

Interpolation, property binding, event binding and two-way binding.

**22. What is the difference between interpolation and property binding?**

Interpolation displays a value as text; property binding assigns a value to a DOM or component property.

**23. What is ngModel?**

A directive that provides two-way data binding for form controls.

**24. What is ngModelChange?**

An event emitted when the ngModel value changes.

**25. What is ngNonBindable?**

It prevents Angular from evaluating bindings inside an element.

**26. What is a structural directive?**

A directive that changes DOM structure.

**27. What is an attribute directive?**

A directive that changes an existing element's appearance or behavior.

**28. What is the difference between ngIf and @if?**

ngIf is a structural directive; @if is built-in template control flow.

**29. What is trackBy?**

A function that helps Angular identify list items using stable keys.

**30. What is the difference between ng-container and ng-template?**

ng-container groups content without an extra DOM element; ng-template defines content that is rendered only when instantiated.

**31. Can multiple structural directives be applied to one element?**

Not using multiple structural shorthand expressions. Use ng-container to create nested structural views.

**32. What is ngTemplateOutlet?**

It renders an ng-template dynamically and can pass context values.

## D. Pipes and Styling

**33. What is a pipe?**

A mechanism to transform values in templates.

**34. Pure vs impure pipes?**

Pure pipes run when their input value/reference changes. Impure pipes can run during every change detection cycle.

**35. What is AsyncPipe?**

It subscribes to Observable or Promise values and handles cleanup.

**36. What is ViewEncapsulation?**

It controls how component styles are isolated.

**37. What are the three encapsulation strategies?**

Emulated, ShadowDom and None.

**38. What is the difference between Emulated and ShadowDom?**

Emulated simulates style isolation; ShadowDom uses native browser Shadow DOM.

## E. Dependency Injection

**39. What is Dependency Injection?**

Providing dependencies externally instead of manually creating them.

**40. What is providedIn root?**

Registers a service in the root injector.

**41. What happens when a service is provided at component level?**

The component injector provides its own service instance for that injector subtree.

**42. What is hierarchical DI?**

Angular searches through a hierarchy of injectors to resolve dependencies.

**43. What is InjectionToken?**

A strongly typed token for injecting values that do not have a class token.

**44. What is useClass?**

Maps a token to a class implementation.

**45. What is useValue?**

Maps a token to a fixed value.

**46. What is useFactory?**

Creates a dependency through a factory function.

**47. What is useExisting?**

Creates an alias to an existing provider.

**48. What is multi: true?**

Allows multiple providers to contribute values under the same token.

**49. What is the difference between providers and viewProviders?**

providers can be available to projected content descendants and view descendants, while viewProviders are limited to the component's view descendants.

**50. What are DI resolution modifiers?**

Self, SkipSelf, Optional and Host.

**51. What is the difference between inject() and constructor injection?**

Both resolve dependencies through Angular DI. inject() works in valid injection contexts, while constructor injection uses class constructor parameters.

## F. Lifecycle and Change Detection

**52. What are lifecycle hooks?**

Callbacks executed during component initialization, checking and destruction.

**53. Constructor vs ngOnInit?**

Constructor initializes the class; ngOnInit runs after initial input values are assigned.

**54. What is ngOnChanges?**

It responds to input property changes.

**55. What is Change Detection?**

The mechanism that updates views when application state changes.

**56. What is OnPush?**

A change detection strategy that reduces unnecessary checks.

**57. What is ChangeDetectorRef?**

An API to control component change detection.

**58. What is Zone.js?**

A library traditionally used by Angular to track asynchronous operations.

**59. What is zoneless Angular?**

An approach where Angular does not rely on Zone.js to schedule change detection.

## G. Signals and RxJS

**60. What is a Signal?**

A reactive primitive that stores a value and notifies consumers when it changes.

**61. What is computed?**

A read-only reactive value derived from other signals.

**62. What is effect?**

A mechanism for running side effects when signals change.

**63. What is the difference between Signal and Observable?**

Signals represent reactive state; Observables represent streams over time.

**64. What is Observable?**

A stream that can emit values over time.

**65. Observable vs Promise?**

Observable can emit multiple values and supports unsubscription; Promise settles once.

**66. What is Subject?**

An Observable and Observer that multicasts values.

**67. What is BehaviorSubject?**

A Subject that stores and emits its latest value.

**68. What is ReplaySubject?**

A Subject that replays configured previous values.

**69. What is switchMap?**

It switches to a new inner Observable and unsubscribes from the previous inner subscription.

**70. Difference between switchMap and mergeMap?**

switchMap retains the latest inner subscription; mergeMap allows concurrency.

**71. Difference between concatMap and exhaustMap?**

concatMap queues inner Observables; exhaustMap ignores new source values while busy.

**72. What is debounceTime?**

It waits for a period of inactivity before emitting a value.

**73. What is forkJoin?**

It waits for all sources to complete and emits their final values.

**74. What is combineLatest?**

It emits the latest values from all sources whenever one changes, after each source has emitted.

**75. How do you prevent memory leaks?**

Use AsyncPipe, takeUntilDestroyed and proper cleanup.

## H. Routing and HTTP

**76. What is Angular Router?**

It handles navigation between Angular views.

**77. What are route parameters?**

Dynamic URL path values such as /employees/101.

**78. What are query parameters?**

Optional URL values such as ?page=2.

**79. What is queryParamsHandling?**

It controls whether query parameters are merged, preserved or replaced.

**80. What is lazy loading?**

Loading feature code only when requested.

**81. What is CanActivate?**

A guard that controls route activation.

**82. What is CanMatch?**

A guard that controls whether a route can match.

**83. What is a resolver?**

It loads data before route activation.

**84. What is HttpClient?**

Angular's HTTP API for communicating with backend services.

**85. What is an interceptor?**

A mechanism for processing outgoing HTTP requests and incoming responses.

**86. How do you attach JWT tokens?**

Use an HTTP interceptor and attach the token to trusted backend requests.

**87. How do you handle global errors?**

Use an interceptor with catchError and a centralized notification service.

## I. Forms

**88. What are the types of Angular forms?**

Template-driven and reactive forms.

**89. Reactive vs template-driven forms?**

Reactive forms define controls and validation in TypeScript; template-driven forms use template-based configuration.

**90. What is FormBuilder?**

A helper for creating reactive form controls.

**91. What is FormArray?**

A dynamic collection of form controls or groups.

**92. setValue vs patchValue?**

setValue updates the full form structure; patchValue updates selected controls.

**93. What is a custom validator?**

A developer-defined validation function.

**94. What is an async validator?**

A validator that performs asynchronous validation, commonly through an API.

**95. What is ControlValueAccessor?**

A bridge between a custom form control and Angular Forms.

## J. Angular 18 and Advanced Topics

**96. What is @defer?**

It delays loading of non-critical template dependencies until a configured trigger.

**97. What is @let?**

It declares a local template variable. It was Developer Preview in Angular 18.

**98. What is Event Replay?**

It captures eligible browser events before hydration and replays them afterward.

**99. What is SSR?**

Rendering application HTML on the server.

**100. What is hydration?**

Attaching client-side Angular behavior to server-rendered HTML.

**101. What is incremental hydration?**

Hydrating deferred portions of an SSR application when configured triggers occur.

**102. What is tree shaking?**

Removing unused code from production bundles.

**103. What is dynamic component loading?**

Creating components at runtime using APIs such as ViewContainerRef.createComponent().

**104. createComponent vs createEmbeddedView?**

createComponent creates a component instance; createEmbeddedView renders an existing template.

**105. What is APP_INITIALIZER?**

An injection token that allows asynchronous or synchronous application initialization before startup completes.

**106. What is Angular Location?**

A service for browser URL and history operations.

**107. What is the difference between Location and Router?**

Location handles low-level URL/history operations; Router manages application navigation.

**108. What are micro frontends?**

An architecture that splits a frontend into independently developed and deployed applications.

**109. What is Angular CDK?**

A collection of low-level UI behavior and accessibility utilities.

**110. What is Material 3?**

A modern design system supported by Angular Material.

---

# MODULE 40 — 5+ Years Experience Preparation Checklist

## Core Angular

- [ ] Angular architecture
- [ ] Angular CLI
- [ ] Project structure
- [ ] Standalone components
- [ ] NgModules
- [ ] Component metadata
- [ ] Component lifecycle
- [ ] Component communication
- [ ] Data binding
- [ ] Template reference variables
- [ ] Directives
- [ ] Pipes
- [ ] Styling
- [ ] View encapsulation

## Advanced Components

- [ ] Content projection
- [ ] ngProjectAs
- [ ] ng-container
- [ ] ng-template
- [ ] ngTemplateOutlet
- [ ] TemplateRef
- [ ] ViewContainerRef
- [ ] ViewChild
- [ ] ViewChildren
- [ ] ContentChild
- [ ] ContentChildren
- [ ] QueryList
- [ ] Dynamic component loading

## Dependency Injection

- [ ] Injectable
- [ ] inject()
- [ ] InjectionToken
- [ ] Provider scopes
- [ ] Root injector
- [ ] EnvironmentInjector
- [ ] ElementInjector
- [ ] ModuleInjector
- [ ] Hierarchical DI
- [ ] useClass
- [ ] useValue
- [ ] useFactory
- [ ] useExisting
- [ ] Multi providers
- [ ] Self
- [ ] SkipSelf
- [ ] Optional
- [ ] Host
- [ ] providers vs viewProviders

## Reactive Programming

- [ ] Observable
- [ ] Subject
- [ ] BehaviorSubject
- [ ] ReplaySubject
- [ ] AsyncSubject
- [ ] switchMap
- [ ] mergeMap
- [ ] concatMap
- [ ] exhaustMap
- [ ] forkJoin
- [ ] combineLatest
- [ ] debounceTime
- [ ] distinctUntilChanged
- [ ] catchError
- [ ] retry
- [ ] AsyncPipe
- [ ] takeUntilDestroyed

## Angular 18

- [ ] Built-in control flow
- [ ] @if
- [ ] @for
- [ ] @switch
- [ ] @defer
- [ ] @let
- [ ] Signals
- [ ] Zoneless change detection
- [ ] Event Replay
- [ ] SSR
- [ ] Hydration
- [ ] Material 3

## Application Development

- [ ] Routing
- [ ] Guards
- [ ] Resolvers
- [ ] HTTP Client
- [ ] Interceptors
- [ ] Template-driven forms
- [ ] Reactive forms
- [ ] FormArray
- [ ] Custom validators
- [ ] ControlValueAccessor
- [ ] State management
- [ ] NgRx
- [ ] APP_INITIALIZER
- [ ] Environment configuration
- [ ] Location service

## Performance and Security

- [ ] OnPush
- [ ] ChangeDetectorRef
- [ ] Lazy loading
- [ ] Tree shaking
- [ ] Virtual scrolling
- [ ] Server-side pagination
- [ ] Caching
- [ ] XSS
- [ ] CSRF
- [ ] CORS
- [ ] CSP
- [ ] Authentication
- [ ] Authorization

## Architecture and Deployment

- [ ] Feature-based architecture
- [ ] Smart and presentational components
- [ ] Reusable components
- [ ] Micro frontends
- [ ] Web Workers
- [ ] Angular Material
- [ ] Angular CDK
- [ ] Unit testing
- [ ] Integration testing
- [ ] SSR deployment
- [ ] Production builds
- [ ] Docker
- [ ] CI/CD

---

# RECOMMENDED LEARNING ORDER

| Priority | Modules | Focus |
|---|---|---|
| P1 | 1–8 | Fundamentals, components, templates, directives and pipes |
| P1 | 9–14 | Communication, content projection, queries and DI |
| P1 | 15–23 | Change detection, Signals, RxJS, routing, HTTP, forms and state |
| P1 | 24–26 | Angular 18 control flow, lazy loading and performance |
| P2 | 27–34 | Security, architecture, SSR, zoneless, initialization and advanced UI |
| P2 | 35–37 | Testing, deployment and advanced architecture |
| P1 | 38–40 | Practical implementation, interview preparation and revision |

# PRACTICAL PROJECTS TO BUILD

1. **Employee Management Portal**
   - CRUD operations
   - Routing
   - Reactive forms
   - REST API integration
   - Pagination

2. **Authentication Module**
   - Login and logout
   - Token handling
   - Guards
   - Role-based navigation
   - HTTP interceptors

3. **Employee Dashboard**
   - Charts
   - Filtering
   - Pagination
   - Reusable tables
   - Signals

4. **Search Application**
   - Debouncing
   - switchMap
   - API integration
   - Error handling

5. **Large Data Table**
   - 50,000 records
   - Server-side pagination
   - Virtual scrolling
   - OnPush

6. **Reusable UI Library**
   - Custom components
   - Custom directives
   - Custom pipes
   - ControlValueAccessor
   - Content projection

7. **Advanced Angular Application**
   - Lazy loading
   - SSR
   - Hydration
   - Event Replay
   - Performance optimization

---

# FINAL PREPARATION STRATEGY

For a developer with 5+ years of experience, understanding implementation and architectural decisions is more important than memorizing definitions.

Be prepared to explain:

1. How Angular initializes and bootstraps an application.
2. How standalone components differ from NgModule-based applications.
3. How Angular resolves dependencies through hierarchical injectors.
4. How component communication works.
5. How Angular creates and renders embedded views.
6. How change detection works with Zone.js and OnPush.
7. When to use Signals versus RxJS.
8. How switchMap, mergeMap, concatMap and exhaustMap differ.
9. How to design reusable components and custom form controls.
10. How to prevent memory leaks.
11. How to optimize applications with large datasets.
12. How to implement secure authentication and authorization.
13. How to implement lazy loading and deferred loading.
14. How Angular 18 SSR, hydration and Event Replay work.
15. How Angular integrates with Spring Boot REST APIs.

**Target outcome:** Be able to independently design, implement, debug, optimize and explain an enterprise Angular application rather than simply answer theoretical interview questions.