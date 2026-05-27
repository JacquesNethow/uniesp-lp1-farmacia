package model;

import enums.CategoriaFuncionario;

public class Funcionario extends Pessoa {

    private String login;
    private String senha;
    private CategoriaFuncionario categoria;

    public Funcionario() {
    }

    public Funcionario(int id, String nome, String telefone,
                       String email, String login,
                       String senha,
                       CategoriaFuncionario categoria) {

        super(id, nome, telefone, email);

        this.login = login;
        this.senha = senha;
        this.categoria = categoria;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public CategoriaFuncionario getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaFuncionario categoria) {
        this.categoria = categoria;
    }
}