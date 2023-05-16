import java.util.Scanner;
import java.util.ArrayList;

/*O Menu e submenus desse lab foram baseados no disponível em https://github.com/rebecapadovani/ExemploEnumMenu*/

public class Main {
	private static ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
	private static ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
	private static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	private static  ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();


	// Metodo que instancia os objetos que o lab pede
	private static void instanciar(){
		// Instanciando dois veiculos
		Veiculo gol = new Veiculo("aaa-0000", "Volkswagen", "Gol", 1992);
        Veiculo chevette = new Veiculo("bbb-1111", "Chevrolet", "Chevette", 1978);

		// Instancio um clientePF
		String cpf = "71826141030";
		Date dataNascimento = new Date(07, 02, 2004), dataLicenca = new Date(025, 02, 2022);
		ClientePF clientepf = new ClientePF("João", "Campinas", dataLicenca, "Ensino médio completo", "Masculino", "c", cpf, dataNascimento);

		// Instancio um clientePJ
		String cnpj = "47896954000198";
		ClientePJ clientepj = new ClientePJ("J&M Construtora", "Valinhos", cnpj, dataLicenca, 5);

		// Instancio uma Seguradora
		Seguradora seguradora = new Seguradora("Unicamp-seguros", "35 99999-9999", "unicamp@dac.unicamp.br", "Campinas");

		Date data = new Date(01, 01, 2023);
		// Instancio dois sinistros
		Sinistro sinistro1 = new Sinistro(data, "Dom Pedro", chevette, clientepj);
		Sinistro sinistro2 = new Sinistro(data, "Fernao Dias", gol, clientepf);

		// Adicionadno veiculos aos clientes
		clientepf.addVeiculo(gol);
		clientepj.addVeiculo(chevette);

		// Cadastrando os clientes na Seguradora
		seguradora.addCliente(clientepj);
		seguradora.addCliente(clientepf);

		//Chamando o metodo listar cliente
		seguradora.listarTodosClientes();
		seguradora.visualizarSinistro("PF");
		seguradora.listarSinistros();
		double receita = seguradora.calcularReceita();

		seguradora.calcularPrecoSeguroCliente(clientepj);
		seguradora.calcularPrecoSeguroCliente(clientepf);

		System.out.println(Double.toString(receita));

		listaClientes.add(clientepf);
		listaClientes.add(clientepj);
		listaVeiculos.add(chevette);
		listaVeiculos.add(gol);
		listaSeguradoras.add(seguradora);
		listaSinistros.add(sinistro1);
		listaSinistros.add(sinistro2);

	}

	//Metodo que converte String em inteiro
	private static int INT(String string){
		return Integer.parseInt(string);
	} 


	// Lista todas Seguradoras cadastradas e retorna o indice da seguradora escolhida
	private static int escolhaSeguradora(){
		System.out.println("Seguradoras:");

		// Imprimi todas as seguradoras
		for(int i = 0; i < listaSeguradoras.size(); i++){
			System.out.println(Integer.toString(i) + " - " + listaSeguradoras.get(i).getNome());
		}
		System.out.println("Escolha uma opcao:"); 
		String indice = Leitura.lerString();

		// Enquanto o indice não for um inteiro e não estiver no intervalo de 0 até o tamanho da lista
		while(!Validacao.ehInteiro(indice) && !(0 <= INT(indice) && INT(indice) < (listaSeguradoras.size()))){
			System.out.println("Opcao invalida.\nEscolha uma opcao:"); 
			indice = Leitura.lerString();
		}
		return INT(indice);
	}

	// Lista todos Clientes cadastrados e retorna o indice do cliente escolhido
	private static int escolhaCliente(){
		System.out.println("Clientes:");

		// Imprimi todos os clientes
		for(int i = 0; i < listaClientes.size(); i++){
			System.out.println(Integer.toString(i) + " - " + listaClientes.get(i).getNome());
		}
		System.out.println("Escolha uma opcao:"); 
		String indice = Leitura.lerString();

		// Enquanto o indice não for um inteiro e não estiver no intervalo de 0 até o tamanho da lista
		while(!Validacao.ehInteiro(indice) && !(0 <= INT(indice) && INT(indice) < (listaClientes.size()))){
			System.out.println("Opcao invalida.\nEscolha uma opcao:"); 
			indice = Leitura.lerString();
		}
		return INT(indice);
	}

