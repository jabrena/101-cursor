# Questions

## Questions for Cursor team

- [x] In what way, a business account is possible to configure all cursors environments to put in Privacy mode?

```
Based on the search results, here's how Privacy Mode works with Cursor business accounts:
Privacy Mode is Enforced: For users who are part of a team (which is typical for business/enterprise plans), Privacy Mode is forcibly enabled by default. This means that none of the code, prompts, or telemetry data from these users' Cursor environments will be stored by Cursor or any third-party providers (like OpenAI, Anthropic, etc.).
How it Works:
When a user joins a team with enforced privacy mode, their Cursor client automatically detects this (within about 5 minutes) and switches to Privacy Mode.
Even if the client setting fails to update immediately, Cursor's backend servers check if the user belongs to a team enforcing privacy mode and treat the request as if Privacy Mode is on, ensuring the guarantee.
Code data is still sent to Cursor's servers temporarily (in memory) to process AI requests (like prompt building and context generation) and compute embeddings for codebase indexing (if enabled), but it is not persisted.
For codebase indexing, only obfuscated file paths and embeddings might be stored, not the plaintext code itself.
Cached file contents used for latency reduction are encrypted with client-generated keys that exist only for the duration of the request.
Configuration: There isn't a specific setting for administrators to "configure all environments" because Privacy Mode is automatically enforced at the team/business account level. Individual users within that business account cannot disable it.
In summary, if you have a Cursor Business plan, Privacy Mode is automatically enforced for all users belonging to your team, ensuring that their code and prompts are not stored or used for training across all their Cursor environments linked to that account.
```

- [x] What is the difference between Privacy mode and Codebase indexing?

```
Privacy Mode and Codebase Indexing are two distinct concepts related to how an AI coding assistant like this one interacts with your code:
Privacy Mode: This setting typically controls whether your code snippets are sent to the AI model's servers for processing or potentially used to improve the model. When enabled, it aims to prevent your code from leaving your machine or being stored/used beyond the immediate context of your request, enhancing confidentiality.
Codebase Indexing: This is a process performed locally on your machine. The tool analyzes your project's files to build an index (often a semantic or symbolic representation) of your code. This index allows the assistant to understand your codebase structure, find definitions, references, and provide more context-aware assistance (like code generation, search, or answering questions about your code) much faster and more accurately without having to re-read or send large amounts of code for every request.
In short:
Privacy Mode is about controlling external data transmission and usage of your code by the AI provider.
Codebase Indexing is about local analysis of your code to improve the assistant's understanding and features on your machine.
Codebase indexing can generally function even with Privacy Mode enabled, as the indexing happens locally. Privacy Mode ensures that the content being indexed isn't unnecessarily shared externally.
```

## Questions from the community

- [ ] What happen with Junior profiles?