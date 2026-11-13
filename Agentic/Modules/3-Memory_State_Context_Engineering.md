# Module 3: Agent Memory, State Management & Context Engineering

> **Priority:** Must learn for Senior / Lead Engineer preparation.
> Interview questions for this module are in `Module3_Interview_Questions.md`.

In Module 1, we learned how an agent reasons and acts. In Module 2, we learned how it uses tools and MCP.

This module answers an important question:

> **How does an AI agent remember what happened earlier, retain useful information, and continue a task across multiple interactions?**

Imagine a user asks an IT support agent:

1. "My Spring Boot application is responding slowly."
2. "The issue started after yesterday's deployment."
3. "Check whether database connections are exhausted."
4. "Compare it with the previous incident."

The agent needs to retain the conversation, remember the deployment detail, preserve the investigation progress, and retrieve the previous incident when needed. That is where **memory**, **state management**, and **context engineering** come in.

## Table of Contents

| # | Topic | Link |
|---|---|---|
| 3.1 | What is Agent Memory? | [Go to 3.1](#31-what-is-agent-memory) |
| 3.2 | Types of Agent Memory | [Go to 3.2](#32-types-of-agent-memory) |
| 3.3 | Short-Term Memory | [Go to 3.3](#33-short-term-memory) |
| 3.4 | Long-Term Memory | [Go to 3.4](#34-long-term-memory) |
| 3.5 | Episodic Memory | [Go to 3.5](#35-episodic-memory) |
| 3.6 | Semantic Memory | [Go to 3.6](#36-semantic-memory) |
| 3.7 | Procedural Memory | [Go to 3.7](#37-procedural-memory) |
| 3.8 | Memory vs Conversation History vs State | [Go to 3.8](#38-memory-vs-conversation-history-vs-state) |
| 3.9 | What is Context Engineering? | [Go to 3.9](#39-what-is-context-engineering) |
| 3.10 | Context Window and Token Management | [Go to 3.10](#310-context-window-and-token-management) |
| 3.11 | Memory Storage Technologies | [Go to 3.11](#311-memory-storage-technologies) |
| 3.12 | Embeddings and Vector Memory | [Go to 3.12](#312-embeddings-and-vector-memory) |
| 3.13 | Memory Lifecycle | [Go to 3.13](#313-memory-lifecycle) |
| 3.14 | Implementing Agent State and Memory in Java | [Go to 3.14](#314-implementing-agent-state-and-memory-in-java) |
| 3.15 | LangChain4j and Spring AI | [Go to 3.15](#315-langchain4j-and-spring-ai) |
| 3.16 | Memory vs RAG | [Go to 3.16](#316-memory-vs-rag) |
| 3.17 | Common Problems and Solutions | [Go to 3.17](#317-common-memory-and-context-engineering-problems) |
| 3.18 | Real-World Enterprise Architecture | [Go to 3.18](#318-real-world-enterprise-architecture) |
| 3.19 | Practical Assignment | [Go to 3.19](#319-practical-assignment) |
| — | Interview Takeaways | [Go to takeaways](#module-3-interview-takeaways) |
| — | Interview Questions | [Go to questions](#interview-questions) |

---

## 3.1 What is Agent Memory?

**Agent memory** is the mechanism through which an AI agent stores, retrieves, and uses information from previous interactions or task executions.

A basic LLM request is **stateless**. The model does not automatically remember previous API calls. The application must provide the relevant conversation history or retrieved information in each subsequent request.

### Without Memory

```
Interaction 1
  User: "My application is slow after deployment."

Interaction 2
  User: "Check the database connections."

  Agent has no previous context unless the application supplies it.
```

### With Memory

```
  User:  "My application is slow after deployment."
  Agent remembers: Deployment happened yesterday.
  User:  "Check the database connections."

  Agent uses the previous deployment context to investigate.
```

### Why Do Agents Need Memory?

- Maintain conversational continuity
- Avoid asking users for the same information repeatedly
- Remember user preferences and relevant facts
- Track long-running tasks
- Learn from previous task outcomes, when designed to do so
- Retrieve historical knowledge for better decisions

---

## 3.2 Types of Agent Memory

A useful way to classify memory is by its **purpose** and **lifetime**.

```
                        Agent Memory
                             │
       ┌──────────────┬──────┴───────┬────────────────┐
       ▼              ▼              ▼                ▼
 Short-Term       Long-Term       Episodic         Semantic
 Memory           Memory          Memory           Memory
 Current          Retained        Past events      Facts and
 task context     across          and experiences  knowledge
                  sessions
```

> **Note:** These are conceptual categories, not necessarily separate databases. One storage system can support several memory types.

---

## 3.3 Short-Term Memory

Stores information needed during the current conversation or task.

> **Example:**
> User: "Find the issue in my Spring Boot application."
>
> Agent memory:
> - Application: Payment Service
> - Environment: Production
> - Issue: High response time
> - Deployment: Yesterday
> - Current investigation: Database connections

**Characteristics:**
- Frequently updated
- Used during the current task
- Can be discarded after task completion
- Often maintained in conversation history or application state

**Implementation options:** In-memory objects, conversation buffers, Redis, framework-managed state.

---

## 3.4 Long-Term Memory

Stores information that should remain available across conversations or tasks.

> **Example: User preferences**
> - Preferred language: Java
> - Backend framework: Spring Boot
> - Database: PostgreSQL
> - Preferred explanation style: Detailed technical examples

**Characteristics:**
- Persists across sessions
- Retrieved when relevant
- Needs retention and deletion policies
- Should store useful information, not every conversation

**Implementation options:** PostgreSQL, MongoDB, Redis with persistence, vector databases, or a combination.

---

## 3.5 Episodic Memory

Stores **past events and experiences**. Think of it as the agent's history of what happened.

```
Incident ID: INC-1024

Event:
  Payment API latency increased.

Investigation:
  - Checked application logs.
  - Found database connection pool exhaustion.
  - Identified long-running queries.

Resolution:
  - Optimized SQL query.
  - Adjusted connection pool configuration.

Outcome:
  API latency reduced.
```

**Useful for:**
- Previous incident analysis
- Repeated troubleshooting
- Learning from completed workflows
- Comparing current and historical events

---

## 3.6 Semantic Memory

Stores **facts and knowledge** without necessarily preserving the original event or conversation.

```
Knowledge:
  - HikariCP is a JDBC connection pool.
  - Spring Boot commonly uses HikariCP by default.
  - Connection pool exhaustion can cause request delays.
  - Slow SQL queries can increase connection occupancy.
```

**Useful for:**
- Technical knowledge retrieval
- Company documentation
- API documentation
- Coding standards
- Frequently asked questions

---

## 3.7 Procedural Memory

Procedural memory represents **knowledge about how to perform a task**.

```
Procedure: Investigate API latency

1. Check API response time.
2. Inspect application logs.
3. Check database connection pool.
4. Inspect slow SQL queries.
5. Check downstream service latency.
6. Generate an incident report.
```

It can be represented through stored workflows, instructions, policies, or executable procedures.

> **Interview note:** Short-term and long-term memory describe *retention and scope*. Episodic, semantic, and procedural memory describe *the kind of information*. These classifications overlap.

---

## 3.8 Memory vs Conversation History vs State

These concepts are related, but they are not interchangeable.

| Concept | Purpose | Example |
|---|---|---|
| Conversation history | Previous messages | User asked about API latency |
| Memory | Useful retained information | User's application is Payment Service |
| State | Current execution status | Investigation is at step 3 |
| Context | Information supplied to the LLM now | Relevant history + state + retrieved facts |

Consider a long-running agent workflow:

```
Task ID:        TASK-1001
Status:         IN_PROGRESS
Current step:   CHECK_DATABASE
Application:    PAYMENT_SERVICE
Deployment:     2026-11-11
Findings:
  - API latency increased
  - Database connections near limit
```

- **State** tells the agent where it is in the workflow.
- **Memory** gives it relevant information from the past.
- **Context** is the selected information supplied to the model for its next decision.

---

## 3.9 What is Context Engineering?

**Context engineering** is the process of selecting, organizing, and supplying the right information to an LLM at the right time.

An LLM has a limited context window. Even if a conversation has hundreds of messages, sending all of them every time is inefficient, expensive, and distracting.

> **The goal is not to send more context. The goal is to send relevant context.**

### What Goes Into an Agent's Context?

```
  System instructions       Agent role, rules, constraints
          +
  Conversation history      Recent user and agent messages
          +
  Retrieved memory          Relevant long-term facts and past experiences
          +
  Current task state        Current step, findings, pending actions
          +
  Tool results              Database, API, search, monitoring results
          │
          ▼
    Final context  ──▶  LLM
```

### Example: Context Assembled for an IT Agent

```
SYSTEM:
  You are a production support agent.
  Never restart production services without approval.

USER REQUEST:
  Investigate high latency in Payment Service.

RECENT HISTORY:
  The issue started after yesterday's deployment.

RETRIEVED MEMORY:
  Previous incident was caused by connection pool exhaustion.

CURRENT STATE:
  Database connection usage is 95%.
  Slow query investigation is pending.

TOOL RESULT:
  Average API latency = 4.2 seconds.

TASK:
  Identify the most likely cause and recommend the next diagnostic step.
```

---

## 3.10 Context Window and Token Management

A **context window** is the maximum number of tokens a model can process in a single request, including input and generated output, subject to the model's limits.

A **token** is a unit of text processed by the model. It may be a word, part of a word, punctuation, or other text.

> **Example:** The sentence "Spring Boot is powerful" is split into tokens by the model's tokenizer. Tokenization is not exactly word counting, and token counts depend on the tokenizer and the text.

### Why Context Management Matters

- Large context increases cost and latency
- Irrelevant history can distract the model
- Context limits can cause request failures or truncation
- Long-running tasks need compression and retrieval

### Common Context Management Techniques

| Technique | What It Does |
|---|---|
| Truncation | Removes older or less useful messages |
| Sliding window | Retains the most recent messages |
| Summarization | Compresses old conversation into a summary |
| Retrieval | Fetches only relevant stored information |
| Context prioritization | Preserves critical instructions and facts |
| Structured state | Stores workflow progress separately |

> **Important:** Never discard critical system instructions, security constraints, or unresolved task state just to fit a context window.

---

## 3.11 Memory Storage Technologies

Different storage systems solve different memory problems. No single database is best for all agent memory.

| Technology | Best For | Typical Use in Agents |
|---|---|---|
| **Redis** | Fast short-term state and session data | Session state, recent conversation, TTL-based temporary memory, active workflow data |
| **PostgreSQL** | Structured persistent memory | User preferences, task execution history, workflow state, audit records |
| **MongoDB** | Flexible document-oriented memory | JSON-like memory documents, conversation summaries, variable task metadata |
| **Vector database** | Semantic similarity-based retrieval | Embeddings, historical incident retrieval, knowledge-base search |

Examples of vector database products include **Pinecone**, **Weaviate**, **Milvus**, and **Chroma**.

### Recommended Enterprise Combination

| Requirement | Technology |
|---|---|
| Active conversation state | Redis |
| Persistent user and task data | PostgreSQL |
| Semantic knowledge retrieval | Vector database |
| Application business data | Existing database |
| Logs and observability | Logging and monitoring platform |

> A vector database is not automatically a replacement for PostgreSQL or Redis. It solves a different retrieval problem.

---

## 3.12 Embeddings and Vector Memory

This is an important topic because vector memory is commonly used in RAG and agentic systems.

An **embedding** is a numerical representation of text (or other data) that captures its semantic meaning.

```
Text 1: "Database connection pool exhausted."
Text 2: "HikariCP has reached maximum connections."
Text 3: "Angular component styling issue."

Embedding model converts each text into a vector:

Text 1  →  [0.12, 0.45, 0.89, ...]
Text 2  →  [0.14, 0.43, 0.87, ...]
Text 3  →  [0.92, 0.11, 0.25, ...]
```

Texts 1 and 2 are semantically closer to each other than to Text 3, so their vectors are closer.

### How Semantic Memory Retrieval Works

1. Receive a user question.
2. Convert the question into an embedding.
3. Search the vector database for similar vectors.
4. Retrieve the corresponding text and metadata.
5. Filter or rerank the retrieved results.
6. Add the relevant results to the LLM context.
7. Generate the response.

> **Example:** The user asks, *"Have we experienced a similar database connection issue before?"* The system searches previous incident embeddings and retrieves an incident about HikariCP connection exhaustion.

> **Important:** Vector similarity is not proof that two incidents share the same root cause. Validate the retrieved evidence and its metadata.

---

## 3.13 Memory Lifecycle

Memory is not simply saving everything. A robust system needs a complete lifecycle.

```
  1. Capture   ──▶  Extract useful facts from conversations, tool results,
                    and completed tasks
        │
  2. Evaluate  ──▶  Decide whether the information is useful, reliable,
                    and appropriate to retain
        │
  3. Store     ──▶  Save it in the right place: Redis, relational DB,
                    document DB, or vector DB
        │
  4. Retrieve  ──▶  Find relevant information when a new task needs it
        │
  5. Update    ──▶  Correct outdated information or add new findings
        │
  6. Forget    ──▶  Apply retention policies, TTL, deletion requests,
                    and privacy requirements
```

### Example Memory Record

```json
{
  "memoryId": "MEM-1001",
  "type": "EPISODIC",
  "userId": "USER-101",
  "content": "Payment API latency was caused by DB pool exhaustion.",
  "source": "INC-1024",
  "createdAt": "2026-11-12T10:00:00Z",
  "confidence": 0.95,
  "tags": ["Spring Boot", "HikariCP", "Database"],
  "expiresAt": null
}
```

**In production, also consider:**
- Tenant and user isolation
- Access control and encryption
- Source attribution and timestamps
- Confidence and freshness
- Data retention and deletion
- Protection against storing prompt-injection instructions as trusted memory

---

## 3.14 Implementing Agent State and Memory in Java

Let's connect these concepts to your Java and Spring Boot background.

Imagine building an AI-powered production troubleshooting agent.

### Step 1: Define the Task State

```java
public class AgentState {

    private String taskId;
    private String applicationName;
    private String currentStep;
    private String status;
    private List<String> findings;
    private Map<String, Object> metadata;
}
```

> State is structured application data. Do not confuse it with an unstructured conversation transcript.

### Step 2: Define a Memory Model

```java
public class AgentMemory {

    private String memoryId;
    private String userId;
    private String memoryType;
    private String content;
    private String source;
    private LocalDateTime createdAt;
}
```

### Step 3: Define a Memory Service

```java
public interface AgentMemoryService {

    void saveMemory(AgentMemory memory);

    List<AgentMemory> retrieveRelevantMemories(String userId, String query);

    void deleteMemory(String memoryId);
}
```

**Possible implementations:**
- `RedisAgentMemoryService`: short-lived state
- `PostgresAgentMemoryService`: structured persistent memory
- `VectorAgentMemoryService`: semantic retrieval

### Step 4: Use Memory in the Agent Flow

```java
public String processRequest(String userId, String query) {

    // 1. Retrieve relevant long-term memory
    List<AgentMemory> memories =
        memoryService.retrieveRelevantMemories(userId, query);

    // 2. Load current task state
    AgentState state = stateService.getCurrentState(userId);

    // 3. Build context
    String context = contextBuilder.build(query, memories, state);

    // 4. Send context to the LLM
    String response = llmService.generate(context);

    // 5. Update task state
    stateService.updateFromResponse(state, response);

    return response;
}
```

> **Note:** This is illustrative application-level code. Actual LLM calls, retrieval, persistence, and state updates depend on your chosen framework and model provider.

---

## 3.15 LangChain4j and Spring AI

For Java developers, these are two relevant ecosystems to explore.

| Framework | Description |
|---|---|
| **LangChain4j** | Java library for building LLM applications and agents, with integrations for memory, tools, and retrieval |
| **Spring AI** | Spring ecosystem support for model integration, advisors, vector stores, tools, and chat memory patterns |

### Key Concepts in These Frameworks

| Concept | Purpose |
|---|---|
| Chat memory | Maintains conversation history |
| Memory advisor / provider | Supplies memory during model calls |
| Vector store | Stores and retrieves embeddings |
| Retriever | Fetches relevant information |
| Prompt template | Structures context and instructions |
| Agent state | Tracks workflow progress |

> Framework APIs change across versions, so learn the underlying architecture before memorizing annotations or method names. Check the official documentation for your version.

---

## 3.16 Memory vs RAG

These concepts are often confused.

**RAG** (Retrieval-Augmented Generation) retrieves external knowledge and supplies it to the LLM when answering a question. **Agent memory** is broader: it can preserve conversation history, preferences, experiences, and task progress.

| Aspect | Agent Memory | RAG |
|---|---|---|
| Main purpose | Retain useful information | Retrieve relevant knowledge |
| Typical data | User preferences, history, task state | Documents, manuals, knowledge bases |
| Retrieval | Session lookup, database query, semantic search | Often semantic or hybrid search |
| Persistence | Short-term or long-term | Usually persistent knowledge store |
| Example | Remember the current investigation step | Retrieve a database troubleshooting guide |

> RAG can be part of an agent's long-term semantic memory, but RAG and memory are not synonyms.

### Combined Architecture

A production agent might use:

- **Redis** for current task state
- **PostgreSQL** for persistent structured memories
- **Vector database** for semantic retrieval
- **RAG** for retrieving company documentation
- **Context builder** to combine the relevant information

---

## 3.17 Common Memory and Context Engineering Problems

| Problem | What Happens | Solution |
|---|---|---|
| Context overflow | Too many tokens | Summarization, retrieval, truncation |
| Stale memory | Old facts are used | Expiration, timestamps, updates |
| Irrelevant retrieval | Wrong memories enter context | Metadata filters, reranking |
| Memory duplication | Same information saved repeatedly | Deduplication and consolidation |
| Incorrect memory | Agent stores false facts | Source validation, confidence checks |
| Context pollution | Untrusted content affects reasoning | Treat retrieved content as untrusted |
| Cross-user leakage | One user's data reaches another | Tenant isolation and authorization |
| Lost task progress | Agent cannot resume after failure | Persist state and checkpoints |

> **Senior-level insight:** Memory retrieval is not just a similarity-search problem. It is also a security, data-quality, relevance, and lifecycle-management problem.

---

## 3.18 Real-World Enterprise Architecture

Let's design an **AI Production Support Agent**.

### Example Execution

1. The user asks the agent to investigate Payment API latency.
2. The application loads the current task state from Redis.
3. The memory service retrieves relevant historical incidents.
4. RAG retrieves internal troubleshooting documentation.
5. The context builder prepares a focused prompt.
6. The LLM decides which diagnostic tool to invoke.
7. The tool executor retrieves application metrics.
8. The result is added to the task state and context.
9. The agent recommends the next diagnostic step.
10. The system persists findings and the final incident summary.

> If the service crashes after step 8, a well-designed system can resume from its persisted checkpoint rather than starting the investigation from scratch.

---

## 3.19 Practical Assignment

### Build a Spring Boot AI Employee Support Agent with Memory

**Requirements:**

1. The user can ask questions across multiple conversations.
2. The agent remembers the user's department and preferred programming language.
3. The agent can retrieve previous support incidents.
4. The agent tracks the current investigation step.
5. The agent retrieves relevant technical documentation.
6. The agent can resume an interrupted investigation.
7. The agent does not expose one employee's private memory to another.

**Suggested technology choices:**

| Component | Technology |
|---|---|
| Backend | Java + Spring Boot |
| LLM integration | Spring AI or LangChain4j |
| Short-term state | Redis |
| Persistent memory | PostgreSQL |
| Semantic retrieval | pgvector or another vector database |
| Documentation | RAG pipeline |
| API | REST |

---

## Module 3: Interview Takeaways

Make sure you can explain these concepts without looking at notes.

| Interview Question | Key Answer |
|---|---|
| What is agent memory? | Storing and retrieving useful information across interactions. |
| Short-term vs long-term memory? | Short-term covers the current task; long-term persists across sessions. |
| Episodic vs semantic memory? | Episodic is past experiences; semantic is facts and knowledge. |
| What is agent state? | The current execution status of a workflow. |
| Memory vs conversation history? | Memory is retained useful facts; history is previous messages. |
| What is context engineering? | Selecting and organizing the right information for the LLM. |
| How do you handle context overflow? | Summarization, retrieval, sliding windows, and structured state. |
| What are embeddings? | Numerical representations used for semantic similarity search. |
| Memory vs RAG? | Memory retains agent information; RAG retrieves external knowledge. |
| How do you prevent stale memory? | TTL, timestamps, versioning, and update policies. |
| How do you secure memory? | Authorization, tenant isolation, encryption, and retention policies. |
| How does an agent resume after failure? | Persistent state, checkpoints, and idempotent actions. |