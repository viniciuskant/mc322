import java.util.ArrayList;

public class Cliente{
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
    private int valorSeguro; 
    private int quantidadeVeiculos = 0;

    //construtor
    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public Cliente(){
        super();
    }

    //getters e setters      
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getQuantidadeVeiculos() {
        return quantidadeVeiculos;
    }

    public int getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(int valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public String toString() {
        return "\nNome: " + getNome() +
               "\nEndereço: " + getEndereco() +
               "\nLista de veiculos: " + listaVeiculos();
    }

    //Métodos
    public boolean addVeiculo(Veiculo veiculo){
        for(Veiculo i: listaVeiculos){
            if (i == veiculo)
                return false;
        }

        listaVeiculos.add(veiculo);
        quantidadeVeiculos++;
        return true;

    }

    public String listaVeiculos(){
        String info = "\n";
        for(int i = 0; i < quantidadeVeiculos; i ++){
            info += "\n" + listaVeiculos.get(i).toString() +"\n";
        }
        return info;
    }

    private int indexVeiculo(String placa){ //Talvez tenha erro
        int i = 0; 
        for(; i < quantidadeVeiculos && listaVeiculos.get(i).getplaca() != placa; i ++);
        
        if (i == quantidadeVeiculos)
            return -1;
        
        return i;
    }

    public Boolean removerVeiculo(String placa){
        int i = indexVeiculo(placa);

        if (i == -1)
            return false;

        listaVeiculos.remove(i);
        quantidadeVeiculos--;
        return true;
    }
}