package service;

import model.Medicamento;
import model.Venda;
import utils.DataUtils;
import utils.MensagemUtils;
import enums.TipoMensagem;

import java.util.ArrayList;
import java.util.List;

public class VendaService {

    private static final String MODULO = "VENDA";

    private List<Venda> vendas;

    public VendaService() {
        this.vendas = new ArrayList<>();
    }

    public void realizarVenda(Venda venda) {

        if (venda == null) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Venda não informada.");
            return;
        }

        if (venda.getCliente() == null) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Cliente não informado.");
            return;
        }

        if (venda.getFuncionario() == null) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Funcionário não informado.");
            return;
        }

        if (venda.getMedicamento() == null) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Medicamento não informado.");
            return;
        }

        Medicamento medicamento = venda.getMedicamento();

        if (DataUtils.medicamentoVencido(medicamento.getValidade())) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Não é possível vender medicamento vencido. Validade: "
                            + medicamento.getValidade() + ".");
            return;
        }

        if (DataUtils.validadeProxima(medicamento.getValidade(), 90)) {
            MensagemUtils.exibir(TipoMensagem.AVISO, MODULO,
                    "Medicamento vence em menos de 90 dias ("
                            + medicamento.getValidade() + ").");
        }

        if (venda.getQuantidade() <= 0) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Quantidade inválida. Deve ser maior que zero.");
            return;
        }

        if (medicamento.getEstoque() == 0) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Medicamento sem estoque: " + medicamento.getNome() + ".");
            return;
        }

        if (medicamento.getEstoque() < venda.getQuantidade()) {
            MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                    "Estoque insuficiente. Disponível: "
                            + medicamento.getEstoque()
                            + ", solicitado: "
                            + venda.getQuantidade() + ".");
            return;
        }

        medicamento.setEstoque(
                medicamento.getEstoque() - venda.getQuantidade()
        );

        venda.setValorTotal(
                medicamento.getPreco() * venda.getQuantidade()
        );

        vendas.add(venda);

        MensagemUtils.exibir(TipoMensagem.SUCESSO, MODULO,
                "Venda realizada. Total: R$"
                        + String.format("%.2f", venda.getValorTotal()) + ".");

        if (medicamento.getEstoque() == 0) {
            MensagemUtils.exibir(TipoMensagem.AVISO, MODULO,
                    "Estoque zerado após a venda: " + medicamento.getNome() + ".");
        }
    }

    public List<Venda> listarVendas() {
        return vendas;
    }

    public void removerVenda(int id) {

        for (Venda v : vendas) {

            if (v.getId() == id) {
                vendas.remove(v);
                MensagemUtils.exibir(TipoMensagem.SUCESSO, MODULO,
                        "Venda removida com sucesso.");
                return;
            }
        }

        MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                "Venda não encontrada. ID: " + id);
    }

    public void gerarComprovante(int id) {

        for (Venda v : vendas) {

            if (v.getId() == id) {

                String nomeCliente = v.getCliente() != null
                        ? v.getCliente().getNome()
                        : "Consumidor";

                System.out.println();
                System.out.println("╔══════════════════════════════════════╗");
                System.out.println("║         FARMÁCIA SAÚDE E VIDA        ║");
                System.out.println("╠══════════════════════════════════════╣");
                System.out.printf( "║ Venda #%-5d  Data: %-16s║%n",
                        v.getId(), v.getDataVenda());
                System.out.printf( "║ Cliente:  %-27s║%n", nomeCliente);
                System.out.printf( "║ Atendente:%-27s║%n", v.getFuncionario().getNome());
                System.out.println("╠══════════════════════════════════════╣");
                System.out.printf( "║ %-20s %5s  %8s ║%n", "Produto", "Qtd", "Subtotal");
                System.out.println("╠══════════════════════════════════════╣");
                System.out.printf( "║ %-20s %5d  R$%6.2f ║%n",
                        v.getMedicamento().getNome(),
                        v.getQuantidade(),
                        v.getValorTotal());
                System.out.println("╠══════════════════════════════════════╣");
                System.out.printf( "║ TOTAL:                      R$%6.2f ║%n",
                        v.getValorTotal());
                System.out.println("╚══════════════════════════════════════╝");
                System.out.println();
                return;
            }
        }

        MensagemUtils.exibir(TipoMensagem.ERRO, MODULO,
                "Venda não encontrada. ID: " + id);
    }

    public void relatorioEstoque(List<Medicamento> medicamentos) {

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println( "║               RELATÓRIO DE ESTOQUE              ║");
        System.out.println( "╠══════════════════════════════════════════════════╣");
        System.out.printf(  "║ %-20s %8s %18s ║%n", "Medicamento", "Estoque", "Validade");
        System.out.println( "╠══════════════════════════════════════════════════╣");

        for (Medicamento m : medicamentos) {
            String alerta = m.getEstoque() == 0 ? " ⚠ SEM ESTOQUE" : "";
            System.out.printf("║ %-20s %8d %18s ║%n",
                    m.getNome(), m.getEstoque(), m.getValidade() + alerta);
        }

        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    public void relatorioVendas() {

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println( "║               RELATÓRIO DE VENDAS               ║");
        System.out.println( "╠══════════════════════════════════════════════════╣");
        System.out.printf(  "║ %-4s %-15s %-15s %10s ║%n",
                "ID", "Cliente", "Medicamento", "Total");
        System.out.println( "╠══════════════════════════════════════════════════╣");

        double totalGeral = 0;

        for (Venda v : vendas) {

            String nomeCliente = v.getCliente() != null
                    ? v.getCliente().getNome()
                    : "Consumidor";

            System.out.printf("║ %-4d %-15s %-15s R$%7.2f ║%n",
                    v.getId(),
                    nomeCliente,
                    v.getMedicamento().getNome(),
                    v.getValorTotal());

            totalGeral += v.getValorTotal();
        }

        System.out.println( "╠══════════════════════════════════════════════════╣");
        System.out.printf(  "║ TOTAL ARRECADADO:                    R$%8.2f ║%n", totalGeral);
        System.out.println( "╚══════════════════════════════════════════════════╝");
    }

    public void relatorioProdutosVencidos(List<Medicamento> medicamentos) {

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println( "║            RELATÓRIO DE PRODUTOS VENCIDOS       ║");
        System.out.println( "╠══════════════════════════════════════════════════╣");

        boolean encontrou = false;

        for (Medicamento m : medicamentos) {

            if (DataUtils.medicamentoVencido(m.getValidade())) {
                System.out.printf("║ %-25s Vencimento: %-12s ║%n",
                        m.getNome(), m.getValidade());
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("║       Nenhum produto vencido no estoque.         ║");
        }

        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    public void relatorioLucroMensal(int mes, int ano) {

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.printf( "║        RELATÓRIO DE LUCRO — %02d/%04d            ║%n", mes, ano);
        System.out.println("╠══════════════════════════════════════════════════╣");

        double totalMes = 0;
        int totalVendas = 0;

        for (Venda v : vendas) {

            if (v.getDataVenda().getMonthValue() == mes
                    && v.getDataVenda().getYear() == ano) {

                totalMes += v.getValorTotal();
                totalVendas++;
            }
        }

        System.out.printf("║ Vendas no período:  %28d ║%n", totalVendas);
        System.out.printf("║ Total arrecadado:               R$%13.2f ║%n", totalMes);
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
}