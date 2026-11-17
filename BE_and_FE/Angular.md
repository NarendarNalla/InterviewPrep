# Angular 17 — Complete Learning & Interview Preparation Guide

**Target:** 5+ Years Experience | Full Stack Developer | Product-Based Companies  
**Version:** Angular 17  
**Example Application:** Employee Management Portal  
**Primary Stack:** Angular 17, TypeScript, RxJS, HTML, CSS, REST APIs

---

# TABLE OF CONTENTS

1. Angular Fundamentals and Architecture
2. Angular CLI and Project Structure
3. TypeScript Essentials for Angular
4. Components and Component Communication
5. Templates and Data Binding
6. Directives
7. Pipes
8. Services and Dependency Injection
9. Angular Lifecycle Hooks
10. Change Detection and Zones
11. Signals in Angular 17
12. RxJS and Reactive Programming
13. Routing and Navigation
14. Route Guards and Resolvers
15. HTTP Client and Interceptors
16. Forms — Template-Driven and Reactive
17. Custom Form Controls and Validation
18. State Management
19. Angular 17 Control Flow
20. Lazy Loading and Code Splitting
21. Performance Optimization
22. Angular Security
23. Reusable Components and Application Architecture
24. Angular Testing
25. SSR, Hydration and Angular Universal
26. Build, Deployment and Environment Configuration
27. Advanced Angular Concepts
28. Real-World Scenarios and Coding Problems
29. Angular 17 Interview Questions and Answers
30. 5+ Years Experience Preparation Checklist

---

# MODULE 1 — Angular Fundamentals and Architecture

## 1.1 What is Angular?

Angular is a TypeScript-based frontend framework developed by Google for building scalable, maintainable, single-page applications.

### Important features

- Component-based architecture
- TypeScript support
- Dependency Injection
- Routing
- RxJS and reactive programming
- Reactive and template-driven forms
- Built-in directives and pipes
- HTTP client
- Change detection
- Server-side rendering
- Lazy loading
- Signals

## 1.2 What is a Single Page Application (SPA)?

A SPA loads the application shell once and updates the content dynamically without reloading the complete page for every navigation.

**Traditional application flow:**

1. User requests a page.
2. Browser sends request to the server.
3. Server returns complete HTML.
4. Browser reloads the page.

**Angular SPA flow:**

1. Browser loads Angular application.
2. Angular initializes the application.
3. Router handles navigation.
4. Components render the requested content.
5. HTTP requests fetch backend data independently.

## 1.3 Angular Architecture

Main building blocks:

| Concept | Purpose |
|---|---|
| Component | Controls a portion of the UI |
| Template | Defines HTML presentation |
| Metadata | Configures Angular classes using decorators |
| Module | Groups components and services in NgModule-based applications |
| Directive | Changes DOM structure or behavior |
| Pipe | Transforms displayed data |
| Service | Contains reusable business logic |
| Dependency Injection | Provides dependencies to classes |
| Router | Manages navigation |
| Observable | Handles asynchronous streams |
| Signal | Holds reactive state |
| Injector | Resolves and provides dependencies |

### Angular application architecture

```text
Angular Application
│
├── App Component
│   ├── Header Component
│   ├── Sidebar Component
│   └── Router Outlet
│       ├── Employee Component
│       ├── Department Component
│       └── Dashboard Component
│
├── Services
│   ├── Employee Service
│   ├── Authentication Service
│   └── Notification Service
│
├── Models
│   ├── Employee Model
│   └── Department Model
│
├── Guards
├── Interceptors
├── Pipes
├── Directives
└── Shared Components
```

## 1.4 Angular vs React vs Vue

| Feature | Angular | React | Vue |
|---|---|---|---|
| Type | Framework | UI library | Progressive framework |
| Language | TypeScript | JavaScript/TypeScript | JavaScript/TypeScript |
| Routing | Built-in package | External library | Vue Router |
| Dependency Injection | Built-in | Not built-in | Available |
| Forms | Built-in solutions | External libraries commonly used | Available |
| State management | Signals, RxJS, NgRx | Context, Redux, etc. | Pinia |
| Learning curve | Relatively high | Moderate | Relatively low |

## 1.5 Angular 17 Important Changes

| Feature | Angular 17 |
|---|---|
| Standalone components | Default for newly generated components |
| Control flow | `@if`, `@for`, `@switch` |
| Deferred loading | `@defer` |
| Signals | Stable core reactive primitives |
| Build system | Modern application builder based on esbuild |
| Dev server | Vite-based development server in the modern CLI builder |
| SSR | Improved server rendering and hydration support |

**Interview question:** What are the major features introduced in Angular 17?

**Answer:** Built-in template control flow, deferred loading using `@defer`, standalone components becoming the default for new components, and improvements to the build and SSR experience.

---

# MODULE 2 — Angular CLI and Project Structure

## 2.1 Install Angular CLI

```bash
npm install -g @angular/cli@17
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

## 2.2 Important CLI Commands

| Command | Purpose |
|---|---|
| `ng new project-name` | Create Angular project |
| `ng serve` | Run development server |
| `ng build` | Build application |
| `ng generate component name` | Generate component |
| `ng generate service name` | Generate service |
| `ng generate directive name` | Generate directive |
| `ng generate pipe name` | Generate pipe |
| `ng generate guard name` | Generate route guard |
| `ng generate interceptor name` | Generate interceptor |
| `ng test` | Execute unit tests |
| `ng lint` | Run configured linting |
| `ng update` | Update Angular dependencies |

Short commands:

```bash
ng g c employee
ng g s employee
ng g d highlight
ng g p salary
ng g guard auth
ng g interceptor auth
```

## 2.3 Angular Project Structure

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
│   └── styles.css
│
├── angular.json
├── package.json
├── tsconfig.json
└── tsconfig.app.json
```

**Important files:**

| File | Responsibility |
|---|---|
| `main.ts` | Application bootstrap |
| `app.config.ts` | Application-level providers |
| `app.routes.ts` | Route definitions |
| `app.component.ts` | Root component |
| `angular.json` | Angular CLI configuration |
| `package.json` | Dependencies and scripts |
| `tsconfig.json` | TypeScript configuration |

## 2.4 Standalone Application Bootstrap

Angular 17 supports standalone application bootstrapping.

**main.ts**

```typescript
import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';

bootstrapApplication(AppComponent, appConfig)
  .catch(err => console.error(err));
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

**Interview question:** What is the difference between `bootstrapModule()` and `bootstrapApplication()`?

**Answer:**
- `bootstrapModule()` bootstraps an NgModule-based application.
- `bootstrapApplication()` bootstraps a standalone root component.
- Standalone bootstrapping reduces the need for root NgModule configuration.

---

# MODULE 3 — TypeScript Essentials for Angular

Angular relies heavily on TypeScript. A 5+ year developer should be comfortable with the following concepts.

## 3.1 Basic Types

```typescript
let employeeName: string = 'Naren';
let employeeId: number = 101;
let isActive: boolean = true;

let skills: string[] = ['Java', 'Angular', 'Spring Boot'];

let employee: {
  id: number;
  name: string;
} = {
  id: 101,
  name: 'John'
};
```

## 3.2 Interface

Interfaces define object structure.

```typescript
export interface Employee {
  id: number;
  name: string;
  email: string;
  salary: number;
  department?: string;
}
```

Usage:

```typescript
const employee: Employee = {
  id: 101,
  name: 'John',
  email: 'john@example.com',
  salary: 75000
};
```

## 3.3 Type vs Interface

| Interface | Type |
|---|---|
| Primarily defines object contracts | Defines aliases for many types |
| Supports declaration merging | Does not support declaration merging |
| Supports extension using `extends` | Supports intersections using `&` |
| Useful for API models | Useful for unions, tuples and complex aliases |

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
export class Person {
  constructor(
    public name: string,
    public age: number
  ) {}

  display(): void {
    console.log(this.name);
  }
}

export class Employee extends Person {
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
| private | Within the declaring class |
| protected | Within the class and subclasses |
| readonly | Cannot be reassigned after initialization |

## 3.6 Generics

Generics enable reusable, type-safe code.

```typescript
function getData<T>(data: T): T {
  return data;
}

const name = getData<string>('Angular');
const employee = getData<Employee>({
  id: 1,
  name: 'John',
  email: 'john@example.com',
  salary: 50000
});
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

- `?.` prevents errors when accessing properties on null or undefined values.
- `??` uses the fallback only when the value is null or undefined.
- `||` uses the fallback for all falsy values, including `0`, `false` and empty strings.

## 3.8 Enums

```typescript
enum EmployeeStatus {
  ACTIVE = 'ACTIVE',
  INACTIVE = 'INACTIVE',
  TERMINATED = 'TERMINATED'
}

const status = EmployeeStatus.ACTIVE;
```

## 3.9 Important JavaScript Concepts

Be comfortable with:

- `let`, `const`, `var`
- Arrow functions
- Destructuring
- Spread and rest operators
- Template literals
- Array methods: `map`, `filter`, `reduce`, `find`, `some`, `every`
- Promises and async/await
- Closures
- Hoisting
- Event loop
- `this` keyword
- Shallow copy vs deep copy

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

