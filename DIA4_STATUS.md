# Day 4 checklist — 26 November 2026

This checklist tracks MVP stabilization and demo preparation. A checked box means verified; plans and team actions remain unchecked.

## 1. Stabilize the Day 3 essentials

- [ ] Exercise the complete end-to-end flow: sign-up/sign-in, booking, and viewing/editing/cancellation.
- [x] Confirm that sign-up uses the authentication API and the service's password validation is covered by tests.
- [ ] Test sign-up and sign-in over HTTP/browser with the back-end running. At the last check, no server was listening on `127.0.0.1:8080`.
- [ ] Rerun all tests and Checkstyle after the current changes.
- [x] README, DECISIONS, and AI_LOG exist in English and Brazilian Portuguese.
- [x] The GitHub Actions workflow includes build, Checkstyle, and tests.
- [ ] Confirm a green workflow run for the current changes and after merge to the main branch.
- [x] `.gitignore` excludes `.env` files and the local H2 database; do not commit local data or credentials.

## 2. Happy path and demo data

- [ ] Agree on one demo happy path and run it more than once.
- [x] The code includes an initializer for demonstration items; verify the actual data after starting the application.
- [ ] Confirm the final presentation scope with the team. Current proposal: authenticate, book an item, demonstrate the stock limit, and view/edit/cancel; include rooms only if that flow is stable.
- [ ] Define a fallback plan. Proposal: if the server fails, show tests and implementation while clearly stating that this is not a live demo.

## 3. Scope freeze and collaboration

- [ ] Have the team confirm and freeze the presentation scope; do not add features outside the chosen path.
- [ ] Each teammate makes at least one Conventional Commit today, using small PRs.
- [ ] Confirm demo owners and board task assignments.

## 4. Tests and pipeline

- [x] `AuthServiceTest`: 5 tests passed, including correct/incorrect passwords and a malformed stored hash.
- [x] The last recorded full run before the current changes had 15 passing tests and no Checkstyle violations.
- [ ] Rerun the full suite and Checkstyle after the current changes; confirm essential tests are not empty.
- [ ] Confirm the latest green GitHub Actions result. The green PR #4 run predates the current local changes.
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

- The local request to `127.0.0.1:8080` was refused because no process was listening on that port. “Failed to fetch” occurs before the API responds; it is not a rejection of the password or sign-up fields.
- Opening a GitHub branch or `frontend/index.html` does not start the server. For local testing, start Spring Boot from `backend/` and open `http://127.0.0.1:8080` on the same computer. There is no public deployment yet.
- `AuthServiceTest` passed with 5 tests. The full suite and Checkstyle need to be rerun after the latest changes.
- The JavaScript connection helper now gives clearer guidance when the API is unreachable. `node --check` could not be run because Node.js is not installed in this environment.
- The green PR #4 pipeline run is historical; the current local changes still need a PR and CI.

## Priority next actions

1. Start the back-end and test sign-up/sign-in at `http://127.0.0.1:8080`.
2. Run the chosen happy path more than once and verify the demonstration items.
3. Run the full suite, Checkstyle, and CI for the current changes.
4. Confirm final scope, fallback plan, teammates' commits, and presentation responsibilities.
