# Module 5: Agent Planning, Reasoning & Orchestration

> **Priority:** Must learn for Senior / Lead Engineer preparation.

In the previous modules, we learned:

- **Module 1:** Agent fundamentals and architecture
- **Module 2:** Tools, function calling, and MCP
- **Module 3:** Memory, state management, and context engineering
- **Module 4:** RAG architecture and knowledge retrieval

Now we address a central question in Agentic AI:

> **How does an AI agent decide what to do, break a complex goal into smaller tasks, execute those tasks, evaluate the results, and recover from failures?**

Imagine you build an AI Production Support Agent. A user asks:

> *"My Payment Service response time increased from 200 ms to 4 seconds after yesterday's deployment. Investigate the root cause and prepare an incident report."*

This is not a single-step question. The agent may need to:

1. Understand the objective
2. Break it into smaller tasks
3. Check deployment history
4. Retrieve application metrics
5. Inspect database connection pools
6. Analyze slow queries
7. Compare previous incidents
8. Determine the likely root cause
9. Generate an incident report

The mechanisms that coordinate these actions are **agent planning** and **orchestration**.

## Table of Contents

| # | Topic | Link |
|---|---|---|
| 5.1 | What is Agent Planning? | [Go to 5.1](#51-what-is-agent-planning) |
| 5.2 | Reasoning vs Planning vs Execution | [Go to 5.2](#52-reasoning-vs-planning-vs-execution) |
| 5.3 | Task Decomposition | [Go to 5.3](#53-task-decomposition) |
| 5.4 | Types of Agent Planning | [Go to 5.4](#54-types-of-agent-planning) |
| 5.5 | ReAct: Reasoning + Acting | [Go to 5.5](#55-react--reasoning--acting) |
| 5.6 | Reflection and Self-Correction | [Go to 5.6](#56-reflection-and-self-correction) |
| 5.7 | Workflow Orchestration | [Go to 5.7](#57-workflow-orchestration) |
| 5.8 | Sequential vs Parallel Execution | [Go to 5.8](#58-sequential-vs-parallel-execution) |
| 5.9 | Conditional Routing | [Go to 5.9](#59-conditional-routing) |
| 5.10 | Retry, Timeout, and Failure Recovery | [Go to 5.10](#510-retry-timeout-and-failure-recovery) |
| 5.11 | Human-in-the-Loop (HITL) | [Go to 5.11](#511-human-in-the-loop-hitl) |
| 5.12 | Workflow Patterns | [Go to 5.12](#512-workflow-patterns-you-should-know) |
| 5.13 | Single-Agent vs Multi-Agent Orchestration | [Go to 5.13](#513-single-agent-vs-multi-agent-orchestration) |
| 5.14 | Agent Orchestration Frameworks | [Go to 5.14](#514-agent-orchestration-frameworks) |
| 5.15 | Building an Agent Orchestrator in Java | [Go to 5.15](#515-building-an-agent-orchestrator-using-java) |
| 5.16 | Durable Execution and Distributed Orchestration | [Go to 5.16](#516-durable-workflow-execution-and-distributed-orchestration) |
| 5.17 | Complete Agent Execution Example | [Go to 5.17](#517-putting-it-all-together--complete-agent-execution) |
| 5.18 | Common Orchestration Problems | [Go to 5.18](#518-common-agent-orchestration-problems) |
| 5.19 | Practical Assignment | [Go to 5.19](#519-practical-assignment--build-an-agent-orchestration-system) |
| — | Interview Takeaways | [Go to takeaways](#module-5-interview-takeaways) |
| — | Interview Questions | [Go to questions](#interview-questions) |

---

## 5.1 What is Agent Planning?

**Agent planning** is the process through which an AI agent determines the sequence of actions needed to achieve a goal.

A simple LLM request:

```text
User: What is Redis?
LLM:  Redis is an in-memory data store...
```

A complex agent request:

```text
User: Investigate why my Spring Boot API is slow.

Agent:
  1. Check API metrics.
  2. Inspect application logs.
  3. Check database metrics.
  4. Identify bottlenecks.
  5. Compare findings.
  6. Recommend corrective action.
```

The second example requires planning.

### Planning Architecture

```text
User Goal: Investigate Payment API latency
      │
      ▼
Agent Reasoning      Understand objective and constraints
      │
      ▼
Planning             Decompose goal into subtasks
      │
      ├── Task 1: Check API metrics
      ├── Task 2: Check deployment
      ├── Task 3: Check DB health
      └── Task 4: Analyze logs
      │
      ▼
Execution + Observation   Execute tasks and inspect results
      │
      ▼
Final Result              Diagnosis and incident report
```

---

## 5.2 Reasoning vs Planning vs Execution

These three concepts are related but different.

| Concept | Meaning | Example |
|---|---|---|
| Reasoning | Understand and evaluate the problem | API latency may be caused by DB issues |
| Planning | Decide the tasks and their order | Check metrics, then DB, then logs |
| Execution | Perform the selected actions | Call monitoring and database tools |

**Example:** *Find why the Payment API is slow.*

- **Reasoning:** "The issue began after deployment, so I should investigate deployment changes and resource utilization."
- **Planning:** "First compare deployment versions, then inspect latency and database metrics."
- **Execution:** Call the deployment-history and monitoring tools.

> The LLM may perform reasoning and propose a plan, while the application or agent framework controls execution.

---

## 5.3 Task Decomposition

**Task decomposition** means splitting a large objective into smaller, manageable subtasks.

```text
Goal: Investigate Payment API latency

  Task 1: Collect API metrics
  Task 2: Check deployment changes
  Task 3: Inspect application logs
  Task 4: Check database connection pool
  Task 5: Inspect slow queries
  Task 6: Compare previous incidents
  Task 7: Identify probable root cause
  Task 8: Generate report
```

### Why Decomposition Matters

- Makes complex tasks manageable
- Enables parallel execution
- Improves error isolation
- Supports progress tracking
- Makes retries possible at the task level
- Helps control execution cost

### Hierarchical Task Decomposition

Sometimes even a subtask is complex. A parent task is decomposed into child tasks until each one is actionable.

```text
Investigate Database Performance
  │
  ├── Check Connection Pool
  │     ├── Active connections
  │     ├── Idle connections
  │     └── Pending threads
  │
  ├── Check SQL Queries
  │     ├── Slow queries
  │     ├── Execution plans
  │     └── Index usage
  │
  └── Check Database Health
        ├── CPU
        ├── Memory
        └── Locks
```

This is called **hierarchical planning**.

---

## 5.4 Types of Agent Planning

### 5.4.1 Predefined Planning

The sequence of actions is defined in advance.

```text
Step 1 → Fetch API metrics
Step 2 → Fetch DB metrics
Step 3 → Analyze both
Step 4 → Generate report
```

The agent does not freely decide the entire workflow.

**Use when:** the process is business-critical, predictable, compliance-sensitive, or governed by a defined approval process.

### 5.4.2 Dynamic Planning

The agent decides what to do based on the current situation.

```text
Check API metrics
      │
      ▼
High latency found?
      │
     YES
      │
      ▼
Check database metrics
      │
      ▼
DB healthy?
   ┌──┴───┐
  YES     NO
   │       │
Check    Investigate
logs     DB
```

**Use when:** troubleshooting, research, complex investigations, or tasks with uncertain outcomes.

### 5.4.3 Replanning

Replanning changes the existing plan when new information becomes available.

```text
Initial plan:
  1. Check database
  2. Check application logs
  3. Generate report

Observation: Database is healthy. No connection pool exhaustion.

Updated plan:
  1. Inspect downstream service latency
  2. Check network errors
  3. Analyze application traces
  4. Generate report
```

Replanning prevents the agent from continuing with irrelevant actions.

### 5.4.4 Hierarchical Planning

Breaks the objective into levels of subtasks. Useful for large, multi-stage enterprise workflows.

### 5.4.5 Goal-Based Planning

The agent focuses on achieving a defined outcome, rather than blindly executing a fixed sequence.

```text
Goal:        Reduce Payment API latency below 500 ms

Constraints:
  - No production restart without approval
  - Do not modify production configuration automatically
  - Provide evidence for every recommendation
```

The agent chooses suitable diagnostic and corrective actions while respecting the constraints.

> **Note:** These categories are useful architectural patterns. A specific framework may implement them differently.

---

## 5.5 ReAct — Reasoning + Acting

> **Very important interview topic.**

**ReAct** combines reasoning and action. Instead of answering immediately, the model alternates between deciding what to do and observing the results of its actions.

```text
Reason
  │
  ▼
Choose action
  │
  ▼
Execute tool
  │
  ▼
Observe result
  │
  ▼
Reason again
  │
  ├── Continue (another action)
  └── Final answer
```

### Example: Database Troubleshooting

> **User:** "Find why my Payment API is slow."

| Step | Content |
|---|---|
| Reason | I need to check application performance metrics first. |
| Action | Call `getApiMetrics` |
| Observation | Average latency 4.2 s, P95 latency 8 s |
| Reason | The API is slow. Database connection utilization may be a cause. |
| Action | Call `getDatabaseMetrics` |
| Observation | Active connections 95%, pending threads 30 |
| Reason | Connection pool exhaustion is possible. I should inspect slow queries. |
| Action | Call `getSlowQueries` |
| Observation | Several long-running SQL queries found |
| Final | Present the evidence, likely cause, and remaining uncertainty |

### ReAct vs Simple LLM Response

| Simple LLM | ReAct Agent |
|---|---|
| Answers from supplied context | Obtains new observations through tools |
| Usually a single response | Multiple reasoning and action cycles |
| No inherent external action | Executes permitted tools |
| Limited dynamic investigation | Adapts its next action |

> **Important:** ReAct does not mean exposing private chain-of-thought. In production, log concise decisions, tool calls, observations, and evidence rather than hidden internal reasoning.

---

## 5.6 Reflection and Self-Correction

**Reflection** means evaluating the result of an action or completed task and deciding whether the outcome is satisfactory. An agent should not assume every tool call produces a correct result.

```text
Task:        Find the cause of API latency
Action:      Check database connection metrics
Observation: Connection pool utilization = 35%
Evaluation:  Database connection exhaustion is unlikely
Next:        Investigate downstream service latency
```

### Reflection Workflow

```text
Execute task
     │
     ▼
Observe result
     │
     ▼
Evaluate result
     │
     ▼
Satisfied?
  ┌──┴───┐
 YES      NO
  │        │
Continue  Revise plan
or finish and retry
```

### Reflection vs Replanning

| Reflection | Replanning |
|---|---|
| Evaluates the result | Changes the plan |
| Asks whether the result is acceptable | Decides what to do next |
| May conclude the task is complete | Produces an updated sequence |

They are often used together.

### Self-Correction Example

An agent generates a database optimization recommendation. A validation step finds that the recommendation references an index that does not exist. The agent can:

1. Identify the unsupported recommendation
2. Retrieve the actual database schema
3. Revise the recommendation
4. Validate the revised result

> **Senior insight:** Reflection improves reliability only when the agent has meaningful evidence or validation criteria. Asking an LLM to review its own answer without independent evidence does not guarantee correctness.

---

## 5.7 Workflow Orchestration

**Planning** decides *what* needs to happen. **Orchestration** coordinates *how* those tasks actually happen.

Workflow orchestration manages:

- Task execution
- Task dependencies
- Sequential and parallel processing
- Conditional routing
- State transitions
- Retries and timeouts
- Failure handling
- Human approvals
- Final result aggregation

### Example: Enterprise Incident Investigation

```text
Start
  │
  ▼
Fetch API Metrics
  │
  ├──────────────────────┐
  ▼                      ▼
Fetch DB Metrics     Fetch Deployment Data
  │                      │
  └──────────┬───────────┘
             ▼
       Aggregate Results
             │
             ▼
       Analyze Findings
             │
             ▼
       Generate Report
             │
             ▼
            End
```

The orchestration layer ensures both metric-retrieval tasks finish before analysis begins.

---

## 5.8 Sequential vs Parallel Execution

### 5.8.1 Sequential Execution

Tasks run one after another.

```text
Task A → Task B → Task C → Task D
```

Example:

1. Fetch deployment information
2. Use the deployment version to fetch relevant logs
3. Analyze logs
4. Generate report

Task B depends on Task A's result, so the tasks cannot run independently.

### 5.8.2 Parallel Execution

Independent tasks run at the same time.

```text
                 Start
                   │
     ┌─────────────┼─────────────┐
     ▼             ▼             ▼
  API Metrics   DB Metrics   Deployment History
     │             │             │
     └─────────────┼─────────────┘
                   ▼
              Aggregate
                   │
                   ▼
                Analyze
```

### Sequential vs Parallel

| Factor | Sequential | Parallel |
|---|---|---|
| Execution | One after another | Concurrently |
| Latency | Usually higher for independent tasks | Can be lower |
| Complexity | Simpler | Requires coordination |
| Dependencies | Naturally supported | Must be handled explicitly |
| Failure handling | Easier to isolate | Partial failures need aggregation |

### Performance Example

Three independent calls take 2 s, 3 s, and 4 s.

- Sequential: T = 2 + 3 + 4 = **9 seconds**
- Parallel (all started together): T ≈ max(2, 3, 4) = **4 seconds**

> *Illustrative ideal timing. It excludes orchestration overhead and resource contention.*

> **Interview point:** Parallelize independent operations. Do not parallelize tasks with dependencies or unsafe side effects.

---

## 5.9 Conditional Routing

**Conditional routing** chooses the next execution path based on a condition or observation.

```text
Check API latency
       │
       ▼
Is latency > 1 second?
   ┌───┴───┐
  YES      NO
   │        │
   ▼        ▼
Check DB  Return
metrics   healthy status
```

### Enterprise Example

```text
If database pool utilization > 90%:
    Investigate connection pool
Else if downstream service latency is high:
    Investigate downstream service
Else:
    Inspect application CPU, threads, and logs
```

Conditional routing is useful when workflows have different paths, some actions are needed only under specific conditions, different tools or specialists are required, or business policies determine the next step.

### Where Should the Condition Be Evaluated?

- **Deterministic code** for clear business rules and numeric thresholds
- **An LLM** for ambiguous interpretation or decisions requiring language understanding
- **Policy and authorization layers** to validate high-impact decisions

---

## 5.10 Retry, Timeout, and Failure Recovery

In production, tools and external services fail. Common examples:

- Monitoring API timeout
- Database connection error
- LLM provider rate limit
- Vector database unavailable
- Invalid tool arguments
- Partial execution failure

A reliable agent architecture must handle all of these.

### 5.10.1 Retry

Retry means attempting a failed operation again.

```text
Call monitoring API → Failed → Retry 1 → Failed → Retry 2 → Success
```

> Do not retry indefinitely. Set a maximum number of attempts.

### 5.10.2 Exponential Backoff

Increase the delay between retry attempts:

```text
Delay(n) = min(MaxDelay, BaseDelay × 2ⁿ)
```

With a 1-second base delay:

| Retry | Delay |
|---|---|
| 1 | 1 second |
| 2 | 2 seconds |
| 3 | 4 seconds |
| 4 | 8 seconds |

Add **jitter** (a small random variation) so many clients do not retry at the same instant.

### 5.10.3 Timeout

A timeout defines the maximum duration allowed for an operation.

```text
Monitoring API timeout = 5 seconds
```

If the call takes longer, the application stops waiting and handles the failure.

### 5.10.4 Circuit Breaker

A circuit breaker stops repeated calls to a failing downstream service.

```text
CLOSED     Requests flow normally
   │ failures exceed threshold
   ▼
OPEN       Calls are rejected quickly
   │ wait period elapses
   ▼
HALF-OPEN  Limited test calls are allowed
   │
   ├── Success ──▶ CLOSED
   └── Failure ──▶ OPEN
```

### 5.10.5 Idempotency

**Idempotency** means repeating an operation does not create unintended additional effects.

If a "create payment" request times out, blindly retrying could create a duplicate payment. An idempotency key prevents this:

```text
Idempotency-Key: PAYMENT-REQUEST-1001
```

The backend recognizes the same operation and does not process it twice.

### 5.10.6 Checkpointing

**Checkpointing** means persisting workflow progress at important stages.

```json
{
  "taskId": "TASK-1001",
  "status": "IN_PROGRESS",
  "completedTasks": ["FETCH_API_METRICS", "FETCH_DB_METRICS"],
  "pendingTasks": ["ANALYZE_RESULTS", "GENERATE_REPORT"]
}
```

If the application crashes, the workflow can resume from the last valid checkpoint.

> **Senior takeaway:** Reliable orchestration requires more than retrying an LLM request. You need durable state, task-level failure handling, idempotency, timeouts, and clear recovery policies.

---

## 5.11 Human-in-the-Loop (HITL)

**Human-in-the-Loop** means involving a person in selected decisions or actions. Not every action should be autonomous.

> **User:** "Investigate the production issue and restart the Payment Service if necessary."

A safe workflow:

```text
Investigate issue
      │
      ▼
Identify restart as a possible action
      │
      ▼
Prepare evidence and impact summary
      │
      ▼
Request human approval
      │
      ▼
Approved?
  ┌──┴───┐
 YES      NO
  │         │
Restart   Do not
service   restart
  │         │
  └────┬────┘
       ▼
Record outcome
```

### Where HITL Is Useful

- Production deployments
- Service restarts
- Financial transactions
- Data deletion
- Permission changes
- Customer communication
- Security-sensitive operations

### Approval Design

A proper approval process should:

- Show the exact proposed action
- Explain its impact and supporting evidence
- Authenticate the approver
- Verify that the approver has permission
- Bind the approval to the specific action and its parameters
- Expire stale approvals
- Record the decision in an audit log

> **Key rule:** An LLM saying "approved" must never count as authorization. Approval must come through a trusted application mechanism.

---

## 5.12 Workflow Patterns You Should Know

### 5.12.1 Prompt Chaining

The output of one LLM call becomes the input of the next.

```text
User requirements
      │
      ▼
LLM 1: Analyze requirements
      │
      ▼
Structured requirements
      │
      ▼
LLM 2: Generate design
      │
      ▼
Architecture document
      │
      ▼
LLM 3: Review design
```

Useful when a complex task has clearly separated stages.

### 5.12.2 Routing

A router decides which model, agent, or workflow should handle a request.

```text
                User query
                    │
                    ▼
                 Router
                    │
      ┌─────────────┼─────────────┐
      ▼             ▼             ▼
  Java Agent   Database Agent  Cloud Agent
```

Examples:
- Java question → Java specialist
- Database issue → Database specialist
- GCP issue → Cloud specialist

Routing can use deterministic rules, an LLM classifier, or a combination of both.

### 5.12.3 Parallelization

Multiple independent tasks run concurrently, and their results are combined.

> *Example:* Run three independent security checks at the same time, then merge their findings.

### 5.12.4 Orchestrator-Worker

A central orchestrator delegates tasks to specialized workers.

```text
                 Orchestrator
                      │
       ┌──────────────┼──────────────┐
       ▼              ▼              ▼
  Metrics Worker  DB Worker    Log Worker
       │              │              │
       └──────────────┼──────────────┘
                      ▼
               Aggregate results
```

The orchestrator:
- Understands the overall goal
- Assigns work
- Tracks progress
- Handles dependencies
- Aggregates results
- Decides when the workflow is complete

Workers focus on specific tasks.

### 5.12.5 Evaluator-Optimizer

One component produces an output, and another evaluates it and requests improvements.

```text
Generate output
      │
      ▼
   Evaluator
      │
      ▼
Meets criteria?
  ┌──┴───┐
 YES      NO
  │         │
Finish    Revise output ──▶ evaluate again
```

> *Example:* A generator creates an API design, an evaluator checks scalability, security, and maintainability, and the generator revises the design if required.
>
> Always set a maximum number of evaluation cycles to prevent infinite loops.

### 5.12.6 Supervisor

A supervisor coordinates specialized agents and controls their work.

It differs from a basic orchestrator-worker design mainly in the degree of agent autonomy and delegated decision-making. For example, a supervisor might ask a Java agent and a database agent to investigate independently, then decide which additional investigation is needed.

### Pattern Comparison

| Pattern | Main Purpose |
|---|---|
| Prompt chaining | Sequential LLM processing |
| Routing | Select the appropriate workflow |
| Parallelization | Run independent tasks concurrently |
| Orchestrator-worker | Delegate and aggregate tasks |
| Evaluator-optimizer | Improve output through evaluation |
| Supervisor | Coordinate specialized agents |

> These are reusable design patterns, not mutually exclusive architectures. One enterprise workflow can combine routing, parallel workers, evaluation, and human approval.

---

## 5.13 Single-Agent vs Multi-Agent Orchestration

### Single-Agent Architecture

One agent handles the entire task using multiple tools.

```text
                 User
                  │
                  ▼
               AI Agent
                  │
       ┌──────────┼──────────┐
       ▼          ▼          ▼
    Logs Tool   DB Tool   Metrics Tool
                  │
                  ▼
               Response
```

**Advantages:** simpler architecture, lower coordination overhead, easier debugging, shared context and state.

**Limitations:** complex instructions become hard to manage, tool selection can get complicated, and large tasks can overwhelm a single agent's context.

### Multi-Agent Architecture

Multiple specialized agents collaborate under a supervisor.

```text
                     User
                      │
                      ▼
                  Supervisor
                      │
       ┌──────────────┼──────────────┐
       ▼              ▼              ▼
   Java Agent     DB Agent      Cloud Agent
       │              │              │
    Findings       Findings       Findings
       │              │              │
       └──────────────┼──────────────┘
                      ▼
             Supervisor review
                      │
                      ▼
                Final report
```

### Single-Agent vs Multi-Agent

| Factor | Single Agent | Multi-Agent |
|---|---|---|
| Complexity | Lower | Higher |
| Coordination | Simple | Requires communication |
| Context | Shared | May be separate per agent |
| Specialization | General-purpose | Specialized responsibilities |
| Cost | Often lower | Can be higher |
| Debugging | Easier | More complex |
| Parallelism | Tool-level parallelism | Agent-level parallelism |

### When Should You Choose Multi-Agent?

Use multiple agents when:
- Responsibilities are clearly separable
- Specialized instructions or tools are required
- Independent investigations can run concurrently
- Separate permissions or execution environments are needed
- The complexity justifies the coordination overhead

> **Rule of thumb:** Do not create five agents when one agent with five well-defined tools can solve the problem reliably.

---

## 5.14 Agent Orchestration Frameworks

| Framework | Description |
|---|---|
| **LangGraph** | Graph-based workflows with nodes, edges, conditional routing, checkpoints, and human-in-the-loop execution |
| **Microsoft AutoGen** | Multi-agent collaboration and conversational agent systems. Its newer ecosystem includes the Microsoft Agent Framework, so check current project guidance before starting a new implementation |
| **CrewAI** | Role-based agents, tasks, crews, and process orchestration |
| **Spring AI** | Java/Spring integration for model calls, tools, advisors, and related AI patterns. Build explicit workflow orchestration around these components where needed |
| **LangChain4j** | Java integrations for LLMs, tools, retrieval, and agent-oriented application development |

### What Should a Java Developer Learn?

1. Understand planning, execution, and orchestration independently of frameworks.
2. Build deterministic workflows in Java.
3. Understand Spring AI's tool and advisor abstractions.
4. Explore LangChain4j's agent and workflow capabilities.
5. Study LangGraph's graph-based concepts to understand stateful agent workflows, even if you do not use Python in your backend.

> You do not need to master every framework. Architecture concepts transfer across them.

---

## 5.15 Building an Agent Orchestrator Using Java

Let's design a simplified orchestration engine for the Payment API investigation.

### Step 1: Define Task Status

```java
public enum TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    FAILED,
    WAITING_FOR_APPROVAL
}
```

### Step 2: Define an Agent Task

```java
public class AgentTask {

    private String taskId;
    private String taskName;
    private TaskStatus status;
    private List<String> dependencies;
    private Map<String, Object> result;
}
```

### Step 3: Define Task Execution

```java
public interface AgentTaskExecutor {

    Map<String, Object> execute(AgentTask task, AgentContext context);
}
```

Different implementations handle different tasks:

```text
ApiMetricsExecutor
DatabaseMetricsExecutor
DeploymentHistoryExecutor
LogAnalysisExecutor
IncidentReportExecutor
```

### Step 4: A Simple Sequential Orchestrator

```java
@Service
public class AgentOrchestrator {

    private final AgentTaskExecutorRegistry registry;
    private final AgentStateService stateService;

    public void execute(AgentContext context, List<AgentTask> tasks) {

        for (AgentTask task : tasks) {

            if (!stateService.areDependenciesCompleted(task)) {
                continue;
            }

            try {
                stateService.markInProgress(task);

                AgentTaskExecutor executor =
                        registry.getExecutor(task.getTaskName());

                Map<String, Object> result =
                        executor.execute(task, context);

                stateService.saveResult(task, result);
                stateService.markCompleted(task);

            } catch (Exception ex) {
                stateService.markFailed(task, ex);
                throw ex;
            }
        }
    }
}
```

> This is a simplified sequential orchestrator. It demonstrates the separation of task definitions, execution logic, and workflow state.

A production orchestrator also needs:

- Dependency-aware scheduling
- Parallel execution of independent tasks
- Retry policies
- Per-task timeouts
- Durable state and checkpoints
- Cancellation support
- Idempotency
- Human approval
- Distributed tracing
- Failure recovery and partial-result aggregation

### Step 5: Where Does the LLM Fit?

The LLM can help with:

- Understanding the user's goal
- Decomposing complex objectives
- Selecting suitable tools
- Interpreting unstructured results
- Recommending the next task
- Summarizing the final findings

Java application code should handle:

- Task scheduling
- Authorization
- State persistence
- Dependency validation
- Timeouts and retries
- Approval enforcement
- Side-effecting actions

> This separation is especially valuable in enterprise systems.

---

## 5.16 Durable Workflow Execution and Distributed Orchestration

An agent workflow may run for seconds, minutes, or even hours. A simple Java method that keeps everything in memory is not sufficient for long-running enterprise tasks.

### Example Failure Scenario

```text
TASK-1001
  1. Fetch API metrics     COMPLETED
  2. Fetch DB metrics      COMPLETED
  3. Analyze results       IN_PROGRESS   ◀── service crashes here
  4. Generate report       PENDING
```

If task state exists only in memory, the entire workflow may be lost. Persist the execution state instead.

### Durable Execution Architecture

```text
Agent Orchestrator
      │
      ├── Task Scheduler
      │     Dependencies, parallel execution, retries
      │
      ├── Task Workers
      │     Execute individual actions
      │
      ├── Persist Checkpoints
      │     Task status, outputs, errors, progress
      │
      ├── PostgreSQL
      │     Workflow state
      │
      └── Kafka
            Task events
```

> This is one possible distributed implementation. A workflow engine can provide many of these capabilities instead of building them yourself.

### Workflow Engine vs Message Queue

| Technology | Main Responsibility |
|---|---|
| Kafka | Event streaming and asynchronous messaging |
| Redis | Fast state and caching |
| PostgreSQL | Durable structured workflow data |
| Workflow engine | Durable execution, dependencies, retries, and recovery |

Examples of workflow orchestration technologies:

- **Temporal**
- **Camunda**
- **Apache Airflow**

These are not all AI agent frameworks. They are useful for understanding durable orchestration and long-running workflows.

> **Architecture recommendation:** For critical enterprise processes, use deterministic workflow orchestration for execution, and use an LLM only for decisions that genuinely benefit from language understanding.

---

## 5.17 Putting It All Together — Complete Agent Execution

Let's walk through the full production support example.

**Payment API investigation, end to end:**

1. **Goal understanding:** The user requests an investigation of 4-second API latency after a deployment.
2. **Planning:** The agent decomposes the goal into API metrics, deployment history, DB metrics, logs, and report generation.
3. **Orchestration:** Independent API, DB, and deployment checks run in parallel.
4. **Tool execution:** Monitoring, log-search, database-metrics, and deployment-history tools are invoked.
5. **Observation:** Results show elevated DB connection usage and long-running queries.
6. **Reflection:** The agent evaluates whether the evidence supports the initial hypothesis.
7. **Replanning:** It requests execution-plan analysis for the slow queries.
8. **Validation:** The system checks findings against engineering documentation (RAG) and historical incidents (memory).
9. **Human approval:** If a production configuration change or restart is proposed, authorized approval is requested.
10. **Final response:** An incident report is generated with evidence, likely root cause, confidence level, and recommended actions.

### Sample Final Report

```text
Incident: INC-1001
Service:  Payment API

Observed:
  - Average latency increased from 200 ms to 4.2 seconds.
  - Database connection utilization reached 95%.
  - Pending database connection requests increased.
  - Multiple slow SQL queries were identified.

Likely root cause:
  Database connection contention associated with long-running SQL queries.

Evidence:
  - Monitoring metrics
  - Application logs
  - SQL execution analysis

Recommended actions:
  1. Analyze execution plans.
  2. Review recent SQL changes.
  3. Optimize affected queries.
  4. Monitor connection pool utilization.

Production action:
  No configuration changes or service restarts were executed without approval.
```

> **Note the distinction** between observed facts and the likely root cause. An agent should not report a hypothesis as a confirmed diagnosis without sufficient evidence.

---

## 5.18 Common Agent Orchestration Problems

| Problem | Why It Happens | Recommended Solution |
|---|---|---|
| Infinite agent loop | No completion condition | Maximum iterations and stop criteria |
| Repeated tool calls | Poor state tracking | Deduplication and execution records |
| Incorrect task order | Dependencies not defined | Dependency-aware scheduling |
| Excessive LLM calls | Unnecessary reasoning cycles | Deterministic workflows and budgets |
| Parallel task failures | One worker fails | Partial-result handling |
| Context overflow | Too much accumulated history | Summarization and retrieval |
| Duplicate side effects | Retry after an uncertain outcome | Idempotency keys |
| Agent takes unsafe action | Excessive permissions | Least privilege and approval gates |
| Lost progress | In-memory-only state | Durable checkpoints |
| Conflicting agent results | Independent agents disagree | Evidence-based aggregation and validation |

### Important Design Principles

1. Define a maximum number of agent iterations.
2. Define explicit task-completion conditions.
3. Give each tool only the permissions it needs.
4. Track every tool invocation and its outcome.
5. Make side-effecting operations idempotent.
6. Use deterministic code for critical business rules.
7. Preserve state across failures.
8. Keep a human approval boundary for high-impact operations.

---

## 5.19 Practical Assignment — Build an Agent Orchestration System

### Recommended Project

Build a **Spring Boot AI Production Support Agent**.

### Functional Requirements

1. Accept a natural-language investigation request.
2. Convert the request into structured subtasks.
3. Execute independent diagnostic tasks in parallel.
4. Execute dependent tasks sequentially.
5. Maintain task status and execution state.
6. Use tools to retrieve API, database, deployment, and log information.
7. Evaluate findings and replan when required.
8. Retry failed read-only operations with bounded retries.
9. Persist checkpoints and resume interrupted workflows.
10. Require human approval before production changes.
11. Generate an evidence-based incident report.

### Suggested Project Architecture

| Component | Responsibility |
|---|---|
| `AgentController` | Accept the user request |
| `PlanningService` | Generate the task plan |
| `AgentOrchestrator` | Coordinate execution |
| `AgentTaskExecutor` | Execute individual tasks |
| `ToolRegistry` | Manage available tools |
| `ReflectionService` | Evaluate findings |
| `AgentStateService` | Persist execution state |
| `ApprovalService` | Handle human approvals |
| `ReportService` | Generate the incident report |

### Suggested Technology Stack

| Layer | Technology |
|---|---|
| Backend | Java 17+ / Spring Boot |
| AI integration | Spring AI or LangChain4j |
| Cache | Redis |
| Persistent state | PostgreSQL |
| Messaging | Kafka |
| Resilience | Resilience4j |
| Observability | OpenTelemetry |
| Workflow orchestration | Custom implementation initially; Temporal for advanced durable workflows |

### Implementation Phases

| Phase | Work | Priority |
|---|---|---|
| 1 | Task decomposition and planning | Essential |
| 2 | Sequential execution | Essential |
| 3 | Parallel execution | Essential |
| 4 | Conditional routing | Essential |
| 5 | Reflection and replanning | Essential |
| 6 | Retry and failure handling | Essential |
| 7 | State persistence and checkpointing | Essential |
| 8 | Human-in-the-loop approval | Essential |
| 9 | Multi-agent collaboration | Advanced |
| 10 | Distributed durable orchestration | Advanced |

---

## Module 5: Interview Takeaways

Make sure you can explain these concepts without looking at notes.

| Interview Question | Key Answer |
|---|---|
| What is agent planning? | Determining the sequence of actions needed to achieve a goal. |
| Reasoning vs planning vs execution? | Understanding the problem, deciding the steps, and performing the actions. |
| What is task decomposition? | Breaking a complex goal into smaller, manageable subtasks. |
| Static vs dynamic planning? | Predefined sequence vs a plan that adapts to observations. |
| What is replanning? | Updating the plan when new information changes the picture. |
| What is ReAct? | An alternating loop of reasoning, acting, and observing until a goal is met. |
| What is reflection? | Evaluating whether an action's result is acceptable. |
| What is workflow orchestration? | Coordinating task execution, dependencies, retries, and state. |
| Sequential vs parallel execution? | Dependent tasks run in order; independent tasks run concurrently. |
| What is conditional routing? | Choosing the next path based on rules or decisions. |
| What is orchestrator-worker? | A central coordinator delegating work to specialized workers. |
| Single-agent vs multi-agent? | One agent with tools vs several specialized agents; start simple. |
| What is HITL? | Human approval and oversight for high-impact actions. |
| How do you prevent infinite loops? | Iteration limits, budgets, and explicit stop conditions. |
| How do you handle failed tool calls? | Timeouts, bounded retries with backoff, and circuit breakers. |
| What is checkpointing? | Persisting workflow progress so it can resume. |
| How do you resume after a crash? | Durable state, checkpoints, and idempotent actions. |
| How do you secure agent execution? | Least privilege, approval gates, and audit logging. |

---

## Interview Questions

Interview questions for this module are in **`Module5_Interview_Questions.md`**.