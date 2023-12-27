# API de Planetas de Star wars

Este é um projeto de um desafil de desenvilvimento de uma api rest de planetas de star wars.
Tem as funcionalidades de cadastrar, listar, atualizar e excluir planetas e possui conexão com a API de star wars [swapi.dev](https://swapi.dev/) usada para fornecer dados para listagens dos planetas.

## Métodos:
| Método | Descrição |
|---|---|
| `GET` | Utilizado para retornar informações de um ou mais Planetas. |
| `POST` | Utilizado para registar um novo Planeta. |
| `PUT` | Utilizado para altera dados de um Planeta. |
| `DELETE` | Utilizado para remove um Planeta registado. |

Requisições para a API devem seguir os padrões:

## GET

#### `/planeta`
lista todos os planetas registrados no banco de dados.

#### `/planeta?size=3`
lista uma quatidade de planetas de acordo com que foi pasado no parametro size.

#### `/planeta?page=2&size=2`
lista uma quantidade de planetas de acordo com o que foi passado no parãmetro size e a pagina passada pelo parâmetro page.

#### `/planeta/id`
busca um planeta pelo seu ID e o retorna caso seja encontrado.

#### `/planeta/nome_do_planeta/nome`
busca um planeta pelo nome o retorna caso seja encontrado. 
<br>ex: `http://localhost:8080/planeta/kamino/nome`

<br>

## POST

#### `/planeta`
cadastra um novo planeta passando um nome, clima e terreno no copo da requisição em JSON. <br>
ex:
```
{
    "nome": "Hoth",
    "clima": "ÁRIDO",
    "terreno": "gelado",
}
```
obs: o parâmetro clima recebe e aceita somente ÚMIDO, `ÁRIDO`, `POLAR`, `TEMPERADO` ou `TROPICAL`, que deve ser passado em letra maiuscula exatamente como descreve este documento.

<br>

## PUT

#### `/planeta`
atualiza um planeta registrado passando as infomações que deseja ser atualizadas junto do ID do objeto atraves do corpo da requisição em JSON. <br>
ex:
```
{
    "id": 5
    "clima": "POLAR",
}
```
obs: é necessário passar somente o que deve ser atualizado junto ao id.

<br>

## DELETE

#### `/planeta/id`
deleta um planetas registrado passando um id pela url.<br>
ex: `http://localhost:8080/planeta/14`.
