public class Sinistro {
    private final int ID;
    private Date data;
    private String endereco;
    private Seguro seguro;
    private Condutor condutor;

    // construtor
    public Sinistro(Date data, String endereco, Seguro seguro, Condutor condutor) {
        this.ID = Integer.parseInt(super.toString().replace("Sinistro@", ""), 16);
        this.data = data;
        this.endereco = endereco;
        this.seguro = seguro;
        this.condutor = condutor;
    }

    public Sinistro(Date data, String endereco, Veiculo veiculo, Cliente cliente) {
        this.data = data;
        this.endereco = endereco;
        this.ID = Integer.parseInt(super.toString().replace("Sinistro@", ""), 16);
    }

    public Sinistro(Date data, String endereco) {
        this.data = data;
        this.endereco = endereco;
        this.ID = Integer.parseInt(super.toString().replace("Sinistro@", ""), 16);
    }

    // getters e setters
    public int getid() {
        return ID;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public String toString() {
        return "ID: " + Integer.toString(getid()) + " - Nome: " + condutor.getNome();
    }

    public String toStringBasico() {
        return "ID " + Integer.toString(getid());
    }
}