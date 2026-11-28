# Day 5 Checklist — 27 November 2026

## Goal

Finish and validate the flow to be presented, keep the main branch stable, and prepare the team for the code freeze on Sunday, **29 November at 23:59**. Use the weekend for small fixes and rehearsal; do not start new features.

Check an item only after it has been completed and verified. Record an owner and evidence where needed.

## 1. Close outstanding work and the demo path

- [ ] Run the selected end-to-end flow and repeat it to confirm that it is reproducible.
- [ ] Confirm which features are in the presentation and which are out; record the decision in `DECISIONS.md`.
- [ ] Confirm that sample rooms and items are available on the presentation computer.
- [ ] Prepare a demo account on that computer; do not use a personal account or password.
- [ ] If the presentation uses a hosted version, test its URL on the presentation computer and confirm access to the demo database.
- [ ] Define and rehearse a fallback for network, server, or live-demo failures.

## 2. Keep the main branch stable

- [ ] Merge only demo-critical fixes through small, reviewed pull requests.
- [ ] Avoid new features and risky refactors.
- [ ] Use Conventional Commits, for example: `feat(bookings): validate availability`.
- [ ] Confirm each teammate's contribution without creating artificial commits or committing on someone else's behalf.
- [ ] Set and communicate an internal final-merge deadline earlier than the official freeze.

## 3. Tests and pipeline

- [ ] Run a clean back-end build, Checkstyle, and tests.
- [ ] Stop the local application before running `clean`. From `backend/`, run `.\mvnw.cmd -B clean checkstyle:check test`.
- [ ] Confirm a green GitHub Actions run for the changes being delivered.
- [ ] Confirm that CI runs on pull requests and pushes to the main branch.
- [ ] Do not merge changes while checks are failing; fix the cause before merging.

## 4. Documentation and security

- [ ] Check that `README.md` and `README.pt-BR.md` explain setup, how to run the app, the stack, and repository structure.
- [ ] Review scope decisions and clearly record what was deferred and why.
- [ ] Update AI logs with the tool, prompt or faithful summary, work used, and team review.
- [ ] Review files and Git history for secrets. If a credential was exposed, revoke and replace it; deleting the current file alone does not remove a secret from history.

## 5. Rehearsal and instructor check-in

- [ ] Finalize a 15-minute outline: problem, demo, workflow, decisions, AI use, and pipeline.
- [ ] Divide speaking and demo responsibilities across the team.
- [ ] Hold a timed rehearsal with the whole team.
- [ ] Test the demo in the actual presentation environment and prepare the fallback.
- [ ] Use the 5-minute check-in to show the working flow and align on owners, deadlines, and the final-merge time.

## Team roles

- **Presentation lead:** Theo.
- **Tech lead:** Giovana.
- **Live demo and AI-use log:** Fábio.
- **Presentation team:** Theo, Fábio, and Giovana.

## Progress tracking

| Item or blocker | Owner | Internal deadline | Evidence / notes |
|---|---|---|---|
|  |  |  |  |

The detailed Day 4 status is kept as a historical record in [`DIA4_STATUS.md`](DIA4_STATUS.md). Verify the tasks above before treating any open item as complete.
