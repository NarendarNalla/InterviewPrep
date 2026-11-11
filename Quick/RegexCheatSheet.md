# JAVA REGEX — INTERVIEW CHEAT SHEET

## 1. BASIC REGEX SYMBOLS

### 1.1 Character Classes

| Regex | Meaning | Example |
|---|---|---|
| `\d` | Digit (0–9) | `123` |
| `\D` | Non-digit | `abc` |
| `\w` | Word character `[a-zA-Z0-9_]` | `abc_123` |
| `\W` | Non-word character | `@#$` |
| `\s` | Whitespace (space, tab, newline) | `" "` |
| `\S` | Non-whitespace | `abc` |
| `.` | Any character except line terminators by default | `a`, `1`, `@` |

### 1.2 Quantifiers

| Regex | Meaning | Example |
|---|---|---|
| `+` | One or more | `a+` → `a`, `aa`, `aaa` |
| `*` | Zero or more | `a*` → `""`, `a`, `aaa` |
| `?` | Zero or one | `a?` → `""`, `a` |
| `{n}` | Exactly n times | `\d{4}` |
| `{n,}` | At least n times | `\d{2,}` |
| `{n,m}` | Between n and m times | `\d{2,5}` |

### 1.3 Anchors

| Regex | Meaning | Example |
|---|---|---|
| `^` | Start of string/line | `^Hello` |
| `$` | End of string/line | `World$` |
| `\b` | Word boundary | `\bJava\b` |
| `\B` | Not a word boundary | `\Bcat` |

### 1.4 Character Sets

| Regex | Meaning | Example |
|---|---|---|
| `[abc]` | a OR b OR c | `[abc]` |
| `[^abc]` | Anything except a, b, c | `[^abc]` |
| `[a-z]` | Lowercase letters | `a` to `z` |
| `[A-Z]` | Uppercase letters | `A` to `Z` |
| `[0-9]` | Digits | `0` to `9` |
| `[a-zA-Z]` | Any English letter | `Java` |
| `[a-zA-Z0-9]` | Alphanumeric | `Java123` |
| `[a-z]+` | One or more lowercase letters | `hello` |
| `[0-9]*` | Zero or more digits | `123` |

### 1.5 Grouping and Alternation

| Regex | Meaning | Example |
|---|---|---|
| `(abc)` | Capture group | `(ab)+` |
| `(a\|b)` | a OR b | `(cat\|dog)` |
| `(ab)+` | Repeat group | `ab`, `abab` |
| `(?:abc)` | Non-capturing group | `(?:ab)+` |

---

## 2. JAVA STRING REGEX METHODS

### 2.1 matches()

Checks whether the entire string matches the regex.

```java
String s = "12345";

System.out.println(s.matches("\\d+"));       // true
System.out.println(s.matches("\\d{5}"));     // true
System.out.println(s.matches("[a-z]+"));     // false
```

**Interview trick:** `matches()` checks the complete string, not just a substring.

### 2.2 split()

Splits a string using a regex delimiter.

```java
String s = "Java,Python,Spring";

String[] arr = s.split(",");

// [Java, Python, Spring]
```

More examples:

```java
String s1 = "Java Python Spring";

String[] a1 = s1.split("\\s+");
// [Java, Python, Spring]


String s2 = "apple123banana456orange";

String[] a2 = s2.split("\\d+");
// [apple, banana, orange]


String s3 = "Java@Python#Spring";

String[] a3 = s3.split("[@#]");
// [Java, Python, Spring]


String s4 = "a,b,c,";

String[] a4 = s4.split(",", -1);
// [a, b, c, ""]
```

**Important:** `split()` removes trailing empty strings by default. Use `split(regex, -1)` to preserve them.

### 2.3 replace()

Replaces literal text.

```java
String s = "Java Java";

System.out.println(s.replace("Java", "Python"));
// Python Python
```

### 2.4 replaceAll()

Replaces all regex matches.