# MODULE 4 — Components and Component Communication

## 4.1 What is a Component?

A component controls a section of the application UI.

It generally contains:

1. TypeScript class
2. HTML template
3. CSS styles
4. Component metadata

## 4.2 Create a Standalone Component

**employee.component.ts**

```typescript
import { Component } from '@angular/core';

@Component({
  selector: 'app-employee',
  standalone: true,
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
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

A component must be imported by its parent when using standalone architecture.

```typescript
import { Component } from '@angular/core';
import { EmployeeComponent } from './employee/employee.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [EmployeeComponent],
  template: `<app-employee />`
})
export class AppComponent {}
```

## 4.3 Parent to Child Communication — @Input

Parent component passes data to a child component.

**employee-list.component.ts**

```typescript
import { Component } from '@angular/core';
import { EmployeeCardComponent } from './employee-card.component';

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

  selectedEmployee = {
    id: 101,
    name: 'John',
    salary: 70000
  };
}
```

**employee-card.component.ts**

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

  @Input() employee!: {
    id: number;
    name: string;
    salary: number;
  };
}
```

## 4.4 Child to Parent Communication — @Output

Child component sends data to parent using EventEmitter.

**employee-card.component.ts**

```typescript
import { Component, EventEmitter, Input, Output } from '@angular/core';

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

  @Output() delete = new EventEmitter<number>();

  deleteEmployee(): void {
    this.delete.emit(this.employeeId);
  }
}
```

**Parent template**

```html
<app-employee-card
  [employeeId]="101"
  (delete)="onDeleteEmployee($event)">
</app-employee-card>
```

**Parent TypeScript**

```typescript
onDeleteEmployee(id: number): void {
  console.log('Deleted employee:', id);
}
```

## 4.5 Communication Methods

| Method | Use case |
|---|---|
| @Input | Parent to child |
| @Output | Child to parent |
| Shared service | Unrelated components |
| RxJS Subject | Event communication |
| BehaviorSubject | Shared state with current value |
| Signals | Reactive shared state |
| Router parameters | Pass navigation-related data |

## 4.6 ViewChild and ContentChild

`ViewChild` accesses elements or child components in the component's own view.

```typescript
import {
  AfterViewInit,
  Component,
  ElementRef,
  ViewChild
} from '@angular/core';

@Component({
  selector: 'app-search',
  standalone: true,
  template: `<input #searchInput type="text">`
})
export class SearchComponent implements AfterViewInit {

  @ViewChild('searchInput')
  searchInput!: ElementRef<HTMLInputElement>;

  ngAfterViewInit(): void {
    this.searchInput.nativeElement.focus();
  }
}
```

`ContentChild` accesses projected content supplied through `ng-content`.

## 4.7 Content Projection

Allows a parent to pass HTML content into a child component.

**card.component.ts**

```typescript
import { Component } from '@angular/core';

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

**Usage**

```html
<app-card>
  <h2>Employee Details</h2>
  <p>Employee information goes here.</p>
</app-card>
```

Multiple projection slots can use `ng-content select`.

---

# MODULE 5 — Templates and Data Binding

## 5.1 Interpolation

Displays component data in HTML.

```typescript
employeeName = 'John';
```

```html
<h2>{{ employeeName }}</h2>
```

## 5.2 Property Binding

Passes a component value to an HTML property.

```typescript
isDisabled = true;
```

```html
<button [disabled]="isDisabled">
  Submit
</button>
```

## 5.3 Event Binding

Handles DOM events.

```html
<button (click)="saveEmployee()">
  Save
</button>
```

## 5.4 Two-Way Data Binding

Combines property and event binding.

```typescript
import { Component } from '@angular/core';
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

## 5.5 Attribute, Class and Style Binding

```html
<td [attr.colspan]="columnSpan">
  Employee
</td>

<div [class.active]="isActive">
  Employee Status
</div>

<div [style.color]="isActive ? 'green' : 'red'">
  Status
</div>
```

Class and style objects:

```html
<div [ngClass]="{
  active: isActive,
  disabled: !isActive
}">
  Employee
</div>

<div [ngStyle]="{
  color: isActive ? 'green' : 'red',
  fontSize: '18px'
}">
  Status
</div>
```

For `ngClass` and `ngStyle`, import `CommonModule` or the respective directives.

---

# MODULE 6 — Directives

A directive changes the behavior or appearance of DOM elements.

## 6.1 Types of Directives

| Type | Purpose | Example |
|---|---|---|
| Component | UI with template | EmployeeComponent |
| Structural | Adds/removes DOM elements | `*ngIf`, `*ngFor` |
| Attribute | Changes appearance or behavior | `ngClass`, `ngStyle` |
| Custom | Developer-defined behavior | HighlightDirective |

## 6.2 Structural Directive — ngIf

```typescript
import { Component, CommonModule } from '@angular/core';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [CommonModule],
  template: `
    <p *ngIf="isActive">
      Employee is active
    </p>
  `
})
export class EmployeeComponent {
  isActive = true;
}
```

## 6.3 Structural Directive — ngFor

```typescript
employees = [
  { id: 1, name: 'John' },
  { id: 2, name: 'David' }
];
```

```html
<div *ngFor="let employee of employees; let i = index">
  {{ i + 1 }} - {{ employee.name }}
</div>
```

## 6.4 ngSwitch

```html
<div [ngSwitch]="status">

  <p *ngSwitchCase="'ACTIVE'">Active</p>

  <p *ngSwitchCase="'INACTIVE'">Inactive</p>

  <p *ngSwitchDefault>Unknown</p>

</div>
```

Import `CommonModule` for these directives.

## 6.5 Custom Attribute Directive

Example: Highlight an element when the mouse enters it.

**highlight.directive.ts**

```typescript
import {
  Directive,
  ElementRef,
  HostListener,
  Renderer2
} from '@angular/core';

@Directive({
  selector: '[appHighlight]',
  standalone: true
})
export class HighlightDirective {

  constructor(
    private elementRef: ElementRef,
    private renderer: Renderer2
  ) {}

  @HostListener('mouseenter')
  onMouseEnter(): void {
    this.renderer.setStyle(
      this.elementRef.nativeElement,
      'backgroundColor',
      'yellow'
    );
  }

  @HostListener('mouseleave')
  onMouseLeave(): void {
    this.renderer.removeStyle(
      this.elementRef.nativeElement,
      'backgroundColor'
    );
  }
}
```

Usage:

```typescript
@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [HighlightDirective],
  template: `<p appHighlight>Employee Name</p>`
})
export class EmployeeComponent {}
```

**Important:** Prefer `Renderer2` over direct DOM manipulation for normal DOM changes. Avoid using `ElementRef.nativeElement` for unsafe DOM operations.

## 6.6 HostBinding and HostListener

- `HostListener` listens to host element events.
- `HostBinding` binds a property or attribute of the host element.

---

# MODULE 7 — Pipes

Pipes transform values before displaying them.

## 7.1 Built-in Pipes

| Pipe | Purpose |
|---|---|
| DatePipe | Formats dates |
| CurrencyPipe | Formats currency |
| DecimalPipe | Formats numbers |
| PercentPipe | Converts to percentage |
| UpperCasePipe | Uppercase |
| LowerCasePipe | Lowercase |
| TitleCasePipe | Title case |
| JsonPipe | Displays JSON |
| SlicePipe | Extracts array/string portion |
| AsyncPipe | Subscribes to Observable or Promise |

Example:

```typescript
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [CommonModule],
  template: `
    <p>{{ employeeName | uppercase }}</p>
    <p>{{ salary | currency:'INR' }}</p>
    <p>{{ joiningDate | date:'dd-MM-yyyy' }}</p>
  `
})
export class EmployeeComponent {
  employeeName = 'john';
  salary = 75000;
  joiningDate = new Date();
}
```

## 7.2 Custom Pipe

Example: Convert employee name to uppercase.

```typescript
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'employeeFormat',
  standalone: true
})
export class EmployeeFormatPipe implements PipeTransform {

  transform(value: string): string {
    return value ? value.toUpperCase() : '';
  }
}
```

Usage:

```typescript
import { Component } from '@angular/core';
import { EmployeeFormatPipe } from './employee-format.pipe';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [EmployeeFormatPipe],
  template: `{{ employeeName | employeeFormat }}`
})
export class EmployeeComponent {
  employeeName = 'john';
}
```

## 7.3 Pure vs Impure Pipes

| Pure Pipe | Impure Pipe |
|---|---|
| Default behavior | `pure: false` |
| Runs when primitive value or object reference changes | Can run during every change detection cycle |
| Better performance | Can affect performance |
| Recommended in most cases | Use only when necessary |

**Interview question:** Why does a pipe sometimes not update when an array is modified?

**Answer:** Pure pipes detect reference changes, not internal mutations. Replacing the array with a new reference triggers the pipe.

```typescript
this.employees = [...this.employees, newEmployee];
```

---

# MODULE 8 — Services and Dependency Injection

## 8.1 What is a Service?

A service is a class used to share reusable business logic and data across components.

Typical responsibilities:

- API communication
- Authentication
- Business logic
- Shared state
- Logging
- Notifications

## 8.2 Create Employee Service

**employee.service.ts**

```typescript
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Employee {
  id: number;
  name: string;
  email: string;
  salary: number;
}

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private apiUrl = 'https://api.example.com/employees';

  constructor(private http: HttpClient) {}

  getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.apiUrl);
  }

  getEmployeeById(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.apiUrl}/${id}`);
  }

  createEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(this.apiUrl, employee);
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
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
```

