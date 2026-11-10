# MODULE 3: API Design and Communication
## Complete System Design Interview Preparation Notes

**Target:** Java Full Stack Developer | 5+ Years Experience  
**Objective:** Product-based company interviews | Senior Software Engineer

---

# PART 1: REST API DESIGN

## 1. REST API Design Principles

### What is REST?

REST stands for Representational State Transfer.

It is an architectural style used to design APIs that allow different applications to communicate over HTTP.

For example, an Angular frontend communicates with a Spring Boot backend through REST APIs.

**Example flow:**

```text
Angular Application
       |
       | HTTP GET /api/users/101
       |
       v
Spring Boot REST Controller
       |
       v
Service Layer
       |
       v
Repository Layer
       |
       v
Database
       |
       v
JSON Response
```

### Six REST architectural constraints

| Principle | Explanation | Example |
|---|---|---|
| Client-Server | Frontend and backend are independent | Angular + Spring Boot |
| Stateless | Every request contains the information needed to process it | JWT sent with each request |
| Cacheable | Responses can be cached when appropriate | Cache GET user details |
| Uniform Interface | Consistent resource-based API structure | `/users/101` |
| Layered System | Client does not need to know internal server layers | API Gateway → Service |
| Code on Demand | Server can optionally send executable code | JavaScript returned by a server |

### Example REST APIs

```http
GET    /api/users
GET    /api/users/101
POST   /api/users
PUT    /api/users/101
PATCH  /api/users/101
DELETE /api/users/101
```

### REST best practices

1. Use nouns instead of verbs in resource URLs.
2. Use HTTP methods according to their intended purpose.
3. Return appropriate HTTP status codes.
4. Use JSON for request and response bodies where appropriate.
5. Keep APIs stateless.
6. Support pagination for large datasets.
7. Implement consistent error responses.
8. Secure APIs using authentication and authorization.
9. Support caching where useful.
10. Document APIs using OpenAPI/Swagger.

**Bad design:**

```http
GET /getUserById?id=101
POST /createNewUser
GET /deleteUser?id=101
```

**Good design:**

```http
GET    /users/101
POST   /users
DELETE /users/101
```

### Spring Boot example

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(
            @PathVariable Long id) {

        return ResponseEntity.ok(
            userService.getUser(id)
        );
    }

    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestBody User user) {

        User saved = userService.createUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }
}
```

**Interview question:** What makes an API RESTful?

**Answer:** A RESTful API follows REST architectural constraints, including stateless communication, a uniform interface, resource-based URLs, appropriate HTTP methods, and cacheability.

---

## 2. Resource-Based API Design

### What is a resource?

A resource is an entity or object exposed through an API.

Examples:

- User
- Product
- Order
- Payment
- Employee
- Department

REST APIs identify resources using URLs.

### Resource-based URL design

Consider an e-commerce application.

```http
GET /api/products
GET /api/products/101

GET /api/orders
GET /api/orders/501

GET /api/users/101/orders
GET /api/orders/501/items
```

### Resource relationships

Suppose:

- One user can place multiple orders.
- One order can contain multiple products.

Resource hierarchy:

```text
User
 |
 +---- Order 1
 |       |
 |       +---- Product 1
 |       +---- Product 2
 |
 +---- Order 2
         |
         +---- Product 3
```

Possible API:

```http
GET /users/101/orders
GET /users/101/orders/501
GET /orders/501/items
```

### Should we use deeply nested URLs?

Avoid unnecessarily deep nesting.

Bad:

```http
GET /users/101/orders/501/items/10/products/20
```

Better:

```http
GET /products/20
GET /orders/501/items
```

### Naming conventions

| Rule | Recommended |
|---|---|
| Use nouns | `/products` |
| Prefer plural names | `/users` |
| Use lowercase | `/orders` |
| Use hyphens for compound words | `/order-items` |
| Avoid verbs | `/payments` instead of `/makePayment` |
| Use query parameters for filtering | `/products?category=mobile` |

**Interview point:** Design APIs around business resources rather than database table names or individual service methods.

---

## 3. API Versioning

### What is API versioning?

API versioning allows us to introduce changes to an API without breaking existing clients.

Consider an API initially returning:

```json
{
  "id": 101,
  "name": "Naren"
}
```

Later, the business requires first and last names separately:

```json
{
  "id": 101,
  "firstName": "Naren",
  "lastName": "Kumar"
}
```

Existing frontend applications may break if the original response contract changes.

Versioning allows both contracts to coexist.

### Types of API versioning

**1. URI versioning**

```http
GET /api/v1/users/101
GET /api/v2/users/101
```

Advantages:
- Easy to understand.
- Easy to test.
- Simple routing.

Disadvantages:
- Version becomes part of the URL.
- Can create duplicate endpoints.

**2. Query parameter versioning**

```http
GET /api/users/101?version=1
GET /api/users/101?version=2
```

Advantages:
- Simple to implement.

Disadvantages:
- Version is less visible.
- Can complicate caching and routing.

**3. Header-based versioning**

```http
GET /api/users/101
Accept: application/vnd.company.v1+json
```

Advantages:
- Keeps URLs clean.
- Version is expressed through HTTP headers.

Disadvantages:
- Less discoverable.
- Requires appropriate client and gateway configuration.

**4. Custom header versioning**

```http
GET /api/users/101
X-API-Version: 2
```

### Comparison

| Approach | Example | Main advantage |
|---|---|---|
| URI | `/v1/users` | Simple |
| Query | `?version=1` | Easy configuration |
| Accept header | `application/vnd.api.v1+json` | Clean URL |
| Custom header | `X-API-Version: 1` | Flexible |

### When should we create a new version?

- Breaking response structure changes.
- Removing or renaming fields.
- Changing existing API behavior.
- Changing required request fields.
- Significant contract changes.

Adding an optional response field generally does not require a new version if clients can tolerate it.

### Best practices

1. Avoid unnecessary version creation.
2. Maintain backward compatibility.
3. Publish deprecation timelines.
4. Monitor old-version usage.
5. Provide migration documentation.
6. Avoid supporting old versions indefinitely.

**Interview question:** How do you introduce a breaking API change without affecting existing consumers?

**Answer:** Introduce a new API version, maintain the previous contract temporarily, communicate a deprecation timeline, migrate consumers, and retire the old version after usage has sufficiently reduced.

---

## 4. Pagination: Offset vs Cursor

### What is pagination?

Pagination divides a large dataset into smaller chunks.

Imagine a database contains 1 million users.

Returning all 1 million records in one API response creates:

- High memory consumption.
- Increased network traffic.
- Slow response times.
- High database load.

Pagination solves this problem.

### A. Offset-based pagination

Offset pagination uses page number and page size or limit and offset.

Example:

```http
GET /api/users?page=0&size=10
```

Or:

```http
GET /api/users?limit=10&offset=20
```

SQL:

```sql
SELECT *
FROM users
ORDER BY id
LIMIT 10 OFFSET 20;
```

Explanation:

- LIMIT 10: Return 10 records.
- OFFSET 20: Skip the first 20 records.

Example:

| Page | Offset | Returned records |
|---|---:|---|
| 1 | 0 | 1–10 |
| 2 | 10 | 11–20 |
| 3 | 20 | 21–30 |

### Advantages

- Simple implementation.
- Easy page navigation.
- Easy to jump to a particular page.
- Supported by most databases.

### Disadvantages

- Large offsets can become expensive.
- Insertions and deletions can cause duplicate or missing records between page requests.
- Database may scan or skip many rows.

### B. Cursor-based pagination

Cursor pagination uses a reference to the last record returned.

Example:

```http
GET /api/users?limit=10&cursor=1050
```

SQL:

```sql
SELECT *
FROM users
WHERE id > 1050
ORDER BY id
LIMIT 10;
```

Suppose the first request returns:

```json
{
  "data": [
    {"id": 1001, "name": "User1"},
    {"id": 1002, "name": "User2"},
    {"id": 1003, "name": "User3"}
  ],
  "nextCursor": "1003"
}
```

Next request:

```http
GET /api/users?limit=3&cursor=1003
```

The next records start after ID 1003.

### Advantages

- Efficient for large datasets when supported by suitable indexes.
- Better for frequently changing datasets.
- Avoids scanning large offsets.
- Suitable for infinite scrolling and feeds.

### Disadvantages

- Cannot easily jump directly to page 50.
- More complicated implementation.
- Requires a stable ordering key.
- Cursor encoding and validation must be handled carefully.

### Comparison

| Feature | Offset | Cursor |
|---|---|---|
| Implementation | Simple | More complex |
| Large datasets | Can be slow | Usually efficient |
| Page jumping | Supported | Not naturally supported |
| Concurrent inserts | Can shift pages | More stable |
| Infinite scrolling | Less suitable | Excellent |
| Typical usage | Admin dashboards | Social feeds, transaction history |

### Spring Boot example

```java
@GetMapping("/users")
public Page<User> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

    Pageable pageable = PageRequest.of(
        page,
        size,
        Sort.by("id").ascending()
    );

    return userRepository.findAll(pageable);
}
```

**Important:** Cursor pagination is not automatically a consistent snapshot. If the underlying data changes, results can still change unless the design uses suitable snapshot or ordering semantics.

**Interview question:** When would you choose cursor pagination over offset pagination?

**Answer:** For large, continuously changing datasets such as social feeds, transaction history, and activity streams, cursor pagination generally provides better performance and more stable traversal.

---

## 5. Filtering and Sorting

### What is filtering?

Filtering retrieves records based on specified conditions.

Example:

```http
GET /api/products?category=mobile
```

Only mobile products are returned.

Multiple filters:

```http
GET /api/products?category=mobile&brand=Samsung&minPrice=10000
```

Possible SQL:

```sql
SELECT *
FROM products
WHERE category = 'mobile'
AND brand = 'Samsung'
AND price >= 10000;
```

### What is sorting?

Sorting arranges records in ascending or descending order.

Example:

```http
GET /api/products?sort=price,asc
```

```http
GET /api/products?sort=createdAt,desc
```

SQL:

```sql
SELECT *
FROM products
ORDER BY price ASC;
```

### Combined filtering, sorting and pagination

```http
GET /api/products?category=mobile&sort=price,asc&page=0&size=20
```

### Spring Boot example

```java
@GetMapping("/products")
public Page<Product> getProducts(
        @RequestParam String category,
        Pageable pageable) {

    return productRepository
            .findByCategory(category, pageable);
}
```

### Best practices

1. Validate allowed filter fields.
2. Validate sorting fields against an allowlist.
3. Set a maximum page size.
4. Add database indexes for common filters.
5. Use stable sorting, ideally with a unique tie-breaker.
6. Avoid exposing arbitrary SQL expressions through query parameters.

**Interview example:** An e-commerce product listing supports category filtering, price sorting, and pagination so customers can efficiently browse millions of products.

---

# PART 2: IDEMPOTENCY AND API SECURITY

## 6. Idempotency

### What is idempotency?

An operation is idempotent if performing it multiple times produces the same intended final result as performing it once.

Mathematically:

```text
f(f(x)) = f(x)
```

### Real-world example

Imagine a user clicks the payment button twice because the application is slow.

Without idempotency:

```text
Request 1 → ₹1,000 deducted
Request 2 → ₹1,000 deducted

