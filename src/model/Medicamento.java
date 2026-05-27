package model;

import java.time.LocalDate;

import enums.StatusMedicamento;

public class Medicamento {

    private int codigo;
    private String nome;
    private String laboratorio;
    private double preco;
    private int estoque;
    private LocalDate validade;

    private StatusMedicamento status;

    public Medicamento() {
    }

    public Medicamento(int codigo, String nome,
                       String laboratorio,
                       double preco,
                       int estoque,
                       LocalDate validade,
                       StatusMedicamento status) {

        this.codigo = codigo;
        this.nome = nome;
        this.laboratorio = laboratorio;
        this.preco = preco;
        this.estoque = estoque;
        this.validade = validade;
        this.status = status;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public StatusMedicamento getStatus() {
        return status;
    }

    public void setStatus(StatusMedicamento status) {
        this.status = status;
    }
}