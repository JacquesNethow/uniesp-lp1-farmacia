import java.time.LocalDate;

import enums.CategoriaFuncionario;
import enums.StatusMedicamento;

import model.Cliente;
import model.Fornecedor;
import model.Funcionario;
import model.Medicamento;
import model.Venda;

import service.ClienteService;
import service.FornecedorService;
import service.FuncionarioService;
import service.MedicamentoService;
import service.VendaService;

public class Main {

    static ClienteService clienteService         = new ClienteService();
    static FornecedorService fornecedorService   = new FornecedorService();
    static FuncionarioService funcionarioService = new FuncionarioService();
    static MedicamentoService medicamentoService = new MedicamentoService();
    static VendaService vendaService             = new VendaService();

    public static void main(String[] args) {

        testarClientes();
        testarFornecedores();
        testarFuncionarios();
        testarMedicamentos();
        testarVendas();
        testarLogin();
        testarRelatorios();
        resultadoFinal();
    }

    // ═════════════════════════════════════════════════
    // CLIENTES
    // ═════════════════════════════════════════════════

    static void testarClientes() {

        System.out.println("\n========== TESTES CLIENTE ==========");

        clienteService.cadastrarCliente(new Cliente(
                1, "João Silva", "99999-9999",
                "joao@email.com", "123.456.789-00"));

        clienteService.cadastrarCliente(new Cliente(
                2, "", "98888-8888",
                "maria@email.com", "987.654.321-00"));

        clienteService.cadastrarCliente(new Cliente(
                3, "Jo", "98888-8888",
                "maria@email.com", "987.654.321-00"));

        clienteService.cadastrarCliente(new Cliente(
                4, "Carlos123", "97777-7777",
                "carlos@email.com", "111.222.333-44"));

        clienteService.cadastrarCliente(new Cliente(
                5, "Ana Lima", "96666-6666",
                "ana@email.com", "12345678900"));

        clienteService.cadastrarCliente(new Cliente(
                6, "Clone Silva", "95555-5555",
                "clone@email.com", "123.456.789-00"));

        clienteService.cadastrarCliente(new Cliente(
                7, "Bruno Costa", "94444-4444",
                "bruno-sem-arroba", "222.333.444-55"));

        clienteService.cadastrarCliente(new Cliente(
                8, "Maria Oliveira", "92222-2222",
                "maria@email.com", "987.654.321-00"));

        System.out.println("\n--- Editar e Remover ---");

        clienteService.editarCliente(1, "João Santos",
                "99999-0000", "joao@email.com");

        clienteService.editarCliente(99, "Inexistente",
                "00000-0000", "x@email.com");

        clienteService.removerCliente(8);
        clienteService.removerCliente(99);
    }

    // ═════════════════════════════════════════════════
    // FORNECEDORES
    // ═════════════════════════════════════════════════

    static void testarFornecedores() {

        System.out.println("\n========== TESTES FORNECEDOR ==========");

        fornecedorService.cadastrarFornecedor(new Fornecedor(
                1, "Neo Química", "96666-6666",
                "neo@email.com", "11.222.333/0001-44"));

        fornecedorService.cadastrarFornecedor(new Fornecedor(
                2, "", "95555-5555",
                "medley@email.com", "99.888.777/0001-55"));

        fornecedorService.cadastrarFornecedor(new Fornecedor(
                3, "AB", "95555-5555",
                "medley@email.com", "99.888.777/0001-55"));

        fornecedorService.cadastrarFornecedor(new Fornecedor(
                4, "Medley", "95555-5555",
                "medley@email.com", "99888777000155"));

        fornecedorService.cadastrarFornecedor(new Fornecedor(
                5, "Neo Cópia", "94444-4444",
                "copia@email.com", "11.222.333/0001-44"));

        fornecedorService.cadastrarFornecedor(new Fornecedor(
                6, "EMS", "93333-3333",
                "ems@email.com", "33.444.555/0001-66"));

        System.out.println("\n--- Editar e Remover ---");

        fornecedorService.editarFornecedor(1, "Neo Química Ltda.",
                "96666-1111", "neo@email.com");

        fornecedorService.editarFornecedor(99, "Inexistente",
                "00000-0000", "x@email.com");

        fornecedorService.removerFornecedor(6);
        fornecedorService.removerFornecedor(99);
    }

