# desafio-tunts

Esse projeto utiliza [Google Sheets API](https://developers.google.com/sheets/api) para ler,
buscar e escrever valores de uma planilha do Google sheets.

## Requisitos

- Java 11
- Spring 2
- Uma conta Google.
- Um projeto do Google Cloud com a API ativada. Para criar um projeto e ativar uma API,
  consulte [Criar um projeto e ativar a API](https://developers.google.com/workspace/guides/create-project).
- Credenciais de autorização para um aplicativo de desktop.
  Consulte [Criar credenciais](https://developers.google.com/workspace/guides/create-credentials).

## Construindo o projeto

- Primeiro, crie uma cópia da planilha (https://docs.google.com/spreadsheets/d/1XvWJcRLj2WAeXO3ULQ_GxGm9---3SZkjMbGcXMJtt70/edit#gid=0) e deixe-a pública para edição. 
- Copie seu arquivo JSON com as credenciais de autorização e cole dentro do projeto.
(Lembre-se de alterar o **application-dev.yml** )

## Diretrizes utilizadas no projeto

Calcular a situação de cada aluno baseado na média das 3 provas (P1, P2 e P3),
conforme a tabela:

| Média (M) | Situação           |
|-----------|--------------------|
| M < 5     | Reprovado por nota |
| 5 <= M <7 | Exame Final        |
| M >= 7    | Aprovado           |

Caso o número de faltas ultrapasse 25% do número total de aulas o aluno terá a situação **"Reprovado por Falta"**, 
independente da média. Caso a situação seja **"Exame Final"** é necessário calcular a "**Nota para Aprovação Final**" 
**(NAF)** de cada aluno de acordo com seguinte fórmula: **5 <= ( M + NAF) / 2**

## Executando o aplicativo

O aplicativo pode ser executado utilizando:

```shell script
mvn spring-boot:run
```

> **_ENDPOINT:_** http://localhost:8080/api/v1/class-diary

## Guias relacionados

- Documentação da Google Sheets: ([guia](https://developers.google.com/sheets/api/guides/concepts)).
- Documentação Spring ([guia](https://spring.io/)).