# Module 1: AI Agents Fundamentals

## Table of Contents

| # | Topic | Link |
|---|---|---|
| 1.1 | What is AI and Generative AI? | [Go to 1.1](#11-what-is-artificial-intelligence) |
| 1.2 | What is an LLM? | [Go to 1.2](#12-what-is-an-llm) |
| 1.3 | What is an AI Agent? | [Go to 1.3](#13-what-is-an-ai-agent) |
| 1.4 | LLM vs AI Agent vs Agentic AI | [Go to 1.4](#14-llm-vs-ai-agent-vs-agentic-ai) |
| 1.5 | Characteristics of an AI Agent | [Go to 1.5](#15-characteristics-of-an-ai-agent) |
| 1.6 | Components of an AI Agent | [Go to 1.6](#16-core-components-of-an-ai-agent) |
| 1.7 | Agent Execution Loop | [Go to 1.7](#17-agent-execution-loop) |
| 1.8 | Reasoning and Planning | [Go to 1.8](#18-reasoning-and-planning) |
| 1.9 | Tool Usage and Action Execution | [Go to 1.9](#19-tool-usage-and-action-execution) |
| 1.10 | Single Agent vs Multi-Agent | [Go to 1.10](#110-single-agent-vs-multi-agent) |
| 1.11 | Types of AI Agents | [Go to 1.11](#111-types-of-ai-agents) |
| 1.12 | Agent Architecture | [Go to 1.12](#112-complete-agent-architecture-backend-example) |
| 1.13 | Real-World Agent Use Cases | [Go to 1.13](#113-real-world-agent-use-cases) |
| — | Interview Takeaways | [Go to takeaways](#module-1-interview-takeaways) |
| — | Interview Questions | [Go to questions](#interview-questions) |

Each section below builds on the previous one, with architecture diagrams and Java/backend examples.

---

## 1.1 What is Artificial Intelligence?

Artificial Intelligence (AI) is the ability of computer systems to perform tasks that typically require human intelligence.

**Examples:**
- Understanding human language
- Recognizing images
- Making decisions
- Solving problems
- Learning from data
- Predicting future outcomes

**Traditional AI examples:**

| Application | AI Capability |
|---|---|
| Face recognition | Image recognition |
| Spam detection | Classification |
| Fraud detection | Anomaly detection |
| Recommendation engine | Recommendation |
| ChatGPT | Language generation |

### AI Evolution

```
Artificial Intelligence
  └── Broad field of intelligent systems
        └── Machine Learning
              └── Learns patterns from data
                    └── Deep Learning
                          └── Neural networks with multiple layers
                                └── Generative AI
                                      └── Generates text, images, audio and code
                                            └── Agentic AI
                                                  └── Uses models, tools and workflows to accomplish goals
```

> **Note:** This is a conceptual progression, not a strict hierarchy. Agentic AI is an *application architecture* that can use generative models and other AI techniques.

---

## 1.2 What is an LLM?

**LLM** stands for **Large Language Model**. It is a machine learning model trained on large amounts of text and other data to understand and generate human-like language.

**Examples:** GPT, Gemini, Claude, Llama

### How an LLM Works

At a high level:

1. Receives input (prompt)
2. Converts input into tokens
3. Processes tokens through a neural network
4. Predicts the next token
5. Generates subsequent tokens
6. Returns the final response

**Example:**

```
Input:  Explain Java HashMap.

LLM processing:
  Prompt
    ↓
  Tokenization
    ↓
  Neural Network
    ↓
  Next-token prediction
    ↓
  Generated response

Output: HashMap is a Java collection that stores
        key-value pairs using hashing...
```

### Important Limitation of an LLM

An LLM does not inherently have access to your company's database, GitHub repository, production logs, or payment systems.

> **User:** "Check my application's production error logs."

An LLM without external tools cannot independently access your production logs. **This is where AI agents become useful.**

---

## 1.3 What is an AI Agent?

An AI agent is a system that uses an AI model to understand a task, decide what to do, execute actions through available tools, observe the results, and work towards a defined goal.

> **Key concept:** An LLM generates a response; an agent uses an LLM as part of a system that can take actions.

### Practical Example: Java Application Monitoring Agent

Suppose your Spring Boot application experiences slow API responses. You ask:

> *"Find the reason why the customer API takes 5 seconds to respond."*

An AI agent can:

1. Understand the problem
2. Identify the relevant API
3. Call the monitoring API
4. Retrieve application metrics
5. Query database performance metrics
6. Check Redis cache statistics
7. Analyze the findings
8. Recommend a solution

The agent is not just answering the question. It is performing a sequence of actions to achieve the requested objective.

---

## 1.4 LLM vs AI Agent vs Agentic AI

These three terms are frequently confused in interviews.

| Feature | LLM | AI Agent | Agentic AI System |
|---|---|---|---|
| Understands language | Yes | Yes | Yes |
| Generates responses | Yes | Yes | Yes |
| Uses external tools | Not inherently | Yes | Yes |
| Plans tasks | Not inherently | Can | Yes |
| Executes actions | Not inherently | Yes | Yes |
| Maintains workflow state | Not inherently | Can | Yes |
| Multiple agents | No | Not necessarily | Can |
| Autonomous execution | No, by itself | Limited or configurable | Configurable |

### Example: Banking Application

**Scenario:** Transfer ₹10,000 to another account.

**LLM application:**
> **User:** "How do I transfer ₹10,000?"
>
> The LLM explains the transfer process.

**AI Agent:**
> **User:** "Transfer ₹10,000 to account ending 1234."
>
> The agent can:
> 1. Identify the transfer request
> 2. Retrieve account details
> 3. Check balance
> 4. Prepare a transaction
> 5. Request user authorization
> 6. Execute the transaction using an authorized banking tool
> 7. Confirm the result

**Agentic AI system:**

A larger system could coordinate:
- Customer verification agent
- Fraud detection agent
- Transaction processing agent
- Compliance agent
- Notification agent

Each agent performs a specialized task.

> **Note:** A multi-agent system is not automatically better or more autonomous than a single agent. The architecture should depend on the problem.

---

## 1.5 Characteristics of an AI Agent

Understand these six characteristics.

### 1. Autonomy
An agent can perform multiple steps without requiring the user to specify every individual action.

> *"Find the root cause of API latency."*
>
> The user doesn't need to instruct it to check logs, metrics, and database queries separately.

### 2. Goal-Oriented Behavior
An agent works towards a specific objective.

> **Goal:** Reduce API response time from 5 seconds to below 1 second.
>
> The agent identifies bottlenecks and recommends improvements.

### 3. Reasoning and Decision-Making
An agent evaluates information and determines the next appropriate action.

- If database queries are slow → investigate indexing
- If cache hit ratio is low → investigate caching
- If application CPU is high → inspect application processing

### 4. Tool Utilization
An agent can interact with external systems, such as:
- REST APIs
- Databases
- GitHub
- Monitoring platforms
- Search engines
- File systems

### 5. Adaptability
An agent can change its execution strategy based on new information.

> The database metrics look healthy, so the agent changes its investigation to Redis and application logs.

### 6. Feedback-Driven Execution
An agent observes the result of its actions and decides whether additional work is required.

> The agent applies a proposed code fix in a test environment, runs the tests, and revises the code if tests fail.

| Characteristic | Example |
|---|---|
| Autonomy | Executes multiple steps |
| Goal-oriented | Solves a defined task |
| Reasoning | Selects an appropriate action |
| Tool utilization | Calls monitoring APIs |
| Adaptability | Changes investigation path |
| Feedback | Uses test results to improve |

> **Important:** These are *design capabilities*, not guarantees. An agent may have limited autonomy, and its reasoning can be incorrect.

---

## 1.6 Core Components of an AI Agent

This is one of the most important sections.

### Architecture Overview

```
                    User Input / Goal
                           │
                           ▼
┌──────────────────────────────────────────────────────┐
│                   Agent Controller                   │
│                                                      │
│  ┌─────────────┐  ┌──────────────┐  ┌─────────────┐  │
│  │ Instructions│  │Memory/Context│  │  Planning   │  │
│  │ Role &      │  │ Relevant     │  │ Task        │  │
│  │ constraints │  │ information  │  │ decomposition│ │
│  └─────────────┘  └──────────────┘  └─────────────┘  │
│                                                      │
│  ┌─────────────┐  ┌──────────────┐                   │
│  │   State     │  │  Guardrails  │                   │
│  │ Execution   │  │ Safety &     │                   │
│  │ progress    │  │ control      │                   │
│  └─────────────┘  └──────────────┘                   │
│                                                      │
│           ┌────────────────────────┐                 │
│           │  LLM: Reasoning &      │                 │
│           │  Decision Engine       │                 │
│           └────────────────────────┘                 │
└──────────────────────────────────────────────────────┘
          │                              ▲
          ▼                              │
┌──────────────────────┐    ┌────────────────────────┐
│ Action / Tool        │───▶│ Observation & Result   │
│ Execution            │    │ (fed back to the       │
│                      │    │  controller)           │
└──────────────────────┘    └────────────────────────┘
          │
          ▼
┌──────────────────────────────────────────────────────┐
│ External Systems                                     │
│ REST API • Database • GitHub • Redis • Monitoring    │
└──────────────────────────────────────────────────────┘
```

Let's look at each component.

### A. LLM: The Brain of the Agent

The LLM is responsible for understanding instructions, processing information, reasoning, and selecting actions. Examples include Gemini, GPT, Claude, and Llama.

> It does not automatically have permission or connectivity to execute external operations.

### B. Instructions: The Agent's Role

Instructions define what the agent should do and how it should behave.

```text
You are a senior Java code reviewer.

Responsibilities:
1. Analyze Java code.
2. Identify bugs.
3. Identify performance issues.
4. Identify security vulnerabilities.
5. Suggest improvements.

Rules:
- Follow Java best practices.
- Never modify production code.
- Explain every critical issue.
```

> Instructions are similar to defining the responsibilities and constraints of a service.

### C. Tools: The Agent's Hands

Tools enable an agent to interact with external systems.

| Tool | Purpose |
|---|---|
| GitHub API | Read repository code |
| SonarQube API | Analyze code quality |
| Jira API | Retrieve issues |
| Database query tool | Retrieve business data |
| Monitoring API | Retrieve metrics |
| Email API | Send approved emails |

**Example:**

```java
public class MonitoringTool {

    public String getApiMetrics(String apiName) {
        // Call monitoring REST API
        return "Average latency: 5000ms";
    }
}
```

> The LLM decides *when* to use the tool, but your application or framework executes the actual Java method. This separation is important for security and architecture.

### D. Memory: The Agent's Information Retention

Memory allows an agent to retain or retrieve relevant information across interactions.

| Memory Type | Purpose | Example |
|---|---|---|
| Short-term | Current conversation | Current user request |
| Long-term | Information retained across sessions | User preferences |
| Episodic | Past interaction experiences | Previous troubleshooting |
| Semantic | Facts and knowledge | Company API documentation |

For now, remember:
- **Context** is the information available to the model for its current processing.
- **Memory** is information retained or retrieved for use in current or future processing.

Memory is not necessarily the same as the LLM's context window. We will study this in detail in Module 3.

### E. Planning: The Agent's Task Strategy

Planning means breaking a large objective into smaller tasks.

> **Goal:** Analyze application performance.
>
> 1. Identify slow APIs
> 2. Retrieve API metrics
> 3. Inspect database queries
> 4. Check Redis metrics
> 5. Identify bottlenecks
> 6. Generate recommendations

The plan may be predefined by developers or dynamically generated by the LLM.

### F. State: Current Execution Progress

State represents the information maintained during workflow execution.

```json
{
  "taskId": "TASK-101",
  "apiName": "/customer",
  "currentStep": "DATABASE_ANALYSIS",
  "apiLatency": 5000,
  "databaseAnalysisCompleted": true,
  "redisAnalysisCompleted": false,
  "status": "IN_PROGRESS"
}
```

The agent uses this state to understand what has already happened and what needs to happen next.

**Memory vs State:**

| Memory | State |
|---|---|
| Retains useful information | Tracks current execution |
| Can span multiple sessions | Usually tied to a workflow or task |
| Can contain user preferences | Contains task progress and intermediate results |
| Example: Previous incident details | Example: Database analysis completed |

### G. Guardrails: Safety and Control

Guardrails enforce restrictions around agent behavior.

- Do not expose confidential information
- Do not execute unauthorized SQL
- Do not delete production records
- Do not exceed a specified transaction amount
- Require human approval for sensitive operations

> Guardrails can exist at the input, output, tool, and application levels.

---

## 1.7 Agent Execution Loop

> **Very important for interviews.**

The agent execution loop is the repeated process through which an agent understands a task, reasons, takes action, observes results, and decides what to do next. This pattern is often called **ReAct** (Reason + Act).

### Conceptual Model

```
Perceive → Reason → Plan → Act → Observe → Evaluate
                                              │
                        ┌─────────────────────┴──────────────────┐
                        ▼                                        ▼
                 Goal achieved?                          Goal not achieved?
                        │                                        │
                 Return final result                  Update plan and repeat
```

| Stage | Purpose |
|---|---|
| 1. Perceive | Understand user input and environment |
| 2. Reason | Analyze the objective and available information |
| 3. Plan | Choose the next step |
| 4. Act | Execute a tool or action |
| 5. Observe | Receive and interpret the execution result |
| 6. Evaluate | Determine whether the goal is achieved |

> **Note:** This is a conceptual loop. Actual frameworks may combine, reorder, or omit some stages.

### Practical Execution Example

> **User:** "Find why my Spring Boot API is slow."

| Iteration | Agent Action | Result |
|---|---|---|
| 1 | Understand request | API performance issue |
| 2 | Call monitoring API | Latency = 5 seconds |
| 3 | Inspect database metrics | Query takes 4 seconds |
| 4 | Inspect SQL execution plan | Missing index identified |
| 5 | Evaluate result | Likely bottleneck found |
| 6 | Generate report | Recommend index optimization |

The agent does not need to execute every possible tool. It selects relevant actions based on the information available.

### What If the Goal Is Not Achieved?

Suppose the agent identifies a database bottleneck but cannot access the execution plan. It may:

1. Try another diagnostic tool
2. Request additional permissions
3. Ask the user for missing information
4. Stop and report that the investigation is incomplete

**A good agent must have termination conditions**, such as:
- Maximum number of iterations
- Execution timeout
- Token budget
- Maximum tool calls
- Human approval requirement

---

## 1.8 Reasoning and Planning

Reasoning and planning are related but different.

- **Reasoning:** Understanding the problem and deciding what is appropriate.
- **Planning:** Creating an execution strategy to accomplish the objective.

> **User:** "Prepare a production deployment readiness report."
>
> **Reasoning:**
> - Need deployment information
> - Need test results
> - Need security checks
> - Need infrastructure status
>
> **Planning:**
> 1. Retrieve deployment details
> 2. Retrieve CI/CD test results
> 3. Check security scan results
> 4. Verify infrastructure health
> 5. Aggregate findings
> 6. Generate readiness report

### Important Planning Approaches

| Approach | Explanation |
|---|---|
| Predefined planning | Developer defines the execution steps |
| Dynamic planning | LLM decides the next steps |
| Replanning | Agent modifies its plan based on results |
| Hierarchical planning | Breaks a large task into subtasks |

> **Key point:** An LLM can generate a plan, but that does not mean the plan is correct. Production systems need validation, execution constraints, and recovery mechanisms.

---

## 1.9 Tool Usage and Action Execution

An agent without tools is limited to the information available in its prompt and model context. Tools make agents useful in real-world applications.

### Example: Tool Calling

Suppose the agent has two tools:

```
getApiMetrics(apiName)
getDatabaseMetrics(queryId)
```

> **User:** "Check the customer API performance."

The agent may select:

```
Tool:     getApiMetrics
Argument: apiName = "/customer"
```

The application executes the function and returns:

```json
{
  "api": "/customer",
  "averageLatency": 5000,
  "status": "SLOW"
}
```

The agent then decides whether it needs database metrics.

### Function Calling vs Tool Calling

These terms are closely related:

- **Function calling:** The model produces a structured request to invoke a function.
- **Tool calling:** The broader mechanism of invoking an external capability, such as a function, API, or search operation.

The model generally selects the tool and supplies the arguments; the application validates and executes it.

> **Important:** Tool calling does not mean the LLM directly executes arbitrary code. Think of it like a Java interface: the model chooses which method to call and with what arguments, but your application provides the implementation and enforces its own checks.

---

## 1.10 Single Agent vs Multi-Agent

| Single-Agent Architecture | Multi-Agent Architecture |
|---|---|
| One agent handles the task | Multiple specialized agents |
| Simpler orchestration | More complex orchestration |
| Less communication overhead | Agent communication required |
| Easier debugging | More complex debugging |
| Suitable for focused tasks | Suitable for decomposable, complex tasks |

**Single-agent example:** One Java code reviewer that reads code, identifies bugs, checks security, and generates recommendations.

**Multi-agent example:**
- Code analysis agent
- Security agent
- Performance agent
- Testing agent
- Manager agent (combines their results)

### When to Choose Multi-Agent Architecture

**Use multiple agents when:**
- Tasks require different specialized capabilities
- Independent tasks can execute in parallel
- Responsibilities need clear separation
- Complex workflows benefit from delegation

**Avoid it when:**
- One agent can solve the problem
- The workflow is simple and deterministic
- Extra LLM calls add unnecessary cost and latency

---

## 1.11 Types of AI Agents

There is a classical AI classification that is useful for understanding agent concepts. Do not confuse it with modern frameworks such as Google ADK or LangGraph.

| Type | Working Mechanism | Example |
|---|---|---|
| Simple reflex agent | Acts based on the current condition | If temperature is high, turn on cooling |
| Model-based agent | Maintains an internal model of the environment | Robot navigation |
| Goal-based agent | Selects actions to achieve a goal | Route planning |
| Utility-based agent | Chooses actions based on a utility measure | Cost-efficient route selection |
| Learning agent | Improves using experience or feedback | Recommendation system |

### 1. Simple Reflex Agent
Uses condition-action rules.

```java
if (temperature > 30) {
    turnOnCooling();
}
```

### 2. Model-Based Agent
Maintains information about the environment. Example: a robot tracks previously visited locations and current obstacles.

### 3. Goal-Based Agent
Selects actions based on a target. Example: find the shortest route from Hyderabad to Vijayawada.

### 4. Utility-Based Agent
Evaluates alternatives based on a utility function. Example: select a route that balances travel time, fuel consumption, and toll charges.

### 5. Learning Agent
Uses feedback or learning mechanisms to improve future behavior. Example: a recommendation system improves its suggestions based on user interactions.

> **Interview point:** These are classical intelligent-agent categories. Modern LLM agents do not necessarily fit into only one category.

---

## 1.12 Complete Agent Architecture: Backend Example

Now let's connect everything to Java development experience.

**Scenario:** Build an AI-powered application monitoring system.

### Architecture Diagram

```
┌──────────────┐
│   Angular    │  User requests an investigation
│   Frontend   │
└──────┬───────┘
       │
       ▼
┌──────────────────────────────────────────┐
│  Spring Boot REST API                    │
│  Authentication and request validation   │
└──────┬───────────────────────────────────┘
       │
       ▼
┌──────────────────────────────────────────┐
│  AI Agent Runtime                        │
│  ┌──────┐  Reasoning & decision-making   │
│  │ LLM  │  Instructions                 │
│  └──────┘  State / Memory                │
│            Planning                      │
│            Guardrails                    │
└──────┬───────────────────────────────────┘
       │
       ▼
┌──────────────────────────────────────────┐
│  Tool Execution Layer                    │
│  • Prometheus   → Metrics                │
│  • Grafana      → Monitoring             │
│  • PostgreSQL   → Database metrics       │
│  • Redis        → Cache metrics          │
└──────┬───────────────────────────────────┘
       │
       ▼
┌──────────────────────────────────────────┐
│  Analysis Report                         │
│  Root cause, evidence, and recommendations│
└──────────────────────────────────────────┘
```

### Request Flow

1. Angular sends a request to Spring Boot.
2. Spring Boot authenticates and validates the user.
3. The agent runtime receives the investigation goal.
4. The LLM determines which diagnostic tools to use.
5. The tool execution layer calls authorized backend services.
6. Results are returned to the agent.
7. The agent analyzes the results.
8. The final report is returned to Spring Boot.
9. Angular displays the findings.

### Important Production Architecture Decisions

- Use asynchronous execution for long-running investigations.
- Persist workflow state so tasks can resume after a failure.
- Use Redis or a database depending on state and caching requirements.
- Use Kafka when asynchronous, event-driven integration is beneficial.
- Add retries, timeouts, and idempotency.
- Track LLM calls, token usage, and tool execution.
- Restrict access to production tools.
- Require approval before executing sensitive actions.

---

## 1.13 Real-World Agent Use Cases

| Domain | Agent Application |
|---|---|
| Software engineering | Code generation and code review |
| DevOps | Deployment investigation |
| Banking | Fraud investigation |
| Healthcare | Medical document assistance |
| E-commerce | Customer support automation |
| Finance | Financial document analysis |
| Enterprise | Contract lifecycle automation |
| IT operations | Incident management |

For example, an IT incident management agent can retrieve logs, investigate alerts, identify probable causes, create incident tickets, and recommend remediation.

---

## Module 1: Interview Takeaways

Make sure you can explain these concepts without looking at notes.

| Interview Question | Key Answer |
|---|---|
| What is an AI agent? | An LLM-powered system that can reason, use tools, and execute actions towards a goal. |
| What is the agent loop? | Perceive → Reason → Plan → Act → Observe → Evaluate. |
| What is the role of an LLM? | Understands instructions and helps determine actions. |
| What are tools? | External capabilities that allow agents to interact with systems. |
| What is agent memory? | Retained or retrieved information useful across interactions. |
| What is agent state? | Current workflow execution information. |
| What is planning? | Breaking an objective into executable steps. |
| What is autonomy? | Ability to execute tasks with limited human intervention. |
| What is a multi-agent system? | Multiple agents collaborating on a larger task. |
| What are guardrails? | Restrictions and validation mechanisms that control agent behavior. |
| Who executes a tool? | The application, not the LLM. The LLM only requests it. |
| When should you avoid agents? | When the task is simple, deterministic, or needs guaranteed correctness without human review. |

---

## Interview Questions

### 1.1 What is AI and Generative AI?

**Q1. What is the difference between AI, Machine Learning, and Deep Learning?**
AI is the broad goal of building systems that perform intelligent tasks. Machine Learning is a subset of AI where systems learn patterns from data instead of following hand-written rules. Deep Learning is a subset of ML that uses neural networks with many layers.

**Q2. What is Generative AI?**
Generative AI refers to models that create new content such as text, images, audio, or code, based on patterns learned from training data.

**Q3. Is Agentic AI the same as Generative AI?**
No. Generative AI produces content. Agentic AI is an architecture in which a model is combined with tools, memory, and workflows to pursue a goal through actions.

### 1.2 What is an LLM?

**Q1. What is an LLM and how does it generate text?**
An LLM is a neural network trained on large text corpora. It converts the prompt into tokens, then repeatedly predicts the most likely next token until it produces a complete response.

**Q2. What is a token?**
A token is a unit of text the model processes, often a word, part of a word, or punctuation. Token count determines input and output size, cost, and latency.

**Q3. Why can't an LLM check your production logs by itself?**
It only processes text in its context and has no built-in connectivity. Access requires tools that the application exposes to it.

**Q4. Does an LLM "know" facts or "predict" them?**
It predicts likely token sequences based on learned patterns. This is why it can produce fluent but incorrect answers (hallucinations), and why grounding with retrieved data or tools matters.

### 1.3 What is an AI Agent?

**Q1. What is an AI agent?**
An LLM-powered system that reasons about a goal, uses tools to take actions, observes results, and iterates until the goal is met or it can no longer proceed.

**Q2. How is an agent different from a chatbot?**
A chatbot mainly responds to messages. An agent plans and executes actions through tools, maintains workflow state, and works toward an objective with limited step-by-step instruction.

**Q3. Give a real example of an agent in a Java/backend context.**
An incident-investigation agent that calls monitoring APIs, queries the database, checks Redis metrics, and produces a root-cause report.

### 1.4 LLM vs AI Agent vs Agentic AI

**Q1. Explain the difference between an LLM, an AI agent, and agentic AI.**
An LLM generates text. An AI agent wraps an LLM with tools and a control loop so it can act. Agentic AI is the broader system design, which may include multiple agents, planning, memory, and guardrails.

**Q2. Can an LLM be an agent without tools?**
Not in the usual sense. Without tools or actions, it is an LLM application, not an agent, because it cannot change or observe anything outside the conversation.

**Q3. Does a multi-agent system always perform better?**
No. It adds orchestration complexity, latency, and cost. It is useful only when the task decomposes into specialized, parallelizable parts.

### 1.5 Characteristics of an AI Agent

**Q1. What makes an agent "autonomous"? Is full autonomy desirable?**
Autonomy means executing multiple steps without per-step instructions. Full autonomy is rarely desirable in production. Sensitive actions should require human approval, and autonomy should be bounded by guardrails and termination conditions.

**Q2. How do you verify an agent's reasoning?**
Validate tool outputs, check intermediate results against expected schemas, run evaluations on test cases, log every decision, and require confirmation for high-impact actions.

### 1.6 Components of an AI Agent

**Q1. What are the main components of an AI agent?**
The LLM (reasoning), instructions (role and constraints), tools (actions), memory (retained information), planning (task strategy), state (execution progress), and guardrails (safety controls).

**Q2. What is the difference between memory and state?**
Memory holds information that may be useful across tasks or sessions, such as user preferences. State tracks the progress of one workflow, such as which steps have completed.

**Q3. Who executes a tool: the LLM or the application?**
The application. The LLM selects the tool and arguments. The application validates the request, checks permissions, executes the code, and returns the result.

**Q4. Where do guardrails belong in the architecture?**
At multiple layers: validating input, filtering output, restricting which tools are available and what arguments they accept, and requiring approval for sensitive operations.

### 1.7 Agent Execution Loop

**Q1. Walk me through the agent execution loop.**
Perceive the input, reason about the goal, plan the next step, act through a tool, observe the result, and evaluate whether the goal is met. If not, update the plan and repeat.

**Q2. What is ReAct?**
ReAct (Reason + Act) is a prompting and agent pattern where the model alternates between reasoning about the next step and taking an action, then uses the observation to inform the next reasoning step.

**Q3. Why do agents need termination conditions?**
Without them, an agent can loop indefinitely, consume tokens and money, or repeatedly call tools with no progress. Limits such as max iterations, timeouts, and tool-call budgets prevent this.

**Q4. How would you handle a tool that fails mid-loop?**
Return the error as an observation, let the agent retry with backoff if the failure is transient, switch to an alternative tool if one exists, and escalate to a human or stop with a partial report if it keeps failing.

### 1.8 Reasoning and Planning

**Q1. What is the difference between reasoning and planning?**
Reasoning interprets the situation and decides what matters. Planning turns that understanding into an ordered set of actions.

**Q2. When would you use predefined planning instead of dynamic planning?**
When the workflow is well understood, compliance matters, or predictability and testability are more important than flexibility. Dynamic planning suits open-ended tasks, but it is harder to test and audit.

**Q3. How do you make an LLM-generated plan safe to execute?**
Validate each step against an allowlist of tools, enforce argument schemas, cap the number of steps, require approval for risky steps, and log the plan for auditing.

### 1.9 Tool Usage and Action Execution

**Q1. What is tool calling?**
A mechanism where the model returns a structured request naming a tool and its arguments. The application executes the tool and returns the result to the model.

**Q2. Who is responsible for security when an LLM calls a tool?**
The application. Treat model-generated arguments as untrusted input. Validate them, enforce authorization in the tool itself, and never rely on the prompt alone for access control.

**Q3. What is the risk of giving an agent a generic "run SQL" tool?**
It can run destructive or unauthorized queries, leak data, or be manipulated through prompt injection. Prefer narrow, parameterized tools with read-only defaults.

### 1.10 Single Agent vs Multi-Agent

**Q1. When would you choose a multi-agent design over a single agent?**
When subtasks need distinct expertise or tools, can run in parallel, or benefit from separated responsibilities and independent evaluation.

**Q2. What are the main challenges of multi-agent systems?**
Coordination overhead, communication failures, duplicated work, conflicting outputs, harder debugging, and higher cost and latency.

**Q3. What is an orchestrator or manager agent?**
An agent or controller that assigns subtasks to worker agents, collects their results, and produces the final output.

### 1.11 Types of AI Agents

**Q1. Name the five classical types of agents.**
Simple reflex, model-based, goal-based, utility-based, and learning agents.

**Q2. What is the difference between a goal-based and a utility-based agent?**
A goal-based agent asks whether an action reaches the goal. A utility-based agent ranks actions by how well they satisfy multiple criteria, such as cost, time, and risk.

**Q3. Which type is closest to a modern LLM agent?**
Goal-based and learning characteristics are most visible, since LLM agents pursue goals and can adapt through feedback. Most real systems combine several types.

### 1.12 Agent Architecture

**Q1. How would you design an AI-powered monitoring system on Spring Boot?**
Expose an investigation endpoint, run the agent asynchronously, keep tools as authorized service wrappers, persist state, log every tool call, and return a structured report. Add timeouts, retries, and approval gates for any write operations.

**Q2. Why should long-running agent tasks be asynchronous?**
Agent runs can take many LLM calls and tool calls, so they can exceed HTTP timeouts. Asynchronous execution with a task ID and status polling or events keeps the API responsive.

**Q3. How do you make agent workflows resilient to failures?**
Persist state after each step so the run can resume, make tool calls idempotent, apply retries with backoff, and set timeouts on every external call.

**Q4. How would you control LLM cost in production?**
Set per-task token and tool-call budgets, cache repeated tool results, use smaller models for simple steps, and track usage per tenant or workflow.

### 1.13 Real-World Agent Use Cases

**Q1. Pick a use case and describe how you would design an agent for it.**
Define the goal and success criteria, list the tools needed, decide what requires approval, design the state and termination conditions, and plan evaluation with test scenarios.

**Q2. What are the biggest risks of deploying agents in banking or healthcare?**
Incorrect actions with financial or clinical impact, data privacy violations, prompt injection through untrusted documents, and lack of auditability. Mitigate with human approval, strict tool permissions, logging, and evaluation.