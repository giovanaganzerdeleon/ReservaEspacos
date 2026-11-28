# Roteiro da apresentação — ReservaCampus

[English](PRESENTATION_OUTLINE.md) · **Português (Brasil)**

Apresentação de **15 minutos**, seguida de **5 minutos para perguntas**. Este roteiro descreve o que o código atual faz; ajustem as falas aos testes e à execução que forem realmente demonstrados.

## Papéis

- **Theo — líder da apresentação:** conduz a narrativa, controla o tempo e faz as transições.
- **Giovana — tech lead:** explica a arquitetura, as regras do back-end e os testes.
- **Fábio:** conduz a navegação e as ações da demonstração ao vivo.

Esta é uma divisão sugerida: Theo apresenta e conecta os blocos; Fábio opera a interface; Giovana complementa com as explicações técnicas. Ensaiem as transições entre os três.

## Antes de apresentar

- Se o deploy estiver concluído, abram a URL HTTPS do host no computador da apresentação; nesse computador não é necessário instalar Java ou Maven.
- Se ainda não houver deploy, a alternativa local exige Java 21 no computador que executa o back-end e acesso a `http://127.0.0.1:8080`.
- Criem e testem uma conta de demonstração nesse computador. O seed não cria conta nem senha padrão.
- Confirmem que o banco local tem as salas e os itens de exemplo. O seed só popula cada catálogo quando a tabela correspondente está vazia; ele não substitui dados existentes.
- Escolham uma data futura e confirmem o estoque disponível para o período. O exemplo inicial inclui 10 kits de canetões.
- Ensaie edição e cancelamento antes de apresentar. Se algum passo não estiver validado na interface, não o descrevam como demonstrado ao vivo.
- Verifiquem o resultado mais recente do GitHub Actions e o commit correspondente. Não apresentem uma execução antiga como validação das alterações atuais.

## Apresentação de 15 minutos

| Tempo | Bloco | Condução sugerida |
|---|---|---|
| 0:00–1:30 | Problema e proposta | Theo apresenta o problema e o objetivo do ReservaCampus. |
| 1:30–3:00 | Produto e arquitetura | Giovana explica a interface, API, autenticação e banco local. |
| 3:00–8:00 | Demonstração | Fábio opera o sistema; Theo narra o fluxo; Giovana explica as regras técnicas. |
| 8:00–9:30 | Testes e pipeline | Giovana mostra resultados verificáveis de testes e CI. |
| 9:30–11:00 | Decisões e limites | Theo explica o recorte do MVP e o que ficou para depois. |
| 11:00–13:00 | Uso de IA | Fábio apresenta o registro coletivo de uso e revisão de IA. |
| 13:00–15:00 | Fechamento | Theo resume o valor, o estado atual e os próximos passos. |

### 0:00–1:30 — Abertura: problema e proposta

**Fala sugerida — Theo**

> Boa tarde. Nós somos a equipe do ReservaCampus. Nosso projeto parte de um problema simples: encontrar salas e materiais disponíveis no campus pode depender de conversas ou planilhas, e isso facilita conflitos de horário.
>
> Criamos um MVP para consultar salas e itens, fazer reservas e acompanhar as próprias solicitações. O foco foi construir um fluxo claro e impedir conflitos, em vez de tentar incluir todas as possibilidades de um sistema completo.

### 1:30–3:00 — Produto e arquitetura

**Fala sugerida — Giovana**

> A interface foi feita com HTML, CSS e JavaScript, sem framework, em `frontend/`. O back-end usa Java 21 e Spring Boot, e expõe uma API REST. Durante a execução local, o Spring também serve a interface, então o navegador e a API usam a mesma origem.
>
> O cadastro e o login criam uma sessão no servidor. As senhas são armazenadas com hash BCrypt. No desenvolvimento local, usamos H2. Para hospedar a demonstração, preparamos um perfil que usa PostgreSQL; nesse caso, as pessoas acessam a mesma aplicação e o mesmo banco pela URL do host.

**Transição para a demo — Theo**

> Agora vamos mostrar o caminho principal funcionando na aplicação.

### 3:00–8:00 — Demonstração ao vivo

**1. Entrar — Fábio opera; Theo narra**

> Vamos entrar com a conta de demonstração. Depois da autenticação, o sistema carrega as áreas de salas e itens do campus.

**2. Mostrar o catálogo — Fábio**

Mostre brevemente a lista de salas e a aba **Itens do campus**. Aponte que os dados iniciais são demonstrativos, não um inventário oficial.

**3. Reservar itens — Fábio opera; Theo narra; Giovana explica a regra**

> Vamos escolher uma data futura e um intervalo de horário. O sistema consulta a disponibilidade daquele período, não apenas o estoque total.