    // ═════════════════════════════════════════════════
    // FUNCIONÁRIOS
    // ═════════════════════════════════════════════════

    static void testarFuncionarios() {

        System.out.println("\n========== TESTES FUNCIONÁRIO ==========");

        funcionarioService.cadastrarFuncionario(new Funcionario(
                1, "Maria Souza", "93333-3333",
                "maria@email.com", "maria_souza",
                "senha1", CategoriaFuncionario.GERENTE));

        funcionarioService.cadastrarFuncionario(new Funcionario(
                2, "", "92222-2222",
                "pedro@email.com", "pedro",
                "senha2", CategoriaFuncionario.CAIXA));

        funcionarioService.cadastrarFuncionario(new Funcionario(
                3, "Pedro Santos", "92222-2222",
                "pedro@email.com", "pedro santos",
                "senha2", CategoriaFuncionario.CAIXA));

        funcionarioService.cadastrarFuncionario(new Funcionario(
                4, "Maria Clone", "91111-1111",
                "clone@email.com", "maria_souza",
                "senha3", CategoriaFuncionario.FARMACEUTICO));

        funcionarioService.cadastrarFuncionario(new Funcionario(
                5, "Ana Paula", "90000-0000",
                "ana@email.com", "ana_paula",
                "abcdef", CategoriaFuncionario.FARMACEUTICO));

        funcionarioService.cadastrarFuncionario(new Funcionario(
                6, "Pedro Santos", "92222-2222",
                "pedro@email.com", "pedro_s",
                "pedro1", CategoriaFuncionario.CAIXA));

        System.out.println("\n--- Editar e Remover ---");

        funcionarioService.editarFuncionario(1, "Maria Souza Silva",
                "93333-0000", "maria@email.com",
                CategoriaFuncionario.GERENTE);

        funcionarioService.editarFuncionario(99, "Inexistente",
                "00000-0000", "x@email.com",
                CategoriaFuncionario.CAIXA);

        funcionarioService.removerFuncionario(6);
        funcionarioService.removerFuncionario(99);
    }

    // ═════════════════════════════════════════════════
    // MEDICAMENTOS
    // ═════════════════════════════════════════════════

    static void testarMedicamentos() {

        System.out.println("\n========== TESTES MEDICAMENTO ==========");

        medicamentoService.cadastrarMedicamento(new Medicamento(
                1, "Dipirona", "Neo Química",
                10.0, 20,
                LocalDate.of(2027, 6, 1),
                StatusMedicamento.DISPONIVEL));

        medicamentoService.cadastrarMedicamento(new Medicamento(
                2, "Dipirona", "EMS",
                15.0, 10,
                LocalDate.of(2027, 3, 1),
                StatusMedicamento.DISPONIVEL));

        medicamentoService.cadastrarMedicamento(new Medicamento(
                3, "Paracetamol", "Medley",
                -5.0, 30,
                LocalDate.of(2027, 1, 1),
                StatusMedicamento.DISPONIVEL));

        medicamentoService.cadastrarMedicamento(new Medicamento(
                4, "Ibuprofeno", "Medley",
                8.50, -5,
                LocalDate.of(2027, 1, 1),
                StatusMedicamento.DISPONIVEL));

        medicamentoService.cadastrarMedicamento(new Medicamento(
                5, "Amoxicilina", "Neo Química",
                12.0, 15,
                LocalDate.of(2020, 1, 1),
                StatusMedicamento.DISPONIVEL));

        medicamentoService.cadastrarMedicamento(new Medicamento(
                6, "Omeprazol", "EMS",
                9.99, 25,
                LocalDate.now().plusDays(30),
                StatusMedicamento.DISPONIVEL));

        medicamentoService.cadastrarMedicamento(new Medicamento(
                7, "Paracetamol", "Medley",
                7.50, 40,
                LocalDate.of(2027, 8, 1),
                StatusMedicamento.DISPONIVEL));

        System.out.println("\n--- Editar e Remover ---");

        medicamentoService.editarMedicamento(1, 11.50, 20,
                LocalDate.of(2027, 6, 1));

        medicamentoService.editarMedicamento(1, -1.0, 20,
                LocalDate.of(2027, 6, 1));

        medicamentoService.editarMedicamento(99, 10.0, 10,
                LocalDate.of(2027, 1, 1));

        medicamentoService.removerMedicamento(7);
        medicamentoService.removerMedicamento(99);
    }