Total deducted = ₹2,000
```

With idempotency:

```text
Request 1 → ₹1,000 deducted
Request 2 → Existing result returned

Total deducted = ₹1,000
```

### HTTP method idempotency

| HTTP method | Idempotent? | Explanation |
|---|---|---|
| GET | Yes | Does not change server state |
| HEAD | Yes | Retrieves headers |
| PUT | Yes | Replaces resource with specified state |
| DELETE | Yes | Repeated deletion has the same intended final state |
| POST | Not generally | Can create multiple resources |
| PATCH | Depends | Depends on the patch operation |

Example:

```http
PUT /api/users/101

{
  "name": "Naren"
}
```

Repeated requests produce the same intended user representation.

However:

```http
POST /api/orders
```

Repeated requests may create multiple orders unless additional safeguards are implemented.

### Why is idempotency important?

- Payment processing.
- Order creation.
- Bank transfers.
- Booking systems.
- Distributed systems.
- Retry mechanisms.
- Network failure recovery.

**Interview question:** Is DELETE idempotent?

**Answer:** Yes, in terms of intended final resource state. Deleting the same resource repeatedly should leave it deleted, even though the HTTP response might differ between attempts.

---

## 7. Idempotency Keys

### What is an idempotency key?

An idempotency key is a unique identifier supplied by a client to identify a logical operation.

The server uses it to recognize repeated requests and prevent duplicate processing.

Example:

```http
POST /api/payments
Idempotency-Key: payment-12345
Content-Type: application/json
```

Request:

```json
{
  "userId": 101,
  "amount": 1000,
  "currency": "INR"
}
```

### How does it work?

```text
Client
  |
  | POST /payments
  | Idempotency-Key: payment-12345
  |
  v
Payment API
  |
  v
Check Idempotency Key
  |
  +---- Key exists and request matches?
  |          |
  |          +---- YES → Return stored result
  |
  +---- Key does not exist?
             |
             v
       Process payment
             |
             v
       Store operation result
             |
             v
       Return response
```

### Database design

```sql
CREATE TABLE idempotency_records (
    id BIGINT PRIMARY KEY,
    idempotency_key VARCHAR(255) UNIQUE NOT NULL,
    request_hash VARCHAR(255) NOT NULL,
    status VARCHAR(30) NOT NULL,
    response_body TEXT,
    created_at TIMESTAMP
);
```

### Java example (simplified)

```java
public PaymentResponse createPayment(
        String idempotencyKey,
        PaymentRequest request) {

    return repository.findByKey(idempotencyKey)
        .map(existing -> {
            validateSameRequest(existing, request);
            return existing.getResponse();
        })
        .orElseGet(() -> {
            PaymentResponse response =
                paymentService.process(request);

            repository.save(
                new IdempotencyRecord(
                    idempotencyKey,
                    response
                )
            );

            return response;
        });
}
```

This is illustrative pseudocode. A production implementation must atomically claim the key, coordinate concurrent requests, persist the processing state, and handle failures.

### Important implementation considerations

1. Use a unique database constraint on the key.
2. Associate the key with the authenticated user or tenant.
3. Store a request hash to reject reuse with different payloads.
4. Handle concurrent requests using atomic database operations.
5. Track states such as `IN_PROGRESS`, `COMPLETED`, and `FAILED`.
6. Define key expiration and retention policies.
7. Store the response or enough information to reconstruct it.
8. Coordinate database transactions and external payment-provider operations.

**Important distinction:** An idempotency key does not magically guarantee exactly-once execution. It provides deduplication for a logical operation when implemented with appropriate persistence and recovery.

---

## 8. API Authentication

### What is authentication?

Authentication verifies the identity of a user or system.

It answers:

**Who are you?**

Examples:

- Username and password.
- OTP.
- JWT.
- OAuth access token.
- API key.
- Client certificate.

### Authentication flow

```text
Client
  |
  | Username + Password
  v
Authentication Server
  |
  v
Validate Credentials
  |
  v
Generate Token
  |
  v
