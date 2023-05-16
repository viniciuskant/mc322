import java.util.Scanner;
import java.util.ArrayList;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
    private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

    //construtor
    public Seguradora(String nome, String telefone, String email, String endereco){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    //getters e setters
    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEndereco(){
        return endereco;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }
    
    public boolean addCliente(Cliente cliente){
        return listaClientes.add(cliente);
    }

    public boolean addSinistro(Sinistro sinistro){
        return listaSinistros.add(sinistro);
    }

    public String toString(){
        String info;
        info =  "Nome: " + getNome() + "\nTelefone: " + getTelefone() + "\nEmail: " + getEmail() + "\nEndereco: " + getEndereco();
        return info;
    }

    //Métodos
    public boolean cadastrarCliente(Cliente cliente){
        boolean alocou = listaClientes.add(cliente);
        if (alocou)
            nCliente++;
        return alocou;
    }

    public int indexCliente(String cliente){
        int i = 0;
        for(; i < listaClientes.size() && listaClientes.get(i).getNome() != cliente; i ++);
        
        if (i == listaClientes.size())
            return -1;
        
        return i;
    }

    public int indexSinistro(int id){
        int i = 0;
        for(; i < listaSinistros.size() && listaSinistros.get(i).getid() != id; i ++);
        
        if (i == listaSinistros.size())
            return -1;
        
        return i;
    }

    public boolean removeCliente(String cliente){
        int indice = indexCliente(cliente);
        if (indice == -1)
            return false;

        listaClientes.remove(indice);
        return true;
    }

    public boolean removerVeiculo(String cliente, String placa){
        int indice = indexCliente(cliente);
        if (indice == -1)
            return false;

        return listaClientes.get(indice).removerVeiculo(placa);
    }

    public String listarClientes(String cliente){
        String info = "";
        if(cliente.equals("PF")){
            int numeracao = 1;
            for(int i = 0; i < listaClientes.size(); i ++){
                if(listaClientes.get(i) instanceof ClientePF){
                    info += Integer.toString(numeracao++) + " - " + ((ClientePF)listaClientes.get(i)).toString() + "\n";
                }
            }
        }
        else if(cliente.equals("PJ")){
            int numeracao = 1;
            for(int i = 0; i < listaClientes.size(); i ++){
                if(listaClientes.get(i) instanceof ClientePJ){
                    info += Integer.toString(numeracao++) + " - " + ((ClientePJ)listaClientes.get(i)).toString() + "\n";
                }
            }
        }
        return info;
    }

    public void listarTodosClientes(){
        int i = 0;
        for(Cliente cliente: listaClientes){
            if(cliente instanceof ClientePF)
                System.out.println(Integer.toString(i) +  " - " + cliente.getNome() + "(" + ((ClientePF)cliente).getCpf() + ")");

            else
                System.out.println(Integer.toString(i) +  " - " + cliente.getNome() + "(" + ((ClientePJ)cliente).getCnpj() + ")");

        }
    }

    public boolean gerarSinistro(){
        Date dataSinistro;
        String endereco, data, dataSeparada[];
        Scanner input = new Scanner(System.in);

        System.out.print("Data do Sinistro(dd/MM/aaaa): ");
        data = input.nextLine();

        while(!Validacao.validarData(data)){
            System.out.print("Data do Sinistro(dd/MM/aaaa): ");
            data = input.nextLine();
        }
        
        dataSeparada = data.split("/");
        dataSinistro = new Date(dataSeparada[0], dataSeparada[1], dataSeparada[2]);

        System.out.print("Endereco: ");
        endereco = input.nextLine();

        Sinistro sinistro = new Sinistro(dataSinistro, endereco);

        boolean alocou = listaSinistros.add(sinistro);
        return alocou;
    }
    
    public boolean visualizarSinistro(String cliente){
        boolean existe = false;
        for(int i = 0; i < listaSinistros.size(); i++){
            if(listaSinistros.get(i).getCliente().getNome() == cliente){
                System.out.println(Integer.toString(i) + " - "  + listaSinistros.get(i).toStringBasico());
                existe = true;
            }
        }
        return existe;    
    }

    public String listarSinistros(){
        String info = "";
        for(int i = 0; i < listaSinistros.size(); i ++){
            info += (Integer.toString(i) + " - ID: " + Integer.toString(listaSinistros.get(i).getid()));
        }
        return info;
    }

    public void listarVeiculosCliente(String nome){
        int indice = indexCliente(nome);
        if (indice != -1){
            listaClientes.get(indice).listaVeiculos();
        }
    }

    public String listarVeiculos(){
        String info = "";
        for(Cliente cliente: listaClientes){
            info += (cliente.listaVeiculos());
        }
        return info;
    }
    
    public Boolean removerSinistro(int id){
        int i = indexSinistro(id);
        if (i == -1)
            return false;
        
        listaSinistros.remove(i);
        return true;
    }

    public Boolean removerSinistro(String id){
        return removerSinistro(Integer.parseInt(id));
    }
}