## 8.3 Register HttpClient

**app.config.ts**

```typescript
import { ApplicationConfig } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient()
  ]
};
```

## 8.4 Use Service in Component

```typescript
import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmployeeService, Employee } from './employee.service';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div *ngFor="let employee of employees">
      {{ employee.name }}
    </div>
  `
})
export class EmployeeListComponent implements OnInit {

  private employeeService = inject(EmployeeService);

  employees: Employee[] = [];

  ngOnInit(): void {
    this.employeeService.getEmployees().subscribe({
      next: data => this.employees = data,
      error: error => console.error(error)
    });
  }
}
```

## 8.5 What is Dependency Injection?

Dependency Injection is a design pattern where an object receives its dependencies from an external injector instead of creating them itself.

### Without DI

```typescript
class EmployeeComponent {
  service = new EmployeeService();
}
```

Problems:
- Tight coupling
- Difficult unit testing
- Hard to replace dependencies

### With DI

```typescript
import { Component, inject } from '@angular/core';

@Component({
  selector: 'app-employee',
  standalone: true,
  template: `Employee`
})
export class EmployeeComponent {

  private employeeService = inject(EmployeeService);
}
```

## 8.6 Provider Scopes

| Provider | Scope |
|---|---|
| `providedIn: 'root'` | Application root injector |
| Component `providers` | Component's injector subtree |
| Route providers | Route-level injector |
| `providedIn: 'platform'` | Platform injector |

**Important:** Providing a service at component level creates a separate instance for that component's injector subtree. It is not necessarily a global singleton.

## 8.7 Hierarchical Dependency Injection

Angular uses a hierarchical injector system.

Important concepts:

- EnvironmentInjector
- ElementInjector
- Provider resolution
- `@Optional()`
- `@Self()`
- `@SkipSelf()`
- `@Host()`

Know how Angular searches for a dependency and how provider scope affects service instances.

---

# MODULE 9 — Angular Lifecycle Hooks

Lifecycle hooks allow developers to execute code at specific component lifecycle stages.

| Hook | Purpose |
|---|---|
| ngOnChanges | Runs when input properties change |
| ngOnInit | Runs once after initial inputs are set |
| ngDoCheck | Custom change detection |
| ngAfterContentInit | After projected content initialization |
| ngAfterContentChecked | After projected content is checked |
| ngAfterViewInit | After component view initialization |
| ngAfterViewChecked | After component view is checked |
| ngOnDestroy | Before component destruction |

Example:

```typescript
import {
  Component,
  OnInit,
  OnChanges,
  OnDestroy,
  SimpleChanges,
  Input
} from '@angular/core';

@Component({
  selector: 'app-employee',
  standalone: true,
  template: `Employee`
})
export class EmployeeComponent
  implements OnInit, OnChanges, OnDestroy {

  @Input() employeeId!: number;

  ngOnChanges(changes: SimpleChanges): void {
    console.log('Input changed', changes);
  }

  ngOnInit(): void {
    console.log('Component initialized');
  }

  ngOnDestroy(): void {
    console.log('Component destroyed');
  }
}
```

## 9.1 Constructor vs ngOnInit

| Constructor | ngOnInit |
|---|---|
| TypeScript class initialization | Angular lifecycle hook |
| Used for dependency injection and basic initialization | Used for component initialization |
| Runs before Angular initializes input bindings | Runs after initial input values are set |
| Not a lifecycle hook | Lifecycle hook |

## 9.2 ngOnDestroy and Subscription Cleanup

```typescript
import { Component, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

export class EmployeeComponent implements OnDestroy {

  private subscription = new Subscription();

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
```

Angular 16+ also supports `takeUntilDestroyed`.

```typescript
import { Component, DestroyRef, inject } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

export class EmployeeComponent {

  private destroyRef = inject(DestroyRef);

  loadEmployees(): void {
    this.employeeService.getEmployees()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe(data => console.log(data));
  }
}
```

---

# MODULE 10 — Change Detection and Zones

## 10.1 What is Change Detection?

Change detection is the process through which Angular identifies changes in application state and updates the DOM.

Example:

```typescript
employeeName = 'John';

updateName(): void {
  this.employeeName = 'David';
}
```

```html
<h2>{{ employeeName }}</h2>

<button (click)="updateName()">Update</button>
```

Angular updates the displayed name after the event.

## 10.2 Change Detection Strategies

### Default Strategy

Angular checks the component tree according to its normal change detection scheduling.

```typescript
import { Component } from '@angular/core';

@Component({
  selector: 'app-employee',
  standalone: true,
  template: `Employee`
})
export class EmployeeComponent {}
```

### OnPush Strategy

OnPush allows Angular to skip unnecessary checks for a component subtree under defined conditions.

```typescript
import { Component, ChangeDetectionStrategy, Input } from '@angular/core';

@Component({
  selector: 'app-employee',
  standalone: true,
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `<h2>{{ employee.name }}</h2>`
})
export class EmployeeComponent {
  @Input() employee!: { name: string };
}
```

Typical triggers include:

- A new input value/reference
- An event handled within the component subtree
- An explicit change detection notification
- A signal update read by the template

## 10.3 ChangeDetectorRef

```typescript
import {
  ChangeDetectorRef,
  Component,
  inject
} from '@angular/core';

@Component({
  selector: 'app-employee',
  standalone: true,
  template: `Employee`
})
export class EmployeeComponent {

  private cdr = inject(ChangeDetectorRef);

  refreshView(): void {
    this.cdr.markForCheck();
  }
}
```

Important methods:

| Method | Purpose |
|---|---|
| markForCheck() | Marks component for checking |
| detectChanges() | Runs local change detection |
| detach() | Detaches view from normal checking |
| reattach() | Reattaches view |
| checkNoChanges() | Checks for unexpected changes in development scenarios |

## 10.4 Zone.js

Zone.js helps Angular detect asynchronous activity and schedule change detection.

Examples:
- setTimeout
- DOM events
- Promise callbacks
- HTTP operations

`NgZone.runOutsideAngular()` can be used for work that should not trigger Angular change detection.

**Interview question:** How do you improve performance using OnPush?

**Answer:**
- Use immutable data updates.
- Avoid unnecessary object mutations.
- Use signals or observables with AsyncPipe.
- Avoid expensive template methods.
- Use trackBy or `track` in loops.
- Keep component responsibilities small.

---

# MODULE 11 — Signals in Angular 17

Signals are reactive primitives used to manage state and automatically notify consumers when values change.

## 11.1 signal()

```typescript
import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-counter',
  standalone: true,
  template: `
    <h2>{{ count() }}</h2>
    <button (click)="increment()">Increment</button>
  `
})
export class CounterComponent {

  count = signal(0);

  increment(): void {
    this.count.update(value => value + 1);
  }
}
```

Important methods:

| Method | Purpose |
|---|---|
| signal() | Creates writable signal |
| set() | Replaces the value |
| update() | Updates using the current value |
| asReadonly() | Exposes a readonly signal |

## 11.2 computed()

Computed signals derive values from other signals.

```typescript
import { Component, signal, computed } from '@angular/core';

@Component({
  selector: 'app-salary',
  standalone: true,
  template: `
    <p>Basic: {{ basicSalary() }}</p>
    <p>Bonus: {{ bonus() }}</p>
    <p>Total: {{ totalSalary() }}</p>
  `
})
export class SalaryComponent {

  basicSalary = signal(50000);
  bonus = signal(10000);

  totalSalary = computed(
    () => this.basicSalary() + this.bonus()
  );
}
```

## 11.3 effect()

Effects execute side effects when the signals they read change.

```typescript
import { Component, effect, signal } from '@angular/core';

@Component({
  selector: 'app-counter',
  standalone: true,
  template: `{{ count() }}`
})
export class CounterComponent {

  count = signal(0);

  constructor() {
    effect(() => {
      console.log('Count changed:', this.count());
    });
  }
}
```

Use effects for side effects such as logging or synchronization, not as a replacement for `computed()` or normal event handling.

## 11.4 Signals vs RxJS

| Signals | RxJS |
|---|---|
| Represents reactive state | Represents asynchronous streams |
| Synchronous value access | Observable subscription |
| `signal()` | `Observable` |
| `computed()` | Operators such as `map` and `combineLatest` for stream derivation |
| `effect()` | Side-effect handling in reactive signal contexts |
| Suitable for local and shared state | Suitable for HTTP, events, streams and complex async flows |

## 11.5 Signal Interoperability

Angular provides interoperability utilities such as `toSignal()` and `toObservable()`.

```typescript
import { Component, inject } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-employee',
  standalone: true,
  template: `{{ employees()?.length }}`
})
export class EmployeeComponent {

  private employeeService = inject(EmployeeService);

