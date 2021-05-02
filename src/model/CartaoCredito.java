/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jfran
 */
public class CartaoCredito {

    private String dataEmissao, validadeCartao, categoria, bandeira, variante, dataFechamento, dataVencimento, nomeCartao;
    private int numeroCartao, senha, cvv, statusCartao, idFatura, idCartao, idUsuario;
    private double valorLimite,valorDisponivel;

    public CartaoCredito() {
    }

    public CartaoCredito(String dataEmissao, String validadeCartao, String categoria, String bandeira, String dataFechameto,String dataVencimento,String nomeCartao,
            String variante, int numeroCartao, int senha, int cvv, double valorLimite,double valorDisponivel) {
        super();
        this.dataEmissao = dataEmissao;
        this.validadeCartao = validadeCartao;
        this.categoria = categoria;
        this.bandeira = bandeira;
        this.variante = variante;
        this.numeroCartao = numeroCartao;
        this.senha = senha;
        this.cvv = cvv;
        this.valorLimite = valorLimite;
        this.dataFechamento = dataFechamento;
        this.dataVencimento = dataVencimento;
        this.nomeCartao = nomeCartao;
        this.valorDisponivel = valorDisponivel;

    }

    public double getValorDisponivel() {
        return valorDisponivel;
    }

    public void setValorDisponivel(double valorDisponivel) {
        this.valorDisponivel = valorDisponivel;
    }
    

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }
    

    public String getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(String dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    

    public int getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(int idCartao) {
        this.idCartao = idCartao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getStatusCartao() {
        return statusCartao;
    }

    public void setStatusCartao(int statusCartao) {
        this.statusCartao = statusCartao;
    }

    public int getIdFatura() {
        return idFatura;
    }

    public void setIdFatura(int idFatura) {
        this.idFatura = idFatura;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getValidadeCartao() {
        return validadeCartao;
    }

    public void setValidadeCartao(String validadeCartao) {
        this.validadeCartao = validadeCartao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getVariante() {
        return variante;
    }

    public void setVariante(String variante) {
        this.variante = variante;
    }

    public int getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(int numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public double getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(double valorLimite) {
        this.valorLimite = valorLimite;
    }

}
