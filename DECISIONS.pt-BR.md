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

### Manter o fluxo de reserva focado

- **Decisão:** Um usuário autenticado pode criar, consultar, editar ou cancelar as próprias reservas de sala. Reservas ativas não podem se sobrepor para a mesma sala; intervalos adjacentes são permitidos.
- **Alternativas consideradas:** Incluir recorrência, equipamentos, aprovações ou sugestão automática de horários alternativos no escopo do Dia 3.
- **Motivo:** O ciclo de vida da reserva e a detecção de conflito são a menor extensão demonstrável de ponta a ponta do catálogo existente.
- **Consequência:** Reservas canceladas são mantidas como registros cancelados; o MVP não inclui recorrência nem aprovação administrativa.

### Executar as mesmas verificações localmente e na CI

- **Decisão:** Usar Maven Wrapper, Checkstyle e JUnit; executá-los no GitHub Actions em Pull Requests e pushes para `master` e `main`.
- **Alternativas consideradas:** Executar somente o empacotamento ou depender de verificações manuais locais.
- **Motivo:** Lint e testes automatizados detectam regressões do back-end de forma consistente.
- **Consequência:** Só será possível confirmar uma execução remota verde depois que o workflow for enviado e executado no GitHub Actions.
