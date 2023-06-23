package br.com.senac.entities;

import java.io.Serializable;

public class Produtos implements Serializable {
    private String id;
    private String codigo;
    private String nome;
    private String quantidadeMinima;
    private String quantidade;
    private String valorMedio;
    private String mensagem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(String quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getValorMedio() {
        return valorMedio;
    }

    public void setValorMedio(String valorMedio) {
        this.valorMedio = valorMedio;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}
