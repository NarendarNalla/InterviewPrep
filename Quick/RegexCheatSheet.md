# Java Regex: Interview Cheat Sheet

**Basics + everyday patterns only | Java 8+ | Quick revision**

> **Format:** Q → Purpose → Solution. ⭐ = asked very often. Outputs are in comments.
> **Approach:** don't memorise patterns. Learn the building blocks, then *identify the requirement → build the regex → test it in Java*.

---

## Contents

1. [Quick Revision](#1-quick-revision)
2. [Fundamentals and Escaping](#2-fundamentals-and-escaping)
3. [Quantifiers](#3-quantifiers)
4. [Character Classes](#4-character-classes)
5. [Anchors and Word Boundary](#5-anchors-and-word-boundary)
6. [Groups and Alternation](#6-groups-and-alternation)
7. [Greedy vs Reluctant](#7-greedy-vs-reluctant)
8. [Lookahead (Basics)](#8-lookahead-basics)
9. [Java Regex API](#9-java-regex-api)
10. [Interview Programs](#10-interview-programs)
11. [Common Traps](#11-common-traps)
12. [Quick Pattern Table](#12-quick-pattern-table)
13. [Theory Q&A](#13-theory-qa)
14. [Checklist](#14-checklist)

---

## 1. Quick Revision

| Topic | Remember |
|---|---|
| Shorthand | `\d` digit, `\w` word char, `\s` space, `.` any char. Capital letter = opposite |
| Java escaping | Regex `\d` is written `"\\d"` in a Java String |
| Quantifiers | `*` 0+, `+` 1+, `?` 0 or 1, `{n}`, `{n,}`, `{n,m}` |
| Classes | `[abc]`, `[a-z]`, `[^abc]` (not) |
| Anchors | `^` start, `$` end, `\b` word boundary |
| Groups | `( )` capture, `(?: )` no capture, `\|` OR, `\1` repeat group 1 |
| Greedy vs reluctant | `.*` max, `.*?` min |
| Lookahead | `(?=X)` / `(?!X)` check without consuming (password regex) |
| API | `matches()` whole string, `find()` anywhere, `lookingAt()` from start |

**Setup used in examples**

```java
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

// Helper: print every match
static void findAll(String regex, String input) {
    Matcher m = Pattern.compile(regex).matcher(input);
    while (m.find()) System.out.println(m.group());
}
```

---

## 2. Fundamentals and Escaping

### Q1 ⭐ What is Regex?
**Purpose:** A pattern used to **search, match, validate, extract, replace and split** text.

```java
"123".matches("\\d+");       // true
```

### Q2 ⭐ Why double backslashes in Java?
**Purpose:** `\` is an escape character in both Java Strings and regex, so it is written twice.

| Regex | Java String | Meaning |
|---|---|---|
| `\d` | `"\\d"` | Digit |
| `\w` | `"\\w"` | Word character |
| `\s` | `"\\s"` | Whitespace |
| `\D` | `"\\D"` | Non-digit |
| `\.` | `"\\."` | Literal dot |

### Q3 ⭐ Shorthand character classes

| Requirement | Pattern | Matches | Does not match |
|---|---|---|---|
| Any digit | `\\d` | `5` | `A` |
| Any non-digit | `\\D` | `A` | `5` |
| Whitespace | `\\s` | space, tab, newline | `A` |
| Non-whitespace | `\\S` | `A` | space |
| Word character `[a-zA-Z0-9_]` | `\\w` | `a`, `5`, `_` | `@`, `#` |
| Non-word character | `\\W` | `@`, `#` | `a` |
| Any character (except newline) | `.` | `A`, `5`, `@` | newline |

### Q4 Which characters are special?
**Purpose:** These need escaping to match literally.

```
.  ^  $  *  +  ?  (  )  [  ]  {  }  |  \
```

```java
"a.b".matches("a\\.b");        // true (literal dot)
"1+1".matches("1\\+1");        // true
```

---

## 3. Quantifiers

**Purpose:** How many times the **previous** character / group can occur.

| Symbol | Meaning | Example |
|---|---|---|
| `*` | Zero or more | `a*` |
| `+` | One or more | `a+` |
| `?` | Zero or one (optional) | `colou?r` |
| `{n}` | Exactly n | `\\d{4}` |
| `{n,}` | At least n | `\\d{3,}` |
| `{n,m}` | Between n and m | `\\d{3,5}` |

```java
"\\d{4}"        // exactly 4 digits
"\\d{10,}"      // 10 or more digits
"\\d{3,5}"      // 3 to 5 digits
"[a-zA-Z]+"     // one or more alphabets
"\\d*"          // zero or more digits
```

### Q ⭐ `*` vs `+` vs `?`
```java
"".matches("a*");       // true  (zero allowed)
"".matches("a+");       // false (needs at least one)
"ab".matches("a?b");    // true  (a is optional)
```

---

## 4. Character Classes

| Pattern | Meaning |
|---|---|
| `[abc]` | a **or** b **or** c |
| `[a-z]` | Lowercase letters |
| `[A-Z]` | Uppercase letters |
| `[0-9]` | Digits |
| `[a-zA-Z]` | Any alphabet |
| `[a-zA-Z0-9]` | Alphanumeric |
| `[a-zA-Z0-9_]` | Alphanumeric or underscore (same as `\\w`) |
| `[^abc]` | **Anything except** a, b, c |
| `[^0-9]` | Non-digit (same as `\\D`) |

```java
"[a-z]+"          // lowercase words
"[A-Z]+"          // uppercase words
"[0-9]+"          // numbers
"[a-zA-Z0-9]+"    // alphanumeric
"[^0-9]+"         // one or more non-digits
```

**Rules inside `[ ]`:** `^` at the start means "not". Most special characters become literal, so `[.]` is a literal dot.

---

## 5. Anchors and Word Boundary

| Pattern | Meaning |
|---|---|
| `^` | Start of string |
| `$` | End of string |
| `\\b` | Word boundary |
| `\\B` | Non-word boundary |

```java
"^Java"        // starts with Java
"Java$"        // ends with Java
"^Java$"       // exactly Java
"\\bJava\\b"   // Java as a complete word
```

### Q ⭐ Difference between `Java`, `^Java$` and `\bJava\b`?

| Pattern | Meaning | `"I love Java"` | `"Java"` | `"JavaScript"` |
|---|---|---|---|---|
| `Java` (with `find()`) | Java anywhere | ✅ | ✅ | ✅ |
| `^Java$` | Entire string is Java | ❌ | ✅ | ❌ |
| `\\bJava\\b` | Java as a separate word | ✅ | ✅ | ❌ |

> `matches()` already checks the **whole** string, so `^` and `$` are redundant there. They matter with `find()`.

---

## 6. Groups and Alternation

| Pattern | Meaning |
|---|---|
| `(abc)` | Capturing group (numbered 1, 2, 3...) |
| `(?:abc)` | Non-capturing group |
| `a\|b` | a **or** b |
| `(ab)+` | Repeat a group |
| `\\1` | Backreference: repeat what group 1 matched |

```java
"(Java|Python)"       // Java OR Python
"(Mr|Mrs|Ms)"         // title alternatives
"(ab)+"               // ab, abab, ababab
"(?:Java|Python)"     // group without capturing
```

### Q ⭐ How do you read captured groups?
```java
Matcher m = Pattern.compile("(\\w+):(\\d+)").matcher("John:25");
if (m.matches()) {
    m.group(0);      // John:25 (whole match)
    m.group(1);      // John
    m.group(2);      // 25
}
```

### Q ⭐ Find repeated words (backreference)
```java
findAll("\\b(\\w+)\\s+\\1\\b", "Java Java Spring Boot");     // Java Java
```

### Q Use groups in a replacement (`$1`, `$2`)
```java
"06-11-2026".replaceAll("(\\d{2})-(\\d{2})-(\\d{4})", "$3-$2-$1");   // 2026-11-06
```

---

## 7. Greedy vs Reluctant

| Type | Pattern | Meaning |
|---|---|---|
| Greedy (default) | `.*` `.+` | Match as **much** as possible |
| Reluctant | `.*?` `.+?` | Match as **little** as possible |

### Q ⭐ Greedy vs reluctant
```java
String input = "<h1>Hello</h1><h2>World</h2>";

input.replaceAll("<.*>", "X");      // X                (first < to the LAST >)
input.replaceAll("<.*?>", "X");     // XHelloXXWorldX   (each of the 4 tags separately)
```

### Q Extract text inside quotes
```java
findAll("\"(.*?)\"", "say \"hi\" and \"bye\"");
// "hi"
// "bye"
```

---

## 8. Lookahead (Basics)

**Purpose:** Check what comes **after** (or before) a position **without consuming** characters.

| Syntax | Name | Meaning |
|---|---|---|
| `(?=X)` | Positive lookahead | Followed by X |
| `(?!X)` | Negative lookahead | **Not** followed by X |
| `(?<=X)` | Positive lookbehind | Preceded by X |
| `(?<!X)` | Negative lookbehind | **Not** preceded by X |

```java
// Q: Number followed by USD
findAll("\\d+(?= USD)", "Price is 500 USD");        // 500

// Q: Number preceded by ₹
findAll("(?<=₹)\\d+", "Price is ₹500");             // 500
```

### Q ⭐ Why can several lookaheads be combined in a password regex?
Each lookahead checks a condition from the **same starting position** and consumes nothing, so all conditions are tested independently. (See the password program in Section 10.)

---

## 9. Java Regex API

### Q1 ⭐ `matches()` vs `find()` vs `lookingAt()`

| Method | Meaning |
|---|---|
| `matches()` | **Entire** input must match |
| `find()` | Finds the next match **anywhere** (call in a loop) |
| `lookingAt()` | Match must start at the **beginning** |

```java
Pattern p = Pattern.compile("\\d+");

p.matcher("123").matches();        // true
p.matcher("abc123").matches();     // false
p.matcher("abc123").find();        // true
p.matcher("123abc").lookingAt();   // true
p.matcher("abc123").lookingAt();   // false
```

### Q2 ⭐ Find all matches
```java
String input = "Java 123 Spring 456";

Pattern pattern = Pattern.compile("\\d+");
Matcher matcher = pattern.matcher(input);

while (matcher.find()) {
    System.out.println(matcher.group());
}
// 123
// 456
```

### Q3 Pattern and Matcher methods

| Method | Purpose |
|---|---|
| `Pattern.compile(regex)` | Compile regex into a `Pattern` |
| `Pattern.compile(regex, Pattern.CASE_INSENSITIVE)` | Compile with ignore-case |
| `pattern.matcher(input)` | Create a `Matcher` |
| `matcher.matches()` / `find()` / `lookingAt()` | Matching modes |
| `matcher.group()` / `group(n)` | Matched text / group n |
| `matcher.start()` | Start index of match |
| `matcher.end()` | End index (**exclusive**) |

### Q4 ⭐ String methods that use regex

| Method | Purpose |
|---|---|
| `s.matches(regex)` | Full match |
| `s.replaceAll(regex, repl)` | Replace **all** matches |
| `s.replaceFirst(regex, repl)` | Replace the **first** match |
| `s.split(regex)` | Split on a regex delimiter |
| `s.replace(a, b)` | Replace **literal** text (no regex) |

### Q5 Case-insensitive matching
```java
Pattern.compile("java", Pattern.CASE_INSENSITIVE).matcher("JAVA").matches();   // true
"JAVA".matches("(?i)java");                                                    // true
```

---

## 10. Interview Programs

### 10A. Validation (`matches()`)

**Q ⭐ Mobile number: 10 digits, starting with 6–9**
```java
"9876543210".matches("[6-9]\\d{9}");        // true
```

**Q ⭐ Email address**
```java
String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
"user123@gmail.com".matches(regex);         // true
```
A practical basic pattern, **not** a full implementation of the email standard.

**Q ⭐ Password: ≥ 8 chars, uppercase, lowercase, digit, special character**
```java
String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
"Java@123".matches(regex);                  // true
```

| Part | Meaning |
|---|---|
| `(?=.*[A-Z])` | Contains an uppercase letter |
| `(?=.*[a-z])` | Contains a lowercase letter |
| `(?=.*\\d)` | Contains a digit |
| `(?=.*[@#$%^&+=!])` | Contains a special character |
| `.{8,}` | At least 8 characters |

**Q Only alphabets / only digits / alphanumeric**
```java
"JavaProgramming".matches("[a-zA-Z]+");     // true
"123456".matches("\\d+");                   // true
"Java123".matches("[a-zA-Z0-9]+");          // true
```

---

### 10B. Extraction (`find()`)

**Q ⭐ Find all numbers in a String**
```java
findAll("\\d+", "Java 123 Spring 456 Boot 789");
// 123
// 456
// 789
```

**Q Words starting with a capital letter**
```java
findAll("\\b[A-Z][a-zA-Z]*\\b", "Java Spring Boot Microservices");
// Java
// Spring
// Boot
// Microservices
```

**Q Extract dates (DD-MM-YYYY)**
```java
findAll("\\b\\d{2}-\\d{2}-\\d{4}\\b", "Today is 06-11-2026");     // 06-11-2026
```
Checks the **format** only, not whether the calendar date exists.

**Q Extract URLs**
```java
findAll("https?://[^\\s]+", "Visit https://example.com today");   // https://example.com
```

**Q ⭐ Find duplicate words (Regex + Streams)**
```java
String input = "Java Spring Java Microservices Spring";

Arrays.stream(input.split("\\s+"))
    .collect(Collectors.groupingBy(w -> w, LinkedHashMap::new, Collectors.counting()))
    .forEach((word, count) -> { if (count > 1) System.out.println(word + " = " + count); });
// Java = 2
// Spring = 2
```
Regex splits the words; Streams count them. `LinkedHashMap` keeps the output order stable.

---

### 10C. Replacement and Splitting

**Q ⭐ Remove all special characters**
```java
"Java@123#Spring!".replaceAll("[^a-zA-Z0-9]", "");      // Java123Spring
```

**Q ⭐ Collapse multiple spaces into one**
```java
"Java     is    powerful".replaceAll("\\s+", " ");       // Java is powerful
```

**Q Remove all digits**
```java
"Java123Spring456".replaceAll("\\d", "");               // JavaSpring
```

**Q Replace vowels with `*`**
```java
"Java Regex".replaceAll("[aeiouAEIOU]", "*");           // J*v* R*g*x
```

**Q ⭐ Split a sentence into words**
```java
"Learn   Java Regex".split("\\s+");                     // [Learn, Java, Regex]
```

**Q Split on several delimiters**
```java
"a,b;c|d".split("[,;|]");                               // [a, b, c, d]
```

---

## 11. Common Traps

### Output prediction

```java
// T1 ⭐ matches() vs find()
"abc123".matches("\\d+");                                // false
Pattern.compile("\\d+").matcher("abc123").find();        // true

// T2 ⭐ split(".")  -> "." means "any character"
"a.b.c".split(".").length;       // 0
"a.b.c".split("\\.");            // [a, b, c]

// T3 ⭐ split("|")  -> "|" means "empty OR empty"
"a|b".split("|");                // [a, |, b]
"a|b".split("\\|");              // [a, b]

// T4 ⭐ replace vs replaceAll
"a.b".replace(".", "-");         // a-b   (literal)
"a.b".replaceAll(".", "-");      // ---   (regex: every character)

// T5 group() before find()
Matcher m = Pattern.compile("\\d+").matcher("abc 123");
m.group();                       // IllegalStateException: No match found
m.find();  m.group();            // 123

// T6 Negative lookahead surprise
findAll("\\d+(?! USD)", "500 USD and 300 INR");          // 50 and 300 (not 500)
// "500" backtracks to "50" because the next char "0" is not " USD"
findAll("\\b\\d+\\b(?! USD)", "500 USD and 300 INR");    // 300 (fixed)
```

### Quick traps table

| Question | Answer |
|---|---|
| Is `matches()` a partial match? | No. The **entire** input must match. |
| Need `^` and `$` with `matches()`? | No, implied. Needed with `find()`. |
| `replace()` vs `replaceAll()`? | Literal text vs regex. |
| Does `.` match a newline? | No (unless `DOTALL`). |
| `\\w` matches `-`? | No. It is `[a-zA-Z0-9_]`. |
| Greedy `.*` in HTML / quotes? | Over-matches. Use `.*?`. |
| Can regex validate dates fully? | No, format only. Use `LocalDate`. |
| Can regex validate every email? | Only a practical subset. Use libraries. |
| Compile `Pattern` inside a loop? | Avoid. Compile once and reuse. |
| Nested quantifiers like `(a+)+`? | Can cause catastrophic backtracking (very slow). |

---

## 12. Quick Pattern Table

| Requirement | Java Regex |
|---|---|
| Only digits | `"\\d+"` |
| Only alphabets | `"[a-zA-Z]+"` |
| Only lowercase | `"[a-z]+"` |
| Only uppercase | `"[A-Z]+"` |
| Alphanumeric | `"[a-zA-Z0-9]+"` |
| Exactly 10 digits | `"\\d{10}"` |
| Mobile (India) | `"[6-9]\\d{9}"` |
| Starts with Java | `"^Java.*"` |
| Ends with Java | `".*Java$"` |
| Contains Java | `".*Java.*"` (with `matches()`) |
| Email (basic) | `"^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"` |
| Password (strong) | `"^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$"` |
| Remove special characters | `"[^a-zA-Z0-9]"` |
| Multiple spaces | `"\\s+"` |
| Extract numbers | `"\\d+"` |
| Extract words | `"\\b\\w+\\b"` |
| Date DD-MM-YYYY (format) | `"\\d{2}-\\d{2}-\\d{4}"` |
| Hexadecimal characters | `"[0-9A-Fa-f]+"` |
| Java or Python | `"Java\|Python"` |
| Whole word Java | `"\\bJava\\b"` |
| Non-digit | `"\\D"` |
| Optional character | `"colou?r"` |
| URL | `"https?://[^\\s]+"` |
| Repeated word | `"\\b(\\w+)\\s+\\1\\b"` |

---

## 13. Theory Q&A

| # | Question | Short answer |
|---|---|---|
| 1 ⭐ | What is Regex? | A pattern used to search, match, validate and manipulate text. |
| 2 ⭐ | What is `Pattern`? | Compiled representation of a regex. |
| 3 ⭐ | What is `Matcher`? | Performs matching operations on input text. |
| 4 ⭐ | `matches()` vs `find()`? | Entire input vs matching subsequence anywhere. |
| 5 | `lookingAt()`? | Match must start at the beginning. |
| 6 ⭐ | `replaceAll()` vs `replaceFirst()`? | All matches vs first match. |
| 7 | `replace()` vs `replaceAll()`? | Literal text vs regex. |
| 8 | Purpose of `split()`? | Splits text using a regex delimiter. |
| 9 ⭐ | `*` vs `+`? | Zero or more vs one or more. |
| 10 | Purpose of `?`? | Zero or one. After a quantifier it makes it reluctant. |
| 11 | `^` vs `$`? | Start vs end anchor. |
| 12 | `\\d` vs `[0-9]`? | Both match ASCII digits. |
| 13 | `\\w` meaning? | Word character `[a-zA-Z0-9_]`. |
| 14 | `\\b` meaning? | Word boundary. |
| 15 ⭐ | Greedy vs reluctant? | Maximum vs minimum matching. |
| 16 ⭐ | Capturing vs non-capturing group? | `( )` stores the match; `(?: )` only groups. |
| 17 | What is a backreference? | `\\1` reuses what group 1 captured (repeated words). |
| 18 ⭐ | What is lookahead? | Checks a following condition without consuming it. |
| 19 | What is lookbehind? | Checks a preceding condition without consuming it. |
| 20 ⭐ | Why escape `\` in Java? | Java string literals also use backslash as an escape. |
| 21 | How to ignore case? | `Pattern.CASE_INSENSITIVE` or `(?i)`. |
| 22 ⭐ | Why compile `Pattern` once? | Compilation is costly; `String.matches()` recompiles each call. |
| 23 | Can regex fully validate an email? | Not reliably. Use a basic pattern plus a library. |

---

## 14. Checklist

Tick a topic only when you can **explain it and write the code without looking**.

**Basics**
- [ ] `\d \D \w \W \s \S .` and Java escaping (`"\\d"`)
- [ ] Quantifiers `* + ? {n} {n,} {n,m}`
- [ ] Character classes, ranges, `[^...]`
- [ ] Anchors `^ $ \b`
- [ ] Groups, alternation `|`, backreference `\1`
- [ ] Greedy vs reluctant
- [ ] Lookahead for passwords

**Java API**
- [ ] `matches()` vs `find()` vs `lookingAt()`
- [ ] `Pattern.compile`, `Matcher.group/start/end`
- [ ] `replaceAll`, `replaceFirst`, `split` and their traps

**Programs**
- [ ] Mobile, email, password validation
- [ ] Alphabets / digits / alphanumeric checks
- [ ] Find numbers, dates, URLs, capitalised words
- [ ] Remove special characters, collapse spaces
- [ ] Split a sentence, duplicate words
- [ ] Traps T1 to T6

---

**Final strategy:** Revise Sections 1, 2, 3, 5 and 6 first. Practise Section 10 **without looking at the solutions**, then finish with Section 11 (traps) and Section 13 (Q&A).