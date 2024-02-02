# SeniorPDV

## Descrição

O SeniorPDV é um projeto demostrativo desenvolvido com Spring Boot, destinado a fornecer uma solução simples para gerenciamento de ponto de venda (PDV). Utilizando as melhores práticas e tecnologias modernas, o projeto oferece uma base sólida para a construção de aplicações de PDV robustas e escaláveis.

### Tecnologias Utilizadas

- **Spring Boot** 2.3.12.RELEASE: Facilita a criação de aplicações stand-alone baseadas em Spring com configurações mínimas.
- **Java** 11: Linguagem de programação utilizada.
- **Spring Data JPA**: Para persistência de dados de forma simplificada.
- **H2 Database**: Banco de dados em memória para testes rápidos e desenvolvimento.
- **PostgreSQL**: Banco de dados recomendado para ambientes de produção.
- **Spring Boot DevTools**: Para desenvolvimento rápido com reinicialização automática.
- **Spring Boot Starter Test**: Para testes unitários e de integração.

## Como Executar

### Pré-requisitos

- JDK 11 ou superior
- Maven 3.6 ou superior

### Instruções

1. Clone o repositório:

```bash
git clone https://github.com/seuusuario/seniorpdv.git
cd seniorpdv

./mvnw spring-boot:run

## Testando com Postman

Para facilitar os testes das funcionalidades da aplicação, disponibilizamos uma coleção do Postman com todos os endpoints configurados.

### Como Usar

1. Baixe e instale o [Postman](https://www.postman.com/downloads/) se ainda não o tiver feito.
2. Importe a coleção do Postman para a sua instância do Postman. A coleção pode ser encontrada no diretório raiz do projeto, nomeada como `SeniorPDV_Postman_Collection.json`.
3. Certifique-se de que a aplicação `seniorpdv` está rodando localmente.
4. Utilize as requisições pré-configuradas na coleção do Postman para testar os diferentes endpoints da aplicação.

Essa coleção do Postman inclui exemplos de requisições para todas as operações CRUD disponíveis no `seniorpdv`, facilitando a compreensão e teste da API.


