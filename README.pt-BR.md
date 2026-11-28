# ReservaCampus

[English](README.md) · **Português (Brasil)**

ReservaCampus é um MVP para reservar salas e itens do campus. A aplicação tem uma interface web e uma API REST em Java.

## Problema e solução

Estudantes e equipes do campus podem depender de conversas ou planilhas para encontrar salas e materiais. Isso dificulta acompanhar a disponibilidade e evitar conflitos. O ReservaCampus oferece catálogos de salas e itens e permite que usuários autenticados criem, consultem, editem ou cancelem reservas. O sistema impede a sobreposição de reservas de sala e limita as reservas de itens ao estoque disponível no período.

## Escopo do MVP

- Cadastro de conta e login com e-mail/senha.
- Catálogo de salas com nome, capacidade e disponibilidade.
- Catálogo de itens do campus com categoria e quantidade em estoque; é possível cadastrar materiais de sala e equipamentos.
- Criar, listar, editar e cancelar as próprias reservas de salas e itens.
- Impedir sobreposição de reservas de sala e impedir reservas acima do estoque de itens.

Recorrência, integração com sistemas oficiais do campus, aprovações e sugestões de horários por IA estão fora do MVP. As quantidades iniciais de itens são apenas demonstrativas, não representam o inventário oficial.

## Stack

- **Front-end:** HTML, CSS e JavaScript sem framework em `/frontend`; chamadas JSON à API REST.
- **Back-end:** Java 21, Spring Boot 4.1.2, Spring MVC, Spring Data JPA e Hibernate.
- **Autenticação:** hash de senha BCrypt e sessões HTTP no servidor.
- **Banco de dados:** H2 localmente; deploy de demonstração no Railway preparado com PostgreSQL, ainda não publicado.
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
├── DIA4_STATUS.pt-BR.md
├── DIA5_STATUS.md
└── DIA5_STATUS.pt-BR.md
```

Durante o desenvolvimento local, o back-end serve os arquivos de `/frontend`, então navegador e API usam a mesma origem. O front-end não exige gerenciador de pacotes.

> **Importante:** abrir `frontend/index.html` diretamente ou visualizar uma branch do GitHub não inicia a API. Na execução local, o back-end também precisa estar iniciado. Depois de hospedar a aplicação, o computador de apresentação precisa apenas de navegador e internet. O deploy ainda não foi realizado; veja [`docs/DEPLOYMENT.pt-BR.md`](docs/DEPLOYMENT.pt-BR.md).

## Executar localmente (Windows)

**Pré-requisito:** Java 21. O Maven Wrapper baixa o Maven no primeiro uso.

1. Na raiz do projeto, clique duas vezes em `iniciar.bat`.
2. Mantenha a janela do terminal aberta enquanto usa o sistema.
3. Aguarde a mensagem `ReservaCampus esta pronto` no terminal.
4. Só então abra [http://127.0.0.1:8080](http://127.0.0.1:8080). Se a mensagem não aparecer, a aplicação ainda não iniciou; confira o erro no terminal.

Se houver erro, a janela ficará aberta para mostrar a mensagem. Para iniciar pelo PowerShell manualmente:

```powershell
cd C:\caminho\para\ReservaEspacos\backend
.\mvnw.cmd spring-boot:run
```

Crie uma conta pela tela de cadastro e use as abas **Salas** e **Itens do campus**. Quando as respectivas tabelas estiverem vazias, o sistema cadastra quatro salas (Sala 101, Sala 102, Laboratório de Informática e Sala multimídia) e cinco tipos de itens (projetores, notebooks, kits de laboratório, lousa móvel e kits de canetões). As quantidades são ilustrativas; não representam o inventário oficial. O seed não cria conta nem senha padrão.

O banco H2 é criado em `backend/data/` em cada computador. Cada instalação local tem seus próprios usuários, reservas e dados; eles não são sincronizados entre computadores. Não envie esse arquivo ao repositório. Para compartilhar dados entre máquinas, será necessário configurar um banco de dados compartilhado.

Se já houver uma instância usando o banco local, pare-a com `Ctrl+C` antes de iniciar outra.

## API

- `POST /auth/cadastro`, `POST /auth/login`, `GET /auth/me`, `POST /auth/logout`
- `GET|POST /salas`, `GET|PUT|DELETE /salas/{id}`, `GET /salas/disponiveis`
- `GET /reservas/minhas`, `POST /reservas`, `PUT /reservas/{id}`, `DELETE /reservas/{id}`
- `GET|POST /itens`, `GET /itens/disponiveis?data=YYYY-MM-DD&horaInicio=HH:mm&horaFim=HH:mm`
- `GET /reservas-itens/minhas`, `POST /reservas-itens`, `PUT /reservas-itens/{id}`, `DELETE /reservas-itens/{id}`

As rotas de salas, itens e reservas exigem o cookie de sessão retornado pelo cadastro ou login. O corpo de uma reserva de sala contém `salaId`, `data` (`YYYY-MM-DD`), `horaInicio` e `horaFim` (`HH:mm`). Uma reserva de item contém `itemId`, `quantidade`, `data`, `horaInicio` e `horaFim`. O solicitante é obtido da sessão autenticada, não dos dados enviados pelo cliente.

## Testes e CI

Execute Checkstyle e os testes do back-end:

```powershell
cd backend
.\mvnw.cmd -B checkstyle:check test
```

O GitHub Actions executa build, Checkstyle e testes em Pull Requests e em pushes para `master` e `main`. Verifique a aba **Actions** para consultar o resultado mais recente. O fluxo de contribuição esperado é abrir um Pull Request, obter revisão e aguardar as verificações antes do merge.

## Equipe e links

- **Turma:** TDS, turmas mistas 6570, 6756 e 6863.
- **Tech lead:** Giovana.
- **Liderança da apresentação:** Theo.
- **Equipe de apresentação:** Theo, Fábio e Giovana.
- **Roteiro:** ver [`docs/PRESENTATION_OUTLINE.pt-BR.md`](docs/PRESENTATION_OUTLINE.pt-BR.md).
- **Repositório:** [giovanaganzerdeleon/ReservaEspacos](https://github.com/giovanaganzerdeleon/ReservaEspacos).
- **Hospedagem pública:** não configurada.

## Documentação do projeto

- [Decisões do projeto](DECISIONS.pt-BR.md)
- [Registro de uso de IA](AI_LOG.pt-BR.md)
- [Checklist do Dia 5](DIA5_STATUS.pt-BR.md)
- [Roteiro da apresentação](docs/PRESENTATION_OUTLINE.pt-BR.md)
- [Guia de hospedagem da demonstração](docs/DEPLOYMENT.pt-BR.md)