Return Access Token
  |
  v
Client stores token securely
  |
  v
Send token with subsequent requests
```

Example:

```http
GET /api/profile

Authorization: Bearer eyJhbGciOi...
```

### Common authentication mechanisms

| Mechanism | Usage |
|---|---|
| Basic Authentication | Simple username/password authentication |
| Session Authentication | Traditional web applications |
| JWT | Token-based API authentication |
| OAuth 2.0 | Delegated authorization |
| API Keys | Identifying applications or API consumers |
| Mutual TLS | Service or client certificate authentication |

### Security best practices

- Always use HTTPS.
- Hash passwords using Argon2id or bcrypt.
- Never store plaintext passwords.
- Never log passwords or access tokens.
- Apply token expiration.
- Rotate credentials.
- Implement brute-force protection.
- Use secure token storage.

---

## 9. OAuth 2.0

### What is OAuth 2.0?

OAuth 2.0 is an authorization framework that allows a client application to access protected resources on behalf of a user or itself, within granted permissions.

Example:

You want to use an application and choose:

**Sign in with Google.**

The application can obtain permission to access selected Google resources without receiving your Google password.

OAuth 2.0 is primarily an authorization framework, not an authentication protocol by itself.

OpenID Connect (OIDC) adds an identity layer on top of OAuth 2.0.

### Important OAuth components

| Component | Meaning |
|---|---|
| Resource Owner | User who owns the data |
| Client | Application requesting access |
| Authorization Server | Issues access tokens |
| Resource Server | Hosts protected APIs |
| Access Token | Credential used to access resources |
| Refresh Token | Credential used to obtain new access tokens |
| Scope | Defines permitted access |

### OAuth 2.0 authorization code flow

```text
User
 |
 | 1. Click Login
 v
Client Application
 |
 | 2. Redirect to Authorization Server
 v
Authorization Server
 |
 | 3. User authenticates and grants consent
 v
Authorization Server
 |
 | 4. Redirect with Authorization Code
 v
Client Application
 |
 | 5. Exchange code for tokens
 v
Authorization Server
 |
 | 6. Return access token
 v
Client Application
 |
 | 7. Call protected API
 v
Resource Server
```

### Important OAuth 2.0 grant types

| Grant type | Usage |
|---|---|
| Authorization Code + PKCE | Browser-based and mobile applications |
| Client Credentials | Machine-to-machine communication |
| Refresh Token | Obtaining a new access token |
| Device Authorization | Devices with limited input capabilities |

Avoid the deprecated Resource Owner Password Credentials grant. Implicit flow is also no longer recommended for modern applications.

### Client Credentials example

A backend service needs to call another backend service.

```http
POST /oauth/token
Content-Type: application/x-www-form-urlencoded

grant_type=client_credentials
&client_id=order-service
&client_secret=secret
&scope=payments.read
```

Example response:

```json
{
  "access_token": "abc123",
  "token_type": "Bearer",
  "expires_in": 3600,
  "scope": "payments.read"
}
```

### OAuth vs authentication

OAuth 2.0 answers:

**What is this client allowed to access?**

OpenID Connect answers:

**Who is the authenticated user?**

**Interview question:** What is the difference between OAuth 2.0 and JWT?

**Answer:** OAuth 2.0 is an authorization framework, whereas JWT is a token format. OAuth access tokens may be JWTs or opaque tokens.

---

## 10. JWT (JSON Web Token)

### What is JWT?

JWT stands for JSON Web Token.

It is a compact, URL-safe token format used to transfer claims between parties.

It is commonly used for stateless API authentication.

### JWT structure

A JWT consists of three parts:

```text
HEADER.PAYLOAD.SIGNATURE
```

Example:

```text
eyJhbGciOiJIUzI1NiJ9
.
eyJzdWIiOiIxMDEiLCJyb2xlIjoiQURNSU4ifQ
.
signature
```

### 1. Header

Contains token metadata.

```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

### 2. Payload

Contains claims.

```json
{
  "sub": "101",
  "name": "Naren",
  "role": "ADMIN",
  "iat": 1794130000,
  "exp": 1794133600
}
```

Important claims:

| Claim | Meaning |
|---|---|
| sub | Subject |
| iss | Issuer |
| aud | Audience |
| exp | Expiration |
| iat | Issued at |
| nbf | Not before |
| jti | Token identifier |

### 3. Signature

The signature verifies token integrity.

Conceptually:

```text
HMAC-SHA256(
    base64Url(header) + "." + base64Url(payload),
    secret
)
```

With asymmetric signing algorithms such as RS256, the issuer signs with a private key and verifiers use the corresponding public key.

### JWT authentication flow

```text
1. User logs in
       |
       v
2. Authentication server validates credentials
       |
       v
3. JWT is generated and signed
       |
       v
4. JWT returned to client
       |
       v
5. Client sends JWT in Authorization header
       |
       v
6. API validates signature and claims
       |
       v
7. API checks permissions
       |
       v
8. Return protected resource
```

### Spring Security example

```java
http
    .csrf(csrf -> csrf.disable())
    .sessionManagement(session ->
        session.sessionCreationPolicy(
            SessionCreationPolicy.STATELESS
        )
    )
    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/auth/**").permitAll()
        .anyRequest().authenticated()
    );
```

A JWT resource server should also validate the token's signature, issuer, audience where applicable, expiry, and other relevant claims.

### Advantages

- No server-side HTTP session is required.
- Suitable for distributed APIs.
- Works well across multiple services.
- Supports claims and roles.
- Easy to transmit through HTTP headers.

### Disadvantages

- Difficult to revoke immediately without additional infrastructure.
- Token size can increase.
- Stolen tokens can be reused until expiry or revocation.
- Claims may become stale.
- Incorrect implementation creates security vulnerabilities.

### Important security practices

1. Use HTTPS.
2. Keep access-token lifetimes short.
3. Use secure refresh-token rotation.
4. Never put passwords or sensitive data in the payload.
5. Validate signatures and claims.
6. Do not accept arbitrary signing algorithms.
7. Avoid storing tokens in insecure browser storage.
8. Consider revocation or denylisting for sensitive applications.

**Remember:** JWT payloads are normally encoded, not encrypted. Anyone holding a regular signed JWT can decode and read its payload.

---

## 11. Session-Based Authentication

### What is a session?

A session stores authentication state on the server after a user logs in.

The client receives a session identifier, usually in a cookie.

### How does session authentication work?

```text
1. User enters credentials
       |
       v
2. Server validates credentials
       |
       v
3. Server creates session
       |
       v
4. Server stores session data
       |
       v
5. Session ID sent to browser in cookie
       |
       v
6. Browser sends cookie with next request
       |
       v
7. Server looks up session ID
       |
       v
8. User is authenticated
```

Example response:

```http
HTTP/1.1 200 OK
Set-Cookie: JSESSIONID=abc123; HttpOnly; Secure; SameSite=Lax
```

Subsequent request:

```http
GET /api/profile
Cookie: JSESSIONID=abc123
```

### Session storage

Session data can be stored in:

- Application memory.
- Redis.
- Distributed session storage.
- Database.

### Advantages

- Easy session invalidation.
- Server controls authentication state.
- Suitable for traditional web applications.
- Sensitive user details remain on the server.

### Disadvantages

- Server-side storage is required.
- Multiple instances need shared session storage or sticky sessions.
- Session replication and availability need consideration.
- Cookie-based authentication requires CSRF protection where applicable.

### Spring Boot Redis session example

A distributed application can use Spring Session with Redis:

```text
Browser
   |
   v
Load Balancer
   |
   +---- Instance 1
   |
   +---- Instance 2
   |
   v
Redis Session Store
```

