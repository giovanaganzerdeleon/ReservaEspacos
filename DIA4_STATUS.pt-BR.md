# Checklist do Dia 4 — 26 de novembro de 2026

> **Registro histórico:** este status foi elaborado em 26/11. As caixas refletem as verificações daquele momento e não devem ser interpretadas como o estado atual. Para as prioridades atuais, consulte [`DIA5_STATUS.pt-BR.md`](DIA5_STATUS.pt-BR.md).

Este checklist acompanha o fechamento do MVP e a preparação da demo. Uma caixa marcada indica algo já verificado; planos e tarefas da equipe continuam desmarcados.

## 1. Estabilizar o essencial do Dia 3

- [ ] Executar o fluxo completo de ponta a ponta: cadastro/login, reserva e consulta/edição/cancelamento.
- [x] Confirmar que o cadastro usa a API de autenticação e que a validação de senha do serviço está coberta por testes.
- [ ] Testar cadastro e login por HTTP/navegador; a inicialização do servidor foi confirmada, mas o fluxo de autenticação ainda não foi verificado.
- [x] README, DECISIONS e AI_LOG existem em inglês e português brasileiro.
- [x] O workflow do GitHub Actions inclui build, Checkstyle e testes.
- [ ] Confirmar uma execução verde do workflow que inclua as alterações locais atuais e após o merge na branch principal. O PR #6 passou antes destas alterações.
- [x] `.gitignore` exclui arquivos `.env` e o banco H2 local; não adicionar dados locais ou credenciais ao Git.

## 2. Caminho feliz e dados de demonstração

- [ ] Definir com a equipe o caminho único da demo e executá-lo mais de uma vez.
- [x] Há carga inicial de quatro salas e cinco tipos de itens, somente quando as respectivas tabelas estão vazias; confirmar as salas após reiniciar a aplicação com esta alteração.
- [ ] Confirmar o escopo final que será apresentado. Proposta atual: autenticar, reservar item, demonstrar limite de estoque e listar/editar/cancelar; incluir salas somente se o fluxo estiver estável.
- [ ] Definir um plano B. Proposta: se o servidor falhar, mostrar testes e implementação, deixando claro que isso não substitui uma demo ao vivo.

## 3. Congelamento de escopo e colaboração

- [ ] A equipe confirmar e congelar o escopo da apresentação; não adicionar funcionalidades fora do caminho escolhido.
- [ ] Cada integrante fazer ao menos um Conventional Commit hoje, em PRs pequenos.
- [ ] Confirmar responsáveis pelo fluxo da demo e pelas tarefas do board.

## 4. Testes e pipeline

- [x] `AuthServiceTest`: 5 testes aprovados, incluindo senha correta/incorreta e hash armazenado inválido.
- [x] Suíte completa: 19 testes aprovados e 0 violações do Checkstyle; nessa execução o Maven informou `Nothing to compile`, então a recompilação limpa ainda está pendente.
- [x] A execução do PR #6 passou em Build, Lint e Tests para o commit `c1e545e`; ela antecede as alterações locais atuais.
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

- O terminal enviado pelo usuário mostrou Tomcat iniciado na porta `8080` e a mensagem `ReservaCampus esta pronto`; uma verificação local posterior também encontrou o servidor escutando. Cadastro/login e reservas ainda precisam de verificação HTTP/navegador.
- Abrir uma branch do GitHub ou `frontend/index.html` não inicia o servidor. Para teste local, iniciar Spring Boot em `backend/` e acessar `http://127.0.0.1:8080` no mesmo computador. A aplicação ainda não tem deploy público.
- A suíte completa passou com 19 testes e o Checkstyle reportou 0 violações após a inclusão do seed de salas.
- O JavaScript de conexão agora mostra uma orientação mais clara quando a API não está acessível. Não foi possível executar `node --check`, pois Node.js não está instalado neste ambiente.
- `iniciar.bat` foi executado e a mensagem `ReservaCampus esta pronto` apareceu no terminal; manter a janela aberta durante a demo.
- O seed de salas foi adicionado depois da inicialização mostrada; parar e iniciar a aplicação novamente para carregar salas quando a tabela estiver vazia.
- A pipeline do PR #6 passou para o commit `c1e545e`; as mudanças locais feitas depois ainda precisam de PR e CI.

## Próximas ações prioritárias

1. Reiniciar o back-end para carregar as salas demonstrativas e confirmar os catálogos na interface.
2. Testar cadastro/login em `http://127.0.0.1:8080` e executar o caminho feliz mais de uma vez.
3. Após encerrar o servidor atual, executar build/teste limpo; depois confirmar a pipeline do GitHub Actions para as alterações atuais.
4. Confirmar escopo final, plano B, commits dos integrantes e divisão da apresentação.
