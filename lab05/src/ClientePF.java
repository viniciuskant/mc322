import java.util.ArrayList;

public class ClientePF extends Cliente {
    private final String cpf;
    private String genero;
    private String educacao;
    private Date dataNascimento;
    private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

    // construtor
    public ClientePF(String nome, String endereco, String telefone, String email, String educacao, String genero,
            String cpf, Date dataNascimento) {
        super(nome, endereco, telefone, email);

        this.educacao = educacao;
        this.genero = genero;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    // getters e setters
    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    // metodos
    @Override
    public String toString() {
        return super.toString() +
                "\nCPF: " + getCpf() +
                "\nData de nascimento: " + getDataNascimento() +
                "\nEducação: " + getEducacao() +
                "\nGênero: " + getGenero() +
                "\nLista Veiculos" + listaVeiculos.toString();
    }

    public String toStringBasico(){
        return getNome() + " - " + getCpf() + "\n";
    }

    public boolean CadastrarVeiculo(Veiculo veiculo) {
        return listaVeiculos.add(veiculo);
    }

    private int indexVeiculo(String placa) { // Talvez tenha erro
        int i = 0;
        for (; i < listaVeiculos.size() && listaVeiculos.get(i).getplaca() != placa; i++)
            ;

        if (i == listaVeiculos.size())
            return -1;

        return i;
    }

    public Boolean removerVeiculo(String placa) {
        int i = indexVeiculo(placa);

        if (i == -1)
            return false;

        listaVeiculos.remove(i);
        return true;
    }
}