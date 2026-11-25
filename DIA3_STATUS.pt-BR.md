# Status do checklist do Dia 3 — 25 de novembro de 2026

Este status separa o trabalho local das ações que dependem da equipe ou do GitHub. Uma caixa marcada significa que o item foi verificado localmente ou no GitHub — não apenas planejado.

## 1. Pendências dos Dias 1 e 2

- [ ] Confirmar que o responsável de TI e o professor são colaboradores do repositório; o briefing informa o e-mail do professor como `adrianoanschau@gmail.com`.
- [ ] Confirmar que o Pull Request de criação do projeto teve revisão de um colega. PRs anteriores merged não comprovam que as alterações locais atuais foram revisadas.
- [ ] Atualizar o board do GitHub e atribuir cada tarefa a um integrante; adicionar o link do board ao README.
- [x] Documentar o MVP e a stack nos dois READMEs da raiz.
- [x] Separar as fontes em `/frontend` e `/backend`; manter materiais de apoio em `/docs`.
- [x] Criar workflow do GitHub Actions com build, Checkstyle e testes em Pull Requests e pushes para `master` e `main`.

## 2. Funcionalidades do MVP

- [x] Implementar o fluxo central: cadastro/login, gerenciamento de salas e criação/listagem/edição/cancelamento das próprias reservas.
- [x] Rejeitar reservas ativas sobrepostas para uma sala; permitir horários adjacentes.
- [ ] Executar o fluxo completo de reserva com a aplicação em funcionamento. A ferramenta de inicialização Java retornou falha de build Maven sem expor a causa; não foi feito teste HTTP de reserva nesta etapa.
- [ ] Verificar pelo menos um Conventional Commit de cada integrante hoje; não criar commits em nome dos colegas.

## 3. Testes

- [x] Testes de serviço do back-end executados localmente: 10 testes aprovados, 0 falhas.
- [x] Checkstyle executado localmente: 0 violações.
- [ ] Adicionar/confirmar teste automatizado de front-end. O repositório atual não possui runner de testes de front-end.
- [ ] Adicionar/confirmar teste HTTP/de integração para o ciclo de vida da reserva; a cobertura automatizada atual está no nível de serviço.

## 4. Pipeline

- [x] Workflow inclui build, lint e testes em Pull Requests e pushes para `master`/`main`.
- [ ] Enviar o workflow e as alterações; depois confirmar que a última execução do GitHub Actions ficou verde. O GitHub retornou **0 execuções** na consulta feita para este status.

## 5. Documentação e apresentação

- [x] README, decisões e log de IA na raiz em inglês e português brasileiro.
- [x] Documentar stack real, estrutura de pastas, execução local, escopo do MVP e rotas da API.
- [x] Substituir documentação antiga/de exemplo pela implementação atual e por um status bilíngue do Dia 3.
- [x] Preparar em `/docs` um rascunho da apresentação de 15 minutos e do check-in de 5 minutos.
- [ ] Confirmar quem apresenta cada bloco e ensaiar o fluxo da demo mais perto do congelamento de código, em 29 de novembro.

## 6. Segurança

- [x] `.gitignore` ignora arquivos `.env` e o novo caminho local do banco H2.
- [x] Verificar os 10 commits mais recentes e os arquivos não binários atuais por padrões comuns de chave privada, AWS, GitHub token e atribuição de credenciais; nenhuma ocorrência. Esta é uma busca direcionada, não uma garantia contra todo formato possível de segredo. Trocar qualquer credencial que se saiba exposta.
- [ ] Revisar a exclusão pendente do antigo `demo/data/reservas.mv.db`, que era rastreado, antes de fazer commit. A cópia local continua em `backend/data/`, ignorada pelo Git; não adicionar o banco novamente.

## 7–8. Apresentação e check-in com o professor

- [x] Preparar rascunhos da apresentação e do check-in de 5 minutos.
- [ ] Definir os apresentadores e combinar o fluxo da demo ao vivo.
- [ ] Mostrar ao professor a funcionalidade central e o status da pipeline; informar o que foi feito, próximos passos e bloqueios.

## Verificações locais realizadas

Na pasta `backend/`, `.\mvnw.cmd -B checkstyle:check test package` terminou com sucesso: 10 testes aprovados, 0 violações do Checkstyle e build do pacote concluído. O código ainda não foi commitado nem enviado neste worktree. A informação do GitHub Actions acima vem da consulta ao repositório remoto; nenhuma execução foi retornada.

## Próximas ações da equipe

1. Concluir a demo de reservas em execução e adicionar/executar teste HTTP de integração.
2. Enviar por Pull Request revisado; depois verificar o resultado do Actions e a proteção da branch.
3. Atribuir tarefas no board, definir responsáveis pela apresentação e confirmar o acesso do professor/responsável de TI.
4. Revisar o histórico recente em busca de segredos e garantir que o arquivo local H2 não entre no commit.
