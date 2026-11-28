# ReservaCampus

**English** · [Português (Brasil)](README.pt-BR.md)

ReservaCampus is an MVP for booking campus rooms and items. It includes a web interface and a Java REST API.

## Problem and solution

Students and campus teams may depend on chats or spreadsheets to find rooms and classroom materials. This makes availability difficult to track and conflicts easy to miss. ReservaCampus provides room and item catalogs and lets signed-in users create, view, edit, or cancel bookings. The system prevents overlapping room bookings and limits item bookings to available stock for the requested period.

## MVP scope

- Account registration and email/password sign-in.
- Room catalog with name, capacity, and availability.
- Campus item catalog with category and stock quantity; users can add classroom materials and equipment.
- Create, list, edit, and cancel a user's room and item bookings.
- Prevent overlapping room bookings and prevent reservations from exceeding item stock.

Recurring bookings, integration with official campus systems, approvals, and AI-generated time suggestions are outside the MVP. Initial item quantities are illustrative and do not represent the official inventory.

## Stack

- **Front-end:** HTML, CSS, and vanilla JavaScript in `/frontend`; JSON requests to the REST API.
- **Back-end:** Java 21, Spring Boot 4.1.2, Spring MVC, Spring Data JPA, and Hibernate.
- **Authentication:** BCrypt password hashing and server-side HTTP sessions.
- **Database:** H2 file database for local development; each computer has its own database.
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
├── DIA4_STATUS.pt-BR.md
├── DIA5_STATUS.md
└── DIA5_STATUS.pt-BR.md
```

During local development, the back end serves files from `/frontend`, so the browser and API share an origin. The front end does not require a package manager.

> **Important:** opening `frontend/index.html` directly or viewing a GitHub branch does not start the API. Start the back end on the computer that will run the application. The project is not publicly hosted.

## Run locally (Windows)

**Prerequisite:** Java 21. The Maven Wrapper downloads Maven on first use.

1. From the project root, double-click `iniciar.bat`.
2. Keep the terminal window open while using the app.
3. Wait for `ReservaCampus esta pronto` to appear in the terminal.
4. Only then open [http://127.0.0.1:8080](http://127.0.0.1:8080). If the message does not appear, the app has not started; check the terminal error.

If startup fails, the window stays open so you can read the error. To start manually from PowerShell:

```powershell
cd C:\path\to\ReservaEspacos\backend
.\mvnw.cmd spring-boot:run
```

Create an account through the sign-up form, then use the **Salas** and **Itens do campus** tabs. When the respective tables are empty, the application seeds four sample rooms (Sala 101, Sala 102, Laboratório de Informática, and Sala multimídia) and five types of items (projectors, notebooks, lab kits, a mobile whiteboard, and marker kits). Quantities are illustrative and do not represent the official inventory. The seed does not create an account or default password.

The H2 database is created under `backend/data/` on each computer. Each local installation has its own users, bookings, and data; they are not synchronized between computers. Do not commit this database file. Sharing data across computers requires configuring a shared database.

If an application instance is already using the local database, stop it with `Ctrl+C` before starting another.

## API

- `POST /auth/cadastro`, `POST /auth/login`, `GET /auth/me`, `POST /auth/logout`
- `GET|POST /salas`, `GET|PUT|DELETE /salas/{id}`, `GET /salas/disponiveis`
- `GET /reservas/minhas`, `POST /reservas`, `PUT /reservas/{id}`, `DELETE /reservas/{id}`
- `GET|POST /itens`, `GET /itens/disponiveis?data=YYYY-MM-DD&horaInicio=HH:mm&horaFim=HH:mm`
- `GET /reservas-itens/minhas`, `POST /reservas-itens`, `PUT /reservas-itens/{id}`, `DELETE /reservas-itens/{id}`

Room, item, and booking routes require the session cookie returned by registration or login. A room booking body contains `salaId`, `data` (`YYYY-MM-DD`), `horaInicio`, and `horaFim` (`HH:mm`). An item booking body contains `itemId`, `quantidade`, `data`, `horaInicio`, and `horaFim`. The requester is taken from the authenticated session, not from client input.

## Tests and CI

Run Checkstyle and the back-end tests:

```powershell
cd backend
.\mvnw.cmd -B checkstyle:check test
```

GitHub Actions runs the build, Checkstyle, and tests for pull requests and pushes to `master` and `main`. Check the **Actions** tab for the latest result. The expected contribution flow is to open a pull request, get it reviewed, and wait for checks before merging.

## Team and project links

- **Class:** TDS, mixed groups 6570, 6756, and 6863.
- **Tech lead:** Giovana.
- **Presentation lead:** Theo.
- **Presentation outline:** see [`docs/PRESENTATION_OUTLINE.md`](docs/PRESENTATION_OUTLINE.md).
- **Repository:** [giovanaganzerdeleon/ReservaEspacos](https://github.com/giovanaganzerdeleon/ReservaEspacos).
- **Public hosting:** not configured.

## Project documentation

- [Project decisions](DECISIONS.md)
- [AI usage log](AI_LOG.md)
- [Day 5 checklist](DIA5_STATUS.md)
- [Presentation outline](docs/PRESENTATION_OUTLINE.md)