    // ═════════════════════════════════════════════════
    // VENDAS
    // ═════════════════════════════════════════════════

    static void testarVendas() {

        System.out.println("\n========== TESTES VENDA ==========");

        Cliente joao          = clienteService.listarClientes().get(0);
        Funcionario maria     = funcionarioService.listarFuncionarios().get(0);
        Medicamento dipirona  = medicamentoService.listarMedicamentos().get(0);
        Medicamento omeprazol = medicamentoService.listarMedicamentos().get(1);

        vendaService.realizarVenda(new Venda(
                1, joao, maria, dipirona,
                2, 0, LocalDate.now()));

        vendaService.realizarVenda(new Venda(
                2, null, maria, dipirona,
                1, 0, LocalDate.now()));

        vendaService.realizarVenda(new Venda(
                3, joao, null, dipirona,
                1, 0, LocalDate.now()));

        vendaService.realizarVenda(new Venda(
                4, joao, maria, null,
                1, 0, LocalDate.now()));

        vendaService.realizarVenda(new Venda(
                5, joao, maria, dipirona,
                0, 0, LocalDate.now()));

        vendaService.realizarVenda(new Venda(
                6, joao, maria, dipirona,
                100, 0, LocalDate.now()));

        vendaService.realizarVenda(new Venda(
                7, joao, maria, omeprazol,
                1, 0, LocalDate.now()));

        System.out.println("\n--- Comprovante ---");

        vendaService.gerarComprovante(1);
        vendaService.gerarComprovante(99);

        System.out.println("\n--- Remover ---");

        vendaService.removerVenda(2);
        vendaService.removerVenda(99);
    }

    // ═════════════════════════════════════════════════
    // LOGIN
    // ═════════════════════════════════════════════════

    static void testarLogin() {

        System.out.println("\n========== TESTES LOGIN ==========");

        funcionarioService.autenticar("", "senha1");
        funcionarioService.autenticar("maria_souza", "");
        funcionarioService.autenticar("fantasma", "senha1");
        funcionarioService.autenticar("maria_souza", "errada");
        funcionarioService.autenticar("maria_souza", "senha1");
    }

    // ═════════════════════════════════════════════════
    // RELATÓRIOS
    // ═════════════════════════════════════════════════

    static void testarRelatorios() {

        System.out.println("\n========== RELATÓRIOS ==========");

        vendaService.relatorioEstoque(
                medicamentoService.listarMedicamentos());

        vendaService.relatorioVendas();

        vendaService.relatorioProdutosVencidos(
                medicamentoService.listarMedicamentos());

        vendaService.relatorioLucroMensal(
                LocalDate.now().getMonthValue(),
                LocalDate.now().getYear());
    }

    // ═════════════════════════════════════════════════
    // RESULTADO FINAL
    // ═════════════════════════════════════════════════

    static void resultadoFinal() {

        System.out.println("\n========== RESULTADO FINAL ==========");

        System.out.println("Clientes cadastrados:     "
                + clienteService.listarClientes().size());
        System.out.println("Fornecedores cadastrados: "
                + fornecedorService.listarFornecedores().size());
        System.out.println("Funcionários cadastrados: "
                + funcionarioService.listarFuncionarios().size());
        System.out.println("Medicamentos cadastrados: "
                + medicamentoService.listarMedicamentos().size());
        System.out.println("Vendas realizadas:        "
                + vendaService.listarVendas().size());

        System.out.println("\n--- Estoque final ---");

        for (Medicamento m : medicamentoService.listarMedicamentos()) {
            System.out.println(m.getNome() + ": "
                    + m.getEstoque() + " unidades");
        }

        System.out.println();
    }
}