	// Cadastra o cliente e o adiciona na lista de cliente e se pedido o adiciona a uma seguradora
	private static void cadastrarCliente(){
		Cliente cliente = Leitura.lerCliente();
		listaClientes.add(cliente); // adiciona o cliente a lista com todos os clientes
		if(listaSeguradoras.size() != 0){ //se houver uma seguradora já cadastrada
			System.out.println("Deseja cadastar em um seguradora previamente cadastrada?\n0 - Sim\n1 - Nao\nEscolha uma opcao:");
			String resposta = Leitura.lerString();
			boolean respostaIncorreta = true;
			while(respostaIncorreta){ 
				if(resposta.equals("0")){
					int indice = escolhaSeguradora();
					if(listaSeguradoras.get(indice).addCliente(cliente)){ //se o objeto for adiconado na lista
						System.out.println("Cliente " + cliente.getNome() + " cadastrado na Seguradora " + listaSeguradoras.get(indice).getNome() + ".");
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
					resposta = Leitura.lerString();
				}
			}
		}
	}

	// Cadastra o veiculo e o adiciona na lista de veiculos e se pedido o adiciona a um cliente
	private static void cadastrarVeiculo(){
		Veiculo veiculo = Leitura.lerVeiculo();
		listaVeiculos.add(veiculo);

		if(listaClientes.size() != 0){
			System.out.println("Deseja cadastar em um cliente previamente cadastrado?\n0 - Sim\n1 - Nao\nEscolha uma opcao:");
			String resposta = Leitura.lerString();
			boolean respostaIncorreta = true;

			while(respostaIncorreta){ //Não sai do loop enquanto a entrada não for no intevalo permitido
				if(resposta.equals("0")){
					int indice = escolhaCliente();
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
					resposta = Leitura.lerString();

				}
			}
		}
	}

	// Cadastra uma seguradora e adiciona na lista de seguradoras
	private static void cadastrarSeguradora(){
		Seguradora seguradora = Leitura.lerSeguradora();
		listaSeguradoras.add(seguradora);  //adiciona a lista de seguradoras
		
		if (listaClientes.size() != 0){ // Significa que clientes já foram cadastrados
			Boolean opcaoIncorreta = true;
			System.out.println("Deseja copiar os clientes preciamente cadastrados:\n0 - Sim\n1 - Não");

			while(opcaoIncorreta){ //Não sai do loop enquanto a entrada não for no intevalo permitido
				String resposta = Leitura.lerString();
				if(resposta.equals("0")){
					int indice = listaSeguradoras.size() - 1; // o indice da seguradora atual na lista de seguradoras

					// Adicionando os clientes
					System.out.println("Clientes");
					for(int i = 0; i < listaClientes.size(); i++){ //lista os clientes
						System.out.println(Integer.toString(i) + " - " + listaClientes.get(i).getNome());
					}
					System.out.println("Escolha as opcao(separar por virgula): "); 
					String indices = Leitura.lerString();
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
					resposta = Leitura.lerString();
				}
			}
			if(listaSinistros.size() != 0){ //Significa que há sinistros previamente cadastrados
				opcaoIncorreta = true;
				System.out.println("Deseja copiar os sinistros preciamente cadastrados:\n0 - Sim\n1 - Não");

				while(opcaoIncorreta){ //Não sai do loop enquanto a entrada não for dentro do intelavo permitido
					String resposta = Leitura.lerString();
					if(resposta.equals("0")){
						int indice = listaSeguradoras.size() - 1;

						// Adicionando os sinistros
						System.out.println("Sinistros");
						for(int i = 0; i < listaSinistros.size(); i++){ //lista os sinistros
							System.out.println(Integer.toString(i) + " - " + listaSinistros.get(i).getid());
						}

						System.out.println("Escolha as opcao(separar por virgula): "); 
						String indices = Leitura.lerString();
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
					resposta = Leitura.lerString();
					}
				}
			}
			System.out.println("Seguradora " + seguradora.getNome() + " cadastrada com sucesso.");
		}
	}
	
	// Lista todos os clientes do tipo PF da seguradora desejada
	private static void listarClientesPF(){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			int indice = escolhaSeguradora();
			String lista = listaSeguradoras.get(indice).listarClientesBasicos("PF");

			if(lista.equals(""))
				System.out.println("Não há clientes PF cadastrados.");
			else
				System.out.println(lista);
		}
	}
	
