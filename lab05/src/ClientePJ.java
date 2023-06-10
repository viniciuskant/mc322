import java.util.ArrayList;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private Date dataFundacao;
    private ArrayList<Frota> listaFrota = new ArrayList<Frota>();

    // construtor
    public ClientePJ(String nome, String endereco, String telefone, String email, String cnpj, Date dataFundacao) {
        super(nome, endereco, telefone, email);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
    }

    // getters e setters    
    public String getCnpj() {
        return cnpj;
    }

    public Date getdataFundacao() {
        return dataFundacao;
    }

    public void setdataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public ArrayList<Frota> getListaFrota() {
        return listaFrota;
    }

    public void setListaFrota(ArrayList<Frota> listaFrota) {
        this.listaFrota = listaFrota;
    }

    // Métodos
    @Override
    public String toString() {
        String info = super.toString() +
                "\nCNPJ: " + getCnpj() +
                "\nData de fundação: " + getdataFundacao() +
                "\nCodigo das frotas:\n";
        for (Frota frota : listaFrota)
            info += "\t" + frota.getCode() + "\n";

        return info;
    }

    public String toStringBasico(){
        return getNome() + " - " + getCnpj() + "\n";
    }

    public String listarFrotas(){
        String info = "";
        for(int i = 0; i < listaFrota.size(); i++)
            info+=  "\t" + i + "- " + listaFrota.get(i).getCode() + "\n";
        
            return info;
    }

    public boolean cadastarFrota(Frota frota) {
        return listaFrota.add(frota);
    }

    public boolean cadastarFrota(){
        return cadastarFrota(Frota.lerFrota());
    }

    private int indiceFrota(String code) {
        for (int i = 0; i < listaFrota.size(); i++) {
            if (listaFrota.get(i).getCode().equals(code))
                return i;
        }
        return -1;
    }

    public boolean atualizarFrota(String code, Veiculo veiculo) { // Adiciona o veiculo da frota do codigo desejado
        int indice = indiceFrota(code);
        if (indice == -1)
            return false;
        return listaFrota.get(indice).getListaVeiculos().add(veiculo);
    }

    public boolean atualizarFrota(Veiculo veiculo) { // Adiciona o veiculo da frota do codigo desejado
        for(Frota frota: listaFrota)
            if(frota.getListaVeiculos().remove(veiculo))
                return true;
        return false;
    }

    public boolean atualizarFrota(String code, ArrayList<Veiculo> listaVeiculos) { // Troca a lista de veiculo da frota do codigo desejado
        int indice = indiceFrota(code);
        if (indice == -1)
            return false;
        listaFrota.get(indice).setListaVeiculos(listaVeiculos);
        return true;
    }

    public boolean getVeiculosPorFrota(int code) {
        for (Frota frota : listaFrota) {
            if (Integer.parseInt(frota.getCode()) == code) {
                System.out.println("Lista de veiculos:");
                for (Veiculo veiculo : frota.getListaVeiculos())
                    System.out.println("\n\t" + veiculo.toStringBasico());
            }
            return true;
        }
        return false;
    }
}