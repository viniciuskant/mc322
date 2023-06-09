/*O Menu e submenus desse lab foram baseados no disponível em https://github.com/rebecapadovani/ExemploEnumMenu*/
public class Main {
	// Metodo que instancia os objetos que o lab pede
	private static void instanciar() {
		ClientePF clientepf = new ClientePF(null, null, null, null, null, null, null, null);
		ClientePJ clientepj =  new ClientePJ(null, null, null, null, null, null);
		Condutor condutor1 = new Condutor(null, null, null, null, null, null);
		Condutor condutor2 = new Condutor(null, null, null, null, null, null);
		Condutor condutor3 = new Condutor(null, null, null, null, null, null);
		Seguradora seguradora = new Seguradora(null, null, null, null, null);

		// Instancio os veiculos para a frota1
		Veiculo frota1A = new Veiculo("111-AAAA", "1A", "-1A", 1990);
		Veiculo frota1B = new Veiculo("111-BBBB", "1B", "-1B", 1991);
		Veiculo frota1C = new Veiculo("111-CCCC", "1C", "-1C", 1992);
		Frota frota1 = new Frota();
		frota1.addVeiculo(frota1A);
		frota1.addVeiculo(frota1B);
		frota1.addVeiculo(frota1C);

		// Instancio os veiculos para a frota2
		Veiculo frota2A = new Veiculo("222-AAAA", "[2A", "-2A", 1990);
		Veiculo frota2B = new Veiculo("222-BBBB", "2B", "-2B", 1991);
		Veiculo frota2C = new Veiculo("222-CCCC", "2C", "-2C", 1992);
		Frota frota2 = new Frota();
		frota2.addVeiculo(frota2A);
		frota2.addVeiculo(frota2B);
		frota2.addVeiculo(frota2C);

		// Instancio os veiculos para a a lista de veiculos
		Veiculo veiculo1 = new Veiculo("aaa-1111", "V1", "-V1", 2000);
		Veiculo veiculo2 = new Veiculo("bbb-1111", "V2", "-V2", 2001);
		Veiculo veiculo3 = new Veiculo("ccc-1111", "V3", "-V3", 2002);

		// Instancio PF
		if(Validacao.validarCPF("452.044.120-00")){
			clientepf = new ClientePF("João", "Campinas", "99999-9999" , "PF@cliente.com.br", "Medio completo", "Masculino", "452.044.120-00", new Date(10, 10, 1990));
			clientepf.CadastrarVeiculo(veiculo1);
			clientepf.CadastrarVeiculo(veiculo2);
			clientepf.CadastrarVeiculo(veiculo3);
		}

		// Instancio PJ
		if(Validacao.validarCNPJ("28414146000100")){
			clientepj = new ClientePJ("J&M", "Campinas", "99999-9999" , "PJ@cliente.com.br", "28414146000100", new Date(20, 05, 2000));
			clientepj.cadastarFrota(frota1);
			clientepj.cadastarFrota(frota2);
		}


		// Instancio uma Seguradora
		if (Validacao.validarCNPJ("46.068.666/0001-82")) {
			seguradora = new Seguradora("Unicamp-seguros", "46.068.666/0001-82", "35 99999-9999", "Campinas",
					"unicamp@dac.unicamp.br");
			seguradora.cadastrarCliente(clientepf);
			seguradora.cadastrarCliente(clientepj);
		}

		// Intancio os sinistros
		Sinistro sinistro1 = new Sinistro(new Date(10, 6, 2012), "Salvador");
		Sinistro sinistro2 = new Sinistro(new Date(7, 4, 2008), "Santos");
		Sinistro sinistro3 = new Sinistro(new Date(3, 2, 2013), "Santa Rita");
		Sinistro sinistro4 = new Sinistro(new Date(4, 2, 2018), "Pouso Alegre");

		// Instancio condutor
		if (Validacao.validarCPF("039.840.120-98")){
			condutor1 =  new Condutor("Maria", "039.840.120-98", "98888-8888", "Sao Paulo", "condutor1condutor.com.br", new Date(12, 9, 1971));
			condutor1.adicionarSinistro(sinistro1);
			sinistro1.setCondutor(condutor1);
			condutor1.adicionarSinistro(sinistro2);
			sinistro2.setCondutor(condutor1);
		}

		if(Validacao.validarCPF("452.044.120-00")){ // São os mesmos dados do clientepf, já que ele também é um condutor
			condutor2 = new Condutor("João", "172.298.960-29", "99999-9999" , "Campinas", "Masculino", new Date(10, 10, 1990));
			condutor2.adicionarSinistro(sinistro3);
			sinistro3.setCondutor(condutor2);
			condutor2.adicionarSinistro(sinistro4);
			sinistro4.setCondutor(condutor2);
		}

		if (Validacao.validarCPF("172.298.960-29")){
			condutor3 =  new Condutor("Paulo", "172.298.960-29", "97777-77777", "Borta da Mata", "condutor2condutor.com.br", new Date(2, 8, 1986));
		}

		// Instancio um seguro PF
		SeguroPF seguroPF = new SeguroPF(new Date(6, 6, 2023), new Date(6, 6, 2024), seguradora, clientepf);
		seguroPF.setVeiculo(veiculo1);
		seguroPF.gerarSinistro(sinistro3);
		seguroPF.gerarSinistro(sinistro4);
		seguroPF.autorizarCondutor(condutor2);
		seguroPF.autorizarCondutor(condutor3);

		// Instancio um seguro PJ
		SeguroPJ seguroPJ = new SeguroPJ(new Date(4, 6, 2023), new Date(4, 6, 2024), seguradora, frota1, clientepj);
		seguroPJ.gerarSinistro(sinistro1);
		seguroPJ.gerarSinistro(sinistro2);
		seguroPJ.autorizarCondutor(condutor1);

		System.out.println(frota1.toString());
		System.out.println(veiculo1.toString());
		System.out.println(clientepf.toString());
		System.out.println(clientepj.toString());
		System.out.println(seguradora.toString());
		System.out.println(sinistro1.toString());
		System.out.println(condutor1.toString());
		System.out.println(seguroPF.toString());
		System.out.println(seguroPJ.toString());
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
		instanciar();
		MenuOpcoes op;
		do {
			exibirMenuExterno();
			op = lerOpcaoMenuExterno();
			executarOpcaoMenuExterno(op);
		} while (op != MenuOpcoes.SAIR);
		System.out.println("Saiu do sistema");
	}
}