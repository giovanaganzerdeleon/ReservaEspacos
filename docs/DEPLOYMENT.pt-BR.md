# Hospedar uma versão de demonstração

[English](DEPLOYMENT.md) · **Português (Brasil)**

## Deploy no Railway (caminho preparado)

O computador de apresentação precisa apenas de um navegador e acesso à internet. Java, Maven e o servidor ficam na infraestrutura do Railway. O computador não precisa de Java, Maven, PostgreSQL, VS Code ou Docker.

O repositório inclui um `Dockerfile` na raiz. O Railway usa esse arquivo para compilar o projeto com Java 21 e iniciar o JAR; o `.dockerignore` impede que o banco H2 local e arquivos de ambiente sejam enviados no contexto do build.

1. Envie as alterações do projeto para o GitHub.
2. No Railway, crie um projeto e escolha **Deploy from GitHub repo**. Conecte o repositório `ReservaEspacos`. Mantenha o diretório raiz do serviço como raiz do repositório: o `Dockerfile` precisa acessar `backend/` e `frontend/`.
3. No mesmo projeto, adicione um serviço PostgreSQL em **New → Database → PostgreSQL**.
4. No serviço da aplicação, abra **Variables** e cadastre:

   ```text
   SPRING_PROFILES_ACTIVE=prod
   SPRING_DATASOURCE_URL=jdbc:postgresql://${{Postgres.PGHOST}}:${{Postgres.PGPORT}}/${{Postgres.PGDATABASE}}
   SPRING_DATASOURCE_USERNAME=${{Postgres.PGUSER}}
   SPRING_DATASOURCE_PASSWORD=${{Postgres.PGPASSWORD}}
   ```

   Se o serviço do banco tiver outro nome, substitua `Postgres` pelo nome exibido no Railway. Use as referências de variáveis/autocomplete do painel; não copie senhas para o repositório. A conexão entre os serviços deve usar as variáveis privadas do PostgreSQL, sem habilitar acesso público ao banco. O Railway fornece `PORT` para a aplicação.
5. Aguarde o build e o deploy. O Railway detecta o `Dockerfile` na raiz; não é necessário configurar comandos separados de build ou inicialização.
6. No serviço da aplicação, acesse **Settings → Networking → Generate Domain** para obter uma URL pública HTTPS.
7. Abra a URL e teste cadastro, login, reservas, edição e cancelamento. O seed cria salas e itens de exemplo quando as tabelas estão vazias, mas não cria usuário; cadastre uma conta de demonstração.

O serviço PostgreSQL é separado e persistente. O computador de apresentação acessa somente a aplicação pela URL; não precisa executar banco nem expor uma porta local.

**Custos:** confira os [planos e preços do Railway](https://railway.com/pricing) antes de criar os serviços. Créditos e limites podem mudar; a hospedagem do PostgreSQL pode exigir um plano pago. Em planos com suspensão por inatividade, a primeira abertura da demo pode levar mais tempo.

Documentação oficial: [Spring Boot no Railway](https://railway.com/guides/spring-boot), [PostgreSQL no Railway](https://docs.railway.com/databases/postgresql) e [referências de variáveis](https://docs.railway.com/variables).

## Outros hosts Java

Se outro provedor for escolhido, ele precisa oferecer Java 21 e PostgreSQL persistente. Configure `SPRING_PROFILES_ACTIVE=prod`, as três variáveis `SPRING_DATASOURCE_*` com os dados JDBC do banco e o `PORT` fornecido pelo host. O JAR gerado em `backend/target/` já inclui os arquivos de `frontend/`; o comando de inicialização é:

```sh
java -jar backend/target/reservacampus-backend-1.0-SNAPSHOT.jar
```

## Depois do deploy

- Teste a URL no computador que será usado na apresentação.
- Crie uma conta de demonstração e execute login, reserva, edição e cancelamento.
- Confirme que salas e itens de exemplo foram carregados. O seed só insere dados quando as tabelas estão vazias e não cria uma conta.
- Use somente dados fictícios na demonstração.
- Confira os logs de inicialização e o estado do banco no painel do provedor.

## Limites importantes

O perfil de hospedagem é uma base para demonstração e validação do MVP, não uma certificação de prontidão para produção. O banco inicia com `ddl-auto=update`; para dados reais, adote migrações de esquema revisadas e backups. O console do H2 fica desativado nesse perfil e a conexão de banco deve usar credenciais fornecidas pelo host.

Antes de abrir o sistema para uso real, revisem proteção contra CSRF, limitação de tentativas de login/cadastro, controle de permissões administrativas e política de dados. Atualmente, qualquer usuário autenticado pode gerenciar cadastros de salas e adicionar itens; não há papel administrativo separado.

O projeto ainda não está publicado. O endereço HTTPS só existirá depois que alguém conectar o repositório, configurar os serviços e concluir o deploy no Railway.
