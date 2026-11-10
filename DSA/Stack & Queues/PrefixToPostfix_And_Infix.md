# Prefix → Postfix & Prefix → Infix — Short Notes

| Property | Prefix → Postfix | Prefix → Infix |
|---|---|---|
| Traversal | Right → Left | Right → Left |
| Operand | Push | Push |
| First pop | Left operand | Left operand |
| Second pop | Right operand | Right operand |
| Combination | `pop1 + pop2 + operator` | `(pop1 + operator + pop2)` |
| Final result | Postfix | Infix |

## Remember the Pattern

**1. Traversal:** Prefix operators come first, so traverse Right → Left to process operands before operators.

**2. Operand:** Push operands directly into the stack.

**3. Operator:** Pop two operands and combine them.

**4. Combination:**

```java
// Prefix to Postfix
result = pop1 + pop2 + operator;

// Prefix to Infix
result = "(" + pop1 + operator + pop2 + ")";
```

**5. Final:** Push the combined result back into the stack.

## One-line Memory Trick

- **Prefix → Postfix:** Right to Left + Operator at End.
- **Prefix → Infix:** Right to Left + Operator in Middle + Parentheses.


| Conversion | Direction | Key Idea |
|----------|----------|---------|
| Infix → Postfix | L → R | precedence + stack |
| Postfix → Infix | L → R | pop 2 → combine |
| Prefix → Infix | R → L | pop 2 → combine |
| Postfix → Prefix | L → R | operator first |
| Prefix → Postfix | R → L | operator last |
| Infix → Prefix | trick | reverse + postfix |