```java
String s = "Java123Spring456";

System.out.println(s.replaceAll("\\d+", ""));
// JavaSpring
```

More examples:

```java
String s1 = "Java@123#Spring!";

System.out.println(s1.replaceAll("[^a-zA-Z0-9]", ""));
// Java123Spring


String s2 = "Java    Spring   Boot";

System.out.println(s2.replaceAll("\\s+", " "));
// Java Spring Boot


String s3 = "hello123world456";

System.out.println(s3.replaceAll("\\d", "*"));
// hello***world***
```

### 2.5 replaceFirst()

Replaces only the first regex match.

```java
String s = "Java123Spring456";

System.out.println(s.replaceFirst("\\d+", ""));
// JavaSpring456
```

---

## 3. PATTERN AND MATCHER

Use `Pattern` and `Matcher` when you need more control over regex matching.

### 3.1 Basic Syntax

```java
Pattern pattern = Pattern.compile("\\d+");

Matcher matcher = pattern.matcher("Java123Spring456");

while (matcher.find()) {

    System.out.println(matcher.group());
}

// Output:
// 123
// 456
```

### 3.2 Important Matcher Methods

| Method | Purpose |
|---|---|
| `matches()` | Entire input matches |
| `find()` | Finds next matching substring |
| `lookingAt()` | Checks match at beginning |
| `group()` | Returns matched text |
| `start()` | Starting index |
| `end()` | Ending index (exclusive) |

Example:

```java
Pattern pattern = Pattern.compile("\\d+");

Matcher matcher = pattern.matcher("Java123Spring456");

System.out.println(matcher.find());      // true
System.out.println(matcher.group());     // 123

System.out.println(matcher.start());     // 4
System.out.println(matcher.end());       // 7

System.out.println(matcher.find());      // true
System.out.println(matcher.group());     // 456
```

### 3.3 matches() vs find() vs lookingAt()

```java
Pattern p = Pattern.compile("\\d+");

Matcher m1 = p.matcher("123abc");

System.out.println(m1.matches());       // false
System.out.println(m1.find());          // true


Matcher m2 = p.matcher("123abc");

System.out.println(m2.lookingAt());     // true


Matcher m3 = p.matcher("abc123");

System.out.println(m3.lookingAt());     // false
System.out.println(m3.find());          // true
```

**Remember:**

- `matches()` → Whole string.
- `find()` → Anywhere.
- `lookingAt()` → Starting position.

---

# 4. MOST IMPORTANT REGEX PATTERNS FOR INTERVIEWS

These are the patterns worth practicing repeatedly.

## 4.1 Extract All Digits

**Question:** Extract all numbers from a string.

```java
String s = "Java123Spring456Boot789";

Pattern p = Pattern.compile("\\d+");
Matcher m = p.matcher(s);

while (m.find()) {
    System.out.println(m.group());
}

// 123
// 456
// 789
```

Regex: `\\d+`

## 4.2 Extract All Words

**Question:** Extract words from a sentence.

```java
String s = "Java is powerful 123";

Pattern p = Pattern.compile("[a-zA-Z]+");
Matcher m = p.matcher(s);

while (m.find()) {
    System.out.println(m.group());
}

// Java
// is
// powerful
```

Regex: `[a-zA-Z]+`

## 4.3 Remove Special Characters

**Question:** Keep only letters and digits.

```java
String s = "Java@123#Spring!";

String result = s.replaceAll("[^a-zA-Z0-9]", "");

System.out.println(result);

// Java123Spring
```

Regex: `[^a-zA-Z0-9]`

## 4.4 Remove All Whitespaces

```java
String s = "Java   Spring \t Boot";

String result = s.replaceAll("\\s+", "");

System.out.println(result);

// JavaSpringBoot
```

Regex: `\\s+`

## 4.5 Replace Multiple Spaces with One Space

```java
String s = "Java     Spring    Boot";

String result = s.replaceAll("\\s+", " ");

System.out.println(result);

// Java Spring Boot
```

