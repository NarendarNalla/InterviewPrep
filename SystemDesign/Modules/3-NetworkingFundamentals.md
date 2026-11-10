# MODULE 2: NETWORKING FUNDAMENTALS
## Complete System Design Interview Preparation – Java Full Stack (5+ YOE)

---

## TABLE OF CONTENTS

| # | Topic | Priority |
|---:|---|---|
| 1 | OSI Model | High |
| 2 | TCP/IP Model | High |
| 3 | HTTP and HTTPS | Critical |
| 4 | HTTP Methods and Status Codes | Critical |
| 5 | HTTP/1.1 vs HTTP/2 vs HTTP/3 | Critical |
| 6 | TCP vs UDP | Critical |
| 7 | TCP Three-Way Handshake | High |
| 8 | TLS Handshake | High |
| 9 | DNS Resolution | High |
| 10 | IP Addresses | High |
| 11 | Ports | High |
| 12 | Reverse Proxy | Critical |
| 13 | Forward Proxy | Medium |
| 14 | Load Balancers | Critical |
| 15 | L4 vs L7 Load Balancing | Critical |
| 16 | WebSockets | High |
| 17 | Server-Sent Events | High |
| 18 | Long Polling | Medium |
| 19 | gRPC | High |
| 20 | REST vs GraphQL vs gRPC | Critical |
| 21 | CDN | High |
| 22 | Anycast | Medium |
| 23 | Network Latency | Critical |
| 24 | Connection Pooling | Critical |
| 25 | Keep-Alive | High |
| 26 | Rate Limiting | Critical |

---

# PART 1: NETWORKING FOUNDATIONS

## 1. OSI Model

### Definition

OSI (Open Systems Interconnection) is a conceptual networking model that explains how data travels from one device to another through seven different layers.

Each layer has a specific responsibility.

It helps developers and network engineers understand communication, identify networking problems, and design distributed applications.

### Seven layers of OSI Model

| Layer | Name | Responsibility | Example |
|---|---|---|---|
| 7 | Application | Provides network services to applications | HTTP, DNS, SMTP |
| 6 | Presentation | Data formatting, encryption, compression | TLS, JSON encoding |
| 5 | Session | Manages communication sessions | Session management |
| 4 | Transport | End-to-end data transport | TCP, UDP |
| 3 | Network | Routing and logical addressing | IP |
| 2 | Data Link | Communication within a local network | Ethernet, Wi-Fi, MAC |
| 1 | Physical | Transmits raw bits | Cables, radio signals |

### Understanding each layer

**Layer 7 – Application Layer**

Provides networking services to applications.

Examples:
- HTTP
- HTTPS
- DNS
- SMTP
- FTP

Example: When a browser sends an HTTP request to a Spring Boot API, HTTP operates at the application layer.

**Layer 6 – Presentation Layer**

Responsible for data representation and transformations.

Responsibilities:
- Encryption and decryption.
- Compression and decompression.
- Data serialization and formatting.

Examples:
- JSON.
- UTF-8.
- JPEG.
- TLS encryption (commonly mapped conceptually to this layer, although modern TLS spans the practical networking stack rather than being a strict OSI layer-6 protocol).

**Layer 5 – Session Layer**

Manages communication sessions between applications.

Responsibilities:
- Establishing sessions.
- Maintaining sessions.
- Synchronizing communication.
- Closing sessions.

Example: Managing a logical communication session between two applications.

**Layer 4 – Transport Layer**

Responsible for end-to-end communication.

Protocols:
- TCP.
- UDP.

Responsibilities:
- Segmentation.
- Port addressing.
- Flow control.
- Error detection.
- Reliable delivery when using TCP.

Example: A Spring Boot application communicating with a database over TCP.

**Layer 3 – Network Layer**

Responsible for routing packets between networks.

Protocol:
- IP.

Responsibilities:
- Logical addressing.
- Routing.
- Packet forwarding.

Example: Sending data from a laptop to a server hosted in AWS.

**Layer 2 – Data Link Layer**

Responsible for transferring frames between devices on the same local network.

Examples:
- Ethernet.
- Wi-Fi.
- MAC addresses.

**Layer 1 – Physical Layer**

Responsible for transmitting raw bits over physical or wireless media.

Examples:
- Ethernet cables.
- Fiber optic cables.
- Radio signals.

### OSI data flow

```text
SENDER

Application       HTTP Request
      |
Presentation      Encoding / Encryption
      |
Session           Session Management
      |
Transport         TCP Segment
      |
Network           IP Packet
      |
Data Link         Ethernet Frame
      |
Physical          Bits
      |
      v
    NETWORK
      |
      v
RECEIVER

Reverse process: Decapsulation
```

### Important terminology

| Term | Meaning |
|---|---|
| Data | Application and upper-layer information |
| Segment | Transport-layer data unit (TCP) |
| Datagram | Commonly used for UDP transport data |
| Packet | Network-layer data unit |
| Frame | Data-link-layer data unit |
| Bits | Physical-layer representation |

### Real-world example

When you access:

`https://api.example.com/users`

- Application layer: HTTP request.
- Transport layer: TCP or QUIC transport.
- Network layer: IP routing.
- Data link layer: Ethernet/Wi-Fi frames.
- Physical layer: Electrical, optical, or radio signals.

### Interview questions

1. Explain the seven OSI layers.
2. Which OSI layer does TCP operate at?
3. What is the difference between Layer 2 and Layer 3?
4. At which layer does HTTP operate?
5. How would you troubleshoot a network connectivity issue using OSI?

**Remember:** OSI is a conceptual reference model, not an exact implementation blueprint for every modern networking protocol.

---

## 2. TCP/IP Model

### Definition

TCP/IP (Transmission Control Protocol / Internet Protocol) is the practical networking architecture used for communication across the Internet and most modern computer networks.

Unlike the seven-layer OSI model, the commonly used TCP/IP model has four layers.

### Four layers of TCP/IP

| TCP/IP Layer | Responsibility | Protocols |
|---|---|---|
| Application | Application-level communication | HTTP, HTTPS, DNS, SMTP |
| Transport | Process-to-process communication | TCP, UDP |
| Internet | Logical addressing and routing | IPv4, IPv6, ICMP |
| Network Access | Local network communication and transmission | Ethernet, Wi-Fi |

### TCP/IP architecture

```text
+----------------------------+
|       Application          |
|   HTTP, HTTPS, DNS, SMTP   |
+----------------------------+
|        Transport           |
|          TCP, UDP          |
+----------------------------+
|         Internet           |
|          IP, ICMP           |
+----------------------------+
|       Network Access       |
|       Ethernet, Wi-Fi      |
+----------------------------+
```

### OSI vs TCP/IP

| OSI | TCP/IP |
|---|---|
| Seven layers | Four commonly described layers |
| Reference model | Practical protocol architecture |
| Separates session and presentation | Combines these into application layer |
| Transport layer | Transport layer |
| Network layer | Internet layer |
| Data Link + Physical | Network Access |

### Example

Suppose a Spring Boot application sends a request to another service.

```text
Spring Boot Application
        |
        v
HTTP Request
        |
        v
TCP Connection
        |
        v
IP Routing
        |
        v
Ethernet / Wi-Fi
        |
        v
Destination Server
```

Each layer contributes to delivering the request.

### Interview questions

1. What is the TCP/IP model?
2. How many layers does TCP/IP have?
3. Explain OSI vs TCP/IP.
4. Where do HTTP, TCP and IP belong?

---

## 3. HTTP and HTTPS

### HTTP – HyperText Transfer Protocol

HTTP is an application-layer protocol used for communication between clients and servers.

It follows a request-response communication model.

### Example

A browser requests user information:

```http
GET /api/users/101 HTTP/1.1
Host: api.example.com
Accept: application/json
```

Server response:

```http
HTTP/1.1 200 OK
Content-Type: application/json

{
  "id": 101,
  "name": "Naren",
  "role": "Developer"
}
```

### How HTTP works

```text
       CLIENT
         |
         | HTTP Request
         v
      SERVER
         |
         | Process Request
         |
         v
      RESPONSE
         |
         v
       CLIENT
```

### Important HTTP characteristics

1. HTTP is application-layer protocol.
2. It follows a request-response model.
3. HTTP is generally stateless.
4. It uses methods such as GET, POST, PUT and DELETE.
5. It supports headers, body and status codes.
6. HTTP/1.1 and HTTP/2 commonly use TCP; HTTP/3 uses QUIC over UDP.

### What is HTTPS?

HTTPS is HTTP communication protected using TLS (Transport Layer Security).

It provides:

- Encryption.
- Authentication of the server.
- Integrity protection.

### HTTP vs HTTPS

| HTTP | HTTPS |
|---|---|
| Unencrypted application data | TLS-protected application data |
| Commonly port 80 | Commonly port 443 |
| No TLS authentication | Server authentication using certificates |
| Data can be read or modified by network attackers | Data confidentiality and integrity are protected by TLS |

### Example

HTTP:

```text
http://example.com
```

HTTPS:

```text
https://example.com
```

### How HTTPS works

```text
Browser
   |
   | 1. Connect to server
   v
Server
   |
   | 2. TLS handshake
   v
Certificate Validation
   |
   | 3. Secure session established
   v
Encrypted HTTP Communication
```

### Real-world Java example

```java
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users")
    public String getUsers() {
        return "Users retrieved";
    }
}
```

