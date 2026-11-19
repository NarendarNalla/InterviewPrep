
# Java Exception Handling – Interview Preparation

## 1. Exception Handling Fundamentals

### Q1. What is an Exception in Java?

An exception is an unexpected event that occurs during program execution and interrupts the normal flow of the application.

Examples:
- Dividing a number by zero.
- Accessing an invalid array index.
- Accessing a method on a null reference.
- Reading a file that does not exist.
- Database connection failures.

Example:

    public class Main {
        public static void main(String[] args) {
            int result = 10 / 0;
            System.out.println(result);
        }
    }

Output:
    Exception in thread "main" java.lang.ArithmeticException: / by zero

### Q2. What is Exception Handling?

Exception handling is a mechanism used to handle runtime errors and maintain the normal flow of application execution.

Java provides:
- try
- catch
- finally
- throw
- throws

Example:

    public class Main {
        public static void main(String[] args) {
            try {
                int result = 10 / 0;
            } catch (ArithmeticException e) {
                System.out.println("Cannot divide by zero");
            }
        }
    }

### Q3. What is the difference between Error and Exception?

| Error | Exception |
|---|---|
| Represents serious JVM or system problems. | Represents conditions an application may handle. |
| Usually not recoverable. | Often recoverable. |
| Belongs to java.lang.Error. | Belongs to java.lang.Exception or RuntimeException. |
| Generally not caught by applications. | Can be caught and handled. |
| Example: OutOfMemoryError. | Example: IOException. |

Examples:

- Error: StackOverflowError, OutOfMemoryError.
- Exception: IOException, SQLException, NullPointerException.

### Q4. Explain the Java Exception Hierarchy.

All throwable objects inherit from Throwable.

    Object
       |
    Throwable
       |
       |-------------------|
       |                   |
      Error             Exception
                           |
                   RuntimeException

Important categories:

1. Error
2. Checked Exception
3. Unchecked Exception

Examples:

Error:
- OutOfMemoryError
- StackOverflowError
- NoClassDefFoundError

Checked Exception:
- IOException
- SQLException
- ClassNotFoundException
- InterruptedException

Unchecked Exception:
- NullPointerException
- ArithmeticException
- ArrayIndexOutOfBoundsException
- IllegalArgumentException
- NumberFormatException

### Q5. What are the different types of exceptions in Java?

Java exceptions are broadly categorized into:

1. Checked Exceptions
2. Unchecked Exceptions

Checked exceptions are checked by the compiler.

Unchecked exceptions occur at runtime and are subclasses of RuntimeException.

---

## 2. Checked vs Unchecked Exceptions

### Q6. What is a Checked Exception?

A checked exception is an exception checked by the compiler at compile time.

The developer must either:
- Handle it using try-catch.
- Declare it using throws.

