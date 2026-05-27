package service;

import model.Fornecedor;
import utils.MensagemUtils;
import utils.ValidacaoUtils;
import enums.TipoMensagem;

import java.util.ArrayList;
import java.util.List;

public class FornecedorService {

    private static final String MODULO = "FORNECEDOR";

    private List<Fornecedor> fornecedores;

    public FornecedorService() {
        this.fornecedores = new ArrayList<>();
    }

    public void cadastrarFornecedor(Fornecedor fornecedor) {

        if (fornecedor == null) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Fornecedor não informado.");
            return;
        }

        // ── Nome ──────────────────────────────────────────
        if (ValidacaoUtils.textoVazio(fornecedor.getNome())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Nome não informado.");
            return;
        }

        if (fornecedor.getNome().trim().length() < 3) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Nome muito curto. Mínimo 3 caracteres.");
            return;
        }

        // Fornecedor é empresa: aceita letras, números, espaços e & . - '
        if (!fornecedor.getNome().matches("[a-zA-ZÀ-ú0-9 &.'\\-]+")) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Nome inválido. Caracteres não permitidos.");
            return;
        }

        // ── CNPJ ──────────────────────────────────────────
        if (ValidacaoUtils.textoVazio(fornecedor.getCnpj())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "CNPJ não informado.");
            return;
        }

        if (!ValidacaoUtils.cnpjFormatoValido(fornecedor.getCnpj())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "CNPJ inválido. Use o formato 00.000.000/0000-00.");
            return;
        }

        for (Fornecedor forn : fornecedores) {
            if (forn.getCnpj().equals(fornecedor.getCnpj())) {
                MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                        "Já existe um fornecedor com esse CNPJ.");
                return;
            }
        }

        // ── Email ─────────────────────────────────────────
        if (ValidacaoUtils.textoVazio(fornecedor.getEmail())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Email não informado.");
            return;
        }

        if (!ValidacaoUtils.emailFormatoValido(fornecedor.getEmail())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Email inválido. Use o formato exemplo@dominio.com.");
            return;
        }

        for (Fornecedor forn : fornecedores) {
            if (forn.getEmail().equalsIgnoreCase(fornecedor.getEmail())) {
                MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                        "Já existe um fornecedor com esse email.");
                return;
            }
        }

        // ── Sucesso ───────────────────────────────────────
        fornecedores.add(fornecedor);
        MensagemUtils.exibir(TipoMensagem.SUCESSO, MODULO,
                "Fornecedor cadastrado com sucesso.");
    }

    public List<Fornecedor> listarFornecedores() {
        return fornecedores;
    }

    public void editarFornecedor(int id, String novoNome,
                                 String novoTelefone, String novoEmail) {

        for (Fornecedor forn : fornecedores) {

            if (forn.getId() == id) {

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

                if (!novoNome.matches("[a-zA-ZÀ-ú0-9 &.'\\-]+")) {
                    MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                            "Nome inválido. Caracteres não permitidos.");
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

                for (Fornecedor outro : fornecedores) {
                    if (outro.getId() != id &&
                            outro.getEmail().equalsIgnoreCase(novoEmail)) {
                        MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                                "Já existe um fornecedor com esse email.");
                        return;
                    }
                }

                // ── Aplica ────────────────────────────────────
                forn.setNome(novoNome);
                forn.setTelefone(novoTelefone);
                forn.setEmail(novoEmail);

                MensagemUtils.exibir(TipoMensagem.SUCESSO, MODULO,
                        "Fornecedor atualizado com sucesso.");
                return;
            }
        }

        MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                "Fornecedor não encontrado. ID: " + id);
    }

    public void removerFornecedor(int id) {

        for (Fornecedor forn : fornecedores) {

            if (forn.getId() == id) {
                fornecedores.remove(forn);
                MensagemUtils.exibir(TipoMensagem.SUCESSO, MODULO,
                        "Fornecedor removido com sucesso.");
                return;
            }
        }

        MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                "Fornecedor não encontrado. ID: " + id);
    }
}