  employees = toSignal(
    this.employeeService.getEmployees(),
    { initialValue: [] }
  );
}
```

**Angular 17 version note:** Core signals and the built-in control-flow syntax are stable in Angular 17. Signal-based component inputs were introduced in the Angular 17 release line as a developer-preview feature. Do not assume every newer signal-based API is stable in Angular 17.

---

# MODULE 12 — RxJS and Reactive Programming

RxJS is essential for handling asynchronous data streams in Angular.

## 12.1 Observable

An Observable represents a stream of values over time.

```typescript
import { Observable } from 'rxjs';

const observable = new Observable<number>(subscriber => {
  subscriber.next(10);
  subscriber.next(20);
  subscriber.next(30);
  subscriber.complete();
});

observable.subscribe(value => console.log(value));
```

## 12.2 Observable vs Promise

| Observable | Promise |
|---|---|
| Can emit multiple values | Resolves or rejects once |
| Lazy by default | Starts when created |
| Can be cancelled through unsubscribe | No built-in cancellation |
| Supports RxJS operators | Uses then/catch/finally |
| Can be synchronous or asynchronous | Asynchronous result |

## 12.3 Subject Types

| Type | Behavior |
|---|---|
| Subject | Multicasts new values |
| BehaviorSubject | Holds latest value and emits it to new subscribers |
| ReplaySubject | Replays a configured number of previous values |
| AsyncSubject | Emits the last value on completion |

Example:

```typescript
import { BehaviorSubject } from 'rxjs';

export class EmployeeStateService {

  private employeeSubject =
    new BehaviorSubject<string>('John');

  employee$ = this.employeeSubject.asObservable();

  updateEmployee(name: string): void {
    this.employeeSubject.next(name);
  }
}
```

## 12.4 Important RxJS Operators

### Transformation Operators

| Operator | Purpose |
|---|---|
| map | Transform emitted values |
| pluck | Extract property (deprecated in newer RxJS usage; prefer map) |
| scan | Accumulate values |
| switchMap | Switch to a new inner Observable |
| mergeMap | Subscribe to multiple inner Observables concurrently |
| concatMap | Process inner Observables sequentially |
| exhaustMap | Ignore new triggers while one inner Observable is active |

### Filtering Operators

| Operator | Purpose |
|---|---|
| filter | Filter values |
| distinctUntilChanged | Ignore consecutive duplicate values |
| debounceTime | Wait for inactivity |
| throttleTime | Limit emission frequency |
| take | Take a fixed number of values |
| first | Take first matching value |
| skip | Skip initial values |

### Combination Operators

| Operator | Purpose |
|---|---|
| combineLatest | Emits when any source changes after all have emitted |
| forkJoin | Waits for all sources to complete and emits their last values |
| merge | Merges multiple streams |
| concat | Subscribes to sources sequentially |
| zip | Combines corresponding emissions |
| withLatestFrom | Combines source emission with latest values from other streams |

### Error Handling

| Operator | Purpose |
|---|---|
| catchError | Handles errors |
| retry | Retries failed stream |
| retryWhen | Conditional retry logic in older RxJS patterns |
| finalize | Executes on completion, error or unsubscribe |

## 12.5 switchMap Example — Search API

Use case: Search employees while typing.

```typescript
import { Component, inject } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [ReactiveFormsModule, AsyncPipe],
  template: `
    <input [formControl]="searchControl">

    <div *ngFor="let employee of employees$ | async">
      {{ employee.name }}
    </div>
  `
})
export class EmployeeSearchComponent {

  private employeeService = inject(EmployeeService);

  searchControl = new FormControl('', { nonNullable: true });

  employees$ = this.searchControl.valueChanges.pipe(
    debounceTime(300),
    distinctUntilChanged(),
    switchMap(value =>
      this.employeeService.searchEmployees(value)
    )
  );
}
```

## 12.6 switchMap vs mergeMap vs concatMap vs exhaustMap

| Operator | Behavior | Common use case |
|---|---|---|
| switchMap | Cancels previous inner subscription | Search autocomplete |
| mergeMap | Allows concurrent inner subscriptions | Independent parallel requests |
| concatMap | Queues requests sequentially | Ordered updates |
| exhaustMap | Ignores new triggers while busy | Prevent duplicate form submissions |

## 12.7 forkJoin Example

```typescript
import { forkJoin } from 'rxjs';

forkJoin({
  employees: this.employeeService.getEmployees(),
  departments: this.departmentService.getDepartments()
}).subscribe(result => {
  console.log(result.employees);
  console.log(result.departments);
});
```

`forkJoin` is appropriate for finite streams such as normal HTTP requests. It waits for every source to complete.

## 12.8 AsyncPipe

```typescript
employees$ = this.employeeService.getEmployees();
```

```html
<div *ngFor="let employee of employees$ | async">
  {{ employee.name }}
</div>
```

AsyncPipe manages subscriptions and cleanup automatically when the view is destroyed.

---

# MODULE 13 — Routing and Navigation

Angular Router enables navigation between components without full-page reloads.

## 13.1 Configure Routes

**app.routes.ts**

```typescript
import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EmployeeListComponent } from './employee/employee-list.component';
import { EmployeeDetailsComponent } from './employee/employee-details.component';

export const routes: Routes = [

  { path: '', component: DashboardComponent },

  { path: 'employees', component: EmployeeListComponent },

  {
    path: 'employees/:id',
    component: EmployeeDetailsComponent
  },

  { path: '**', redirectTo: '' }
];
```

## 13.2 Register Router

**app.config.ts**

```typescript
import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes)
  ]
};
```

## 13.3 Router Outlet

```typescript
import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
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

## 13.4 Route Parameters

```typescript
import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-employee-details',
  standalone: true,
  template: `Employee Details`
})
export class EmployeeDetailsComponent implements OnInit {

  private route = inject(ActivatedRoute);

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    console.log(id);
  }
}
```

Reactive route parameter:

```typescript
this.route.paramMap.subscribe(params => {
  const id = params.get('id');
  console.log(id);
});
```

## 13.5 Query Parameters

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
import { Router } from '@angular/router';

private router = inject(Router);

navigateToEmployees(): void {
  this.router.navigate(['/employees'], {
    queryParams: {
      page: 2,
      status: 'ACTIVE'
    }
  });
}
```

## 13.6 Child Routes

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

The parent component must contain its own `<router-outlet>`.

## 13.7 Router Events

Router events can be used for:
- Loading indicators
- Navigation logging
- Analytics
- Navigation failure handling

Important events include `NavigationStart`, `NavigationEnd`, `NavigationCancel` and `NavigationError`.

---

# MODULE 14 — Route Guards and Resolvers

## 14.1 What are Route Guards?

Guards control whether navigation is allowed.

| Guard | Purpose |
|---|---|
| CanActivate | Controls route activation |
| CanActivateChild | Controls child route activation |
| CanDeactivate | Controls leaving a route |
| CanMatch | Controls whether a route matches |

## 14.2 Functional Auth Guard

```typescript
import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';

export const authGuard: CanActivateFn = () => {

  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.isLoggedIn()) {
    return true;
  }

  return router.createUrlTree(['/login']);
};
```

Apply it:

```typescript
{
  path: 'employees',
  component: EmployeeListComponent,
  canActivate: [authGuard]
}
```

## 14.3 Route Resolver

A resolver fetches required data before route activation.

```typescript
import { inject } from '@angular/core';
import { ResolveFn } from '@angular/router';
import { EmployeeService, Employee } from './employee.service';

export const employeeResolver: ResolveFn<Employee> = route => {

  const id = Number(route.paramMap.get('id'));

  return inject(EmployeeService).getEmployeeById(id);
};
```

Configure:

```typescript
{
  path: 'employees/:id',
  component: EmployeeDetailsComponent,
  resolve: {
    employee: employeeResolver
  }
}
```

Read data:

```typescript
import { ActivatedRoute } from '@angular/router';
import { inject } from '@angular/core';

private route = inject(ActivatedRoute);