Example:

    import java.io.*;

    public class Main {
        public static void main(String[] args) {
            try {
                FileReader reader = new FileReader("data.txt");
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }
    }

Common checked exceptions:

- IOException
- SQLException
- ClassNotFoundException
- InterruptedException
- ParseException

### Q7. What is an Unchecked Exception?

Unchecked exceptions are exceptions that the compiler does not force developers to handle.

They occur at runtime and extend RuntimeException.

Example:

    public class Main {
        public static void main(String[] args) {
            String name = null;
            System.out.println(name.length());
        }
    }

Output:
    NullPointerException

Common unchecked exceptions:

- NullPointerException
- ArithmeticException
- IllegalArgumentException
- IllegalStateException
- NumberFormatException
- IndexOutOfBoundsException

### Q8. Difference between Checked and Unchecked Exceptions.

| Checked Exception | Unchecked Exception |
|---|---|
| Checked at compile time. | Occurs at runtime. |
| Must be handled or declared. | Handling is not mandatory. |
| Extends Exception, excluding RuntimeException subclasses. | Extends RuntimeException. |
| Usually represents recoverable external conditions. | Often represents programming errors or invalid state. |
| Example: IOException. | Example: NullPointerException. |

### Q9. Why does Java have Checked and Unchecked Exceptions?

Checked exceptions encourage developers to handle expected external failures.

Examples:
- File not found.
- Database connectivity problems.
- Network communication failures.

Unchecked exceptions generally indicate programming mistakes or invalid application states.

Examples:
- NullPointerException.
- Invalid arguments.
- Illegal state.

### Q10. Is NullPointerException Checked or Unchecked?

NullPointerException is an unchecked exception because it extends RuntimeException.

Example:

    String value = null;
    value.length(); // NullPointerException

---

## 3. try, catch, finally

### Q11. Explain try-catch blocks.

The try block contains code that might throw an exception.

The catch block handles the exception thrown from the try block.

Example:

    try {
        int result = 10 / 0;
    } catch (ArithmeticException e) {
        System.out.println(e.getMessage());
    }

### Q12. Can we have multiple catch blocks?

Yes. Java allows multiple catch blocks to handle different exception types.

Example:

    try {
        int[] numbers = {10, 20};
        System.out.println(numbers[5]);
    } catch (ArithmeticException e) {
        System.out.println("Arithmetic error");
    } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Invalid array index");
    } catch (Exception e) {
        System.out.println("General exception");
    }

Important:
- More specific exceptions must appear before general exceptions.
- Exception should be the last catch block when using separate catch blocks.

### Q13. What is a Multi-catch block?

Java 7 introduced multi-catch to handle multiple exception types in a single catch block.

Example:

    try {
        int result = 10 / 0;
    } catch (ArithmeticException | NullPointerException e) {
        System.out.println("Exception occurred: " + e);
    }

Advantages:
- Reduces duplicate code.
- Improves readability.

Note:
Related exception types cannot be used together if one is a subclass of the other.

### Q14. What is the finally block?

The finally block contains code that generally executes whether an exception occurs or not.

It is commonly used for cleanup operations.

Example:

    try {
        int result = 10 / 0;
    } catch (ArithmeticException e) {
        System.out.println("Exception handled");
    } finally {
        System.out.println("Finally executed");
    }

Output:
    Exception handled
    Finally executed

### Q15. Is finally always executed?

No. Finally generally executes, but there are exceptional situations where it may not execute.

Examples:
- System.exit() terminates the JVM.
- JVM crashes.
- Process is forcibly terminated.
- Machine shuts down unexpectedly.

### Q16. What happens if an exception occurs inside finally?

If finally throws a new exception, it can replace the exception originally thrown from the try block.

Example:

    public class Main {
        public static void main(String[] args) {
            try {
                throw new RuntimeException("Try exception");
            } finally {
                throw new RuntimeException("Finally exception");
            }
        }
    }

The finally exception becomes the propagated exception, and the original exception can be lost.

Best practice:
Avoid throwing exceptions from finally. Use try-with-resources where possible.

### Q17. Can we write try without catch?

Yes. A try block can be followed by finally without catch.

Example:

    try {
        System.out.println("Inside try");
    } finally {
        System.out.println("Cleanup");
    }

### Q18. Can we write try without catch and finally?

Yes, using try-with-resources.

Example:

    try (FileReader reader = new FileReader("data.txt")) {
        System.out.println(reader.read());
    }

### Q19. Can finally execute without an exception?

Yes.

Example:

    try {
        System.out.println("Try block");
    } finally {
        System.out.println("Finally block");
    }

Both statements execute.

### Q20. What happens when return is used inside try and finally?

The finally block executes before the method returns.

Example:

    public static int test() {
        try {
            return 10;
        } finally {
            System.out.println("Finally executed");
        }
    }

Output:
    Finally executed

Return value: 10

Important interview scenario:

    public static int test() {
        try {
            return 10;
        } finally {
            return 20;
        }
    }

Return value: 20

The return inside finally overrides the return from try.

