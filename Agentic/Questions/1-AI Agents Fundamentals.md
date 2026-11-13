Here's the redesigned **Interview Questions** section. Replace the existing section at the bottom of your file with this. Each question is a dark code-style window with a title bar, a difficulty badge, and a Q/A body.

## Interview Questions

```text
  ●  ●  ●   LEGEND
────────────────────────────────────────────────────────────
  🟢  BASIC          definitions and core concepts
  🟡  INTERMEDIATE   how it works and the trade-offs
  🔴  ADVANCED       production design and failure handling
```

### 1.1 What is AI and Generative AI?

```text
  ●  ●  ●   1.1 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is the difference between AI, ML, and Deep Learning?

A  AI is the broad goal of building systems that perform
   intelligent tasks. Machine Learning is a subset of AI where
   systems learn patterns from data instead of following
   hand-written rules. Deep Learning is a subset of ML that
   uses neural networks with many layers.
```

```text
  ●  ●  ●   1.1 · Q2                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is Generative AI?

A  Models that create new content such as text, images,
   audio, or code, based on patterns learned from training data.
```

```text
  ●  ●  ●   1.1 · Q3                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Is Agentic AI the same as Generative AI?

A  No. Generative AI produces content. Agentic AI is an
   architecture where a model is combined with tools, memory,
   and workflows to pursue a goal through actions.
```

### 1.2 What is an LLM?

```text
  ●  ●  ●   1.2 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is an LLM and how does it generate text?

A  An LLM is a neural network trained on large text corpora.
   It converts the prompt into tokens, then repeatedly predicts
   the most likely next token until the response is complete.
```

```text
  ●  ●  ●   1.2 · Q2                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is a token?

A  A unit of text the model processes, often a word, part of
   a word, or punctuation. Token count drives input and output
   size, cost, and latency.
```

```text
  ●  ●  ●   1.2 · Q3                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Why can't an LLM check your production logs by itself?

A  It only processes text in its context and has no built-in
   connectivity. Access requires tools the application exposes.
```

```text
  ●  ●  ●   1.2 · Q4                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Does an LLM "know" facts or "predict" them?

A  It predicts likely token sequences based on learned patterns.
   This is why it can produce fluent but incorrect answers
   (hallucinations), and why grounding with retrieved data or
   tools matters.
```

### 1.3 What is an AI Agent?

```text
  ●  ●  ●   1.3 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is an AI agent?

A  An LLM-powered system that reasons about a goal, uses tools
   to take actions, observes the results, and iterates until
   the goal is met or it can no longer proceed.
```

```text
  ●  ●  ●   1.3 · Q2                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  How is an agent different from a chatbot?

A  A chatbot mainly responds to messages. An agent plans and
   executes actions through tools, maintains workflow state,
   and works toward an objective with limited step-by-step
   instruction.
```

```text
  ●  ●  ●   1.3 · Q3                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Give a real example of an agent in a Java/backend context.

A  An incident-investigation agent that calls monitoring APIs,
   queries the database, checks Redis metrics, and produces a
   root-cause report.
```

### 1.4 LLM vs AI Agent vs Agentic AI

```text
  ●  ●  ●   1.4 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  Explain the difference between an LLM, an AI agent, and
   agentic AI.

A  An LLM generates text. An AI agent wraps an LLM with tools
   and a control loop so it can act. Agentic AI is the broader
   system design, which may include multiple agents, planning,
   memory, and guardrails.
```

```text
  ●  ●  ●   1.4 · Q2                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Can an LLM be an agent without tools?

A  Not in the usual sense. Without tools or actions, it is an
   LLM application, not an agent, because it cannot change or
   observe anything outside the conversation.
```

```text
  ●  ●  ●   1.4 · Q3                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Does a multi-agent system always perform better?

A  No. It adds orchestration complexity, latency, and cost.
   It is useful only when the task decomposes into specialized,
   parallelizable parts.
```

### 1.5 Characteristics of an AI Agent

