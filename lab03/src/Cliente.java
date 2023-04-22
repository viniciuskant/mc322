import java.util.ArrayList;

public class Cliente{
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
    private int nVeiculos = 0;

    //construtor
    public Cliente() {
        super();
    }

    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
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

    public String listaVeiculos(){
        String info = "\n";
        for(int i = 0; i < nVeiculos; i ++){
            info += "\n" + listaVeiculos.get(i).toString() +"\n";
        }
        return info;
    }

    public String toString() {
        return "\nNome: " + getNome() +
               "\nEndereço: " + getEndereco() +
               "\nLista de veiculos: " + listaVeiculos();
    }

    //Métodos
    public void addVeiculo(Veiculo veiculo){
        listaVeiculos.add(veiculo);
        nVeiculos++;
    }
}