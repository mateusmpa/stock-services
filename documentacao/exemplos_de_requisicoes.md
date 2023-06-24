# Exemplos de requisições

## Buscar os depositos

* GET /estoque/depositos

```sh
curl -X 'GET' \
  'http://localhost:9090/estoque/depositos' \
  -H 'accept: application/json'
```

```json
[
  {
    "codigo": "salv_0001",
    "id": "1",
    "nome": "Salvador"
  },
  {
    "codigo": "saop_0001",
    "id": "2",
    "nome": "São Paulo"
  },
  {
    "codigo": "riog_0001",
    "id": "3",
    "nome": "Rio Grande do Sul"
  }
]
```

## Criar um deposito

* POST /estoque/depositos

```sh
curl -X 'POST' \
  'http://localhost:9090/estoque/depositos' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "codigo": "mato_0001",
  "nome": "Mato Grosso do Sul"
}'
```

```json
{
  "mensagem": "Deposito cadastrado!"
}
```

## Buscar um depósito

* GET /estoque/depositos/{codigoDeposito}

```sh
curl -X 'GET' \
  'http://localhost:9090/estoque/depositos/saop_0001' \
  -H 'accept: application/json'
```

```json
{
  "codigo": "saop_0001",
  "id": "2",
  "nome": "São Paulo"
}
```

## Criar uma movimentação adicionando um produto a um depósito

* /estoque/depositos/{codigoDeposito}/movimentacoes

```sh
curl -X 'POST' \
  'http://localhost:9090/estoque/depositos/riog_0001/movimentacoes' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "codigo": 1,
  "codigoProduto": "teni_0001",
  "quantidade": 826,
  "quantidadeMinima": 30,
  "valorUnitario": 956.87
}'
```

```json
{
  "mensagem": "Movimentação registrada!"
}
```

## Buscar os produtos de um depósito

* GET /estoque/depositos/{codigoDeposito}/produtos

```sh
curl -X 'GET' \
  'http://localhost:9090/estoque/depositos/saop_0001/produtos' \
  -H 'accept: application/json'
```

```json
[
  {
    "codigoDeposito": "saop_0001",
    "codigoProduto": "teni_0001",
    "deposito": "São Paulo",
    "produto": "Tênis esportivo",
    "quantidade": "433",
    "quantidadeMinima": "300"
  },
  {
    "codigoDeposito": "saop_0001",
    "codigoProduto": "smar_0001",
    "deposito": "São Paulo",
    "produto": "Smartphone",
    "quantidade": "110",
    "quantidadeMinima": "80"
  }
]
```

## Buscar um produto de um depósito

* GET /estoque/depositos/{codigoDeposito}/produtos/{codigoProduto}

```sh
curl -X 'GET' \
  'http://localhost:9090/estoque/depositos/saop_0001/produtos/teni_0001' \
  -H 'accept: application/json'
```

```json
{
  "codigoDeposito": "saop_0001",
  "codigoProduto": "teni_0001",
  "deposito": "São Paulo",
  "produto": "Tênis esportivo",
  "quantidade": "433",
  "quantidadeMinima": "300"
}
```

## Criar uma movimentação atualizando a quantidade de um produto em um depósito

* POST /estoque/depositos/{codigoDeposito}/produtos/{codigoProduto}/movimentacoes

```sh
curl -X 'POST' \
  'http://localhost:9090/estoque/depositos/saop_0001/produtos/teni_0001/movimentacoes' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "codigo": 3,
  "quantidade": 5,
  "valorUnitario": 0
}'
```

```json
{
  "mensagem": "Movimentação registrada!"
}
```

## Editar um depósito

* PUT /estoque/depositos/{id}

```sh
curl -X 'PUT' \
  'http://localhost:9090/estoque/depositos/3' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Rio Grande do Norte"
}'
```

```json
{
  "mensagem": "Deposito alterado!"
}
```

## Deletar um depósito

* DELETE /estoque/depositos/{id}

```sh
curl -X 'DELETE' \
  'http://localhost:9090/estoque/depositos/4' \
  -H 'accept: application/json'
```

```json
{
  "mensagem": "Depósito apagado!"
}
```

## Buscar relação de depositos com produtos

* GET /estoque/depositos_produtos

```sh
curl -X 'GET' \
  'http://localhost:9090/estoque/depositos_produtos' \
  -H 'accept: application/json'
```