When this endpoint is exposed through HTTPS, TLS is generally configured at the application server, reverse proxy, ingress controller or load balancer.

### Interview questions

1. What is HTTP?
2. What is HTTPS?
3. Why do we need TLS?
4. What is the difference between encryption and authentication?
5. Why is HTTPS important for REST APIs?

---

## 4. HTTP Methods and Status Codes

### HTTP Methods

HTTP methods describe the intended action to be performed on a resource.

| Method | Purpose | Safe? | Idempotent? |
|---|---|---|---|
| GET | Retrieve data | Yes | Yes |
| POST | Create/process resource | No | Not necessarily |
| PUT | Replace resource | No | Yes |
| PATCH | Partially update resource | No | Not necessarily |
| DELETE | Delete resource | No | Yes |
| HEAD | Retrieve headers only | Yes | Yes |
| OPTIONS | Discover communication options | Yes | Yes |

### 1. GET

Used to retrieve information.

Example:

```http
GET /api/users/101
```

Response:

```json
{
  "id": 101,
  "name": "Naren"
}
```

GET should not modify the server's business state.

### 2. POST

Used to create a resource or submit an operation.

Example:

```http
POST /api/users
Content-Type: application/json

{
  "name": "Naren",
  "email": "naren@example.com"
}
```

Response:

```http
201 Created
```

### 3. PUT

Used to replace the target resource representation.

```http
PUT /api/users/101
Content-Type: application/json

{
  "name": "Naren Updated",
  "email": "updated@example.com"
}
```

PUT is idempotent by HTTP semantics.

### 4. PATCH

Used to partially update a resource.

```http
PATCH /api/users/101
Content-Type: application/json

{
  "name": "Updated Name"
}
```

PATCH is not automatically idempotent. Its behavior depends on the patch operation.

### 5. DELETE

Used to delete a resource.

```http
DELETE /api/users/101
```

DELETE is idempotent by intended effect, even if repeated calls return different status codes.

### What is idempotency?

An operation is idempotent when performing it multiple times has the same intended effect as performing it once.

Example:

```text
PUT /users/101

Set name = Naren

First call:
Name = Naren

Second call:
Name = Naren

Third call:
Name = Naren
```

The final resource state remains the same.

POST is not inherently idempotent.

For payment APIs, an idempotency key can help prevent duplicate charges when clients retry requests.

Example:

```http
POST /api/payments
Idempotency-Key: payment-12345
```

The server stores the key and associates it with the original operation result.

### HTTP Status Codes

Status codes communicate the result of an HTTP request.

#### 1xx – Informational

| Code | Meaning |
|---|---|
| 100 | Continue |
| 101 | Switching Protocols |

#### 2xx – Success

| Code | Meaning |
|---|---|
| 200 | OK |
| 201 | Created |
| 202 | Accepted |
| 204 | No Content |

Examples:

- 200: User details retrieved.
- 201: New user created.
- 202: Background processing accepted.
- 204: Successful deletion with no response body.

#### 3xx – Redirection

| Code | Meaning |
|---|---|
| 301 | Moved Permanently |
| 302 | Found |
| 304 | Not Modified |
| 307 | Temporary Redirect |
| 308 | Permanent Redirect |

#### 4xx – Client Errors

| Code | Meaning |
|---|---|
| 400 | Bad Request |
| 401 | Unauthorized / Authentication Required |
| 403 | Forbidden |
| 404 | Not Found |
| 405 | Method Not Allowed |
| 408 | Request Timeout |
| 409 | Conflict |
| 413 | Content Too Large |
| 415 | Unsupported Media Type |
| 422 | Unprocessable Content |
| 429 | Too Many Requests |

Important distinction:

- 401: The request lacks valid authentication credentials.
- 403: The server understands the request but refuses authorization.
- 404: Resource not found.
- 409: Request conflicts with current resource state.
- 429: Client exceeded a rate limit.

#### 5xx – Server Errors

| Code | Meaning |
|---|---|
| 500 | Internal Server Error |
| 501 | Not Implemented |
| 502 | Bad Gateway |
| 503 | Service Unavailable |
| 504 | Gateway Timeout |

### Example: Microservice communication

Suppose Order Service calls Payment Service.

If Payment Service is unavailable:

```http
HTTP/1.1 503 Service Unavailable
```

If Order Service receives an invalid response from an upstream server:

```http
HTTP/1.1 502 Bad Gateway
```

If the upstream service does not respond within the configured gateway timeout:

```http
HTTP/1.1 504 Gateway Timeout
```

### Interview questions

1. Difference between PUT and PATCH?
2. What is idempotency?
3. Is DELETE idempotent?
4. Difference between 401 and 403?
5. Difference between 502, 503 and 504?
6. Why do payment APIs need idempotency keys?

---

## 5. HTTP/1.1 vs HTTP/2 vs HTTP/3

These are different versions of HTTP with improvements in communication efficiency and performance.

### HTTP/1.1

Introduced persistent connections and widely adopted HTTP communication.

Characteristics:

- Text-based protocol.
- Supports persistent TCP connections.
- Supports keep-alive.
- Requests on a connection are generally processed sequentially by default.
- Multiple connections may be opened to improve concurrency.
- Head-of-line blocking can occur within a connection.

Example:

```text
Client
  |
  | Request 1
  v
Server
  |
  | Response 1
  v
Client
  |
  | Request 2
  v
Server
```

### HTTP/2

Introduced significant improvements over HTTP/1.1.

Important features:

**1. Binary Framing**

HTTP/2 uses binary frames instead of HTTP/1.1's textual message framing.

**2. Multiplexing**

Multiple request/response streams can share one TCP connection.

```text
              TCP Connection
                    |
        +-----------+-----------+
        |           |           |
        v           v           v
     Stream 1    Stream 2    Stream 3
     Request A   Request B   Request C
```

**3. Header Compression**

Uses HPACK to reduce repeated HTTP header overhead.

**4. Stream Prioritization**

Allows clients to express preferences about stream processing, although modern implementations do not all use the original prioritization mechanism.

**Limitation:** HTTP/2 still runs over TCP in common deployments. Packet loss can block delivery of data across multiple streams on that TCP connection.

### HTTP/3

HTTP/3 uses QUIC instead of TCP.

QUIC operates over UDP and integrates TLS 1.3 security.

Important features:

- Independent streams.
- Reduced cross-stream head-of-line blocking.
- Faster connection establishment in suitable scenarios.
- Connection migration.
- TLS 1.3 integration.

### HTTP version comparison

| Feature | HTTP/1.1 | HTTP/2 | HTTP/3 |
|---|---|---|---|
| Transport | TCP | TCP | QUIC over UDP |
| Format | Text-based | Binary framing | Binary framing |
| Multiplexing | No native multiplexing | Yes | Yes |
| Header compression | None built into HTTP | HPACK | QPACK |
| TLS | Optional | Commonly used | Required through QUIC |
| Packet-loss behavior | TCP connection affected | TCP head-of-line blocking | Independent streams reduce cross-stream blocking |
| Connection migration | No native support | No native support | Supported by QUIC |

### Real-world example

A website loads:

- HTML.
- CSS.
- JavaScript.
- Images.
- API responses.

With HTTP/2 and HTTP/3, multiple resources can be transferred efficiently without opening a separate connection for every request.

### Interview questions

1. What is HTTP/2 multiplexing?
2. Why is HTTP/2 faster than HTTP/1.1 in many scenarios?
3. What is head-of-line blocking?
4. Why does HTTP/3 use QUIC?
5. What is the difference between HPACK and QPACK?

---

## 6. TCP vs UDP

### TCP – Transmission Control Protocol

TCP is a connection-oriented transport protocol that provides reliable, ordered byte-stream delivery.

Characteristics:

- Connection-oriented.
- Reliable delivery.
- Ordered data.
- Acknowledgments.
- Retransmission.
- Flow control.
- Congestion control.

Example: HTTPS over HTTP/1.1 or HTTP/2 commonly uses TCP.

### UDP – User Datagram Protocol

UDP is a connectionless transport protocol that sends datagrams without providing built-in delivery guarantees or ordering.

Characteristics:

- Connectionless.
- No built-in delivery acknowledgment.
- No built-in retransmission.
- No built-in ordering guarantee.
- Lower protocol overhead.
- Supports multicast and broadcast in applicable network environments.

Example: DNS queries commonly use UDP, and QUIC uses UDP as its underlying transport.

### TCP vs UDP comparison

| Feature | TCP | UDP |
|---|---|---|
| Connection | Connection-oriented | Connectionless |
| Reliability | Built-in reliable delivery | No built-in reliability |
| Ordering | Ordered byte stream | No ordering guarantee |
| Acknowledgment | Yes | No |
| Retransmission | Yes | No |
| Flow control | Yes | No |
| Congestion control | Yes | No built-in transport-level mechanism |
| Overhead | Higher | Lower |
| Common usage | HTTPS, SSH, database connections | DNS, streaming, gaming, QUIC |

### TCP communication

<LearningViz type_id="TCP_VS_UDP" initial_values={{protocol:"tcp",lossMode:"drop_packet_3"}}/>

TCP ensures missing data can be detected and retransmitted.

### UDP communication

```text
Sender                 Receiver

Packet 1  ----------->  Packet 1
Packet 2  ----------->  Packet 2
Packet 3  ----X         Lost
Packet 4  ----------->  Packet 4
Packet 5  ----------->  Packet 5
```

