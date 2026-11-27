# Registro de uso de IA

[English](AI_LOG.md) · **Português (Brasil)**

As entradas descrevem o trabalho assistido por IA neste repositório. O conteúdo gerado foi revisado e comparado com o código existente; ele não comprova que ações externas da equipe ou do GitHub foram concluídas.

## 25 de novembro — Dia 3

### Implementação e validação do MVP

- **Ferramenta:** GitHub Copilot no VS Code.
- **Resumo do prompt:** Implementar um fluxo demonstrável de reserva de salas, preservar o login existente, manter as fontes de front-end e back-end separadas e seguir o checklist do Dia 3 e as orientações Markdown do repositório.
- **Trabalho gerado/assistido:** API e persistência de reservas, validação de propriedade e sobreposição, interações para criar/listar/editar/cancelar reservas, atualização de CI/testes e documentação do projeto.
- **Revisão e correções humanas:** Manter a stack Java/Spring Boot existente; preservar reservas canceladas em vez de apagá-las; verificar propriedade antes da edição; permitir horários adjacentes e rejeitar sobreposições; usar BCrypt e sessões no servidor; remover exemplos antigos da documentação em vez de tratá-los como fatos do projeto. Corrigido um caso de interface para permitir manter a sala de uma reserva existente caso ela seja marcada indisponível depois.
- **Validação:** Checkstyle Maven e JUnit foram executados localmente; a classe de testes de autenticação agora tem 5 testes aprovados, incluindo login correto, senha incorreta e hash armazenado inválido. O status remoto do Actions é acompanhado separadamente e não é inferido a partir dos testes locais.

### Estrutura do repositório e documentação bilíngue

- **Ferramenta:** GitHub Copilot no VS Code.
- **Resumo do prompt:** Completar a documentação pendente do Dia 3 em inglês e português brasileiro e organizar o código-fonte em diretórios de front-end, back-end e documentação conforme a orientação já existente no projeto.
- **Trabalho gerado/assistido:** Atualização dos READMEs, registros de arquitetura/decisões, logs de IA, checklist de status e roteiro de apresentação/check-in; movimentação da aplicação Java para `/backend` e do briefing de apoio para `/docs`.
- **Revisão e correções humanas:** Manter os READMEs principais na raiz; não inventar responsáveis pela apresentação, links do board, acesso do professor, commits individuais ou resultados remotos da CI; marcar esses itens para confirmação da equipe/GitHub.
- **Validação:** As afirmações da documentação são conferidas com a árvore de arquivos e os resultados locais dos testes. As ações externas restantes aparecem explicitamente nos arquivos de status bilíngues.

### Reservas de itens do campus

- **Ferramenta:** GitHub Copilot no VS Code.
- **Resumo do prompt:** Adicionar uma página no sistema para reservar itens do campus/Senac com controle de quantidade em estoque, incluindo projetores, notebooks, equipamentos de laboratório, lousas e canetões; manter os arquivos Markdown do projeto atualizados.
- **Trabalho gerado/assistido:** Entidades de item e reserva de item, APIs autenticadas, validação de disponibilidade com bloqueio da linha do item, carga inicial demonstrativa e interface **Itens do campus** para catálogo, reserva, edição, cancelamento e disponibilidade por quantidade.
- **Revisão e correções humanas:** Tratar quantidades como dados demonstrativos, não como inventário Senac verificado; manter esta funcionalidade separada das reservas de sala; calcular estoque compartilhado considerando intervalos sobrepostos; documentar recorrência e integração com inventário oficial como fora do escopo.
- **Validação:** Checkstyle reportou 0 violações e 15 testes do back-end passaram, incluindo quatro testes focados no serviço de reservas de itens e um teste de repositório para estoque sobreposto, adjacente, cancelado e ignorado na edição. Ainda falta testar o novo fluxo via HTTP/navegador.

## 26 de novembro — Dia 4

### Diagnóstico do cadastro e preparação do caminho da demo

- **Ferramenta:** GitHub Copilot no VS Code.
- **Resumo do prompt:** Investigar falhas de conexão no cadastro/login, preparar o iniciador local e revisar a documentação contra o código e o checklist do Dia 4.
- **Trabalho gerado/assistido:** Esclarecido que uma branch do GitHub não hospeda a API; adicionadas mensagens acionáveis no front-end e no terminal e o iniciador `iniciar.bat`; implementado seed de quatro salas e itens apenas quando suas respectivas tabelas estão vazias; corrigidas divergências encontradas nos READMEs, status, decisões e roteiro bilíngues.
- **Revisão e correções humanas:** Manter API e front-end na mesma origem no modo local; não sobrescrever dados existentes, criar senha padrão, expor o servidor local à rede ou afirmar que há deploy público. A inicialização foi confirmada pelo terminal enviado pelo usuário; o fluxo de cadastro/reserva por HTTP continua pendente.
- **Validação:** `checkstyle:check test` passou com 19 testes e 0 violações, mas o Maven informou `Nothing to compile`; a recompilação limpa ainda precisa ser feita após parar a instância que usa `target`. A porta `8080` foi observada escutando e o terminal mostrou a mensagem de pronto antes da inclusão do seed de salas; reiniciar e conferir os dados. O Actions do PR #6 passou em Build, Lint e Tests no commit `c1e545e`; as alterações locais posteriores ainda não passaram pela pipeline. `node --check` não estava disponível porque Node.js não está instalado neste ambiente.

## 27 de novembro — Dia 5

### Organização da documentação e preparação da apresentação

- **Ferramenta:** GitHub Copilot no VS Code.
- **Resumo do prompt:** Organizar os arquivos Markdown do projeto para deixá-los mais claros e profissionais e registrar Theo como líder da apresentação e Giovana como tech lead.
- **Trabalho gerado/assistido:** Simplificação dos READMEs bilíngues, criação de checklists do Dia 5 em português e inglês, atualização do roteiro e registro dos papéis da equipe.
- **Orientações da equipe aplicadas:** Manter os briefings originais do curso intactos; identificar os status dos Dias 3 e 4 como históricos; deixar tarefas não verificadas desmarcadas; esclarecer que bancos H2 locais não sincronizam entre computadores; registrar Theo como líder da apresentação e Giovana como tech lead.
- **Validação:** Revisão de consistência dos links e dos papéis nos documentos. Como as alterações são apenas documentais, não foram executados testes de código.

### Roteiro da apresentação baseado no código

- **Ferramenta:** GitHub Copilot no VS Code.
- **Resumo do prompt:** Montar um roteiro de apresentação fiel ao código atual do ReservaCampus.
- **Trabalho gerado/assistido:** Roteiro falado de 15 minutos e check-in de 5 minutos, com falas sugeridas, sequência de demonstração e preparação do ambiente.
- **Revisão:** Conferidos o fluxo da interface, os endpoints e as regras de autenticação, reservas de sala, estoque de itens, cancelamento e pipeline. O roteiro não apresenta a IA como funcionalidade do produto nem sugere uma tentativa de excesso de estoque que a interface limita.
- **Validação:** Revisão documental baseada no código e nos testes existentes. Nenhum código foi alterado e nenhum teste foi executado nesta etapa.