```json
[
  {
    "codigoDeposito": "riog_0001",
    "codigoProduto": "teni_0001",
    "deposito": "Rio Grande do Norte",
    "produto": "Tênis esportivo",
    "quantidade": "826",
    "quantidadeMinima": "30"
  },
  {
    "codigoDeposito": "salv_0001",
    "codigoProduto": "smar_0001",
    "deposito": "Salvador",
    "produto": "Smartphone",
    "quantidade": "795",
    "quantidadeMinima": "100"
  },
  {
    "codigoDeposito": "saop_0001",
    "codigoProduto": "teni_0001",
    "deposito": "São Paulo",
    "produto": "Tênis esportivo",
    "quantidade": "428",
    "quantidadeMinima": "300"
  },
  {
    "codigoDeposito": "saop_0001",
    "codigoProduto": "smar_0001",
    "deposito": "São Paulo",
    "produto": "Smartphone",
    "quantidade": "110",
    "quantidadeMinima": "80"
  }
]
```

## Buscar a relação de depósitos com produtos que estão com estoque baixo

* GET /estoque/depositos_produtos/para_repor

```sh
curl -X 'GET' \
  'http://localhost:9090/estoque/depositos_produtos/para_repor' \
  -H 'accept: application/json'
```

```json
[
  {
    "codigoDeposito": "salv_0001",
    "codigoProduto": "teni_0001",
    "deposito": "Salvador",
    "produto": "Tênis esportivo",
    "quantidade": "200",
    "quantidadeMinima": "300"
  }
]
```

## Buscar os fornecedores

* GET /estoque/fornecedores

```sh
curl -X 'GET' \
  'http://localhost:9090/estoque/fornecedores' \
  -H 'accept: application/json'
```

```json
[
  {
    "codigo": "acme_0001",
    "id": "1",
    "nome": "Acme Corporation"
  },
  {
    "codigo": "glob_0001",
    "id": "2",
    "nome": "Global Solutions"
  },
  {
    "codigo": "prim_0001",
    "id": "3",
    "nome": "Prime Industries"
  }
]
```

## Criar um fornecedor

* POST /estoque/fornecedores

```sh
curl -X 'POST' \
  'http://localhost:9090/estoque/fornecedores' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Amazon Brasil",
  "codigo": "amaz_0001"
}'
```

```json
{
  "mensagem": "Fornecedor cadastrado!"
}
```

## Buscar um fornecedor

* GET /estoque/fornecedores/{codigoFornecedor}

```sh
curl -X 'GET' \
  'http://localhost:9090/estoque/fornecedores/acme_0001' \
  -H 'accept: application/json'
```

```json
{
  "codigo": "acme_0001",
  "id": "1",
  "nome": "Acme Corporation"
}
```

## Buscar os produtos de um fornecedor

* GET /estoque/fornecedores/{codigoFornecedor}/produtos

```sh
curl -X 'GET' \
  'http://localhost:9090/estoque/fornecedores/glob_0001/produtos' \
  -H 'accept: application/json'
```

```json
[
  {
    "codigoFornecedor": "glob_0001",
    "codigoProduto": "smar_0001",
    "fornecedor": "Global Solutions",
    "produto": "Smartphone"
  },
  {
    "codigoFornecedor": "glob_0001",
    "codigoProduto": "teni_0001",
    "fornecedor": "Global Solutions",
    "produto": "Tênis esportivo"
  }
]
```

## Editar um fornecedor

* PUT /estoque/fornecedores/{id}

```sh
curl -X 'PUT' \
  'http://localhost:9090/estoque/fornecedores/3' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Amazon EUA",
  "codigo": "amaz_0002"
}'
```

```json
{
  "mensagem": "Fornecedor alterado!"
}
```

## Deletar um fornecedor

* DELETE /estoque/fornecedores/{id}

```sh
curl -X 'DELETE' \
  'http://localhost:9090/estoque/fornecedores/3' \
  -H 'accept: application/json'
```

```json
{
  "mensagem": "Fornecedor apagado!"
}
```

## Buscar as movimentações

* GET /estoque/movimentacoes

```sh
curl -X 'GET' \
  'http://localhost:9090/estoque/movimentacoes' \
  -H 'accept: application/json'
```

