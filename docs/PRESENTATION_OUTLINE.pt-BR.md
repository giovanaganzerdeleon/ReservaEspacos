# Roteiro da apresentação de 15 minutos e check-in de 5 minutos

[English](PRESENTATION_OUTLINE.md) · **Português (Brasil)**

É um rascunho. A equipe precisa definir os apresentadores e ensaiar; nenhum nome foi presumido neste roteiro.

## Apresentação de 15 minutos

| Tempo | Bloco | Conteúdo sugerido | Apresentador |
|---|---|---|---|
| 0:00–1:30 | Problema e público | Conflitos de reserva no campus; quem precisa da ferramenta e o que o MVP resolve. | A definir |
| 1:30–3:00 | Produto e arquitetura | Separação de `/frontend` e `/backend`; fluxo navegador/API REST e H2 local. | A definir |
| 3:00–8:00 | Demo ao vivo | Entrar; mostrar salas e a aba de itens do campus; reservar kits de canetões ou um projetor; tentar reservar acima do estoque restante e mostrar a rejeição; editar, listar e cancelar. | A definir |
| 8:00–9:30 | Fluxo de trabalho | Mostrar Pull Request revisado, histórico de Conventional Commits e workflow/execução do Actions. Não afirmar que está verde até existir uma execução. | A definir |
| 9:30–11:00 | Decisões | Explicar Java/Spring, diretórios separados, sessões e por que recorrência/equipamentos/aprovações estão fora do MVP. | A definir |
| 11:00–13:00 | Uso de IA | Mostrar o log de IA; descrever o que foi gerado e o que a equipe revisou ou recusou. | A definir |
| 13:00–15:00 | Status e próximos passos | Demonstrar testes locais, informar o status real da pipeline remota, pendências e plano até o congelamento. | A definir |

### Preparação da demo

- Criar uma conta de demonstração antes da apresentação; não usar senha de usuário real nem expor o arquivo H2 local.
- Usar uma data futura e um intervalo claro, por exemplo, 09:00–10:00; reservar uma quantidade de itens e depois tentar uma reserva sobreposta que ultrapasse o estoque restante.
- Explicar que as quantidades iniciais são demonstrativas e devem ser substituídas por um inventário do campus verificado antes do uso real.
- Confirmar edição, listagem e cancelamento antes da demo ao vivo. Se a verificação de reservas em execução não estiver concluída, mostrar um resultado local claramente identificado como teste, sem afirmar que o fluxo da interface foi verificado.
- Conferir que somente uma instância local está usando o arquivo H2 e que o navegador está em `http://127.0.0.1:8080`.

## Check-in de 5 minutos com o professor

| Tempo | Conteúdo |
|---|---|
| 0:00–0:30 | Apresentar o problema de reserva de salas no campus e os limites do MVP. |
| 0:30–3:00 | Mostrar login e o fluxo de reserva/conflito de sala ou de estoque de itens, se a verificação ao vivo tiver passado. |
| 3:00–4:00 | Mostrar resultados locais de testes/Checkstyle e o status real do GitHub Actions. |
| 4:00–5:00 | Resumir o que foi feito, próximos passos e bloqueios; pedir feedback. |

**Antes do check-in:** definir quem apresentará, enviar o workflow e informar o status remoto da CI com precisão. Se o Actions ainda não tiver executado, dizer isso e mostrar somente resultados locais verificados.
