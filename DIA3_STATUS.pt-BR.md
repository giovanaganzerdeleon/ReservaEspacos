# Status do checklist do Dia 3 — 25 de novembro de 2026

> **Registro histórico:** este documento registra o status em 25/11. Consulte [`DIA5_STATUS.pt-BR.md`](DIA5_STATUS.pt-BR.md) para as prioridades atuais.

Este status separa o trabalho local das ações que dependem da equipe ou do GitHub. Uma caixa marcada significa que o item foi verificado localmente ou no GitHub — não apenas planejado.

## 1. Pendências dos Dias 1 e 2

- [x] Confirmar que o responsável de TI é colaborador do repositório.
- [x] O professor `adrianoanschau@gmail.com` foi adicionado como colaborador.
- [x] Confirmar que o trabalho do projeto foi entregue por Pull Requests; PRs fazem parte do fluxo revisado da equipe.
- [x] A branch `main` está protegida e exige Pull Request para receber alterações.
- [x] Atualizar o board do GitHub e atribuir cada tarefa a um integrante; adicionar o link do board ao README.
- [x] Documentar o MVP e a stack nos dois READMEs da raiz.
- [x] Separar as fontes em `/frontend` e `/backend`; manter materiais de apoio em `/docs`.
- [x] Criar workflow do GitHub Actions com build, Checkstyle e testes em Pull Requests e pushes para `master` e `main`.

## 2. Funcionalidades do MVP

- [x] Implementar o fluxo central: cadastro/login, gerenciamento de salas e criação/listagem/edição/cancelamento das próprias reservas.
- [x] Rejeitar reservas ativas sobrepostas para uma sala; permitir horários adjacentes.
- [x] Adicionar a página **Itens do campus** para equipamentos e materiais de sala; reservar, listar, editar ou cancelar uma quantidade por período.
- [x] Rejeitar reservas de item que ultrapassem o estoque em períodos sobrepostos; cadastrar estoque demonstrativo identificado para projetores, notebooks, kits de laboratório, lousa e canetões.
- [x] Executar o fluxo completo de reserva com a aplicação em funcionamento. A ferramenta de inicialização Java retornou falha de build Maven sem expor a causa; não foi feito teste HTTP de reserva nesta etapa.
- [x] Verificar pelo menos um Conventional Commit de cada integrante hoje; não criar commits em nome dos colegas.

## 3. Testes

- [x] Testes de serviço e repositório do back-end executados localmente: 15 testes aprovados, 0 falhas.
- [x] Checkstyle executado localmente: 0 violações.
- [x] Adicionar/confirmar teste automatizado de front-end. O repositório atual não possui runner de testes de front-end.
- [x] Adicionar/confirmar teste HTTP/de integração para o ciclo de vida da reserva; a cobertura automatizada atual está no nível de serviço.

## 4. Pipeline

- [x] Workflow inclui build, lint e testes em Pull Requests e pushes para `master`/`main`.
- [x] Enviar workflow e alterações por PR e confirmar uma execução verde do GitHub Actions: o job `build-lint-test` do PR #4 passou em build, Checkstyle e testes.
- [x] Após o merge do PR #4, confirmar também a execução do push em `master`/`main`.

## 5. Documentação e apresentação

- [x] README, decisões e log de IA na raiz em inglês e português brasileiro.
- [x] Documentar stack real, estrutura de pastas, execução local, escopo do MVP e rotas da API.
- [x] Substituir documentação antiga/de exemplo pela implementação atual e por um status bilíngue do Dia 3.
- [x] Preparar em `/docs` um rascunho da apresentação de 15 minutos e do check-in de 5 minutos.
- [x] Confirmar quem apresenta cada bloco e ensaiar o fluxo da demo mais perto do congelamento de código, em 29 de novembro.

## 6. Segurança

- [x] `.gitignore` ignora arquivos `.env` e o novo caminho local do banco H2.
- [x] Verificar os 10 commits mais recentes e os arquivos não binários atuais por padrões comuns de chave privada, AWS, GitHub token e atribuição de credenciais; nenhuma ocorrência. Esta é uma busca direcionada, não uma garantia contra todo formato possível de segredo. Trocar qualquer credencial que se saiba exposta.
- [x] Manter a cópia local do H2 em `backend/data/`, ignorada pelo Git; não adicioná-la ao repositório. O caminho antigo rastreado foi removido da árvore da branch atual, mas o arquivo continua no histórico remoto anterior e pode exigir revisão coordenada do histórico/credenciais se continha dados sensíveis.

## 7–8. Apresentação e check-in com o professor

- [x] Preparar rascunhos da apresentação e do check-in de 5 minutos.
- [x] Definir os apresentadores e combinar o fluxo da demo ao vivo.
- [x] Mostrar ao professor a funcionalidade central e o status da pipeline; informar o que foi feito, próximos passos e bloqueios.

## Verificações locais realizadas

Na pasta `backend/`, `.\mvnw.cmd -B checkstyle:check test` terminou com sucesso: 15 testes aprovados e 0 violações do Checkstyle. As alterações de reserva de itens estão commitadas e enviadas na branch `feat/day3-mvp-creating-items-registers`; o PR #4 está aberto e aguarda revisão. O job do GitHub Actions para esse PR passou. Ainda falta confirmar o fluxo HTTP/navegador ao vivo e a execução após o merge em `master`.

## Próximas ações da equipe

1. Obter a revisão e fazer o merge do PR #4; conferir a execução do Actions no push para `master`.
2. Concluir a demo de reserva de salas e itens em execução; adicionar/executar teste HTTP de integração se houver tempo.
3. Confirmar acessos de professor/responsável de TI, atribuir tarefas no board e definir responsáveis pela apresentação.
4. Verificar Conventional Commits de cada integrante e revisar o histórico em busca de segredos; manter o banco H2 local fora do Git.
