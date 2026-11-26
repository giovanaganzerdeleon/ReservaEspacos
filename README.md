# ReservaCampus

**English** · [Português (Brasil)](README.pt-BR.md)

ReservaCampus is a campus resource-booking MVP. Its front-end is maintained separately from the Java REST back-end.

## Problem and solution

Students and campus teams often depend on chats or spreadsheets to find rooms and classroom materials. Information can be outdated and two groups may plan to use the same resource. ReservaCampus provides room and item catalogs and lets signed-in users create, view, edit, or cancel bookings. Room bookings cannot overlap; item bookings also enforce the available stock quantity for each time window.

## MVP scope

- Account registration and email/password sign-in.
- Room catalog with name, capacity, and availability.
- Campus item catalog with category and stock quantity; users can add classroom materials and equipment.
- Create, list, edit, and cancel a user's room and item bookings.
- Prevent overlapping room bookings and prevent reservations from exceeding item stock.

Recurring bookings, campus-system integration, approvals, and AI-generated alternative slots are outside the current MVP. The initial item quantities are demonstration data and must be adjusted to match the real campus inventory.

## Stack

- **Front-end:** HTML, CSS, and vanilla JavaScript in `/frontend`; JSON requests to the REST API.
- **Back-end:** Java 21, Spring Boot 4.1.2, Spring MVC, Spring Data JPA, and Hibernate.
- **Authentication:** BCrypt password hashing and server-side HTTP sessions.
- **Database:** H2 file database for local development.
- **Build and CI:** Maven Wrapper, Checkstyle, JUnit, and GitHub Actions.

## Repository structure

```text
/
├── .github/workflows/ci.yml
├── backend/                  # Spring Boot application and automated tests
├── docs/                     # Briefing and presentation/check-in outlines
├── frontend/                 # Standalone HTML, CSS, and JavaScript source
├── iniciar.bat               # Windows double-click launcher for the back-end
├── AI_LOG.md
├── AI_LOG.pt-BR.md
├── DECISIONS.md
├── DECISIONS.pt-BR.md
├── README.md
├── README.pt-BR.md
├── DIA3_STATUS.md
├── DIA3_STATUS.pt-BR.md
├── DIA4_STATUS.md
└── DIA4_STATUS.pt-BR.md
```

The back-end serves files from `/frontend` during local development, so the browser and API share an origin. The source remains separated; no front-end package manager is required.

> **Important:** opening `frontend/index.html` directly or viewing a GitHub branch does not start the API. Sign-up and sign-in require the Spring back-end to be running; each person running the app locally must start their own back-end. The application is not hosted for public access yet.

## Run locally (Windows)

Prerequisite: Java 21. The Maven Wrapper downloads Maven on first use.

1. From the project root, double-click `iniciar.bat`.
2. Keep the terminal window open while using the app.
3. Wait for `ReservaCampus esta pronto` to appear in the terminal.
4. Only then open [http://127.0.0.1:8080](http://127.0.0.1:8080). If the message does not appear, the app has not started; check the terminal error.

If startup fails, the window stays open so you can read the error. To start manually from PowerShell:

```powershell
cd C:\path\to\ReservaEspacos\backend
.\mvnw.cmd spring-boot:run
```

Create an account and use the **Salas** and **Itens do campus** tabs to manage bookings. If the item table is empty, the back-end seeds demonstration entries for a projector, notebooks, lab kits, a mobile whiteboard, and marker kits. Stop an existing app with `Ctrl+C` before starting another copy because embedded H2 locks its local database file.

The local database is stored under `backend/data/`. It is machine-specific and must not be committed.

## API

- `POST /auth/cadastro`, `POST /auth/login`, `GET /auth/me`, `POST /auth/logout`
- `GET|POST /salas`, `GET|PUT|DELETE /salas/{id}`, `GET /salas/disponiveis`
- `GET /reservas/minhas`, `POST /reservas`, `PUT /reservas/{id}`, `DELETE /reservas/{id}`
- `GET|POST /itens`, `GET /itens/disponiveis?data=YYYY-MM-DD&horaInicio=HH:mm&horaFim=HH:mm`
- `GET /reservas-itens/minhas`, `POST /reservas-itens`, `PUT /reservas-itens/{id}`, `DELETE /reservas-itens/{id}`

Room, item, and booking routes require the session cookie returned by registration or login. A room booking body contains `salaId`, `data` (`YYYY-MM-DD`), `horaInicio`, and `horaFim` (`HH:mm`). An item booking body contains `itemId`, `quantidade`, `data`, `horaInicio`, and `horaFim`. The requester is taken from the authenticated session, not from client input.

## Tests and CI

Run local lint and tests:

```powershell
cd backend
.\mvnw.cmd -B checkstyle:check test
```

GitHub Actions runs build, Checkstyle, and tests for pull requests and pushes to `master` and `main`. PR #4's run passed all three steps. The `main` branch is protected and work is delivered through Pull Requests; confirmation of the `master`/`main` push run after the final merge remains.

## Team and project links

- **Class:** TDS, mixed groups 6570, 6756, and 6863.
- **Members listed in the previous team README:** Giovana, Fábio, Theo, and João Raupp; confirm the roster and GitHub handles with the team.
- **Tech lead / presentation block owners:** to be confirmed by the team; see [`docs/PRESENTATION_OUTLINE.pt-BR.md`](docs/PRESENTATION_OUTLINE.pt-BR.md).
- **Instructor collaborator:** `adrianoanschau@gmail.com` has been added to the repository.
- **Repository:** [giovanaganzerdeleon/ReservaEspacos](https://github.com/giovanaganzerdeleon/ReservaEspacos).
- **GitHub board:** link and task ownership need to be added by the team.
- **Deployment:** not configured.

## Remaining delivery checks

The GitHub board, task ownership, Conventional Commits from every member, live application verification, presentation rehearsal, and the post-merge pipeline run still need confirmation. The instructor has been added to the repository and PR #4's Actions run passed. Current Day 4 status and next actions are recorded in [`DIA4_STATUS.md`](DIA4_STATUS.md).
