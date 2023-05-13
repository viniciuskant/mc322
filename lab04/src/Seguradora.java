import java.util.ArrayList;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
    private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
    private int nSinistro = 0;
    private int nCliente = 0;

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

    public int getnSinistro() {
        return nSinistro;
    }

    public int getnCliente() {
        return nCliente;
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
        for(; i < nCliente && listaClientes.get(i).getNome() != cliente; i ++);
        
        if (i == nCliente)
            return -1;
        
        return i;
    }

    public boolean removeCliente(String cliente){
        int indice = indexCliente(cliente);
        if (indice == -1)
            return false;

        listaClientes.remove(indice);
        nCliente--;
        return true;
    }

    public String listarClientes(String cliente){
        String info = "\n";
        if(cliente.equals("PF")){
            int numeracao = 1;
            for(int i = 0; i < nCliente; i ++){
                if(listaClientes.get(i) instanceof ClientePF){
                    info += Integer.toString(numeracao++) + "- " + ((ClientePF)listaClientes.get(i)).toString() + "\n";
                }
            }
        }
        else if(cliente.equals("PJ")){
            int numeracao = 1;
            for(int i = 0; i < nCliente; i ++){
                if(listaClientes.get(i) instanceof ClientePJ){
                    info += Integer.toString(numeracao++) + "- " + ((ClientePJ)listaClientes.get(i)).toString() + "\n";
                }
            }
        }

        return info;
    }

    public boolean gerarSinistro(Sinistro sinistro){
        boolean alocou = listaSinistros.add(sinistro);
        if (alocou)
            nSinistro++;
        return alocou;
    }
    
    public boolean visualizarSinistro(String cliente){
        for(int i = 0; i < nSinistro; i++){
            if(listaSinistros.get(i).getCliente().getNome() == cliente){
                System.out.println(listaSinistros.get(i).toString());
                return true;
            }
        }
        return false;    
    }

    public String listarSinistros(){
        String info = "";
        for(int i = 0; i < nSinistro; i ++){
            info += "\n" + Integer.toString(i + 1);
            info += "- ";
            info += listaSinistros.get(i).toString() + "\n";
        }
        return info;
    }

    

    // public boolean addVeiculo(String cliente, Veiculo veiculo){
    //     int indice = indexCliente(cliente);
    //     if (indice == -1)
    //         return false;
        
    //     else{
    //         this.listaClientes.get(indice).addVeiculo(veiculo);
    //         return true;
    //     }
    // }
}