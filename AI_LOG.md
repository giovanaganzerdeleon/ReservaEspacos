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

### Campus item reservations

- **Tool:** GitHub Copilot in VS Code.
- **Prompt summary:** Add an in-app page for reserving campus/Senac items, with quantity-based stock and examples covering projectors, notebooks, lab equipment, whiteboards, and markers; keep the project Markdown guidance up to date.
- **Generated/assisted work:** Added item and item-booking entities, authenticated APIs, stock availability checks with item-row locking, a sample inventory initializer, and the **Itens do campus** interface for catalog, booking, editing, cancellation, and quantity-aware availability.
- **Human review and corrections:** Treated stock counts as demonstration data rather than verified Senac inventory; kept the feature separate from room bookings; used overlapping time intervals to calculate shared stock; documented recurring bookings and official inventory integration as out of scope.
- **Validation:** Checkstyle reported 0 violations and 15 back-end tests passed, including four focused item-reservation service tests and a repository test for overlapping, adjacent, cancelled, and self-ignored stock reservations. Live HTTP/browser testing of the new flow remains to be done.
