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

		//Adiciono os sinistros a seguradora:
		seguradora.addSinistro(sinistro1);
		seguradora.addSinistro(sinistro2);

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

	// Lista todas Seguradoras cadastradas e retorna a seguradora escolhida
	private static Seguradora escolhaSeguradora(){
		System.out.println("Seguradoras:");

		// Imprimi todas as seguradoras
		for(int i = 0; i < listaSeguradoras.size(); i++){
			System.out.println(Integer.toString(i) + " - " + listaSeguradoras.get(i).getNome());
		}
		System.out.println("Escolha uma opcao:"); 
		String indice = Leitura.lerString();

		// Enquanto o indice não for um inteiro e não estiver no intervalo de 0 até o tamanho da lista
		while(true){
			try{
				return listaSeguradoras.get(INT(indice));
			}
			catch(Exception e){
				System.out.println("Escolha uma opcao valida:"); 
				indice = Leitura.lerString();
			}
		}
	}

	// Lista todos Clientes cadastrados e retorna o cliente escolhido
	private static Cliente escolhaCliente(){
		System.out.println("Clientes:");

		// Imprimi todos os clientes
		for(int i = 0; i < listaClientes.size(); i++){
			System.out.println(Integer.toString(i) + " - " + listaClientes.get(i).getNome());
		}
		System.out.println("Escolha uma opcao:"); 
		String indice = Leitura.lerString();

		// Enquanto o indice não for um inteiro e não estiver no intervalo de 0 até o tamanho da lista
				// Enquanto o indice não for um inteiro e não estiver no intervalo de 0 até o tamanho da lista
		while(true){
			try{
				return listaClientes.get(INT(indice));
			}
			catch(Exception e){
				System.out.println("Escolha uma opcao valida:"); 
				indice = Leitura.lerString();
			}
		}
	}

	// Cadastra o cliente e o adiciona na lista de cliente e se pedido o adiciona a uma seguradora
	private static void cadastrarCliente(){
		Cliente cliente = Leitura.lerCliente();
		listaClientes.add(cliente); // adiciona o cliente a lista com todos os clientes

		if(listaSeguradoras.size() != 0){ //se houver uma seguradora já cadastrada
			System.out.println("Deseja cadastar em um seguradora previamente cadastrada?\n0 - Sim\n1 - Nao\nEscolha uma opcao:");
			String resposta = Leitura.lerString();
			boolean respostaIncorreta = true;

			while(respostaIncorreta){  // enquanto não for escolhido ou '0' ou '1'

				if(resposta.equals("0")){ //cadastrar em um seguradora ja cadastrada
					Seguradora seguradora = escolhaSeguradora();
					if(seguradora.addCliente(cliente)){ //se o objeto for adiconado na lista
						System.out.println("Cliente " + cliente.getNome() + " cadastrado na Seguradora " + seguradora.getNome() + ".");
					}
					else{
						System.out.println("Não foi possível adicionar à seguradora.");
					}
					respostaIncorreta = false;
				}

				else if(resposta.equals("1")){ // apenas add na listaClientes
					System.out.println("Cliente " + cliente.getNome() + " cadastrado com sucesso.");
					respostaIncorreta = false;
				}
				else{
					System.out.println("Opcao invalida.\nEscolha uma opcao:");
					resposta = Leitura.lerString();
				}
			}
		}

		else{
			System.out.println("Cliente " + cliente.getNome() + " cadastrado com sucesso.");
		}
	}

	// Cadastra o veiculo e o adiciona na lista de veiculos e se pedido o adiciona a um cliente
	private static void cadastrarVeiculo(){
		Veiculo veiculo = Leitura.lerVeiculo();
		listaVeiculos.add(veiculo);

		if(listaClientes.size() != 0){ // caso ja exista clientes no sistema
			System.out.println("Deseja cadastar em um cliente previamente cadastrado?\n0 - Sim\n1 - Nao\nEscolha uma opcao:");
			String resposta = Leitura.lerString();
			boolean respostaIncorreta = true;

			while(respostaIncorreta){ //Não sai do loop enquanto a entrada não for '0' ou '1'
				if(resposta.equals("0")){ // cadastar em um cliente ja cadastrado
					Cliente cliente = escolhaCliente();
					cliente.addVeiculo(veiculo);
					System.out.println("Veiculo " + veiculo.getModelo() + " cadastrado no Cliente " + cliente.getNome() + ".");

					respostaIncorreta = false;
				}
				else if(resposta.equals("1")){ // apenas adiciona na lista
					System.out.println("Veiculo " + veiculo.getModelo() + " cadastrado com sucesso.");
					respostaIncorreta = false;
				}
				else{
					System.out.println("Escolha uma opcao:");
					resposta = Leitura.lerString();

				}
			}
		}

		else{ // apenas adiciona na lista
			System.out.println("Veiculo " + veiculo.getModelo() + " cadastrado com sucesso.");
		}
	}

	// Cadastra uma seguradora e adiciona na lista de seguradoras
	private static void cadastrarSeguradora(){
		Seguradora seguradora = Leitura.lerSeguradora();
		listaSeguradoras.add(seguradora);  //adiciona a lista de seguradoras
		
		if (listaClientes.size() != 0){ // Significa que clientes já foram cadastrados
			Boolean opcaoIncorreta = true;
			System.out.println("Deseja copiar os clientes preciamente cadastrados:\n0 - Sim\n1 - Não");

			while(opcaoIncorreta){ //Não sai do loop enquanto a entrada não for '0' ou '1'
				String resposta = Leitura.lerString();

				if(resposta.equals("0")){ //copiar clientes

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
						try{
							seguradora.addCliente(listaClientes.get(INT(numero)));
							System.out.println("Cliente" + listaClientes.get(INT(numero)).getNome() + "adicionado");
						}
						catch(Exception e){
							System.out.println(Integer.toString(i + 1) + "° indice inválido.");
						}
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

				while(opcaoIncorreta){ //Não sai do loop enquanto a entrada não for '0' ou '1'
					String resposta = Leitura.lerString();

					if(resposta.equals("0")){ //copiar os sinistros

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
							try{
								seguradora.addSinistro(listaSinistros.get(INT(numero)));
							}
							catch (Exception e){
								System.out.println(Integer.toString(i + 1) + "° indice inválido.");
							}
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
		}
		System.out.println("Seguradora " + seguradora.getNome() + " cadastrada com sucesso.");
	}
	
	// Lista todos os clientes do tipo PF da seguradora desejada
	private static void listarClientesPF(){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			Seguradora seguradora = escolhaSeguradora();
			String lista = seguradora.listarClientesBasicos("PF");

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
			Seguradora seguradora = escolhaSeguradora();
			String lista = seguradora.listarClientesBasicos("PJ");
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
			Seguradora seguradora = escolhaSeguradora();
			String lista = seguradora.listarSinistros();

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
			Seguradora seguradora = escolhaSeguradora();
			System.out.println("Clientes: ");
			seguradora.listarTodosClientes();
			System.out.println("Escolha uma opcao:");
			String resposta = Leitura.lerString();

			while(true){
				try{
					String nome = seguradora.getListaClientes().get(INT(resposta)).getNome();
					if (!seguradora.visualizarSinistro(nome)) // o proprio metodo ja imprimi
						System.out.println("Não há sinistros desse cliente.");
					break;
				}
				catch(Exception e){
					System.out.println("Escolha invalida!\nEscolha uma opcao:");
					resposta = Leitura.lerString();
				}
			}


		}
	}

	// Lista todos os veiculos de cliente desejado
	private static void listarVeiculosCliente(){
		Cliente cliente = escolhaCliente();
		String lista = cliente.listaVeiculos();

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
			Seguradora seguradora = escolhaSeguradora();
			String lista = seguradora.listarVeiculos();

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

		// Adicinado ao sinistro o cliente que o sofreu **************************************************************************
		if(listaClientes.size() != 0){ //Já ha clientes cadastrados
			System.out.println("Opcoes:\n0 - Novo cliente\n1 - Cliente ja cadastrado");
			resposta = Leitura.lerString();

			while(true){ //Não sai do loop enquanto a entrada não for dentro do intevalo permitido
				if(resposta.equals("0")){
					cliente = Leitura.lerCliente();
					listaClientes.add(cliente); // Adiciona o novo cliente a lista de cliente
					sinistro.setCliente(cliente);
					break;
				}
				else if (resposta.equals("1")){
					System.out.println("Clientes:");
					cliente = escolhaCliente();
					System.out.println("Cliente escolhido com sucesso.");
					sinistro.setCliente(cliente);
					break;
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

		// Adiconando ao sinistro o veiculo que o sofreu************************************************************************************
		if(listaVeiculos.size() != 0){
			System.out.println("Opcoes:\n0 - Novo Veiculo\n1 - Veiculo ja cadastrado");
			resposta = Leitura.lerString();
			while(true){
				if(resposta.equals("0")){
					Veiculo veiculo = Leitura.lerVeiculo();
					sinistro.setVeiculo(veiculo);
					cliente.addVeiculo(veiculo); // Adiciona implicitamnete o veiculo ao cliente
					listaVeiculos.add(veiculo); 
					break;
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
					break;

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

		// Adicionar a uma seguradora************************************************************************************
		if(listaSeguradoras.size() != 0){
			System.out.println("Escolha uma opcao de seguradora vincular:\n0 - Nova\n1 - Ja cadastrado");
			resposta = Leitura.lerString();
			while(true){
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
					
					break;
				}
				else if(resposta.equals("1")){
					System.out.println("Escolha uma seguradora ja cadastrada para vincular.");
					Seguradora seguradora = escolhaSeguradora();
					seguradora.addCliente(cliente);

					if(seguradora.addSinistro(sinistro)){
						System.out.println("Sinistro " + sinistro.getid() + " adicionado a Seguradora " + seguradora.getNome() + ".");
						sinistro.setSeguradora(seguradora);
					}
					else
						System.out.println("Não foi possivel adicionar.");

					break;
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
			Seguradora seguradora = escolhaSeguradora();
			System.out.println("Clientes:");
			seguradora.listarTodosClientes();
			System.out.println("Escolha uma opcao:");
			String resposta = Leitura.lerString();
			
			while(true){
				try{
					String nome = seguradora.getListaClientes().get(INT(resposta)).getNome();
					if(seguradora.removeCliente(nome))
						System.out.println("Cliente " + nome + " removido.");
					else
						System.out.println("Não foi possivel remover.");
					break;
				}
				catch (Exception e){
					System.out.println("Escolha invalida!\nEscolha uma opcao:");
					resposta = Leitura.lerString();
				}
			}
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
			Seguradora seguradora = escolhaSeguradora();
			System.out.println("Clientes:");
			seguradora.listarTodosClientes();
			System.out.println("Escolha uma opcao:");
			String indiceCliente = Leitura.lerString(), preLista, listaFormatada;
			Veiculo veiculo;
			Cliente cliente;
	
			//Não sai do loop enquanto a entrada não for valida
			while(true){
				try{
					cliente = seguradora.getListaClientes().get(INT(indiceCliente));
					break;
				}
				catch(Exception e){
					System.out.println("Escolha invalida!\nEscolha uma opcao:");
					indiceCliente = Leitura.lerString();
				}
			}

			preLista = cliente.listaVeiculos();
			if(cliente.getListaVeiculos().size() != 0){
				listaFormatada = formatarString(preLista);
				System.out.println("Veiculos:\n" + listaFormatada + "Escolha um opcao:");
				String indiceVeiculo = Leitura.lerString();

				//Não sai do loop enquanto a entrada não for valida
				while(true){
					try {
						veiculo = seguradora.getListaClientes().get(INT(indiceCliente)).getListaVeiculos().get(INT(indiceVeiculo));
						break;
					}
					catch (Exception e) {
						System.out.println("Escolha invalida!\nEscolha uma opcao:");
						indiceVeiculo = Leitura.lerString();
					}
				}
				String modelo = veiculo.getModelo();
				String placa = veiculo.getplaca();
				if (seguradora.getListaClientes().get(INT(indiceCliente)).removerVeiculo(placa)){
					System.out.println("Veiculo (" + modelo + ")" + placa + " removido.");
				} 
				else{
					System.out.println("Não foi possivel remover o veiculo.");
				}
			}
			else{
				System.out.println("O cliente não possui veiculos.");
			}
		}
		
	}

	// Excluir o sinistro de um determinado cliente
	private static void excluirSinistro(){
		if (listaSeguradoras.size() == 0){
			System.out.println("Ainda não há seguradora cadastrada no sistema!");
		}
		else{
			Seguradora seguradora = escolhaSeguradora();
			if(seguradora.getListaSinistros().size() != 0){
				System.out.println("ID do Sinistro: ");
				System.out.println(seguradora.listarSinistros());
				System.out.println("Escolha uma opcao:");
				String indiceSinistro = Leitura.lerString();

				//Não sai do loop enquanto a entrada não for valida
				int id;
				while(true){
					try {
						id = seguradora.getListaSinistros().get(INT(indiceSinistro)).getid();
						break;			
					}
					catch (Exception e) {
						System.out.println("Escolha invalida!\nEscolha uma opcao:");
						indiceSinistro = Leitura.lerString();
					}

				}
				if(seguradora.removerSinistro(id)){
					System.out.println("Sinistro " + Integer.toString(id) + " removido.");
				}
				else{
					System.out.println("Não foi possível remover o sinistro.");
				}
			}
			else{
				System.out.println("Não há sinistros nessa seguradora.");
			}
		}
	}

	//Imprimi a receita da seguradora escolhida
	private static void calcularReceira(){
		Seguradora seguradora = escolhaSeguradora();
		double receita = seguradora.calcularReceita();
		System.out.println("Receita da Seguradora " + seguradora.getNome() + ": R$ " + Double.toString(receita));
	}

	// Transfere a lista de veiculos de um cliente para outro
	private static void transferirSeguro(){
		Seguradora seguradora = escolhaSeguradora();
		if(seguradora.getListaClientes().size() >=  2){
			System.out.println("Escolha o Cliente Remetente:");
			seguradora.listarTodosClientes();
			String indiceClienteRemetente = Leitura.lerString();

			while(true){
				try {
					if(seguradora.getListaClientes().get(INT(indiceClienteRemetente)).getListaVeiculos().size() != 0){
						seguradora.listarTodosClientes();
						System.out.println("Escolha o Cliente Destinatario:");
						String indiceClienteDestinatario = Leitura.lerString();
				
						while(true){
							try {
								if(indiceClienteRemetente == indiceClienteDestinatario)
									System.out.println("Operacao abortada. O cliente possui 0 veiculos.");
								else{
									Cliente remetente = seguradora.getListaClientes().get(INT(indiceClienteRemetente));
									Cliente destinatario = seguradora.getListaClientes().get(INT(indiceClienteDestinatario));
					
									if (destinatario.addVeiculo(remetente.getListaVeiculos())){
										System.out.println("Tranferencia ocorrida com sucesso.");
										seguradora.calcularPrecoSeguroCliente(destinatario);
										seguradora.calcularPrecoSeguroCliente(remetente);
										remetente.removerTodosVeiculos();
									}
									else{
										System.out.println("Ocorreu um erro ao transferir os veiculos");
									}
									break;
								}
							}
							catch (Exception e) {
								System.out.println("Escolha invalida!\nEscolha uma opcao:");
								indiceClienteDestinatario = Leitura.lerString();
							}
						}
					}
					else{
						System.out.println("Operacao abortada. O cliente possui 0 veiculos.");
					}
					break;
				}
				catch (Exception e) {
					System.out.println("Escolha invalida!\nEscolha uma opcao:");
					indiceClienteRemetente = Leitura.lerString();
				}
			}
		}
		else{
			System.out.println("Operacao abortada. Ha apenas um clientes.");
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
		String opUsuario;
		MenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = Leitura.lerString();
		}while(!Validacao.ehInteiro(opUsuario) || (INT(opUsuario) < 0 || INT(opUsuario) > MenuOpcoes.values().length - 1));
		opUsuarioConst = MenuOpcoes.values()[INT(opUsuario)];
		return opUsuarioConst;
	}
	
	//ler opção dos submenus
	private static SubmenuOpcoes lerOpcaoSubmenu(MenuOpcoes op) {
		String opUsuario;
		SubmenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = Leitura.lerString();
		}while(!Validacao.ehInteiro(opUsuario) || (INT(opUsuario) < 0 || INT(opUsuario) > op.getSubmenu().length - 1));
		opUsuarioConst = op.getSubmenu()[INT(opUsuario)];
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