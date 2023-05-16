import java.util.Scanner;
import java.util.ArrayList;

public class Main {

	//Metodo que le o Terminal
	private static String lerString(){
		Scanner input = new Scanner(System.in);
		return input.nextLine();
	}

	//Metodo que converte String em inteiro
	private static int INT(String string){
		return Integer.parseInt(string);
	} 

	//Metodo que interage com o usuário e retorna um ClientePF
	private static ClientePF lerPF(){
        String nome, endereco, data, educacao, genero, classeEconomica, cpf;
        Date dataLicenca;  

        System.out.print("Nome da Pessoa: ");
        nome = lerString();
        while(!Validacao.validaNome(nome)){ //Não sai do loop enquanto a entrada não for valida
            System.out.print("Nome invalido!\nNome da Pessoa: ");
            nome = lerString();
        }

        System.out.print("Endereço: ");
        endereco = lerString();

        System.out.print("Data de licença(dd/MM/aaaa): ");
        data = lerString();
        while(!Validacao.validarData(data)){ //Não sai do loop enquanto a entrada não for valida
            System.out.print("Data invalida\nData de licença(dd/MM/aaaa): ");
            data = lerString();
        }
        
        String[] dataSeparada = data.split("/");
        dataLicenca = new Date(dataSeparada[0], dataSeparada[1], dataSeparada[2]);

        System.out.print("Escolaridade: ");
        educacao = lerString();

        System.out.print("Gênero: ");
        genero = lerString();

        System.out.print("Classe economica: ");
        classeEconomica = lerString();

        System.out.print("CPF: ");
        cpf = lerString();
        while(!Validacao.validarCPF(cpf)){ //Não sai do loop enquanto a entrada não for valida
            System.out.print("CPF inválido!\nCPF: ");
            cpf = lerString();
        }

        return new ClientePF(nome, endereco, dataLicenca, educacao, genero, classeEconomica, cpf, dataLicenca);
    }

	//Metodo que interage com o usuário e retorna um ClientePJ
    private static ClientePJ lerPJ(){
        String nome, endereco, data, cnpj, nFuncionarios;
        Date dataLicenca;

        System.out.print("Nome da Empresa: "); //Não sai do loop enquanto a entrada não for valida
        nome = lerString();
        while(!Validacao.validaNome(nome)){
            System.out.print("Nome invalido!\nNome da Empresa: ");
            nome = lerString();
        }

        System.out.print("Endereço: ");
        endereco = lerString();

		System.out.print("Data de licença(dd/MM/aaaa): ");
        data = lerString();
        while(!Validacao.validarData(data)){ //Não sai do loop enquanto a entrada não for valida
            System.out.print("Data invalida\nData de licença(dd/MM/aaaa): ");
            data = lerString();
        }

        String[] dataSeparada = data.split("/");
        dataLicenca = new Date(dataSeparada[0], dataSeparada[1], dataSeparada[2]);

        System.out.print("CNPJ: ");
        cnpj = lerString();
		while(!Validacao.validarCNPJ(cnpj)){ //Não sai do loop enquanto a entrada não for valida
            System.out.print("CNPJ inválido!\n CNPJ: ");
            cnpj = lerString();
        }

        System.out.print("Número de Funcionarios: ");
        nFuncionarios = lerString();
		while(!Validacao.ehNatural(nFuncionarios)){ //Não sai do loop enquanto a entrada não for valida
            System.out.print("Número de Funcinários invalido!\nNúmero de Funcionarios: ");
            cnpj = lerString();
        }

        return new ClientePJ(nome, endereco, cnpj, dataLicenca, INT(nFuncionarios));
    }

	//Metodo que interage com o usuário e retorna um Cliente
    private static Cliente lerCliente(){
        boolean opcaoIncorreta = true;
        
        System.out.print("Tipo de cliente:\n0 - Pessoa Fisica\n1 - Pessoa Juridica\nDigite uma opcao:\n");
        String tipoCadastro = lerString();
        while(opcaoIncorreta){ //Não sai do loop enquanto a entrada não for uma das opcoes
            if(tipoCadastro.equals("0")){
                return lerPF();  
            }
            else if(tipoCadastro.equals("1")){
                return lerPJ();
            }
            else{
                System.out.println("Opcao invalida.\nEscolha uma opcao:\n.");
				tipoCadastro = lerString();
            }
        }
        return new Cliente();
    }

