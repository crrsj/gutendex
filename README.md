
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

🤝 Créditos
Desenvolvido por Carlos Roberto Ribeiro Santos Junior.
Projeto integrante da formação Oracle Next Education.```

  

   <img width="1920" height="1080" alt="gut1" src="https://github.com/user-attachments/assets/2b4412cc-d4e5-40dc-a98a-54d9610df85d" />

   <img width="1920" height="1080" alt="gut2" src="https://github.com/user-attachments/assets/c1f2483a-b983-4bed-8df0-dbec1a4ebda0" />

   <img width="1920" height="1080" alt="gut3" src="https://github.com/user-attachments/assets/4705e2a4-2769-4710-86be-5c645a0799f1" />

   <img width="960" height="540" alt="gut4" src="https://github.com/user-attachments/assets/84b1225e-4ab6-41dd-9696-51dcfeabef01" />

   <img width="1920" height="1080" alt="gut5" src="https://github.com/user-attachments/assets/427c8a6b-93cf-47dc-b363-2abb333eecd3" />




