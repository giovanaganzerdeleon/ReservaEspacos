# Decisões do projeto

[English](DECISIONS.md) · **Português (Brasil)**

Este arquivo registra as escolhas atuais de implementação do MVP. A equipe deve revisá-las e confirmá-las no check-in do projeto.

## 25 de novembro — Dia 3

### Manter o back-end Java/Spring Boot existente

- **Decisão:** Continuar a aplicação existente em Java 21 e Spring Boot.
- **Alternativas consideradas:** Recriar a API com outro framework de servidor ou usar um serviço de back-end hospedado.
- **Motivo:** O repositório já continha a aplicação Java e a API de salas; continuar evita duplicar trabalho e mantém o MVP no projeto existente da equipe.
- **Consequência:** O projeto exige runtime Java 21 e ferramentas de build Maven.

### Separar os diretórios-fonte de front-end e back-end

- **Decisão:** Manter o código-fonte da interface em `/frontend`, a aplicação Spring em `/backend` e os briefings/roteiros em `/docs`.
- **Alternativas consideradas:** Colocar os arquivos estáticos no classpath Java ou criar um front-end com framework e build separado.
- **Motivo:** Os diretórios separados deixam claras as responsabilidades sem introduzir gerenciador de pacotes ou etapa extra de build para um MVP pequeno. No desenvolvimento local, o Spring serve os arquivos do front-end para que interface e API compartilhem a origem.
- **Consequência:** O front-end não usa framework e ainda não tem suíte de testes automatizados própria.

### Usar sessões no servidor para autenticação

- **Decisão:** Armazenar senhas com hash BCrypt e usar uma sessão HTTP-only no servidor para o usuário autenticado.
- **Alternativas consideradas:** Tokens JWT guardados pelo navegador ou provedor de identidade gerenciado.
- **Motivo:** Navegador e API são servidos pela mesma origem local; um cookie de sessão é suficiente e evita manter credenciais bearer no armazenamento do navegador.
- **Consequência:** O desenvolvimento local usa a sessão da aplicação; deploy de produção e operações de sessão ainda não foram configurados.

### Incluir itens compartilhados do campus com reserva limitada pelo estoque

- **Decisão:** Adicionar uma página **Itens do campus** onde usuários autenticados podem criar, consultar, editar ou cancelar reservas de uma quantidade de itens do estoque, incluindo projetores, notebooks, kits de laboratório, lousas e kits de canetões. Reservas ativas do mesmo item podem se sobrepor somente enquanto a quantidade combinada não ultrapassar o estoque; horários adjacentes não se sobrepõem.
- **Alternativas consideradas:** Reservar uma unidade física por pedido ou permitir reserva de equipamento somente junto com uma sala.
- **Motivo:** Estoque compartilhado representa melhor materiais como kits de canetões e permite demonstrar equipamentos e materiais básicos de sala.
- **Consequência:** As quantidades iniciais são ilustrativas, não correspondem a um inventário Senac verificado. Recorrência, aprovação e sincronização com um sistema oficial de estoque continuam fora do escopo. Reservas canceladas são mantidas como registros cancelados.

### Executar as mesmas verificações localmente e na CI

- **Decisão:** Usar Maven Wrapper, Checkstyle e JUnit; executá-los no GitHub Actions em Pull Requests e pushes para `master` e `main`.
- **Alternativas consideradas:** Executar somente o empacotamento ou depender de verificações manuais locais.
- **Motivo:** Lint e testes automatizados detectam regressões do back-end de forma consistente.
- **Consequência:** Só será possível confirmar uma execução remota verde depois que o workflow for enviado e executado no GitHub Actions.

### Proteger a `main` e entregar alterações por Pull Requests revisados

- **Decisão:** Manter a `main` protegida e exigir Pull Requests para alterações no repositório.
- **Motivo:** A restrição torna visíveis as mudanças da branch e mantém builds, revisão, lint e testes no fluxo de contribuição.
- **Consequência:** Não será permitido enviar alterações diretamente para `main`; o trabalho deve ficar em uma branch e passar por revisão antes do merge.

## 26 de novembro — Dia 4

### Preparar catálogo demonstrativo sem substituir dados existentes

- **Decisão:** Ao iniciar a aplicação, cadastrar quatro salas e os itens demonstrativos existentes somente quando a respectiva tabela estiver vazia.
- **Motivo:** A equipe pode demonstrar o catálogo sem cadastrar recursos um por um, e os dados já existentes não são sobrescritos.
- **Consequência:** Nomes e estoques são ilustrativos, não um inventário oficial. O seed não cria usuário nem senha padrão; a conta de demonstração deve ser criada pela tela de cadastro.

### Priorizar a estabilidade do caminho da demo antes de novas funcionalidades

- **Decisão proposta para confirmação da equipe:** Congelar o escopo da apresentação em autenticação e reservas; demonstrar reserva de item com limite de estoque e incluir reservas de sala somente se o fluxo estiver estável.
- **Adiado para depois do MVP:** Sugestões de horário com IA, interpretação de pedidos em linguagem natural, recorrência, aprovações, integração com inventário oficial e deploy público.
- **Motivo:** O checklist do Dia 4 prioriza um caminho feliz funcional, repetível e coberto por testes, em vez de ampliar a interface antes da demo.
- **Estado:** A equipe ainda precisa confirmar o escopo final e o plano B; até lá, tratar esta seção como proposta, não como decisão coletiva concluída.

## 27 de novembro — Dia 5

### Papéis de liderança da equipe

- **Liderança da apresentação:** Theo coordena o roteiro e o ensaio.
- **Tech lead:** Giovana conduz as decisões e a preparação técnica.
- **Observação:** os demais apresentadores e responsáveis por tarefas devem ser definidos pela equipe.