	//Metodo que interage com o usuário e retorna um Sinistro
    private static Sinistro lerSinistro(){
        Date dataSinistro;
        String endereco, data, dataSeparada[];

        System.out.print("Data do Sinistro(dd/MM/aaaa): ");
        data = lerString();
        while(!Validacao.validarData(data)){ //Não sai do loop enquanto a entrada não for valida
            System.out.print("Data invalida!\nData do Sinistro(dd/MM/aaaa): ");
            data = lerString();
        }
        
        dataSeparada = data.split("/");
        dataSinistro = new Date(dataSeparada[0], dataSeparada[1], dataSeparada[2]);

        System.out.print("Endereco: ");
        endereco = lerString();

        return new Sinistro(dataSinistro, endereco);

    }

	//Metodo que interage com o usuário e retorna um Veiculo
    private static Veiculo lerVeiculo(){
        String placa, marca, modelo;
        String anoFabricacao;

        System.out.print("Placa do veículo: ");
        placa = lerString();

        System.out.print("Marca: ");
        marca = lerString();

        System.out.print("Modelo: ");
        modelo = lerString();

        System.out.print("Ano de fabricação do veículo: ");
        anoFabricacao = lerString();
		while(!Validacao.ehInteiro(anoFabricacao)){ //Não sai do loop enquanto a entrada não for valida
			System.out.print("Ano de fabricação invalido!\nAno de fabricação:");
			anoFabricacao = lerString();
		}

        return new Veiculo(placa, marca, modelo, INT(anoFabricacao));
    }

	//Metodo que interage com o usuário e retorna uma Seguradora
    private static Seguradora lerSeguradora(){
        String nome, telefone, email, endereco;

        System.out.print("Nome da Seguradora: ");
        nome = lerString();
        while(!Validacao.validaNome(nome)){ //Não sai do loop enquanto a entrada não for valida
            System.out.print("Nome invalido!\nNome da Seguradora: ");
            nome = lerString();
        }
		
        System.out.print("Telefone da Seguradora: ");
        telefone = lerString();

        System.out.print("Email da Seguradora: ");
        email = lerString();

        System.out.print("Endereço da Seguradora: ");
        endereco = lerString();

        return new Seguradora(nome, telefone, email, endereco); //Cadastra a Seguradora
    }
	
	// Lista todas Seguradoras cadastradas e retorna o indice da seguradora escolhida
	private static int escolhaSeguradora(ArrayList<Seguradora> listaSeguradoras){
		System.out.println("Seguradoras:");

		// Imprimi todas as seguradoras
		for(int i = 0; i < listaSeguradoras.size(); i++){
			System.out.println(Integer.toString(i) + " - " + listaSeguradoras.get(i).getNome());
		}
		System.out.println("Escolha uma opcao:"); 
		String indice = lerString();

		// Enquanto o indice não for um inteiro e não estiver no intervalo de 0 até o tamanho da lista
		while(!Validacao.ehInteiro(indice) && !(0 <= INT(indice) && INT(indice) < (listaSeguradoras.size()))){
			System.out.println("Opcao invalida.\nEscolha uma opcao:"); 
			indice = lerString();
		}
		return INT(indice);
	}

	// Lista todos Clientes cadastrados e retorna o indice do cliente escolhido
	private static int escolhaCliente(ArrayList<Cliente> listaClientes){
		System.out.println("Clientes:");

		// Imprimi todos os clientes
		for(int i = 0; i < listaClientes.size(); i++){
			System.out.println(Integer.toString(i) + " - " + listaClientes.get(i).getNome());
		}
		System.out.println("Escolha uma opcao:"); 
		String indice = lerString();

		// Enquanto o indice não for um inteiro e não estiver no intervalo de 0 até o tamanho da lista
		while(!Validacao.ehInteiro(indice) && !(0 <= INT(indice) && INT(indice) < (listaClientes.size()))){
			System.out.println("Opcao invalida.\nEscolha uma opcao:"); 
			indice = lerString();
		}
		return INT(indice);
	}

