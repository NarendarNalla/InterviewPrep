# Infix → Postfix — Short Notes

## Logic / Approach

1. Create an empty Stack to store operators.
2. Create an empty result string.
3. Traverse the Infix expression from Left to Right.
4. If the character is an operand, add it directly to the result.
5. If the character is `(`, push it onto the Stack.
6. If the character is `)`, pop operators until `(` is found. Remove `(`.
7. If the character is an operator:
   - Pop operators having higher precedence.
   - If precedence is equal, pop when the current operator is left-associative.
   - Push the current operator.
8. After traversal, pop all remaining operators into the result.
9. Final result is Postfix.

## Remember the Pattern

| Property | Infix → Postfix |
|---|---|
| Traversal | Left to Right |
| Operand | Add directly to result |
| Step 1 | Create operator Stack |
| Step 2 | Handle operands and parentheses |
| Step 3 | Process operators using precedence and associativity |
| Step 4 | Pop remaining operators |
| Final result | Postfix |

## Operator Precedence

| Operator | Precedence | Associativity |
|---|---:|---|
| `^` | 3 | Right to Left |
| `*`, `/`, `%` | 2 | Left to Right |
| `+`, `-` | 1 | Left to Right |

## Important: Equal Precedence Rule

During Infix → Postfix conversion:

- Left-associative operators (`+`, `-`, `*`, `/`, `%`): Pop on equal precedence.
- Right-associative operator (`^`): Do NOT pop on equal precedence.

## One-line Memory Trick

**Infix → Postfix = Operand directly to Result + Operators to Stack + Precedence/Associativity Handling.**

## Example

```text
Infix:   A+B*C

Step 1:  A → Result
Step 2:  + → Stack
Step 3:  B → Result
Step 4:  * → Stack (higher precedence)
Step 5:  C → Result
Step 6:  Pop * and +

Postfix: ABC*+
```

## Complexity

- Time: O(N)
- Space: O(N)

---

# Infix → Prefix — Short Notes

## Logic / Approach

1. Reverse the Infix expression.
2. Swap opening and closing parentheses:
   - `(` → `)`
   - `)` → `(`
3. Convert the modified expression into Postfix.
4. Reverse the resulting Postfix expression.
5. Final result is Prefix.

## Remember the Pattern

| Property | Infix → Prefix |
|---|---|
| Traversal | Reverse → Left to Right |
| Step 1 | Reverse expression |
| Step 2 | Swap parentheses |
| Step 3 | Convert to Postfix |
| Step 4 | Reverse Postfix |
| Final result | Prefix |

## Operator Precedence

| Operator | Precedence | Associativity |
|---|---:|---|
| `^` | 3 | Right to Left |
| `*`, `/`, `%` | 2 | Left to Right |
| `+`, `-` | 1 | Left to Right |

## Important: Equal Precedence Rule

During reversed Infix → Postfix conversion:

- Left-associative operators (`+`, `-`, `*`, `/`, `%`): Do NOT pop on equal precedence.
- Right-associative operator (`^`): Pop on equal precedence.

## One-line Memory Trick

**Infix → Prefix = Reverse + Swap Parentheses + Postfix Conversion + Reverse Result.**

## Example

```text
Infix:   (A+B)*C

Reverse: C*(B+A)

Postfix: CBA+*

Reverse: *+ABC

Prefix:  *+ABC
```

## Complexity

- Time: O(N)
- Space: O(N)


# Infix To Prefix – Java Code

```java
package com.july.play;

import java.util.Stack;

public class InfixToPrefix {

    public static void main(String[] args) {

        String s = "A+B*C";

        String prefix = infixToPrefix(s);

        System.out.println(prefix); // +A*BC
    }

    public static String infixToPrefix(String s) {

        // Step 1: Reverse expression
        StringBuilder sb = new StringBuilder(s);

        sb.reverse();

        // Step 2: Swap parentheses
        for (int i = 0; i < sb.length(); i++) {

            if (sb.charAt(i) == '(') {
                sb.setCharAt(i, ')');
            }
            else if (sb.charAt(i) == ')') {
                sb.setCharAt(i, '(');
            }
        }

        // Step 3: Convert reversed expression to Postfix
        String postfix = reversedInfixToPostfix(sb.toString());

        // Step 4: Reverse Postfix to get Prefix
        return new StringBuilder(postfix).reverse().toString();
    }

    public static String reversedInfixToPostfix(String s) {

        Stack<Character> st = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (Character.isWhitespace(ch)) {
                continue;
            }

            // Operand
            if (Character.isLetterOrDigit(ch)) {

                result.append(ch);
            }

            // Opening bracket
            else if (ch == '(') {

                st.push(ch);
            }

            // Closing bracket
            else if (ch == ')') {

                while (!st.isEmpty() && st.peek() != '(') {
                    result.append(st.pop());
                }

                if (!st.isEmpty()) {
                    st.pop();
                }
            }

            // Operator
            else {

                while (!st.isEmpty() && st.peek() != '(' &&
                       (precedence(st.peek()) > precedence(ch) ||
                       (precedence(st.peek()) == precedence(ch)
                       && isRightAssociative(ch)))) {

                    result.append(st.pop());
                }

                st.push(ch);
            }
        }

        // Pop remaining operators
        while (!st.isEmpty()) {
            result.append(st.pop());
        }

        return result.toString();
    }

    public static int precedence(char ch) {

        switch (ch) {

            case '^':
                return 3;

            case '*':
            case '/':
            case '%':
                return 2;

            case '+':
            case '-':
                return 1;

            default:
                return -1;
        }
    }

    public static boolean isRightAssociative(char ch) {
        return ch == '^';
    }
}
```

**Output:**
```text
+A*BC
```