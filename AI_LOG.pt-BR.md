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
- **Resumo do prompt:** Investigar o erro “Failed to fetch” no cadastro/login e incorporar o checklist do Dia 4 à documentação do projeto.
- **Trabalho gerado/assistido:** Confirmada a ausência de um processo escutando em `127.0.0.1:8080`; esclarecido que a branch do GitHub não hospeda a API; adicionadas mensagens mais acionáveis no front-end e no terminal, além do iniciador `iniciar.bat`; preparados status do Dia 4 em português/inglês e proposta de escopo para a demo.
- **Revisão e correções humanas:** Manter a API e o front-end na mesma origem no modo local; não expor o servidor local à rede nem afirmar que há deploy público. O cadastro ao vivo permanece não verificado até o back-end ser iniciado.
- **Validação:** compilação limpa, Checkstyle (0 violações) e suíte completa (17 testes) passaram. `git diff --check` passou. A inicialização real por `iniciar.bat`, o fluxo HTTP/navegador e a pipeline para as alterações atuais ainda precisam ser verificados; `node --check` não estava disponível porque Node.js não está instalado neste ambiente.
