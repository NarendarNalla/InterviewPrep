# Module 6: Multi-Agent Systems & Collaboration

> **Priority:** Must learn for Senior / Lead Engineer preparation.
> Interview questions for this module are in `Module6_Interview_Questions.md`.

Module 6 of 10 in the Agentic AI learning path.

**Goal:** Understand how multiple AI agents work together, divide responsibilities, communicate, share information, and solve complex enterprise problems.

For Java full-stack and senior engineering preparation, we focus on designing a multi-agent system using **Java, Spring Boot, Spring AI / LangChain4j, Kafka, Redis, and PostgreSQL**.

## Table of Contents

| # | Topic | Link |
|---|---|---|
| 6.1 | What is a Multi-Agent System? | [Go to 6.1](#61-what-is-a-multi-agent-system) |
| 6.2 | Why Do We Need Multiple Agents? | [Go to 6.2](#62-why-do-we-need-multiple-agents) |
| 6.3 | Types of Agents | [Go to 6.3](#63-types-of-agents-in-a-multi-agent-system) |
| 6.4 | Supervisor–Worker Architecture | [Go to 6.4](#64-supervisorworker-architecture) |
| 6.5 | Agent-to-Agent Communication | [Go to 6.5](#65-agent-to-agent-communication) |
| 6.6 | Shared vs Individual Memory | [Go to 6.6](#66-shared-memory-vs-individual-agent-memory) |
| 6.7 | Agent Handoffs | [Go to 6.7](#67-agent-handoffs) |
| 6.8 | Agent Coordination | [Go to 6.8](#68-agent-coordination) |
| 6.9 | Conflict Resolution | [Go to 6.9](#69-conflict-resolution-between-agents) |
| 6.10 | Multi-Agent Failure Handling | [Go to 6.10](#610-multi-agent-failure-handling) |
| 6.11 | Multi-Agent Security | [Go to 6.11](#611-multi-agent-security) |
| 6.12 | Building a Multi-Agent System in Java | [Go to 6.12](#612-building-a-multi-agent-system-using-java--spring-boot) |
| 6.13 | Multi-Agent Frameworks | [Go to 6.13](#613-multi-agent-frameworks) |
| 6.14 | Single Agent vs Multi-Agent | [Go to 6.14](#614-single-agent-vs-multi-agent--interview-comparison) |
| 6.15 | Real-World Enterprise Use Cases | [Go to 6.15](#615-real-world-enterprise-use-cases) |
| — | Interview Takeaways | [Go to takeaways](#module-6-interview-takeaways) |
| — | Interview Questions | [Go to questions](#interview-questions) |

---

## 6.1 What is a Multi-Agent System?

A **Multi-Agent System (MAS)** is a system in which multiple specialized AI agents collaborate to achieve a common goal. Instead of one agent handling every task, responsibilities are divided among several agents.

### Single-Agent Architecture

Consider a Production Support Agent that must investigate a slow Payment API. One agent needs to check logs, analyze database performance, investigate infrastructure metrics, identify root causes, and generate an incident report.

```text
User Request
     │
     ▼
Production Support Agent
(Logs · Database · Metrics · Root cause · Report)
     │
     ▼
Final Incident Report
```

**Problem:** One agent must manage many responsibilities, tools, and contexts. This becomes difficult as the system grows.

### Multi-Agent Architecture

Specialized agents each handle one area:

```text
                Supervisor / Orchestrator Agent
            (understands the goal and delegates work)
                           │
       ┌──────────────┬────┴─────────┬─────────────────┐
       ▼              ▼              ▼                 ▼
   Log Agent     Database Agent  Infrastructure   Code Analysis
 Analyzes logs  Analyzes SQL    Agent            Agent
                performance     CPU, memory,     Checks recent
                                network          code changes
       │              │              │                 │
       └──────────────┴──────┬───────┴─────────────────┘
                             ▼
                 Report / Evaluator Agent
            (combines findings and validates conclusions)
```

Each agent has a specialized role, instructions, tools, and possibly its own memory.

> **Important:** Multiple agents do not necessarily mean multiple different LLMs. They can use the same model with different instructions, tools, and state.

---

## 6.2 Why Do We Need Multiple Agents?

Consider this enterprise task:

> *"Investigate why the Payment API response time increased from 200 ms to 3 seconds after the latest deployment."*

A single agent can perform this task, but a specialized multi-agent system can provide better separation of responsibilities.

| Challenge | Multi-Agent Solution |
|---|---|
| Too many responsibilities | Divide work by specialization |
| Complex investigation | Delegate subtasks |
| Large context | Keep relevant context per agent |
| Independent analysis | Execute tasks in parallel |
| Different expertise | Give agents specialized instructions |
| Validation | Use a separate evaluator |

Multi-agent systems also introduce complexity:

- More LLM calls and higher cost
- Inter-agent communication overhead
- Coordination problems
- Conflicting results
- More difficult debugging
- Security and access-control challenges

> **Interview takeaway:** Multi-agent architecture is not automatically better. Use it when specialization, independent execution, or coordination provides enough benefit to justify the added complexity.

---

## 6.3 Types of Agents in a Multi-Agent System

### 6.3.1 Supervisor Agent

The supervisor manages the overall workflow.

**Responsibilities:**
- Understand the user's goal
- Break the goal into subtasks
- Select appropriate agents
- Delegate work
- Track execution
- Review results
- Decide the next action
- Generate the final response

**Example:**

```text
User: "Investigate Payment API latency."

Supervisor:
  1. Assign log investigation to Log Agent.
  2. Assign SQL analysis to Database Agent.
  3. Assign infrastructure analysis to Infrastructure Agent.
  4. Collect findings.
  5. Ask an agent to validate the suspected root cause.
  6. Prepare the final report.
```

### 6.3.2 Worker Agent

A worker agent handles a specific task assigned by the supervisor.

| Agent | Responsibility |
|---|---|
| Log Agent | Find errors and exceptions |
| Database Agent | Find slow queries and locks |
| Infrastructure Agent | Check CPU, memory, and network |
| Security Agent | Identify security-related issues |
| Code Agent | Analyze code and deployment changes |
| Testing Agent | Suggest or execute relevant tests |

Workers generally report results to the supervisor rather than controlling the entire workflow.

### 6.3.3 Specialist Agent

A specialist agent is designed for a particular domain, such as:

- Java performance
- SQL optimization
- Kafka troubleshooting
- Kubernetes troubleshooting

A specialist can act as a worker under a supervisor or be invoked directly.

### 6.3.4 Evaluator / Critic Agent

The evaluator checks the quality and correctness of another agent's output.

> **Example:** The Database Agent says, *"The slow SQL query is the root cause."* The evaluator checks:
> - Is there query execution evidence?
> - Did database latency increase?
> - Are there relevant slow-query logs?
> - Is there a competing explanation?
>
> It can accept the finding, reject it, or request more evidence.

> **Important:** An evaluator does not guarantee correctness. It should use independent evidence, not merely ask another LLM to agree with the first answer.

---

## 6.4 Supervisor–Worker Architecture

This is one of the most important multi-agent design patterns. The supervisor controls the workflow, while worker agents execute delegated tasks.

```text
                      User
     "Investigate Payment API latency"
                      │
                      ▼
            ┌───────────────────┐
            │  Supervisor Agent │
            │ Plan · Delegate · │
            │ Coordinate ·      │
            │ Aggregate         │
            └─────────┬─────────┘
       ┌──────────────┼──────────────┐
       ▼              ▼              ▼
   Log Worker     DB Worker     Infra Worker
     (logs)         (SQL)        (metrics)
       └──────────────┼──────────────┘
                      ▼
          Supervisor aggregates findings
                      │
                      ▼
            Final Incident Report
```

### Execution Flow

1. The user submits the investigation request.
2. The supervisor creates a task plan.
3. The supervisor delegates independent tasks to workers.
4. Workers call their respective tools.
5. Workers return structured findings.
6. The supervisor evaluates the results.
7. The supervisor may request further investigation.
8. The final response is generated.

### When to Use This Pattern

- Several independent areas of investigation exist
- Different tasks require different tools or expertise
- A central component must control execution
- Results need to be consolidated

---

## 6.5 Agent-to-Agent Communication

Agents must exchange information to complete their tasks. This is called **Agent-to-Agent (A2A) communication**.

> **Example:**
> - The Log Agent discovers repeated database timeout exceptions.
> - The Database Agent discovers a slow query.
> - The Infrastructure Agent reports normal CPU and memory usage.
> - The supervisor combines these observations.

There are three common communication approaches.

### 6.5.1 Direct Communication

One agent sends a message directly to another agent.

```text
Log Agent
    │  Sends database timeout details
    ▼
Database Agent
    │  Investigates the query
    ▼
Returns findings
```

Useful when one agent needs another agent's output to continue.

### 6.5.2 Supervisor-Mediated Communication

Agents communicate through the supervisor.

```text
Log Agent ─────────┐
                   │
Database Agent ────┼──▶ Supervisor
                   │
Infra Agent ───────┘
```

The supervisor receives results, decides what to do next, and delegates further work.

- **Advantage:** Centralized coordination and easier control
- **Disadvantage:** The supervisor can become a bottleneck

### 6.5.3 Shared-State Communication

Agents communicate by reading and updating a common workflow state.

```json
{
  "incidentId": "INC-101",
  "status": "INVESTIGATING",
  "findings": {
    "logs": "Database timeout exceptions found",
    "database": "Slow query identified",
    "infrastructure": "CPU and memory normal"
  }
}
```

The supervisor and workers read the relevant state and update their results.

> **Important distinction:** Shared state is not the same as shared memory inside an LLM. It is application-managed data that agents can access under defined permissions.

---

## 6.6 Shared Memory vs Individual Agent Memory

An agent needs its own context, while multiple agents also need access to common information.

### Individual Memory

Each agent maintains information relevant to its role.

| Agent | Individual Memory |
|---|---|
| Log Agent | Log patterns, previous log findings |
| Database Agent | Query plans, indexing findings |
| Infra Agent | CPU, memory, and network metrics |
| Code Agent | Recent deployments and code changes |

**Benefits:** less irrelevant context, better specialization, reduced context-window usage, and clearer separation of responsibilities.

### Shared Memory

Multiple agents access common information, such as:

- Incident ID
- The user's investigation goal
- Application name
- Time range
- Current task status
- Findings from other agents

**Benefits:** consistent information, easier collaboration, and less repeated work.

### Comparison

| Individual Memory | Shared Memory |
|---|---|
| Agent-specific | Common workflow information |
| Private to an agent or role | Accessible to authorized agents |
| Contains specialized context | Contains shared facts and state |
| Supports specialization | Supports collaboration |

### Enterprise Implementation

| Technology | Purpose |
|---|---|
| Redis | Fast, short-lived workflow state |
| PostgreSQL | Durable workflow state and history |
| Vector database | Semantic retrieval of relevant knowledge |
| Kafka | Asynchronous task and event communication |
| Object storage | Large reports, logs, and artifacts |

```text
   Agent A       Agent B       Agent C
       │             │             │
       └─────────────┼─────────────┘
                     ▼
                 Supervisor
                     │
                     ▼
        Shared Workflow State
  Task status · Findings · Context · Agent outputs
        │            │             │
      Redis     PostgreSQL     Vector DB
     (fast)     (durable)     (knowledge)
```

> **Design principle:** Do not give every agent unrestricted access to all memory. Use scoped access, tenant isolation, and explicit ownership of updates.

---

## 6.7 Agent Handoffs

A **handoff** occurs when one agent transfers control of a task to another agent.

> **Example:**
> 1. The supervisor assigns log investigation to the Log Agent.
> 2. The Log Agent discovers database timeouts.
> 3. It determines that database analysis is required.
> 4. It hands off the relevant task to the Database Agent.
> 5. The Database Agent investigates and returns the result.

```text
Supervisor Agent
   │  Handoff: investigate logs
   ▼
Log Agent
   │  Finds database timeout exceptions
   │  Handoff: investigate DB timeouts
   ▼
Database Agent
   │  Analyzes slow queries and locks
   │  Returns findings
   ▼
Supervisor Agent
```

### What a Handoff Should Contain

A good handoff includes:

- Task ID
- Source agent and target agent
- Task description
- Relevant findings
- Required output format
- Deadline or timeout
- Correlation or trace ID

```json
{
  "taskId": "TASK-102",
  "fromAgent": "log-agent",
  "toAgent": "database-agent",
  "task": "Investigate database timeout errors",
  "context": {
    "incidentId": "INC-101",
    "error": "Connection timeout",
    "service": "payment-service"
  },
  "expectedOutput": "Database investigation findings"
}
```

### Handoff vs Delegation

- **Delegation:** Assigning work to another agent. The original agent or supervisor usually remains responsible.
- **Handoff:** Transferring control or responsibility for the task to another agent.

A delegation can be followed by a return to the original agent or supervisor. A handoff may transfer the active execution path.

---

## 6.8 Agent Coordination

**Coordination** ensures that agents execute tasks in the correct order and work toward the same objective.

> **Example:** Task C (generate report) depends on Task A (log analysis) and Task B (database analysis). Execution should be:
>
> ```text
> Start investigation
>    ├── A. Log analysis
>    └── B. DB analysis
>            │
>     Wait for both results
>            │
>            ▼
>        C. Report generation
> ```

### Common Coordination Strategies

| Strategy | Explanation | Example |
|---|---|---|
| Sequential | Agents execute one after another | Log → DB → Report |
| Parallel | Independent agents execute together | Log + DB + Infra |
| Conditional | Execute based on a result | If DB timeout, invoke DB Agent |
| Hierarchical | Supervisor manages sub-agents | Supervisor → Specialists |
| Collaborative | Agents exchange information iteratively | Code Agent and DB Agent refine a diagnosis |

### Parallel Execution Example

| Agent | Duration |
|---|---|
| Log Agent | 2 seconds |
| Database Agent | 3 seconds |
| Infrastructure Agent | 4 seconds |

- **Sequential:** 2 + 3 + 4 = **9 seconds**
- **Parallel:** max(2, 3, 4) = **4 seconds**

> *Idealized calculation. It excludes orchestration, communication, model latency, and aggregation overhead.*

> **Interview point:** Only independent tasks should run in parallel. If one agent depends on another agent's findings, use sequential execution or a dependency-aware workflow.

---

## 6.9 Conflict Resolution Between Agents

Multiple agents may produce contradictory findings:

- Log Agent: *"Database timeout is the root cause."*
- Infrastructure Agent: *"CPU utilization is high."*
- Code Agent: *"A recent deployment introduced a performance regression."*

The supervisor should not simply select the most confident-sounding answer.

### Common Conflict-Resolution Strategies

| Strategy | How It Works |
|---|---|
| Evidence-based resolution | Compare actual evidence behind each finding |
| Evaluator agent | A separate evaluator checks findings against available evidence |
| Consensus | Multiple agents independently assess the question and compare conclusions |
| Priority-based resolution | Trusted sources and domain rules carry more weight |
| Escalation to a human | Ambiguous evidence is escalated instead of resolved with false certainty |

**Evidence example:**

| Agent | Finding | Evidence |
|---|---|---|
| Log Agent | DB timeout | Timeout exceptions in logs |
| Infra Agent | CPU high | CPU metrics |
| Code Agent | Deployment regression | Before/after comparison |

> For database saturation, actual database metrics may carry more weight than an agent's unsupported interpretation of a log message.

### Important Enterprise Principle

Separate three things in every output:

1. **Observed facts**
2. **Hypotheses**
3. **Confirmed root causes**

> An AI agent should never present an unverified hypothesis as a confirmed incident root cause.

---

## 6.10 Multi-Agent Failure Handling

A multi-agent system can fail in several ways.

| Failure | Example | Solution |
|---|---|---|
| Agent timeout | DB Agent does not respond | Timeout and controlled retry |
| Agent unavailable | Log service is down | Fallback or mark the task failed |
| Infinite delegation | Agent A calls B, and B calls A | Maximum handoff depth |
| Duplicate execution | Same task executed twice | Idempotency keys |
| Conflicting results | Agents disagree | Evidence evaluation |
| Lost state | Application restarts | Durable checkpoints |
| Context overflow | Too much conversation history | Summarize and retrieve relevant context |
| Cascading failures | One failed agent blocks all tasks | Failure isolation and partial results |

### Preventing Infinite Agent Loops

A dangerous pattern:

```text
Agent A → Agent B
Agent B → Agent A
Agent A → Agent B
Agent B → Agent A
...
```

Prevent it with:

- A maximum agent invocation count
- A maximum workflow depth
- Task deadlines
- Cycle detection
- Explicit rules for allowed handoffs
- Supervisor-controlled termination

### Partial Failure Handling

Suppose three agents are investigating:

- Log Agent succeeds.
- Database Agent succeeds.
- Infrastructure Agent fails.

The system should return a **partial report** that explicitly identifies the missing infrastructure evidence.

> **Do not silently treat a failed agent as if it found nothing.**

---

## 6.11 Multi-Agent Security

Security becomes more important when multiple agents can access enterprise systems. Giving every agent access to every tool creates unnecessary risk.

> **Example:** The Log Agent can read application logs, the Database Agent can read performance metadata, the Deployment Agent can restart services, and the Security Agent can inspect access events. Each should have only its own permissions.

### Recommended Security Controls

| Control | Description |
|---|---|
| Least privilege | Each agent receives only the permissions it needs |
| Tool authorization | Permissions are validated in the backend, not just in the agent prompt |
| Human approval | Required for production deployments, restarts, deletions, and sensitive changes |
| Input validation | All tool arguments are validated |
| Prompt injection defense | Logs, documents, and tool outputs are treated as untrusted data |
| Audit logs | Record which agent requested which action, and why |
| Tenant isolation | One customer's data is never exposed to another |
| Execution limits | Rate limits, timeouts, and maximum tool invocations |

> **Critical point:** An agent's identity or role is not a security boundary by itself. The application must enforce authorization for every operation.

---

## 6.12 Building a Multi-Agent System Using Java & Spring Boot

Let's connect these concepts to Java backend experience.

We'll design a simplified **Production Support Multi-Agent System**.

### 6.12.1 High-Level Architecture

```text
Spring Boot REST Controller
  POST /api/incidents/investigate
          │
          ▼
Multi-Agent Supervisor
  Delegation · Coordination · Aggregation
          │
  ┌───────┼──────────────┬────────────────┐
  ▼       ▼              ▼                ▼
Log     Database      Infra            Evaluator
Agent   Agent         Agent            Agent
  │       │              │                │
LogService DatabaseService MetricsService EvidenceEvaluator
          │
          ▼
Incident Report Service
```

### 6.12.2 Suggested Package Structure

```text
com.example.agentic
├── controller
│   └── IncidentController.java
├── supervisor
│   └── SupervisorAgent.java
├── agent
│   ├── Agent.java
│   ├── LogAgent.java
│   ├── DatabaseAgent.java
│   ├── InfrastructureAgent.java
│   └── EvaluatorAgent.java
├── orchestration
│   ├── AgentOrchestrator.java
│   ├── AgentRegistry.java
│   └── TaskCoordinator.java
├── model
│   ├── AgentTask.java
│   ├── AgentResult.java
│   └── IncidentContext.java
├── memory
│   └── WorkflowStateService.java
├── service
│   ├── LogService.java
│   ├── DatabaseService.java
│   └── MetricsService.java
└── report
    └── IncidentReportService.java
```

### 6.12.3 Common Agent Interface

All specialized agents follow a common contract. The supervisor only needs to know how to invoke an agent and receive its result.

```java
public interface Agent {

    String getName();

    AgentResult execute(AgentTask task);
}
```

### 6.12.4 Structured Agent Result

Agents return structured results rather than unstructured text wherever possible.

```java
public record AgentResult(
        String agentName,
        String status,
        String summary,
        List<String> observations,
        List<String> recommendations
) {}
```

### 6.12.5 Specialized Agent Implementation

```java
@Service
public class LogAgent implements Agent {

    private final LogService logService;

    public LogAgent(LogService logService) {
        this.logService = logService;
    }

    @Override
    public String getName() {
        return "log-agent";
    }

    @Override
    public AgentResult execute(AgentTask task) {

        List<String> findings = logService.findErrors(task.serviceName());

        return new AgentResult(
                getName(),
                "SUCCESS",
                "Log investigation completed",
                findings,
                List.of("Investigate database timeouts")
        );
    }
}
```

> The Database Agent and Infrastructure Agent implement the same interface and invoke their respective services.
>
> This example uses deterministic Java services. In an AI implementation, agents can also use an LLM to interpret requests, select permitted tools, and analyze returned evidence.

### 6.12.6 Agent Registry

The registry lets the supervisor discover agents by name.

```java
@Component
public class AgentRegistry {

    private final Map<String, Agent> agents;

    public AgentRegistry(List<Agent> agentList) {
        this.agents = agentList.stream()
                .collect(Collectors.toMap(Agent::getName, Function.identity()));
    }

    public Agent getAgent(String name) {
        Agent agent = agents.get(name);

        if (agent == null) {
            throw new IllegalArgumentException("Unknown agent: " + name);
        }

        return agent;
    }
}
```

### 6.12.7 Supervisor Orchestration

The supervisor coordinates agents and collects their findings.

```java
@Service
public class SupervisorAgent {

    private final AgentRegistry registry;

    public SupervisorAgent(AgentRegistry registry) {
        this.registry = registry;
    }

    public List<AgentResult> investigate(AgentTask task) {

        List<AgentResult> results = new ArrayList<>();

        List<String> agentNames = List.of(
                "log-agent",
                "database-agent",
                "infrastructure-agent"
        );

        for (String name : agentNames) {

            Agent agent = registry.getAgent(name);

            try {
                results.add(agent.execute(task));

            } catch (Exception exception) {
                results.add(new AgentResult(
                        name,
                        "FAILED",
                        exception.getMessage(),
                        List.of(),
                        List.of()
                ));
            }
        }

        return results;
    }
}
```

> This is a simplified sequential example, not a production-ready AI supervisor. A production system adds dependency management, bounded parallel execution, durable state, retries, authorization, timeouts, and LLM-driven delegation where appropriate.

### 6.12.8 Where Does the LLM Fit?

The code above uses fixed agent selection. An AI-powered supervisor can decide dynamically which agents to invoke.

```text
User: Investigate Payment API latency.

Supervisor LLM:
  1. Understands the request.
  2. Selects Log Agent.
  3. Selects Database Agent.
  4. Selects Infrastructure Agent.
  5. Receives their findings.
  6. Decides whether more investigation is required.
  7. Produces the final report.
```

The application still controls:

- Which agents are registered
- Which tools each agent can access
- Which operations are permitted
- How tasks are scheduled
- How results are persisted
- When human approval is required

> **The LLM proposes decisions; the application enforces them.**

### 6.12.9 Scaling the Design

| Component | Technology | Responsibility |
|---|---|---|
| API | Spring Boot | Receive requests |
| Agent framework | Spring AI / LangChain4j | LLM and tool integration |
| Orchestration | Workflow engine or graph framework | Manage agent execution |
| Messaging | Kafka | Asynchronous task communication |
| Fast state | Redis | Current workflow state |
| Durable state | PostgreSQL | Tasks and execution history |
| Knowledge | Vector database | Relevant domain knowledge |
| Monitoring | OpenTelemetry | Traces, metrics, and logs |

> **Note:** Kafka supports asynchronous communication, but it does not provide agent reasoning or workflow orchestration on its own.

---

## 6.13 Multi-Agent Frameworks

Understand the role of each framework rather than memorizing every API.

| Framework | Highlights |
|---|---|
| **LangGraph** | Graph-based workflows, nodes, edges, conditional routing, state management, checkpointing, and human-in-the-loop. Useful for complex supervisor-worker systems |
| **CrewAI** | Role-based agents, tasks, and crews. Sequential and hierarchical processes. Good for learning agent collaboration |
| **Microsoft Agent Framework** | Agent and workflow abstractions, multi-agent collaboration, and workflow execution. Successor direction for the AutoGen and Semantic Kernel ecosystems |
| **Spring AI** | Java and Spring Boot integration, chat clients, model integration, tool calling, advisors, and memory integrations |
| **LangChain4j** | Java-first LLM integration, AI Services, tools, memory, and RAG |

### Which to Learn First?

For a Java developer:

1. Understand multi-agent architecture independently of frameworks.
2. Implement the simplified supervisor-worker design in Java.
3. Learn Spring AI or LangChain4j for Java LLM integration.
4. Study LangGraph's graph-based orchestration concepts.
5. Explore CrewAI and Microsoft's framework to understand alternative approaches.

> You do not need to master all five frameworks to explain multi-agent architecture in an interview.

---

## 6.14 Single Agent vs Multi-Agent — Interview Comparison

| Dimension | Single Agent | Multi-Agent |
|---|---|---|
| Architecture | Simple | More complex |
| Responsibilities | Multiple or limited | Specialized |
| Coordination | Usually unnecessary | Required |
| Communication | Tool interactions | Agent-to-agent or shared state |
| Context | Can become large | Can be separated |
| Execution | Sequential or parallel tools | Coordinated agent workflows |
| Cost | Usually lower | Usually higher |
| Debugging | Easier | More difficult |
| Best use | Focused tasks | Complex, specialized tasks |

### When to Choose Each

**Choose a single agent when:**
- The task is straightforward
- A few tools are sufficient
- The workflow is predictable
- Agent coordination adds little benefit

**Choose multi-agent when:**
- Different domains require specialized analysis
- Multiple independent investigations can run in parallel
- Tasks need different permissions or tools
- Complex work benefits from delegation and independent evaluation

---

## 6.15 Real-World Enterprise Use Cases

| Use Case | Agents Involved |
|---|---|
| Production incident management | Log, DB, Infra, Code, Report |
| Software development | Requirement, Coding, Testing, Review |
| Customer support | Query classification, Knowledge, Resolution, Escalation |
| Fraud investigation | Transaction, Risk, Identity, Compliance |
| Data analytics | Query, Data quality, Analysis, Visualization |
| Security operations | Alert analysis, Threat intelligence, Investigation, Response |

> The first three are particularly useful project examples for a Java and backend career, because they connect directly to existing backend engineering workflows.

---

## Module 6: Interview Takeaways

Make sure you can explain these concepts without looking at notes.

| Interview Question | Key Answer |
|---|---|
| What is a multi-agent system? | Multiple specialized agents collaborating toward a common goal. |
| Single agent vs multi-agent? | Single agent is simpler; multi-agent adds specialization at the cost of coordination. |
| What is a supervisor agent? | An agent that plans, delegates, monitors, and aggregates work. |
| What are worker agents? | Agents that execute specific delegated tasks. |
| What is agent-to-agent communication? | Direct messages, supervisor-mediated exchange, or shared state. |
| Shared vs individual memory? | Shared holds common workflow facts; individual holds role-specific context. |
| What is a handoff? | Transferring a task or control to another agent. |
| How do agents coordinate? | Sequential, parallel, conditional, hierarchical, or collaborative execution. |
| How do you resolve conflicting results? | Evidence comparison, evaluator agents, consensus, and human escalation. |
| How do you prevent infinite loops? | Invocation limits, depth limits, deadlines, and cycle detection. |
| How do you handle agent failures? | Timeouts, retries, fallbacks, checkpoints, and partial reports. |
| How do you secure multi-agent systems? | Least privilege, backend tool authorization, approvals, and audit logs. |
| When should you use multi-agent? | When specialization and collaboration justify the added complexity. |
| How would you implement it in Java? | Spring Boot, an agent interface, a registry, an orchestrator, and durable state. |

---

## Interview Questions

Interview questions for this module are in **`Module6_Interview_Questions.md`**.