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
- **Prompt summary:** Investigate sign-up/sign-in connection failures, prepare a local launcher, and review documentation against the code and Day 4 checklist.
- **Generated/assisted work:** Clarified that a GitHub branch does not host the API; added actionable front-end/terminal messages and the `iniciar.bat` launcher; implemented room and item seeds that only run when their respective tables are empty; corrected mismatches found in bilingual READMEs, status, decisions, and presentation outline.
- **Human review and corrections:** Keep the API and front end on one origin for local use; do not overwrite existing data, create a default password, expose the local server to the network, or claim a public deployment. Startup was confirmed by the user's terminal output; HTTP sign-up/booking remains unverified.
- **Validation:** `checkstyle:check test` passed with 19 tests and 0 violations, but Maven reported `Nothing to compile`; a clean rebuild remains pending until the server using `target` is stopped. Port `8080` was observed listening and the terminal displayed the ready message before the room seed was added; restart and verify the data. PR #6's Actions run passed Build, Lint, and Tests on commit `c1e545e`; later local changes have not gone through CI. `node --check` was unavailable because Node.js is not installed in this environment.

## 27 November — Day 5

### Documentation organization and presentation preparation

- **Tool:** GitHub Copilot in VS Code.
- **Prompt summary:** Organize the project Markdown files for clarity and professionalism, and record Theo as presentation lead and Giovana as tech lead.
- **Generated/assisted work:** Simplified the bilingual READMEs, added Day 5 checklists in English and Portuguese, updated the presentation outline, and recorded the team roles.
- **Team guidance applied:** Preserved the original course briefings; labeled Day 3 and Day 4 status files as historical; left unverified tasks unchecked; clarified that local H2 databases do not synchronize across computers; recorded Theo as presentation lead and Giovana as tech lead.
- **Validation:** Reviewed link consistency and role references across the documentation. No code tests were run because the changes are documentation-only.
- **Team update:** After the user confirmed the presentation team, Fábio was added as a presenter and assigned the live demo and the shared AI-use log.

### Code-based presentation script

- **Tool:** GitHub Copilot in VS Code.
- **Prompt summary:** Create a presentation script faithful to the current ReservaCampus code.
- **Generated/assisted work:** Added a spoken 15-minute presentation and 5-minute instructor check-in, with suggested dialogue, demo sequence, and setup guidance.
- **Review:** Checked the UI flow, endpoints, and rules for authentication, room bookings, item stock, cancellation, and CI. The script does not claim AI is a product feature or suggest an over-stock attempt that the UI limits.
- **Validation:** Documentation review based on the code and existing tests. No code was changed and no tests were run in this step.

### Preparing to host the demo

- **Tool:** GitHub Copilot in VS Code.
- **Prompt summary:** Prepare the project for Railway deployment so the presentation computer can access the MVP in a browser without installing Java or Maven.
- **Generated/assisted work:** Added a PostgreSQL hosting profile, packaged front-end files into the JAR, added a root `Dockerfile`, excluded local files with `.dockerignore`, and wrote bilingual Railway deployment instructions.
- **Review and limits:** Retained H2 and the local configuration; credentials are supplied through environment variables; the H2 console and verbose SQL are disabled in the hosting profile. This is for a demo, does not complete deployment, and is not a production-readiness guarantee.
- **Validation:** `.\mvnw.cmd -B package` passed with 19 tests; `.\mvnw.cmd -B checkstyle:check` passed with 0 violations. The JAR was inspected and contains the static files, the `prod` profile, and the PostgreSQL driver. The Dockerfile uses the Maven Wrapper and runs the tests during image build. PostgreSQL connectivity and deployment were not tested because Railway services are not configured yet.
