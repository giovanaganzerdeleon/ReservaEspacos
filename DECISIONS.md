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

### Keep the booking flow focused

- **Decision:** A signed-in user can create, view, edit, or cancel their own room bookings. Active bookings cannot overlap for the same room; adjacent time intervals are allowed.
- **Alternatives considered:** Add recurring events, equipment, approval workflows, or automatic alternative-slot suggestions to the Day 3 scope.
- **Reason:** The booking lifecycle and conflict check are the smallest demonstrable end-to-end extension of the existing room catalog.
- **Trade-off:** Cancelled bookings are retained as cancelled records, and the MVP does not include recurring bookings or administrative approvals.

### Run the same quality checks locally and in CI

- **Decision:** Use Maven Wrapper, Checkstyle, and JUnit; run them in GitHub Actions for pull requests and pushes to `master` and `main`.
- **Alternatives considered:** Run only a package build or depend on local manual checks.
- **Reason:** Automated lint and tests catch back-end regressions consistently.
- **Trade-off:** A remote green run can only be confirmed after the workflow is pushed and executed in GitHub Actions.
