package service;

import enums.TipoMensagem;
import model.Cliente;
import utils.MensagemUtils;
import utils.ValidacaoUtils;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private static final String MODULO = "CLIENTE";

    private List<Cliente> clientes;

    public ClienteService() {
        this.clientes = new ArrayList<>();
    }

    public void cadastrarCliente(Cliente cliente) {

        if (cliente == null) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Cliente não informado.");
            return;
        }

        // ── Nome ──────────────────────────────────────────
        if (ValidacaoUtils.textoVazio(cliente.getNome())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Nome não informado.");
            return;
        }

        if (cliente.getNome().trim().length() < 3) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Nome muito curto. Mínimo 3 caracteres.");
            return;
        }

        if (!cliente.getNome().matches("[a-zA-ZÀ-ú ]+")) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Nome inválido. Use apenas letras e espaços.");
            return;
        }

        // ── CPF ───────────────────────────────────────────
        if (ValidacaoUtils.textoVazio(cliente.getCpf())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "CPF não informado.");
            return;
        }

        if (!ValidacaoUtils.cpfFormatoValido(cliente.getCpf())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "CPF inválido. Use o formato 000.000.000-00.");
            return;
        }

        for (Cliente cli : clientes) {
            if (cli.getCpf().equals(cliente.getCpf())) {
                MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                        "Já existe um cliente com esse CPF.");
                return;
            }
        }

        // ── Email ─────────────────────────────────────────
        if (ValidacaoUtils.textoVazio(cliente.getEmail())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Email não informado.");
            return;
        }

        if (!ValidacaoUtils.emailFormatoValido(cliente.getEmail())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Email inválido. Use o formato exemplo@dominio.com.");
            return;
        }

        for (Cliente cli : clientes) {
            if (cli.getEmail().equalsIgnoreCase(cliente.getEmail())) {
                MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                        "Já existe um cliente com esse email.");
                return;
            }
        }

        // ── Sucesso ───────────────────────────────────────
        clientes.add(cliente);
        MensagemUtils.exibir(TipoMensagem.SUCESSO, MODULO,
                "Cliente cadastrado com sucesso.");
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }
    public void editarCliente(int id, String novoNome,
                              String novoTelefone, String novoEmail) {

        for (Cliente cli : clientes) {

            if (cli.getId() == id) {

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

                // ── Email ─────────────────────────────────────
                if (ValidacaoUtils.textoVazio(novoEmail)) {
                    MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                            "Email não informado.");
                    return;
                }

                if (!ValidacaoUtils.emailFormatoValido(novoEmail)) {
                    MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                            "Email inválido. Use o formato exemplo@dominio.com.");
                    return;
                }

                for (Cliente outro : clientes) {
                    if (outro.getId() != id &&
                            outro.getEmail().equalsIgnoreCase(novoEmail)) {
                        MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                                "Já existe um cliente com esse email.");
                        return;
                    }
                }

                // ── Aplica ────────────────────────────────────
                cli.setNome(novoNome);
                cli.setTelefone(novoTelefone);
                cli.setEmail(novoEmail);

                MensagemUtils.exibir(TipoMensagem.SUCESSO, MODULO,
                        "Cliente atualizado com sucesso.");
                return;
            }
        }

        MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                "Cliente não encontrado. ID: " + id);
    }

    public void removerCliente(int id) {

        for (Cliente cli : clientes) {

            if (cli.getId() == id) {
                clientes.remove(cli);
                MensagemUtils.exibir(TipoMensagem.SUCESSO, MODULO,
                        "Cliente removido com sucesso.");
                return;
            }
        }

        MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                "Cliente não encontrado. ID: " + id);
    }
}