```json
[
  {
    "codigo": "1",
    "codigoDeposito": "salv_0001",
    "codigoProduto": "smar_0001",
    "deposito": "Salvador",
    "movimentacao": "Entrada por Nota Fiscal",
    "produto": "Smartphone",
    "quantidade": "800",
    "registro": "2023-06-23 20:41:34",
    "valorTotal": "840000",
    "valorUnitario": 1050
  },
  {
    "codigo": "3",
    "codigoDeposito": "salv_0001",
    "codigoProduto": "smar_0001",
    "deposito": "Salvador",
    "movimentacao": "Saída por Nota Fiscal",
    "produto": "Smartphone",
    "quantidade": "50",
    "registro": "2023-06-23 20:41:34",
    "valorUnitario": 0
  },
  {
    "codigo": "2",
    "codigoDeposito": "salv_0001",
    "codigoProduto": "smar_0001",
    "deposito": "Salvador",
    "movimentacao": "Entrada por Doação",
    "produto": "Smartphone",
    "quantidade": "100",
    "registro": "2023-06-23 20:41:35",
    "valorUnitario": 0
  },
  {
    "codigo": "1",
    "codigoDeposito": "salv_0001",
    "codigoProduto": "smar_0001",
    "deposito": "Salvador",
    "movimentacao": "Entrada por Nota Fiscal",
    "produto": "Smartphone",
    "quantidade": "55",
    "registro": "2023-06-23 20:41:35",
    "valorTotal": "110027.5",
    "valorUnitario": 2000.5
  },
  {
    "codigo": "6",
    "codigoDeposito": "salv_0001",
    "codigoProduto": "smar_0001",
    "deposito": "Salvador",
    "movimentacao": "Transferência entre depósitos (saída)",
    "produto": "Smartphone",
    "quantidade": "110",
    "registro": "2023-06-23 20:41:35",
    "valorUnitario": 0
  },
  {
    "codigo": "5",
    "codigoDeposito": "saop_0001",
    "codigoProduto": "smar_0001",
    "deposito": "São Paulo",
    "movimentacao": "Transferência entre depósitos (entrada)",
    "produto": "Smartphone",
    "quantidade": "110",
    "registro": "2023-06-23 20:41:35",
    "valorUnitario": 0
  },
  {
    "codigo": "1",
    "codigoDeposito": "saop_0001",
    "codigoProduto": "teni_0001",
    "deposito": "São Paulo",
    "movimentacao": "Entrada por Nota Fiscal",
    "produto": "Tênis esportivo",
    "quantidade": "433",
    "registro": "2023-06-23 20:41:35",
    "valorTotal": "385759.7",
    "valorUnitario": 890.9
  },
  {
    "codigo": "1",
    "codigoDeposito": "riog_0001",
    "codigoProduto": "teni_0001",
    "deposito": "Rio Grande do Norte",
    "movimentacao": "Entrada por Nota Fiscal",
    "produto": "Tênis esportivo",
    "quantidade": "826",
    "registro": "2023-06-23 21:17:09",
    "valorTotal": "790374.62",
    "valorUnitario": 956.87
  },
  {
    "codigo": "3",
    "codigoDeposito": "saop_0001",
    "codigoProduto": "teni_0001",
    "deposito": "São Paulo",
    "movimentacao": "Saída por Nota Fiscal",
    "produto": "Tênis esportivo",
    "quantidade": "5",
    "registro": "2023-06-23 21:32:45",
    "valorTotal": "0",
    "valorUnitario": 0
  }
]
```

## Buscar os produtos

* GET /estoque/produtos

```sh
curl -X 'GET' \
  'http://localhost:9090/estoque/produtos' \
  -H 'accept: application/json'
```

```json
[
  {
    "codigo": "smar_0001",
    "id": "1",
    "nome": "Smartphone",
    "quantidade": "905",
    "quantidadeMinima": "3000",
    "valorMedio": "1111.14"
  },
  {
    "codigo": "teni_0001",
    "id": "2",
    "nome": "Tênis esportivo",
    "quantidade": "1254",
    "quantidadeMinima": "500",
    "valorMedio": "934.18"
  },
  {
    "codigo": "relo_0001",
    "id": "3",
    "nome": "Relógio de pulso",
    "quantidade": "0",
    "quantidadeMinima": "800",
    "valorMedio": "0"
  }
]
```

## Buscar produtos com estoque baixo

* GET /estoque/produtos/para_repor

```sh
curl -X 'GET' \
  'http://localhost:9090/estoque/produtos/para_repor' \
  -H 'accept: application/json'
```

```json
[
  {
    "codigo": "smar_0001",
    "id": "1",
    "nome": "Smartphone",
    "quantidade": "905",
    "quantidadeMinima": "3000",
    "valorMedio": "1111.14"
  },
  {
    "codigo": "relo_0001",
    "id": "3",
    "nome": "Relógio de pulso",
    "quantidade": "0",
    "quantidadeMinima": "800",
    "valorMedio": "0"
  }
]
```

## Criar um produto

* POST /estoque/produtos

```sh
curl -X 'POST' \
  'http://localhost:9090/estoque/produtos' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "codigo": "sand_0001",
  "nome": "Sandalias Havaianas",
  "quantidadeMinima": 100
}'
```

```json
{
  "mensagem": "Produto cadastrado!"
}
```

## Buscar um produto

* GET /estoque/produtos/{codigoProduto}

