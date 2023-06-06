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

    public boolean gerarSeguro(Seguro seguro) {
        return listaSeguros.add(seguro);
    }

    public boolean cancelarSeguro(Seguro seguro) {
        return listaSeguros.remove(seguro);
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

    public ArrayList<Seguro> getSeguroPorCliente() { // Ainda não entendi o que essa função irá retornar
        return new ArrayList<Seguro>();
    }

    public double calcularReceita() {
        double receita = 0;
        for (Seguro seguro : listaSeguros) {
            if (seguro instanceof SeguroPF)
                receita += ((SeguroPF) seguro).getValorMensal();
            else
                receita += ((SeguroPJ) seguro).getValorMensal();
        }
        return receita;
    }
}