Best practice:
Never use return inside finally.

---

## 4. throw vs throws

### Q21. What is the throw keyword?

The throw keyword is used to explicitly throw an exception from a method or block.

Example:

    public void validateAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException(
                "Age must be 18 or above"
            );
        }
    }

### Q22. What is the throws keyword?

The throws keyword declares exceptions that a method may propagate to its caller.

Example:

    public void readFile() throws IOException {
        FileReader reader = new FileReader("data.txt");
    }

The caller must handle the checked exception or declare it further.

### Q23. Difference between throw and throws.

| throw | throws |
|---|---|
| Used to explicitly throw an exception. | Declares possible exceptions. |
| Used inside method or block. | Used in method signature. |
| Followed by an exception object. | Followed by exception class names. |
| Throws one exception object at a time. | Can declare multiple exception types. |
| Transfers control to exception handling. | Does not itself throw an exception. |

### Q24. Can we use throw and throws together?

Yes.

Example:

    public void validate(int age) throws Exception {
        if (age < 18) {
            throw new Exception("Invalid age");
        }
    }

### Q25. Can we throw a checked exception without using throws?

Yes, if the exception is handled inside the method using try-catch.

Example:

    public void test() {
        try {
            throw new IOException("File error");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

---

## 5. Custom Exceptions

### Q26. What is a Custom Exception?

A custom exception is a user-defined exception created to represent application-specific error conditions.

It helps make error handling meaningful and domain-specific.

Example:

    public class InsufficientBalanceException
            extends RuntimeException {

        public InsufficientBalanceException(String message) {
            super(message);
        }
    }

Usage:

    public class BankService {

        public void withdraw(double balance, double amount) {

            if (amount > balance) {
                throw new InsufficientBalanceException(
                    "Insufficient account balance"
                );
            }

            System.out.println("Withdrawal successful");
        }
    }

### Q27. How do you create a Checked Custom Exception?

Extend Exception.

Example:

    public class InvalidUserException extends Exception {

        public InvalidUserException(String message) {
            super(message);
        }
    }

Usage:

    public void validateUser(boolean valid)
            throws InvalidUserException {

        if (!valid) {
            throw new InvalidUserException("Invalid user");
        }
    }

### Q28. How do you create an Unchecked Custom Exception?

Extend RuntimeException.

Example:

    public class InvalidOrderException extends RuntimeException {

        public InvalidOrderException(String message) {
            super(message);
        }
    }

### Q29. When should you create Checked vs Unchecked Custom Exceptions?

Use checked exceptions when the caller is expected to take a meaningful recovery action and the API contract benefits from requiring handling.

Use unchecked exceptions for programming errors, invalid application state, and domain violations that generally propagate to a centralized exception handler.

In modern Spring Boot applications, unchecked custom exceptions are commonly used for business and API errors.

### Q30. What are the best practices for Custom Exceptions?

- Use meaningful exception names.
- Provide useful error messages.
- Preserve the original cause.
- Avoid creating unnecessary exception classes.
- Extend RuntimeException for most application-level business exceptions when appropriate.
- Do not expose sensitive information.
- Keep exception handling centralized where possible.

---

## 6. Exception Propagation and Method Overriding

### Q31. What is Exception Propagation?

Exception propagation is the process of passing an exception from the method where it occurs to its calling method until it is handled.

Example:

    public class Main {

        static void method1() {
            int result = 10 / 0;
        }

        static void method2() {
            method1();
        }

        public static void main(String[] args) {
            try {
                method2();
            } catch (ArithmeticException e) {
                System.out.println("Exception handled");
            }
        }
    }

Flow:

    main() -> method2() -> method1()

Exception occurs in method1 and propagates to main.

### Q32. Can we override a method and declare a broader checked exception?

No.

An overriding method cannot declare broader checked exceptions than the overridden method.

Example:

    class Parent {
        void display() throws IOException {}
    }

    class Child extends Parent {
        // Invalid: FileNotFoundException is allowed,
        // but a broader Exception is not.
        // void display() throws Exception {}
    }

### Q33. Can an overriding method throw a narrower checked exception?

Yes.

Example:

    class Parent {
        void display() throws IOException {}
    }

    class Child extends Parent {
        @Override
        void display() throws FileNotFoundException {}
    }

FileNotFoundException is a subclass of IOException.

### Q34. Can an overriding method throw an unchecked exception?

Yes. An overriding method can throw unchecked exceptions regardless of whether the parent method declares them.

### Q35. What happens if the parent method does not declare any exception?

The overriding method cannot introduce a new checked exception.

However, it can throw unchecked exceptions.

---

## 7. Advanced Exception Handling

### Q36. What is try-with-resources?

Try-with-resources was introduced in Java 7.

It automatically closes resources that implement AutoCloseable or Closeable.

Example:

    try (BufferedReader reader =
            new BufferedReader(new FileReader("data.txt"))) {

        System.out.println(reader.readLine());

    } catch (IOException e) {
        System.out.println(e.getMessage());
    }

Advantages:
- Automatic resource management.
- Prevents resource leaks.
- Cleaner code.
- Handles suppressed exceptions correctly.

### Q37. What is AutoCloseable?

AutoCloseable is an interface in java.lang that provides the close() method.

Resources implementing AutoCloseable can be used in try-with-resources.

Example:

    public class MyResource implements AutoCloseable {

        @Override
        public void close() {
            System.out.println("Resource closed");
        }
    }

    try (MyResource resource = new MyResource()) {
        System.out.println("Using resource");
    }

### Q38. What are Suppressed Exceptions?

A suppressed exception occurs when an exception is thrown while another exception is already being propagated.

In try-with-resources:
- The exception from the try block is generally the primary exception.
- An exception thrown during resource closing is added as a suppressed exception.

Example:

    class Resource implements AutoCloseable {

        public void close() {
            throw new RuntimeException("Close failed");
        }
    }

    public class Main {
        public static void main(String[] args) {

            try (Resource resource = new Resource()) {
                throw new RuntimeException("Main failed");

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());

                for (Throwable t : e.getSuppressed()) {
                    System.out.println(t.getMessage());
                }
            }
        }
    }

Output:
    Main failed
    Close failed

### Q39. What is the difference between getMessage(), toString(), and printStackTrace()?

| Method | Description |
|---|---|
| getMessage() | Returns exception message. |
| toString() | Returns exception class name and message. |
| printStackTrace() | Prints exception details and stack trace. |

Example:

    try {
        int result = 10 / 0;
    } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println(e.toString());
        e.printStackTrace();
    }

