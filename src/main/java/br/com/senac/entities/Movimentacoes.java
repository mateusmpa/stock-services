package br.com.senac.entities;

import java.io.Serializable;

public class Movimentacoes implements Serializable {
    private String codigo;
    private String movimentacao;
    private String codigoDeposito;
    private String deposito;
    private String codigoProduto;
    private String produto;
    private String quantidade;
    private String quantidadeMinima;
    private Float valorUnitario;
    private String valorTotal;
    private String registro;
    private String mensagem;

    public Float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(String movimentacao) {
        this.movimentacao = movimentacao;
    }

    public String getCodigoDeposito() {
        return codigoDeposito;
    }

    public void setCodigoDeposito(String codigoDeposito) {
        this.codigoDeposito = codigoDeposito;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(String quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }
}