Both application instances can retrieve the same user's session.

### JWT vs Session authentication

| Feature | JWT | Session |
|---|---|---|
| State | Can be stateless | Stateful |
| Server storage | Not required for basic validation | Required |
| Revocation | Requires additional mechanism | Easier |
| Scaling | Easy validation across instances | Requires shared session store or affinity |
| Token size | Usually larger | Small session ID |
| Common usage | Distributed APIs | Traditional web apps |

**Interview point:** JWT is not automatically more secure or more scalable than sessions. The right choice depends on application architecture, revocation requirements, browser security, and operational complexity.

---

## 12. API Authorization

### What is authorization?

Authorization determines what an authenticated user is allowed to do.

It answers:

**What are you allowed to access?**

### Authentication vs authorization

| Authentication | Authorization |
|---|---|
| Verifies identity | Verifies permissions |
| Who are you? | What can you do? |
| Login | Access control |
| Password, token | Roles, permissions, policies |

Example:

A user successfully logs into a banking application.

However:

- Customer can view their own account.
- Bank employee can access assigned customer records.
- Admin can manage selected administrative operations.

Authentication confirms identity. Authorization controls access.

### Authorization models

**1. RBAC – Role-Based Access Control**

Permissions are assigned to roles.

Example:

```text
ADMIN
  |
  +---- CREATE_USER
  +---- DELETE_USER
  +---- VIEW_USER

MANAGER
  |
  +---- VIEW_USER
  +---- UPDATE_USER

USER
  |
  +---- VIEW_OWN_PROFILE
```

Spring Security:

```java
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/users/{id}")
public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
}
```

**2. ABAC – Attribute-Based Access Control**

Access is decided using attributes.

Example:

A manager can access employee records only if the employee belongs to the manager's department.

Attributes:

- User department.
- User designation.
- Resource department.
- Time.
- Location.

**3. Resource-based authorization**

Checks whether a user can access a specific resource.

Example:

```text
User 101 can access Order 501
User 101 cannot access Order 502
```

This prevents insecure direct object reference (IDOR) vulnerabilities.

### Best practices

- Apply authorization on the backend.
- Never trust frontend role checks alone.
- Check resource ownership.
- Follow least privilege.
- Deny access by default.
- Avoid exposing sensitive data in error responses.

---

## 13. Rate Limiting

### What is rate limiting?

Rate limiting restricts how many requests a client can make within a defined time period.

Example:

```text
Maximum 100 requests per minute per API key.
```

If the client exceeds the limit, the server returns:

```http
HTTP/1.1 429 Too Many Requests
Retry-After: 30
```

### Why do we need rate limiting?

1. Prevent abuse.
2. Protect backend services.
3. Prevent brute-force attacks.
4. Control infrastructure costs.
5. Ensure fair usage.
6. Protect database resources.

### Common rate-limiting algorithms

**1. Fixed Window Counter**

Counts requests in a fixed time window.

Example:

```text
10:00:00 – 10:01:00 → Maximum 100 requests
10:01:00 – 10:02:00 → Maximum 100 requests
```

Problem: A client can send 100 requests at the end of one window and another 100 at the beginning of the next.

This creates a boundary burst.

**2. Sliding Window Log**

Stores request timestamps and counts requests in the last specified duration.

Example:

```text
Current time = 10:01:30

Count requests between:
10:00:30 and 10:01:30
```

Advantage: Accurate rolling-window enforcement.

Disadvantage: Can consume significant storage.

**3. Sliding Window Counter**

Combines current and previous window counts using a weighted approximation.

Advantage: Lower storage requirements than a full timestamp log.

**4. Token Bucket**

A bucket stores tokens.

Each request consumes one token. Tokens are replenished at a fixed rate.

Example:

```text
Bucket capacity = 10 tokens
Refill rate = 2 tokens/second
Each request = 1 token
```

When the bucket becomes empty, additional requests are rejected until tokens refill.

Supports controlled bursts.

**5. Leaky Bucket**

Requests enter a queue and are processed at a controlled rate.

Excess requests may be rejected when the queue is full.

### Comparison

| Algorithm | Burst handling | Main characteristic |
|---|---|---|
| Fixed Window | Weak at boundaries | Simple |
| Sliding Log | Accurate | Stores timestamps |
| Sliding Counter | Approximate | Efficient |
| Token Bucket | Allows controlled bursts | Flexible |
| Leaky Bucket | Smooths traffic | Controlled processing rate |

### Distributed rate limiting

Suppose an API runs on three instances.

If each instance independently allows 100 requests per minute, the same client may receive 300 requests across the cluster.

Solution:

Use a shared rate-limit store such as Redis or enforce limits at a centralized API gateway.

```text
              Client
                 |
                 v
             API Gateway
                 |
                 v
              Redis
          Shared Counters
                 |
       +---------+---------+
       |         |         |
    API 1     API 2     API 3
```

---

## 14. Throttling

### What is throttling?

Throttling controls the rate at which incoming requests are processed.

When traffic exceeds the allowed capacity, the system may:

- Delay requests.
- Queue requests.
- Reject excess requests.
- Reduce processing rate.

### Example

An API can process 500 requests per second.

Incoming traffic:

```text
Requests received = 2,000/sec
Processing capacity = 500/sec
```

The application must control excess traffic.

### Rate limiting vs throttling

| Rate limiting | Throttling |
|---|---|
| Enforces a request quota | Controls processing speed |
| May reject excess requests | May delay, queue, or reject requests |
| Protects API usage limits | Protects service capacity |
| Example: 100 requests/minute | Example: Process 500 requests/second |

These mechanisms overlap in real systems, and some products use the terms interchangeably.

### Practical example

A Spring Boot application receives a sudden traffic spike.

Instead of allowing every request to consume database connections:

1. Gateway applies request limits.
2. Application applies concurrency limits.
3. Queue absorbs supported asynchronous workloads.
4. Excess traffic is rejected gracefully.

---

## 15. API Gateway

### What is an API Gateway?

An API Gateway is a centralized entry point through which clients access backend services.

It handles common cross-cutting concerns.

### Architecture

```text
               Angular / Mobile
                       |
                       v
                 API GATEWAY
                       |
        +--------------+--------------+
        |              |              |
        v              v              v
    User Service   Order Service   Payment Service
        |              |              |
        v              v              v
     User DB        Order DB       Payment DB
```

### Responsibilities

1. Request routing.
2. Authentication.
3. Authorization enforcement.
4. Rate limiting.
5. SSL/TLS termination.
6. Request and response transformation.
7. Logging and monitoring.
8. API version routing.
9. Caching.
10. Load balancing, depending on the gateway product.

### Example routing

```http
GET /api/users/101
```

Routes to:

```text
user-service:8081
```

```http
POST /api/orders
```

Routes to:

```text
order-service:8082
```

### API Gateway vs Load Balancer

| API Gateway | Load Balancer |
|---|---|
| API-aware routing and policies | Distributes traffic across instances |
| Authentication and rate limiting | Health checks and traffic distribution |
| Request transformation | Primarily traffic distribution |
| API aggregation may be supported | Does not normally compose business APIs |

A product can perform both gateway and load-balancing functions.

### Common API Gateway technologies

- Spring Cloud Gateway.
- Kong.
- NGINX.
- AWS API Gateway.
- Apigee.
- Azure API Management.

