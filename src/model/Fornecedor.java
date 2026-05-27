package model;

public class Fornecedor extends Pessoa {

    private String cnpj;

    public Fornecedor() {
    }

    public Fornecedor(int id, String nome, String telefone,
                      String email, String cnpj) {

        super(id, nome, telefone, email);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}