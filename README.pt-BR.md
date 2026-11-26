# ReservaCampus

[English](README.md) · **Português (Brasil)**

ReservaCampus é um MVP para reservar recursos do campus. O código do front-end é mantido separado do back-end Java com API REST.

## Problema e solução

Estudantes e equipes do campus muitas vezes dependem de conversas ou planilhas para encontrar salas e materiais de aula. As informações podem ficar desatualizadas e dois grupos podem planejar usar o mesmo recurso. O ReservaCampus oferece catálogos de salas e itens e permite que usuários autenticados criem, consultem, editem ou cancelem reservas. Reservas de sala não podem se sobrepor; reservas de itens também respeitam o estoque disponível em cada faixa de horário.

## Escopo do MVP

- Cadastro de conta e login com e-mail/senha.
- Catálogo de salas com nome, capacidade e disponibilidade.
- Catálogo de itens do campus com categoria e quantidade em estoque; é possível cadastrar materiais de sala e equipamentos.
- Criar, listar, editar e cancelar as próprias reservas de salas e itens.
- Impedir sobreposição de reservas de sala e impedir reservas acima do estoque de itens.

Recorrência, integração com sistemas do campus, aprovações e sugestões alternativas geradas por IA ficam fora do MVP atual. As quantidades iniciais dos itens são dados demonstrativos e devem ser ajustadas ao estoque real do campus.

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
├── iniciar.bat               # Iniciador local do back-end no Windows
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

Durante o desenvolvimento local, o back-end serve os arquivos de `/frontend`, então navegador e API usam a mesma origem. O código-fonte continua separado e não exige gerenciador de pacotes para o front-end.

> **Importante:** abrir `frontend/index.html` diretamente ou visualizar uma branch do GitHub não inicia a API. Para cadastro e login, o back-end Spring precisa estar rodando; cada pessoa que executa localmente precisa iniciar o próprio back-end. A aplicação ainda não está hospedada para acesso público.

## Executar localmente (Windows)

Pré-requisito: Java 21. O Maven Wrapper baixa o Maven no primeiro uso.

1. Na raiz do projeto, clique duas vezes em `iniciar.bat`.
2. Mantenha a janela do terminal aberta enquanto usa o sistema.
3. Aguarde a mensagem `ReservaCampus esta pronto` no terminal.
4. Só então abra [http://127.0.0.1:8080](http://127.0.0.1:8080). Se a mensagem não aparecer, a aplicação ainda não iniciou; confira o erro no terminal.

Se houver erro, a janela ficará aberta para mostrar a mensagem. Para iniciar pelo PowerShell manualmente:

```powershell
cd C:\caminho\para\ReservaEspacos\backend
.\mvnw.cmd spring-boot:run
```

Crie uma conta e use as abas **Salas** e **Itens do campus** para gerenciar reservas. Se a tabela de itens estiver vazia, o back-end cadastra itens demonstrativos: projetor, notebooks, kits de laboratório, lousa móvel e kits de canetões. Pare uma instância existente com `Ctrl+C` antes de iniciar outra, pois o H2 embutido bloqueia o arquivo local do banco.

O banco local fica em `backend/data/`. É específico da máquina e não deve ser enviado ao repositório.

## API

- `POST /auth/cadastro`, `POST /auth/login`, `GET /auth/me`, `POST /auth/logout`
- `GET|POST /salas`, `GET|PUT|DELETE /salas/{id}`, `GET /salas/disponiveis`
- `GET /reservas/minhas`, `POST /reservas`, `PUT /reservas/{id}`, `DELETE /reservas/{id}`
- `GET|POST /itens`, `GET /itens/disponiveis?data=YYYY-MM-DD&horaInicio=HH:mm&horaFim=HH:mm`
- `GET /reservas-itens/minhas`, `POST /reservas-itens`, `PUT /reservas-itens/{id}`, `DELETE /reservas-itens/{id}`

As rotas de salas, itens e reservas exigem o cookie de sessão retornado pelo cadastro ou login. O corpo de uma reserva de sala contém `salaId`, `data` (`YYYY-MM-DD`), `horaInicio` e `horaFim` (`HH:mm`). Uma reserva de item contém `itemId`, `quantidade`, `data`, `horaInicio` e `horaFim`. O solicitante é obtido da sessão autenticada, não dos dados enviados pelo cliente.

## Testes e CI

Execute lint e testes localmente:

```powershell
cd backend
.\mvnw.cmd -B checkstyle:check test
```

O GitHub Actions executa build, Checkstyle e testes em Pull Requests e pushes para `master` e `main`. A execução do PR #4 passou nos três passos. A branch `main` está protegida e exige Pull Request; ainda falta confirmar a execução após o merge final em `master`/`main`.

## Equipe e links

- **Turma:** TDS, turmas mistas 6570, 6756 e 6863.
- **Integrantes listados no README anterior da equipe:** Giovana, Fábio, Theo e João Raupp; confirmar a lista e os usuários do GitHub com o grupo.
- **Tech lead / responsáveis pelos blocos da apresentação:** a confirmar pelo grupo; ver [`docs/PRESENTATION_OUTLINE.pt-BR.md`](docs/PRESENTATION_OUTLINE.pt-BR.md).
- **Colaborador professor:** `adrianoanschau@gmail.com` foi adicionado ao repositório.
- **Repositório:** [giovanaganzerdeleon/ReservaEspacos](https://github.com/giovanaganzerdeleon/ReservaEspacos).
- **Board do GitHub:** o grupo precisa adicionar o link e definir responsáveis pelas tarefas.
- **Deploy:** ainda não configurado.

## Verificações restantes da entrega

Ainda precisam ser confirmados o board e a atribuição das tarefas, os Conventional Commits de todos os integrantes, a verificação ao vivo da aplicação, o ensaio da apresentação e a execução da pipeline após o merge. O professor já foi adicionado ao repositório e o Actions do PR #4 passou. O status atual do Dia 4 e as próximas ações estão em [`DIA4_STATUS.pt-BR.md`](DIA4_STATUS.pt-BR.md).
