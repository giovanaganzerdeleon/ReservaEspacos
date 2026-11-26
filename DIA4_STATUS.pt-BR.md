# Checklist do Dia 4 — 26 de novembro de 2026

Este checklist acompanha o fechamento do MVP e a preparação da demo. Uma caixa marcada indica algo já verificado; planos e tarefas da equipe continuam desmarcados.

## 1. Estabilizar o essencial do Dia 3

- [ ] Executar o fluxo completo de ponta a ponta: cadastro/login, reserva e consulta/edição/cancelamento.
- [x] Confirmar que o cadastro usa a API de autenticação e que a validação de senha do serviço está coberta por testes.
- [ ] Testar o cadastro e login por HTTP/navegador com o back-end ativo. Na última verificação, não havia servidor escutando em `127.0.0.1:8080`.
- [ ] Executar novamente todos os testes e Checkstyle após as alterações atuais.
- [x] README, DECISIONS e AI_LOG existem em inglês e português brasileiro.
- [x] O workflow do GitHub Actions inclui build, Checkstyle e testes.
- [ ] Confirmar uma execução verde do workflow para as alterações atuais e após o merge na branch principal.
- [x] `.gitignore` exclui arquivos `.env` e o banco H2 local; não adicionar dados locais ou credenciais ao Git.

## 2. Caminho feliz e dados de demonstração

- [ ] Definir com a equipe o caminho único da demo e executá-lo mais de uma vez.
- [x] Há carga inicial de itens demonstrativos no código; confirmar os dados reais após iniciar a aplicação.
- [ ] Confirmar o escopo final que será apresentado. Proposta atual: autenticar, reservar item, demonstrar limite de estoque e listar/editar/cancelar; incluir salas somente se o fluxo estiver estável.
- [ ] Definir um plano B. Proposta: se o servidor falhar, mostrar testes e implementação, deixando claro que isso não substitui uma demo ao vivo.

## 3. Congelamento de escopo e colaboração

- [ ] A equipe confirmar e congelar o escopo da apresentação; não adicionar funcionalidades fora do caminho escolhido.
- [ ] Cada integrante fazer ao menos um Conventional Commit hoje, em PRs pequenos.
- [ ] Confirmar responsáveis pelo fluxo da demo e pelas tarefas do board.

## 4. Testes e pipeline

- [x] `AuthServiceTest`: 5 testes aprovados, incluindo senha correta/incorreta e hash armazenado inválido.
- [x] Execução limpa após as alterações atuais: 17 testes aprovados e 0 violações do Checkstyle.
- [ ] Confirmar o resultado verde mais recente do GitHub Actions. A execução verde do PR #4 é anterior às alterações locais atuais.
- [ ] Avaliar/adicionar teste HTTP de integração para o ciclo de vida de reserva; atualmente não há runner automatizado de front-end.

## 5. Documentação, segurança e apresentação

- [x] READMEs bilíngues descrevem stack, estrutura, execução local e a necessidade do back-end para cadastro/login.
- [x] DECISIONS registra as escolhas atuais e os itens fora do escopo do MVP.
- [x] AI_LOG registra o trabalho assistido por IA e as validações conhecidas.
- [ ] Revisar novamente os arquivos e o histórico antes de enviar mudanças; a busca anterior por padrões comuns de segredos foi direcionada e não é garantia absoluta.
- [x] Roteiro de 15 minutos e check-in de 5 minutos estão preparados em `/docs`.
- [ ] Dividir falas e demo entre os integrantes e ensaiar mais perto da apresentação.
- [ ] Fazer o check-in com o professor, mostrar o que estiver funcionando e declarar pendências/bloqueios com precisão.

## Verificações e bloqueios observados

- A chamada local a `127.0.0.1:8080` foi recusada porque não havia processo escutando nessa porta. O “Failed to fetch” acontece antes de a API responder; não é uma rejeição de senha ou dos campos do cadastro.
- Abrir uma branch do GitHub ou `frontend/index.html` não inicia o servidor. Para teste local, iniciar Spring Boot em `backend/` e acessar `http://127.0.0.1:8080` no mesmo computador. A aplicação ainda não tem deploy público.
- A suíte completa passou com 17 testes e o Checkstyle reportou 0 violações após as alterações mais recentes.
- O JavaScript de conexão agora mostra uma orientação mais clara quando a API não está acessível. Não foi possível executar `node --check`, pois Node.js não está instalado neste ambiente.
- Foi criado `iniciar.bat` para iniciar o back-end com duplo clique; a execução real do iniciador e o fluxo HTTP ainda precisam ser testados.
- A aplicação agora emite `ReservaCampus esta pronto` após concluir a inicialização; confirmar a mensagem no primeiro teste real.
- A pipeline verde do PR #4 é um resultado anterior; as mudanças locais atuais ainda precisam de PR e CI.

## Próximas ações prioritárias

1. Iniciar o back-end e testar cadastro/login em `http://127.0.0.1:8080`.
2. Executar o caminho feliz escolhido mais de uma vez e confirmar os itens demonstrativos.
3. Rodar suíte completa, Checkstyle e pipeline para as alterações atuais.
4. Confirmar escopo final, plano B, commits dos integrantes e divisão da apresentação.