ngOnInit(): void {
  const employee = this.route.snapshot.data['employee'];
}
```

**Important security point:** Angular route guards are client-side navigation controls, not security boundaries. Authorization must also be enforced by the backend.

---

# MODULE 15 — HTTP Client and Interceptors

## 15.1 HttpClient

Angular HttpClient communicates with backend REST APIs.

Import:

```typescript
import { HttpClient } from '@angular/common/http';
```

Register:

```typescript
import { provideHttpClient } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient()
  ]
};
```

## 15.2 HTTP Methods

| Method | Purpose |
|---|---|
| GET | Retrieve data |
| POST | Create resource |
| PUT | Replace/update resource |
| PATCH | Partially update resource |
| DELETE | Delete resource |

## 15.3 HTTP Interceptor

An interceptor can modify outgoing requests and process incoming responses.

Common use cases:
- Attach authentication token
- Add correlation ID
- Logging
- Error handling
- Request timing
- Common headers

### Functional Interceptor

**auth.interceptor.ts**

```typescript
import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {

  const token = localStorage.getItem('access_token');

  const clonedRequest = req.clone({
    setHeaders: {
      Authorization: token ? `Bearer ${token}` : ''
    }
  });

  return next(clonedRequest);
};
```

Register:

```typescript
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { authInterceptor } from './auth.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(
      withInterceptors([authInterceptor])
    )
  ]
};
```

Do not attach tokens indiscriminately to third-party URLs. Validate the request destination.

## 15.4 Global Error Handling

```typescript
import { HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {

  return next(req).pipe(
    catchError(error => {
      console.error('HTTP Error:', error);
      return throwError(() => error);
    })
  );
};
```

## 15.5 HttpParams

```typescript
import { HttpParams } from '@angular/common/http';

const params = new HttpParams()
  .set('page', 1)
  .set('size', 10)
  .set('status', 'ACTIVE');

this.http.get('/api/employees', { params });
```

`HttpParams` is immutable. Each `set()` returns a new instance.

## 15.6 Retry and Timeout

```typescript
import { retry, timeout, catchError, throwError } from 'rxjs';

this.http.get('/api/employees').pipe(
  timeout(5000),
  retry(2),
  catchError(error => throwError(() => error))
);
```

Use retry selectively. Retrying non-idempotent operations such as POST can create duplicate effects unless the backend supports idempotency.

---

# MODULE 16 — Forms: Template-Driven and Reactive

Angular supports two primary approaches to form handling.

## 16.1 Template-Driven Forms

Suitable for simple forms where most logic lives in the template.

Import:

```typescript
import { FormsModule } from '@angular/forms';
```

Component:

```typescript
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-employee-form',
  standalone: true,
  imports: [FormsModule],
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
<form #employeeForm="ngForm" (ngSubmit)="submitForm()">

  <input
    name="name"
    [(ngModel)]="employee.name"
    required
    minlength="3"
    #name="ngModel">

  <div *ngIf="name.invalid && name.touched">
    Name is required and must have at least 3 characters.
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

## 16.2 Reactive Forms

Reactive forms define form controls and validation in TypeScript.

Import:

```typescript
import {
  ReactiveFormsModule,
  FormGroup,
  FormControl,
  Validators
} from '@angular/forms';
```

Component:

```typescript
import { Component } from '@angular/core';
import {
  FormGroup,
  FormControl,
  Validators,
  ReactiveFormsModule
} from '@angular/forms';

@Component({
  selector: 'app-employee-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './employee-form.component.html'
})
export class EmployeeFormComponent {

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

  submitForm(): void {
    if (this.employeeForm.valid) {
      console.log(this.employeeForm.value);
    } else {
      this.employeeForm.markAllAsTouched();
    }
  }
}
```

HTML:

```html
<form [formGroup]="employeeForm" (ngSubmit)="submitForm()">

  <input formControlName="name">
  <input formControlName="email">
  <input type="number" formControlName="salary">

  <button type="submit">Save</button>

</form>
```

## 16.3 FormBuilder

FormBuilder reduces boilerplate when creating complex forms.

```typescript
import { Component, inject } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [ReactiveFormsModule],
  template: `Employee Form`
})
export class EmployeeComponent {

  private fb = inject(FormBuilder);

  employeeForm = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    salary: [0, [Validators.required, Validators.min(10000)]]
  });
}
```

## 16.4 FormArray

Useful for dynamic form fields.

```typescript
import { Component, inject } from '@angular/core';
import {
  FormArray,
  FormBuilder,
  ReactiveFormsModule
} from '@angular/forms';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [ReactiveFormsModule],
  template: `
    <div formArrayName="skills">
      @for (skill of skills.controls; track $index; let i = $index) {
        <input [formControlName]="i">
      }
    </div>

    <button type="button" (click)="addSkill()">Add Skill</button>
  `
})
export class EmployeeComponent {

  private fb = inject(FormBuilder);

  form = this.fb.group({
    skills: this.fb.array([])
  });

  get skills(): FormArray {
    return this.form.get('skills') as FormArray;
  }

  addSkill(): void {
    this.skills.push(this.fb.control(''));
  }
}
```

## 16.5 FormGroup vs FormControl vs FormArray

| Concept | Purpose |
|---|---|
| FormControl | Manages one field |
| FormGroup | Groups multiple controls |
| FormArray | Manages dynamic collection of controls |

## 16.6 Custom Validators

```typescript
import { AbstractControl, ValidationErrors } from '@angular/forms';

export function noSpaceValidator(
  control: AbstractControl
): ValidationErrors | null {

  const value = control.value;

  if (typeof value === 'string' && value.includes(' ')) {
    return { noSpace: true };
  }

  return null;
}
```

Usage:

```typescript
name: ['', [Validators.required, noSpaceValidator]]
```

## 16.7 Cross-Field Validation

Example: Password and confirm password must match.

```typescript
import {
  AbstractControl,
  ValidationErrors
} from '@angular/forms';

export function passwordMatchValidator(
  group: AbstractControl
): ValidationErrors | null {

  const password = group.get('password')?.value;
  const confirmPassword = group.get('confirmPassword')?.value;

  return password === confirmPassword
    ? null
    : { passwordMismatch: true };
}
```

## 16.8 Form Status and Methods

| Property/Method | Purpose |
|---|---|
| valid | Form is valid |
| invalid | Form is invalid |
| touched | User interacted and left control |
| dirty | Value changed |
| pristine | Value unchanged |
| pending | Async validation in progress |
| setValue() | Sets all required controls |
| patchValue() | Updates selected controls |
| reset() | Resets the form |
| markAllAsTouched() | Marks all controls touched |

**Interview question:** Difference between `setValue()` and `patchValue()`?

**Answer:**
- `setValue()` expects values for all controls in the form structure.
- `patchValue()` updates only the specified controls.

---

# MODULE 17 — Custom Form Controls and Validation

## 17.1 ControlValueAccessor (CVA)

ControlValueAccessor connects a custom UI component with Angular Forms.

It is useful when building reusable controls such as:
- Custom dropdown
- Date picker
- Rating component
- Searchable select
- Multi-select component

Main methods:

| Method | Purpose |
|---|---|
| writeValue | Receives value from form |
| registerOnChange | Registers change callback |
| registerOnTouched | Registers touched callback |
| setDisabledState | Handles disabled state |

Example:

```typescript
import {
  Component,
  forwardRef
} from '@angular/core';

import {
  ControlValueAccessor,
  NG_VALUE_ACCESSOR
} from '@angular/forms';

@Component({
  selector: 'app-custom-input',
  standalone: true,
  template: `
    <input
      [value]="value"
      [disabled]="disabled"
      (input)="onInput($event)"
      (blur)="onTouched()">
  `,
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => CustomInputComponent),
      multi: true
    }
  ]
})
export class CustomInputComponent implements ControlValueAccessor {

  value = '';
  disabled = false;

  onChange = (value: string) => {};
  onTouched = () => {};

  writeValue(value: string): void {
    this.value = value ?? '';
  }

  registerOnChange(fn: (value: string) => void): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: () => void): void {
    this.onTouched = fn;
  }

  setDisabledState(disabled: boolean): void {
    this.disabled = disabled;
  }

  onInput(event: Event): void {
    const value = (event.target as HTMLInputElement).value;
    this.value = value;
    this.onChange(value);
  }
}
```

Usage:

```html
<app-custom-input formControlName="name" />
```

---

# MODULE 18 — State Management

State management controls how application data is stored, shared and updated.

## 18.1 Types of State

| State | Example |
|---|---|
| Local component state | Modal visibility |
| Shared UI state | Selected theme |
| Server state | Employee list from API |
| Authentication state | Logged-in user |
| Form state | Form values and validation |

## 18.2 Shared Service with BehaviorSubject

```typescript
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeStateService {

  private employeesSubject =
    new BehaviorSubject<Employee[]>([]);

  employees$ = this.employeesSubject.asObservable();

  setEmployees(employees: Employee[]): void {
    this.employeesSubject.next(employees);
  }

  getEmployeesSnapshot(): Employee[] {
    return this.employeesSubject.value;
  }
}
```

## 18.3 Shared State with Signals

```typescript
import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeStateService {

  private employeesSignal = signal<Employee[]>([]);

  employees = this.employeesSignal.asReadonly();

  setEmployees(employees: Employee[]): void {
    this.employeesSignal.set(employees);
  }

  addEmployee(employee: Employee): void {
    this.employeesSignal.update(
      employees => [...employees, employee]
    );
  }
}
```

## 18.4 NgRx

NgRx is a Redux-inspired state management library for Angular.

Important building blocks:

| Concept | Purpose |
|---|---|
| Store | Holds application state |
| Action | Describes an event |
| Reducer | Calculates new state |
| Selector | Reads derived state |
| Effect | Handles side effects |
| Entity | Manages collections of entities |

Typical flow:

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
Store Updated
   |
   v
Selector
   |
   v
Component
```

**When to use NgRx?**

Use it when an application has complex shared state, predictable event flows, significant side effects, and a need for debugging and state traceability. Do not introduce it automatically for every small application.

---

# MODULE 19 — Angular 17 Built-in Control Flow

Angular 17 introduced built-in control flow syntax.

## 19.1 @if

```html
@if (isLoggedIn) {
  <h2>Welcome User</h2>
} @else {
  <h2>Please Login</h2>
}
```

## 19.2 @for

```html
@for (employee of employees; track employee.id) {
  <div>
    {{ employee.name }}
  </div>
} @empty {
  <p>No employees found</p>
}
```

Important loop variables:

- `$index`
- `$first`
- `$last`
- `$even`
- `$odd`
- `$count`

Example:

```html
@for (employee of employees; track employee.id; let i = $index) {
  <p>{{ i + 1 }} - {{ employee.name }}</p>
}
```

## 19.3 @switch

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

## 19.4 @defer

`@defer` delays loading of non-critical UI until a specified trigger.

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

Other triggers include:
- `on idle`
- `on viewport`
- `on interaction`
- `on hover`
- `on immediate`
- `on timer(...)`

Example:

```html
@defer (on interaction) {
  <app-heavy-chart />
} @placeholder {
  <button>Load Chart</button>
}
```

For deferred dependencies to be loaded separately, the relevant dependencies generally need to be standalone and not eagerly referenced elsewhere in the same compilation context.

**Interview question:** Difference between `*ngIf` and `@if`?

**Answer:**
- `*ngIf` is a structural directive.
- `@if` is Angular's built-in control-flow syntax.
- `@if` does not require importing `CommonModule` just for conditional rendering.
- Built-in control flow provides clearer syntax and integrates with modern Angular template compilation.

---

# MODULE 20 — Lazy Loading and Code Splitting

Lazy loading loads feature code only when needed.

## 20.1 Lazy Load a Standalone Component

```typescript
import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'employees',
    loadComponent: () =>
      import('./employee/employee-list.component')
        .then(m => m.EmployeeListComponent)
  }
];
```

## 20.2 Lazy Load Child Routes

```typescript
export const routes: Routes = [
  {
    path: 'admin',
    loadChildren: () =>
      import('./admin/admin.routes')
        .then(m => m.ADMIN_ROUTES)
  }
];
```

**admin.routes.ts**

```typescript
import { Routes } from '@angular/router';

