# Module 2: Interview Questions

## Legend

```text
  ●  ●  ●   LEGEND
────────────────────────────────────────────────────────────
  🟢  BASIC          definitions and core concepts
  🟡  INTERMEDIATE   how it works and the trade-offs
  🔴  ADVANCED       production design and failure handling
```

Questions are grouped by section. Each one is a code-style window with its section, question number, and difficulty.

## Table of Contents

| Section | Topic | Link |
|---|---|---|
| 2.1 – 2.3 | Tools | [Go to](#21--23-tools) |
| 2.4 – 2.10 | Function Calling and Execution | [Go to](#24--210-function-calling-and-execution) |
| 2.11 – 2.18 | MCP | [Go to](#211--218-mcp) |

---

## 2.1 – 2.3 Tools

```text
  ●  ●  ●   2.1 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What are tools in Agentic AI?

A  External, executable capabilities that an agent can invoke
   to read data or perform actions. The application exposes
   them, and the agent selects which one to use.
```

```text
  ●  ●  ●   2.2 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  Why do AI agents need tools?

A  An LLM alone cannot access external data or act on external
   systems. Tools provide access to live data such as order
   status or account balances, and allow actions such as
   creating tickets or sending emails.
```

```text
  ●  ●  ●   2.3 · Q1                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  How does an agent choose the right tool?

A  The model compares the user's request against each tool's
   name and description, then selects the best match. Clear,
   specific descriptions improve selection. Limiting the tools
   exposed to each task also reduces wrong choices.
```

## 2.4 – 2.10 Function Calling and Execution

```text
  ●  ●  ●   2.4 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is function calling?

A  A capability where the LLM requests the execution of a
   predefined function by producing structured arguments. The
   LLM does not run the function itself.
```

```text
  ●  ●  ●   2.4 · Q2                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  Does the LLM execute Java methods?

A  No. The LLM only generates a structured request. The
   application validates it, checks permissions, executes the
   Java method, and returns the result to the model.
```

```text
  ●  ●  ●   2.6 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is a tool schema?

A  A JSON definition containing the function name, a
   description, and the parameters with their types and required
   fields. The model uses it to decide when and how to call the
   tool.
```

```text
  ●  ●  ●   2.5 · Q1                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  How does function calling work end to end?

A  The application sends the prompt and tool definitions to the
   LLM. The LLM returns a tool call. The application validates
   and executes it, sends the result back, and the LLM produces
   the final answer. This involves two LLM interactions.
```

```text
  ●  ●  ●   2.8 · Q1                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Can an agent call multiple tools?

A  Yes, sequentially or concurrently. A model may request
   several tool calls in one response, but the application
   decides whether they run in parallel.
```

```text
  ●  ●  ●   2.9 · Q1                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is tool chaining?

A  Using the output of one tool as the input to another. For
   example, fetching an employee's manager ID, then fetching the
   manager's details, then sending an email.
```

```text
  ●  ●  ●   2.9 · Q2                                  🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  Should an LLM decide every step of a business workflow?

A  No. When a fixed business process exists, use deterministic
   workflow logic for critical operations, and let the LLM
   decide only where judgment adds value. Free-form LLM control
   over payments or approvals is risky and hard to audit.
```

```text
  ●  ●  ●   2.10 · Q1                                 🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How do you handle a tool that fails?

A  Classify the failure. Retry transient errors such as timeouts
   or 503s with exponential backoff and a maximum attempt count.
   Respect Retry-After on 429s, return a meaningful error for 404s
   and 403s, and use circuit breakers and timeouts to protect the
   system.
```

```text
  ●  ●  ●   2.10 · Q2                                 🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  Why should you not blindly retry every tool call?

A  Some operations, such as payments or order creation, may have
   succeeded even though the response was lost. Retrying them can
   create duplicates. Use idempotency keys or operations that are
   safe to repeat.
```

## 2.11 – 2.18 MCP

```text
  ●  ●  ●   2.11 · Q1                                 🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is MCP?

A  Model Context Protocol, an open protocol that standardizes
   how AI applications connect to external tools, data sources,
   and services, so one client can work with many compatible
   servers.
```

```text
  ●  ●  ●   2.12 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What are the MCP Host, Client, and Server?

A  The host is the AI application. The client lives inside the
   host and manages the connection to a server. The server
   exposes tools, resources, and prompts backed by external
   systems.
```

```text
  ●  ●  ●   2.14 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What are MCP Tools, Resources, and Prompts?

A  Tools perform actions. Resources provide data or context,
   identified by URIs. Prompts are reusable templates that guide
   how the model performs a task.
```

```text
  ●  ●  ●   2.15 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is JSON-RPC and how does MCP use it?

A  JSON-RPC 2.0 is the message format MCP uses for requests,
   responses, and notifications. A client might send a tools/list
   request, and the server replies with its tool definitions.
```

```text
  ●  ●  ●   2.15 · Q2                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What are the MCP transport options?

A  STDIO, which runs a local server process and communicates
   over stdin and stdout, and Streamable HTTP, which connects to
   remote servers over HTTP and can stream responses. The older
   HTTP+SSE transport is deprecated.
```

```text
  ●  ●  ●   2.16 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Function calling vs MCP: what is the difference?

A  Function calling is how a model requests a tool call.
   MCP is a protocol for discovering and invoking external
   capabilities in a standard way. They are complementary: an
   MCP client can expose discovered tools to the model as
   function definitions.
```

```text
  ●  ●  ●   2.16 · Q2                                 🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  Can MCP and function calling work together?

A  Yes. The model selects a tool through function calling. The
   agent's MCP client then invokes the discovered MCP tool on the
   server, which executes the external operation and returns the
   result.
```

```text
  ●  ●  ●   2.17 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Can MCP servers be written in Java?

A  Yes. Java services can expose their existing business logic
   through a Java MCP SDK, with tools, resources, and prompts
   registered on the server.
```

```text
  ●  ●  ●   2.17 · Q2                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Can MCP work with Spring Boot?

A  Yes. Spring Boot services can be registered as MCP tools
   through a Java MCP SDK or Spring integration. The Spring
   services, repositories, and security stay the same. MCP is
   an additional interface on top of them.
```

```text
  ●  ●  ●   2.17 · Q3                                 🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  When should you use REST instead of MCP?

A  Use REST for frontends, mobile apps, and service-to-service
   calls. Use MCP when AI clients need discoverable tools and
   resources. Keep the business logic independent of either
   protocol and expose it through both where needed.
```

```text
  ●  ●  ●   2.18 · Q1                                 🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How do you secure MCP tools?

A  Authenticate every connection, for example with OAuth 2.0.
   Authorize each tool against the user's role, apply least
   privilege, validate all arguments, require human approval for
   sensitive operations, treat retrieved content as untrusted, and
   keep audit logs with sensitive fields redacted.
```

```text
  ●  ●  ●   2.18 · Q2                                 🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  What is prompt injection and how does it affect tools?

A  Malicious text in a document or tool result tries to override
   the agent's instructions, for example by asking it to send
   data elsewhere. Defend by treating tool output as data, never
   as instructions, and by enforcing authorization in the tool
   itself rather than in the prompt.
```

---

**Notes on the design:**
- Question dividers follow the Module 1 style. Each window shows its section number, question number, and difficulty.
- Difficulty tags are my judgment based on the question's depth, not part of the original syllabus.
- Some questions were added beyond the 18 in the source table, such as the retry question under 2.10 and the REST vs MCP question under 2.17, because they're commonly asked alongside those topics.