```text
  ●  ●  ●   1.5 · Q1                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What makes an agent "autonomous"? Is full autonomy desirable?

A  Autonomy means executing multiple steps without per-step
   instructions. Full autonomy is rarely desirable in production.
   Sensitive actions should require human approval, and autonomy
   should be bounded by guardrails and termination conditions.
```

```text
  ●  ●  ●   1.5 · Q2                                  🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How do you verify an agent's reasoning?

A  Validate tool outputs, check intermediate results against
   expected schemas, run evaluations on test cases, log every
   decision, and require confirmation for high-impact actions.
```

### 1.6 Components of an AI Agent

```text
  ●  ●  ●   1.6 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What are the main components of an AI agent?

A  The LLM (reasoning), instructions (role and constraints),
   tools (actions), memory (retained information), planning
   (task strategy), state (execution progress), and guardrails
   (safety controls).
```

```text
  ●  ●  ●   1.6 · Q2                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is the difference between memory and state?

A  Memory holds information that may be useful across tasks or
   sessions, such as user preferences. State tracks the progress
   of one workflow, such as which steps have completed.
```

```text
  ●  ●  ●   1.6 · Q3                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Who executes a tool: the LLM or the application?

A  The application. The LLM selects the tool and arguments. The
   application validates the request, checks permissions,
   executes the code, and returns the result.
```

```text
  ●  ●  ●   1.6 · Q4                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Where do guardrails belong in the architecture?

A  At multiple layers: validating input, filtering output,
   restricting which tools are available and what arguments they
   accept, and requiring approval for sensitive operations.
```

### 1.7 Agent Execution Loop

```text
  ●  ●  ●   1.7 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  Walk me through the agent execution loop.

A  Perceive the input, reason about the goal, plan the next
   step, act through a tool, observe the result, and evaluate
   whether the goal is met. If not, update the plan and repeat.
```

```text
  ●  ●  ●   1.7 · Q2                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is ReAct?

A  Reason + Act. A pattern where the model alternates between
   reasoning about the next step and taking an action, then uses
   the observation to inform the next reasoning step.
```

```text
  ●  ●  ●   1.7 · Q3                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Why do agents need termination conditions?

A  Without them, an agent can loop indefinitely, consume tokens
   and money, or repeatedly call tools with no progress. Max
   iterations, timeouts, and tool-call budgets prevent this.
```

```text
  ●  ●  ●   1.7 · Q4                                  🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How would you handle a tool that fails mid-loop?

A  Return the error as an observation. Retry with backoff if the
   failure is transient, switch to an alternative tool if one
   exists, and escalate to a human or stop with a partial report
   if it keeps failing.
```

### 1.8 Reasoning and Planning

```text
  ●  ●  ●   1.8 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is the difference between reasoning and planning?

A  Reasoning interprets the situation and decides what matters.
   Planning turns that understanding into an ordered set of
   actions.
```

```text
  ●  ●  ●   1.8 · Q2                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  When would you use predefined planning instead of dynamic
   planning?

A  When the workflow is well understood, compliance matters, or
   predictability and testability matter more than flexibility.
   Dynamic planning suits open-ended tasks but is harder to test
   and audit.
```

```text
  ●  ●  ●   1.8 · Q3                                  🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How do you make an LLM-generated plan safe to execute?

A  Validate each step against an allowlist of tools, enforce
   argument schemas, cap the number of steps, require approval
   for risky steps, and log the plan for auditing.
```

### 1.9 Tool Usage and Action Execution

```text
  ●  ●  ●   1.9 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is tool calling?

A  A mechanism where the model returns a structured request
   naming a tool and its arguments. The application executes
   the tool and returns the result to the model.
```

```text
  ●  ●  ●   1.9 · Q2                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Who is responsible for security when an LLM calls a tool?

A  The application. Treat model-generated arguments as untrusted
   input. Validate them, enforce authorization inside the tool
   itself, and never rely on the prompt alone for access control.
```