Selecione o **Kit de canetões (4 cores)**, escolha 09:00–10:00 e reserve 6 unidades. Mostre a reserva em **Minhas reservas de itens**. Como o formulário é limpo após salvar, informe novamente a mesma data e o mesmo horário para consultar a disponibilidade atualizada desse período.

> O catálogo tem 10 unidades demonstrativas. Com 6 reservadas nesse intervalo, a tela informa que restam 4 disponíveis. A quantidade oferecida no formulário acompanha essa disponibilidade.

**4. Editar e cancelar — Fábio opera; Theo narra**

Edite a reserva, reduza a quantidade para 5 e salve. Mostre a atualização na lista. Em seguida, cancele a reserva e mostre que ela passa a constar como cancelada.

> A reserva pertence à conta autenticada. O back-end verifica a propriedade antes de permitir alterações, e reservas canceladas são mantidas com o status correspondente.

**Importante:** a interface limita a quantidade ao saldo mostrado. Não tentem forçar uma quantidade acima do estoque para simular uma falha. Expliquem a regra e, se necessário, mostrem o teste automatizado correspondente em vez de alegar que essa tentativa foi feita na interface.

**Se o fluxo de salas também tiver sido ensaiado:** use o tempo restante para criar ou consultar uma reserva de sala e mostrar a lista **Minhas reservas**. Não adicionem esse passo se ele não estiver estável.

### 8:00–9:30 — Testes e pipeline

**Fala sugerida — Giovana**

> O back-end tem testes automatizados para regras como autenticação, conflito de reservas de sala e disponibilidade de estoque. Também usamos Checkstyle para padronizar o código.
>
> Esta é a execução do GitHub Actions para o commit [identificar o commit na tela]. Ela executa build, Checkstyle e testes em Pull Requests e em pushes para `main` e `master`.

Mostre apenas resultados que tenham sido realmente executados. Se a execução exibida não incluir as alterações mais recentes, diga isso claramente e diferencie o resultado local do resultado remoto.

### 9:30–11:00 — Decisões e limites do MVP

**Fala sugerida — Theo**

> Priorizamos um MVP pequeno que pudesse ser demonstrado: catálogo, autenticação e reservas de salas e itens. Para salas, o sistema impede sobreposição de horários. Para itens, calcula o estoque disponível considerando reservas no mesmo período.
>
> Recorrência, aprovação de reservas, integração com o inventário oficial e sugestões de horário por IA ficaram fora do escopo. As quantidades de exemplo servem para demonstração e precisam ser substituídas por dados verificados antes de qualquer uso operacional.

### 11:00–13:00 — Uso de IA

**Fala sugerida — Fábio**

> Usamos ferramentas de IA como apoio ao desenvolvimento e à documentação. A equipe revisou as sugestões, comparou mudanças com o código e corrigiu ou descartou o que não atendia às regras do projeto.
>
> A IA não é uma funcionalidade integrada ao produto atual. As sugestões automáticas de horários ficaram fora do MVP. Registramos o uso e as validações no `AI_LOG.md` e no `AI_LOG.pt-BR.md`.

Mostre um exemplo concreto do registro de IA e explique brevemente o que a equipe aceitou e revisou. Fábio apresenta o uso coletivo; não atribua a ele contribuições individuais que não tenham sido confirmadas.

### 13:00–15:00 — Fechamento

**Fala sugerida — Theo**

> O principal resultado é um fluxo de reserva que permite consultar recursos e administrar as próprias reservas, com regras para evitar conflitos de sala e excesso de reservas de itens.
>
> Este ainda é um MVP, sem integração com inventário oficial. Nosso próximo passo é finalizar a validação do fluxo, conferir a pipeline para o commit entregue e manter o escopo estável.
>
> Obrigado. Estamos prontos para as perguntas.

**Ajustem esta informação conforme o status no dia:** se a demo estiver hospedada, digam que está disponível pela URL e usa dados de demonstração; caso contrário, informem que o acesso por URL pública continua pendente.

## Check-in de 5 minutos com o professor

| Tempo | O que mostrar |
|---|---|
| 0:00–0:30 | Theo resume o problema, a proposta e o limite do MVP. |
| 0:30–3:00 | Fábio opera login e reserva de itens; Giovana explica as regras técnicas. Inclua salas somente se o fluxo estiver validado. |
| 3:00–4:00 | Mostrar testes locais e o status real do GitHub Actions, identificando o commit. |
| 4:00–5:00 | Theo apresenta pendências, plano até o congelamento e solicita feedback. |

**Princípio para toda a apresentação:** relatem somente o que foi verificado. Se algo não foi testado ao vivo ou pela pipeline atual, identifiquem como pendência.
