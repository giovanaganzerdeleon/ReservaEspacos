# Day 3 checklist status — 25 November 2026

> **Historical snapshot:** this document records status on 25 November. See [`DIA5_STATUS.md`](DIA5_STATUS.md) for current priorities.

This status distinguishes local work from actions that need the team or GitHub. A checked box means verified locally or against GitHub; it does not mean merely planned.

## 1. Days 1–2 follow-up

- [ ] Confirm the IT lead is a repository collaborator.
- [x] Instructor `adrianoanschau@gmail.com` has been added as a repository collaborator.
- [x] Confirm that project work was submitted through Pull Requests; a reviewed PR is part of the team workflow.
- [x] The `main` branch is protected and work requires a Pull Request.
- [ ] Update the GitHub board and assign each task to a teammate; add the board link to the README.
- [x] Document the MVP and stack in both root READMEs.
- [x] Separate source into `/frontend` and `/backend`; keep supporting material in `/docs`.
- [x] Add a GitHub Actions workflow for build, Checkstyle, and tests on pull requests and pushes to `master` and `main`.

## 2. MVP functionality

- [x] Implement the core user flow: sign up/sign in, manage rooms, and create/list/edit/cancel one's own bookings.
- [x] Reject overlapping active bookings for a room; allow adjacent bookings.
- [x] Add an **Items** page for campus equipment/classroom materials; reserve, list, edit, or cancel a quantity from stock for a date/time range.
- [x] Reject item reservations that would make overlapping bookings exceed stock; seed clearly labeled demonstration inventory for projectors, notebooks, lab kits, a whiteboard, and marker kits.
- [ ] Exercise the complete booking flow against a running application. The Java debug launch tool returned a Maven build failure without exposing the underlying cause; no live booking HTTP check was completed in this pass.
- [ ] Verify at least one Conventional Commit from every team member today; do not create commits on behalf of teammates.

## 3. Tests

- [x] Back-end service and repository tests run locally: 15 tests passed, 0 failures.
- [x] Checkstyle run locally: 0 violations.
- [ ] Add/confirm an automated front-end test. There is no front-end test runner in the current repository.
- [ ] Add/confirm an HTTP/integration test for the end-to-end booking lifecycle; current automated coverage is service-level.

## 4. Pipeline

- [x] Workflow includes build, lint, and tests for pull requests and `master`/`main` pushes.
- [x] Push the workflow and changes in a PR and confirm a green GitHub Actions run: PR #4's `build-lint-test` job passed build, Checkstyle, and tests.
- [ ] After merging PR #4, confirm the push run on `master`/`main` as well.

## 5. Documentation and presentation

- [x] Root README, decisions, and AI-log files are present in English and Brazilian Portuguese.
- [x] Document the actual stack, folder structure, local run instructions, MVP scope, and API routes.
- [x] Replace obsolete/example documentation with the current implementation and a bilingual Day 3 status.
- [x] Draft a 15-minute presentation and 5-minute instructor check-in outline in `/docs`.
- [ ] Confirm who presents each block and rehearse the live demo nearer to the 29 November code freeze.

## 6. Security

- [x] `.gitignore` excludes `.env` files and the new local H2 database path.
- [x] Scan the 10 most recent commits and current non-binary worktree files for common private-key, AWS, GitHub-token, and credential-assignment patterns; no matches. This is a targeted scan, not a guarantee against every secret format. Rotate any known exposed credential.
- [x] Keep the local H2 copy under ignored `backend/data/`; do not add it to Git. The old tracked path is removed from the current branch tree, but the file remains in earlier remote history and may need a coordinated history/credential review if it contained sensitive data.

## 7–8. Team presentation and instructor check-in

- [x] Prepare outlines for the presentation and 5-minute check-in.
- [ ] Assign speakers and agree on the live demo flow.
- [ ] Show the working central feature and pipeline status to the instructor; report completed work, next steps, and blockers.

## Local verification performed

From `backend/`, `.\mvnw.cmd -B checkstyle:check test` completed successfully: 15 tests passed and Checkstyle reported 0 violations. The item-booking changes are committed and pushed to `feat/day3-mvp-creating-items-registers`; PR #4 is open and awaiting review. Its GitHub Actions job passed. The live HTTP/browser flow and the post-merge `master` run still need confirmation.

## Next actions for the team

1. Get PR #4 reviewed and merged; confirm the Actions run on the push to `master`.
2. Complete the live room-and-item booking demo; add/run an HTTP integration test if time allows.
3. Confirm instructor/IT access, assign board tasks, and define presentation owners.
4. Verify each teammate's Conventional Commit and review history for secrets; keep the local H2 database out of Git.