	// Cadastra o cliente e o adiciona na lista de cliente e se pedido o adiciona a uma seguradora
	private static void cadastrarCliente(ArrayList<Cliente> listaClientes, ArrayList<Seguradora> listaSeguradoras){
		Cliente cliente = lerCliente();
		listaClientes.add(cliente); // adiciona o cliente a lista com todos os clientes
		if(listaSeguradoras.size() != 0){ //se houver uma seguradora já cadastrada
			System.out.println("Deseja cadastar em um seguradora previamente cadastrada?\n0 - Sim\n1 - Nao\nEscolha uma opcao:");
			String resposta = lerString();
			boolean respostaIncorreta = true;
			while(respostaIncorreta){ 
				if(resposta.equals("0")){
					int indice = escolhaSeguradora(listaSeguradoras);
					if(listaSeguradoras.get(indice).addCliente(cliente)){ //se o objeto for adiconado na lista
						System.out.println("Cliente" + cliente.getNome() + " cadastrado na Seguradora " + listaSeguradoras.get(indice).getNome() + ".");
					}
					else{
						System.out.println("Não foi possível adicionar à seguradora.");
					}
					respostaIncorreta = false;
				}
				else if(resposta.equals("1")){
					System.out.println("Cliente " + cliente.getNome() + " cadastrado com sucesso.");
					respostaIncorreta = false;
				}
				else{
					System.out.println("Opcao invalida.\nEscolha uma opcao:");
					resposta = lerString();
				}
			}
		}
	}

	// Cadastra o veiculo e o adiciona na lista de veiculos e se pedido o adiciona a um cliente
	private static void cadastrarVeiculo(ArrayList<Cliente> listaClientes, ArrayList<Veiculo> listaVeiculos, ArrayList<Seguradora> listaSeguradoras){
		Veiculo veiculo = lerVeiculo();
		listaVeiculos.add(veiculo);

		if(listaClientes.size() != 0){
			System.out.println("Deseja cadastar em um cliente previamente cadastrado?\n0 - Sim\n1 - Nao\nEscolha uma opcao:");
			String resposta = lerString();
			boolean respostaIncorreta = true;

			while(respostaIncorreta){ //Não sai do loop enquanto a entrada não for no intevalo permitido
				if(resposta.equals("0")){
					int indice = escolhaCliente(listaClientes);
					listaClientes.get(indice).addVeiculo(veiculo);
					System.out.println("Veiculo " + veiculo.getModelo() + " cadastrado no Cliente " + listaClientes.get(indice).getNome() + ".");

					respostaIncorreta = false;
				}
				else if(resposta.equals("1")){
					System.out.println("Veiculo " + veiculo.getModelo() + " cadastrado com sucesso.");
					respostaIncorreta = false;
				}
				else{
					System.out.println("Escolha uma opcao:");
					resposta = lerString();

				}
			}
		}
	}

	// Cadastra uma seguradora e adiciona na lista de seguradoras
	private static void cadastrarSeguradora(ArrayList<Cliente> listaClientes, ArrayList<Sinistro> listaSinistros, ArrayList<Seguradora> listaSeguradoras){
		Seguradora seguradora = lerSeguradora();
		listaSeguradoras.add(seguradora);  //adiciona a lista de seguradoras
		
		if (listaClientes.size() != 0){ // Significa que clientes já foram cadastrados
			Boolean opcaoIncorreta = true;
			System.out.println("Deseja copiar os clientes preciamente cadastrados:\n0 - Sim\n1 - Não");

			while(opcaoIncorreta){ //Não sai do loop enquanto a entrada não for no intevalo permitido
				String resposta = lerString();
				if(resposta.equals("0")){
					int indice = listaSeguradoras.size() - 1; // o indice da seguradora atual na lista de seguradoras

					// Adicionando os clientes
					System.out.println("Clientes");
					for(int i = 0; i < listaClientes.size(); i++){ //lista os clientes
						System.out.println(Integer.toString(i) + " - " + listaClientes.get(i).getNome());
					}
					System.out.println("Escolha as opcao(separar por virgula): "); 
					String indices = lerString();
					String indicesSeparados[] = indices.split(",");
					for(int i = 0; i < indicesSeparados.length; i++){
						String numero = indicesSeparados[i];
						if(Validacao.ehInteiro(numero)){
							listaSeguradoras.get(indice).addCliente(listaClientes.get(INT(numero)));
							System.out.println("Cliente" + listaClientes.get(INT(numero)).getNome() + "adicionado");
						}
						else
							System.out.println("Indice inválido.");
					}
					opcaoIncorreta = false;
				}
				else if(resposta.equals("1")){
					opcaoIncorreta = false;
				}
				else{
					System.out.println("Opcao invalida\n! Tente Novamente: ");
					resposta = lerString();
				}
			}
			if(listaSinistros.size() != 0){ //Significa que há sinistros previamente cadastrados
				opcaoIncorreta = true;
				System.out.println("Deseja copiar os sinistros preciamente cadastrados:\n0 - Sim\n1 - Não");

				while(opcaoIncorreta){ //Não sai do loop enquanto a entrada não for dentro do intelavo permitido
					String resposta = lerString();
					if(resposta.equals("0")){
						int indice = listaSeguradoras.size() - 1;

						// Adicionando os sinistros
						System.out.println("Sinistros");
						for(int i = 0; i < listaSinistros.size(); i++){ //lista os sinistros
							System.out.println(Integer.toString(i) + " - " + listaSinistros.get(i).getid());
						}

						System.out.println("Escolha as opcao(separar por virgula): "); 
						String indices = lerString();
						String indicesSeparados[] = indices.split(",");
						for(int i = 0; i < indicesSeparados.length; i++){
							String numero = indicesSeparados[i];
							if(Validacao.ehInteiro(numero))
								listaSeguradoras.get(indice).addSinistro(listaSinistros.get(INT(numero)));
							else
								System.out.println("Indice inválido.");
						}
						opcaoIncorreta = false;
					}
					else if(resposta.equals("1")){
						opcaoIncorreta = false;
					}
					else{
						System.out.println("Opcao invalida\n! Tente Novamente: ");
					resposta = lerString();
					}
				}
			}
			System.out.println("Seguradora " + seguradora.getNome() + " cadastrada com sucesso.");
		}
	}
	