### Spring Cloud Gateway example

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: http://localhost:8081
          predicates:
            - Path=/api/users/**

        - id: order-service
          uri: http://localhost:8082
          predicates:
            - Path=/api/orders/**
```

**Interview question:** Why do we need an API Gateway in microservices?

**Answer:** It provides a unified entry point and centralizes cross-cutting concerns such as routing, authentication, rate limiting, and observability, reducing the need for each service to implement these concerns independently.

---

## 16. BFF Pattern (Backend for Frontend)

### What is BFF?

BFF stands for Backend for Frontend.

It is an architectural pattern in which separate backend services or API layers are designed for different client types.

For example:

- Web application.
- Mobile application.
- Admin application.

Each client has different data and performance requirements.

### Architecture

```text
             API Gateway
                  |
       +----------+----------+
       |          |          |
       v          v          v
    Web BFF    Mobile BFF   Admin BFF
       |          |          |
       +----------+----------+
                  |
           Microservices
          /      |       \
         v       v        v
       User    Order   Payment
       Service Service Service
```

### Example

A web application needs:

```json
{
  "user": {},
  "orders": [],
  "recommendations": []
}
```

A mobile application may need a smaller response:

```json
{
  "userName": "Naren",
  "latestOrder": {}
}
```

Instead of making every frontend call multiple microservices, the respective BFF aggregates and adapts responses.

### Advantages

- Client-specific API design.
- Reduced frontend complexity.
- Optimized payloads.
- Fewer client-to-backend round trips.
- Independent evolution of client APIs.

### Disadvantages

- Additional services to maintain.
- Potential code duplication.
- More operational complexity.
- Risk of duplicating business logic.

**Important:** BFF should handle client-specific orchestration and presentation concerns, not duplicate core business rules.

---

# PART 3: VALIDATION, ERROR HANDLING AND RESILIENCE

## 17. Request Validation

### What is request validation?

Request validation ensures that incoming API data follows expected business and technical rules.

Example:

A user registration API requires:

- Name must not be empty.
- Email must be valid.
- Age must be at least 18.
- Password must meet security requirements.

### Without validation

Invalid data can enter the service layer and database.

### Spring Boot validation example

Dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

DTO:

```java
public class UserRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
}
```

Controller:

```java
@PostMapping("/users")
public ResponseEntity<?> createUser(
        @Valid @RequestBody UserRequest request) {

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.createUser(request));
}
```

### Types of validation

| Type | Example |
|---|---|
| Required field | Name cannot be null |
| Format validation | Valid email |
| Length validation | Password length |
| Range validation | Age >= 18 |
| Business validation | Account must be active |
| Cross-field validation | Start date must precede end date |
| Database validation | Email must be unique |

### Important practices

1. Validate at the API boundary.
2. Use DTOs rather than exposing entities directly.
3. Validate business rules in the service layer.
4. Use database constraints for data integrity.
5. Return meaningful validation errors.
6. Never trust frontend validation alone.

---

## 18. Error Handling

### What is API error handling?

Error handling provides a consistent response when an API request cannot be processed.

Example:

A client requests:

```http
GET /api/users/999
```

If user 999 does not exist, the API should return an appropriate error rather than an unexplained server exception.

### Important HTTP status codes

| Status | Meaning | Example |
|---|---|---|
| 200 | OK | Successful GET |
| 201 | Created | Successful resource creation |
| 204 | No Content | Successful DELETE without body |
| 400 | Bad Request | Invalid input |
| 401 | Unauthorized | Missing or invalid authentication |
| 403 | Forbidden | Authenticated but not permitted |
| 404 | Not Found | Resource not found |
| 409 | Conflict | Duplicate or conflicting operation |
| 422 | Unprocessable Content | Semantically invalid request |
| 429 | Too Many Requests | Rate limit exceeded |
| 500 | Internal Server Error | Unexpected server failure |
| 502 | Bad Gateway | Invalid upstream response |
| 503 | Service Unavailable | Service temporarily unavailable |
| 504 | Gateway Timeout | Upstream response timed out |

### Standard error response

Use a consistent response format, preferably based on RFC 9457 Problem Details.

Example:

```json
{
  "type": "https://api.example.com/errors/user-not-found",
  "title": "User Not Found",
  "status": 404,
  "detail": "User with ID 999 does not exist",
  "instance": "/api/users/999"
}
```

### Spring Boot global exception handling

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>>
    handleUserNotFound(UserNotFoundException ex) {

        Map<String, String> error = new HashMap<>();

        error.put("message", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>
    handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
          .getFieldErrors()
          .forEach(error ->
              errors.put(
                  error.getField(),
                  error.getDefaultMessage()
              )
          );

        return ResponseEntity
                .badRequest()
                .body(errors);
    }
}
```

### Best practices

- Use appropriate HTTP status codes.
- Maintain a standard error schema.
- Include a trace or correlation ID.
- Never expose stack traces.
- Never expose passwords, tokens, or internal secrets.
- Log detailed errors internally.
- Provide actionable client-facing messages.

---

## 19. Retry Mechanisms

### What is a retry mechanism?

A retry mechanism attempts a failed operation again when the failure is considered temporary.

Example:

An Order Service calls a Payment Service.

```text
Order Service
      |
      v
Payment Service
      |
      X Temporary network failure
      |
      v
Retry Request
      |
      v
Payment Service
      |
      v
Success
```

### Why are retries needed?

- Temporary network failures.
- Service overload.
- Transient database failures.
- Temporary connection failures.
- Cloud infrastructure interruptions.

### Retry strategies

**1. Immediate Retry**

```text
Request 1 → Failed
Request 2 → Failed
Request 3 → Success
```

Problem: Can overload an already struggling service.

**2. Fixed Delay Retry**

```text
Attempt 1 → Wait 2 seconds
Attempt 2 → Wait 2 seconds
Attempt 3 → Wait 2 seconds
```

**3. Exponential Backoff**

Waiting time increases after every failed attempt.

```text
Retry 1 → 1 second
Retry 2 → 2 seconds
Retry 3 → 4 seconds
Retry 4 → 8 seconds
```

**4. Exponential Backoff with Jitter**

Adds randomness to retry delays.

Example:

```text
Retry 1 → 0.8 seconds
Retry 2 → 1.7 seconds
Retry 3 → 3.4 seconds
```

Jitter prevents many clients from retrying simultaneously.

### Spring Retry example

Conceptual example:

```java
@Retryable(
    retryFor = RuntimeException.class,
    maxAttempts = 3,
    backoff = @Backoff(
        delay = 1000,
        multiplier = 2
    )
)
public PaymentResponse callPaymentService() {

    return restClient.get()
            .uri("/payments")
            .retrieve()
            .body(PaymentResponse.class);
}
```

This is illustrative. Production retry policies should target specific transient failures, limit total elapsed time, and avoid retrying non-retryable errors.

### When should we retry?

Retry:

- Temporary connection reset.
- Selected 5xx errors.
- Temporary service unavailability.
- Transient failures explicitly classified as retryable.

Do not blindly retry:

- 400 Bad Request.
- 401 Unauthorized.
- 403 Forbidden.
- 404 Not Found.
- Business validation errors.

### Important rules

1. Use bounded retries.
2. Add exponential backoff.
3. Add jitter.
4. Set request deadlines.
5. Ensure operations are idempotent before retrying non-idempotent requests.
6. Avoid retry storms.
7. Coordinate retries across layers.

**Interview question:** What happens if all microservices retry failed requests simultaneously?

**Answer:** It can create a retry storm, increasing traffic against an already unhealthy service. Exponential backoff, jitter, retry budgets, timeouts, and circuit breakers help prevent this.

---

## 20. Timeouts

### What is a timeout?

A timeout defines the maximum amount of time a system waits for an operation to complete.

Example:

An Order Service calls Payment Service.

If the Payment Service does not respond within 3 seconds, the caller stops waiting.

```text
Order Service
      |
      | Request
      v
Payment Service
      |
      | No response within 3 seconds
      |
      v
Timeout Exception
```

### Types of timeouts

| Timeout | Purpose |
|---|---|
| Connection Timeout | Maximum time to establish connection |
| Read Timeout | Maximum wait for response data |
| Write Timeout | Maximum time allowed for writing data |
| Request Timeout | Maximum overall request duration |
| Database Query Timeout | Maximum time for query execution |
| Idle Timeout | Maximum idle connection duration |

### Example

Suppose:

```text
API Gateway timeout = 5 seconds
Order Service timeout = 4 seconds
Payment Service timeout = 3 seconds
```

A downstream service should generally have a shorter deadline than its caller, leaving time for the caller to process the response or failure.

### Spring Boot RestClient example

```java
@Bean
RestClient paymentRestClient() {

    HttpClient httpClient = HttpClient.create()
        .option(
            ChannelOption.CONNECT_TIMEOUT_MILLIS,
            2000
        )
        .responseTimeout(Duration.ofSeconds(3));

    return RestClient.builder()
        .requestFactory(
            new ReactorClientHttpRequestFactory(httpClient)
        )
        .build();
}
```

The example uses Reactor Netty-specific configuration; in a real project, choose the request factory and timeout settings supported by the HTTP client in use.

### Why are timeouts important?

- Prevent resource exhaustion.
- Avoid threads waiting indefinitely.
- Protect connection pools.
- Improve system responsiveness.
- Limit cascading failures.

**Important:** A timeout does not necessarily mean the downstream operation was not executed. For example, a payment may succeed even though the response never reaches the caller. This is why idempotency and operation-status checks matter.

---

## 21. Circuit Breaker

### What is a circuit breaker?

A circuit breaker is a resilience pattern that temporarily stops requests to a failing downstream service.

It prevents repeated calls from consuming resources and causing cascading failures.

### Real-world example

Order Service calls Payment Service.

Payment Service becomes unavailable.

Without a circuit breaker:

```text
Order Service
      |
      v
Payment Service
      |
      X Failure
      |
      v
Retry
      |
      X Failure
      |
      v
Retry
      |
      X Failure
```

The Order Service wastes resources repeatedly calling an unavailable dependency.

With a circuit breaker:

```text
Order Service
      |
      v
Circuit Breaker
      |
      v
Payment Service
      |
      X Repeated failures
      |
      v
Circuit Opens
      |
      v
Fast failure / Fallback
```

### Three circuit breaker states

**1. CLOSED**

Requests are allowed.

The circuit breaker monitors failures.

```text
Request → Payment Service
Request → Payment Service
Request → Payment Service
```

**2. OPEN**

Requests are blocked temporarily.

The circuit breaker fails fast or invokes an appropriate fallback.

```text
Request → Circuit Breaker → Rejected
```

**3. HALF-OPEN**

After a configured wait duration, a limited number of test requests are allowed.

- If successful, transition to CLOSED.
- If unsuccessful, transition to OPEN.

### State diagram

```text
       CLOSED
          |
          | Failure threshold reached
          v
         OPEN
          |
          | Wait duration completed
          v
       HALF-OPEN
          |
          +---- Success ----> CLOSED
          |
          +---- Failure ----> OPEN
```

### Resilience4j example

```java
@CircuitBreaker(
    name = "paymentService",
    fallbackMethod = "paymentFallback"
)
public PaymentResponse makePayment(
        PaymentRequest request) {

    return paymentClient.process(request);
}

public PaymentResponse paymentFallback(
        PaymentRequest request,
        Throwable ex) {

    return new PaymentResponse(
        "PAYMENT_PENDING"
    );
}
```

The fallback must represent a safe business outcome. Do not report a payment as successful when its actual status is unknown.

### Circuit breaker vs Retry

| Retry | Circuit Breaker |
|---|---|
| Repeats a failed operation | Stops calls to unhealthy dependency |
| Handles transient failures | Prevents cascading failures |
| Can increase traffic | Reduces traffic |
| Usually operates per request | Tracks dependency health over time |

### Circuit breaker vs Timeout

- Timeout: Stops waiting too long for a request.
- Circuit breaker: Stops repeatedly calling a failing dependency.

They complement each other.

**Interview question:** How do you prevent cascading failures in microservices?

**Answer:** Use bounded timeouts, circuit breakers, controlled retries with backoff and jitter, bulkheads, concurrency limits, rate limiting, and graceful degradation.

---

# PART 4: SERVICE COMMUNICATION

## 22. Synchronous vs Asynchronous Communication

### What is synchronous communication?

In synchronous communication, the caller waits for the response from the called service.

Example:

Order Service calls Payment Service and waits for the result.

```text
Order Service
     |
     | Request
     v
Payment Service
     |
     | Response
     v
Order Service
```

Example REST call:

```http
POST /api/payments
```

### Advantages

- Simple request-response flow.
- Immediate response.
- Easier to understand.
- Suitable for queries and operations requiring an immediate result.

### Disadvantages

- Services are tightly coupled at runtime.
- Caller waits for downstream services.
- Failures can propagate.
- High latency when multiple services are involved.

### What is asynchronous communication?

In asynchronous communication, the producer sends a message or event without waiting for the consumer to finish processing it.

Example:

An Order Service publishes an order-created event to Kafka.

```text
Order Service
     |
     | Publish event
     v
    Kafka
     |
     v
Notification Service
     |
     v
Send Email
```

The Order Service does not need to wait for the email to be sent.

### Advantages

- Loose runtime coupling.
- Better absorption of traffic spikes.
- Independent processing.
- Improved fault isolation.
- Supports eventual consistency.

### Disadvantages

- More complex architecture.
- Eventual consistency.
- Message duplication and ordering concerns.
- Harder debugging and tracing.
- Requires message recovery and monitoring.

### Comparison

| Feature | Synchronous | Asynchronous |
|---|---|---|
| Caller waits | Yes | Usually no |
| Coupling | Runtime dependency | Looser runtime coupling |
| Response | Immediate | Later or via another channel |
| Scaling | Can be constrained by dependency | Consumers can scale independently |
| Failure handling | Timeouts and errors | Retries, DLQ, replay |
| Example | REST API | Kafka, RabbitMQ |

### Java example

Synchronous:

```java
PaymentResponse response =
    paymentClient.makePayment(request);
```

Asynchronous:

```java
kafkaTemplate.send(
    "order-created",
    orderEvent
);
```

---

## 23. Event-Driven Communication

### What is event-driven architecture?

Event-driven architecture is a design approach in which services communicate by publishing and consuming events.

An event represents something that has already happened.

Examples:

- OrderCreated.
- PaymentCompleted.
- UserRegistered.
- InventoryReserved.
- ShipmentDispatched.

### Architecture

```text
             Order Service
                   |
                   | OrderCreated
                   v
                Kafka
                   |
        +----------+----------+
        |          |          |
        v          v          v
 Notification  Inventory   Analytics
   Service      Service     Service
```

One event can be consumed by multiple independent services.

### Producer

A producer creates and publishes events.

Example:

```java
OrderCreatedEvent event =
    new OrderCreatedEvent(
        order.getId(),
        order.getUserId(),
        order.getTotalAmount()
    );

kafkaTemplate.send(
    "order-created",
    event
);
```

### Consumer

A consumer listens for events.

```java
@KafkaListener(
    topics = "order-created",
    groupId = "notification-service"
)
public void consume(OrderCreatedEvent event) {

    notificationService.sendOrderConfirmation(
        event.getOrderId()
    );
}
```

### Important concepts

| Concept | Explanation |
|---|---|
| Producer | Publishes events |
| Consumer | Processes events |
| Broker | Transfers and stores messages |
| Topic | Logical event stream |
| Partition | Divides a topic for scalability |
| Consumer Group | Coordinates consumption among consumers |
| Offset | Position in a partition |
| DLQ | Stores messages that cannot be processed normally |

### Kafka vs RabbitMQ

| Kafka | RabbitMQ |
|---|---|
| Distributed event streaming platform | Message broker |
| Retains events based on configured retention | Message acknowledgement and queue lifecycle |
| High-throughput event streams | Flexible queue-based messaging |
| Replay from retained offsets | Replay depends on queue and message configuration |
| Common for analytics and event pipelines | Common for task queues and routing |

### Challenges in event-driven systems

1. Duplicate events.
2. Event ordering.
3. Schema evolution.
4. Consumer failures.
5. Message replay.
6. Eventual consistency.
7. Distributed tracing.
8. Dead-letter handling.

### Outbox pattern

A common problem:

A service saves an order to its database but fails to publish the corresponding Kafka event.

This creates inconsistent state.

Solution: Transactional Outbox Pattern.

```text
Order Service
     |
     v
Database Transaction
     |
     +---- Save Order
     |
     +---- Save Outbox Event
                  |
                  v
             Outbox Table
                  |
                  v
             Relay / CDC
                  |
                  v
                Kafka
```

The order and outbox record are saved in the same database transaction. A separate publisher or CDC process publishes the event.

Consumers should also be designed to handle duplicate delivery safely.

---

## 24. API Composition

### What is API composition?

API composition combines data from multiple backend services and returns a single response to the client.

Example:

A user dashboard needs:

- User details.
- Orders.
- Payment history.
- Notifications.

Without composition, the frontend makes four API calls.

```text
Angular
  |
  +---- User Service
  |
  +---- Order Service
  |
  +---- Payment Service
  |
  +---- Notification Service
```

With API composition:

```text
Angular
   |
   v
Dashboard API
   |
   +---- User Service
   |
   +---- Order Service
   |
   +---- Payment Service
   |
   +---- Notification Service
   |
   v
Combined JSON Response
```

Example response:

```json
{
  "user": {
    "id": 101,
    "name": "Naren"
  },
  "orders": [
    {
      "orderId": 501,
      "status": "DELIVERED"
    }
  ],
  "payments": [
    {
      "paymentId": 901,
      "amount": 1000
    }
  ],
  "notifications": []
}
```

### Implementation example

```java
public DashboardResponse getDashboard(Long userId) {

    User user = userClient.getUser(userId);

    List<Order> orders =
        orderClient.getOrders(userId);

    List<Payment> payments =
        paymentClient.getPayments(userId);

    List<Notification> notifications =
        notificationClient.getNotifications(userId);

    return new DashboardResponse(
        user,
        orders,
        payments,
        notifications
    );
}
```

This is a basic sequential example. Production implementations may use parallel calls, bounded concurrency, timeouts, and partial-result handling.

### Advantages

- Fewer frontend requests.
- Centralized response aggregation.
- Better client experience.
- Useful for dashboards.

### Disadvantages

- Aggregation service becomes dependent on multiple services.
- Increased complexity.
- Potentially higher latency.
- One slow dependency may delay the whole response.
- Large payloads can become expensive.

### API composition vs database joins

| API composition | Database join |
|---|---|
| Combines responses from services | Combines rows in a database |
| Works across service boundaries | Usually operates within one database |
| Can create network overhead | Often efficient for relational queries |
| Preserves service ownership | Requires access to underlying data |

**Interview point:** API composition is useful when data belongs to different microservices and the client needs a unified view.

---

## 25. Service-to-Service Authentication

### What is service-to-service authentication?

Service-to-service authentication verifies the identity of one backend service when it communicates with another backend service.

Example:

```text
Order Service
      |
      | Authenticated Request
      v
Payment Service
```

The Payment Service must verify that the caller is an authorized service.

### Why is it needed?

- Prevent unauthorized internal API access.
- Protect sensitive operations.
- Establish service identity.
- Enforce least privilege.
- Secure communication across distributed environments.

### Common approaches

**1. API Key**

```http
POST /payments
X-API-Key: abc123
```

Simple, but keys require secure storage, rotation, and careful scope management.

**2. OAuth 2.0 Client Credentials**

A service obtains an access token using its own client identity.

```text
Order Service
      |
      | Client ID + Client Credential
      v
Authorization Server
      |
      v
Access Token
      |
      v
Payment Service
```

Example:

```http
Authorization: Bearer eyJhbGciOi...
```

The token contains or represents permissions such as:

```text
payments.read
payments.write
```

**3. Mutual TLS (mTLS)**

Both communicating services authenticate using certificates.

```text
Order Service  <---- TLS + Client Certificate ----> Payment Service
```

Advantages:

- Strong service identity.
- Mutual authentication.
- Encrypted transport.
- Useful for zero-trust architectures.

**4. Workload Identity**

Cloud platforms can provide identities to running workloads.

For example, a service running on a cloud platform obtains credentials associated with its workload identity instead of storing long-lived secrets.

### Comparison

| Mechanism | Security characteristics | Typical usage |
|---|---|---|
| API Key | Shared secret | Simple internal or partner APIs |
| OAuth Client Credentials | Scoped, short-lived tokens | Microservices |
| mTLS | Certificate-based mutual identity | High-security service communication |
| Workload Identity | Platform-managed identity | Cloud-native applications |

### Best practices

1. Never trust a service merely because it is inside the network.
2. Use HTTPS or mTLS.
3. Prefer short-lived credentials.
4. Apply least privilege.
5. Rotate secrets and certificates.
6. Validate token issuer, audience, expiry, and scopes.
7. Audit service access.
8. Avoid hardcoded secrets.
9. Use workload identity where supported.

---

# PART 5: QUICK REVISION AND INTERVIEW CHEAT SHEET

## A. Important comparisons

| Concepts | Key difference |
|---|---|
| Authentication vs Authorization | Identity vs permissions |
| OAuth 2.0 vs JWT | Authorization framework vs token format |
| JWT vs Session | Self-contained token validation vs server-managed session |
| Offset vs Cursor | Position-based pagination vs continuation-based pagination |
| Rate Limiting vs Throttling | Enforcing request quotas vs controlling processing rate |
| API Gateway vs Load Balancer | API policies and routing vs traffic distribution |
| BFF vs API Gateway | Client-specific backend vs common API entry point |
| Retry vs Circuit Breaker | Repeat failed calls vs stop calling unhealthy dependency |
| Timeout vs Circuit Breaker | Stop waiting vs prevent repeated calls |
| Sync vs Async | Wait for result vs process independently |
| REST vs Event-Driven | Request-response vs event-based communication |
| API Composition vs Database Join | Combine service responses vs combine database records |

## B. REST API design checklist

When designing a production REST API, consider:

- [ ] Resource-based URLs.
- [ ] Correct HTTP methods.
- [ ] Appropriate HTTP status codes.
- [ ] API versioning.
- [ ] Pagination.
- [ ] Filtering and sorting.
- [ ] Request validation.
- [ ] Consistent error responses.
- [ ] Authentication.
- [ ] Authorization.
- [ ] Rate limiting.
- [ ] Idempotency.
- [ ] Caching.
- [ ] API documentation.
- [ ] Logging and monitoring.
- [ ] Timeouts and retry policies.

## C. End-to-end e-commerce example

Let's combine the concepts into a real-world architecture.

**Scenario:** A customer places an order and makes a payment.

```text
                 Angular Frontend
                        |
                        v
                   API Gateway
                        |
             Authentication (JWT/OIDC)
                        |
             Rate Limiting / Routing
                        |
                        v
                  Order Service
                        |
              Request Validation
                        |
              Idempotency Check
                        |
                        v
                   Order DB
                        |
                        v
                Payment Service
                        |
               Service Authentication
                        |
               Timeout / Retry /
                Circuit Breaker
                        |
                        v
                 Payment Provider
                        |
                        v
                Payment Completed
                        |
                        v
                    Kafka
                        |
           +------------+------------+
           |            |            |
           v            v            v
      Inventory     Notification   Analytics
       Service        Service       Service
           |            |
           v            v
      Update Stock   Send Email
```

### Step-by-step explanation

1. Customer clicks Place Order.
2. Angular sends a POST request to the API Gateway.
3. Gateway authenticates the request.
4. Gateway checks the request rate limit.
5. Request reaches Order Service.
6. Order Service validates the request.
7. Idempotency key prevents duplicate order processing.
8. Order Service creates a pending order.
9. Order Service calls Payment Service.
10. Payment Service authenticates the service caller.
11. A timeout limits waiting time.
12. Retry handles eligible transient failures.
13. Circuit breaker protects against persistent downstream failures.
14. Payment result is recorded.
15. An order/payment event is published through Kafka.
16. Inventory Service updates stock.
17. Notification Service sends confirmation.
18. Analytics Service processes the event independently.

For a robust production implementation, order creation, payment processing, and event publishing need explicit transaction boundaries, idempotent consumers, recovery workflows, and compensation handling where necessary.

---

# PART 6: PRODUCT-BASED COMPANY INTERVIEW QUESTIONS

## Frequently asked theoretical questions

1. What are REST architectural constraints?
2. What is the difference between REST and RESTful APIs?
3. Why should REST APIs be stateless?
4. How do you design APIs for an e-commerce application?
5. What is API versioning? Which strategy do you prefer?
6. Offset vs cursor pagination: which is better?
7. What is idempotency? Why is it important in payment systems?
8. How do you prevent duplicate payment requests?
9. What is an idempotency key?
10. Authentication vs authorization?
11. OAuth 2.0 vs OpenID Connect?
12. OAuth 2.0 vs JWT?
13. Explain JWT structure.
14. How does JWT validation work?
15. JWT vs session-based authentication?
16. How do you revoke a JWT?
17. What is RBAC vs ABAC?
18. What is rate limiting?
19. Explain Token Bucket and Leaky Bucket.
20. Rate limiting vs throttling?
21. What is an API Gateway?
22. API Gateway vs Load Balancer?
23. What is the BFF pattern?
24. How do you implement global exception handling in Spring Boot?
25. How do you validate API requests?
26. Why are timeouts important in microservices?
27. What is exponential backoff?
28. Why is jitter needed?
29. What is a circuit breaker?
30. Explain CLOSED, OPEN, and HALF-OPEN states.
31. Retry vs Circuit Breaker?
32. How do you prevent cascading failures?
33. Synchronous vs asynchronous communication?
34. What is event-driven architecture?
35. Kafka vs RabbitMQ?
36. What is the Outbox Pattern?
37. What is API composition?
38. How do microservices authenticate each other?
39. What is mTLS?
40. How would you design a secure payment API?

## Practical design questions

| Scenario | Concepts to apply |
|---|---|
| Design a payment API | Idempotency, JWT, validation, authorization |
| Design an e-commerce product API | REST, filtering, sorting, pagination |
| Design a login system | OAuth, JWT, sessions, refresh tokens |
| Design an API for millions of users | Gateway, rate limiting, caching |
| Design an order-processing system | REST, Kafka, event-driven architecture |
| Design a notification system | Async communication, Kafka, retries |
| Design a resilient microservice | Timeout, retry, circuit breaker |
| Design a dashboard API | API composition, BFF |
| Secure internal microservices | OAuth Client Credentials, mTLS |
| Prevent duplicate transactions | Idempotency keys, database constraints |

---

# PART 7: WHAT TO MASTER IN JAVA SPRING BOOT

For your experience level, don't stop at theoretical understanding. Implement these concepts.

| Priority | Practical implementation |
|---|---|
| 1 | Build CRUD REST APIs |
| 2 | Implement DTO validation using `@Valid` |
| 3 | Implement global exception handling using `@RestControllerAdvice` |
| 4 | Implement pagination using `Pageable` |
| 5 | Implement JWT authentication using Spring Security |
| 6 | Implement role-based authorization using `@PreAuthorize` |
| 7 | Implement OAuth 2.0 Resource Server |
| 8 | Implement Redis-based rate limiting |
| 9 | Configure Spring Cloud Gateway |
| 10 | Implement Resilience4j Retry |
| 11 | Implement Resilience4j Circuit Breaker |
| 12 | Configure connection and response timeouts |
| 13 | Implement Kafka producer and consumer |
| 14 | Implement idempotency using a database unique constraint |
| 15 | Implement Outbox Pattern |

## Suggested learning sequence

| Day | Topics | Practice |
|---|---|---|
| Day 1 | REST principles, resource design, versioning | Design user and order APIs |
| Day 2 | Pagination, filtering, sorting | Implement pageable APIs |
| Day 3 | Idempotency, idempotency keys | Implement duplicate payment protection |
| Day 4 | Authentication, OAuth 2.0, JWT, sessions | Implement Spring Security |
| Day 5 | Authorization, rate limiting, throttling | Implement RBAC and Redis rate limiter |
| Day 6 | Gateway, BFF, validation, error handling | Build gateway and error responses |
| Day 7 | Retry, timeout, circuit breaker | Implement Resilience4j |
| Day 8 | Sync, async, event-driven communication | Implement Kafka producer/consumer |
| Day 9 | API composition, service authentication | Build dashboard aggregation API and secure service calls |
| Day 10 | Revision and interview questions | Conduct a mock interview |

---

# FINAL INTERVIEW TAKEAWAYS

For a Java developer with 5+ years of experience, these are the concepts you should be able to explain confidently:

1. Design a REST API from scratch.
2. Explain the complete JWT authentication flow.
3. Explain OAuth 2.0 Authorization Code + PKCE and Client Credentials.
4. Implement pagination and filtering efficiently.
5. Explain idempotency using a payment example.
6. Explain how API Gateway handles routing and security.
7. Explain rate limiting using Redis and Token Bucket.
8. Explain Retry, Timeout, and Circuit Breaker with real scenarios.
9. Explain synchronous vs asynchronous microservice communication.
10. Explain Kafka event publishing, consumers, and the Outbox Pattern.
11. Explain how services authenticate one another.
12. Design a secure, scalable order and payment API.

**The most important interview principle:** Be able to explain not just what a concept is, but why it is needed, what problem it solves, how it works internally, its trade-offs, and how you would implement it using Java and Spring Boot.

---

## References for further learning

- REST architectural style: https://ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm
- HTTP semantics (RFC 9110): https://www.rfc-editor.org/rfc/rfc9110
- OAuth 2.0 (RFC 6749): https://www.rfc-editor.org/rfc/rfc6749
- OAuth 2.0 Security Best Current Practice: https://www.rfc-editor.org/rfc/rfc9700
- OpenID Connect: https://openid.net/developers/how-connect-works/
- JWT (RFC 7519): https://www.rfc-editor.org/rfc/rfc7519
- Spring Security: https://docs.spring.io/spring-security/reference/
- Spring Cloud Gateway: https://docs.spring.io/spring-cloud-gateway/reference/
- Resilience4j: https://resilience4j.readme.io/
- Apache Kafka: https://kafka.apache.org/documentation/
- Problem Details (RFC 9457): https://www.rfc-editor.org/rfc/rfc9457

**END OF MODULE 3**