export const ADMIN_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./admin-dashboard.component')
        .then(m => m.AdminDashboardComponent)
  }
];
```

## 20.3 Eager vs Lazy Loading

| Eager Loading | Lazy Loading |
|---|---|
| Loads code as part of initial application loading | Loads feature code when requested |
| Can increase initial bundle | Can reduce initial bundle |
| Useful for small/common features | Useful for large/infrequently used features |

---

# MODULE 21 — Performance Optimization

This is an important area for 5+ years experienced developers.

## 21.1 Change Detection Optimization

- Use OnPush.
- Use immutable state updates.
- Avoid unnecessary component checks.
- Use signals where appropriate.
- Use AsyncPipe for observable-based templates.

## 21.2 trackBy / track

Without tracking, Angular may recreate DOM elements unnecessarily when lists change.

Older `*ngFor`:

```typescript
trackByEmployeeId(index: number, employee: Employee): number {
  return employee.id;
}
```

```html
<div *ngFor="let employee of employees; trackBy: trackByEmployeeId">
  {{ employee.name }}
</div>
```

Angular 17:

```html
@for (employee of employees; track employee.id) {
  <div>{{ employee.name }}</div>
}
```

## 21.3 Avoid Methods in Templates

Avoid:

```html
<p>{{ calculateSalary() }}</p>
```

Prefer a precomputed value, a computed signal, or an appropriate observable transformation.

## 21.4 Lazy Loading

Use lazy loading for large feature areas.

## 21.5 Deferred Loading

Use `@defer` for heavy non-critical components.

## 21.6 Virtual Scrolling

Useful when displaying thousands of records.

Angular CDK provides virtual scrolling.

Install/use Angular CDK:

```bash
npm install @angular/cdk@17
```

Example:

```typescript
import { Component } from '@angular/core';
import { ScrollingModule } from '@angular/cdk/scrolling';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [ScrollingModule],
  template: `
    <cdk-virtual-scroll-viewport itemSize="50" class="viewport">
      <div *cdkVirtualFor="let employee of employees">
        {{ employee.name }}
      </div>
    </cdk-virtual-scroll-viewport>
  `
})
export class EmployeeListComponent {
  employees = Array.from({ length: 10000 }, (_, i) => ({
    id: i + 1,
    name: `Employee ${i + 1}`
  }));
}
```

## 21.7 Bundle Optimization

- Remove unused dependencies.
- Use lazy loading.
- Use production builds.
- Avoid large libraries when lightweight alternatives exist.
- Inspect bundle size.
- Use tree-shakable imports.

## 21.8 Other Performance Techniques

- Pagination and server-side filtering
- Debouncing search input
- Caching API responses when appropriate
- Avoiding memory leaks
- Optimizing images
- Using Web Workers for CPU-heavy work
- Avoiding unnecessary subscriptions
- Profiling before optimizing

---

# MODULE 22 — Angular Security

## 22.1 Cross-Site Scripting (XSS)

XSS occurs when malicious scripts are injected into a web page and executed in another user's browser.

Angular sanitizes values in many template binding contexts.

Avoid:

```typescript
element.innerHTML = userInput;
```

Prefer Angular template bindings and safe rendering.

## 22.2 DomSanitizer

Angular provides `DomSanitizer`, but bypass methods must be used carefully.

```typescript
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { Component, inject } from '@angular/core';

@Component({
  selector: 'app-content',
  standalone: true,
  template: `<div [innerHTML]="safeContent"></div>`
})
export class ContentComponent {

  private sanitizer = inject(DomSanitizer);

  safeContent: SafeHtml =
    this.sanitizer.bypassSecurityTrustHtml('<b>Safe content</b>');
}
```

**Warning:** Never use `bypassSecurityTrustHtml` on untrusted user input. It disables Angular's sanitization for that value.

## 22.3 Important Security Concepts

| Concept | Explanation |
|---|---|
| XSS | Injecting malicious scripts |
| CSRF | Tricking authenticated browser into sending unwanted requests |
| CORS | Browser-enforced cross-origin access policy |
| CSP | Restricts permitted resource sources |
| JWT | Token format often used for authentication |
| HTTPS | Encrypts data in transit |
| Authentication | Verifies identity |
| Authorization | Determines access permissions |

## 22.4 Secure Angular Application

- Validate and authorize requests on the backend.
- Use HTTPS.
- Avoid storing highly sensitive tokens in browser-accessible storage.
- Understand cookie-based authentication and CSRF protection.
- Never hardcode secrets in frontend code.
- Validate user input on the backend.
- Avoid unsafe DOM manipulation.
- Keep dependencies updated.
- Apply a suitable Content Security Policy.

---

# MODULE 23 — Reusable Components and Application Architecture

## 23.1 Feature-Based Architecture

Organize application code by business feature rather than only by technical file type.

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

## 23.2 Smart vs Presentational Components

| Smart Component | Presentational Component |
|---|---|
| Handles business logic | Focuses on rendering |
| Communicates with services | Receives data through inputs |
| May manage state | Emits user events |
| Coordinates application behavior | Reusable UI |

Example:

```text
EmployeePageComponent
       |
       v
EmployeeService
       |
       v
EmployeeListComponent
       |
       v
EmployeeCardComponent
```

## 23.3 Reusable Table Component

A reusable table should support:

- Dynamic columns
- Sorting
- Pagination
- Row selection
- Loading state
- Empty state
- Custom cell templates

For enterprise applications, Angular Material/CDK can help with accessible, reusable UI building blocks.

---

# MODULE 24 — Angular Testing

Testing is important for maintaining reliable enterprise applications.

## 24.1 Types of Testing

| Type | Purpose |
|---|---|
| Unit testing | Tests an individual function or class |
| Component testing | Tests component behavior and template |
| Integration testing | Tests interactions between parts |
| End-to-end testing | Tests complete user workflows |

## 24.2 Component Unit Test

Angular 17 CLI projects commonly use Jasmine and Karma by default, depending on project configuration.

```typescript
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EmployeeComponent } from './employee.component';

describe('EmployeeComponent', () => {

  let component: EmployeeComponent;
  let fixture: ComponentFixture<EmployeeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(EmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create component', () => {
    expect(component).toBeTruthy();
  });
});
```

## 24.3 Service Testing

```typescript
import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController
} from '@angular/common/http/testing';

describe('EmployeeService', () => {

  let service: EmployeeService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [EmployeeService]
    });

    service = TestBed.inject(EmployeeService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should fetch employees', () => {

    const mockEmployees = [
      {
        id: 1,
        name: 'John',
        email: 'john@example.com',
        salary: 50000
      }
    ];

    service.getEmployees().subscribe(result => {
      expect(result).toEqual(mockEmployees);
    });

    const request = httpMock.expectOne(
      'https://api.example.com/employees'
    );

    expect(request.request.method).toBe('GET');
    request.flush(mockEmployees);
  });

  afterEach(() => httpMock.verify());
});
```

## 24.4 Important Testing Concepts

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
- Testing asynchronous Observables

**Note:** Angular 17 projects can also use Jest or other test runners when configured. The examples above use the conventional Angular testing APIs.

---

# MODULE 25 — SSR, Hydration and Angular Universal

## 25.1 What is SSR?

Server-Side Rendering generates HTML on the server before sending it to the browser.

Benefits:
- Faster initial content visibility in appropriate scenarios
- Improved search engine discoverability
- Better social link previews
- Better experience for some low-powered devices

## 25.2 CSR vs SSR

| CSR | SSR |
|---|---|
| Browser renders application | Server renders initial HTML |
| Requires JavaScript to render most app UI | Can deliver rendered HTML before client bootstrapping |
| Common SPA architecture | Useful for SEO and initial rendering |
| Server mostly provides data/API | Server also renders application |

## 25.3 Hydration

Hydration allows Angular to reuse server-rendered DOM and attach client-side behavior instead of rebuilding the entire DOM.

Angular 17 supports hydration configuration.

Example configuration:

```typescript
import { ApplicationConfig } from '@angular/core';
import { provideClientHydration } from '@angular/platform-browser';