### Q40. What is the difference between cause and suppressed exceptions?

| Cause | Suppressed Exception |
|---|---|
| Represents the original underlying failure. | Represents an additional failure. |
| Usually created using initCause() or a constructor. | Often generated by try-with-resources. |
| Retrieved using getCause(). | Retrieved using getSuppressed(). |

### Q41. What is exception chaining?

Exception chaining is the process of wrapping one exception inside another while preserving the original cause.

Example:

    try {
        // Database operation
    } catch (SQLException e) {
        throw new RuntimeException(
            "Unable to fetch customer",
            e
        );
    }

The original SQLException is preserved as the cause.

### Q42. Can we catch Throwable?

Yes, but generally it is not recommended.

Throwable includes both Error and Exception.

Catching Throwable can accidentally catch serious JVM errors that an application should not attempt to recover from.

Prefer catching specific exceptions.

### Q43. What is StackOverflowError?

StackOverflowError occurs when the thread stack exceeds its permitted size, commonly due to infinite or excessively deep recursion.

Example:

    public class Main {
        static void test() {
            test();
        }

        public static void main(String[] args) {
            test();
        }
    }

### Q44. What is OutOfMemoryError?

OutOfMemoryError occurs when the JVM cannot allocate memory required for an object.

Common reasons:
- Memory leaks.
- Large object allocation.
- Insufficient heap memory.
- Excessive caching.
- Unbounded collections.

