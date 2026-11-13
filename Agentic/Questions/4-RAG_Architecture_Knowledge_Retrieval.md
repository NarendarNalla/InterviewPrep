# Module 4: Interview Questions

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
| 4.1 – 4.3 | RAG Basics and Architecture | [Go to](#41--43-rag-basics-and-architecture) |
| 4.4 – 4.7 | Ingestion, Chunking, Embeddings, Vector DBs | [Go to](#44--47-ingestion-chunking-embeddings-and-vector-dbs) |
| 4.8 – 4.11 | Retrieval, Search, Reranking, Query Transformation | [Go to](#48--411-retrieval-search-reranking-and-query-transformation) |
| 4.12 – 4.13 | Advanced Patterns and Spring Boot Implementation | [Go to](#412--413-advanced-patterns-and-spring-boot-implementation) |
| 4.14 – 4.18 | Production, Evaluation, and Design | [Go to](#414--418-production-evaluation-and-design) |

---

## 4.1 – 4.3 RAG Basics and Architecture

```text
  ●  ●  ●   4.1 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is RAG?

A  Retrieval-Augmented Generation. The system retrieves relevant
   external information, adds it to the LLM context, and generates
   an answer grounded in that evidence.
```

```text
  ●  ●  ●   4.2 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  Why do we need RAG?

A  LLMs do not know private, recently updated, or domain-specific
   company information. RAG supplies that knowledge at runtime,
   reduces unsupported answers, and provides source references.
```

```text
  ●  ●  ●   4.2 · Q2                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Does RAG retrain or modify the LLM?

A  No. RAG does not change the model's parameters. It supplies
   relevant external information during inference.
```

```text
  ●  ●  ●   4.3 · Q1                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Explain the two pipelines in a RAG architecture.

A  The indexing pipeline ingests, chunks, embeds, and stores
   documents. The query-time pipeline embeds the question,
   retrieves and filters chunks, builds the context, and generates
   the grounded answer.
```

## 4.4 – 4.7 Ingestion, Chunking, Embeddings, and Vector DBs

```text
  ●  ●  ●   4.4 · Q1                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What does document ingestion involve?

A  Loading documents, extracting their text, cleaning it, extracting
   metadata such as source and version, and preparing the content for
   chunking. PDFs with tables, scans, or multi-column layouts may need
   OCR or specialized parsing.
```

```text
  ●  ●  ●   4.5 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What is chunking and why is it needed?

A  Splitting a large document into smaller pieces. Sending an entire
   document for every question is inefficient and may exceed the
   context window. Chunks let the retriever return only relevant text.
```

```text
  ●  ●  ●   4.5 · Q2                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is the trade-off between small and large chunks?

A  Small chunks give more precise retrieval and less irrelevant text,
   but can lose surrounding context. Large chunks preserve context but
   may include unrelated content and increase prompt size.
```

```text
  ●  ●  ●   4.5 · Q3                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is chunk overlap and why use it?

A  Repeating some content between adjacent chunks so that related
   sentences are not split apart at arbitrary boundaries.
```

```text
  ●  ●  ●   4.5 · Q4                              🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  Fixed-size vs semantic chunking: which would you choose?

A  Fixed-size is simple and predictable but can split related text.
   Semantic or structure-aware chunking preserves meaning and sections
   better, at the cost of more complex processing. Start with recursive
   or structure-aware chunking and evaluate against real queries.
```

```text
  ●  ●  ●   4.6 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What are embeddings?

A  Numerical vectors that represent the meaning of text. Texts with
   similar meaning produce vectors that are close together.
```

```text
  ●  ●  ●   4.6 · Q2                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Explain cosine similarity.

A  A measure of the angle between two vectors. Values closer to 1
   indicate greater semantic alignment within a compatible embedding
   space.
```

```text
  ●  ●  ●   4.6 · Q3                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Why must the same embedding model be used for documents and queries?

A  Both must be represented in a compatible vector space. Changing the
   embedding model generally requires re-embedding the indexed documents.
```

```text
  ●  ●  ●   4.7 · Q1                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is a vector database?

A  A system that stores embeddings with their text and metadata, and
   supports similarity-based retrieval using vector indexes.
```

## 4.8 – 4.11 Retrieval, Search, Reranking, and Query Transformation

```text
  ●  ●  ●   4.8 · Q1                                  🟢 BASIC
────────────────────────────────────────────────────────────
Q  What are Top-K and similarity threshold?

A  Top-K is the maximum number of highest-ranked results returned.
   A similarity threshold is the minimum score a result must reach to
   be used. Threshold values must be calibrated for the model and data.
```

```text
  ●  ●  ●   4.9 · Q1                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Semantic search vs keyword search?

A  Keyword search matches exact terms and is strong for identifiers
   and error codes. Semantic search matches meaning and handles different
   wording, but may miss exact identifiers.
```

```text
  ●  ●  ●   4.9 · Q2                            🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is hybrid search?

A  A combination of keyword and vector retrieval, with the results merged
   through a fusion method such as Reciprocal Rank Fusion. It is useful
   for technical content that mixes natural-language questions and exact
   identifiers.
```

```text
  ●  ●  ●   4.10 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is reranking?

A  A second pass that evaluates the query and each candidate together
   to produce a more accurate relevance order over a small candidate set.
```

```text
  ●  ●  ●   4.10 · Q2                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  Bi-encoder vs cross-encoder: when do you use each?

A  Bi-encoders encode query and documents separately, enabling fast
   vector search over large collections. Cross-encoders process the
   query and document together, which is more accurate but more costly,
   so they are typically used to rerank a small candidate set.
```

```text
  ●  ●  ●   4.11 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What is query transformation?

A  Rewriting or expanding the user's question to match how the knowledge
   base is written. Techniques include query rewriting, query expansion,
   multi-query retrieval, HyDE, and multi-hop decomposition.
```

```text
  ●  ●  ●   4.11 · Q2                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  What is the risk of HyDE?

A  The hypothetical answer is generated by the model and may be wrong.
   It is only a retrieval aid and must never be treated as verified
   evidence in the final answer.
```

## 4.12 – 4.13 Advanced Patterns and Spring Boot Implementation

```text
  ●  ●  ●   4.12 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Compare naive RAG and advanced RAG.

A  Naive RAG runs a single vector search and sends the top-K chunks to
   the LLM. Advanced RAG adds query transformation, hybrid retrieval,
   metadata filtering, reranking, and context construction for better
   relevance.
```

```text
  ●  ●  ●   4.12 · Q2                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  What is Agentic RAG?

A  A pattern where an agent decides whether to retrieve, which source
   to search, whether to rewrite the query, whether to search again,
   and when the evidence is sufficient to answer.
```

```text
  ●  ●  ●   4.12 · Q3                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  When would you use Graph RAG?

A  When questions depend on relationships, dependencies, or multi-hop
   reasoning across connected entities. It adds extraction and graph
   maintenance complexity, so it is not automatically better than
   vector RAG.
```

```text
  ●  ●  ●   4.13 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  How would you build RAG in Spring Boot?

A  Use an ingestion service to parse, chunk, embed, and store documents
   in a vector store such as pgvector. A retrieval service searches for
   relevant chunks, a context builder assembles the prompt, and an LLM
   service generates the grounded answer exposed through a REST endpoint.
```

## 4.14 – 4.18 Production, Evaluation, and Design

```text
  ●  ●  ●   4.14 · Q1                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How do you handle stale or updated documents?

A  Use stable document IDs, versioning, and content checksums. When a
   document changes, re-index only that document, delete its obsolete
   chunks, and make sure old versions cannot appear in retrieval results.
```

```text
  ●  ●  ●   4.14 · Q2                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  Where should authorization be enforced in enterprise RAG?

A  During or before retrieval, using authenticated identity and the
   document's access metadata. Relying on the LLM to hide unauthorized
   content is not a security control.
```

```text
  ●  ●  ●   4.14 · Q3                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How do you defend against prompt injection in retrieved documents?

A  Treat retrieved content as untrusted data, separate it clearly from
   instructions, never let it override system policy, enforce access
   control and tool permissions in application code, and minimize secrets
   in the model context.
```

```text
  ●  ●  ●   4.14 · Q4                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  Why should document ingestion be asynchronous?

A  Large documents take time to parse, chunk, and embed. Processing them
   through a queue such as Kafka allows retries, independent worker scaling,
   status tracking, and avoids long-running HTTP requests.
```

```text
  ●  ●  ●   4.14 · Q5                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  What should you cache in a RAG system, and what must be careful?

A  Embedding results, retrieval results, extracted document content, and
   final answers can all be cached. Response caches must respect user
   permissions, document versions, and freshness, or they can leak or
   serve stale answers.
```

```text
  ●  ●  ●   4.15 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  How do you evaluate retrieval quality?

A  Use Precision@K, Recall@K, MRR, and NDCG@K against a labeled set of
   questions and expected relevant documents.
```

```text
  ●  ●  ●   4.15 · Q2                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How do you evaluate the generated answer, and why not just read it?

A  Measure faithfulness to the retrieved evidence, answer relevance,
   correctness against reference answers, and citation accuracy. A fluent
   answer can still be based on the wrong document, so retrieval and
   generation must be evaluated separately.
```

```text
  ●  ●  ●   4.16 · Q1                           🟡 INTERMEDIATE
────────────────────────────────────────────────────────────
Q  RAG vs fine-tuning: when would you choose each?

A  Use RAG when knowledge changes often, answers need sources, or access
   depends on the user. Use fine-tuning to adapt behavior, style, or a
   specialized task with a curated dataset. They are complementary, not
   mutually exclusive.
```

```text
  ●  ●  ●   4.17 · Q1                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  What should happen when no relevant document is retrieved?

A  The system should not force an answer. It can try alternative retrieval
   or ask a clarifying question, and if evidence is still missing, state
   that the information is unavailable.
```

```text
  ●  ●  ●   4.18 · Q1                             🔴 ADVANCED
────────────────────────────────────────────────────────────
Q  How would you scale document ingestion and retrieval latency?

A  Move ingestion to async workers with retries and batching, index with
   an appropriate vector index, cache embeddings and frequent retrievals,
   run independent retrieval steps in parallel, and track latency at each
   pipeline stage to find bottlenecks.
```

---

**Notes on the design:**
- Image-placeholder artifacts from the source (the vector-database "Official website" and "Official repository" stubs) were removed.
- Repeated content was merged, for example the retrieval-failure flow and the production observability table.
- Difficulty tags are my judgment, not part of the original syllabus.
- The source's 24-row interview table was condensed into the Module 4 Interview Takeaways table and the question file. A few questions were added, such as the no-relevant-document and bi-encoder vs cross-encoder questions, because they are commonly asked alongside these topics.