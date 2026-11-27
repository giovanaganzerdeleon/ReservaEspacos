# Checklist do Dia 5 — 27 de novembro de 2026

## Objetivo

Concluir e validar o fluxo que será apresentado, manter a branch principal estável e preparar a equipe para o congelamento de código no domingo, **29 de novembro, às 23h59**. O fim de semana deve ser reservado a correções pequenas e ensaio; não iniciar funcionalidades novas.

Marque um item somente depois de concluído e validado. Registre o responsável e a evidência quando necessário.

## 1. Fechar as pendências e o caminho da demonstração

- [ ] Executar o fluxo escolhido do início ao fim e repeti-lo para confirmar que é reproduzível.
- [ ] Confirmar quais funcionalidades entram na apresentação e quais ficam fora; registrar a decisão em `DECISIONS.pt-BR.md`.
- [ ] Confirmar que salas e itens demonstrativos estão disponíveis no computador da apresentação.
- [ ] Preparar a conta de demonstração nesse computador; não usar conta ou senha pessoal.
- [ ] Definir e ensaiar um plano B para falha de rede, servidor ou demonstração ao vivo.

## 2. Manter a branch principal estável

- [ ] Integrar apenas ajustes necessários à demonstração, por Pull Requests pequenos e revisados.
- [ ] Evitar funcionalidades novas e refatorações arriscadas.
- [ ] Usar Conventional Commits, por exemplo: `feat(reservas): validar disponibilidade`.
- [ ] Confirmar as contribuições de cada integrante sem criar commits artificiais ou em nome de outra pessoa.
- [ ] Definir e comunicar o horário interno do último merge, antes do congelamento oficial.

## 3. Testes e pipeline

- [ ] Executar uma compilação limpa, Checkstyle e testes no back-end.
- [ ] Antes de executar `clean`, parar a aplicação local. Em `backend/`, executar `.\mvnw.cmd -B clean checkstyle:check test`.
- [ ] Confirmar uma execução verde do GitHub Actions para as alterações que serão entregues.
- [ ] Confirmar que a pipeline executa em Pull Requests e em pushes para a branch principal.
- [ ] Não integrar mudanças com verificações falhando; corrigir a causa antes do merge.

## 4. Documentação e segurança

- [ ] Conferir que `README.md` e `README.pt-BR.md` explicam instalação, execução, stack e estrutura.
- [ ] Revisar as decisões de escopo e registrar claramente o que foi adiado e por quê.
- [ ] Atualizar os registros de IA com ferramenta, prompt ou resumo fiel, trabalho utilizado e revisões feitas pela equipe.
- [ ] Revisar os arquivos e o histórico Git em busca de segredos. Se uma credencial tiver sido exposta, revogá-la e substituí-la; apagar o arquivo atual, por si só, não remove o segredo do histórico.

## 5. Ensaio e check-in

- [ ] Fechar um roteiro que caiba em 15 minutos: problema, demonstração, fluxo de trabalho, decisões, uso de IA e pipeline.
- [ ] Dividir as falas e responsabilidades da demonstração entre a equipe.
- [ ] Fazer um ensaio cronometrado com toda a equipe.
- [ ] Testar a demonstração no ambiente real e preparar o plano B.
- [ ] Usar o check-in de 5 minutos para mostrar o fluxo funcionando e alinhar responsáveis, prazos e horário do último merge.

## Papéis da equipe

- **Liderança da apresentação:** Theo.
- **Tech lead:** Giovana.
- **Demais responsáveis pelas tarefas e falas:** definir com a equipe.

## Registro de acompanhamento

| Item ou pendência | Responsável | Prazo interno | Evidência / observação |
|---|---|---|---|
|  |  |  |  |

O status detalhado do Dia 4 foi mantido como registro histórico em [`DIA4_STATUS.pt-BR.md`](DIA4_STATUS.pt-BR.md). Verifique as tarefas acima antes de considerar qualquer pendência concluída.
