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

- Wrap the title in `<h3>`.
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
Leetcode 138 - Copy List With Random Pointer.
A linked list of length n is given such that each node contains an additional random
pointer, which could point to any node in the list, or null. Construct a deep copy.
None of the pointers in the new list should point to nodes in the original list.

**Examples:**

```
Input:  [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Input:  [[1,1],[2,1]]
Output: [[1,1],[2,1]]
```

**Constraints:**

```
0 <= n <= 1000
-10^4 <= Node.val <= 10^4
Node.random is null or points to some node in the linked list.
```

**File in context:** `DeepCopyLinkedListWithRandomPointers.java`

### Expected output (the inserted block)

```java
/**
 * <h3>Leetcode 138: Copy List With Random Pointer</h3>
 * <p>
 * A linked list of length n is given such that each node contains an additional random
 * pointer, which could point to any node in the list, or null. Construct a deep copy
 * of the list. None of the pointers in the new list should point to nodes in the
 * original list.
 * </p>
 * <p>
 * Example: <br>
 * Input:  [[7,null],[13,0],[11,4],[10,2],[1,0]] <br>
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]] <br>
 * Input:  [[1,1],[2,1]] <br>
 * Output: [[1,1],[2,1]] <br>
 * </p>
 * <p>
 * Constraints:
 * <ul>
 * <li> 0 &lt;= n &lt;= 1000 </li>
 * <li> -10^4 &lt;= Node.val &lt;= 10^4 </li>
 * <li> Node.random is null or points to some node in the linked list. </li>
 * </ul>
 * </p>
 */
public class DeepCopyLinkedListWithRandomPointers {
```

---

## Error Cases

| Situation                        | Agent behaviour                                                                                                         |
|----------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| No file provided in context      | Ask the user to attach or paste the solution file                                                                       |
| File language cannot be detected | Ask the user to confirm the language before proceeding                                                                  |
| Problem statement is absent      | Ask the user to provide it; do not generate a placeholder                                                               |
| Multiple files in context        | Apply the comment to each file individually if each is a separate solution; otherwise ask the user which file to target |
