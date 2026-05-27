NAME = farmacia

JC = javac
JAVA = java

SRC =	src/Main.java								\
		src/controller/ClienteController.java			\
		src/controller/FornecedorController.java		\
		src/controller/FuncionarioController.java		\
		src/controller/MedicamentoController.java		\
		src/controller/VendaController.java				\
		src/enums/CategoriaFuncionario.java				\
		src/enums/StatusMedicamento.java				\
		src/enums/TipoMensagem.java						\
		src/model/Pessoa.java							\
		src/model/Cliente.java							\
		src/model/Fornecedor.java						\
		src/model/Funcionario.java						\
		src/model/Medicamento.java						\
		src/model/Venda.java							\
		src/service/ClienteService.java					\
		src/service/FornecedorService.java				\
		src/service/FuncionarioService.java				\
		src/service/MedicamentoService.java				\
		src/service/VendaService.java					\
		src/utils/DataUtils.java						\
		src/utils/MensagemUtils.java					\
		src/utils/ValidacaoUtils.java

all:
	$(JC) -cp src $(SRC)

run: all
	cd src && $(JAVA) Main

clean:
	find src -name "*.class" -delete

fclean: clean

re: fclean all

.PHONY: all run clean fclean re