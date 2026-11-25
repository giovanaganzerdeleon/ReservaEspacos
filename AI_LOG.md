# AI usage log

**English** · [Português (Brasil)](AI_LOG.pt-BR.md)

Entries describe AI-assisted work in this repository. Generated output was reviewed and checked against the existing code; it is not evidence that external team or GitHub actions have been completed.

## 25 November — Day 3

### MVP implementation and verification

- **Tool:** GitHub Copilot in VS Code.
- **Prompt summary:** Implement a demonstrable room-booking flow, preserve the existing sign-in, keep front-end and back-end source separate, and follow the Day 3 checklist and repository Markdown guidance.
- **Generated/assisted work:** Booking API and persistence, ownership and overlap validation, booking form/list/edit/cancel interactions, CI/test updates, and project documentation.
- **Human review and corrections:** Kept the existing Java/Spring Boot stack; retained cancelled bookings instead of deleting them; checked ownership before edits; allowed adjacent bookings while rejecting time overlaps; used server-side BCrypt/session authentication; removed stale sample documentation rather than treating it as project fact. A UI edge case was corrected so an existing booking can retain its room if that room is later marked unavailable.
- **Validation:** Maven Checkstyle and JUnit were run locally; the verified result (10 tests) is recorded in `DIA3_STATUS.md`. Remote Actions status is tracked separately and is not inferred from local results.

### Repository structure and bilingual delivery docs

- **Tool:** GitHub Copilot in VS Code.
- **Prompt summary:** Finish missing Day 3 documentation in English and Brazilian Portuguese and organize source into front-end, back-end, and docs directories as required by the existing project guidance.
- **Generated/assisted work:** Updated the READMEs, architecture/decision records, AI logs, status checklist, and presentation/check-in outline; moved the Java application under `/backend` and supporting briefing material under `/docs`.
- **Human review and corrections:** Kept the main README files at the repository root; did not invent presenter assignments, board links, instructor access, individual commits, or remote CI results; marked those items for team/GitHub confirmation.
- **Validation:** Documentation statements are checked against the current source tree and local test output. Remaining external actions are explicitly listed in the bilingual status files.