UDP does not automatically retransmit packet 3.

### When to use TCP?

- Banking transactions.
- REST APIs.
- Database connections.
- File transfer.
- Email.
- Reliable messaging.

### When to use UDP?

- Real-time gaming.
- Voice calls.
- Video communication.
- DNS.
- QUIC-based communication.

**Important:** UDP is not automatically faster in every application. Application requirements, network conditions and protocol design determine performance.

### Interview questions

1. Difference between TCP and UDP?
2. Why does HTTP/3 use UDP?
3. Why do banking systems prefer reliable transport?
4. Does UDP guarantee packet delivery?
5. What is TCP congestion control?

---

## 7. TCP Three-Way Handshake

### Definition

The TCP three-way handshake is the process used to establish a TCP connection between a client and a server.

It synchronizes initial sequence numbers and establishes connection state.

### Three steps

**Step 1 – SYN**

The client sends a SYN packet to the server.

```text
Client ----------------> Server

          SYN
```

The client requests a TCP connection.

**Step 2 – SYN-ACK**

The server responds with SYN-ACK.

```text
Client <---------------- Server

        SYN + ACK
```

The server acknowledges the client's SYN and sends its own SYN.

**Step 3 – ACK**

The client sends ACK.

```text
Client ----------------> Server

          ACK
```

The TCP connection is established.

### Complete diagram

```text
CLIENT                         SERVER

   |                              |
   | -------- SYN --------------> |
   |                              |
   | <------ SYN + ACK ---------- |
   |                              |
   | -------- ACK --------------> |
   |                              |
   |       CONNECTION             |
   |        ESTABLISHED           |
```

### Why three steps?

- Client announces its initial sequence number.
- Server acknowledges it and announces its own sequence number.
- Client acknowledges the server's sequence number.

This establishes that both endpoints can send and receive TCP segments.

### Example

A browser opens a TCP connection to a Spring Boot application on port 443.

The TCP handshake occurs before TLS negotiation when using HTTPS over TCP.

### TCP connection termination

TCP commonly uses a four-segment FIN/ACK exchange to close a connection gracefully.

Simplified:

```text
Client                 Server

  | ------ FIN -------> |
  | <----- ACK -------- |
  | <----- FIN -------- |
  | ------ ACK -------> |
```

### Interview questions

1. Explain TCP three-way handshake.
2. Why do we need three steps?
3. What happens if SYN-ACK is lost?
4. What is the difference between SYN and ACK?
5. Explain TCP connection termination.

---

## 8. TLS Handshake

### Definition

TLS (Transport Layer Security) is a cryptographic protocol that protects communication between clients and servers.

The TLS handshake establishes security parameters and authenticated encryption keys.

### Why do we need TLS?

Without TLS, network attackers may be able to read or modify application data in transit.

TLS provides:

1. Confidentiality – Data is encrypted.
2. Integrity – Data modification is detectable.
3. Authentication – The client can verify the server's identity using certificates.

### TLS handshake – Simplified TLS 1.3 flow

```text
CLIENT                         SERVER

   |                              |
   | ------ ClientHello --------> |
   |                              |
   | <------ ServerHello -------- |
   | <------ Encrypted Extensions |
   | <------ Certificate -------- |
   | <------ Certificate Verify - |
   | <------ Finished ------------|
   |                              |
   | ------ Finished -----------> |
   |                              |
   |    SECURE COMMUNICATION      |
```

### Step-by-step explanation

**Step 1 – ClientHello**

The client sends:

- Supported TLS versions.
- Supported cipher suites.
- Key share.
- Random values and extensions.

**Step 2 – ServerHello**

The server selects compatible security parameters and sends its key share.

**Step 3 – Certificate**

The server provides its digital certificate so the client can authenticate the server.

**Step 4 – Certificate Validation**

The client validates:

- Certificate chain.
- Validity period.
- Hostname.
- Trust chain.
- Relevant certificate constraints.

**Step 5 – Key Establishment**

The client and server derive shared traffic encryption keys using the negotiated key exchange.

**Step 6 – Finished Messages**

Both sides verify handshake integrity and establish the secure connection.

### What is a digital certificate?

A digital certificate binds a public key to an identity, such as a domain name.

Certificates are generally issued by trusted Certificate Authorities (CAs).

### Symmetric vs Asymmetric Encryption

| Asymmetric Encryption | Symmetric Encryption |
|---|---|
| Uses public/private key pair | Uses shared secret key |
| Useful for authentication and key establishment | Efficient for bulk data encryption |
| More computationally expensive | Generally faster |
| Example: RSA signatures, ECDSA signatures | Example: AES-GCM, ChaCha20-Poly1305 |

Modern TLS commonly uses ephemeral key exchange and symmetric authenticated encryption for application traffic.

### TCP handshake vs TLS handshake

| TCP Handshake | TLS Handshake |
|---|---|
| Establishes transport connection | Establishes secure communication |
| SYN, SYN-ACK, ACK | ClientHello, ServerHello and authentication/key establishment |
| TCP layer | Security protocol above transport |
| Establishes TCP connection | Negotiates encryption and authentication |

### Interview questions

1. What is TLS?
2. What is the difference between TLS and SSL?
3. Explain the TLS handshake.
4. What is a certificate authority?
5. What is the difference between symmetric and asymmetric encryption?
6. How does HTTPS protect communication?

---

## 9. DNS Resolution

### Definition

DNS (Domain Name System) translates human-readable domain names into IP addresses.

Computers use IP addresses to communicate across networks.

Humans prefer domain names.

Example:

```text
www.google.com
        |
        v
DNS Resolution
        |
        v
142.x.x.x (Illustrative)
```

### Why do we need DNS?

Without DNS, users would need to remember IP addresses for every website and service.

### DNS resolution process

```text
          Browser
             |
             v
      Browser DNS Cache
             |
             v
      OS DNS Cache
             |
             v
      Recursive Resolver
             |
             v
       Root DNS Server
             |
             v
       TLD DNS Server
             |
             v
 Authoritative DNS Server
             |
             v
       IP Address
             |
             v
          Browser
```

### Step-by-step explanation

**Step 1 – Browser Cache**

The browser checks whether it already knows the IP address.

**Step 2 – Operating System Cache**

If not found, the operating system's resolver checks its cache.

**Step 3 – Recursive Resolver**

The request is sent to a DNS recursive resolver, commonly provided by an ISP or public DNS service.

**Step 4 – Root DNS Server**

The resolver asks a root server where to find the relevant top-level domain servers.

**Step 5 – TLD Server**

The TLD server directs the resolver toward the authoritative name server.

Example TLDs:

- .com
- .org
- .in

**Step 6 – Authoritative DNS Server**

The authoritative server returns the relevant DNS record.

**Step 7 – IP Address Returned**

The resolver caches the answer according to its TTL and returns it to the client.

### Important DNS records

| Record | Purpose | Example |
|---|---|---|
| A | Maps domain to IPv4 | example.com → IPv4 |
| AAAA | Maps domain to IPv6 | example.com → IPv6 |
| CNAME | Alias to another domain | www → example.com |
| MX | Mail server information | mail.example.com |
| NS | Authoritative name servers | ns1.example.com |
| TXT | Text information | Domain verification |
| PTR | Reverse DNS lookup | IP → domain |
| SRV | Service location and port | Service discovery |

### What is TTL?

TTL (Time To Live) determines how long a DNS record can remain cached.

Example:

```text
DNS record:
api.example.com -> 10.0.0.10

TTL = 300 seconds
```

The cached answer can be reused for approximately 300 seconds, subject to resolver behavior.

### Real-world example

A Spring Boot microservice calls:

```text
https://payment-service.example.com
```

DNS resolves the service hostname to an IP address.

The client then establishes the required transport connection.

### Interview questions

1. What is DNS?
2. Explain DNS resolution step by step.
3. What is a DNS recursive resolver?
4. What is an authoritative DNS server?
5. What is DNS TTL?
6. Difference between A, AAAA and CNAME?
7. What happens if DNS is unavailable?

---

## 10. IP Addresses

### Definition

An IP address is a logical network address used to identify an interface and route packets across IP networks.

There are two main IP versions:

- IPv4.
- IPv6.

### IPv4

IPv4 uses 32-bit addresses.

Example:

```text
192.168.1.10
```

It provides approximately 4.3 billion possible address values.

### IPv6

IPv6 uses 128-bit addresses.

Example:

```text
2001:db8::1
```

IPv6 provides a vastly larger address space.

### IPv4 vs IPv6

| Feature | IPv4 | IPv6 |
|---|---|---|
| Address size | 32 bits | 128 bits |
| Representation | Decimal | Hexadecimal |
| Example | 192.168.1.10 | 2001:db8::1 |
| Address space | Smaller | Extremely large |
| Broadcast | Supported | No broadcast; multicast is used |
| NAT | Commonly used | Generally less necessary |

### Public vs Private IP

**Public IP**

An address that can be routed on the public Internet.

Example: A cloud server's public-facing IP.

**Private IP**

An address used within private networks.

Common private IPv4 ranges:

| Range | CIDR |
|---|---|
| 10.0.0.0 – 10.255.255.255 | 10.0.0.0/8 |
| 172.16.0.0 – 172.31.255.255 | 172.16.0.0/12 |
| 192.168.0.0 – 192.168.255.255 | 192.168.0.0/16 |

Example:

A laptop may have:

```text
Private IP: 192.168.1.10
```

Its router may use a public IP to communicate with the Internet.

### Static vs Dynamic IP

| Static IP | Dynamic IP |
|---|---|
| Remains assigned until changed | Can change over time |
| Useful for stable endpoints | Common for client devices |
| Manually or administratively configured | Commonly assigned through DHCP |

### What is NAT?

Network Address Translation modifies IP address information in packet headers as traffic passes through a router or gateway.

Example:

```text
Laptop:
192.168.1.10
       |
       v
     Router
       |
       v
Public IP:
203.0.113.10
       |
       v
    Internet
```

A home router commonly uses NAT to allow multiple private-network devices to share a public IPv4 address.

### Interview questions

1. What is an IP address?
2. Difference between IPv4 and IPv6?
3. Public vs private IP?
4. What is NAT?
5. What is DHCP?
6. What is CIDR notation?

---

## 11. Ports

### Definition

A port is a logical endpoint used by transport protocols such as TCP and UDP to identify the application or service communicating on a device.

An IP address identifies the network interface or destination host.

A port helps identify the intended service or process.

### Example

```text
IP Address: 192.168.1.10
Port: 8080

Application:
Spring Boot
```

The application can be accessed using:

```text
http://192.168.1.10:8080
```

### Common port numbers

| Port | Protocol / Service |
|---:|---|
| 20, 21 | FTP |
| 22 | SSH |
| 25 | SMTP |
| 53 | DNS |
| 80 | HTTP |
| 110 | POP3 |
| 123 | NTP |
| 143 | IMAP |
| 443 | HTTPS |
| 3306 | MySQL |
| 5432 | PostgreSQL |
| 5672 | RabbitMQ |
| 6379 | Redis |
| 8080 | Common Spring Boot HTTP port |
| 9092 | Common Kafka broker port |

These are conventional/default ports; deployments can configure different ports.

### Port ranges

| Range | Category |
|---|---|
| 0–1023 | Well-known ports |
| 1024–49151 | Registered ports |
| 49152–65535 | Dynamic/private ports |

### Example: Spring Boot

In `application.properties`:

```properties
server.port=8080
```

Spring Boot starts its embedded web server on port 8080.

### IP vs Port

```text
192.168.1.10:8080
      |        |
      IP      Port
```

IP identifies the destination host/interface.

Port identifies the destination service endpoint.

### Interview questions

1. What is a port?
2. Difference between IP and port?
3. What is port 443?
4. What happens if two applications bind to the same IP and port?
5. What is an ephemeral port?

---

# PART 2: PROXIES AND LOAD BALANCING

## 12. Reverse Proxy

### Definition

A reverse proxy is a server that sits in front of backend servers and receives client requests on their behalf.

It forwards requests to appropriate backend servers and returns responses to clients.

The client communicates with the reverse proxy instead of directly communicating with individual backend servers.

### Architecture

```text
             CLIENTS
                |
                v
          REVERSE PROXY
                |
         +------+------+
         |      |      |
         v      v      v
       App 1  App 2  App 3
```

### Why do we need a reverse proxy?

1. Hide backend infrastructure.
2. Route requests.
3. Terminate TLS.
4. Distribute traffic.
5. Compress responses.
6. Cache responses.
7. Apply security rules.
8. Handle request filtering.

### Example: NGINX

Suppose Spring Boot runs on:

```text
localhost:8080
```

NGINX receives public traffic on port 443 and forwards it to Spring Boot.

Simplified configuration:

```nginx
server {
    listen 443 ssl;

    location /api/ {
        proxy_pass http://localhost:8080;
    }
}
```

Production configuration also needs certificate configuration, appropriate headers, timeouts and upstream health handling.

### Reverse proxy vs Load Balancer

A reverse proxy is an intermediary for backend servers.

A load balancer distributes traffic across multiple backend instances.

A reverse proxy can also perform load balancing.

### Real-world examples

- NGINX.
- HAProxy.
- Envoy.
- Cloud application gateways.

### Interview questions

1. What is a reverse proxy?
2. Why do we use NGINX?
3. Can a reverse proxy perform load balancing?
4. What is TLS termination?
5. Reverse proxy vs API Gateway?

---

## 13. Forward Proxy

### Definition

A forward proxy sits between clients and external servers.

It makes outbound requests on behalf of clients.

The external destination sees the proxy as the immediate network client rather than directly seeing the original client connection.

### Architecture

```text
         CLIENTS
            |
            v
       FORWARD PROXY
            |
            v
      EXTERNAL SERVER
```

### Why do we need a forward proxy?

1. Control outbound Internet access.
2. Apply company security policies.
3. Filter websites.
4. Monitor outbound traffic.
5. Cache certain responses.
6. Hide internal client IP addresses from destination servers.

### Example

In a corporate environment, employees may need to access external websites through an enterprise proxy.

```text
Employee Laptop
       |
       v
Corporate Proxy
       |
       v
External Website
```

The proxy can enforce company policies.

### Forward Proxy vs Reverse Proxy

| Forward Proxy | Reverse Proxy |
|---|---|
| Represents clients | Represents backend servers |
| Sits in front of clients | Sits in front of servers |
| Controls outbound traffic | Manages inbound traffic |
| Protects client identity/network | Hides backend infrastructure |
| Example: Corporate proxy | Example: NGINX |

### Interview questions

1. What is a forward proxy?
2. What is the difference between forward and reverse proxy?
3. Why do enterprises use forward proxies?

---

## 14. Load Balancers

### Definition

A load balancer distributes incoming network traffic across multiple backend servers or service instances.

Its primary purpose is to improve scalability, availability and resource utilization.

### Problem without load balancing

Suppose one Spring Boot instance receives 100,000 requests.

It may become overloaded.

```text
           100,000 Users
                 |
                 v
            Spring Boot
               App 1
                 |
                 v
              Database
```

This can cause:
- High CPU usage.
- Memory pressure.
- Thread exhaustion.
- Increased latency.
- Application crashes.

### Solution: Load Balancer

```text
            100,000 Users
                  |
                  v
             Load Balancer
                  |
         +--------+--------+
         |        |        |
         v        v        v
       App 1    App 2    App 3
```

Traffic is distributed across multiple instances.

### Load balancing algorithms

**1. Round Robin**

Distributes requests sequentially.

```text
Request 1 -> Server 1
Request 2 -> Server 2
Request 3 -> Server 3
Request 4 -> Server 1
```

**2. Weighted Round Robin**

Servers receive traffic according to configured weights.

Example:

```text
Server 1: Weight 5
Server 2: Weight 3
Server 3: Weight 2
```

Server 1 receives a larger share of traffic.

**3. Least Connections**

Routes traffic to the server with the fewest active connections.

Useful when request processing times vary.

**4. Weighted Least Connections**

Considers both active connections and server capacity.

**5. IP Hash**

Uses the client IP address to select a backend.

Can provide a degree of connection affinity, but it does not guarantee that application sessions remain available after backend changes.

**6. Random**

Selects a backend randomly.

### Health checks

A load balancer periodically checks whether backend servers are healthy.

Example:

```text
Server 1 -> HEALTHY
Server 2 -> UNHEALTHY
Server 3 -> HEALTHY
```

New traffic is routed to healthy instances.

### Health check endpoint in Spring Boot

Using Spring Boot Actuator:

```properties
management.endpoints.web.exposure.include=health
```

Example endpoint:

```http
GET /actuator/health
```

The load balancer can use this endpoint to determine application health.

### Types of load balancers

| Type | Description |
|---|---|
| Hardware | Dedicated physical load balancer |
| Software | NGINX, HAProxy |
| Cloud-managed | AWS ALB/NLB, Azure Load Balancer |
| DNS-based | Distributes clients across IP addresses using DNS |

### Interview questions

1. What is a load balancer?
2. Why do we need load balancing?
3. Explain Round Robin.
4. What is least connections?
5. What happens when one server fails?
6. What are health checks?
7. How do you make a load balancer highly available?

---

## 15. L4 vs L7 Load Balancing

Load balancing can happen at different networking layers.

### L4 Load Balancing – Transport Layer

Layer 4 load balancing operates using transport-level information.

It commonly considers:

- Source IP.
- Destination IP.
- Source port.
- Destination port.
- TCP/UDP connection information.

It does not need to inspect the HTTP URL or application payload to make basic routing decisions.

### Architecture

```text
Client
  |
  v
L4 Load Balancer
  |
  +----------+----------+
  |          |          |
  v          v          v
Server 1  Server 2  Server 3
```

Example:

Route incoming TCP traffic on port 443 to a backend server.

### L7 Load Balancing – Application Layer

Layer 7 load balancing understands application-level protocols such as HTTP.

It can route requests based on:

- URL path.
- Hostname.
- HTTP headers.
- Cookies.
- HTTP method.

### Example

```text
api.example.com/users
        |
        v
    L7 Load Balancer
        |
        +------------------+
        |                  |
        v                  v
    User Service       Order Service
```

Example routing:

```text
/users/*  -> User Service
/orders/* -> Order Service
/payments/* -> Payment Service
```

### Comparison

| Feature | L4 | L7 |
|---|---|---|
| OSI layer | Transport | Application |
| Protocol information | TCP/UDP | HTTP/HTTPS and application protocols |
| Routing | IP and port | URL, host, headers, etc. |
| Content inspection | No application payload inspection for basic routing | Yes |
| Performance overhead | Generally lower | Generally higher |
| Flexibility | Lower | Higher |
| Example | Network Load Balancer | Application Load Balancer |

