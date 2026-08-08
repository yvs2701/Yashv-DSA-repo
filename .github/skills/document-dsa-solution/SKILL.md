---
name: document-dsa-solution
description: Add a documentation comment to a DSA or programming solution file. Use when given a problem statement (from LeetCode, HackerRank, or similar) and one or more solution files that need to be documented with the problem description, examples, and constraints as a doc comment (Javadoc, JSDoc, docstring, etc.) placed above the top-level class or at the top of the file.
---

# Skill: Document DSA Solution File

## Description

Given a DSA or programming problem statement (with optional examples and constraints),
add a documentation comment to the solution file(s) provided in context. The agent must
only modify files explicitly provided in context and must not read, infer from, or touch
any other file in the workspace.

---

## Inputs

| Input                  | Required | Description                                                                                  |
|------------------------|----------|----------------------------------------------------------------------------------------------|
| Problem statement      | Yes      | Full problem description as plain text or copied from the source (e.g. LeetCode, HackerRank) |
| Example inputs/outputs | No       | One or more example cases                                                                    |
| Constraints            | No       | Any constraints block from the problem                                                       |
| File(s) in context     | Yes      | The solution file(s) to be documented                                                        |

---

## Instructions

### Scope

- **Only operate on files explicitly attached or pasted into the current context.**
- **Do not scan, read, or infer from any other file in the project, workspace, or repository.**
- If no file is provided, respond asking for the solution file before proceeding.

### Placement

1. If the file contains a top-level class, interface, or enum declaration — place the
   documentation comment **immediately above** that declaration.
2. If no such top-level OOP construct exists — place the comment at **line 1** of the
   file, even above any import or package statements.
3. Do **not** insert the comment inside a class body, inside a method, or below any
   existing code at the top of the file.

### Comment Style

Use the idiomatic documentation comment style for the detected language:

| Language                | Style                                              |
|-------------------------|----------------------------------------------------|
| Java                    | Javadoc (`/** ... */`) with HTML tags              |
| JavaScript / TypeScript | JSDoc (`/** ... */`)                               |
| Python                  | Module-level docstring (`""" ... """`)             |
| C / C++                 | Doxygen (`/** ... */`) or block comment            |
| C#                      | XML doc comment (`/// <summary>...`)               |
| Go                      | GoDoc line comments (`// ...`) above the construct |
| Kotlin                  | KDoc (`/** ... */`)                                |
| Other                   | Standard block comment for that language           |

### Content Structure

Produce the comment in this order. Omit a section entirely if that information was not
provided — do not fabricate or infer it.

```
1. Problem title / source identifier  (e.g. "Leetcode 138: Copy List With Random Pointer")
2. Problem statement                   (full description, preserve all semantic detail)
3. Examples                            (each example as a labeled input/output pair)
4. Constraints                         (bullet list if multiple)
```

### Formatting Rules (Java / Javadoc — apply equivalent idioms for other languages if widely known or if it is requested)

- Wrap the title in `<h3><a href="">` tags.
- Wrap each logical paragraph in `<p>...</p>`.
- Use `<br>` for line breaks within a paragraph where needed.
- Use `<ul><li>...</li></ul>` for constraint or property lists.
- Use `<b>` for critical warnings or notes already present in the problem text.
- For examples, format each case as:
  ```
  Input: <value> <br>
  Output: <value> <br>
  ```
  grouped inside a single `<p>` block.

### What to Preserve

- **Do not modify any existing code** — methods, fields, logic, inner classes, imports,
  package declarations, existing comments or documentations within method bodies, etc.
- If a documentation comment already exists on the target construct, **replace it**
  entirely with the newly generated one. Do not append or merge.
- Preserve all blank lines and indentation elsewhere in the file exactly as they are.

### What to Ignore

- Any other source files in the project not explicitly provided in context.
- Build files, configs, READMEs, test files, and resources not in context.
- The agent must not use any information from those files even if it can infer their
  existence from package names or import statements.

---

## Output

Return the **full updated file content** with the documentation comment inserted.
Do not return just diff, or just the comment block. Edit the file if possible or return the complete file with
documentations.
No need to fabricate or infer any information from any other sources that was not provided in the input. Which means:

- If certain sections (e.g. examples, constraints) were not provided, simply omit those sections from the comment.
- Do not add any additional notes, warnings, or formatting based on the code content or any comments already present in
  the file. Only use the information explicitly given in the problem statement and examples.

---

## Example (Java)

### Input supplied to the agent

**Problem:**
LeetCode 1415: The k-th Lexicographical String of All Happy Strings of Length n.
link: https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/description/

A happy string is a string that:

- consists only of letters of the set ['a', 'b', 'c'].
- s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).

For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc" are
not happy strings.

Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order. Return the
kth string of this list or return an empty string if there are less than k happy strings of length n.

**Examples:**

```
Example 1:
Input: n = 1, k = 3
Output: "c"
Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".

Example 2:
Input: n = 1, k = 4
Output: ""
Explanation: There are only 3 happy strings of length 1.

Example 3:
Input: n = 3, k = 9
Output: "cab"
Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]. You will find the 9th string = "cab".
```

**Constraints:**

```
1 <= n <= 10
1 <= k <= 10^2
```

**File in context:** `KthHappyString.java`

### Expected output (the inserted block)

```java
/**
 * <h3><a href="https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/description/">
 * LeetCode 1415: The k-th Lexicographical String of All Happy Strings of Length n
 * </a></h3>
 * <p>
 * A happy string is a string that:
 * <ul>
 * <li>consists only of letters of the set ['a', 'b', 'c'].</li>
 * <li>s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).</li>
 * </ul>
 * </p>
 * <p>
 * For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings
 * "aa", "baa" and "ababbc" are not happy strings.
 * </p>
 * <p>
 * Given two integers n and k, consider a list of all happy strings of length n sorted in
 * lexicographical order.
 * Return the kth string of this list or return an empty string if there are less than k
 * happy strings of length n.
 * </p>
 * <p>
 * Example 1: <br>
 * Input: n = 1, k = 3 <br>
 * Output: "c" <br>
 * Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".
 * </p>
 * <p>
 * Example 2: <br>
 * Input: n = 1, k = 4 <br>
 * Output: "" <br>
 * Explanation: There are only 3 happy strings of length 1.
 * </p>
 * <p>
 * Example 3: <br>
 * Input: n = 3, k = 9 <br>
 * Output: "cab" <br>
 * Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb",
 * "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]. You will find the 9th string = "cab".
 * </p>
 * <p>
 * Constraints:
 * <ul>
 * <li>1 &lt;= n &lt;= 10</li>
 * <li>1 &lt;= k &lt;= 10^2</li>
 * </ul>
 * </p>
 */
public class KthHappyString {
    // ... code implementation
}
```

---

## Error Cases

| Situation                        | Agent behaviour                                                                                                         |
|----------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| No file provided in context      | Ask the user to attach or paste the solution file                                                                       |
| File language cannot be detected | Ask the user to confirm the language before proceeding                                                                  |
| Problem statement is absent      | Ask the user to provide it; do not generate a placeholder                                                               |
| Multiple files in context        | Apply the comment to each file individually if each is a separate solution; otherwise ask the user which file to target |
