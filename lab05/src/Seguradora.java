import java.util.ArrayList;

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
        System.out.println("Lista de Clientes:");
        for (Cliente cliente : listaClientes)
            System.out.println("\t" + cliente.toStringBasico());
    }

    public boolean gerarSeguro(Seguro seguro){
        return listaSeguros.add(seguro);
    }

    public boolean gerarSeguro() {
        String indiceVeiculo, indiceFrota, resposta;
        Cliente cliente = escolhaCliente();

        Date dataInicio = Leitura.lerData("Data de inicio");
        Date dataFim = Leitura.lerData("Data de fim");

        if (cliente instanceof ClientePF) {
            SeguroPF seguro = new SeguroPF(dataInicio, dataFim, this, (ClientePF) cliente);
            System.out.println("Qual veiculo receberá o seguro:\n" + ((ClientePF) cliente).listarVeiculos());
            indiceVeiculo = Leitura.lerString();

            while (true) {
                try {
                    seguro.setVeiculo(((ClientePF)cliente).getListaVeiculos().get(Leitura.INT(indiceVeiculo)));
                    break;
                } catch (Exception e) {
                    System.out.println("Indice invalido. Escolha novamente: ");
                    indiceVeiculo = Leitura.lerString();
                }
            }

            System.out.println("Cadatrar condutores:\n\t1-Nao\n\t2-Sim");
            resposta = Leitura.lerString();
            while(true){
                if(resposta.equals("1")){
                    seguro.autorizarCondutor();
                }
                
                else if(resposta.equals("2")){
                    System.out.println("Seguro" + seguro.getIdSeguro() +"cadastrado com sucesso.");
                    return listaSeguros.add(seguro);
                }

                else{
                    System.out.println("Indice invalido. Escolha novamente: ");
                    resposta = Leitura.lerString();

                }
            }
        }

        else{
            SeguroPJ seguro = new SeguroPJ(dataInicio, dataFim, this, (ClientePJ)cliente);
            System.out.println("Qual veiculo receberá o seguro:\n" + ((ClientePJ) cliente).listarFrotas());
            indiceFrota = Leitura.lerString();

            while (true) {
                try {
                    seguro.setFrota(((ClientePJ)cliente).getListaFrota().get(Leitura.INT(indiceFrota)));
                    break;
                } catch (Exception e) {
                    System.out.println("Indice invalido. Escolha novamente: ");
                    indiceFrota = Leitura.lerString();
                }
            }

            System.out.println("Cadatrar condutores:\n\t1-Nao\n\t2-Sim");
            resposta = Leitura.lerString();
            while(true){
                if(resposta.equals("1")){
                    seguro.autorizarCondutor();
                }
                
                else if(resposta.equals("2")){
                    System.out.println("Seguro" + seguro.getIdSeguro() +"cadastrado com sucesso.");
                    return listaSeguros.add(seguro);
                }

                else{
                    System.out.println("Indice invalido. Escolha novamente: ");
                    resposta = Leitura.lerString();

                }
            }

        }
    }

    public boolean cancelarSeguro(Seguro seguro) {
        return listaSeguros.remove(seguro);
    }

    public boolean cancelarSeguro(){
        return cancelarSeguro(escolhaSeguro());
    }

    public boolean cadastrarCliente(Cliente cliente) {
        return listaClientes.add(cliente);
    }

    public int indexCliente(String cliente) {
        int i = 0;
        for (; i < listaClientes.size() && listaClientes.get(i).getNome() != cliente; i++)
            ;

        if (i == listaClientes.size())
            return -1;

        return i;
    }

    public boolean removeCliente(String cliente) {
        int indice = indexCliente(cliente);
        if (indice == -1)
            return false;

        listaClientes.remove(indice);
        return true;
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

    public double calcularReceita() {
        double receita = 0;
        for (Seguro seguro : listaSeguros) {
            receita += seguro.getValorMensal();
        }
        return receita;
    }

    // Lista todos os clientes e retorna o cliente escolhido
    private Cliente escolhaCliente() {
        System.out.println("Clientes:");

        // Imprimi todos os clientes
        for (int i = 0; i < listaClientes.size(); i++) {
            System.out.println(Integer.toString(i) + " - " + listaClientes.get(i).getNome());
        }
        System.out.println("Escolha uma opcao:");
        String indice = Leitura.lerString();

        // Enquanto o indice não for um inteiro e não estiver no intervalo de 0 até o
        // tamanho da lista

        while (true) {
            try {
                return listaClientes.get(Leitura.INT(indice));
            } catch (Exception e) {
                System.out.print("Escolha uma opcao valida: ");
                indice = Leitura.lerString();
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
        String indice = Leitura.lerString();

        // Enquanto o indice não for um inteiro e não estiver no intervalo de 0 até o
        // tamanho da lista

        while (true) {
            try {
                return listaSeguros.get(Leitura.INT(indice));
            } catch (Exception e) {
                System.out.print("Escolha uma opcao valida: ");
                indice = Leitura.lerString();
            }
        }
    } 
}