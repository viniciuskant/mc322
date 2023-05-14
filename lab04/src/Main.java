import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	
	//exibir menu externo
	private static void exibirMenuExterno() {
		MenuOpcoes menuOpcoes[] = MenuOpcoes.values();
		System.out.println("Menu principal");
		for(MenuOpcoes op: menuOpcoes) {
			System.out.println(op.ordinal() + " - " + op.getDescricao());
		}
	}
	
	/* exibir submenus
	 * se a lista de constantes do submenu for percorrida da mesma forma que o meu externo, a opção Voltar
	 * é printada com a posição que está na lista do enum (9 - Voltar). Por isso, a lista é percorrida 
	 * de forma diferente, tendo i como o inteiro correspondente. Assim, para listar o submenu de cadastros,
	 * por exemplo, vai ser printado "3 - Voltar".
	 */
	private static void exibirSubmenu(MenuOpcoes op) {
		SubmenuOpcoes[] submenu = op.getSubmenu();
		System.out.println(op.getDescricao());
		for(int i=0; i<submenu.length; i++) {
			System.out.println(i +" - " + submenu[i].getDescricao());
		}
	}
	
	//ler opções do menu externo
	private static MenuOpcoes lerOpcaoMenuExterno() {
		Scanner scanner = new Scanner(System.in);
		int opUsuario;
		MenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = scanner.nextInt();
		}while(opUsuario < 0 || opUsuario > MenuOpcoes.values().length - 1);
		opUsuarioConst = MenuOpcoes.values()[opUsuario];
		return opUsuarioConst;
	}
	
	//ler opção dos submenus
	private static SubmenuOpcoes lerOpcaoSubmenu(MenuOpcoes op) {
		Scanner scanner = new Scanner(System.in);
		int opUsuario;
		SubmenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = scanner.nextInt();
		}while(opUsuario < 0 || opUsuario > op.getSubmenu().length - 1);
		opUsuarioConst = op.getSubmenu()[opUsuario];
		return opUsuarioConst;
	}
	
	//executar opções do menu externo
	private static void executarOpcaoMenuExterno(MenuOpcoes op, ArrayList<Seguradora> listaSeguradoras, ArrayList<Sinistro> listaSinistros, ArrayList<Cliente> listaClientes, ArrayList<Veiculo> listaVeiculos) {
		Scanner input = new Scanner(System.in);
		switch(op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op, listaSeguradoras,  listaClientes, listaVeiculos, listaSinistros);
				break;
			case GERAR_SINISTRO:
				Sinistro sinistro = Gerar.sinistro();

				// Adicinado ao sinistro o cliente que o sofreu
				System.out.println("Opcoes:\n0 - Novo cliente\n1 - Cliente ja cadastrado");
				String resposta = input.nextLine();
				if(resposta.equals("0")){
					Cliente cliente = Gerar.cliente();
					sinistro.setCliente(cliente);
				}
				else if (resposta.equals("1")){
					System.out.println("Clientes:");
					for(int i = 0; i < listaClientes.size(); i++){
						System.out.println(Integer.toString(i) + " - " + listaClientes.get(i).getNome());
					}
					System.out.println("Escolha uma opcao:"); 
					String indice = input.nextLine();
					while(!Validacao.ehInteiro(indice)){
						System.out.println("Escolha uma opcao:"); 
						indice = input.nextLine();
					}
					sinistro.setCliente(listaClientes.get(Integer.parseInt(indice)));
				}

					// Adiconando ao sinistro o veiculo que o sofreu
					System.out.println("Opcoes:\n0 - Novo Veiculo\n1 - Veiculo ja cadastrado");
					resposta = input.nextLine();
					if(resposta.equals("0")){
						Veiculo veiculo = Gerar.veiculo();
						sinistro.setVeiculo(veiculo);
					}
					else if (resposta.equals("1")){
						System.out.println("Veiculos:");
						for(int i = 0; i < listaVeiculos.size(); i++){
							System.out.println(Integer.toString(i) + " - " + listaVeiculos.get(i).getplaca());
						}
						System.out.println("Escolha uma opcao:"); 
						String indice = input.nextLine();
						while(!Validacao.ehInteiro(indice)){
							System.out.println("Escolha uma opcao:"); 
							indice = input.nextLine();
						}
						sinistro.setVeiculo(listaVeiculos.get(Integer.parseInt(indice)));
					}

				listaSinistros.add(sinistro);
				break;
			case TRANSFERIR_SEGURO:
				System.out.println("Executar metodo tranferir seguro");
				break;
			case CALCULAR_RECEITA:
				System.out.println("Executar metodo calcular receita");
				break;
			//case SAIR:
		}
	}

	private static int escolhaSeguradora(ArrayList<Seguradora> listaSeguradoras){
		Scanner input = new Scanner(System.in);
		System.out.println("Seguradoras:");
		for(int i = 0; i < listaSeguradoras.size(); i++){
			System.out.println(Integer.toString(i) + " - " + listaSeguradoras.get(i).getNome());
		}
		System.out.println("Escolha uma opcao:"); 
		String indice = input.nextLine();
		while(!Validacao.ehInteiro(indice)){
			System.out.println("Escolha uma opcao:"); 
			indice = input.nextLine();
		}
		return Integer.parseInt(indice);
	}
	
	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu, ArrayList<Seguradora> listaSeguradoras, ArrayList<Cliente> listaClientes, ArrayList<Veiculo> listaVeiculos, ArrayList<Sinistro> listaSinistros) {
		Scanner input = new Scanner(System.in);
		switch(opSubmenu) {
		case CADASTRAR_CLIENTE:
			Cliente cliente = Gerar.cliente();
			listaClientes.add(cliente);
			break;
		case CADASTRAR_VEICULO:
			Veiculo veiculo = Gerar.veiculo();
			listaVeiculos.add(veiculo);
			break;
		case CADASTRAR_SEGURADORA:
			Seguradora seguradora = Gerar.seguradora();
			listaSeguradoras.add(seguradora);
			if ((listaClientes.size() != 0 || listaSinistros.size() != 0)){ //Deve atualozar a seguradora
				Boolean opcaoIncorreta = true;
				System.out.println("DESEJA COPIAR OS CLIENTES:\n0 - SIM\n1 - NÃO");
				String resposta;

				while(opcaoIncorreta){
					resposta = input.nextLine();
					if(resposta.equals("0")){
						int indice = listaSeguradoras.size() - 1;

						// Adicionando os clientes
						System.out.println("Clientes");
						for(int i = 0; i < listaClientes.size(); i++){
							System.out.println(Integer.toString(i) + " - " + listaClientes.get(i).getNome());
						}
						System.out.println("Escolha as opcao(separar por virgula):"); 
						String indices = input.nextLine();
						String indicesSeparados[] = indices.split(",");
						for(int i = 0; i < indicesSeparados.length; i++){
							String numero = indicesSeparados[i];
							if(Validacao.ehInteiro(numero))
								listaSeguradoras.get(indice).addCliente(listaClientes.get(Integer.parseInt(numero)));
							else
								System.out.println("Indice inválido.");
						}

					}
					else if(resposta.equals("1")){
						opcaoIncorreta = false;
					}
					else{
						System.out.println("OPÇÃO INVALIDA! TENTE NOVAMENTE.");
					}
				}

				opcaoIncorreta = true;
				System.out.println("DESEJA COPIAR OS SINISTROS:\n0 - SIM\n1 - NÃO");

				while(opcaoIncorreta){
					resposta = input.nextLine();
					if(resposta.equals("0")){
						int indice = listaSeguradoras.size() - 1;

						// Adicionando os sinistros
						System.out.println("Sinistros");
						for(int i = 0; i < listaSinistros.size(); i++){
							System.out.println(Integer.toString(i) + " - " + listaSinistros.get(i).getid());
						}
						System.out.println("Escolha as opcao(separar por virgula):"); 
						String indices = input.nextLine();
						String indicesSeparados[] = indices.split(",");
						for(int i = 0; i < indicesSeparados.length; i++){
							String numero = indicesSeparados[i];
							if(Validacao.ehInteiro(numero))
								listaSeguradoras.get(indice).addSinistro(listaSinistros.get(Integer.parseInt(numero)));
							else
								System.out.println("Indice inválido.");
						}

					}
					else if(resposta.equals("1")){
						opcaoIncorreta = false;
					}
					else{
						System.out.println("OPÇÃO INVALIDA! TENTE NOVAMENTE.");
					}
				}
			}
			break;
		case LISTAR_CLIENTES_PF:
			if (listaSeguradoras.size() == 0){
				System.out.println("AINDA NÃO HÁ SEGURADORA CADASTRADA!");
			}
			else{
				int indice = escolhaSeguradora(listaSeguradoras);
				listaSeguradoras.get(indice).listarClientes("PF");
			}
			break;
		case LISTAR_CLIENTES_PJ:
			if (listaSeguradoras.size() == 0){
				System.out.println("AINDA NÃO HÁ SEGURADORA CADASTRADA!");
			}
			else{
				int indice = escolhaSeguradora(listaSeguradoras);
				listaSeguradoras.get(indice).listarClientes("PJ");
			}
			break;
		case LISTAR_SINISTROS_SEGURADORA:
			if (listaSeguradoras.size() == 0){
				System.out.println("AINDA NÃO HÁ SEGURADORA CADASTRADA!");
			}
			else{
				int indice = escolhaSeguradora(listaSeguradoras);
				listaSeguradoras.get(indice).listarClientes("PJ");
			}
			break;
		case LISTAR_SINISTROS_CLIENTE:
			if (listaSeguradoras.size() == 0){
				System.out.println("AINDA NÃO HÁ SEGURADORA CADASTRADA!");
			}
			else{
				int indice = escolhaSeguradora(listaSeguradoras);
				System.out.print("Nome do Cliente: ");
				String nome = input.nextLine();
				listaSeguradoras.get(indice).visualizarSinistro(nome);
			}
			break;
		case LISTAR_VEICULOS_CLIENTE:
			if (listaSeguradoras.size() == 0){
				System.out.println("AINDA NÃO HÁ SEGURADORA CADASTRADA!");
			}
			else{
				int indice = escolhaSeguradora(listaSeguradoras);
				System.out.print("Nome do Cliente: ");
				String nome = input.nextLine();
				listaSeguradoras.get(indice).listarVeiculosCliente(nome);
			}
			break;
		case LISTAR_VEICULOS_SEGURADORA:
			if (listaSeguradoras.size() == 0){
				System.out.println("AINDA NÃO HÁ SEGURADORA CADASTRADA!");
			}
			else{
				int indice = escolhaSeguradora(listaSeguradoras);
				listaSeguradoras.get(indice).listarVeiculos();
			}
			break;
		case EXCLUIR_CLIENTE:
			if (listaSeguradoras.size() == 0){
				System.out.println("AINDA NÃO HÁ SEGURADORA CADASTRADA!");
			}
			else{
				int indice = escolhaSeguradora(listaSeguradoras);
				System.out.print("Nome do Cliente: ");
				String nome = input.nextLine();
				listaSeguradoras.get(indice).removeCliente(nome);
			}
			break;
		case EXCLUIR_VEICULO:
			if (listaSeguradoras.size() == 0){
				System.out.println("AINDA NÃO HÁ SEGURADORA CADASTRADA!");
			}
			else{
				int indice = escolhaSeguradora(listaSeguradoras);
				System.out.print("Nome do Cliente: ");
				String nome = input.nextLine();
				System.out.print("Placa do Veiculo: ");
				String placa = input.nextLine();
				if (listaSeguradoras.get(indice).removerVeiculo(nome, placa)){
					System.out.println("VEICULO REMOVIDO.");
				}
				else{
					System.out.println("NÃO FOI POSSÍVEL REMOVER.");
				}
			}
			break;
		case EXCLUIR_SINISTRO:
			if (listaSeguradoras.size() == 0){
				System.out.println("AINDA NÃO HÁ SEGURADORA CADASTRADA!");
			}
			else{
				int indice = escolhaSeguradora(listaSeguradoras);
				System.out.print("ID do Sinistro: ");
				String id = input.nextLine();
				listaSeguradoras.get(indice).removerSinistro(id);
			}
			break;
		//case VOLTAR:
		//	break;
		}
	}
	
	//executa os submenus: exibição do menu, leitura da opção e execução dos métodos
	private static void executarSubmenu(MenuOpcoes op, ArrayList<Seguradora> listaSeguradoras, ArrayList<Cliente> listaClientes, ArrayList<Veiculo> listaVeiculos, ArrayList<Sinistro> listaSinistros) {
		SubmenuOpcoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op);
			executarOpcaoSubMenu(opSubmenu, listaSeguradoras, listaClientes, listaVeiculos, listaSinistros);
		}while(opSubmenu != SubmenuOpcoes.VOLTAR);
	}
	
	//executa o menu externo: exibição do menu, leitura da opção e execução da opção
	public static void main(String[] args) {
		MenuOpcoes op;
		ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
		ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
		ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
		ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

		do {
			exibirMenuExterno();
			op = lerOpcaoMenuExterno();
			executarOpcaoMenuExterno(op, listaSeguradoras, listaSinistros, listaClientes, listaVeiculos);
		}while(op != MenuOpcoes.SAIR);
		System.out.println("Saiu do sistema");
	}

}
