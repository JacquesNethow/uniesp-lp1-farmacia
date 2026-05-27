package service;

import enums.CategoriaFuncionario;
import model.Funcionario;
import utils.MensagemUtils;
import utils.ValidacaoUtils;
import enums.TipoMensagem;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioService {

    private static final String MODULO       = "FUNCIONARIO";
    private static final String MODULO_LOGIN = "LOGIN";

    private List<Funcionario> funcionarios;

    public FuncionarioService() {
        this.funcionarios = new ArrayList<>();
    }

    public void cadastrarFuncionario(Funcionario funcionario) {

        if (funcionario == null) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Funcionário não informado.");
            return;
        }

        // ── Nome ──────────────────────────────────────────
        if (ValidacaoUtils.textoVazio(funcionario.getNome())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Nome não informado.");
            return;
        }

        if (funcionario.getNome().trim().length() < 3) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Nome muito curto. Mínimo 3 caracteres.");
            return;
        }

        if (!funcionario.getNome().matches("[a-zA-ZÀ-ú ]+")) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Nome inválido. Use apenas letras e espaços.");
            return;
        }

        // ── Login ─────────────────────────────────────────
        if (ValidacaoUtils.textoVazio(funcionario.getLogin())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Login não informado.");
            return;
        }

        if (!ValidacaoUtils.loginFormatoValido(funcionario.getLogin())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Login inválido. Use entre 3 e 20 caracteres (letras, números e _).");
            return;
        }

        for (Funcionario func : funcionarios) {
            if (func.getLogin().equalsIgnoreCase(funcionario.getLogin())) {
                MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                        "Já existe um funcionário com esse login.");
                return;
            }
        }

        // ── Senha ─────────────────────────────────────────
        if (ValidacaoUtils.textoVazio(funcionario.getSenha())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Senha não informada.");
            return;
        }

        if (!ValidacaoUtils.senhaForteValida(funcionario.getSenha())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Senha fraca. Mínimo 6 caracteres com ao menos 1 letra e 1 número.");
            return;
        }

        // ── Categoria ─────────────────────────────────────
        if (funcionario.getCategoria() == null) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Categoria não informada.");
            return;
        }

        // ── Sucesso ───────────────────────────────────────
        funcionarios.add(funcionario);
        MensagemUtils.exibir(TipoMensagem.SUCESSO, MODULO,
                "Funcionário cadastrado com sucesso.");
    }

    public Funcionario autenticar(String login, String senha) {

        // ── Entradas vazias ───────────────────────────────
        if (ValidacaoUtils.textoVazio(login)) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO_LOGIN,
                    "Login não informado.");
            return null;
        }

        if (ValidacaoUtils.textoVazio(senha)) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO_LOGIN,
                    "Senha não informada.");
            return null;
        }

        // ── Busca e valida ────────────────────────────────
        for (Funcionario funcionario : funcionarios) {

            if (funcionario.getLogin().equalsIgnoreCase(login)) {

                if (!funcionario.getSenha().equals(senha)) {
                    MensagemUtils.exibir(TipoMensagem.ERRO, MODULO_LOGIN,
                            "Senha incorreta.");
                    return null;
                }

                MensagemUtils.exibir(TipoMensagem.SUCESSO, MODULO_LOGIN,
                        "Autenticação realizada. Bem-vindo, "
                                + funcionario.getNome() + "!");
                return funcionario;
            }
        }

        MensagemUtils.exibir(TipoMensagem.ERRO, MODULO_LOGIN,
                "Usuário não encontrado.");
        return null;
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarios;
    }

    public void editarFuncionario(int id, String novoNome,
                                  String novoTelefone, String novoEmail,
                                  CategoriaFuncionario novaCategoria) {

        for (Funcionario func : funcionarios) {

            if (func.getId() == id) {

                // ── Nome ──────────────────────────────────────
                if (ValidacaoUtils.textoVazio(novoNome)) {
                    MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                            "Nome não informado.");
                    return;
                }

                if (novoNome.trim().length() < 3) {
                    MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                            "Nome muito curto. Mínimo 3 caracteres.");
                    return;
                }

                if (!novoNome.matches("[a-zA-ZÀ-ú ]+")) {
                    MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                            "Nome inválido. Use apenas letras e espaços.");
                    return;
                }

                // ── Categoria ─────────────────────────────────
                if (novaCategoria == null) {
                    MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                            "Categoria não informada.");
                    return;
                }

                // ── Aplica ────────────────────────────────────
                func.setNome(novoNome);
                func.setTelefone(novoTelefone);
                func.setEmail(novoEmail);
                func.setCategoria(novaCategoria);

                MensagemUtils.exibir(TipoMensagem.SUCESSO, MODULO,
                        "Funcionário atualizado com sucesso.");
                return;
            }
        }

        MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                "Funcionário não encontrado. ID: " + id);
    }

    public void removerFuncionario(int id) {

        for (Funcionario func : funcionarios) {

            if (func.getId() == id) {
                funcionarios.remove(func);
                MensagemUtils.exibir(TipoMensagem.SUCESSO, MODULO,
                        "Funcionário removido com sucesso.");
                return;
            }
        }

        MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                "Funcionário não encontrado. ID: " + id);
    }
}