# Day 4 checklist — 26 November 2026

> **Historical snapshot:** this status was prepared on 26 November. Checkboxes reflect verification at that time and should not be treated as current. See [`DIA5_STATUS.md`](DIA5_STATUS.md) for current priorities.

This checklist tracks MVP stabilization and demo preparation. A checked box means verified; plans and team actions remain unchecked.

## 1. Stabilize the Day 3 essentials

- [ ] Exercise the complete end-to-end flow: sign-up/sign-in, booking, and viewing/editing/cancellation.
- [x] Confirm that sign-up uses the authentication API and the service's password validation is covered by tests.
- [ ] Test sign-up and sign-in over HTTP/browser; server startup was confirmed, but the authentication flow has not yet been verified.
- [x] README, DECISIONS, and AI_LOG exist in English and Brazilian Portuguese.
- [x] The GitHub Actions workflow includes build, Checkstyle, and tests.
- [ ] Confirm a green workflow run that includes the current local changes and after merge to the main branch. PR #6 passed before these edits.
- [x] `.gitignore` excludes `.env` files and the local H2 database; do not commit local data or credentials.

## 2. Happy path and demo data

- [ ] Agree on one demo happy path and run it more than once.
- [x] Startup seeds four rooms and five item types only when their respective tables are empty; verify the rooms after restarting with this change.
- [ ] Confirm the final presentation scope with the team. Current proposal: authenticate, book an item, demonstrate the stock limit, and view/edit/cancel; include rooms only if that flow is stable.
- [ ] Define a fallback plan. Proposal: if the server fails, show tests and implementation while clearly stating that this is not a live demo.

## 3. Scope freeze and collaboration

- [ ] Have the team confirm and freeze the presentation scope; do not add features outside the chosen path.
- [ ] Each teammate makes at least one Conventional Commit today, using small PRs.
- [ ] Confirm demo owners and board task assignments.

## 4. Tests and pipeline

- [x] `AuthServiceTest`: 5 tests passed, including correct/incorrect passwords and a malformed stored hash.
- [x] Full suite: 19 tests passed and Checkstyle reported 0 violations; Maven reported `Nothing to compile`, so a clean source rebuild is still pending.
- [x] PR #6 passed Build, Lint, and Tests for commit `c1e545e`; that run predates the current local changes.
- [ ] Evaluate/add an HTTP integration test for the booking lifecycle; there is currently no automated front-end test runner.

## 5. Documentation, security, and presentation

- [x] Bilingual READMEs describe the stack, structure, local run instructions, and the back-end requirement for sign-up/sign-in.
- [x] DECISIONS records current choices and items outside the MVP scope.
- [x] AI_LOG records AI-assisted work and known validation.
- [ ] Rescan files and history before pushing; the previous search for common secret patterns was targeted and is not an absolute guarantee.
- [x] 15-minute presentation and 5-minute check-in outlines are prepared in `/docs`.
- [ ] Divide speaking/demo responsibilities and rehearse closer to presentation day.
- [ ] Check in with the instructor, show what works, and accurately state remaining items/blockers.

## Verification and blockers observed

- The user's terminal output showed Tomcat started on port `8080` and `ReservaCampus esta pronto`; a later local check also found the server listening. Sign-up/sign-in and booking still need HTTP/browser verification.
- Opening a GitHub branch or `frontend/index.html` does not start the server. For local testing, start Spring Boot from `backend/` and open `http://127.0.0.1:8080` on the same computer. There is no public deployment yet.
- The full suite passed with 19 tests and Checkstyle reported 0 violations after adding the room seed.
- The JavaScript connection helper now gives clearer guidance when the API is unreachable. `node --check` could not be run because Node.js is not installed in this environment.
- `iniciar.bat` was run and printed `ReservaCampus esta pronto`; keep its terminal window open during the demo.
- The room seed was added after the startup shown in the user's log; stop and restart the app to load rooms when the table is empty.
- The PR #6 pipeline passed for commit `c1e545e`; changes made locally since then still need a PR and CI.

## Priority next actions

1. Restart the back-end to load the sample rooms and verify both catalogs in the UI.
2. Test sign-up/sign-in at `http://127.0.0.1:8080` and run the happy path more than once.
3. After stopping the current server, run a clean build/test; then confirm GitHub Actions for the current changes.
4. Confirm final scope, fallback plan, teammates' commits, and presentation responsibilities.
