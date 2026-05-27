package service;

import model.Medicamento;
import utils.DataUtils;
import utils.MensagemUtils;
import utils.ValidacaoUtils;
import enums.TipoMensagem;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class MedicamentoService {

    private static final String MODULO = "MEDICAMENTO";

    private List<Medicamento> medicamentos;

    public MedicamentoService() {

        this.medicamentos = new ArrayList<>();

    }

    public void cadastrarMedicamento(Medicamento medicamento) {

        if (medicamento == null) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Medicamento não informado.");
            return;
        }

        // ── Nome ──────────────────────────────────────────
        if (ValidacaoUtils.textoVazio(medicamento.getNome())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Nome não informado.");
            return;
        }

        if (medicamento.getNome().trim().length() < 3) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Nome muito curto. Mínimo 3 caracteres.");
            return;
        }

        for (Medicamento med : medicamentos) {
            if (med.getNome().equalsIgnoreCase(medicamento.getNome())) {
                MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                        "Já existe um medicamento com esse nome.");
                return;
            }
        }

        // ── Preço ─────────────────────────────────────────
        if (!ValidacaoUtils.numeroPositivo(medicamento.getPreco())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Preço inválido. Deve ser maior que zero.");
            return;
        }

        // ── Estoque ───────────────────────────────────────
        if (!ValidacaoUtils.estoqueValido(medicamento.getEstoque())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Estoque inválido. Não pode ser negativo.");
            return;
        }

        // ── Validade ──────────────────────────────────────
        if (!DataUtils.dataValida(medicamento.getValidade())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Data de validade não informada.");
            return;
        }

        if (DataUtils.medicamentoVencido(medicamento.getValidade())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Medicamento vencido. Data de validade: "
                            + medicamento.getValidade() + ".");
            return;
        }

        // ── Sucesso + aviso de validade próxima ───────────
        medicamentos.add(medicamento);
        MensagemUtils.exibir(TipoMensagem.SUCESSO, MODULO,
                "Medicamento cadastrado com sucesso.");

        if (DataUtils.validadeProxima(medicamento.getValidade(), 90)) {
            MensagemUtils.exibir(TipoMensagem.AVISO, MODULO,
                    "Atenção: medicamento vence em menos de 90 dias ("
                            + medicamento.getValidade() + ").");
        }
    }

    public List<Medicamento> listarMedicamentos() {

        return medicamentos;

    }

    public void editarMedicamento(int codigo, double novoPreco,
                                  int novoEstoque, LocalDate novaValidade) {

        for (Medicamento med : medicamentos) {

            if (med.getCodigo() == codigo) {

                // ── Preço ─────────────────────────────────────
                if (!ValidacaoUtils.numeroPositivo(novoPreco)) {
                    MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                            "Preço inválido. Deve ser maior que zero.");
                    return;
                }

                // ── Estoque ───────────────────────────────────
                if (!ValidacaoUtils.estoqueValido(novoEstoque)) {
                    MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                            "Estoque inválido. Não pode ser negativo.");
                    return;
                }

                // ── Validade ──────────────────────────────────
                if (!DataUtils.dataValida(novaValidade)) {
                    MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                            "Data de validade não informada.");
                    return;
                }

                if (DataUtils.medicamentoVencido(novaValidade)) {
                    MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                            "Data de validade inválida. Medicamento já vencido.");
                    return;
                }

                // ── Aplica ────────────────────────────────────
                med.setPreco(novoPreco);
                med.setEstoque(novoEstoque);
                med.setValidade(novaValidade);

                MensagemUtils.exibir(TipoMensagem.SUCESSO, MODULO,
                        "Medicamento atualizado com sucesso.");

                if (DataUtils.validadeProxima(novaValidade, 90)) {
                    MensagemUtils.exibir(TipoMensagem.AVISO, MODULO,
                            "Atenção: medicamento vence em menos de 90 dias ("
                                    + novaValidade + ").");
                }
                return;
            }
        }

        MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                "Medicamento não encontrado. Código: " + codigo);
    }

    public void removerMedicamento(int codigo) {

        for (Medicamento med : medicamentos) {

            if (med.getCodigo() == codigo) {
                medicamentos.remove(med);
                MensagemUtils.exibir(TipoMensagem.SUCESSO, MODULO,
                        "Medicamento removido com sucesso.");
                return;
            }
        }

        MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                "Medicamento não encontrado. Código: " + codigo);
    }
}