### Real-world example

An e-commerce application:

- L4 balances TCP connections across backend nodes.
- L7 routes `/products` to Product Service and `/orders` to Order Service.

### Interview questions

1. What is L4 load balancing?
2. What is L7 load balancing?
3. Difference between L4 and L7?
4. Can L7 load balancing route using HTTP headers?
5. Which one is suitable for path-based routing?

---

# PART 3: REAL-TIME COMMUNICATION

## 16. WebSockets

### Definition

WebSocket is a communication protocol that provides full-duplex communication over a single connection.

Full-duplex means both client and server can send messages independently.

Unlike ordinary HTTP request-response communication, WebSockets maintain a persistent connection.

### Why do we need WebSockets?

Consider a chat application.

With normal HTTP:

```text
Client sends request
        |
        v
Server responds
```

To receive new messages, the client must keep making requests or use another communication technique.

With WebSockets:

```text
CLIENT <=================> SERVER

       Persistent Connection

Client can send messages
Server can send messages
```

### WebSocket handshake

WebSockets typically begin with an HTTP handshake that upgrades the connection to the WebSocket protocol.

Example:

```http
GET /chat HTTP/1.1
Host: example.com
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Key: ...
Sec-WebSocket-Version: 13
```

Successful response:

```http
HTTP/1.1 101 Switching Protocols
Upgrade: websocket
Connection: Upgrade
```

After the upgrade, communication happens using WebSocket frames.

### WebSocket communication

```text
          CLIENT
             |
             | Connect
             v
          SERVER
             |
             | WebSocket Established
             |
       +-----+-----+
       |           |
       v           v
    Message     Message
    Client      Server
       |           |
       +-----+-----+
             |
       Persistent Connection
```

### Real-world use cases

- Chat applications.
- Live stock prices.
- Multiplayer games.
- Collaborative editors.
- Real-time dashboards.
- Live notifications.

### Spring Boot example

WebSocket support can be configured using Spring WebSocket.

```java
@Configuration
@EnableWebSocket
public class WebSocketConfig
        implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(
            WebSocketHandlerRegistry registry) {

        registry.addHandler(
            new MyWebSocketHandler(),
            "/ws"
        ).setAllowedOrigins("https://example.com");
    }
}
```

For production, configure appropriate authentication, origin validation and message authorization.

### Important system design challenge

If a WebSocket connection is established with Server 1, subsequent messages on that connection stay associated with that connection.

When multiple backend instances exist, a shared messaging system such as Redis Pub/Sub or Kafka may be needed to distribute messages between instances.

### Interview questions

1. What are WebSockets?
2. What is full-duplex communication?
3. How does a WebSocket handshake work?
4. WebSockets vs HTTP?
5. How do you scale WebSocket applications?

---

## 17. Server-Sent Events (SSE)

### Definition

Server-Sent Events is a technology that allows a server to continuously send updates to a browser over a single HTTP connection.

It is one-way communication.

Server -> Client.

### Example

A stock market dashboard needs to display live stock prices.

The server continuously pushes price updates to the browser.

```text
       CLIENT
          |
          | HTTP Request
          v
        SERVER
          |
          | Price Update
          v
        CLIENT
          |
          | Price Update
          v
        CLIENT
```

The client does not need to repeatedly request every update.

### SSE communication

```text
Client ----------------> Server
       HTTP Request

Client <---------------- Server
       Event 1

Client <---------------- Server
       Event 2

Client <---------------- Server
       Event 3
```

### SSE response format

```http
Content-Type: text/event-stream
Cache-Control: no-cache
```

Example events:

```text
event: stock-price
id: 101
data: {"symbol":"AAPL","price":190.50}

event: stock-price
id: 102
data: {"symbol":"AAPL","price":191.20}
```

### Java Spring Boot example

```java
@GetMapping(
    value = "/events",
    produces = MediaType.TEXT_EVENT_STREAM_VALUE
)
public SseEmitter streamEvents() {

    SseEmitter emitter = new SseEmitter();

    try {
        emitter.send(
            SseEmitter.event()
                .name("notification")
                .data("New notification received")
        );
    } catch (IOException ex) {
        emitter.completeWithError(ex);
    }

    return emitter;
}
```

This example sends an event; a production streaming implementation must keep the emitter active and manage asynchronous updates, timeouts and cleanup.

### SSE vs WebSockets

| Feature | SSE | WebSocket |
|---|---|---|
| Communication | One-way server to client | Full-duplex |
| Underlying connection | HTTP streaming | WebSocket |
| Server pushes data | Yes | Yes |
| Client sends messages | Normal HTTP requests separately | Through same connection |
| Reconnection | Built-in browser EventSource behavior | Application usually implements reconnection |
| Data format | UTF-8 text events | Text or binary frames |
| Use case | Live notifications | Chat application |

### Interview questions

1. What is SSE?
2. Is SSE bidirectional?
3. How is SSE different from WebSockets?
4. What is the Content-Type for SSE?
5. When would you choose SSE?

---

## 18. Long Polling

### Definition

Long polling is a communication technique in which the client sends an HTTP request and the server holds that request open until new data becomes available or a timeout occurs.

After receiving a response, the client sends another request.

### Normal polling

The client sends requests at fixed intervals.

```text
Client -> Server: Any updates?
Server -> Client: No

Wait 5 seconds

Client -> Server: Any updates?
Server -> Client: No

Wait 5 seconds

Client -> Server: Any updates?
Server -> Client: New message
```

This can generate unnecessary requests.

### Long polling

```text
Client -> Server: Any updates?

Server waits...

New event occurs

Server -> Client: New message

Client -> Server: Wait for next event
```

### Example

A notification application wants to notify users when a new message arrives.

The client sends:

```http
GET /api/notifications
```

The server holds the request until a notification arrives or a timeout occurs.

### Comparison

| Normal Polling | Long Polling |
|---|---|
| Requests at fixed intervals | Request remains open |
| Frequent unnecessary requests | Fewer requests when events are infrequent |
| Simple implementation | More connection management |
| Periodic updates | Near-real-time updates |

### Long polling vs SSE vs WebSockets

| Feature | Long Polling | SSE | WebSocket |
|---|---|---|---|
| Communication | Repeated HTTP requests | One-way stream | Two-way |
| Connection | Recreated repeatedly | Persistent HTTP connection | Persistent WebSocket |
| Server push | Via held request | Native | Native |
| Client push | Via new HTTP request | Separate HTTP request | Same connection |
| Complexity | Moderate | Low to moderate | Higher |

### Interview questions

1. What is long polling?
2. How is long polling different from normal polling?
3. What is the difference between SSE and long polling?
4. When should WebSockets be preferred?

---

## 19. gRPC

### Definition

gRPC is a high-performance Remote Procedure Call (RPC) framework originally developed at Google.

It allows one service to call methods on another service as if it were invoking a local method, while the actual execution happens remotely.

It commonly uses:

- Protocol Buffers.
- HTTP/2.
- Generated client and server code.
- Strongly defined service contracts.

### Why do we need gRPC?

In microservice architectures, services frequently communicate with one another.

For example:

```text
Order Service
      |
      v
Inventory Service
      |
      v
Payment Service
```

gRPC can provide efficient service-to-service communication with compact binary serialization.

### What are Protocol Buffers?

Protocol Buffers (Protobuf) are a language-neutral mechanism for serializing structured data.

Example:

```protobuf
syntax = "proto3";

service UserService {
    rpc GetUser (UserRequest)
        returns (UserResponse);
}

message UserRequest {
    int32 user_id = 1;
}

message UserResponse {
    int32 user_id = 1;
    string name = 2;
    string email = 3;
}
```

### How gRPC works

```text
        SERVICE A
            |
            | gRPC Request
            v
       HTTP/2 Transport
            |
            v
        SERVICE B
            |
            | Execute Method
            v
       HTTP/2 Response
            |
            v
        SERVICE A
```

### gRPC communication types

**1. Unary RPC**

One request and one response.

```text
Client -> Request -> Server
Client <- Response <- Server
```

**2. Server Streaming**

One request and multiple responses.

```text
Client -> Request -> Server

Client <- Response 1
Client <- Response 2
Client <- Response 3
```

**3. Client Streaming**

Multiple client requests and one server response.

```text
Client -> Request 1
Client -> Request 2
Client -> Request 3

Server -> One Response
```

**4. Bidirectional Streaming**

Both client and server continuously exchange messages.

```text
Client <============> Server
       Stream
```

### gRPC advantages

- Efficient binary serialization.
- Strongly typed contracts.
- Code generation.
- Multiple streaming modes.
- Supports multiple programming languages.
- Good for internal microservice communication.

### gRPC disadvantages

- Browser support generally needs a compatible gateway or gRPC-Web.
- Harder to inspect manually than JSON APIs.
- Requires Protobuf schema management.
- HTTP/2 infrastructure and observability need proper configuration.

### Java example

A Java gRPC client can invoke a generated stub:

```java
UserServiceGrpc.UserServiceBlockingStub stub =
    UserServiceGrpc.newBlockingStub(channel);

UserResponse response =
    stub.getUser(
        UserRequest.newBuilder()
            .setUserId(101)
            .build()
    );
```

The generated stub handles serialization and remote invocation.

### Interview questions

