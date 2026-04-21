# 📚 Consumindo API Gutendex com Java 21 & Spring Boot

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)

Este projeto foi desenvolvido como parte do programa **Oracle Next Education (ONE)** em parceria com a **Alura**. A aplicação demonstra a integração entre um ecossistema Backend Java e a API externa [Gutendex](https://gutendex.com/), permitindo a busca, persistência e gestão de metadados de livros de domínio público.

## 🚀 Tecnologias e Ferramentas

- **Linguagem:** Java 21 (LTS)
- **Framework:** Spring Boot 3.x
- **Persistência:** Spring Data JPA / Hibernate
- **Banco de Dados:** H2 Database (ou MySQL/PostgreSQL configurável)
- **Comunicação HTTP:** RestTemplate
- **Documentação:** Swagger UI / OpenAPI 3.0
- **Segurança e Integridade:** Versão otimista com `@Version` e validação de integridade.

## 🛠️ Arquitetura e Boas Práticas

A aplicação foi estruturada seguindo princípios fundamentais de engenharia de software:

- **Separação de Camadas:** Divisão clara entre `Controller`, `Service`, `Repository` e `Entity` para garantir a manutenção do código.
- **Clean Code:** Métodos curtos, nomes significativos e uso de constantes para configurações globais.
- **Solid (SRP):** Responsabilidades isoladas na camada de serviço, garantindo que o controlador se preocupe apenas com as requisições HTTP.
- **Tratamento de Exceções:** Gestão de erros de integridade de dados para evitar inconsistências no banco.
- **Documentação Automática:** Endpoint `/swagger-ui.html` configurado para facilitar o teste das rotas.

## 📋 Funcionalidades

1.  **Fetch & Save:** Busca um livro pelo ID diretamente na API Gutendex e salva no banco de dados local. Caso o livro já exista, os dados são atualizados automaticamente.
2.  **Listagem Completa:** Retorna todos os livros previamente catalogados.
3.  **Busca por ID:** Consulta detalhada de um livro específico no repositório local.
4.  **Interface Web:** Front-end responsivo construído com Bootstrap para interação direta com a API.

## ⚙️ Como Executar

### Pré-requisitos
- Java 21 instalado.
- Maven 3.x.




### Instalação
1. Clone o repositório:
   ```bash
   git clone [https://github.com/seu-usuario/api-gutendex.git](https://github.com/seu-usuario/api-gutendex.git)

  ``

   <img width="1920" height="1080" alt="gut1" src="https://github.com/user-attachments/assets/2b4412cc-d4e5-40dc-a98a-54d9610df85d" />