### Q45. Can we handle OutOfMemoryError?

Technically yes, because it extends Error.

However, recovery is not guaranteed. The application may not have enough memory to continue safely.

Best practice:
Identify the memory issue using heap dumps, profiling, and JVM monitoring.

---

## 8. Spring Boot Exception Handling

### Q46. How do you handle exceptions globally in Spring Boot?

Spring Boot provides @ControllerAdvice and @ExceptionHandler.

@ControllerAdvice allows centralized exception handling across controllers.

Example:

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<String> handleRuntimeException(
                RuntimeException ex) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        }
    }

### Q47. What is the difference between @ControllerAdvice and @RestControllerAdvice?

| @ControllerAdvice | @RestControllerAdvice |
|---|---|
| Provides global controller advice. | Combines @ControllerAdvice and @ResponseBody. |
| Response body behavior must be configured when needed. | Returns response data directly in the response body. |
| Useful for MVC applications. | Commonly used in REST APIs. |

### Q48. What is @ExceptionHandler?

@ExceptionHandler is used to handle specific exceptions in controller classes or controller advice classes.

Example:

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(
            ResourceNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

### Q49. What is ResponseEntity?

ResponseEntity represents the complete HTTP response.

It allows us to control:
- HTTP status code.
- Response headers.
- Response body.

Example:

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body("User not found");

### Q50. How do you create a standardized error response?

Create a common error response DTO.

Example:

    public class ApiError {

        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;

        // Constructors, getters and setters
    }

Global handler:

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiError> handleNotFound(
                ResourceNotFoundException ex,
                HttpServletRequest request) {

            ApiError error = new ApiError();

            error.setTimestamp(LocalDateTime.now());
            error.setStatus(404);
            error.setError("NOT_FOUND");
            error.setMessage(ex.getMessage());
            error.setPath(request.getRequestURI());

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
    }

### Q51. What is the difference between @ExceptionHandler and @ControllerAdvice?

| @ExceptionHandler | @ControllerAdvice |
|---|---|
| Handles exceptions. | Provides centralized controller advice. |
| Can be defined inside controllers. | Applies across multiple controllers. |
| Can be combined with ControllerAdvice. | Can contain multiple exception handlers. |

### Q52. How do you handle validation exceptions in Spring Boot?

Spring Boot supports Bean Validation using annotations such as:

- @NotNull
- @NotBlank
- @Size
- @Email
- @Min
- @Max

Example:

    public class UserRequest {

        @NotBlank(message = "Name is required")
        private String name;

        @Email(message = "Invalid email")
        private String email;
    }

Controller:

    @PostMapping("/users")
    public ResponseEntity<String> createUser(
            @Valid @RequestBody UserRequest request) {

        return ResponseEntity.ok("User created");
    }

For request-body validation, handle MethodArgumentNotValidException globally.

### Q53. Difference between MethodArgumentNotValidException and ConstraintViolationException?

| MethodArgumentNotValidException | ConstraintViolationException |
|---|---|
| Commonly occurs during @Valid request-body validation. | Occurs when method or parameter constraints are violated. |
| Contains BindingResult. | Contains constraint violations. |
| Used for DTO validation errors. | Used for method-level and other constraint validation. |

### Q54. How do you handle exceptions in Spring Boot REST APIs?

Recommended approach:

1. Create custom business exceptions.
2. Create a standardized error response DTO.
3. Implement @RestControllerAdvice.
4. Use @ExceptionHandler for specific exception types.
5. Return appropriate HTTP status codes.
6. Log unexpected errors.
7. Avoid exposing internal stack traces or sensitive data.

### Q55. What is ProblemDetail in Spring Boot?

ProblemDetail is a Spring-supported representation of HTTP API errors based on RFC 9457 (which supersedes RFC 7807).

Available in Spring Framework 6+.

Example:

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleNotFound(
            ResourceNotFoundException ex) {

        ProblemDetail problem =
            ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        problem.setTitle("Resource Not Found");
        problem.setDetail(ex.getMessage());

        return problem;
    }

It provides a standardized structure for API error responses.

---

## 9. Real-Time Scenario-Based Interview Questions

### Q56. Your Spring Boot API returns HTTP 500. How will you debug it?

Answer:

1. Check application logs.
2. Identify the exception type and stack trace.
3. Locate the failing class and line number.
4. Check database, external service, and dependency failures.
5. Reproduce the issue locally or in a lower environment.
6. Fix the root cause.
7. Add appropriate exception handling and logging.
8. Verify with unit and integration tests.

### Q57. A database operation fails. How do you handle it?

Answer:

- Catch exceptions at the appropriate application boundary.
- Preserve the original cause.
- Use meaningful custom exceptions if necessary.
- Ensure transactions roll back when appropriate.
- Log technical details without exposing sensitive information.
- Return an appropriate API response.

Example:

    try {
        repository.save(customer);
    } catch (DataAccessException e) {
        throw new CustomerPersistenceException(
            "Unable to save customer", e
        );
    }

### Q58. How do you handle exceptions in a microservices architecture?

Answer:

- Use standardized error response structures.
- Return meaningful HTTP status codes.
- Implement appropriate timeouts.
- Use retries for transient failures.
- Apply circuit breakers where appropriate.
- Propagate correlation IDs.
- Avoid exposing internal service details.
- Use centralized logging and monitoring.

### Q59. Should we retry every exception?

No.

Retries should be used only for transient failures.

Examples:
- Temporary network failure.
- HTTP 503.
- Temporary service unavailability.

Do not retry:
- Invalid request.
- Authentication failure.
- Business validation failure.
- Non-recoverable errors.

Use exponential backoff and jitter to avoid overwhelming downstream services.

### Q60. How do you handle exceptions in CompletableFuture?

Use exceptionally(), handle(), or whenComplete().

Example:

    CompletableFuture<Integer> future =
        CompletableFuture.supplyAsync(() -> 10 / 0);

    future.exceptionally(ex -> {
        System.out.println("Error: " + ex.getMessage());
        return 0;
    });

Difference:

- exceptionally(): Handles failure and provides a fallback result.
- handle(): Processes success or failure and returns a result.
- whenComplete(): Observes success or failure without transforming the result.

### Q61. How do you handle exceptions in multithreaded applications?

Answer:

- Use try-catch inside the task where appropriate.
- Use Future.get() to observe exceptions from submitted tasks.
- Use CompletableFuture exception-handling methods.
- Configure an UncaughtExceptionHandler for uncaught thread exceptions.
- Use appropriate logging and monitoring.

### Q62. What happens if an exception occurs in a child thread?

An exception in a child thread does not automatically propagate to the parent thread.

If using ExecutorService, exceptions from submitted tasks can be observed through Future.get().

Example:

    ExecutorService executor =
        Executors.newSingleThreadExecutor();

    Future<?> future = executor.submit(() -> {
        throw new RuntimeException("Task failed");
    });

    try {
        future.get();
    } catch (ExecutionException e) {
        System.out.println(e.getCause());
    } finally {
        executor.shutdown();
    }

### Q63. How do you prevent sensitive information from appearing in error responses?

- Do not return stack traces to clients.
- Avoid exposing database queries.
- Avoid exposing passwords, tokens, or internal URLs.
- Return generic messages for unexpected errors.
- Log detailed technical information securely on the server.

---

## 10. Output-Based and Tricky Interview Questions

### Q64. What is the output?

    public class Main {
        public static void main(String[] args) {
            try {
                System.out.println(10 / 0);
            } catch (Exception e) {
                System.out.println("Exception");
            } catch (ArithmeticException e) {
                System.out.println("Arithmetic");
            }
        }
    }

Answer:

Compilation error.

Reason:
ArithmeticException is a subclass of Exception. The more specific catch block cannot appear after the general catch block.

### Q65. What is the output?

    try {
        System.out.println("A");
        return;
    } finally {
        System.out.println("B");
    }

Answer:

    A
    B

Reason:
Finally executes before the method returns.

### Q66. What is the output?

    try {
        throw new RuntimeException("Try");
    } catch (Exception e) {
        System.out.println("Catch");
    } finally {
        System.out.println("Finally");
    }

Answer:

    Catch
    Finally

### Q67. What is the output?

    public static int test() {
        try {
            return 10;
        } finally {
            System.out.println("Finally");
        }
    }

Answer:

    Finally

Return value: 10.

### Q68. What is the output?

    public static int test() {
        int value = 10;

        try {
            return value;
        } finally {
            value = 20;
        }
    }

Answer:

    10

Reason:
The primitive return value is evaluated before finally executes.

### Q69. What happens here?

    try {
        throw new NullPointerException();
    } catch (RuntimeException e) {
        System.out.println("Runtime");
    } catch (Exception e) {
        System.out.println("Exception");
    }

Answer:

    Runtime

Reason:
The first matching catch block executes.

### Q70. Can we have multiple finally blocks?

No. A single try statement can have only one finally block.

---

## 11. Coding Interview Questions

### Q71. Write a custom exception for insufficient account balance.

    class InsufficientBalanceException
            extends RuntimeException {

        public InsufficientBalanceException(String message) {
            super(message);
        }
    }

    class BankAccount {

        private double balance = 5000;

        public void withdraw(double amount) {

            if (amount <= 0) {
                throw new IllegalArgumentException(
                    "Amount must be positive"
                );
            }

            if (amount > balance) {
                throw new InsufficientBalanceException(
                    "Insufficient balance"
                );
            }

            balance -= amount;
        }
    }

### Q72. Write a method that validates an email and throws a custom exception.

    class InvalidEmailException extends RuntimeException {

        public InvalidEmailException(String message) {
            super(message);
        }
    }

    public void validateEmail(String email) {

        if (email == null ||
            !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            throw new InvalidEmailException(
                "Invalid email address"
            );
        }
    }

### Q73. Write a program to handle multiple exceptions.

    public class Main {

        public static void main(String[] args) {

            try {
                int[] numbers = {10, 20};

                int result = numbers[5] / 0;

                System.out.println(result);

            } catch (ArithmeticException |
                     ArrayIndexOutOfBoundsException e) {

                System.out.println(e.getMessage());

            } finally {
                System.out.println("Execution completed");
            }
        }
    }

Note:
The array index exception occurs first, so ArithmeticException is not thrown.

### Q74. Write a program using try-with-resources.

    import java.io.*;

    public class Main {

        public static void main(String[] args) {

            try (BufferedReader reader =
                    new BufferedReader(
                        new FileReader("data.txt"))) {

                System.out.println(reader.readLine());

            } catch (IOException e) {
                System.out.println(
                    "Unable to read file: " + e.getMessage()
                );
            }
        }
    }

### Q75. Implement a global exception handler in Spring Boot.

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<Map<String, Object>>
                handleNotFound(ResourceNotFoundException ex) {

            Map<String, Object> response = new HashMap<>();

            response.put("status", 404);
            response.put("message", ex.getMessage());
            response.put("timestamp", LocalDateTime.now());

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<Map<String, Object>>
                handleGeneric(Exception ex) {

            Map<String, Object> response = new HashMap<>();

            response.put("status", 500);
            response.put("message", "Internal server error");
            response.put("timestamp", LocalDateTime.now());

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

---

## 12. Exception Handling Best Practices

1. Catch specific exceptions instead of generic Exception.
2. Never swallow exceptions silently.
3. Avoid empty catch blocks.
4. Do not use exceptions for normal business flow.
5. Preserve original exceptions using exception chaining.
6. Use custom exceptions for meaningful domain failures.
7. Use try-with-resources for resource management.
8. Do not return sensitive exception details to API clients.
9. Avoid throwing exceptions from finally blocks.
10. Log exceptions at the appropriate layer.
11. Avoid logging the same exception repeatedly at every layer.
12. Use meaningful error messages.
13. Implement centralized exception handling in Spring Boot.
14. Retry only transient failures.
15. Use appropriate HTTP status codes.

---

## 13. Important HTTP Status Codes for Exception Handling

| Status Code | Meaning | Example |
|---|---|---|
| 400 | Bad Request | Invalid request payload |
| 401 | Unauthorized | Missing or invalid authentication |
| 403 | Forbidden | Insufficient permissions |
| 404 | Not Found | Customer does not exist |
| 405 | Method Not Allowed | Unsupported HTTP method |
| 409 | Conflict | Duplicate customer |
| 422 | Unprocessable Content | Business validation failure |
| 429 | Too Many Requests | Rate limit exceeded |
| 500 | Internal Server Error | Unexpected server failure |
| 502 | Bad Gateway | Invalid downstream response |
| 503 | Service Unavailable | Service temporarily unavailable |
| 504 | Gateway Timeout | Downstream service timeout |

---

## 14. Most Important Questions for 5+ Years Experience

Prioritize these questions during interview preparation:

1. Explain the Java exception hierarchy.
2. Checked vs unchecked exceptions.
3. Difference between Error and Exception.
4. throw vs throws.
5. How does exception propagation work?
6. Can we have try without catch?
7. Is finally always executed?
8. What happens if finally throws an exception?
9. Return inside try and finally scenarios.
10. Custom checked vs unchecked exceptions.
11. Exception handling during method overriding.
12. What is try-with-resources?
13. What are suppressed exceptions?
14. What is exception chaining?
15. How do you handle exceptions globally in Spring Boot?
16. @ControllerAdvice vs @RestControllerAdvice.
17. @ExceptionHandler vs @ResponseStatus.
18. MethodArgumentNotValidException vs ConstraintViolationException.
19. How do you standardize API error responses?
20. How do you handle database exceptions?
21. How do you handle exceptions in CompletableFuture?
22. How do you handle exceptions in ExecutorService?
23. How do you handle transient failures in microservices?
24. How do you implement retry and circuit breaker?
25. How do you debug production HTTP 500 errors?

---

## 15. Interview Preparation Checklist

### Core Java
- [ ] Exception hierarchy
- [ ] Checked and unchecked exceptions
- [ ] Error vs Exception
- [ ] try-catch-finally
- [ ] Multiple catch blocks
- [ ] throw vs throws
- [ ] Custom exceptions
- [ ] Exception propagation
- [ ] Method overriding rules
- [ ] try-with-resources
- [ ] Suppressed exceptions
- [ ] Exception chaining

### Advanced Java
- [ ] Exception handling in multithreading
- [ ] Future and ExecutionException
- [ ] CompletableFuture
- [ ] UncaughtExceptionHandler
- [ ] JVM errors
- [ ] Resource management

### Spring Boot
- [ ] @ExceptionHandler
- [ ] @ControllerAdvice
- [ ] @RestControllerAdvice
- [ ] ResponseEntity
- [ ] Custom business exceptions
- [ ] Global exception handling
- [ ] Bean Validation
- [ ] MethodArgumentNotValidException
- [ ] ConstraintViolationException
- [ ] ProblemDetail
- [ ] Standardized API error response

### Real-Time Scenarios
- [ ] Database failure
- [ ] External API failure
- [ ] HTTP 500 debugging
- [ ] Retry mechanisms
- [ ] Circuit breaker
- [ ] Transaction rollback
- [ ] Production logging
- [ ] Sensitive information handling