# Hosting a demo deployment

**English** · [Português (Brasil)](DEPLOYMENT.pt-BR.md)

## Deploy to Railway (prepared path)

The presentation computer only needs a browser and internet access. Java, Maven, and the application server run on Railway infrastructure. The computer does not need Java, Maven, PostgreSQL, VS Code, or Docker.

The repository includes a root-level `Dockerfile`. Railway uses it to build the project with Java 21 and start the JAR; `.dockerignore` prevents the local H2 database and environment files from being sent in the build context.

1. Push the project changes to GitHub.
2. In Railway, create a project and choose **Deploy from GitHub repo**. Connect the `ReservaEspacos` repository. Keep the service root directory at the repository root: the `Dockerfile` needs both `backend/` and `frontend/`.
3. Add a PostgreSQL service to the same project using **New → Database → PostgreSQL**.
4. In the application service, open **Variables** and add:

   ```text
   SPRING_PROFILES_ACTIVE=prod
   SPRING_DATASOURCE_URL=jdbc:postgresql://${{Postgres.PGHOST}}:${{Postgres.PGPORT}}/${{Postgres.PGDATABASE}}
   SPRING_DATASOURCE_USERNAME=${{Postgres.PGUSER}}
   SPRING_DATASOURCE_PASSWORD=${{Postgres.PGPASSWORD}}
   ```

   If the database service has a different name, replace `Postgres` with the name shown in Railway. Use the dashboard's variable references/autocomplete; do not copy passwords into the repository. Connect the services using the PostgreSQL private variables and do not enable public database access. Railway provides `PORT` to the application.
5. Wait for the build and deployment. Railway detects the root `Dockerfile`; separate build and start commands are not needed.
6. In the application service, go to **Settings → Networking → Generate Domain** to create a public HTTPS URL.
7. Open the URL and test sign-up, sign-in, bookings, editing, and cancellation. The seed creates sample rooms and items when their tables are empty, but it does not create a user; register a demo account.

The PostgreSQL service is separate and persistent. The presentation computer accesses only the app URL; it does not need to run a database or expose a local port.

**Cost:** check [Railway plans and pricing](https://railway.com/pricing) before creating services. Credits and limits can change; hosting PostgreSQL may require a paid plan. On plans that suspend idle services, the first demo request may take longer.

Official docs: [Spring Boot on Railway](https://railway.com/guides/spring-boot), [Railway PostgreSQL](https://docs.railway.com/databases/postgresql), and [variable references](https://docs.railway.com/variables).

## Other Java hosts

If you choose another provider, it must offer Java 21 and persistent PostgreSQL. Set `SPRING_PROFILES_ACTIVE=prod`, the three `SPRING_DATASOURCE_*` variables with the database JDBC details, and the `PORT` supplied by the host. The JAR generated in `backend/target/` includes the files from `frontend/`; use this start command:

```sh
java -jar backend/target/reservacampus-backend-1.0-SNAPSHOT.jar
```

## After deployment

- Test the URL on the computer that will be used for the presentation.
- Create a demo account and exercise sign-in, booking, editing, and cancellation.
- Confirm that sample rooms and items loaded. Seeding runs only when the corresponding tables are empty and does not create an account.
- Use fictional data only for the demo.
- Check startup logs and database status in the provider dashboard.

## Important limitations

The hosting profile is a starting point for an MVP demo and validation, not a production-readiness certification. The database starts with `ddl-auto=update`; before using real data, adopt reviewed schema migrations and backups. The H2 console is disabled in this profile, and database credentials must be provided by the host.

Before opening the system for real use, review CSRF protection, login/registration rate limiting, administrative permissions, and data-handling policy. Currently, any authenticated user can manage room records and add items; there is no separate administrator role.

The project is not deployed yet. An HTTPS address will exist only after someone connects the repository, configures the services, and completes deployment on Railway.