	// Lista todos os clientes do tipo PF da seguradora desejada
	private static void listarClientesPF(ArrayList<Seguradora> listaSeguradoras){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			int indice = escolhaSeguradora(listaSeguradoras);
			String lista = listaSeguradoras.get(indice).listarClientes("PF");

			if(lista.equals(""))
				System.out.println("Não há clientes PF cadastrados.");
			else
				System.out.println(lista);
		}
	}
	
	// Lista todos os clientes do tipo PJ da seguradora desejada
	private static void listaClientesPJ(ArrayList<Seguradora> listaSeguradoras){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			int indice = escolhaSeguradora(listaSeguradoras);
			String lista = listaSeguradoras.get(indice).listarClientes("PJ");

			if(lista.equals(""))
				System.out.println("Não há clientes PJ cadastrados.");
			else
				System.out.println(lista);
		}
	}
	
	// Lista todos os sinistros da seguradora desejada
	private static void listarSinistrosSeguradora(ArrayList<Seguradora> listaSeguradoras){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			int indice = escolhaSeguradora(listaSeguradoras);
			String lista = listaSeguradoras.get(indice).listarSinistros();

			if(lista.equals(""))
				System.out.println("Não há Sinistros nessa seguradora.");
			else
				System.out.println(lista);
		}
	}

	// Lista todos os sinistros do cliente desejado
	private static void listaSinistrosCliente(ArrayList<Seguradora> listaSeguradoras){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			int indice = escolhaSeguradora(listaSeguradoras);
			System.out.print("Clientes: ");
			listaSeguradoras.get(indice).listarTodosClientes();
			System.out.println("Escolha uma opcao:");
			String resposta = lerString();

			while(!Validacao.ehInteiro(resposta) && (INT(resposta) < 0 || INT(resposta) > listaSeguradoras.get(indice).getnCliente())){
				System.out.println("Escolha invalida!\nEscolha uma opcao:");
				resposta = lerString();
			}

			String nome = listaSeguradoras.get(indice).getListaClientes().get(INT(resposta)).getNome();
			if (!listaSeguradoras.get(indice).visualizarSinistro(nome))
				System.out.println("Não há sinistros desse cliente.");
		}
	}

	// Lista todos os veiculos de cliente desejado
	private static void listarVeiculosCliente(ArrayList<Cliente> listaClientes){
		int indice = escolhaCliente(listaClientes);
		String lista = listaClientes.get(indice).listaVeiculos();

		if (lista.equals(""))
			System.out.println("Não há veiculos para serem listados.");
		else
			System.out.println(lista);
	}

	// Lista todos os veiculos da seguradora desejada
	private static void listarVeiculosSeguradora(ArrayList<Seguradora> listaSeguradoras){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			int indice = escolhaSeguradora(listaSeguradoras);
			String lista = listaSeguradoras.get(indice).listarVeiculos();

			if (lista.equals(""))
				System.out.println("Não há veiculos para serem listados.");
			else
				System.out.println(lista);
		}
	}

	// Gera um sinisto e o vincula a um cliente
	private static void gerarSinistro(ArrayList<Cliente> listaClientes, ArrayList<Sinistro> listaSinistros, ArrayList<Veiculo> listaVeiculos){
		Sinistro sinistro = lerSinistro();
		Cliente cliente = new Cliente(null, null);
		String resposta;
		boolean respostaIncorreta = true;
		// Adicinado ao sinistro o cliente que o sofreu
		if(listaClientes.size() != 0){ //Já ha clientes cadastrados
			System.out.println("Opcoes:\n0 - Novo cliente\n1 - Cliente ja cadastrado");
			resposta = lerString();

			while(respostaIncorreta){ //Não sai do loop enquanto a entrada não for dentro do intevalo permitido
				if(resposta.equals("0")){
					cliente = lerCliente();
					sinistro.setCliente(cliente);
					respostaIncorreta = false;
				}
				else if (resposta.equals("1")){
					System.out.println("Clientes:");
					int indice = escolhaCliente(listaClientes);
					cliente = listaClientes.get(indice);
					System.out.println("Cliente escolhido com sucesso.");
					sinistro.setCliente(cliente);
					respostaIncorreta = false;
				}
				else{
					System.out.println("Opcao invalida! Escolha outra:\n");
					resposta = lerString();
				}
			}
		}
		else{
			cliente = lerCliente();
			sinistro.setCliente(cliente);
			System.out.println("Sinistro " + sinistro.getid() + " cadastrado com sucesso.");
		}

		// Adiconando ao sinistro o veiculo que o sofreu
		System.out.println("Opcoes:\n0 - Novo Veiculo\n1 - Veiculo ja cadastrado");
		resposta = lerString();
		if(resposta.equals("0")){
			Veiculo veiculo = lerVeiculo();
			sinistro.setVeiculo(veiculo);
			cliente.addVeiculo(veiculo); // Adiciona implicitamnete o veiculo ao cliente 
		}
		else if (resposta.equals("1")){
			System.out.println("Veiculos:");
			for(int i = 0; i < listaVeiculos.size(); i++){
				System.out.println(Integer.toString(i) + " - " + listaVeiculos.get(i).getplaca());
			}
			System.out.println("Escolha uma opcao:"); 
			String indice = lerString();
			while(!Validacao.ehInteiro(indice)){
				System.out.println("Escolha uma opcao:"); 
				indice = lerString();
			}
			sinistro.setVeiculo(listaVeiculos.get(INT(indice)));
			cliente.addVeiculo(listaVeiculos.get(INT(indice))); // Adiciona implicitamnete o veiculo ao cliente

		}

		listaSinistros.add(sinistro);
	}

	// Exclui um cliente desejado
	private static void excluirCliente(ArrayList<Seguradora> listaSeguradoras){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			int indice = escolhaSeguradora(listaSeguradoras);
			System.out.print("Clientes: ");
			listaSeguradoras.get(indice).listarTodosClientes();
			System.out.println("Escolha uma opcao:");
			String resposta = lerString();
	
			while(!Validacao.ehInteiro(resposta) && (INT(resposta) < 0 || INT(resposta) > listaSeguradoras.get(indice).getnCliente())){
				System.out.println("Escolha invalida!\nEscolha uma opcao:");
				resposta = lerString();
			}
			String nome = listaSeguradoras.get(indice).getListaClientes().get(INT(resposta)).getNome();
			listaSeguradoras.get(indice).removeCliente(nome);
		}
	}

	private static String formatarString(String string){
		String separado[] = string.split("\n"), retorno = "";
		for(int i = 0; i < separado.length; i++){
			retorno += Integer.toString(i) + " - " + separado[i] + "\n";
		}
		return retorno;
	}

	// Exclui o veiculo de um determinado cliente
	private static void excluirVeiculo(ArrayList<Seguradora> listaSeguradoras){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			int indiceSeguradora = escolhaSeguradora(listaSeguradoras);
			System.out.print("Clientes: ");
			listaSeguradoras.get(indiceSeguradora).listarTodosClientes();
			System.out.println("Escolha uma opcao:");
			String indiceCliente = lerString();
	
			//Não sai do loop enquanto a entrada não for valida
			while(!Validacao.ehInteiro(indiceCliente) && (INT(indiceCliente) < 0 || INT(indiceCliente) > listaSeguradoras.get(indiceSeguradora).getnCliente())){
				System.out.println("Escolha invalida!\nEscolha uma opcao:");
				indiceCliente = lerString();
			}
			String preLista = listaSeguradoras.get(indiceSeguradora).getListaClientes().get(INT(indiceCliente)).listaVeiculos();
			String listaFormatada = formatarString(preLista);

			System.out.print("Veiculos:\n" + listaFormatada + "\nEscolha um opcao:\n");
			String indiceVeiculo = lerString();

			//Não sai do loop enquanto a entrada não for valida
			while(!Validacao.ehInteiro(indiceVeiculo) && (INT(indiceVeiculo) < 0 || INT(indiceVeiculo) > preLista.split("\n").length)){
				System.out.println("Escolha invalida!\nEscolha uma opcao:");
				indiceVeiculo = lerString();
			}
			String modelo = listaSeguradoras.get(indiceSeguradora).getListaClientes().get(INT(indiceCliente)).getListaVeiculos().get(INT(indiceVeiculo)).getModelo();
			String placa = listaSeguradoras.get(indiceSeguradora).getListaClientes().get(INT(indiceCliente)).getListaVeiculos().get(Integer
			.parseInt(indiceVeiculo)).getplaca();
			if (listaSeguradoras.get(indiceSeguradora).getListaClientes().get(INT(indiceCliente)).removerVeiculo(placa)){
				System.out.println("Veiculo (" + modelo + ")" + placa + " removido.");
			} 
			else{
				System.out.println("Não foi possivel remover o veiculo.");
			}
		}
	}

	// Excluir o sinistro de um determinado cliente
	private static void excluirSinistro(ArrayList<Seguradora> listaSeguradoras){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			int indice = escolhaSeguradora(listaSeguradoras);
			System.out.print("ID do Sinistro: ");
			listaSeguradoras.get(indice).listarSinistros();
			System.out.println("Escolha uma opcao:");
			String indiceSinistro = lerString();

			//Não sai do loop enquanto a entrada não for valida
			while(!Validacao.ehInteiro(indiceSinistro) && (INT(indiceSinistro) < 0 || INT(indiceSinistro) > listaSeguradoras.get(indice).getnSinistro())){
				System.out.println("Escolha invalida!\nEscolha uma opcao:");
				indiceSinistro = lerString();
			}
			int id = listaSeguradoras.get(indice).getListaSinistros().get(INT(indiceSinistro)).getid();
			if(listaSeguradoras.get(indice).removerSinistro(id)){
				System.out.println("Sinistro " + Integer.toString(id) + " removido.");
			}

			else{
				System.out.println("Não foi possível remover o sinistro.");
			}
		}
	}

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
		switch(op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op, listaSeguradoras,  listaClientes, listaVeiculos, listaSinistros);
				break;
			case GERAR_SINISTRO:
				gerarSinistro(listaClientes, listaSinistros, listaVeiculos);
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

	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu, ArrayList<Seguradora> listaSeguradoras, ArrayList<Cliente> listaClientes, ArrayList<Veiculo> listaVeiculos, ArrayList<Sinistro> listaSinistros) {
		switch(opSubmenu) {
			case CADASTRAR_CLIENTE:
				cadastrarCliente(listaClientes, listaSeguradoras);
				break;
			case CADASTRAR_VEICULO:
				cadastrarVeiculo(listaClientes, listaVeiculos, listaSeguradoras);
				break;
			case CADASTRAR_SEGURADORA:
				cadastrarSeguradora(listaClientes, listaSinistros, listaSeguradoras);;
				break;
			case LISTAR_CLIENTES_PF:
				listarClientesPF(listaSeguradoras);
				break;
			case LISTAR_CLIENTES_PJ:
				listaClientesPJ(listaSeguradoras);
				break;
			case LISTAR_SINISTROS_SEGURADORA:
				listarSinistrosSeguradora(listaSeguradoras);
				break;
			case LISTAR_SINISTROS_CLIENTE:
				listaSinistrosCliente(listaSeguradoras);;
				break;
			case LISTAR_VEICULOS_CLIENTE:
				listarVeiculosCliente(listaClientes);
				break;
			case LISTAR_VEICULOS_SEGURADORA:
				listarVeiculosSeguradora(listaSeguradoras);
				break;
			case EXCLUIR_CLIENTE:
				excluirCliente(listaSeguradoras);
				break;
			case EXCLUIR_VEICULO:
				excluirVeiculo(listaSeguradoras);;
				break;
			case EXCLUIR_SINISTRO:
				excluirSinistro(listaSeguradoras);;
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