1. What is gRPC?
2. What is Protocol Buffers?
3. Why does gRPC use HTTP/2?
4. Explain the four types of gRPC communication.
5. Why choose gRPC over REST?
6. What is gRPC-Web?

---

## 20. REST vs GraphQL vs gRPC

These are different approaches to API communication.

### REST

REST (Representational State Transfer) is an architectural style for building APIs around resources.

Example:

```http
GET /api/users/101
GET /api/orders/101
POST /api/orders
DELETE /api/orders/101
```

Characteristics:

- Resource-oriented.
- Commonly uses JSON.
- Uses standard HTTP methods.
- Stateless request semantics.
- Easy integration with browsers.

### GraphQL

GraphQL is a query language and runtime for APIs that allows clients to request specific fields and related data.

Example:

```graphql
query {
    user(id: 101) {
        name
        email
        orders {
            orderId
            total
        }
    }
}
```

The client specifies the data it needs.

Advantages:

- Flexible data selection.
- Reduces over-fetching.
- Can combine related data in one query.

Disadvantages:

- More complex caching.
- Query complexity management.
- Authorization at field and resolver levels.
- Requires protection against expensive queries.

### gRPC

gRPC is an RPC framework typically used for efficient communication between backend services.

Example:

```protobuf
rpc GetUser(UserRequest)
    returns (UserResponse);
```

### REST vs GraphQL vs gRPC

| Feature | REST | GraphQL | gRPC |
|---|---|---|---|
| Style | Resource-based | Query language | RPC |
| Data format | Commonly JSON | Commonly JSON | Protobuf |
| Transport | HTTP | Commonly HTTP | Commonly HTTP/2 |
| Client data selection | Defined by endpoint | Client selects fields | Defined by RPC contract |
| Streaming | Separate mechanisms | Subscriptions require additional transport | Built-in streaming |
| Browser support | Excellent | Excellent | Requires gRPC-Web/gateway for typical browsers |
| Best suited for | Public APIs | Flexible frontend data | Internal service communication |

### When to use which?

**Choose REST when:**
- Building public APIs.
- Integrating frontend applications.
- Using standard HTTP semantics.
- Simplicity and broad compatibility are important.

**Choose GraphQL when:**
- Frontend needs different combinations of data.
- Many related resources must be retrieved.
- Over-fetching and under-fetching are problems.

**Choose gRPC when:**
- Building internal microservices.
- Strong contracts are important.
- Efficient serialization matters.
- Streaming communication is required.

### Real-world architecture

```text
              FRONTEND
                  |
          +-------+-------+
          |               |
          v               v
        REST           GraphQL
          |               |
          +-------+-------+
                  |
                  v
             API Gateway
                  |
          +-------+-------+
          |               |
          v               v
      User Service    Order Service
          |               |
          +-------+-------+
                  |
                 gRPC
                  |
                  v
             Inventory Service
```

This is one possible architecture, not a mandatory arrangement.

### Interview questions

1. REST vs GraphQL?
2. REST vs gRPC?
3. Why is gRPC suitable for microservices?
4. What is over-fetching?
5. What is under-fetching?
6. Why is GraphQL caching more complex?
7. Can REST support streaming?

---

# PART 4: NETWORK PERFORMANCE AND INFRASTRUCTURE

## 21. CDN (Content Delivery Network)

### Definition

A CDN is a geographically distributed network of servers that delivers content to users from locations closer to them.

It improves content delivery performance and reduces load on the origin server.

### Why do we need CDN?

Imagine an application hosted in the USA.

A user in India requests an image.

Without CDN:

```text
User in India
      |
      v
Server in USA
      |
      v
Image returned
```

The request travels a long geographical distance.

With CDN:

```text
User in India
      |
      v
Nearby CDN Edge
      |
      v
Cached Image
```

The content can be served from a nearby edge location.

### How CDN works

1. User requests a resource.
2. DNS or CDN routing directs the request to a suitable edge.
3. Edge checks whether the content is cached.
4. Cache hit: content is returned.
5. Cache miss: edge retrieves content from the origin.
6. The content may be cached according to cache policy.

### CDN architecture

```text
             USERS
                |
                v
           CDN NETWORK
                |
        +-------+-------+
        |       |       |
        v       v       v
      Edge    Edge    Edge
      India   USA     Europe
        |
        v
    Origin Server
```

### What can CDN cache?

- Images.
- CSS.
- JavaScript.
- Videos.
- Fonts.
- Public static files.
- Some cacheable API responses.

### CDN advantages

- Lower latency.
- Reduced origin-server traffic.
- Better content availability.
- Improved performance during traffic spikes.
- Geographic distribution.

### Common CDN providers

- Cloudflare.
- Amazon CloudFront.
- Akamai.
- Google Cloud CDN.
- Azure Front Door/CDN offerings.

### Interview questions

1. What is a CDN?
2. Why do we need CDN?
3. What is a cache hit and cache miss?
4. What is an origin server?
5. How does CDN reduce latency?
6. Can APIs use a CDN?

---

## 22. Anycast

### Definition

Anycast is a network routing technique in which the same IP address is announced from multiple geographically distributed locations.

Network routing directs a client to an appropriate destination based on routing policies and network topology.

It often directs users to a nearby or favorable location, but not necessarily the geographically closest location.

### Example

Suppose a DNS provider has servers in:

- India.
- USA.
- Europe.

All locations advertise the same Anycast IP address.

```text
             ANYCAST IP
              203.x.x.x
                   |
         +---------+---------+
         |         |         |
         v         v         v
       India      USA      Europe
       Node       Node      Node
```

A user in India may be routed to the India location based on network routing.

### Benefits

1. Reduces network latency in many scenarios.
2. Improves geographic distribution.
3. Helps distribute traffic.
4. Supports resilience through routing changes.
5. Useful for DNS and global network services.

### Anycast vs Unicast vs Multicast

| Type | Meaning |
|---|---|
| Unicast | One sender to one destination |
| Broadcast | One sender to all devices in a broadcast domain |
| Multicast | One sender to a subscribed group |
| Anycast | One sender to an appropriate one of multiple destinations sharing an address |

### Anycast vs CDN

| Anycast | CDN |
|---|---|
| Network routing technique | Distributed content-delivery infrastructure |
| Routes to one of multiple advertised destinations | Delivers content from edge locations |
| Uses routing policies | Uses caching and request routing |
| Often used for DNS | Used for websites, media and cacheable APIs |

### Interview questions

1. What is Anycast?
2. How is Anycast different from Unicast?
3. Why do DNS providers use Anycast?
4. How is Anycast different from CDN?

---

## 23. Network Latency

### Definition

Network latency is the time taken for data to travel between two points in a network.

It is generally measured in milliseconds (ms).

### Example

A browser sends a request to a server in another country.

```text
Client
  |
  | Request
  v
Network
  |
  v
Server
  |
  | Response
  v
Client
```

The network delay contributes to the overall request response time.

### Types of latency

**1. Propagation Delay**

Time taken for a signal to travel through the physical medium.

**2. Transmission Delay**

Time required to place the packet's bits onto the network link.

**3. Processing Delay**

Time required by networking devices to process a packet.

**4. Queuing Delay**

Time a packet waits in a router, switch or other network queue.

### RTT – Round Trip Time

RTT is the time required for a signal to travel from source to destination and for the response to return.

Example:

```text
Request: 40 ms
Response: 40 ms

RTT = 80 ms
```

This is a simplified example assuming equal travel times.

### Factors affecting latency

- Geographical distance.
- Network congestion.
- Routing paths.
- DNS resolution.
- TLS handshake.
- TCP connection establishment.
- Server processing time.
- Database query time.
- External API calls.

### How to reduce latency?

1. Use CDN.
2. Deploy services closer to users.
3. Use caching.
4. Optimize database queries.
5. Reuse network connections.
6. Reduce unnecessary service-to-service calls.
7. Use asynchronous processing where suitable.
8. Reduce payload sizes.
9. Optimize DNS resolution.
10. Avoid unnecessary cross-region communication.

### Latency vs Response Time

| Latency | Response Time |
|---|---|
| Delay associated with an operation or communication | Total time from request to completed response |
| Can refer specifically to network delay | Includes network and processing delays |
| One component of overall response time | End-to-end user-visible duration |

### Interview questions

1. What is network latency?
2. What is RTT?
3. What factors affect network latency?
4. How do CDN and Redis improve response times?
5. Difference between latency and throughput?

---

## 24. Connection Pooling

### Definition

Connection pooling is a technique in which applications maintain a reusable collection of established connections instead of creating a new connection for every operation.

Establishing connections can be expensive.

Connection pooling reduces repeated connection creation overhead.

### Problem without connection pooling

Suppose a Spring Boot application receives 1,000 requests.

Each request creates a new database connection.

```text
Request 1 -> Create DB Connection -> Execute -> Close
Request 2 -> Create DB Connection -> Execute -> Close
Request 3 -> Create DB Connection -> Execute -> Close
```

This creates unnecessary overhead.

### Solution: Connection Pooling

```text
          Spring Boot
               |
               v
         Connection Pool
          /     |     \
         v      v      v
       Conn1  Conn2  Conn3
         |      |      |
         +------+------+
                |
                v
              MySQL
```

Connections are borrowed from the pool and returned after use.

### How it works

1. Application starts.
2. Pool creates a configured number of connections.
3. A request borrows an available connection.
4. SQL operation executes.
5. Connection is returned to the pool.
6. Another request can reuse it.

