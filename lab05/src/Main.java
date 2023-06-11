import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	// Metodo que instancia os objetos que o lab pede
	private static void instanciar(	ArrayList<Seguradora> listaSeguradoras) {
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
		if (Validacao.validarCNPJ("54.829.973/0001-67")) {
			seguradora = new Seguradora("Unicamp-seguros", "54.829.973/0001-67", "35 99999-9999", "Campinas",
					"unicamp@dac.unicamp.br");
			seguradora.cadastrarCliente(clientepf);
			seguradora.cadastrarCliente(clientepj);
			listaSeguradoras.add(seguradora);
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
		seguradora.gerarSeguro(seguroPF);

		// Instancio um seguro PJ
		SeguroPJ seguroPJ = new SeguroPJ(new Date(4, 6, 2023), new Date(4, 6, 2024), seguradora, frota1, clientepj);
		seguroPJ.gerarSinistro(sinistro1);
		seguroPJ.gerarSinistro(sinistro2);
		seguroPJ.autorizarCondutor(condutor1);
		seguradora.gerarSeguro(seguroPJ);

		System.out.println("\nFrota 1" + frota1.toString() + "\n");
		System.out.println("Veiculo 1:\n" + veiculo1.toString() + "\n");
		System.out.println("Cliente PF" + clientepf.toString());
		System.out.println("Cliente PJ" + clientepj.toString());
		System.out.println("Seguradora:\n" + seguradora.toString());
		System.out.println("\nSinistro 1\n" + sinistro1.toString());
		System.out.println("\nCondutor 1\n" + condutor1.toString());
		System.out.println("\nSeguro PF\n" + ((SeguroPF)seguroPF).toString());
		System.out.println("\nSeguro PJ\n" + ((SeguroPJ)seguroPJ).toString() + "\n");
	}

	// Metodo que converte String em inteiro
	private static int INT(String string) {
		return Integer.parseInt(string);
	}

	// Listar todas as Seguradoras
	private static void listar(ArrayList<Seguradora> listaSeguradoras){
		System.out.println("Seguradoras:");

		// Imprimi todas as seguradoras
		for(int i = 0; i < listaSeguradoras.size(); i++){
			System.out.println(Integer.toString(i) + " - " + listaSeguradoras.get(i).getNome());
		}
	}

	// Lista todas Seguradoras cadastradas e retorna a seguradora escolhida
	private static Seguradora escolherSeguradora(ArrayList<Seguradora> listaSeguradoras){
		listar(listaSeguradoras);
		System.out.println("Escolha uma opcao:"); 
		String indice = lerString();

		// Enquanto o indice não for um inteiro e não estiver no intervalo de 0 até o tamanho da lista
		while(true){
			try{
				return listaSeguradoras.get(INT(indice));
			}
			catch(Exception e){
				System.out.println("Escolha uma opcao valida:"); 
				indice = lerString();
			}
		}
	}

    // Metodo que le o Terminal
    private static String lerString() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static Seguradora lerSeguradora() {
        String nome, telefone, email, endereco, cnpj;

        nome = Validacao.lerNome("Nome da Seguradora");

        cnpj = Validacao.lerCNPJ();

        System.out.print("Telefone da Seguradora: ");
        telefone = lerString();

        System.out.print("Email da Seguradora: ");
        email = lerString();

        System.out.print("Endereço da Seguradora: ");
        endereco = lerString();

        return new Seguradora(nome, cnpj, telefone, endereco, email); // Cadastra a Seguradora
    }

	// exibir menu externo
	private static void exibirMenuExterno() {
		MenuOpcoes menuOpcoes[] = MenuOpcoes.values();
		System.out.println("Menu principal");
		for (MenuOpcoes op : menuOpcoes) {
			System.out.println(op.ordinal() + " - " + op.getDescricao());
		}
	}
	private static void exibirSubmenu(MenuOpcoes op) {
		SubmenuOpcoes[] submenu = op.getSubmenu();
		System.out.println(op.getDescricao());
		for (int i = 0; i < submenu.length; i++) {
			System.out.println(i + " - " + submenu[i].getDescricao());
		}
	}
	private static void exibirSubSubmenun(SubmenuOpcoes op){
		SubSubmenuOpcoes[] subsubmenu = op.getSubSubmenu();
		System.out.println(op.getDescricao());
		for (int i = 0; i < subsubmenu.length; i++) {
			System.out.println(i + " - " + subsubmenu[i].getDescricao());
		}
	}

	// ler opções do menu externo
	private static MenuOpcoes lerOpcaoMenuExterno() {
		String opUsuario;
		MenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = lerString();
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
			opUsuario = lerString();
		} while (!Validacao.ehInteiro(opUsuario)
				|| (INT(opUsuario) < 0 || INT(opUsuario) > op.getSubmenu().length - 1));
		opUsuarioConst = op.getSubmenu()[INT(opUsuario)];
		return opUsuarioConst;
	}
	// ler opção dos subsubmenus
	private static SubSubmenuOpcoes lerOpcaoSubSubmenu(SubmenuOpcoes op) {
		String opUsuario;
		SubSubmenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = lerString();
		} while (!Validacao.ehInteiro(opUsuario)
				|| (INT(opUsuario) < 0 || INT(opUsuario) > op.getSubSubmenu().length - 1));
		opUsuarioConst = op.getSubSubmenu()[INT(opUsuario)];
		return opUsuarioConst;
	}

	// executar opções do menu externo
	private static void executarOpcaoMenuExterno(MenuOpcoes op, ArrayList<Seguradora> listaSeguradoras) {
		switch (op) {
			case SEGURADORAS:
				Seguradora seguradora = escolherSeguradora(listaSeguradoras);
				executarSubmenu(op, seguradora);
				break;
			case CONFIGURACOES:
				executarSubmenu(op, listaSeguradoras);
				break;
			case SAIR:
		}
	}

	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu,  ArrayList<Seguradora> listaSeguradoras) {
		switch (opSubmenu) {
			case CADASTRAR_SEGURADORA:
				Seguradora seguradora = lerSeguradora();
				if(listaSeguradoras.add(seguradora)){
					System.out.println("Seguradora adicionda com sucesso.");
				}
				else{
					System.out.println("Falha ao adiconar!");
				}
				break;
			case LISTAR_SEGURADORAS:
				listar(listaSeguradoras);
			break;
		}
	}

	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu, Seguradora seguradora) {
		switch(opSubmenu){
			case CADASTAR_SEGURO:
				if(seguradora.getListaClientes().size() != 0)
					seguradora.gerarSeguro();
				else
					System.out.println("Não ha clientes cadastrados. Cadastrar primeiro um cliente.");
				break;
			case CANCELAR_SEGURO:
				if(seguradora.cancelarSeguro())
					System.out.println("Seguro cancelado com sucesso!");
				else
					System.out.println("Falha! Não foi possivel cancelar.");
				break;
			case LISTAR_SEGUROS_CLIENTE:
				if(seguradora.getListaSeguros().size() != 0){
					ArrayList<Seguro> seguros = seguradora.getSeguroPorCliente();
					if(seguros.size() != 0){
						System.out.println("Lista de seguros:");
						for(int i = 0; i < seguros.size(); i++){
							System.out.println(Integer.toString(i) + " - " + seguros.get(i).toStringBasico() + "\n");
						}
					}
					else
						System.out.println("Nao ha seguros para listar.");
				}
				else{
					System.out.println("Nao ha seguros para listar.");
				}
				break;
			case CADASTAR_CLIENTE:
				if (seguradora.cadastrarCliente())
					System.out.println("Cliente cadastrado com sucesso!");
				else
					System.out.println("Falha! Cliente não cadastrado.");
				break;
			case REMOVER_CLIENTE:
				if(seguradora.getListaClientes().size() !=0){
					if(seguradora.removeCliente())
						System.out.println("Cliente removido com sucesso.");
					else
						System.out.println("Falha! Não foi possível remover.");
				}
				else
					System.out.println("Nao ha clientes cadastrados.");
				break;
			case LISTAR_CLIENTES:
				seguradora.listarClientes();
				break;
			case CONFIGURACOES_CLIENTES_PF:
				if(seguradora.nClientesPF() != 0){
					ClientePF clientePF = seguradora.escolherClientePF();
					executarSubSubmenu(opSubmenu, clientePF);
				}
				else{
					System.out.println("Não ha Clientes PF cadastrados.");
				}
				break;
			case CONFIGURACOES_CLIENTES_PJ:
				if(seguradora.nClientesPF() != 0){
					ClientePJ clientePJ = seguradora.escolherClientePJ();
					executarSubSubmenu(opSubmenu, clientePJ);
				}
				else{
					System.out.println("Não ha Clientes PJ cadastrados.");
				}				
				break;
			case SINISTROS_POR_CLIENTE:
				if(seguradora.getListaClientes().size() != 0){
					ArrayList<Sinistro> sinistros = seguradora.getSinistroPorCliente();
					if(sinistros.size() != 0){
						System.out.println("Lista de sinistros:");
						for(int i = 0; i < sinistros.size(); i++){
							System.out.println(Integer.toString(i) + " - " + sinistros.get(i).toStringBasico() + "\n");
						}
					}
					else
						System.out.println("Nao ha sinistros para listar.");
				}
				else{
					System.out.println("Não ha clientes cadastrados.");
				}
			break;		
		}
	}

	public static void executarOpcoesSubSubmenu(SubSubmenuOpcoes op, ClientePF cliente){
		switch(op){
			case CADASTRA_VEICULO:
				cliente.CadastrarVeiculo();
				break;
			case REMOVER_VEICULO:
				if(cliente.removerVeiculo()){
					System.out.println("Veiculo removido");
				}
				else{
					System.out.println("Falha! Lista Vazia ou não foi possivel remover.");
				}
				break;
			case LISTAR_VEICULOS:
				String lista = cliente.listarVeiculos();
				if(lista.equals(""))
					System.out.println("Lista vazia.");
				else{
					System.out.println("Lista de veiculos:\n" + lista);
				}
			break;
		}
		
	}
	public static void executarOpcoesSubSubmenu(SubSubmenuOpcoes op, ClientePJ cliente){
		switch(op){
			case CADASTRA_FROTA:
				cliente.cadastarFrota();
				break;
			case ADICIONAR_VEICULO_FROTA:
				cliente.addVeiculo();
				break;
			case REMOVER_VEICULO_FROTA:
				if(cliente.removeVeiculo()){
					System.out.println("Veiculo removido com sucesso!");
				}
				break;
			case LISTAR_FROTAS:
				String lista = cliente.listarFrotas();
				if(lista.equals(""))
					System.out.println("Nao ha frotas para listar.");
				else
					System.out.print("Lista de frotas:\n" + lista);
				break;
			case SUBSTITUIR_FROTA:
				if(!cliente.substituirFrota()){
					System.out.println("Não foi possivel substituir.");
				}
			break;
		}
	}

	// executa os submenus: exibição do menu, leitura da opção e execução dos métodos
	private static void executarSubmenu(MenuOpcoes op,   ArrayList<Seguradora> listaSeguradoras) {
		SubmenuOpcoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op);
			executarOpcaoSubMenu(opSubmenu, listaSeguradoras);
		} while (opSubmenu != SubmenuOpcoes.VOLTAR);
	}

	private static void executarSubmenu(MenuOpcoes op, Seguradora seguradora) {
		SubmenuOpcoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op);
			executarOpcaoSubMenu(opSubmenu, seguradora);
		} while (opSubmenu != SubmenuOpcoes.VOLTAR);
	}

	private static void executarSubSubmenu(SubmenuOpcoes op, ClientePF cliente) {
		SubSubmenuOpcoes opSubmenu;
		do {
			exibirSubSubmenun(op);
			opSubmenu = lerOpcaoSubSubmenu(op);
			executarOpcoesSubSubmenu(opSubmenu, cliente);
		} while (opSubmenu != SubSubmenuOpcoes.VOLTAR);
	}

	private static void executarSubSubmenu(SubmenuOpcoes op, ClientePJ cliente) {
		SubSubmenuOpcoes opSubmenu;
		do {
			exibirSubSubmenun(op);
			opSubmenu = lerOpcaoSubSubmenu(op);
			executarOpcoesSubSubmenu(opSubmenu, cliente);
		} while (opSubmenu != SubSubmenuOpcoes.VOLTAR);
	}

	// executa o menu externo: exibição do menu, leitura da opção e execução da opção
	public static void main(String[] args) {
		ArrayList<Seguradora> listaSeguradoras =  new ArrayList<Seguradora>();
		instanciar(listaSeguradoras);
		MenuOpcoes op;
		do {
			exibirMenuExterno();
			op = lerOpcaoMenuExterno();
			executarOpcaoMenuExterno(op, listaSeguradoras);
		} while (op != MenuOpcoes.SAIR);
		System.out.println("Saiu do sistema");
	}
}