export const appConfig: ApplicationConfig = {
  providers: [
    provideClientHydration()
  ]
};
```

For a complete SSR project, configure the Angular SSR setup using the Angular CLI.

```bash
ng add @angular/ssr@17
```

## 25.4 Important SSR Considerations

- Avoid directly accessing `window` and `document` during server rendering.
- Be careful with browser-only libraries.
- Avoid server/client HTML mismatches.
- Understand hydration.
- Handle platform-specific logic.
- Consider server resource consumption.

---

# MODULE 26 — Build, Deployment and Environment Configuration

## 26.1 Environment Files

Example:

```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};
```

Production environment:

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

Never put secrets in frontend environment files. Their values are included in the built application.

## 26.2 Production Build

```bash
ng build --configuration production
```

## 26.3 Deployment Options

- Nginx
- Apache
- AWS S3 and CloudFront
- Azure Static Web Apps
- Google Cloud Storage and CDN
- Container-based deployments
- Node.js server for SSR applications

## 26.4 CI/CD Pipeline

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

Important topics:
- npm and package-lock
- Build configurations
- Environment variables
- Artifact management
- Git branching
- Docker basics
- CI/CD
- Rollback strategy

---

# MODULE 27 — Advanced Angular Concepts

## 27.1 Standalone Components vs NgModules

| Standalone | NgModule |
|---|---|
| Component declares its dependencies through imports | NgModule groups declarations and imports |
| Less module boilerplate | Module-based organization |
| Recommended for new Angular development | Supported for existing applications |
| Uses `bootstrapApplication()` in standalone apps | Commonly uses `bootstrapModule()` |

## 27.2 Dynamic Component Loading

Angular supports dynamically creating components using `ViewContainerRef`.

```typescript
import {
  Component,
  ViewContainerRef,
  inject
} from '@angular/core';

@Component({
  selector: 'app-host',
  standalone: true,
  template: `<button (click)="load()">Load Component</button>`
})
export class HostComponent {

  private viewContainer = inject(ViewContainerRef);

  async load(): Promise<void> {
    const { EmployeeComponent } =
      await import('./employee.component');

    this.viewContainer.clear();
    this.viewContainer.createComponent(EmployeeComponent);
  }
}
```

## 27.3 Dynamic Component Rendering

Know:
- `ViewContainerRef`
- `createComponent`
- `ComponentRef`
- `setInput`
- Component destruction
- Dynamic input/output handling

## 27.4 Web Workers

Web Workers execute CPU-intensive tasks in a background thread.

Useful for:
- Large data processing
- Complex calculations
- Parsing
- Computationally expensive operations

They cannot directly manipulate the DOM.

## 27.5 Micro Frontends

Micro frontends split a large frontend application into independently developed and deployed parts.

Important concepts:
- Module Federation
- Shell application
- Remote application
- Shared dependencies
- Independent deployment
- Version compatibility
- Routing integration

## 27.6 Internationalization (i18n)

Internationalization supports multiple languages and locales.

Concepts:
- Translation extraction
- Locale configuration
- Currency formatting
- Date formatting
- Number formatting
- Translation workflows

## 27.7 Accessibility

Important topics:
- Semantic HTML
- Keyboard navigation
- ARIA attributes
- Focus management
- Screen reader compatibility
- Color contrast
- Angular CDK accessibility utilities

## 27.8 Angular Material and CDK

Angular Material provides UI components; CDK provides lower-level behavior and accessibility utilities.

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

---

# MODULE 28 — Real-World Scenarios and Coding Problems

These are particularly important for 5+ years experience.

## Scenario 1 — Implement Employee CRUD

Requirements:
- List employees
- Add employee
- Update employee
- Delete employee
- View employee details
- Search and filter employees

Expected concepts:
- Components
- Services
- HttpClient
- Routing
- Reactive forms
- RxJS
- Interceptors
- Error handling

## Scenario 2 — Implement Search with Debouncing

```typescript
this.searchControl.valueChanges.pipe(
  debounceTime(300),
  distinctUntilChanged(),
  switchMap(value => this.employeeService.searchEmployees(value))
).subscribe(result => {
  this.employees = result;
});
```

Explain why `switchMap` is suitable: previous search subscriptions are cancelled when newer input arrives.

## Scenario 3 — Prevent Duplicate Form Submission

```typescript
import { exhaustMap } from 'rxjs';