### Java Spring Boot example

Spring Boot commonly uses HikariCP as its JDBC connection pool.

Configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/app
spring.datasource.username=root
spring.datasource.password=password

spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
```

Do not use production credentials directly in source-controlled configuration files.

### Important connection pool settings

| Property | Purpose |
|---|---|
| maximumPoolSize | Maximum pool connections |
| minimumIdle | Minimum idle connections |
| connectionTimeout | Maximum time to wait for a connection |
| idleTimeout | Maximum idle time before eligible removal |
| maxLifetime | Maximum lifetime of a pooled connection |
| leakDetectionThreshold | Helps detect suspected connection leaks |

### How to choose pool size?

Do not blindly increase the connection pool size.

Consider:

- Database maximum connection limit.
- Number of application instances.
- Concurrent database workload.
- Query execution time.
- CPU and memory.
- Connection acquisition wait time.

Example:

If 5 application instances each have a maximum pool size of 20:

```text
Total potential DB connections =
5 * 20

= 100 connections
```

This must be considered when configuring database capacity.

### Connection pooling vs Thread pooling

| Connection Pooling | Thread Pooling |
|---|---|
| Reuses database/network connections | Reuses execution threads |
| Reduces connection creation overhead | Reduces thread creation overhead |
| Example: HikariCP | Example: Java ExecutorService |
| Manages connection resources | Manages execution resources |

### Interview questions

1. What is connection pooling?
2. Why does Spring Boot use HikariCP?
3. What happens when the pool is exhausted?
4. How do you identify connection leaks?
5. How do you calculate the maximum possible DB connections?

---

## 25. Keep-Alive

### Definition

Keep-alive allows an established network connection to be reused for multiple requests instead of creating a new connection for each request.

The exact behavior depends on the protocol and connection configuration.

### HTTP/1.1 Keep-Alive

HTTP/1.1 uses persistent connections by default unless the connection is closed.

Example:

```text
Client
  |
  | TCP Connection
  |
  +---- Request 1
  |
  +---- Response 1
  |
  +---- Request 2
  |
  +---- Response 2
  |
  +---- Request 3
  |
  +---- Response 3
```

The connection can be reused.

### Without Keep-Alive

```text
Request 1 -> New TCP Connection -> Response -> Close

Request 2 -> New TCP Connection -> Response -> Close

Request 3 -> New TCP Connection -> Response -> Close
```

### With Keep-Alive

```text
Establish TCP Connection

Request 1 -> Response 1
Request 2 -> Response 2
Request 3 -> Response 3

Reuse Connection
```

### Advantages

- Reduces repeated TCP handshakes.
- Reduces connection setup overhead.
- Improves performance.
- Reduces resource consumption.

### Keep-Alive vs Connection Pooling

| Keep-Alive | Connection Pooling |
|---|---|
| Keeps an established connection reusable | Manages multiple reusable connections |
| Connection-level behavior | Resource-management strategy |
| Common in HTTP | Common in HTTP clients and databases |
| Avoids repeated connection establishment | Allows multiple requests to borrow connections |

### Important distinction

HTTP/1.1 keep-alive and database connection pooling are related ideas but not identical.

HTTP/2 multiplexes streams over a persistent connection, while HTTP/3 uses QUIC connections and streams.

### Java example

Using Java's HttpClient:

```java
HttpClient client = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .build();

HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(
            "https://api.example.com/users"
        ))
        .GET()
        .build();

HttpResponse<String> response =
        client.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        );
```

The client can manage and reuse connections internally.

### Interview questions

1. What is keep-alive?
2. How does HTTP/1.1 keep-alive work?
3. What is the difference between keep-alive and connection pooling?
4. Why is connection reuse important?
5. How does HTTP/2 improve connection utilization?

---

## 26. Rate Limiting – Network and Application Levels

### Definition

Rate limiting controls how many requests a client or source can make within a specified time period.

It protects applications from excessive traffic, accidental request floods and certain denial-of-service patterns.

### Example

An API allows:

```text
100 requests per minute per client
```

If the client exceeds the limit, the server can return:

```http
HTTP/1.1 429 Too Many Requests
Retry-After: 30
```

### Why do we need rate limiting?

1. Prevent API abuse.
2. Protect backend services.
3. Control resource utilization.
4. Reduce excessive traffic.
5. Prevent brute-force attacks.
6. Protect expensive endpoints.
7. Maintain fair resource allocation.

### Network-level rate limiting

Applied at network infrastructure or network security layers.

Examples:

- Firewall rules.
- Router traffic policing.
- Bandwidth shaping.
- DDoS protection.
- Network gateway limits.

It may control traffic using:

- Source IP.
- Destination IP.
- Protocol.
- Port.
- Traffic volume.

### Application-level rate limiting

Applied at API gateways, reverse proxies or application services.

It can use:

- User ID.
- API key.
- Access token.
- Client IP.
- Endpoint.
- Tenant ID.

Example:

```text
User A -> 100 requests/minute
User B -> 100 requests/minute
User C -> 100 requests/minute
```

### Common rate-limiting algorithms

**1. Fixed Window Counter**

Divides time into fixed intervals and counts requests within each interval.

Example:

```text
10:00 - 10:01 -> 100 requests allowed
10:01 - 10:02 -> 100 requests allowed
```

Problem: Boundary bursts may allow more traffic over a short interval than expected.

**2. Sliding Window Log**

Stores request timestamps and counts requests within the moving time window.

It is accurate but may require more memory.

**3. Sliding Window Counter**

Combines fixed-window counters with weighted estimates from the previous window.

It provides a practical balance between accuracy and resource usage.

**4. Token Bucket**

Tokens are added to a bucket at a configured rate.

A request consumes one or more tokens.

If no token is available, the request is rejected or delayed.

Example:

```text
Bucket Capacity = 100 tokens

Refill Rate = 10 tokens/second

Request arrives
      |
      v
Token Available?
   /       \
 Yes        No
  |          |
  v          v
Allow      Reject/
Request    Delay
```

**5. Leaky Bucket**

Requests enter a bucket and are processed at a controlled rate.

This smooths traffic bursts.

### Comparison

| Algorithm | Main Idea | Advantage |
|---|---|---|
| Fixed Window | Count per time interval | Simple |
| Sliding Log | Store timestamps | Accurate |
| Sliding Counter | Estimate moving window | Efficient |
| Token Bucket | Refill tokens over time | Allows controlled bursts |
| Leaky Bucket | Process at fixed rate | Smooth traffic |

### Spring Boot implementation approach

You can implement application-level rate limiting using:

- Bucket4j.
- Redis.
- API Gateway.
- Spring Cloud Gateway filters.

Example architecture:

```text
          Client
             |
             v
        API Gateway
             |
             v
       Rate Limiter
             |
        +----+----+
        |         |
        v         v
      Redis     Backend
                   |
                   v
              Spring Boot
```

Redis can store counters or token state shared across multiple gateway instances.

### HTTP response headers

Commonly used headers include:

```http
HTTP/1.1 429 Too Many Requests
Retry-After: 30
```

RateLimit-related response fields can communicate the policy and remaining quota, depending on the API's implementation.

### Rate Limiting vs Throttling

| Rate Limiting | Throttling |
|---|---|
| Defines allowed request rate | Controls or slows request processing |
| Often rejects excess requests | May delay or queue requests |
| Protects service capacity | Smooths traffic and workload |

### Rate Limiting vs Load Balancing

| Rate Limiting | Load Balancing |
|---|---|
| Controls request volume | Distributes incoming requests |
| Protects backend capacity | Utilizes multiple backend instances |
| May reject excess traffic | Routes traffic to available instances |

### Real-world example

A login API:

```text
POST /api/login

