import java.util.ArrayList;

/*O Menu e submenus desse lab foram baseados no disponível em https://github.com/rebecapadovani/ExemploEnumMenu*/

public class Main {
	private static ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
	private static ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
	private static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	private static ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

	// Metodo que instancia os objetos que o lab pede
	private static void instanciar() {

	}

	// Metodo que converte String em inteiro
	private static int INT(String string) {
		return Integer.parseInt(string);
	}

	// Cadastra o cliente e o adiciona na lista de cliente e se pedido o adiciona a
	// uma seguradora
	private static void cadastrarCliente() {

	}

	// Cadastra o veiculo e o adiciona na lista de veiculos e se pedido o adiciona a
	// um cliente
	private static void cadastrarVeiculo() {

	}

	// Cadastra uma seguradora e adiciona na lista de seguradoras
	private static void cadastrarSeguradora() {

	}

	// Gera um sinisto e o vincula a um cliente
	private static void gerarSinistro() {

	}

	// Exclui um cliente desejado
	private static void excluirCliente() {
	}

	private static String formatarString(String string) {
		String separado[] = string.split("\n"), retorno = "";
		for (int i = 0; i < separado.length; i++) {
			retorno += Integer.toString(i) + " - " + separado[i] + "\n";
		}
		return retorno;
	}

	// Exclui o veiculo de um determinado cliente
	private static void excluirVeiculo() {

	}

	// Excluir o sinistro de um determinado cliente
	private static void excluirSinistro() {

	}

	// Imprimi a receita da seguradora escolhida
	private static void calcularReceira() {

	}

	// Transfere a lista de veiculos de um cliente para outro
	private static void transferirSeguro() {
		
	}

	// exibir menu externo
	private static void exibirMenuExterno() {
		MenuOpcoes menuOpcoes[] = MenuOpcoes.values();
		System.out.println("Menu principal");
		for (MenuOpcoes op : menuOpcoes) {
			System.out.println(op.ordinal() + " - " + op.getDescricao());
		}
	}

	/*
	 * exibir submenus
	 * se a lista de constantes do submenu for percorrida da mesma forma que o meu
	 * externo, a opção Voltar
	 * é printada com a posição que está na lista do enum (9 - Voltar). Por isso, a
	 * lista é percorrida
	 * de forma diferente, tendo i como o inteiro correspondente. Assim, para listar
	 * o submenu de cadastros,
	 * por exemplo, vai ser printado "3 - Voltar".
	 */
	private static void exibirSubmenu(MenuOpcoes op) {
		SubmenuOpcoes[] submenu = op.getSubmenu();
		System.out.println(op.getDescricao());
		for (int i = 0; i < submenu.length; i++) {
			System.out.println(i + " - " + submenu[i].getDescricao());
		}
	}

	// ler opções do menu externo
	private static MenuOpcoes lerOpcaoMenuExterno() {
		String opUsuario;
		MenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = Leitura.lerString();
		} while (!Validacao.ehInteiro(opUsuario)
				|| (INT(opUsuario) < 0 || INT(opUsuario) > MenuOpcoes.values().length - 1));
		opUsuarioConst = MenuOpcoes.values()[INT(opUsuario)];
		return opUsuarioConst;
	}

	// ler opção dos submenus
	private static SubmenuOpcoes lerOpcaoSubmenu(MenuOpcoes op) {
		String opUsuario;
		SubmenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = Leitura.lerString();
		} while (!Validacao.ehInteiro(opUsuario)
				|| (INT(opUsuario) < 0 || INT(opUsuario) > op.getSubmenu().length - 1));
		opUsuarioConst = op.getSubmenu()[INT(opUsuario)];
		return opUsuarioConst;
	}

	// executar opções do menu externo
	private static void executarOpcaoMenuExterno(MenuOpcoes op) {
		switch (op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op);
				break;
			case GERAR_SINISTRO:
				gerarSinistro();
				break;
			case CALCULAR_RECEITA:
				calcularReceira();
				break;
			// case SAIR:
		}
	}

	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu) {
		switch (opSubmenu) {
			case CADASTRAR_CLIENTE:
				cadastrarCliente();
				break;
			case CADASTRAR_VEICULO:
				cadastrarVeiculo();
				break;
			case CADASTRAR_SEGURADORA:
				cadastrarSeguradora();
				break;
			case EXCLUIR_CLIENTE:
				excluirCliente();
				break;
			case EXCLUIR_VEICULO:
				excluirVeiculo();
				;
				break;
			case EXCLUIR_SINISTRO:
				excluirSinistro();
				;
				break;
			// case VOLTAR:
			// break;
		}
	}

	// executa os submenus: exibição do menu, leitura da opção e execução dos
	// métodos
	private static void executarSubmenu(MenuOpcoes op) {
		SubmenuOpcoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op);
			executarOpcaoSubMenu(opSubmenu);
		} while (opSubmenu != SubmenuOpcoes.VOLTAR);
	}

	// executa o menu externo: exibição do menu, leitura da opção e execução da
	// opção
	public static void main(String[] args) {
		MenuOpcoes op;
		instanciar();
		do {
			exibirMenuExterno();
			op = lerOpcaoMenuExterno();
			executarOpcaoMenuExterno(op);
		} while (op != MenuOpcoes.SAIR);
		System.out.println("Saiu do sistema");
	}
}