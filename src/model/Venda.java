package model;

import java.time.LocalDate;

public class Venda {

    private int id;
    private Cliente cliente;
    private Funcionario funcionario;
    private Medicamento medicamento;

    private int quantidade;
    private double valorTotal;

    private LocalDate dataVenda;

    public Venda() {
    }

    public Venda(int id,
                 Cliente cliente,
                 Funcionario funcionario,
                 Medicamento medicamento,
                 int quantidade,
                 double valorTotal,
                 LocalDate dataVenda) {

        this.id = id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.medicamento = medicamento;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.dataVenda = dataVenda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
}