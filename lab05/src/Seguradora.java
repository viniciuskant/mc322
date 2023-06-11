import java.util.ArrayList;
import java.util.Scanner;

public class Seguradora {
    private final String cnpj;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
    private ArrayList<Seguro> listaSeguros = new ArrayList<Seguro>();

    // construtor
    public Seguradora(String nome, String cnpj, String telefone, String endereco, String email) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
    }

    // getters e setters
    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Seguro> getListaSeguros() {
        return listaSeguros;
    }

    public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
        this.listaSeguros = listaSeguros;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    // Métodos
    public String toString() {
        return "Nome: " + getNome() +
                "\nCNPJ: " + getCnpj() +
                "\nTelefone: " + getTelefone() +
                "\nEmail: " + getEmail() +
                "\nEndereco: " + getEndereco();
    }

    public void listarClientes() {
        if(listaClientes.size() != 0){
            System.out.println("Lista de Clientes:");
            for (Cliente cliente : listaClientes)
                System.out.println("\t" + cliente.toStringBasico());
        }
        else
            System.out.println("Não a clientes cadastrados.");
    }

    public boolean gerarSeguro(Seguro seguro){
        return listaSeguros.add(seguro);
    }

    public boolean gerarSeguro() {
        String indiceVeiculo, indiceFrota, resposta;
        Cliente cliente = escolherCliente();

        Date dataInicio = Validacao.lerData("Data de inicio");
        Date dataFim = Validacao.lerData("Data de fim");

        if (cliente instanceof ClientePF) {
            SeguroPF seguro = new SeguroPF(dataInicio, dataFim, this, (ClientePF) cliente);
            System.out.println("Qual veiculo receberá o seguro:\n" + ((ClientePF) cliente).listarVeiculos());
            indiceVeiculo = lerString();

            while (true) {
                try {
                    seguro.setVeiculo(((ClientePF)cliente).getListaVeiculos().get(Integer.parseInt(indiceVeiculo)));
                    break;
                } catch (Exception e) {
                    System.out.println("Indice invalido. Escolha novamente: ");
                    indiceVeiculo = lerString();
                }
            }

            System.out.println("Cadatrar condutores:\n\t0-Sim\n\t1-Nao");
            resposta = lerString();
            while(true){
                if(resposta.equals("0")){
                    seguro.autorizarCondutor();
                    System.out.println("Condutor cadastrado com sucesso.\nCadatrar mais condutores:\n\t0-Sim\n\t1-Nao");
                    resposta = lerString();
                }
                
                else if(resposta.equals("1")){
                    System.out.println("Seguro" + seguro.getIdSeguro() +"cadastrado com sucesso.");
                    return listaSeguros.add(seguro);
                }

                else{
                    System.out.println("Indice invalido. Escolha novamente: ");
                    resposta = lerString();

                }
            }
        }

        else{
            SeguroPJ seguro;
            System.out.print("Qual frota receberá o seguro:\n" + ((ClientePJ) cliente).listarFrotas());
            indiceFrota = lerString();
            
            while (true) {
                try {
                    Frota frota = ((ClientePJ)cliente).getListaFrota().get(Integer.parseInt((indiceFrota)));
                    seguro = new SeguroPJ(dataInicio, dataFim, this, frota, (ClientePJ)cliente);
                    break;
                } catch (Exception e) {
                    System.out.println("Indice invalido. Escolha novamente: ");
                    indiceFrota = lerString();
                }
            }

            System.out.println("Cadatrar condutores:\n\t0-Sim\n\t1-Nao");
            resposta = lerString();
            while(true){
                if(resposta.equals("0")){
                    seguro.autorizarCondutor();
                    System.out.println("Condutor cadastrado com sucesso.\\n" + "Cadatrar condutores:\n\t0-Sim\n\t1-Nao");
                    resposta = lerString();
                }
                
                else if(resposta.equals("1")){
                    System.out.println("Seguro" + seguro.getIdSeguro() +"cadastrado com sucesso.");
                    return listaSeguros.add(seguro);
                }

                else{
                    System.out.println("Indice invalido. Escolha novamente: ");
                    resposta = lerString();

                }
            }

        }
    }

    public boolean cancelarSeguro(Seguro seguro) {
        return listaSeguros.remove(seguro);
    }

    public boolean cancelarSeguro(){
        if (listaSeguros.size() == 0)
            return false;
        return cancelarSeguro(escolhaSeguro());
    }

    public boolean cancelarSeguro(Cliente cliente){
        int incides[] = new int[listaSeguros.size()];
        int contador = 0;
        for(int i = 0; i < listaSeguros.size(); i++){
            if(listaSeguros.get(i).getCliente().equals(cliente)){
                incides[contador] = i;
                contador++;
            }
        }
        for(int indice: incides){
            listaSeguros.remove(indice);
        }
        return true;
    }

    public boolean cadastrarCliente(Cliente cliente) {
        return listaClientes.add(cliente);
    }

    public boolean cadastrarCliente(){
        Cliente cliente = lerCliente();
        return cadastrarCliente(cliente);
    }

    public int indexCliente(String cliente) {
        int i = 0;
        for (; i < listaClientes.size() && listaClientes.get(i).getNome() != cliente; i++)
            ;

        if (i == listaClientes.size())
            return -1;

        return i;
    }

    public boolean removeCliente(Cliente cliente) {
        return listaClientes.remove(cliente);
    }

    public boolean removeCliente(){
       Cliente cliente = escolherCliente();
       cancelarSeguro(cliente);
       return removeCliente(cliente);
    }

    public ArrayList<Sinistro> getSinistroPorCliente(String CadastroNacional) {
        for (Seguro seguro : listaSeguros) {
            if (seguro instanceof SeguroPF) {
                if (((SeguroPF) seguro).getCliente().getCpf().equals(CadastroNacional)) {
                    return seguro.getListaSinistros();
                }
            } else {
                if (((SeguroPJ) seguro).getCliente().getCnpj().equals(CadastroNacional)) {
                    return seguro.getListaSinistros();
                }
            }
        }
        return new ArrayList<Sinistro>();
    }

    public ArrayList<Sinistro> getSinistroPorCliente(){
        Cliente cliente = escolherCliente();
        for(Seguro seguro: listaSeguros){
            if(seguro.getCliente().equals(cliente))
                return seguro.getListaSinistros();
        }
        return new ArrayList<Sinistro>();
    }

    public ArrayList<Seguro> getSeguroPorCliente(String CadastroNacional) {
        ArrayList<Seguro> lista = new ArrayList<Seguro>();
        for (Seguro seguro : listaSeguros) {
            if (seguro instanceof SeguroPF) {
                if (((SeguroPF) seguro).getCliente().getCpf().equals(CadastroNacional)) {
                    lista.add(seguro);
                }
            } else {
                if (((SeguroPJ) seguro).getCliente().getCnpj().equals(CadastroNacional)) {
                    lista.add(seguro);
                }
            }
        }
        return lista;
    }

    public ArrayList<Seguro> getSeguroPorCliente() {
        ArrayList<Seguro> lista = new ArrayList<Seguro>();
        Cliente cliente = escolherCliente();
        for(Seguro seguro: listaSeguros){
            if(seguro.getCliente().equals(cliente))
                lista.add(seguro);
        }
        return lista;
    }

    public double calcularReceita() {
        double receita = 0;
        for (Seguro seguro : listaSeguros) {
            receita += seguro.getValorMensal();
        }
        return receita;
    }

    public int nClientesPF(){
            int contador = 0;
        for(Cliente cliente: listaClientes){
            if(cliente instanceof ClientePF)
                contador++;
        }
        return contador;
    }

    public int nClientesPJ(){
            int contador = 0;
        for(Cliente cliente: listaClientes){
            if(cliente instanceof ClientePJ)
                contador++;
        }
        return contador;
    }

    public ClientePF escolherClientePF(){
        System.out.println("Clientes PF:");
        int i = 0, indicesValidos[] =  new int[listaClientes.size()];
        for(int indice = 0; indice < listaClientes.size(); indice++){
            if(listaClientes.get(indice) instanceof ClientePF){
                System.out.print(i + " - " + listaClientes.get(indice).toStringBasico());
                indicesValidos[i] = indice;
                i++;
            }
        }
		System.out.println("Escolha uma opcao:"); 
		String indice = lerString();

		while(true){
			try{
                int intIndice = Integer.parseInt(indice);
                if(intIndice < 0 || intIndice >= i){
                    System.out.println("Escolha uma opcao valida:"); 
				    indice = lerString();
                }
                else {
                    return (ClientePF)listaClientes.get(indicesValidos[intIndice]);
                }
			}
			catch(Exception e){
				System.out.println("Escolha uma opcao valida:"); 
				indice = lerString();
			}
		}
    }

    public ClientePJ escolherClientePJ(){
        System.out.println("Clientes PJ:");
        int i = 0, indicesValidos[] =  new int[listaClientes.size()];
        for(int intIndice = 0; intIndice < listaClientes.size(); intIndice++){
            if(listaClientes.get(intIndice) instanceof ClientePJ){
                System.out.print(i + " - " + listaClientes.get(intIndice).toStringBasico());
                indicesValidos[i] = intIndice;
                i++;
            }
        }
		System.out.println("Escolha uma opcao:"); 
		String indice = lerString();

		while(true){
			try{
                int intIndice = Integer.parseInt(indice);
                if(intIndice < 0 || intIndice >= i){
                    System.out.println("Escolha uma opcao valida:"); 
				    indice = lerString();
                }
                else {
                    return (ClientePJ)listaClientes.get(indicesValidos[intIndice]);
                }
			}
			catch(Exception e){
				System.out.println("Escolha uma opcao valida:"); 
				indice = lerString();
			}
		}
    }

    // Lista todos os clientes e retorna o cliente escolhido
    private Cliente escolherCliente() {
        System.out.println("Clientes:");

        // Imprimi todos os clientes
        for (int i = 0; i < listaClientes.size(); i++) {
            System.out.println("\t" + Integer.toString(i) + " - " + listaClientes.get(i).getNome());
        }
        System.out.println("Escolha uma opcao:");
        String indice = lerString();

        while (true) { // Enquanto o indice não for um inteiro e não estiver no intervalo de 0 até o tamanho da lista
            try {
                return listaClientes.get(Integer.parseInt(indice));
            } catch (Exception e) {
                System.out.print("Escolha uma opcao valida: ");
                indice = lerString();
            }
        }
    }

    // Lista todos os seguros e retorna o seguro escolhido
    private Seguro escolhaSeguro() {
        System.out.println("Seguros:");

        // Imprimi todos os clientes
        for (int i = 0; i < listaSeguros.size(); i++) {
            System.out.println(Integer.toString(i) + " - " + listaSeguros.get(i).getCliente().getNome() + " - " + listaSeguros.get(i).getIdSeguro());
        }
        System.out.println("Escolha uma opcao:");
        String indice = lerString();

        while (true) { // Enquanto o indice não for um inteiro e não estiver no intervalo de 0 até o tamanho da lista
            try {
                return listaSeguros.get(Integer.parseInt(indice));
            } catch (Exception e) {
                System.out.print("Escolha uma opcao valida: ");
                indice = lerString();
            }
        }
    } 

    // Metodo que interage com o usuário e retorna um ClientePF
    public static ClientePF lerPF() {
        String nome, endereco, educacao, genero, cpf, telefone, email;
        Date dataNascimento;

        nome = Validacao.lerNome("Nome");

        System.out.print("Endereço: ");
        endereco = lerString();

        System.out.print("Telefone: ");
        telefone = lerString();

        System.out.print("Email: ");
        email = lerString();

        dataNascimento = Validacao.lerData("Data de Nascimento");

        System.out.print("Escolaridade: ");
        educacao = lerString();

        System.out.print("Gênero: ");
        genero = lerString();

        cpf = Validacao.lerCPF();

        return new ClientePF(nome, endereco, telefone, email, educacao, genero, cpf, dataNascimento);
    }

    // Metodo que interage com o usuário e retorna um ClientePJ
    public static ClientePJ lerPJ() {
        String nome, endereco, cnpj, telefone, email;
        Date dataFundacao;

        nome = Validacao.lerNome("Nome da Empresa");

        System.out.print("Endereço: ");
        endereco = lerString();

        System.out.print("Telefone: ");
        telefone = lerString();

        System.out.print("Endereço: ");
        endereco = lerString();

        System.out.print("Email: ");
        email = lerString();

        dataFundacao = Validacao.lerData("Data de Fundacao");

        cnpj = Validacao.lerCNPJ();

        return new ClientePJ(nome, endereco, telefone, email, cnpj, dataFundacao);
    }

    // Metodo que interage com o usuário e retorna um Cliente
    public static Cliente lerCliente() {

        System.out.print("Tipo de cliente:\n0 - Pessoa Fisica\n1 - Pessoa Juridica\nDigite uma opcao:\n");
        String tipoCadastro = lerString();
        while (true) { // Não sai do loop enquanto a entrada não for uma das opcoes
            if (tipoCadastro.equals("0")) {
                return lerPF();
            } else if (tipoCadastro.equals("1")) {
                return lerPJ();
            } else {
                System.out.println("Opcao invalida.\nEscolha uma opcao:\n");
                tipoCadastro = lerString();
            }
        }
    }

    // Metodo que le o Terminal
    private static String lerString() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

}