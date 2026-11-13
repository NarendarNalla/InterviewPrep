# Module 3: Interview Questions

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
| 3.1 – 3.4 | Memory Basics | [Go to](#31--34-memory-basics) |
| 3.5 – 3.8 | Memory Types and State | [Go to](#35--38-memory-types-and-state) |
| 3.9 – 3.12 | Context and Retrieval | [Go to](#39--312-context-and-retrieval) |
| 3.13 – 3.19 | Lifecycle, Production, and Design | [Go to](#313--319-lifecycle-production-and-design) |

---

## 3.1 – 3.4 Memory Basics

```text
  ●  ●  ●   3.1 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is agent memory?

A  The mechanism through which an agent stores, retrieves, and
   uses information from earlier interactions or task executions.
   Without it, each LLM request is stateless.
```

```text
  ●  ●  ●   3.2 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is the difference between short-term and long-term memory?

A  Short-term memory holds information for the current conversation
   or task and can be discarded afterward. Long-term memory persists
   across sessions and needs retention and deletion policies.
```

```text
  ●  ●  ●   3.4 · Q1                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Why should an agent not store every conversation as long-term
   memory?

A  Storing everything adds cost, creates privacy and retention
   risks, and surfaces irrelevant or outdated facts later. Only
   useful, reliable, and appropriate information should be kept.
```

## 3.5 – 3.8 Memory Types and State

```text
  ●  ●  ●   3.5 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is episodic memory?

A  Memory of past events and experiences, such as a previous
   incident, its investigation steps, and its resolution. It is
   useful for comparing current and historical events.
```

```text
  ●  ●  ●   3.6 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is semantic memory?

A  Stored facts and knowledge, such as "HikariCP is a JDBC
   connection pool", without necessarily preserving the original
   event or conversation.
```

```text
  ●  ●  ●   3.5 · Q2                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is the difference between episodic and semantic memory?

A  Episodic memory records what happened in a specific case.
   Semantic memory records general facts that apply across cases.
   An incident is episodic; the fact that pool exhaustion causes
   delays is semantic.
```

```text
  ●  ●  ●   3.7 · Q1                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is procedural memory?

A  Knowledge about how to perform a task, such as a stored
   investigation procedure. It can be represented as workflows,
   instructions, policies, or executable procedures.
```

```text
  ●  ●  ●   3.8 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is the difference between memory, conversation history,
   and state?

A  Conversation history is previous messages. Memory is useful
   retained information. State is the current execution status of
   a workflow. Context is what the application selects and sends
   to the LLM for the next decision.
```

## 3.9 – 3.12 Context and Retrieval

```text
  ●  ●  ●   3.9 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is context engineering?

A  Selecting, organizing, and supplying the right information to
   the LLM at the right time. The goal is relevant context, not
   more context.
```

```text
  ●  ●  ●   3.10 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is a context window?

A  The maximum number of tokens a model can process in a single
   request, including input and generated output, subject to the
   model's limits.
```

```text
  ●  ●  ●   3.10 · Q2                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  How do you handle context overflow?

A  Summarize older conversation, retrieve only relevant stored
   information, use a sliding window for recent messages, and keep
   workflow progress in structured state. Never drop critical
   system instructions or security constraints to make room.
```

```text
  ●  ●  ●   3.11 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Which storage technology would you use for each type of memory?

A  Redis for active session state and TTL-based data, PostgreSQL
   for persistent preferences and task history, MongoDB for flexible
   document-style metadata, and a vector database for semantic
   retrieval.
```

```text
  ●  ●  ●   3.12 · Q1                                 🟢 BASIC
────────────────────────────────────────────────────────────
Q  What are embeddings?

A  Numerical vector representations of text that capture semantic
   meaning. Texts with similar meaning have vectors that are close
   together.
```

```text
  ●  ●  ●   3.12 · Q2                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  How does semantic retrieval work?

A  Convert the user's question into an embedding, search the
   vector database for similar vectors, retrieve the matching text
   and metadata, filter or rerank the results, and add the relevant
   items to the LLM context.
```

```text
  ●  ●  ●   3.12 · Q3                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  Does vector similarity prove two incidents have the same cause?

A  No. Similarity only indicates that the texts are semantically
   close. The retrieved evidence and metadata must be validated
   before the agent relies on them.
```

```text
  ●  ●  ●   3.16 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is the difference between agent memory and RAG?

A  Agent memory retains information such as preferences, history,
   and task progress. RAG retrieves external knowledge, such as
   documentation, to answer a question. RAG can be part of an
   agent's semantic memory, but they are not the same thing.
```

## 3.13 – 3.19 Lifecycle, Production, and Design

```text
  ●  ●  ●   3.13 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What are the stages of the memory lifecycle?

A  Capture, evaluate, store, retrieve, update, and forget. Each
   stage decides which information is worth keeping, where it lives,
   how it is found again, and when it expires.
```

```text
  ●  ●  ●   3.13 · Q2                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How do you prevent stale memory from misleading the agent?

A  Store timestamps and source references, apply TTLs or
   expiration where appropriate, update or replace outdated facts,
   and prefer recent, high-confidence memories during retrieval.
```

```text
  ●  ●  ●   3.14 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  How would you implement agent memory in Java?

A  Model the state and memory as classes, define a memory service
   interface, and provide implementations backed by Redis for short
   term state, PostgreSQL for persistent records, and a vector store
   for semantic retrieval. Spring Boot services and repositories
   manage access.
```

```text
  ●  ●  ●   3.15 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What are LangChain4j and Spring AI?

A  Java ecosystems for building LLM applications. They provide
   support for model integration, chat memory, vector stores,
   retrievers, and tools. Check official documentation for the
   version you use, since APIs change.
```

```text
  ●  ●  ●   3.17 · Q1                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  What is cross-user memory leakage and how do you prevent it?

A  One user's private memory reaches another user's context. Prevent
   it with tenant and user isolation in every query, authorization
   checks in the memory service, and scoped retrieval filters. Never
   rely on the prompt alone to keep data separate.
```

```text
  ●  ●  ●   3.18 · Q1                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How would you design memory for a production AI agent?

A  Separate conversation history, persistent memory, and workflow
   state. Use Redis for active state, PostgreSQL for structured
   records, and a vector database for semantic retrieval. Build the
   context per request from recent messages, relevant memories,
   current state, and retrieved documents. Add tenant isolation,
   access control, retention policies, and checkpointing.
```

```text
  ●  ●  ●   3.18 · Q2                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How does an agent resume an interrupted investigation?

A  Persist workflow state and checkpoints after each meaningful
   step. On restart, load the last checkpoint and continue from the
   next pending step. Tool actions must be idempotent so that a
   replay does not repeat side effects.
```

```text
  ●  ●  ●   3.19 · Q1                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How would you secure memory in an enterprise agent?

A  Enforce authorization on every read and write, isolate data per
   tenant and user, encrypt data at rest and in transit, apply
   retention and deletion policies, and treat retrieved memory as
   untrusted input so that stored prompt-injection content cannot
   act as instructions.
```

---

**Notes on the design:**
- Image-placeholder artifacts from the source (`lantern.dev`, `linkedin.com`, `agentfactory.panaversity.org`) were removed.
- Repeated content and the duplicated interview-answer table were merged into the takeaways table.
- Difficulty tags are my judgment, not part of the original syllabus.
- A few questions were added beyond the source's 15 Q&A rows, such as the stale-memory and cross-user leakage questions, because they are commonly asked alongside these topics.