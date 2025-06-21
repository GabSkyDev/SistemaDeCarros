# Sistema de Gerenciamento de Serviços Automotivos

## Status do Projeto
**Em desenvolvimento...**


## 🛠️ Tecnologias Utilizadas
- **Linguagem:** Java 17+
- **Framework:** Spring Boot (Spring MVC, Spring Data JPA)
- **Banco de Dados:** MySQL
- **Gerenciamento de Dependências:** Maven


## Objetivo do Projeto
Este projeto tem como objetivo desenvolver uma **API RESTful** robusta e eficiente para o gerenciamento de serviços automotivos, atendendo tanto às oficinas mecânicas quanto aos seus clientes.

🔧 **Para oficinas:**
- Gerenciar serviços realizados.
- Organizar especialidades, horários de funcionamento e histórico de manutenções.
- Controlar estoque de peças e insumos.

🚘 **Para clientes:**
- Encontrar rapidamente oficinas de acordo com localização, especialidade e disponibilidade.
- Consultar serviços disponíveis e histórico de atendimentos.


## 🚀 Funcionalidades Implementadas / Planejadas

### Oficina Mecânica
- Cadastro de oficinas com:
  - CNPJ, Nome, Endereço, Contato
  - Especialidades
  - Horários de funcionamento
  - Histórico de serviços realizados

### 👥 Usuários
-  Cadastro de usuários com:
    - Nome, CPF, Email, Telefone
    - Acesso ao acervo de oficinas
    - Histório de serviços realizados no carro
    - Sistema de avaliação

### Carros
- Cadastro de veículos dos clientes:
  - Placa, modelo, marca, ano de fabricação, cor, tipo de combustível e transmissão

### Serviços
- Registro de serviços realizados:
  - Descrição, data, valor pago, veículo associado e oficina responsável


## 👨‍💻 Desenvolvedor

- **LinkedIn:** [Gabriel Lima de Sousa](https://www.linkedin.com/in/gabriel-lima-de-sousa-31a358287/)


## 💡 Como rodar o projeto localmente

```bash
# Clone este repositório
git clone https://github.com/GabSkyDev/SistemaDeCarros.git

# Acesse a pasta do projeto
cd SistemaDeCarros

# Configure seu application.properties com os dados do seu banco MySQL

# Execute a aplicação
./mvnw spring-boot:run
