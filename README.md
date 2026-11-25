# ReservaCampus

**English** · [Português (Brasil)](README.pt-BR.md)

ReservaCampus is a campus room-booking MVP. Its front-end is maintained separately from the Java REST back-end.

## Problem and solution

Students and campus teams often depend on chats or spreadsheets to find a room. Information can be outdated and two groups may plan to use the same space. ReservaCampus provides a room catalog and lets signed-in users create, view, edit, or cancel bookings. The back-end rejects overlapping bookings for the same room and time.

## MVP scope

- Account registration and email/password sign-in.
- Room catalog with name, capacity, and availability.
- Create, list, edit, and cancel a user's bookings.
- Prevent overlapping active bookings for a room.

Equipment booking, recurring bookings, campus-system integration, approvals, and AI-generated alternative slots are outside the current MVP.

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
├── AI_LOG.md
├── AI_LOG.pt-BR.md
├── DECISIONS.md
├── DECISIONS.pt-BR.md
├── README.md
├── README.pt-BR.md
├── DIA3_STATUS.md
└── DIA3_STATUS.pt-BR.md
```

The back-end serves files from `/frontend` during local development, so the browser and API share an origin. The source remains separated; no front-end package manager is required.

## Run locally (Windows PowerShell)

Prerequisite: Java 21. The Maven Wrapper downloads Maven on first use.

```powershell
cd backend
.\mvnw.cmd clean spring-boot:run
```

Open [http://127.0.0.1:8080](http://127.0.0.1:8080), create an account, and then manage rooms and bookings. Stop an existing app with `Ctrl+C` before starting another copy because embedded H2 locks its local database file.

The local database is stored under `backend/data/`. It is machine-specific and must not be committed.

## API

- `POST /auth/cadastro`, `POST /auth/login`, `GET /auth/me`, `POST /auth/logout`
- `GET|POST /salas`, `GET|PUT|DELETE /salas/{id}`, `GET /salas/disponiveis`
- `GET /reservas/minhas`, `POST /reservas`, `PUT /reservas/{id}`, `DELETE /reservas/{id}`

Room and booking routes require the session cookie returned by registration or login. A booking body contains `salaId`, `data` (`YYYY-MM-DD`), `horaInicio`, and `horaFim` (`HH:mm`). The requester is taken from the authenticated session, not from client input.

## Tests and CI

Run local lint and tests:

```powershell
cd backend
.\mvnw.cmd -B checkstyle:check test
```

GitHub Actions runs build, Checkstyle, and tests for pull requests and pushes to `master` and `main`. The repository's current default branch is `master`; a green remote Actions run still needs to be confirmed after these changes are pushed.

## Team and project links

- **Class:** TDS, mixed groups 6570, 6756, and 6863.
- **Members listed in the previous team README:** Giovana, Fábio, Theo, and João Raupp; confirm the roster and GitHub handles with the team.
- **Tech lead / presentation block owners:** to be confirmed by the team; see [`docs/PRESENTATION_OUTLINE.pt-BR.md`](docs/PRESENTATION_OUTLINE.pt-BR.md).
- **Instructor collaborator:** repository administrator must verify/add `adrianoanschau@gmail.com`.
- **Repository:** [giovanaganzerdeleon/ReservaEspacos](https://github.com/giovanaganzerdeleon/ReservaEspacos).
- **GitHub board:** link and task ownership need to be added by the team.
- **Deployment:** not configured.

## Remaining delivery checks

Remote access/board setup, review assignment for the current changes, Conventional Commits from every member, the instructor check-in, and a green GitHub Actions run cannot be asserted from this local worktree. Their current status and next actions are recorded in [`DIA3_STATUS.md`](DIA3_STATUS.md).
