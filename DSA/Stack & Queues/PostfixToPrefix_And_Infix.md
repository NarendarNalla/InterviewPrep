# Postfix → Prefix & Postfix → Infix — Short Notes

| Property | Postfix → Prefix | Postfix → Infix |
|---|---|---|
| Traversal | Left → Right | Left → Right |
| Operand | Push | Push |
| First pop | Right operand | Right operand |
| Second pop | Left operand | Left operand |
| Combination | `operator + pop1 + pop2` | `(pop1 + operator + pop2)` |
| Final result | Prefix | Infix |

## Remember the Pattern

**1. Traversal:** Postfix operators come after operands, so traverse Left → Right to process operands before their operators.

**2. Operand:** Push operands directly into the stack.

**3. Operator:** Pop two operands and combine them.

**4. Combination:**

```java
// Postfix to Prefix
result = operator + pop1 + pop2;

// Postfix to Infix
result = "(" + pop1 + operator + pop2 + ")";
```

**5. Final:** Push the combined result back into the stack.

## One-line Memory Trick

- **Postfix → Prefix:** Left to Right + Operator at Beginning.
- **Postfix → Infix:** Left to Right + Operator in Middle + Parentheses.

**Important:** First pop is always the RIGHT operand and second pop is the LEFT operand in both Postfix conversions.