this.submitClicks.pipe(
  exhaustMap(() => this.employeeService.createEmployee(this.form.value))
).subscribe();
```

Also disable the submit button while a request is pending.

## Scenario 4 — Handle API Errors Globally

Implement an HTTP interceptor using `catchError` and display consistent error messages through a notification service.

## Scenario 5 — Prevent Memory Leaks

Use:
- AsyncPipe
- `takeUntilDestroyed`
- Explicit unsubscribe when necessary
- Proper cleanup of event listeners and timers

## Scenario 6 — Optimize a Table with 50,000 Records

Possible solution:
1. Server-side pagination.
2. Server-side sorting and filtering.
3. Virtual scrolling where appropriate.
4. OnPush change detection.
5. Track rows by stable ID.
6. Avoid rendering unnecessary columns.
7. Profile rendering and network requests.

## Scenario 7 — Share State Between Unrelated Components

Use:
- Shared service with BehaviorSubject
- Signal-based state service
- NgRx for complex global state

## Scenario 8 — Implement Role-Based Navigation

Use:
- Authentication service
- Route guards
- Role-aware navigation
- Backend authorization

Remember that hiding a button or protecting a route does not replace backend authorization.

## Scenario 9 — Build a Reusable Form Component

Use:
- ControlValueAccessor
- Custom validators
- Reactive forms
- Input configuration
- Error display
- Accessibility

## Scenario 10 — Fix an Angular Memory Leak

Investigate:
- Subscriptions that never complete
- Event listeners
- Timers
- Long-lived subjects
- Component destruction
- Detached views

---

# MODULE 29 — Angular 17 Interview Questions and Answers

## A. Fundamentals and Architecture

**1. What is Angular?**

Angular is a TypeScript-based frontend framework used to build scalable web applications with components, dependency injection, routing and reactive programming.

**2. What is the difference between AngularJS and Angular?**

AngularJS is the older JavaScript-based framework, while modern Angular is TypeScript-based and uses component-oriented architecture.

**3. What is a SPA?**

A single-page application loads its main shell once and updates views dynamically without full-page reloads.

**4. What are the main building blocks of Angular?**

Components, templates, directives, pipes, services, dependency injection and routing.

**5. What is a standalone component?**

A component that can declare its dependencies directly through its `imports` property without being declared in an NgModule.

**6. What are the major Angular 17 features?**

Built-in control flow, `@defer`, standalone components as the default for new components, and build/SSR improvements.

## B. Components and Communication

**7. What is the difference between @Input and @Output?**

`@Input` receives data from a parent; `@Output` emits events to a parent.

**8. How do components communicate?**

Using inputs, outputs, shared services, RxJS subjects, signals or router state.

**9. What is ViewChild?**

It provides access to a child component, directive or element in the component's view.

**10. What is Content Projection?**

It allows a component to render content provided by its parent using `ng-content`.

**11. What is the difference between ViewChild and ContentChild?**

ViewChild accesses the component's own view; ContentChild accesses content projected into it.

**12. What is a smart component?**

A component that handles application logic, state and service interaction.

**13. What is a presentational component?**

A component primarily responsible for displaying data and emitting UI events.

## C. Data Binding and Directives

**14. What are the types of data binding?**

Interpolation, property binding, event binding and two-way binding.

**15. What is the difference between interpolation and property binding?**

Interpolation displays text; property binding assigns a value to a DOM or component property.

**16. What is a structural directive?**

A directive that changes the DOM structure, such as `*ngIf` or `*ngFor`.

**17. What is an attribute directive?**

A directive that changes the appearance or behavior of an existing element.

**18. What is the difference between @if and *ngIf?**

`@if` is built-in control flow; `*ngIf` is a structural directive.

**19. What is the purpose of track in @for?**

It identifies items consistently and helps Angular efficiently reconcile list updates.

## D. Services and Dependency Injection

**20. What is Dependency Injection?**

A pattern where dependencies are supplied by an injector rather than manually constructed by the consumer.

**21. What does providedIn: root mean?**

The service is provided through the root environment injector and is generally shared application-wide.

**22. What happens if a service is provided in a component?**

A provider is created in that component's injector scope, potentially producing a separate instance for that component subtree.

**23. What is hierarchical DI?**

Angular resolves dependencies through a hierarchy of injectors.

**24. What is the difference between inject() and constructor injection?**

Both resolve dependencies through Angular DI. `inject()` can be used in valid injection contexts, while constructor injection declares dependencies as constructor parameters.

## E. Lifecycle and Change Detection

**25. What are Angular lifecycle hooks?**

Hooks that execute at different stages of component creation, checking and destruction.

**26. What is the difference between constructor and ngOnInit?**

The constructor initializes the class; ngOnInit is called by Angular after initial input properties are set.

**27. What is change detection?**

The process Angular uses to determine which views need updating.

**28. What is OnPush?**

A change detection strategy that allows Angular to skip checking a component subtree until a relevant notification occurs.

**29. What is ChangeDetectorRef?**

An API for explicitly interacting with change detection.

**30. What is Zone.js?**

A library Angular traditionally uses to track asynchronous operations and schedule change detection.

## F. RxJS

**31. What is an Observable?**

A representation of a stream of values over time.

**32. What is the difference between Observable and Promise?**

Observables can emit multiple values and support cancellation through unsubscribe; promises settle once and have no built-in cancellation.

**33. What is a Subject?**

An Observable and Observer that multicasts values to subscribers.

**34. What is a BehaviorSubject?**

A Subject that stores a current value and emits it immediately to new subscribers.

**35. What is the difference between Subject and BehaviorSubject?**

Subject does not require an initial value; BehaviorSubject requires one and provides the latest value to new subscribers.

**36. What is switchMap?**

It maps each source value to an inner Observable and unsubscribes from the previous inner Observable when a new value arrives.

**37. Difference between switchMap and mergeMap?**

switchMap keeps only the latest inner subscription; mergeMap allows concurrent inner subscriptions.

**38. Difference between concatMap and exhaustMap?**

concatMap queues inner Observables sequentially; exhaustMap ignores new source values while an inner Observable is active.

**39. What is debounceTime?**

It emits a value only after a specified period without another emission.

**40. What is forkJoin?**

It waits for all input Observables to complete and emits their last values together.

**41. What is combineLatest?**

It emits the latest values from each source whenever one source emits, after all sources have emitted at least once.

**42. What is AsyncPipe?**

It subscribes to an Observable or Promise, exposes the latest value and handles cleanup.

**43. How do you avoid memory leaks?**

Use AsyncPipe, `takeUntilDestroyed`, explicit unsubscription and proper cleanup.

## G. Signals

**44. What is a Signal?**

A reactive primitive that holds a value and notifies consumers when the value changes.

**45. What is the difference between signal and computed?**

`signal` stores writable state; `computed` derives a read-only value from other reactive state.

**46. What is effect?**

An API for executing side effects when signals read by the effect change.

**47. Signals vs RxJS?**

Signals are useful for synchronous reactive state; RxJS is suited to asynchronous streams and complex event processing.

## H. Routing and HTTP

**48. What is Angular Router?**

The Angular package used for navigation between views.

**49. What are route parameters?**

Dynamic values included in the URL path, such as `/employees/101`.

**50. What are query parameters?**

Optional URL parameters after `?`, such as `?page=2`.

**51. What is lazy loading?**

Loading feature code only when its route or feature is requested.

**52. What is a route guard?**

A function that controls navigation based on conditions.

**53. What is a resolver?**

A function that provides data before a route is activated.

**54. What is an HTTP interceptor?**

A mechanism for modifying outgoing HTTP requests and handling incoming responses.

**55. How do you attach JWT tokens to requests?**

Use an HTTP interceptor and add an Authorization header for trusted API destinations.

**56. How do you handle global API errors?**

Use interceptors with `catchError` and a centralized error/notification service.

## I. Forms

**57. What are the two types of Angular forms?**

Template-driven forms and reactive forms.

**58. Reactive vs template-driven forms?**

Reactive forms define controls and validation in TypeScript; template-driven forms rely more on template declarations.

**59. What is FormBuilder?**

A helper service that simplifies the creation of reactive forms.

**60. What is FormArray?**

A collection of form controls or groups that can grow or shrink dynamically.

**61. Difference between setValue and patchValue?**

setValue updates the full form structure; patchValue updates selected controls.

**62. What is a custom validator?**

A developer-defined function that returns validation errors or null.

**63. What is ControlValueAccessor?**

An interface that connects a custom UI control with Angular Forms.

## J. Performance and Security

**64. How do you optimize an Angular application?**

Use OnPush, lazy loading, `@defer`, efficient list tracking, virtual scrolling, pagination, caching and bundle optimization.

**65. What is virtual scrolling?**

Rendering only the visible portion of a large list instead of creating DOM elements for every item.

**66. What is tree shaking?**

Removing unused code during the build process.

**67. What is XSS?**

An attack where malicious scripts are injected into a page and executed in another user's browser.

**68. How does Angular help prevent XSS?**

Angular sanitizes values in many template binding contexts, but unsafe bypass methods and direct DOM manipulation can introduce vulnerabilities.

**69. Are Angular route guards enough for security?**

No. The backend must enforce authentication and authorization.

## K. Advanced and Architecture

**70. What is SSR?**

Server-side rendering generates application HTML on the server.

**71. What is hydration?**

Hydration attaches client-side Angular behavior to server-rendered HTML.

**72. What is micro frontend architecture?**

An architecture that divides a frontend application into independently developed and deployed parts.

**73. What is dynamic component loading?**

Creating and rendering components at runtime using APIs such as ViewContainerRef.

**74. What is ControlValueAccessor?**

A bridge between a custom component and Angular's forms API.

**75. What is NgRx?**

A Redux-inspired state management library for Angular.

**76. What is the difference between NgRx and a shared service?**

A shared service is often sufficient for simpler shared state; NgRx provides structured actions, reducers, selectors and effects for complex application state.

**77. What is the difference between eager and lazy loading?**

Eager loading includes code in the initial loading path; lazy loading loads code when required.

**78. What is the purpose of @defer?**

It delays loading of non-critical template dependencies until a configured trigger occurs.

---

# MODULE 30 — 5+ Years Experience Preparation Checklist

## Core Angular

- [ ] Angular architecture
- [ ] Standalone components
- [ ] Component lifecycle
- [ ] Component communication
- [ ] Data binding
- [ ] Directives
- [ ] Pipes
- [ ] Services
- [ ] Dependency Injection
- [ ] Routing
- [ ] Route guards
- [ ] Resolvers

## Advanced Angular

- [ ] Change detection
- [ ] OnPush strategy
- [ ] Zone.js
- [ ] Signals
- [ ] RxJS
- [ ] Subjects and BehaviorSubjects
- [ ] RxJS operators
- [ ] HTTP interceptors
- [ ] Error handling
- [ ] Lazy loading
- [ ] Deferred loading
- [ ] Dynamic component loading

## Forms

- [ ] Template-driven forms
- [ ] Reactive forms
- [ ] FormBuilder
- [ ] FormArray
- [ ] Custom validators
- [ ] Cross-field validation
- [ ] ControlValueAccessor

## Performance and Architecture

- [ ] Performance optimization
- [ ] Virtual scrolling
- [ ] State management
- [ ] NgRx fundamentals
- [ ] Reusable components
- [ ] Feature-based architecture
- [ ] SSR and hydration
- [ ] Micro frontends
- [ ] Security
- [ ] Accessibility

## Testing and Deployment

- [ ] Component testing
- [ ] Service testing
- [ ] HTTP testing
- [ ] TestBed
- [ ] Mocking and spies
- [ ] Production build
- [ ] Environment configuration
- [ ] CI/CD
- [ ] Deployment

## Practical Projects to Build

1. **Employee Management Portal:** CRUD, routing, reactive forms and HTTP.
2. **Authentication Module:** Login, logout, token handling, guards and role-based navigation.
3. **Employee Dashboard:** Charts, filtering, pagination and reusable tables.
4. **Search Application:** Debouncing, switchMap, API integration and error handling.
5. **Large Data Table:** Server-side pagination, OnPush and virtual scrolling.
6. **Reusable UI Library:** Custom form controls, CVA, directives and pipes.

---

# Recommended Learning Order

| Priority | Modules | Focus |
|---|---|---|
| P1 — Must Know | 1–9 | Angular fundamentals, components, directives, DI and lifecycle |
| P1 — Must Know | 10–16 | Change detection, RxJS, routing, HTTP and forms |
| P1 — Must Know | 19–21 | Angular 17 control flow, lazy loading and performance |
| P2 — Advanced | 11, 17–18 | Signals, custom controls and state management |
| P2 — Advanced | 22–27 | Security, architecture, testing, SSR and advanced concepts |
| P3 — Practical | 28–30 | Real-world scenarios, interviews and project implementation |

**Preparation target:** For a 5+ years developer, prioritize understanding trade-offs and implementation decisions over memorizing definitions. Be ready to explain how you designed reusable components, managed API state, prevented memory leaks, improved rendering performance, secured routes and integrated Angular with Spring Boot REST APIs.

