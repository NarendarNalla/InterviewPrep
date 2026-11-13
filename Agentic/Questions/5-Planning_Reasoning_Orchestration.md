# Module 5: Interview Questions

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
| 5.1 – 5.3 | Planning Basics and Decomposition | [Go to](#51--53-planning-basics-and-decomposition) |
| 5.4 – 5.6 | Planning Types, ReAct, and Reflection | [Go to](#54--56-planning-types-react-and-reflection) |
| 5.7 – 5.9 | Orchestration, Execution, and Routing | [Go to](#57--59-orchestration-execution-and-routing) |
| 5.10 – 5.11 | Failure Recovery and Human Approval | [Go to](#510--511-failure-recovery-and-human-approval) |
| 5.12 – 5.14 | Patterns, Multi-Agent, and Frameworks | [Go to](#512--514-patterns-multi-agent-and-frameworks) |
| 5.15 – 5.19 | Implementation, Durability, and Design | [Go to](#515--519-implementation-durability-and-design) |

---

## 5.1 – 5.3 Planning Basics and Decomposition

```text
  ●  ●  ●   5.1 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is agent planning?

A  The process through which an agent determines the sequence of
   actions needed to achieve a goal. A simple question needs one
   response; a complex investigation needs a plan.
```

```text
  ●  ●  ●   5.2 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is the difference between reasoning, planning, and execution?

A  Reasoning understands and evaluates the problem. Planning decides
   the tasks and their order. Execution performs the chosen actions
   through tools.
```

```text
  ●  ●  ●   5.2 · Q2                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Who decides what gets executed: the LLM or the application?

A  The LLM can reason and propose a plan, but the application or
   agent framework controls execution, validates tool calls, and
   enforces permissions.
```

```text
  ●  ●  ●   5.3 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is task decomposition?

A  Splitting a large objective into smaller, actionable subtasks.
   It supports parallel execution, error isolation, progress tracking,
   and task-level retries.
```

```text
  ●  ●  ●   5.3 · Q2                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is hierarchical planning?

A  Decomposing a parent task into child tasks, and then decomposing
   those children further, until each task is concrete enough to
   execute.
```

## 5.4 – 5.6 Planning Types, ReAct, and Reflection

```text
  ●  ●  ●   5.4 · Q1                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is the difference between static and dynamic planning?

A  Static (predefined) planning follows a fixed sequence defined in
   advance, which suits predictable, compliance-sensitive workflows.
   Dynamic planning chooses the next action based on observations,
   which suits troubleshooting and uncertain investigations.
```

```text
  ●  ●  ●   5.4 · Q2                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is replanning?

A  Updating the existing plan when new information shows that the
   original steps are no longer useful, such as dropping database
   checks after finding the database healthy.
```

```text
  ●  ●  ●   5.5 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is the ReAct pattern?

A  Reasoning and Acting. The model reasons about the next step, calls
   a tool, observes the result, and uses that observation to decide the
   next action, repeating until a completion condition is reached.
```

```text
  ●  ●  ●   5.5 · Q2                              🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  Should you expose the agent's chain-of-thought in production logs?

A  No. Log concise decisions, tool calls, observations, and evidence
   for auditing. Hidden internal reasoning should not be exposed or
   treated as a reliable audit record.
```

```text
  ●  ●  ●   5.6 · Q1                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is reflection in an agent?

A  Evaluating the result of an action or task to decide whether it is
   acceptable, and then continuing, finishing, or revising the plan.
```

```text
  ●  ●  ●   5.6 · Q2                              🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  Can an LLM reliably check its own answer?

A  Not on its own. Reflection is only meaningful when it is grounded in
   independent evidence or validation criteria, such as schema checks,
   retrieved documentation, or test results.
```

## 5.7 – 5.9 Orchestration, Execution, and Routing

```text
  ●  ●  ●   5.7 · Q1                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is workflow orchestration?

A  Coordinating how planned tasks run: dependencies, sequencing or
   parallelism, routing, state transitions, retries, timeouts, approvals,
   and result aggregation.
```

```text
  ●  ●  ●   5.8 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  Sequential vs parallel execution?

A  Sequential runs tasks one after another, which is required when a task
   depends on another's result. Parallel runs independent tasks concurrently,
   which can reduce total latency.
```

```text
  ●  ●  ●   5.8 · Q2                              🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  When should you avoid parallel execution?

A  When tasks have dependencies, share a resource that cannot handle the
   load, or have unsafe side effects. Parallel execution also requires
   handling partial failures during aggregation.
```

```text
  ●  ●  ●   5.9 · Q1                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is conditional routing?

A  Choosing the next execution path based on a condition or observation,
   such as investigating the database only when latency exceeds a threshold.
```

```text
  ●  ●  ●   5.9 · Q2                              🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  Where should routing conditions be evaluated?

A  Use deterministic code for clear business rules and numeric thresholds.
   Use an LLM only for ambiguous, language-dependent decisions. Validate
   high-impact decisions through policy and authorization layers.
```

## 5.10 – 5.11 Failure Recovery and Human Approval

```text
  ●  ●  ●   5.10 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  How do you handle a failed tool call?

A  Apply a timeout, retry a bounded number of times with exponential
   backoff and jitter, and use a circuit breaker to stop calls to a
   failing service. Return a clear failure result if all attempts fail.
```

```text
  ●  ●  ●   5.10 · Q2                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  Why is retrying a payment request dangerous, and how do you prevent it?

A  The original request may have succeeded even though the response was
   lost, so a retry can create a duplicate payment. Use an idempotency key
   so the backend recognizes and does not reprocess the same operation.
```

```text
  ●  ●  ●   5.10 · Q3                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is a circuit breaker?

A  A mechanism with closed, open, and half-open states. It rejects calls
   quickly after repeated failures, then allows limited test calls before
   deciding whether to resume normal traffic.
```

```text
  ●  ●  ●   5.10 · Q4                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How do you resume an agent workflow after a crash?

A  Persist task status, outputs, and checkpoints at meaningful stages.
   On restart, load the last valid checkpoint and continue from the next
   pending task, relying on idempotent side-effecting operations.
```

```text
  ●  ●  ●   5.11 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is Human-in-the-Loop and when is it needed?

A  Involving a person in selected decisions or actions. It is needed for
   high-impact operations such as production restarts, deployments, financial
   transactions, data deletion, and permission changes.
```

```text
  ●  ●  ●   5.11 · Q2                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  Can an LLM's statement that an action is "approved" count as approval?

A  No. Approval must come from a trusted application mechanism that
   authenticates the approver, checks permissions, binds the approval to the
   exact action and parameters, expires stale requests, and writes an audit log.
```

## 5.12 – 5.14 Patterns, Multi-Agent, and Frameworks

```text
  ●  ●  ●   5.12 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is the orchestrator-worker pattern?

A  A central orchestrator understands the goal, assigns tasks to specialized
   workers, tracks progress and dependencies, aggregates results, and decides
   when the workflow is complete.
```

```text
  ●  ●  ●   5.12 · Q2                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is the evaluator-optimizer pattern and what risk must you control?

A  One component produces an output, another evaluates it against criteria,
   and the output is revised if needed. Set a maximum number of evaluation
   cycles to prevent infinite loops.
```

```text
  ●  ●  ●   5.12 · Q3                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How does a supervisor pattern differ from orchestrator-worker?

A  Both coordinate multiple workers. A supervisor typically coordinates
   more autonomous specialized agents and makes more delegated decisions,
   such as deciding which additional investigation is needed after
   independent findings are reported.
```

```text
  ●  ●  ●   5.13 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Single-agent vs multi-agent: which would you choose?

A  Start with a single agent and well-defined tools, since it is simpler to
   build and debug. Choose multiple agents when responsibilities are clearly
   separable, specialized tools or permissions are needed, or independent
   investigations can run concurrently.
```

```text
  ●  ●  ●   5.13 · Q2                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  What are the main risks of multi-agent systems?

A  Coordination overhead, conflicting findings, duplicated work, harder
   debugging, higher cost and latency, and unclear accountability when
   agents disagree. Validate aggregated results against evidence.
```

```text
  ●  ●  ●   5.14 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  How should a Java developer approach agent orchestration frameworks?

A  Learn planning, execution, and orchestration concepts first, build
   deterministic workflows in Java, understand Spring AI or LangChain4j
   abstractions, and study graph-based concepts from LangGraph. The
   architecture concepts transfer across frameworks.
```

## 5.15 – 5.19 Implementation, Durability, and Design

```text
  ●  ●  ●   5.15 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What does a simple orchestrator in Java need to do?

A  Iterate through tasks, check that dependencies are complete, mark each
   task in progress, run the matching executor, save the result, and record
   success or failure in the workflow state.
```

```text
  ●  ●  ●   5.16 · Q1                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  Why is in-memory workflow state insufficient for long-running agents?

A  If the service crashes, all progress is lost and the workflow must restart.
   Durable state in a database, with checkpoints and task events, allows the
   workflow to resume from its last valid point.
```

```text
  ●  ●  ●   5.16 · Q2                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  How do Kafka, Redis, PostgreSQL, and a workflow engine differ?

A  Kafka streams events asynchronously. Redis provides fast state and caching.
   PostgreSQL stores durable structured data. A workflow engine such as Temporal
   manages durable execution, dependencies, retries, and recovery.
```

```text
  ●  ●  ●   5.17 · Q1                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  Why should an agent separate observed facts from its root-cause hypothesis?

A  Stating a hypothesis as confirmed can mislead engineers and lead to wrong
   actions. Reports should clearly label observed evidence, the likely cause,
   the confidence level, and what remains uncertain.
```

```text
  ●  ●  ●   5.18 · Q1                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How do you prevent an agent from looping indefinitely?

A  Set a maximum iteration count, an execution timeout, a tool-call budget,
   explicit completion conditions, and duplicate-call detection. When limits
   are reached, return a partial result rather than continuing.
```

```text
  ●  ●  ●   5.19 · Q1                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How do you safely handle production actions taken by an agent?

A  Separate diagnosis from execution. The agent collects evidence and proposes
   an action. The backend validates authorization and requires explicit approval
   for sensitive operations. Execution is logged, idempotent where possible, and
   protected with timeouts and recovery mechanisms.
```

---

**Notes on the design:**
- Image-placeholder artifacts from the source were removed, including the framework and workflow-engine "Official documentation" and "Official website" stubs.
- Repeated content was consolidated, for example the orchestrator design principles and the production-orchestrator requirements list.
- Difficulty tags are my judgment, not part of the original syllabus.
- The source's 20-row interview table and five "prepare well" questions were merged into the question file. Several questions were added beyond the source, such as the chain-of-thought logging and payment-retry questions, because they are commonly asked alongside these topics.