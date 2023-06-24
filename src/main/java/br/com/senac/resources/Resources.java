package br.com.senac.resources;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.com.senac.actions.Actions;
import br.com.senac.entities.Depositos;
import br.com.senac.entities.DepositosProdutos;
import br.com.senac.entities.Fornecedores;
import br.com.senac.entities.Movimentacoes;
import br.com.senac.entities.Produtos;
import br.com.senac.entities.ProdutosFornecedores;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/estoque")
public class Resources {
    @ConfigProperty(name = "quarkus.datasource.url")
    String url;

    @ConfigProperty(name = "quarkus.datasource.username")
    String username;

    @ConfigProperty(name = "quarkus.datasource.password")
    String password;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/depositos/{codigoDeposito}")
    public Depositos getDeposito(@PathParam("codigoDeposito") String codigoDeposito) {
        Actions action = new Actions();
        Depositos deposito = new Depositos();

        deposito = action.getDeposito(codigoDeposito, url, username, password);

        return deposito;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/fornecedores/{codigoFornecedor}")
    public Fornecedores getFornecedor(@PathParam("codigoFornecedor") String codigoFornecedor) {
        Actions action = new Actions();
        Fornecedores fornecedor = new Fornecedores();

        fornecedor = action.getFornecedor(codigoFornecedor, url, username, password);

        return fornecedor;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/produtos/{codigoProduto}")
    public Produtos getProduto(@PathParam("codigoProduto") String codigoProduto) {
        Actions action = new Actions();
        Produtos produto = new Produtos();

        produto = action.getProduto(codigoProduto, url, username, password);

        return produto;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/depositos")
    public List<Depositos> getDepositos() {
        Actions action = new Actions();
        List<Depositos> depositos = new ArrayList<>();

        depositos = action.getDepositos(url, username, password);

        return depositos;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/depositos_produtos")
    public List<DepositosProdutos> getDepositosProdutos() {
        Actions action = new Actions();
        List<DepositosProdutos> depositosProdutos = new ArrayList<>();

        depositosProdutos = action.getDepositosProdutos(url, username, password);

        return depositosProdutos;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/depositos_produtos/para_repor")
    public List<DepositosProdutos> getDepositosProdutosParaRepor() {
        Actions action = new Actions();
        List<DepositosProdutos> depositosProdutos = new ArrayList<>();

        depositosProdutos = action.getDepositosProdutosParaRepor(url, username, password);

        return depositosProdutos;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/movimentacoes")
    public List<Movimentacoes> getMovimentacoes() {
        Actions action = new Actions();
        List<Movimentacoes> movimentacoes = new ArrayList<>();

        movimentacoes = action.getMovimentacoes(url, username, password);

        return movimentacoes;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/produtos_fornecedores")
    public List<ProdutosFornecedores> getProdutosFornecedores() {
        Actions action = new Actions();
        List<ProdutosFornecedores> produtosFornecedores = new ArrayList<>();

        produtosFornecedores = action.getProdutosFornecedores(url, username, password);

        return produtosFornecedores;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/fornecedores/{codigoFornecedor}/produtos")
    public List<ProdutosFornecedores> getProdutosDoFornecedor(@PathParam("codigoFornecedor") String codigoFornecedor) {
        Actions action = new Actions();
        List<ProdutosFornecedores> produtosFornecedores = new ArrayList<>();

        produtosFornecedores = action.getProdutosDoFornecedor(codigoFornecedor, url, username, password);

        return produtosFornecedores;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/depositos/{codigoDeposito}/produtos")
    public List<DepositosProdutos> getProdutosDoDeposito(@PathParam("codigoDeposito") String codigoDeposito) {
        Actions action = new Actions();
        List<DepositosProdutos> depositosProdutos = new ArrayList<>();

        depositosProdutos = action.getProdutosDoDeposito(codigoDeposito, url, username, password);

        return depositosProdutos;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/depositos/{codigoDeposito}/produtos/{codigoProduto}")
    public DepositosProdutos getDepositoProduto(@PathParam("codigoDeposito") String codigoDeposito,
            @PathParam("codigoProduto") String codigoProduto) {
        Actions action = new Actions();
        DepositosProdutos depositoProduto = new DepositosProdutos();

        depositoProduto = action.getDepositoProduto(codigoDeposito, codigoProduto, url, username, password);

        return depositoProduto;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/produtos/{codigoProduto}/depositos")
    public List<DepositosProdutos> getDepositosComProduto(@PathParam("codigoProduto") String codigoProduto) {
        Actions action = new Actions();
        List<DepositosProdutos> depositosProdutos = new ArrayList<>();

        depositosProdutos = action.getDepositosComProduto(codigoProduto, url, username, password);

        return depositosProdutos;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/produtos/{codigoProduto}/fornecedores")
    public List<ProdutosFornecedores> getFornecedoresDoProduto(@PathParam("codigoProduto") String codigoProduto) {
        Actions action = new Actions();
        List<ProdutosFornecedores> produtosFornecedores = new ArrayList<>();

        produtosFornecedores = action.getFornecedoresDoProduto(codigoProduto, url, username, password);

        return produtosFornecedores;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/fornecedores")
    public List<Fornecedores> getFornecedores() {
        Actions action = new Actions();
        List<Fornecedores> fornecedores = new ArrayList<>();

        fornecedores = action.getFornecedores(url, username, password);

        return fornecedores;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/produtos")
    public List<Produtos> getProdutos() {
        Actions action = new Actions();
        List<Produtos> produtos = new ArrayList<>();

        produtos = action.getProdutos(url, username, password);

        return produtos;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/produtos/para_repor")
    public List<Produtos> getProdutosParaRepor() {
        Actions action = new Actions();
        List<Produtos> produtos = new ArrayList<>();

        produtos = action.getProdutosParaRepor(url, username, password);

        return produtos;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/depositos")
    public Depositos postDeposito(Depositos deposito) {
        Actions action = new Actions();
        Depositos ret = new Depositos();

        ret = action.postDeposito(deposito, url, username, password);

        return ret;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/produtos_fornecedores")
    public ProdutosFornecedores postProdutoFornecedor(ProdutosFornecedores produtoFornecedor) {
        Actions action = new Actions();
        ProdutosFornecedores ret = new ProdutosFornecedores();

        ret = action.postProdutoFornecedor(produtoFornecedor, url, username, password);

        return ret;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/fornecedores")
    public Fornecedores postFornecedor(Fornecedores fornecedor) {
        Actions action = new Actions();
        Fornecedores ret = new Fornecedores();

        ret = action.postFornecedor(fornecedor, url, username, password);

        return ret;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/produtos")
    public Produtos postProduto(Produtos produto) {
        Actions action = new Actions();
        Produtos ret = new Produtos();

        ret = action.postProduto(produto, url, username, password);

        return ret;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("depositos/{id}")
    public Depositos putDeposito(@PathParam("id") String id, Depositos deposito) {
        Actions action = new Actions();
        Depositos ret = new Depositos();

        ret = action.putDeposito(id, deposito, url, username, password);

        return ret;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("depositos/{codigoDeposito}/produtos/{codigoProduto}/movimentacoes")
    public Movimentacoes postMovimentacao(@PathParam("codigoDeposito") String codigoDeposito,
            @PathParam("codigoProduto") String codigoProduto, Movimentacoes movimentacao) {
        Actions action = new Actions();
        Movimentacoes ret = new Movimentacoes();

        ret = action.postMovimentacao(codigoDeposito, codigoProduto, movimentacao, url, username, password);

        return ret;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("depositos/{codigoDeposito}/movimentacoes")
    public Movimentacoes postMovimentacao(@PathParam("codigoDeposito") String codigoDeposito,
            Movimentacoes movimentacao) {
        Actions action = new Actions();
        Movimentacoes ret = new Movimentacoes();

        ret = action.postMovimentacao(codigoDeposito, movimentacao, url, username, password);

        return ret;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("fornecedores/{id}")
    public Fornecedores putFornecedor(@PathParam("id") String id, Fornecedores fornecedor) {
        Actions action = new Actions();
        Fornecedores ret = new Fornecedores();

        ret = action.putFornecedor(id, fornecedor, url, username, password);

        return ret;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("produtos/{id}")
    public Produtos putProduto(@PathParam("id") String id, Produtos produto) {
        Actions action = new Actions();
        Produtos ret = new Produtos();

        ret = action.putProduto(id, produto, url, username, password);

        return ret;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("depositos/{id}")
    public Depositos deleteDeposito(@PathParam("id") String id) {
        Actions action = new Actions();
        Depositos deposito = new Depositos();

        deposito = action.deleteDeposito(id, url, username, password);

        return deposito;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("fornecedores/{id}")
    public Fornecedores deleteFornecedor(@PathParam("id") String id) {
        Actions action = new Actions();
        Fornecedores fornecedor = new Fornecedores();

        fornecedor = action.deleteFornecedor(id, url, username, password);

        return fornecedor;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("produtos/{id}")
    public Produtos deleteProduto(@PathParam("id") String id) {
        Actions action = new Actions();
        Produtos produto = new Produtos();

        produto = action.deleteProduto(id, url, username, password);

        return produto;
    }
}