Maximum:
5 failed attempts within 5 minutes
```

If a user exceeds the configured threshold, the service may temporarily block further attempts.

For security, consider account-based limits, IP-based limits, distributed attacks, and safe recovery policies rather than relying only on IP addresses.

### Interview questions

1. What is rate limiting?
2. What is the difference between network-level and application-level rate limiting?
3. Explain Token Bucket.
4. Explain Leaky Bucket.
5. How would you implement rate limiting using Redis?
6. Why is HTTP 429 returned?
7. Rate limiting vs throttling?
8. How do you rate-limit requests across multiple application instances?

---

# PART 5: IMPORTANT COMPARISON TABLES

## 1. OSI vs TCP/IP

| OSI | TCP/IP |
|---|---|
| 7 layers | 4 layers |
| Conceptual reference model | Practical protocol architecture |
| Presentation and Session separate | Included in Application |
| Data Link and Physical separate | Combined as Network Access |

## 2. HTTP vs HTTPS

| HTTP | HTTPS |
|---|---|
| Unencrypted | TLS protected |
| Port 80 | Port 443 |
| No TLS authentication | TLS server authentication |
| Data not protected in transit | Confidentiality and integrity |

## 3. TCP vs UDP

| TCP | UDP |
|---|---|
| Connection-oriented | Connectionless |
| Reliable ordered stream | Best-effort datagrams |
| Retransmission | No built-in retransmission |
| Higher protocol overhead | Lower protocol overhead |
| HTTPS over HTTP/1.1 and HTTP/2 | DNS, QUIC |

## 4. TCP Handshake vs TLS Handshake

| TCP | TLS |
|---|---|
| Establishes transport connection | Establishes secure communication |
| SYN, SYN-ACK, ACK | ClientHello, ServerHello, certificate and key establishment |
| Transport layer | Security protocol |
| Does not encrypt data | Provides encryption |

## 5. Forward Proxy vs Reverse Proxy

| Forward Proxy | Reverse Proxy |
|---|---|
| Represents clients | Represents servers |
| Outbound requests | Incoming requests |
| Client-side network control | Backend routing and protection |

## 6. L4 vs L7 Load Balancing

| L4 | L7 |
|---|---|
| Transport layer | Application layer |
| IP and port | URL, host, headers |
| Lower application-awareness | Higher application-awareness |
| TCP/UDP routing | HTTP routing |

## 7. WebSocket vs SSE vs Long Polling

| Feature | WebSocket | SSE | Long Polling |
|---|---|---|---|
| Communication | Bidirectional | Server to client | Repeated request-response |
| Connection | Persistent | Persistent HTTP stream | Repeated connections/requests |
| Real-time | Yes | Yes | Near real-time |
| Best use | Chat, gaming | Notifications | Simple updates |

## 8. REST vs GraphQL vs gRPC

| Feature | REST | GraphQL | gRPC |
|---|---|---|---|
| Architecture | Resource-based | Query-based | RPC |
| Payload | JSON commonly | JSON commonly | Protobuf commonly |
| HTTP support | Yes | Commonly HTTP | HTTP/2 commonly |
| Streaming | Additional mechanisms | Subscriptions | Built-in |
| Best use | Public APIs | Flexible frontend APIs | Internal microservices |

## 9. CDN vs Anycast

| CDN | Anycast |
|---|---|
| Content delivery infrastructure | Network routing technique |
| Caches content at edge locations | Routes to one of multiple destinations |
| Reduces origin load | Helps distribute network traffic |
| Websites, media, APIs | DNS and network services |

## 10. Connection Pooling vs Keep-Alive

| Connection Pooling | Keep-Alive |
|---|---|
| Manages reusable connections | Keeps connections open for reuse |
| Can manage multiple connections | Connection reuse behavior |
| JDBC, HTTP clients | HTTP persistent connections |

## 11. Rate Limiting vs Load Balancing

| Rate Limiting | Load Balancing |
|---|---|
| Controls request volume | Distributes traffic |
| Protects system from overload | Uses backend resources efficiently |
| May reject requests | Routes requests |

---

# PART 6: IMPORTANT NETWORKING PORTS – QUICK REVISION

| Port | Service |
|---:|---|
| 22 | SSH |
| 25 | SMTP |
| 53 | DNS |
| 80 | HTTP |
| 123 | NTP |
| 443 | HTTPS |
| 3306 | MySQL |
| 5432 | PostgreSQL |
| 5672 | RabbitMQ |
| 6379 | Redis |
| 8080 | Spring Boot default |
| 9092 | Kafka common default |

---

# PART 7: JAVA FULL STACK DEVELOPER – PRACTICAL MAPPING

Understand where these networking concepts appear in your daily development work.

| Networking Concept | Java / Spring Boot Practical Application |
|---|---|
| HTTP | REST API communication |
| HTTPS | Secure API exposure |
| HTTP methods | @GetMapping, @PostMapping, @PutMapping |
| Status codes | ResponseEntity |
| TCP | Database and HTTP client communication |
| DNS | Microservice hostname resolution |
| Ports | server.port configuration |
| Reverse Proxy | NGINX in front of Spring Boot |
| Load Balancer | Multiple Spring Boot instances |
| L4/L7 | Network routing vs API path routing |
| WebSocket | Spring WebSocket |
| SSE | SseEmitter |
| gRPC | Java gRPC stubs |
| CDN | Frontend static assets |
| Latency | API response time monitoring |
| Connection Pooling | HikariCP |
| Keep-Alive | HTTP client connection reuse |
| Rate Limiting | Redis + Bucket4j / API Gateway |

---

# PART 8: PRODUCT-BASED COMPANY INTERVIEW QUESTIONS

## Basic Networking Questions

1. Explain the OSI model with a real-world example.
2. Explain the TCP/IP model.
3. What happens when you enter a URL in a browser?
4. What is the difference between HTTP and HTTPS?
5. Explain DNS resolution.
6. What is the difference between IPv4 and IPv6?
7. What is the difference between IP address and port?
8. Explain TCP vs UDP.

## Intermediate Networking Questions

9. Explain the TCP three-way handshake.
10. Explain the TLS handshake.
11. What is HTTP/2 multiplexing?
12. Why does HTTP/3 use QUIC?
13. What is head-of-line blocking?
14. What is a reverse proxy?
15. What is the difference between a forward proxy and reverse proxy?
16. What is the difference between L4 and L7 load balancing?
17. Explain different load balancing algorithms.
18. What happens when one backend server fails?

## Advanced System Design Questions

19. How would you design a highly available API gateway?
20. How would you scale WebSocket connections across multiple instances?
21. When would you use SSE instead of WebSockets?
22. REST vs GraphQL vs gRPC – when would you use each?
23. How does a CDN reduce application latency?
24. What is Anycast and how does it help global applications?
25. How do you optimize network latency in microservices?
26. How does connection pooling work in Spring Boot?
27. What happens when a database connection pool is exhausted?
28. How would you implement distributed rate limiting using Redis?
29. How would you protect APIs from excessive requests?
30. What happens when DNS resolution fails?

---

# PART 9: PRACTICAL SCENARIO-BASED QUESTIONS

### Scenario 1: API is taking 5 seconds to respond. How would you troubleshoot it?

Investigate:

1. DNS resolution time.
2. TCP connection establishment time.
3. TLS handshake time.
4. Network latency.
5. Load balancer routing.
6. Spring Boot processing time.
7. Database query execution time.
8. External API calls.
9. Connection pool wait time.

Use distributed tracing and metrics to identify where the delay occurs.

### Scenario 2: Your Spring Boot application has 5 instances. How would you distribute traffic?

Use:

- Load balancer.
- Health checks.
- Round Robin or least connections.
- Multiple application instances.
- Stateless service design.
- Shared session storage if necessary.

### Scenario 3: A microservice is taking too long to respond.

Use:

- Connection and request timeouts.
- Circuit breaker.
- Retry with exponential backoff and jitter where appropriate.
- Bulkhead isolation.
- Caching.
- Monitoring and tracing.

### Scenario 4: A chat application needs real-time communication.

Choose WebSockets because both the client and server need to send messages over the same persistent connection.

For multiple backend instances, introduce shared messaging infrastructure.

### Scenario 5: An application needs live notifications but not two-way communication.

Choose SSE if a browser needs a continuous stream of server-to-client updates.

### Scenario 6: Multiple microservices are communicating frequently.

Consider gRPC when compact serialization, generated contracts, and efficient service-to-service communication are important.

### Scenario 7: An API is receiving 100,000 requests per second.

Consider:

- CDN for cacheable content.
- L4/L7 load balancing.
- Horizontal scaling.
- Distributed rate limiting.
- Redis caching.
- Database optimization.
- Kafka for suitable asynchronous workloads.
- Autoscaling and observability.

Do not assume that adding more application servers alone will solve a database or downstream-service bottleneck.

---

# PART 10: FINAL REVISION CHECKLIST

Use this checklist to track your learning.

## Networking Foundations

- [ ] OSI model – 7 layers
- [ ] TCP/IP model – 4 layers
- [ ] HTTP vs HTTPS
- [ ] HTTP methods
- [ ] HTTP status codes
- [ ] HTTP/1.1 vs HTTP/2 vs HTTP/3
- [ ] TCP vs UDP
- [ ] TCP three-way handshake
- [ ] TLS handshake
- [ ] DNS resolution
- [ ] IPv4 vs IPv6
- [ ] IP vs Port

## Proxies and Load Balancing

- [ ] Reverse proxy
- [ ] Forward proxy
- [ ] Load balancers
- [ ] Load balancing algorithms
- [ ] L4 vs L7

## Real-Time Communication

- [ ] WebSockets
- [ ] SSE
- [ ] Long polling
- [ ] gRPC
- [ ] REST vs GraphQL vs gRPC

## Performance and Infrastructure

- [ ] CDN
- [ ] Anycast
- [ ] Network latency
- [ ] Connection pooling
- [ ] Keep-alive
- [ ] Rate limiting

---

# FINAL LEARNING STRATEGY

Recommended time to complete Module 2:

| Activity | Estimated Time |
|---|---:|
| Networking foundations (1–11) | 5 hours |
| Proxies and load balancing (12–15) | 2 hours |
| Real-time communication (16–20) | 3 hours |
| Performance and infrastructure (21–26) | 3 hours |
| Practical examples and diagrams | 2 hours |
| Interview revision | 2 hours |
| **Total** | **17 hours** |

## What you should be able to explain after completing this module

1. How a browser communicates with a Spring Boot application.
2. How DNS converts a domain name into an IP address.
3. How TCP establishes a connection.
4. How TLS secures HTTP communication.
5. How load balancers distribute requests.
6. How reverse proxies route requests to microservices.
7. How WebSockets, SSE and gRPC support different communication requirements.
8. How CDNs improve global application performance.
9. How connection pooling improves database access.
10. How rate limiting protects distributed applications.

**Golden interview preparation rule:** For every networking concept, understand its definition, working mechanism, architecture diagram, real-world example, advantages, disadvantages, and when to use it in a distributed application.