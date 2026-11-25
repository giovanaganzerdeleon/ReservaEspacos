# ReservaCampus

[English](README.md) · **Português (Brasil)**

ReservaCampus é um MVP para reservar salas do campus. O código do front-end é mantido separado do back-end Java com API REST.

## Problema e solução

Estudantes e equipes do campus muitas vezes dependem de conversas ou planilhas para encontrar uma sala. As informações podem ficar desatualizadas e dois grupos podem planejar usar o mesmo espaço. O ReservaCampus oferece um catálogo de salas e permite que usuários autenticados criem, consultem, editem ou cancelem reservas. O back-end rejeita reservas ativas sobrepostas para a mesma sala.

## Escopo do MVP

- Cadastro de conta e login com e-mail/senha.
- Catálogo de salas com nome, capacidade e disponibilidade.
- Criar, listar, editar e cancelar as próprias reservas.
- Impedir sobreposição de reservas ativas para a mesma sala.

Reserva de equipamentos, recorrência, integração com sistemas do campus, aprovações e sugestões alternativas geradas por IA ficam fora do MVP atual.

## Stack

- **Front-end:** HTML, CSS e JavaScript sem framework em `/frontend`; chamadas JSON à API REST.
- **Back-end:** Java 21, Spring Boot 4.1.2, Spring MVC, Spring Data JPA e Hibernate.
- **Autenticação:** hash de senha BCrypt e sessões HTTP no servidor.
- **Banco de dados:** arquivo H2 para desenvolvimento local.
- **Build e CI:** Maven Wrapper, Checkstyle, JUnit e GitHub Actions.

## Estrutura do repositório

```text
/
├── .github/workflows/ci.yml
├── backend/                  # Aplicação Spring Boot e testes automatizados
├── docs/                     # Briefing e roteiros de apresentação/check-in
├── frontend/                 # Código independente em HTML, CSS e JavaScript
├── AI_LOG.md
├── AI_LOG.pt-BR.md
├── DECISIONS.md
├── DECISIONS.pt-BR.md
├── README.md
├── README.pt-BR.md
├── DIA3_STATUS.md
└── DIA3_STATUS.pt-BR.md
```

Durante o desenvolvimento local, o back-end serve os arquivos de `/frontend`, então navegador e API usam a mesma origem. O código-fonte continua separado e não exige gerenciador de pacotes para o front-end.

## Executar localmente (PowerShell do Windows)

Pré-requisito: Java 21. O Maven Wrapper baixa o Maven no primeiro uso.

```powershell
cd backend
.\mvnw.cmd clean spring-boot:run
```

Abra [http://127.0.0.1:8080](http://127.0.0.1:8080), crie uma conta e então gerencie salas e reservas. Pare uma instância existente com `Ctrl+C` antes de iniciar outra, pois o H2 embutido bloqueia o arquivo local do banco.

O banco local fica em `backend/data/`. É específico da máquina e não deve ser enviado ao repositório.

## API

- `POST /auth/cadastro`, `POST /auth/login`, `GET /auth/me`, `POST /auth/logout`
- `GET|POST /salas`, `GET|PUT|DELETE /salas/{id}`, `GET /salas/disponiveis`
- `GET /reservas/minhas`, `POST /reservas`, `PUT /reservas/{id}`, `DELETE /reservas/{id}`

As rotas de salas e reservas exigem o cookie de sessão retornado pelo cadastro ou login. O corpo de uma reserva contém `salaId`, `data` (`YYYY-MM-DD`), `horaInicio` e `horaFim` (`HH:mm`). O solicitante é obtido da sessão autenticada, não dos dados enviados pelo cliente.

## Testes e CI

Execute lint e testes localmente:

```powershell
cd backend
.\mvnw.cmd -B checkstyle:check test
```

O GitHub Actions executa build, Checkstyle e testes em Pull Requests e pushes para `master` e `main`. A branch padrão atual do repositório é `master`; uma execução remota verde ainda precisa ser confirmada depois do envio destas alterações.

## Equipe e links

- **Turma:** TDS, turmas mistas 6570, 6756 e 6863.
- **Integrantes listados no README anterior da equipe:** Giovana, Fábio, Theo e João Raupp; confirmar a lista e os usuários do GitHub com o grupo.
- **Tech lead / responsáveis pelos blocos da apresentação:** a confirmar pelo grupo; ver [`docs/PRESENTATION_OUTLINE.pt-BR.md`](docs/PRESENTATION_OUTLINE.pt-BR.md).
- **Colaborador professor:** o administrador do repositório precisa confirmar/adicionar `adrianoanschau@gmail.com`.
- **Repositório:** [giovanaganzerdeleon/ReservaEspacos](https://github.com/giovanaganzerdeleon/ReservaEspacos).
- **Board do GitHub:** o grupo precisa adicionar o link e definir responsáveis pelas tarefas.
- **Deploy:** ainda não configurado.

## Verificações restantes da entrega

Não é possível confirmar a partir deste worktree local o acesso remoto/board, a revisão das alterações atuais, Conventional Commits de todos os integrantes, o check-in com o professor ou uma execução verde do GitHub Actions. O status e as próximas ações estão em [`DIA3_STATUS.pt-BR.md`](DIA3_STATUS.pt-BR.md).
