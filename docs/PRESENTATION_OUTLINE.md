# Presentation Script — ReservaCampus

**English** · [Português (Brasil)](PRESENTATION_OUTLINE.pt-BR.md)

A **15-minute presentation**, followed by **5 minutes for questions**. This script describes the current code; adapt the statements to the tests and live run that the team has actually verified.

## Roles

- **Theo — presentation lead:** guides the narrative, keeps time, and handles transitions.
- **Giovana — tech lead:** explains the architecture, back-end rules, and tests.
- **Fábio:** leads the live demo, operating the interface and walking through the user flow.

This is the suggested division: Theo presents and connects the sections; Fábio operates the UI; Giovana adds the technical explanations. Rehearse the transitions between all three.

## Before presenting

- If deployment is complete, open the host's HTTPS URL on the presentation computer; Java and Maven are not needed on that computer.
- If deployment is not ready, the local fallback requires Java 21 on the computer running the back end and uses `http://127.0.0.1:8080`.
- Create and test a demo account on that computer. The seed does not create an account or default password.
- Confirm that the local database contains the sample rooms and items. Each seed runs only when its respective table is empty; existing data is not replaced.
- Choose a future date and confirm item availability for the time window. The sample inventory includes 10 marker kits.
- Rehearse editing and cancellation. Do not describe a step as demonstrated live if the UI flow has not been verified.
- Check the latest GitHub Actions run and the commit it covers. Do not present an older run as validation for current changes.

## 15-minute presentation

| Time | Block | Suggested lead |
|---|---|---|
| 0:00–1:30 | Problem and proposal | Theo introduces the problem and ReservaCampus. |
| 1:30–3:00 | Product and architecture | Giovana explains the UI, API, authentication, and local database. |
| 3:00–8:00 | Live demo | Fábio operates the app; Theo narrates the flow; Giovana explains the technical rules. |
| 8:00–9:30 | Tests and pipeline | Giovana shows verifiable test and CI results. |
| 9:30–11:00 | Decisions and scope | Theo explains the MVP boundary and deferred work. |
| 11:00–13:00 | AI use | Fábio presents the team's shared AI-use log. |
| 13:00–15:00 | Wrap-up | Theo summarizes the value, current status, and next steps. |

### 0:00–1:30 — Opening: problem and proposal

**Suggested script — Theo**

> Good afternoon. We are the ReservaCampus team. Our project addresses a simple problem: finding available rooms and supplies on campus can depend on chats or spreadsheets, which makes scheduling conflicts easy to miss.
>
> We built an MVP to browse rooms and items, make bookings, and manage each user's own reservations. We focused on a clear, demonstrable flow and conflict prevention rather than trying to build a complete campus-management system.

### 1:30–3:00 — Product and architecture

**Suggested script — Giovana**

> The interface uses framework-free HTML, CSS, and JavaScript in `frontend/`. The back end uses Java 21 and Spring Boot and exposes a REST API. During local development, Spring also serves the interface, so the browser and API share an origin.
>
> Registration and sign-in create a server-side session. Passwords are stored as BCrypt hashes. Local development uses H2. For a hosted demo, we prepared a profile that uses PostgreSQL; in that case, people access the same application and database through the host URL.

**Transition to the demo — Theo**

> Now we will show the main flow running in the application.

### 3:00–8:00 — Live demo

**1. Sign in — Fábio operates; Theo narrates**

> We will sign in with the demo account. After authentication, the app loads the rooms and campus-items areas.

**2. Show the catalog — Fábio**

Briefly show the room list and the **Itens do campus** tab. Point out that the sample data is illustrative, not an official inventory.

**3. Book items — Fábio operates; Theo narrates; Giovana explains the rule**

> We will choose a future date and a time window. The app checks availability for that period, not just the total inventory.

Select **Kit de canetões (4 cores)**, choose 09:00–10:00, and book 6 units. Show the booking under **Minhas reservas de itens**. Since the form resets after saving, enter the same date and time again to check the updated availability for that period.

> The sample inventory has 10 units. With 6 booked for this period, the screen shows 4 available. The quantity offered by the form follows the availability shown for the selected period.

**4. Edit and cancel — Fábio operates; Theo narrates**

Edit the booking, reduce the quantity to 5, and save. Show the updated booking in the list. Then cancel it and show that its status changes to cancelled.

> The booking belongs to the authenticated account. The back end checks ownership before allowing changes, and cancelled bookings remain recorded with their cancelled status.

**Important:** the UI limits the quantity to the displayed stock. Do not try to force an over-stock quantity to simulate an error. Explain the rule and, if needed, show the relevant automated test rather than claiming the UI made that attempt.

**If the room flow has also been rehearsed:** use any remaining time to create or view a room booking and show **Minhas reservas**. Skip this step if it is not stable.

### 8:00–9:30 — Tests and pipeline

**Suggested script — Giovana**

> The back end has automated tests for rules including authentication, room-booking conflicts, and item-stock availability. We also use Checkstyle to keep the code consistent.
>
> This is the GitHub Actions run for commit [identify the commit on screen]. It runs the build, Checkstyle, and tests for pull requests and pushes to `main` and `master`.

Show only results that have actually run. If the run shown does not include the latest changes, say so clearly and distinguish local results from remote CI.

### 9:30–11:00 — Decisions and MVP boundaries

**Suggested script — Theo**

> We prioritized a small MVP that could be demonstrated: a catalog, authentication, and room and item bookings. The system prevents overlapping room bookings. For items, it calculates available stock based on bookings in the same time window.
>
> Recurring bookings, approval workflows, integration with the official inventory, and AI-generated time suggestions are outside this MVP. Sample quantities are for demonstration and must be replaced with verified data before operational use.

### 11:00–13:00 — AI use

**Suggested script — Fábio**

> We used AI tools to support development and documentation. The team reviewed the suggestions, compared changes with the code, and corrected or rejected work that did not meet the project requirements.
>
> AI is not an integrated feature of the current product. Automatic time suggestions were left out of the MVP. Our AI usage and validation are documented in `AI_LOG.md` and `AI_LOG.pt-BR.md`.

Show a concrete entry from the AI log and briefly explain what the team accepted and reviewed. Fábio presents the team's shared process; do not attribute individual contributions to him unless confirmed.

### 13:00–15:00 — Wrap-up

**Suggested script — Theo**

> The main result is a booking flow that lets people browse resources and manage their own reservations, with rules to prevent room conflicts and item bookings from exceeding available stock.
>
> This is still an MVP without official inventory integration. Our next steps are to finish validating the flow, check CI for the delivered commit, and keep the scope stable.
>
> Thank you. We are ready for questions.

**Adjust this information to the status on presentation day:** if hosted, say it is available at the URL and uses demo data; otherwise, say that a public URL is still outstanding.

## 5-minute instructor check-in

| Time | What to show |
|---|---|
| 0:00–0:30 | Theo summarizes the problem, proposal, and MVP boundary. |
| 0:30–3:00 | Fábio operates sign-in and item booking; Giovana explains the technical rules. Include rooms only if the flow is verified. |
| 3:00–4:00 | Show local test results and the actual GitHub Actions status, identifying the commit. |
| 4:00–5:00 | Theo summarizes open items, the plan to code freeze, and asks for feedback. |

**Throughout the presentation:** report only what has been verified. If something has not been tested live or by the current CI run, identify it as outstanding.
