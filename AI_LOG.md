# AI usage log

**English** · [Português (Brasil)](AI_LOG.pt-BR.md)

Entries describe AI-assisted work in this repository. Generated output was reviewed and checked against the existing code; it is not evidence that external team or GitHub actions have been completed.

## 25 November — Day 3

### MVP implementation and verification

- **Tool:** GitHub Copilot in VS Code.
- **Prompt summary:** Implement a demonstrable room-booking flow, preserve the existing sign-in, keep front-end and back-end source separate, and follow the Day 3 checklist and repository Markdown guidance.
- **Generated/assisted work:** Booking API and persistence, ownership and overlap validation, booking form/list/edit/cancel interactions, CI/test updates, and project documentation.
- **Human review and corrections:** Kept the existing Java/Spring Boot stack; retained cancelled bookings instead of deleting them; checked ownership before edits; allowed adjacent bookings while rejecting time overlaps; used server-side BCrypt/session authentication; removed stale sample documentation rather than treating it as project fact. A UI edge case was corrected so an existing booking can retain its room if that room is later marked unavailable.
- **Validation:** Maven Checkstyle and JUnit were run locally; the authentication test class now has 5 passing tests, including correct login, incorrect password, and malformed stored hashes. Remote Actions status is tracked separately and is not inferred from local results.

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

## 26 November — Day 4

### Sign-up diagnosis and demo-path preparation

- **Tool:** GitHub Copilot in VS Code.
- **Prompt summary:** Investigate the “Failed to fetch” sign-up/sign-in error and incorporate the Day 4 checklist into the project documentation.
- **Generated/assisted work:** Confirmed no process was listening on `127.0.0.1:8080`; clarified that a GitHub branch does not host the API; added more actionable front-end/terminal messages and the `iniciar.bat` launcher; prepared bilingual Day 4 status files and a proposed demo scope.
- **Human review and corrections:** Keep the API and front end on the same origin for local use; do not expose the local server to the network or claim a public deployment. Live sign-up remains unverified until the back-end is started.
- **Validation:** Clean compilation, Checkstyle (0 violations), and the full suite (17 tests) passed. `git diff --check` passed. Actual startup through `iniciar.bat`, HTTP/browser flow, and CI for the current changes still need verification; `node --check` was unavailable because Node.js is not installed in this environment.
