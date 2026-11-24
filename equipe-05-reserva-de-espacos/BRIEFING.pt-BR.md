# Briefing do tema: Reserva de espaços

[English](BRIEFING.md) · **Português (Brasil)**

**Hack com IA: Codando com Inteligência**  
Senac na Prática · Técnico em Desenvolvimento de Sistemas  
Área: vida no campus e comunidade  
Imersão de 23/11 a 29/11 · apresentações em 30/11 e 01/12

Este briefing é o ponto de partida do **seu** tema. Leiam com calma, recortem um MVP que caiba em uma semana e registrem as decisões. Vocês vão transformar este problema em um produto real, com front-end e back-end, repositório organizado e uso documentado de IA. Esta é uma imersão colaborativa: a banca avalia o que o time construiu, decidiu e aprendeu.

## Tema e contexto

**Reserva de espaços** — Sistema para agendar salas, laboratórios e equipamentos, com controle de conflitos.

Salas, laboratórios e equipamentos do campus são disputados no cara a cara, no grupo da turma ou em uma planilha que ninguém atualiza. O resultado clássico é o conflito: dois times no mesmo laboratório, um projetor “reservado” que já estava comprometido, ou um espaço aparentemente livre que na verdade não está. Falta visibilidade do que está ocupado e um jeito objetivo de pedir, confirmar e cancelar, respeitando capacidade e horário.

Vocês vão construir um fluxo de reserva para a vida no campus, em que o conflito é evitado — ou resolvido com uma alternativa — antes de o time aparecer na porta da sala.

## Público e personas

- **Estudante ou time que precisa de um espaço ou equipamento:** quer ver a disponibilidade e concluir um pedido em poucos passos.
- **Quem concede ou acompanha as reservas (papel de equipe ou professor no MVP):** precisa de uma agenda clara e da possibilidade de recusar ou ajustar um pedido, se o recorte incluir isso.
- **Quem chega no horário:** depende de a reserva ter valido de verdade, sem sobreposição.

## Objetivo

Entregar um MVP em que dá para ver a agenda de salas, laboratórios e equipamentos, criar uma reserva com horário, impedir o conflito de sobreposição e cancelar ou alterar o pedido — com a IA ajudando a encontrar horários alternativos.

## Requisitos mínimos (MVP)

O recorte abaixo é realista para uma semana. Fechem-no no primeiro dia e registrem no `DECISIONS.md` o que entra, o que fica de fora e por quê. É melhor um fluxo completo e demonstrável do que uma lista longa pela metade.

1. Um catálogo simples de recursos (sala, laboratório ou equipamento), com nome, tipo e, quando fizer sentido, capacidade.
2. Uma visão de disponibilidade por dia (agenda ou lista de faixas horárias).
3. Criar uma reserva com recurso, data, início, fim e identificação de quem pede.
4. Detectar e impedir sobreposição no mesmo recurso e horário (o coração do MVP).
5. Cancelar ou editar uma reserva, recalculando o conflito.
6. Listar “minhas reservas” e o status de cada uma.

## Onde a IA entra

O uso de IA é liberado e incentivado — no produto e no processo — e precisa aparecer no `AI_LOG.md`. Algumas frentes concretas neste tema:

- Sugerir horários alternativos quando o pedido inicial conflitar.
- Interpretar um pedido em linguagem natural (“laboratório amanhã das 14h às 16h”) e montar a reserva para a pessoa confirmar.
- Ajudar a escolher um recurso equivalente (outra sala com capacidade parecida) se o preferido estiver ocupado.
- Resumir a agenda do dia de um laboratório em poucas linhas.

Não publiquem, sem revisão de vocês, um texto ou uma decisão automática da IA como se já fosse a versão final.

## Extras opcionais

Só depois que o MVP estiver em pé. São ideias, não obrigações:

- Um passo de aprovação para certos recursos.
- Reservas recorrentes (toda quinta, no mesmo horário).
- Tempo de intervalo obrigatório entre duas reservas.
- Vincular um equipamento a uma sala (reservar os dois juntos).

## Fora de escopo

Para proteger o prazo e o foco do produto:

- Integração com o sistema acadêmico oficial do Senac.
- Controle de fechaduras, crachás ou acesso físico às salas.
- Cobrança ou faturamento pelo uso do espaço.
- Uma grade completa de todas as aulas da instituição — o recorte é a reserva pontual de recursos do campus.

## Perguntas para guiar o time

Usem estas perguntas no primeiro dia, ao definir o MVP, e de novo antes da demo:

- Quem pode reservar o quê, no recorte do MVP? Tudo é livre, ou alguns recursos pedem um perfil específico?
- O que acontece se duas solicitações chegarem quase ao mesmo tempo para o mesmo horário?
- Há um intervalo mínimo entre o fim de uma reserva e o início da seguinte?
- Como a interface mostra por que um horário não está disponível?
- Uma reserva pode ultrapassar o horário de funcionamento do campus? Se não, onde isso é validado?

## Entregáveis e como a banca avalia

Esta é uma **imersão colaborativa** de uma semana, com times de 3 ou 4 estudantes do TDS. A banca avalia o trabalho de **vocês**, no próprio projeto — um retorno formativo, não uma comparação com outros times.

**O que precisa estar pronto no repositório** (raiz), em **inglês** (versão principal) e em **português** (`.pt-BR`), com o link entre os idiomas no topo de cada arquivo:

- `README.md` e `README.pt-BR.md`
- `DECISIONS.md` e `DECISIONS.pt-BR.md`
- `AI_LOG.md` e `AI_LOG.pt-BR.md`

A aplicação precisa ter **front-end e back-end**. O back-end pode ser **Java**, **Python** ou **Node.js** (isolados ou combinados, desde que integrados). O front-end é obrigatório (web, desktop ou mobile). Justifiquem a stack no `DECISIONS.md`. Também são obrigatórios o histórico com Conventional Commits, o trabalho via Pull Request com revisão, a proteção da `main` e uma pipeline de **GitHub Actions** verde (build, lint e testes) em PRs e na `main`. Não commitem segredos. No primeiro dia, adicionem o professor como colaborador: **adrianoanschau@gmail.com**.

O **congelamento do código** é no domingo, **29/11, às 23h59**. As apresentações são em **15 minutos + 5 minutos de perguntas**, nos dias 30/11 e 01/12, no Senac de Canoas, Sala 103. Ensaiem a demo e o roteiro (problema e solução, demo ao vivo, fluxo de trabalho, decisões, IA na prática e CI/CD).

A avaliação tem **duas bancas, com o mesmo peso**:

- **Banca Técnica (50%):** qualidade de código e arquitetura; Git, Conventional Commits e PRs; CI/CD; domínio do código nas perguntas.
- **Banca de Inovação e Impacto (50%):** originalidade; impacto (o problema real e para quem); experiência do usuário; clareza do pitch; uso criativo da IA.

Qualquer pessoa do time pode ser perguntada sobre qualquer parte do projeto. Usem a IA com inteligência, registrem o que ela gerou e o que vocês corrigiram, e assumam o código que forem apresentar.

Bom trabalho — e que a semana seja intensa, colaborativa e bem documentada.