Regex: `\\s+`

## 4.6 Check Only Digits

```java
String s = "123456";

System.out.println(s.matches("\\d+"));
// true
```

Regex: `\\d+`

## 4.7 Check Only Alphabets

```java
String s = "Java";

System.out.println(s.matches("[a-zA-Z]+"));
// true
```

Regex: `[a-zA-Z]+`

## 4.8 Check Alphanumeric String

```java
String s = "Java123";

System.out.println(s.matches("[a-zA-Z0-9]+"));
// true
```

Regex: `[a-zA-Z0-9]+`

## 4.9 Check Exactly 10 Digits

```java
String mobile = "9876543210";

System.out.println(mobile.matches("\\d{10}"));
// true
```

Regex: `\\d{10}`

## 4.10 Extract Words Starting with Capital Letters

```java
String s = "Java Spring Boot Python";

Pattern p = Pattern.compile("\\b[A-Z][a-zA-Z]*\\b");

Matcher m = p.matcher(s);

while (m.find()) {
    System.out.println(m.group());
}

// Java
// Spring
// Boot
// Python
```

Regex: `\\b[A-Z][a-zA-Z]*\\b`

## 4.11 Find Duplicate Consecutive Words

```java
String s = "Java Java Spring Spring Boot";

String result = s.replaceAll("\\b(\\w+)\\s+\\1\\b", "$1");

System.out.println(result);

// Java Spring Boot
```

Regex: `\\b(\\w+)\\s+\\1\\b`

**Concept:** Backreference `\\1` refers to the first captured group.

## 4.12 Extract Words Between Spaces

```java
String s = "Java Spring Boot";

String[] words = s.split("\\s+");

// [Java, Spring, Boot]
```

## 4.13 Validate Basic Email Format

```java
String email = "user123@gmail.com";

String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

System.out.println(email.matches(regex));
// true
```

This is a practical basic validation pattern, not a complete implementation of all valid email-address rules.

## 4.14 Validate Date Format (DD-MM-YYYY)

```java
String date = "11-11-2026";

String regex = "^\\d{2}-\\d{2}-\\d{4}$";

System.out.println(date.matches(regex));
// true
```

Note: This checks the format only, not whether the date actually exists.

## 4.15 Extract Words Containing Only Lowercase Letters

```java
String s = "java Spring boot PYTHON";

Pattern p = Pattern.compile("\\b[a-z]+\\b");

Matcher m = p.matcher(s);

while (m.find()) {
    System.out.println(m.group());
}

// java
// boot
```

---

# 5. REGEX PATTERNS USEFUL IN DSA AND CODING PROBLEMS

## 5.1 Count Digits in a String

```java
String s = "Java123Spring456";

int count = s.length() - s.replaceAll("\\d", "").length();

System.out.println(count);

// 6
```

## 5.2 Count Alphabets

```java
String s = "Java123Spring";

int count = s.replaceAll("[^a-zA-Z]", "").length();

System.out.println(count);

// 11
```

## 5.3 Count Special Characters

```java
String s = "Java@123#Spring!";

int count = s.replaceAll("[a-zA-Z0-9]", "").length();

System.out.println(count);

// 3
```

## 5.4 Keep Only Numbers

```java
String s = "A1B2C3D4";

String result = s.replaceAll("\\D", "");

System.out.println(result);

// 1234
```

## 5.5 Keep Only Alphabets

```java
String s = "Java123@Spring";

String result = s.replaceAll("[^a-zA-Z]", "");

System.out.println(result);

// JavaSpring
```

## 5.6 Split String into Individual Characters

```java
String s = "JAVA";

String[] arr = s.split("");

// [J, A, V, A]
```

For reliable character iteration, especially with Unicode, prefer `toCharArray()` or code-point iteration.

## 5.7 Check Whether String Contains Only Lowercase Letters

```java
String s = "java";

System.out.println(s.matches("[a-z]+"));
// true
```

