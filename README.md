# Desafio Indústria - Java

Solução do desafio de funcionários, implementada em Java puro, com Maven e testes unitários JUnit 5.

## Requisitos atendidos
- `Pessoa`: nome e data de nascimento (`LocalDate`).
- `Funcionario extends Pessoa`: salário (`BigDecimal`) e função (`String`).
- Inclusão dos 10 funcionários exatamente na ordem da tabela.
- Remoção de João.
- Impressão com data `dd/MM/yyyy` e números no padrão brasileiro (`.` milhar e `,` decimal).
- Aumento de 10% dos salários.
- Agrupamento por função em `Map<String, List<Funcionario>>`.
- Impressão por função.
- Aniversariantes dos meses 10 e 12.
- Funcionário mais velho com nome e idade.
- Ordenação alfabética.
- Total dos salários.
- Quantidade de salários mínimos por funcionário, considerando R$ 1.212,00.


## Tecnologias
- Java 17+ (LTS; testado também com JDK 21)
- Maven
- JUnit 5

## Executar
```bash
mvn clean test
mvn exec:java
```

Também é possível importar o projeto Maven diretamente no Eclipse, IntelliJ IDEA ou NetBeans.

## Organização
A classe `Principal` mantém a sequência explícita dos requisitos do desafio. Regras reutilizáveis ficam em `FuncionarioService`, facilitando testes unitários e mantendo a classe principal legível.
