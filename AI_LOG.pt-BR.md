# Registro de uso de IA

[English](AI_LOG.md) · **Português (Brasil)**

As entradas descrevem o trabalho assistido por IA neste repositório. O conteúdo gerado foi revisado e comparado com o código existente; ele não comprova que ações externas da equipe ou do GitHub foram concluídas.

## 25 de novembro — Dia 3

### Implementação e validação do MVP

- **Ferramenta:** GitHub Copilot no VS Code.
- **Resumo do prompt:** Implementar um fluxo demonstrável de reserva de salas, preservar o login existente, manter as fontes de front-end e back-end separadas e seguir o checklist do Dia 3 e as orientações Markdown do repositório.
- **Trabalho gerado/assistido:** API e persistência de reservas, validação de propriedade e sobreposição, interações para criar/listar/editar/cancelar reservas, atualização de CI/testes e documentação do projeto.
- **Revisão e correções humanas:** Manter a stack Java/Spring Boot existente; preservar reservas canceladas em vez de apagá-las; verificar propriedade antes da edição; permitir horários adjacentes e rejeitar sobreposições; usar BCrypt e sessões no servidor; remover exemplos antigos da documentação em vez de tratá-los como fatos do projeto. Corrigido um caso de interface para permitir manter a sala de uma reserva existente caso ela seja marcada indisponível depois.
- **Validação:** Checkstyle Maven e JUnit foram executados localmente; o resultado verificado (10 testes) está em `DIA3_STATUS.pt-BR.md`. O status remoto do Actions é acompanhado separadamente e não é inferido a partir dos testes locais.

### Estrutura do repositório e documentação bilíngue

- **Ferramenta:** GitHub Copilot no VS Code.
- **Resumo do prompt:** Completar a documentação pendente do Dia 3 em inglês e português brasileiro e organizar o código-fonte em diretórios de front-end, back-end e documentação conforme a orientação já existente no projeto.
- **Trabalho gerado/assistido:** Atualização dos READMEs, registros de arquitetura/decisões, logs de IA, checklist de status e roteiro de apresentação/check-in; movimentação da aplicação Java para `/backend` e do briefing de apoio para `/docs`.
- **Revisão e correções humanas:** Manter os READMEs principais na raiz; não inventar responsáveis pela apresentação, links do board, acesso do professor, commits individuais ou resultados remotos da CI; marcar esses itens para confirmação da equipe/GitHub.
- **Validação:** As afirmações da documentação são conferidas com a árvore de arquivos e os resultados locais dos testes. As ações externas restantes aparecem explicitamente nos arquivos de status bilíngues.