## 5.8 Remove Consecutive Duplicate Characters

```java
String s = "aaabbcccdd";

String result = s.replaceAll("(.)\\1+", "$1");

System.out.println(result);

// abcd
```

**Concept:** `(.)` captures a character and `\\1+` matches repetitions of that same character.

## 5.9 Find Consecutive Repeated Characters

```java
String s = "programming";

Pattern p = Pattern.compile("(.)\\1+");

Matcher m = p.matcher(s);

while (m.find()) {
    System.out.println(m.group());
}

// mm
```

## 5.10 Check Whether a String Contains a Digit

```java
String s = "Java123";

System.out.println(s.matches(".*\\d.*"));
// true
```

**Important:** `matches()` checks the whole string, so `.*` is used around the digit pattern.

---

# 6. IMPORTANT ESCAPING RULE IN JAVA

Regex uses backslashes, but Java strings also use backslashes.

Therefore, escape backslashes twice.

| Regex meaning | Java string |
|---|---|
| `\d` | `"\\d"` |
| `\D` | `"\\D"` |
| `\w` | `"\\w"` |
| `\W` | `"\\W"` |
| `\s` | `"\\s"` |
| `\S` | `"\\S"` |
| `\b` | `"\\b"` |
| `\.` | `"\\."` |

Example:

```java
String s = "Java123";

System.out.println(s.replaceAll("\\d", ""));
// Java
```

**Common mistake:** Writing `"\d+"` in Java is invalid. Write `"\\d+"`.

Also remember that `"\b"` in Java is a backspace character, not the regex word-boundary expression. Use `"\\b"`.

---

# 7. FINAL REGEX QUICK REVISION TABLE

| Requirement | Regex |
|---|---|
| Only digits | `\\d+` |
| Only alphabets | `[a-zA-Z]+` |
| Only lowercase | `[a-z]+` |
| Only uppercase | `[A-Z]+` |
| Alphanumeric | `[a-zA-Z0-9]+` |
| Exactly 10 digits | `\\d{10}` |
| At least 4 digits | `\\d{4,}` |
| Remove digits | `\\d+` |
| Remove non-digits | `\\D+` |
| Remove special characters | `[^a-zA-Z0-9]` |
| Remove whitespaces | `\\s+` |
| Replace multiple spaces with one | `\\s+` |
| Extract numbers | `\\d+` |
| Extract words | `[a-zA-Z]+` |
| Word boundary | `\\b` |
| Starts with Java | `^Java` |
| Ends with Java | `Java$` |
| Contains digits | `.*\\d.*` |
| Contains only lowercase | `[a-z]+` |
| Repeated characters | `(.)\\1+` |
| Duplicate consecutive words | `\\b(\\w+)\\s+\\1\\b` |
| Email basic validation | `^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$` |
| Split by comma or space | `[,\\s]+` |
| Split by multiple delimiters | `[,;:@]+` |

---

# 8. INTERVIEW TIPS — WHAT TO REMEMBER

1. `matches()` → Entire string.
2. `find()` → Search anywhere.
3. `lookingAt()` → Beginning only.
4. `split()` → Break string using regex.
5. `replace()` → Literal replacement.
6. `replaceAll()` → Regex replacement.
7. `replaceFirst()` → First regex match only.
8. `\\d+` → One or more digits.
9. `\\D+` → One or more non-digits.
10. `[^a-zA-Z0-9]` → Anything except alphanumeric.
11. `\\s+` → One or more whitespace characters.
12. `^` and `$` → Start and end anchors.
13. `\\b` → Word boundary.
14. `(.)\\1+` → Consecutive repeated characters.
15. `Pattern` + `Matcher` → Advanced matching and extraction.

**Final advice:** For Java interviews, prioritize character classes, quantifiers, anchors, `split()`, `replaceAll()`, `matches()`, `find()`, groups and backreferences. These cover most practical Regex questions and common String-processing tasks.