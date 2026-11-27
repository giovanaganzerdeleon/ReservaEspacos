# Project decisions

**English** · [Português (Brasil)](DECISIONS.pt-BR.md)

This file records the current implementation choices for the MVP. The team should review and confirm them during the project check-in.

## 25 November — Day 3

### Keep the existing Java/Spring Boot back-end

- **Decision:** Continue the existing Java 21 and Spring Boot application.
- **Alternatives considered:** Rebuild the API with a different server framework or use a hosted backend service.
- **Reason:** The repository already contained the Java application and room API; continuing it avoids duplicating work and keeps the MVP within the team's existing project.
- **Trade-off:** The project requires a Java 21 runtime and Maven build tooling.

### Separate front-end and back-end source directories

- **Decision:** Keep the UI source in `/frontend`, the Spring application in `/backend`, and supporting briefings/presentation materials in `/docs`.
- **Alternatives considered:** Put static files inside the Java classpath or create a separate front-end framework/build.
- **Reason:** Separate directories make responsibility boundaries clear without introducing a package manager or extra build step for a small MVP. During local development, Spring serves the front-end files so the UI and API share an origin.
- **Trade-off:** The front-end is framework-free and does not have a separate automated test suite yet.

### Use server-side sessions for sign-in

- **Decision:** Store passwords as BCrypt hashes and use an HTTP-only server session for the authenticated user.
- **Alternatives considered:** JWT tokens stored by the browser or a managed identity provider.
- **Reason:** The browser and API are served from one local origin, so a session cookie is sufficient and avoids keeping bearer credentials in browser storage.
- **Trade-off:** Local development uses the application's session store; production deployment and session operations have not been configured.

### Include shared campus items with stock-aware bookings

- **Decision:** Add an **Itens do campus** page where a signed-in user can create, view, edit, or cancel bookings for a quantity of stock items, including projectors, notebooks, lab kits, whiteboards, and marker kits. Active bookings for the same item may overlap only while the combined quantity remains within stock; adjacent time intervals do not overlap.
- **Alternatives considered:** Reserve a single physical unit per booking or reserve equipment only as part of a room booking.
- **Reason:** Shared stock better represents classroom supplies such as marker kits and lets the demo cover both equipment and basic classroom materials.
- **Trade-off:** Initial stock counts are illustrative, not verified Senac inventory. Recurrence, approvals, and synchronization with an official inventory system remain outside scope. Cancelled bookings are retained as cancelled records.

### Run the same quality checks locally and in CI

- **Decision:** Use Maven Wrapper, Checkstyle, and JUnit; run them in GitHub Actions for pull requests and pushes to `master` and `main`.
- **Alternatives considered:** Run only a package build or depend on local manual checks.
- **Reason:** Automated lint and tests catch back-end regressions consistently.
- **Trade-off:** A remote green run can only be confirmed after the workflow is pushed and executed in GitHub Actions.

### Protect `main` and deliver changes through reviewed Pull Requests

- **Decision:** Keep `main` protected and require Pull Requests for repository changes.
- **Reason:** The restriction makes branch changes visible and keeps builds, review, lint, and tests in the contribution workflow.
- **Trade-off:** Direct pushes to `main` are not permitted; feature work must be committed to a branch and submitted for review before merge.

## 26 November — Day 4

### Prepare a demo catalog without replacing existing data

- **Decision:** On startup, seed four rooms and the existing sample items only when their respective tables are empty.
- **Reason:** The team can demonstrate the catalog without entering resources one by one, and existing data is not overwritten.
- **Trade-off:** Names and stock counts are illustrative, not an official inventory. The seed does not create a user or default password; create the demo account through sign-up.

### Stabilize the demo path before adding features

- **Proposed decision for team confirmation:** Freeze the presentation scope around authentication and bookings; demonstrate an item booking with the stock limit, and include room bookings only if that flow is stable.
- **Deferred beyond the MVP:** AI time suggestions, natural-language booking requests, recurrence, approvals, official inventory integration, and public deployment.
- **Reason:** The Day 4 checklist prioritizes a working, repeatable happy path with tests over expanding the interface before the demo.
- **Status:** The team still needs to confirm the final scope and fallback plan; treat this section as a proposal, not a completed team decision.

## 27 November — Day 5

### Team leadership roles

- **Presentation lead:** Theo coordinates the outline and rehearsal.
- **Tech lead:** Giovana leads technical decisions and preparation.
- **Note:** the team should assign the remaining speakers and task owners.
