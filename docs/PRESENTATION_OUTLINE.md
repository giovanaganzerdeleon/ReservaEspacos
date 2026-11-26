# 15-minute presentation and 5-minute check-in

**English** · [Português (Brasil)](PRESENTATION_OUTLINE.pt-BR.md)

Draft only. The team must assign speakers and rehearse; no presenter names are assumed here.

## 15-minute presentation

| Time | Block | Suggested content | Presenter |
|---|---|---|---|
| 0:00–1:30 | Problem and audience | Booking conflicts on campus; who needs the tool and what the MVP solves. | To be assigned |
| 1:30–3:00 | Product and architecture | Separate `/frontend` and `/backend`; explain the browser-to-REST flow and local H2. | To be assigned |
| 3:00–8:00 | Live demo | Sign in; show rooms and the campus-items tab; reserve marker kits or a projector; attempt to reserve more than the remaining stock and show the rejection; edit, list, and cancel. | To be assigned |
| 8:00–9:30 | Workflow | Show a reviewed Pull Request, Conventional Commit history, and the Actions workflow/run. Do not claim a green run until one exists. | To be assigned |
| 9:30–11:00 | Decisions | Explain Java/Spring, separated source directories, sessions, and why recurrence/equipment/approvals are out of MVP scope. | To be assigned |
| 11:00–13:00 | AI use | Show the AI log; describe generated work and the changes the team reviewed or rejected. | To be assigned |
| 13:00–15:00 | Status and next steps | Demonstrate local tests, state actual remote pipeline status, mention remaining items and the code-freeze plan. | To be assigned |

### Demo preparation

- Create a demo account before presenting; do not use a real user's password or expose the local H2 file.
- Use a future date and a clear time window, for example 09:00–10:00; reserve a quantity of items, then attempt an overlapping reservation that exceeds the remaining stock.
- Explain that the initial item quantities are demonstration data and must be replaced with a verified campus inventory before real use.
- Confirm edit, list, and cancellation behavior before the live demo. If live booking verification is not complete, use a clearly labeled local test result instead of claiming the UI flow was verified.
- Check that only one local app instance is using the H2 file and that the browser is pointed at `http://127.0.0.1:8080`.

## 5-minute instructor check-in

| Time | Content |
|---|---|
| 0:00–0:30 | State the campus room-booking problem and the MVP boundary. |
| 0:30–3:00 | Show sign-in and the room or stock-limited item booking/conflict flow, if live verification has passed. |
| 3:00–4:00 | Show local test/Checkstyle results and the actual GitHub Actions status. |
| 4:00–5:00 | Summarize completed work, next steps, and blockers; ask for feedback. |

**Before the check-in:** confirm who will present, push the workflow, and report remote CI status accurately. If Actions has not run yet, say so and show only verified local results.
