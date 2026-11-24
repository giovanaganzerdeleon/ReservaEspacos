# Theme briefing: Space and equipment booking

**English** · [Português (Brasil)](BRIEFING.pt-BR.md)

**Hack with AI: Coding with Intelligence**  
Senac na Prática · Technical Program in Systems Development  
Area: campus life and community  
Immersion from 23 to 29 November · presentations on 30 November and 1 December

This briefing is the starting point for **your** theme. Read it carefully, cut an MVP that fits in one week, and record your decisions. You will turn this problem into a real product, with a front end and a back end, an organized repository, and documented use of AI. This is a collaborative immersion: the panel evaluates what the team built, decided, and learned.

## Theme and context

**Space and equipment booking** — A system to book rooms, labs, and equipment, with conflict control.

Rooms, labs, and equipment on campus are often claimed face to face, in a class group chat, or in a spreadsheet that nobody updates. The classic result is a conflict: two teams in the same lab, a projector that was “booked” but already taken, or a space that looks free and is not. What is missing is visibility of what is occupied and a straightforward way to request, confirm, and cancel, respecting capacity and time.

You will build a booking flow for campus life, in which a conflict is prevented — or resolved with an alternative — before the team shows up at the door.

## Audience and personas

- **A student or team that needs a space or a piece of equipment:** wants to see availability and complete a request in a few steps.
- **Someone who grants or follows bookings (a staff or instructor role in the MVP):** needs a clear calendar and, if your scope includes it, a way to decline or adjust a request.
- **Someone who arrives on time:** depends on the booking actually holding, without overlap.

## Goal

Ship an MVP in which people can see the calendar for rooms, labs, and equipment, create a timed booking, prevent overlapping conflicts, and cancel or change a request — with AI helping to find alternative slots.

## Minimum requirements (MVP)

The scope below is realistic for one week. Lock it on the first day and record in `DECISIONS.md` what is in, what is out, and why. A complete, demonstrable flow is better than a long list left half-done.

1. A simple catalog of resources (room, lab, or equipment), with a name, a type, and, when it makes sense, a capacity.
2. A day view of availability (a calendar or a list of time slots).
3. Create a booking with a resource, date, start, end, and who is requesting it.
4. Detect and prevent overlap on the same resource and time (the heart of the MVP).
5. Cancel or edit a booking, recalculating conflicts.
6. List “my bookings” and the status of each one.

## Where AI comes in

The use of AI is allowed and encouraged — in the product and in the process — and it must appear in `AI_LOG.md`. Some concrete starting points for this theme:

- Suggest alternative times when the first request conflicts.
- Parse a natural-language request (“the lab tomorrow from 2 p.m. to 4 p.m.”) and build the booking for the person to confirm.
- Help choose an equivalent resource (another room with similar capacity) if the preferred one is taken.
- Summarize a lab's day calendar in a few lines.

Do not publish, without your review, an AI-generated text or automatic decision as if it were already the final version.

## Optional extras

Only after the MVP is standing. These are ideas, not obligations:

- An approval step for certain resources.
- Recurring bookings (every Thursday, at the same time).
- A required buffer between two bookings.
- Linking a piece of equipment to a room (booking both together).

## Out of scope

To protect the deadline and the focus of the product:

- Integration with Senac's official academic system.
- Locks, badges, or physical access control for rooms.
- Charging or invoicing for the use of a space.
- A full timetable of every class in the institution — the scope is ad hoc booking of campus resources.

## Questions to guide the team

Use these questions on the first day, when you define the MVP, and again before the demo:

- Who can book what in the MVP? Is everything open, or do some resources require a specific role?
- What happens if two requests arrive at almost the same time for the same slot?
- Is there a minimum gap between the end of one booking and the start of the next?
- How does the interface show why a time is unavailable?
- Can a booking run past campus opening hours? If not, where is that validated?

## Deliverables and how the panel evaluates

This is a one-week **collaborative immersion**, with teams of 3 or 4 TDS students. The panel evaluates **your** work, on your own project — formative feedback, not a comparison with other teams.

**What must be in the repository** (at the root), in **English** (the main version) and in **Portuguese** (`.pt-BR`), with a language link at the top of each file:

- `README.md` and `README.pt-BR.md`
- `DECISIONS.md` and `DECISIONS.pt-BR.md`
- `AI_LOG.md` and `AI_LOG.pt-BR.md`

The application must have a **front end and a back end**. The back end may be **Java**, **Python**, or **Node.js** (alone or combined, as long as they are integrated). A front end is required (web, desktop, or mobile). Justify the stack in `DECISIONS.md`. Also required: a Conventional Commits history, work through reviewed Pull Requests, a protected `main` branch, and a green **GitHub Actions** pipeline (build, lint, and tests) on PRs and on `main`. Do not commit secrets. On the first day, add the instructor as a collaborator: **adrianoanschau@gmail.com**.

**Code freeze** is on Sunday, **29 November, at 23:59**. Presentations are **15 minutes + 5 minutes of questions**, on 30 November and 1 December, at Senac Canoas, Room 103. Rehearse the demo and the required outline (problem and solution, live demo, workflow, decisions, AI in practice, and CI/CD).

Evaluation is split equally between **two panels**:

- **Technical panel (50%):** code quality and architecture; Git, Conventional Commits, and PRs; CI/CD; command of the code in the Q&A.
- **Innovation and Impact panel (50%):** originality; impact (the real problem and for whom); user experience; clarity of the pitch; creative use of AI.

Anyone on the team may be asked about any part of the project. Use AI thoughtfully, record what it generated and what you corrected, and own the code you present.

Good work — and may the week be intense, collaborative, and well documented.