```sh
curl -X 'GET' \
  'http://localhost:9090/estoque/produtos/teni_0001' \
  -H 'accept: application/json'
```

```json
{
  "codigo": "teni_0001",
  "id": "2",
  "nome": "Tênis esportivo",
  "quantidade": "1254",
  "quantidadeMinima": "500",
  "valorMedio": "934.18"
}
```

## Buscar os depósitos que tem um produto

* GET /estoque/produtos/{codigoProduto}/depositos

```sh
curl -X 'GET' \
  'http://localhost:9090/estoque/produtos/teni_0001/depositos' \
  -H 'accept: application/json'
```

```json
[
  {
    "codigoDeposito": "riog_0001",
    "codigoProduto": "teni_0001",
    "deposito": "Rio Grande do Norte",
    "produto": "Tênis esportivo",
    "quantidade": "826",
    "quantidadeMinima": "30"
  },
  {
    "codigoDeposito": "saop_0001",
    "codigoProduto": "teni_0001",
    "deposito": "São Paulo",
    "produto": "Tênis esportivo",
    "quantidade": "428",
    "quantidadeMinima": "300"
  }
]
```

## Buscar os fornecedores que tem um produto

* GET /estoque/produtos/{codigoProduto}/fornecedores

```sh
curl -X 'GET' \
  'http://localhost:9090/estoque/produtos/smar_0001/fornecedores' \
  -H 'accept: application/json'
```

```json
[
  {
    "codigoFornecedor": "glob_0001",
    "codigoProduto": "smar_0001",
    "fornecedor": "Global Solutions",
    "produto": "Smartphone"
  },
  {
    "codigoFornecedor": "acme_0001",
    "codigoProduto": "smar_0001",
    "fornecedor": "Acme Corporation",
    "produto": "Smartphone"
  }
]
```

## Buscar as movimentaçoes de um produto

* GET /estoque/produtos/{codigoProduto}/movimentacoes

```sh
curl -X 'GET' \
  'http://localhost:9090/estoque/produtos/teni_0001/movimentacoes' \
  -H 'accept: application/json'
```

```json
[
  {
    "codigo": "1",
    "codigoDeposito": "saop_0001",
    "codigoProduto": "teni_0001",
    "deposito": "São Paulo",
    "movimentacao": "Entrada por Nota Fiscal",
    "produto": "Tênis esportivo",
    "quantidade": "433",
    "registro": "2023-06-24 21:48:10",
    "valorTotal": "385759.7",
    "valorUnitario": 890.9
  },
  {
    "codigo": "1",
    "codigoDeposito": "salv_0001",
    "codigoProduto": "teni_0001",
    "deposito": "Salvador",
    "movimentacao": "Entrada por Nota Fiscal",
    "produto": "Tênis esportivo",
    "quantidade": "200",
    "registro": "2023-06-24 22:08:13",
    "valorTotal": "179960",
    "valorUnitario": 899.8
  }
]
```

## Editar um produto

* PUT /estoque/produtos/{id}

```sh
curl -X 'PUT' \
  'http://localhost:9090/estoque/produtos/21' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Sandálias Havaianas Femininas",
  "quantidadeMinima": 200
}'
```

```json
{
  "mensagem": "Produto alterado!"
}
```

## Deletar um produto

* DELETE /estoque/produtos/{id}

```sh
curl -X 'DELETE' \
  'http://localhost:9090/estoque/produtos/21' \
  -H 'accept: application/json'
```

```json
{
  "mensagem": "Produto apagado!"
}
```

## Buscar relação de produtos com fornecedores

* GET /estoque/produtos_fornecedores

```sh
curl -X 'GET' \
  'http://localhost:9090/estoque/produtos_fornecedores' \
  -H 'accept: application/json'
```

```json
[
  {
    "codigoFornecedor": "acme_0001",
    "codigoProduto": "smar_0001",
    "fornecedor": "Acme Corporation",
    "produto": "Smartphone"
  },
  {
    "codigoFornecedor": "glob_0001",
    "codigoProduto": "smar_0001",
    "fornecedor": "Global Solutions",
    "produto": "Smartphone"
  },
  {
    "codigoFornecedor": "glob_0001",
    "codigoProduto": "teni_0001",
    "fornecedor": "Global Solutions",
    "produto": "Tênis esportivo"
  }
]
```

## Registrar um produto com um fornecedor

* POST /estoque/produtos_fornecedores

```sh
curl -X 'POST' \
  'http://localhost:9090/estoque/produtos_fornecedores' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "codigoProduto": "teni_0001",
  "codigoFornecedor": "acme_0001"
}'
```

```json
{
  "mensagem": "Produto e fornecedor cadastrado!"
}
```
