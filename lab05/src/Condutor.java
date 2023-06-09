import java.util.ArrayList;

public class Condutor {
    private final String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private Date dataNasc;
    private ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();

    // construtor
    public Condutor(String nome, String cpf, String telefone, String endereco, String email, Date dataNasc, ArrayList<Sinistro> listaSinistros) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNasc = dataNasc;
        this.listaSinistros = listaSinistros;
    }

    public Condutor(String nome, String cpf, String telefone, String endereco, String email, Date dataNasc) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNasc = dataNasc;
    }

    // getters e setters
    public String getCpf() {
        return cpf;
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

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    // metodos
    public String toString() {
        return "Nome: " + getNome() +
                "\nCPF: " + getCpf() +
                "\nTelefone: " + getTelefone() +
                "\nEndereco: " + getEndereco() +
                "\nEmail: " + getEmail() +
                "\nData de nascimento: " + getDataNasc().toString();

    }

    public void adicionarSinistro(Sinistro sinistro) {
        listaSinistros.add(sinistro);
    }
}