	// Lista todos os clientes do tipo PJ da seguradora desejada
	private static void listaClientesPJ(){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			int indice = escolhaSeguradora();
			String lista = listaSeguradoras.get(indice).listarClientesBasicos("PJ");
			if(lista.equals(""))
				System.out.println("Não há clientes PJ cadastrados.");
			else
				System.out.println(lista);
		}
	}
	
	// Lista todos os sinistros da seguradora desejada
	private static void listarSinistrosSeguradora(){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			int indice = escolhaSeguradora();
			String lista = listaSeguradoras.get(indice).listarSinistros();

			if(lista.equals(""))
				System.out.println("Não há Sinistros nessa seguradora.");
			else
				System.out.println(lista);
		}
	}

	// Lista todos os sinistros do cliente desejado
	private static void listaSinistrosCliente(){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			int indice = escolhaSeguradora();
			System.out.println("Clientes: ");
			listaSeguradoras.get(indice).listarTodosClientes();
			System.out.println("Escolha uma opcao:");
			String resposta = Leitura.lerString();

			while(!Validacao.ehInteiro(resposta) && (INT(resposta) < 0 || INT(resposta) > listaSeguradoras.get(indice).getListaClientes().size())){
				System.out.println("Escolha invalida!\nEscolha uma opcao:");
				resposta = Leitura.lerString();
			}

			String nome = listaSeguradoras.get(indice).getListaClientes().get(INT(resposta)).getNome();
			if (!listaSeguradoras.get(indice).visualizarSinistro(nome))
				System.out.println("Não há sinistros desse cliente.");
		}
	}

	// Lista todos os veiculos de cliente desejado
	private static void listarVeiculosCliente(){
		int indice = escolhaCliente();
		String lista = listaClientes.get(indice).listaVeiculos();

		if (lista.equals(""))
			System.out.println("Não há veiculos para serem listados.");
		else
			System.out.println(lista);
	}

	// Lista todos os veiculos da seguradora desejada
	private static void listarVeiculosSeguradora(){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			int indice = escolhaSeguradora();
			String lista = listaSeguradoras.get(indice).listarVeiculos();

			if (lista.equals(""))
				System.out.println("Não há veiculos para serem listados.");
			else
				System.out.println(lista);
		}
	}

	// Gera um sinisto e o vincula a um cliente
	private static void gerarSinistro(){
		Sinistro sinistro = Leitura.lerSinistro();
		Cliente cliente = new Cliente(null, null);
		String resposta;
		boolean respostaIncorreta = true;

		// Adicinado ao sinistro o cliente que o sofreu **************************************************************************
		if(listaClientes.size() != 0){ //Já ha clientes cadastrados
			System.out.println("Opcoes:\n0 - Novo cliente\n1 - Cliente ja cadastrado");
			resposta = Leitura.lerString();

			while(respostaIncorreta){ //Não sai do loop enquanto a entrada não for dentro do intevalo permitido
				if(resposta.equals("0")){
					cliente = Leitura.lerCliente();
					listaClientes.add(cliente); // Adiciona o novo cliente a lista de cliente
					sinistro.setCliente(cliente);
					respostaIncorreta = false;
				}
				else if (resposta.equals("1")){
					System.out.println("Clientes:");
					int indice = escolhaCliente();
					cliente = listaClientes.get(indice);
					System.out.println("Cliente escolhido com sucesso.");
					sinistro.setCliente(cliente);
					respostaIncorreta = false;
				}
				else{
					System.out.println("Opcao invalida! Escolha outra:\n");
					resposta = Leitura.lerString();
				}
			}
		}

		else{
			cliente = Leitura.lerCliente();
			listaClientes.add(cliente);// Adiciona o novo cliente a lista de cliente
			sinistro.setCliente(cliente);
			System.out.println("Sinistro " + sinistro.getid() + " cadastrado com sucesso.");
		}

		respostaIncorreta = true;

		// Adiconando ao sinistro o veiculo que o sofreu************************************************************************************
		if(listaVeiculos.size() != 0){
			System.out.println("Opcoes:\n0 - Novo Veiculo\n1 - Veiculo ja cadastrado");
			resposta = Leitura.lerString();
			while(respostaIncorreta){
				if(resposta.equals("0")){
					Veiculo veiculo = Leitura.lerVeiculo();
					sinistro.setVeiculo(veiculo);
					cliente.addVeiculo(veiculo); // Adiciona implicitamnete o veiculo ao cliente
					listaVeiculos.add(veiculo); 
					respostaIncorreta = false;
				}
				else if (resposta.equals("1")){
					System.out.println("Veiculos:");
					for(int i = 0; i < listaVeiculos.size(); i++){
						System.out.println(Integer.toString(i) + " - " + listaVeiculos.get(i).getplaca());
					}
					System.out.println("Escolha uma opcao:"); 
					String indice = Leitura.lerString();
					while(!Validacao.ehInteiro(indice)){
						System.out.println("Escolha uma opcao:"); 
						indice = Leitura.lerString();
					}
					sinistro.setVeiculo(listaVeiculos.get(INT(indice)));
					cliente.addVeiculo(listaVeiculos.get(INT(indice))); // Adiciona implicitamnete o veiculo ao cliente
					respostaIncorreta = false;

				}
				else{
					System.out.println("Opcao invalida. Escolja uma opcao:\n");
					resposta = Leitura.lerString();
				}
			}
		}

		else{
			Veiculo veiculo = Leitura.lerVeiculo();
			sinistro.setVeiculo(veiculo);
			cliente.addVeiculo(veiculo); // Adiciona implicitamnete o veiculo ao cliente 
			listaVeiculos.add(veiculo); 
		}
		listaSinistros.add(sinistro);

		respostaIncorreta = true;

		// Adicionar a uma seguradora************************************************************************************
		if(listaSeguradoras.size() != 0){
			System.out.println("Escolha uma opcao de seguradora vincular:\n0 - Nova\n1 - Ja cadastrado");
			resposta = Leitura.lerString();
			while(respostaIncorreta){
				if(resposta.equals("0")){
					Seguradora seguradora = Leitura.lerSeguradora();
					sinistro.setSeguradora(seguradora);
					listaSeguradoras.add(seguradora);
					seguradora.addCliente(cliente);

					if(seguradora.addSinistro(sinistro)){
						System.out.println("Sinistro " + sinistro.getid() + " adicionado a Seguradora " + seguradora.getNome() + ".");
						sinistro.setSeguradora(seguradora);
					}
					else
						System.out.println("Não foi possivel adicionar.");
					
					respostaIncorreta = false;
				}
				else if(resposta.equals("1")){
					System.out.println("Escolha uma seguradora ja cadastrada para vincular.");
					int indiceSeguradora = escolhaSeguradora();
					listaSeguradoras.get(indiceSeguradora).addCliente(cliente);

					if(listaSeguradoras.get(indiceSeguradora).addSinistro(sinistro)){
						System.out.println("Sinistro " + sinistro.getid() + " adicionado a Seguradora " + listaSeguradoras.get(indiceSeguradora).getNome() + ".");
						sinistro.setSeguradora(listaSeguradoras.get(indiceSeguradora));
					}
					else
						System.out.println("Não foi possivel adicionar.");

					respostaIncorreta = false;
				}
				else{
					System.out.println("Opcao invalida. Escolja uma opcao:\n");
					resposta = Leitura.lerString();
				}
			}
		}

		else{
			Seguradora seguradora = Leitura.lerSeguradora();
			sinistro.setSeguradora(seguradora);
			listaSeguradoras.add(seguradora);
			seguradora.addCliente(cliente);

			if(seguradora.addSinistro(sinistro)){
				System.out.println("Sinistro " + sinistro.getid() + " adicionado a Seguradora " + seguradora.getNome() + ".");
				sinistro.setSeguradora(seguradora);
			}
			else
				System.out.println("Não foi possivel adicionar.");
					
		}

		

	}

	// Exclui um cliente desejado
	private static void excluirCliente(){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			int indice = escolhaSeguradora();
			System.out.println("Clientes:");
			listaSeguradoras.get(indice).listarTodosClientes();
			System.out.println("Escolha uma opcao:");
			String resposta = Leitura.lerString();
	
			while(!Validacao.ehInteiro(resposta) && (INT(resposta) < 0 || INT(resposta) > listaSeguradoras.get(indice).getListaClientes().size())){
				System.out.println("Escolha invalida!\nEscolha uma opcao:");
				resposta = Leitura.lerString();
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
	private static void excluirVeiculo(){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			int indiceSeguradora = escolhaSeguradora();
			System.out.println("Clientes:");
			listaSeguradoras.get(indiceSeguradora).listarTodosClientes();
			System.out.println("Escolha uma opcao:");
			String indiceCliente = Leitura.lerString();
	
			//Não sai do loop enquanto a entrada não for valida
			while(!Validacao.ehInteiro(indiceCliente) && (INT(indiceCliente) < 0 || INT(indiceCliente) > listaSeguradoras.get(indiceSeguradora).getListaClientes().size())){
				System.out.println("Escolha invalida!\nEscolha uma opcao:");
				indiceCliente = Leitura.lerString();
			}
			String preLista = listaSeguradoras.get(indiceSeguradora).getListaClientes().get(INT(indiceCliente)).listaVeiculos();
			String listaFormatada = formatarString(preLista);

			System.out.print("Veiculos:\n" + listaFormatada + "\nEscolha um opcao:\n");
			String indiceVeiculo = Leitura.lerString();

			//Não sai do loop enquanto a entrada não for valida
			while(!Validacao.ehInteiro(indiceVeiculo) && (INT(indiceVeiculo) < 0 || INT(indiceVeiculo) > preLista.split("\n").length)){
				System.out.println("Escolha invalida!\nEscolha uma opcao:");
				indiceVeiculo = Leitura.lerString();
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
	private static void excluirSinistro(){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			int indice = escolhaSeguradora();
			System.out.print("ID do Sinistro: ");
			listaSeguradoras.get(indice).listarSinistros();
			System.out.println("Escolha uma opcao:");
			String indiceSinistro = Leitura.lerString();

			//Não sai do loop enquanto a entrada não for valida
			while(!Validacao.ehInteiro(indiceSinistro) && (INT(indiceSinistro) < 0 || INT(indiceSinistro) > listaSeguradoras.get(indice).getListaSinistros().size())){
				System.out.println("Escolha invalida!\nEscolha uma opcao:");
				indiceSinistro = Leitura.lerString();
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

	// Calcula a receita da segurado escolhida e a imprimi
	private static void calcularReceira(){
		int indiceSeguradora = escolhaSeguradora();
		double receita = listaSeguradoras.get(indiceSeguradora).calcularReceita();
		System.out.println("Receita da Seguradora " + listaSeguradoras.get(indiceSeguradora).getNome() + ": R$ " + Double.toString(receita));
	}

	// Transfere a lista de veiculos de um cliente para outro
	private static void transferirSeguro(){
		int indiceSeguradora = escolhaSeguradora();
		System.out.println("Escolha o Cliente Remetente:");
		listaSeguradoras.get(indiceSeguradora).listarTodosClientes();
		String indiceClienteRemetente = Leitura.lerString();

		while(!Validacao.ehInteiro(indiceClienteRemetente) && (INT(indiceClienteRemetente) < 0 || INT(indiceClienteRemetente) > listaSeguradoras.get(indiceSeguradora).getListaClientes().size())){
			System.out.println("Escolha invalida!\nEscolha uma opcao:");
			indiceClienteRemetente = Leitura.lerString();
		}

		if(listaSeguradoras.get(indiceSeguradora).getListaClientes().get(INT(indiceClienteRemetente)).getListaVeiculos().size() != 0){
			listaSeguradoras.get(indiceSeguradora).listarTodosClientes();
			System.out.println("Escolha o Cliente Destinatario:");
			String indiceClienteDestinatario = Leitura.lerString();
	
			while(!Validacao.ehInteiro(indiceClienteDestinatario) && !(INT(indiceClienteDestinatario) >= 0 && INT(indiceClienteDestinatario) < listaSeguradoras.get(indiceSeguradora).getListaClientes().size())){
				System.out.println("Escolha invalida!\nEscolha uma opcao:");
				indiceClienteDestinatario = Leitura.lerString();
			}

			if(indiceClienteRemetente == indiceClienteDestinatario)
				System.out.println("Operacao abortada. O cliente possui 0 veiculos.");
			else{
				Cliente remetente = listaSeguradoras.get(indiceSeguradora).getListaClientes().get(INT(indiceClienteRemetente));
				Cliente destinatario = listaSeguradoras.get(indiceSeguradora).getListaClientes().get(INT(indiceClienteDestinatario));

				if (destinatario.addVeiculo(remetente.getListaVeiculos())){
					System.out.println("Tranferencia ocorrida com sucesso.");
					listaSeguradoras.get(indiceSeguradora).calcularPrecoSeguroCliente(destinatario);
					listaSeguradoras.get(indiceSeguradora).calcularPrecoSeguroCliente(remetente);
					remetente.removerTodosVeiculos();
				}
				else{
					System.out.println("Ocorreu um erro ao transferir os veiculos");
				}
			}


		}
		else{
			System.out.println("Operacao abortada. O cliente possui 0 veiculos.");
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
	private static void executarOpcaoMenuExterno(MenuOpcoes op) {
		switch(op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op);
				break;
			case GERAR_SINISTRO:
				gerarSinistro();
				break;
			case TRANSFERIR_SEGURO:
				transferirSeguro();
				break;
			case CALCULAR_RECEITA:
				calcularReceira();
				break;
			//case SAIR:
		}
	}

	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu) {
		switch(opSubmenu) {
			case CADASTRAR_CLIENTE:
				cadastrarCliente();
				break;
			case CADASTRAR_VEICULO:
				cadastrarVeiculo();
				break;
			case CADASTRAR_SEGURADORA:
				cadastrarSeguradora();
				break;
			case LISTAR_CLIENTES_PF:
				listarClientesPF();
				break;
			case LISTAR_CLIENTES_PJ:
				listaClientesPJ();
				break;
			case LISTAR_SINISTROS_SEGURADORA:
				listarSinistrosSeguradora();
				break;
			case LISTAR_SINISTROS_CLIENTE:
				listaSinistrosCliente();;
				break;
			case LISTAR_VEICULOS_CLIENTE:
				listarVeiculosCliente();
				break;
			case LISTAR_VEICULOS_SEGURADORA:
				listarVeiculosSeguradora();
				break;
			case EXCLUIR_CLIENTE:
				excluirCliente();
				break;
			case EXCLUIR_VEICULO:
				excluirVeiculo();;
				break;
			case EXCLUIR_SINISTRO:
				excluirSinistro();;
			break;
		//case VOLTAR:
		//	break;
		}
	}
	
	//executa os submenus: exibição do menu, leitura da opção e execução dos métodos
	private static void executarSubmenu(MenuOpcoes op) {
		SubmenuOpcoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op);
			executarOpcaoSubMenu(opSubmenu);
		}while(opSubmenu != SubmenuOpcoes.VOLTAR);
	}

	//executa o menu externo: exibição do menu, leitura da opção e execução da opção
	public static void main(String[] args) {
		MenuOpcoes op;
		instanciar();
		do {
			exibirMenuExterno();
			op = lerOpcaoMenuExterno();
			executarOpcaoMenuExterno(op);
		}while(op != MenuOpcoes.SAIR);
		System.out.println("Saiu do sistema");
	}
}