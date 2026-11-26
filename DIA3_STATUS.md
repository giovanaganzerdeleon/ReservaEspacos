# Day 3 checklist status — 25 November 2026

This status distinguishes local work from actions that need the team or GitHub. A checked box means verified locally or against GitHub; it does not mean merely planned.

## 1. Days 1–2 follow-up

- [ ] Confirm the IT lead and instructor are repository collaborators; the instructor address in the briefing is `adrianoanschau@gmail.com`.
- [ ] Confirm the project-creation Pull Request had a colleague review. Existing merged PRs do not establish that the current uncommitted changes were reviewed.
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
- [ ] Push the workflow and changes, then confirm the latest GitHub Actions run is green. GitHub reported **0 workflow runs** when checked for this status update.

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

From `backend/`, `.\mvnw.cmd -B checkstyle:check test` completed successfully: 15 tests passed and Checkstyle reported 0 violations. The item-booking changes are not yet committed or pushed; the flow still needs a live HTTP/browser check. The GitHub Actions check above is based on the live repository query; no run was returned.

## Next actions for the team

1. Complete the live room-and-item booking demo and add/execute an HTTP integration test.
2. Push via a reviewed Pull Request, then check the Actions result and branch protection.
3. Assign board tasks and presentation owners; confirm instructor/IT collaborator access.
4. Review the recent history for secrets and confirm that the local H2 file stays out of the commit.
