package model;

public class Cliente extends Pessoa {

    private String cpf;

    public Cliente() {}

    public Cliente(int id, String nome, String telefone,
                   String email, String cpf) {
        super(id, nome, telefone, email);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}