```text
  ●  ●  ●   1.9 · Q3                                  🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  What is the risk of giving an agent a generic "run SQL" tool?

A  It can run destructive or unauthorized queries, leak data, or
   be manipulated through prompt injection. Prefer narrow,
   parameterized tools with read-only defaults.
```

### 1.10 Single Agent vs Multi-Agent

```text
  ●  ●  ●   1.10 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  When would you choose a multi-agent design over a single agent?

A  When subtasks need distinct expertise or tools, can run in
   parallel, or benefit from separated responsibilities and
   independent evaluation.
```

```text
  ●  ●  ●   1.10 · Q2                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What are the main challenges of multi-agent systems?

A  Coordination overhead, communication failures, duplicated
   work, conflicting outputs, harder debugging, and higher cost
   and latency.
```

```text
  ●  ●  ●   1.10 · Q3                                 🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is an orchestrator or manager agent?

A  An agent or controller that assigns subtasks to worker
   agents, collects their results, and produces the final output.
```

### 1.11 Types of AI Agents

```text
  ●  ●  ●   1.11 · Q1                                 🟢 BASIC
────────────────────────────────────────────────────────────
Q  Name the five classical types of agents.

A  Simple reflex, model-based, goal-based, utility-based, and
   learning agents.
```

```text
  ●  ●  ●   1.11 · Q2                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is the difference between a goal-based and a utility-
   based agent?

A  A goal-based agent asks whether an action reaches the goal.
   A utility-based agent ranks actions by how well they satisfy
   multiple criteria, such as cost, time, and risk.
```

```text
  ●  ●  ●   1.11 · Q3                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Which type is closest to a modern LLM agent?

A  Goal-based and learning characteristics are most visible,
   since LLM agents pursue goals and can adapt through feedback.
   Most real systems combine several types.
```

### 1.12 Agent Architecture

```text
  ●  ●  ●   1.12 · Q1                                 🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How would you design an AI-powered monitoring system on
   Spring Boot?

A  Expose an investigation endpoint, run the agent
   asynchronously, keep tools as authorized service wrappers,
   persist state, log every tool call, and return a structured
   report. Add timeouts, retries, and approval gates for any
   write operations.
```

```text
  ●  ●  ●   1.12 · Q2                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Why should long-running agent tasks be asynchronous?

A  Agent runs can make many LLM and tool calls, so they can
   exceed HTTP timeouts. Asynchronous execution with a task ID
   and status polling or events keeps the API responsive.
```

```text
  ●  ●  ●   1.12 · Q3                                 🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How do you make agent workflows resilient to failures?

A  Persist state after each step so the run can resume, make
   tool calls idempotent, apply retries with backoff, and set
   timeouts on every external call.
```

```text
  ●  ●  ●   1.12 · Q4                                 🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How would you control LLM cost in production?

A  Set per-task token and tool-call budgets, cache repeated
   tool results, use smaller models for simple steps, and track
   usage per tenant or workflow.
```

### 1.13 Real-World Agent Use Cases

```text
  ●  ●  ●   1.13 · Q1                                 🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  Pick a use case and describe how you would design an agent
   for it.

A  Define the goal and success criteria, list the tools needed,
   decide what requires approval, design the state and
   termination conditions, and plan evaluation with test
   scenarios.
```

```text
  ●  ●  ●   1.13 · Q2                                 🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  What are the biggest risks of deploying agents in banking or
   healthcare?

A  Incorrect actions with financial or clinical impact, data
   privacy violations, prompt injection through untrusted
   documents, and lack of auditability. Mitigate with human
   approval, strict tool permissions, logging, and evaluation.
```

---

**Notes on the design:**
- Markdown can't force a pure black background. The windows use fenced code blocks, which render dark in GitHub dark mode, Notion, and most dark-themed Markdown viewers. In light mode they show as light gray.
- Lines are wrapped manually because code blocks don't wrap text on most platforms, so they stay readable on mobile.
- If you want a true black background with colored text, that requires HTML and CSS, which I can build as a standalone page or a Claude artifact.