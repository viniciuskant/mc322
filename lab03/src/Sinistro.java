public class Sinistro{
    private final int ID;
    private String data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;

    //construtor
    public Sinistro(String data, String endereco, Veiculo veiculo, Cliente cliente, Seguradora seguradora){
        this.data = data;
        this.endereco = endereco;
        this.ID = Integer.parseInt(super.toString().replace("Sinistro@", ""), 16);
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    //getters e setters
    public int getid(){
        return ID;
    }
    
    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;
    }
    
    public String getEndereco(){
        return endereco;
    }
    
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String toString() {
        if (this.cliente instanceof ClientePF){
            return "ID: " + Integer.toString(getid()) + 
            "\nCliente: " + ((ClientePF)cliente).toString()+
            "\nData: " + getData() + 
            "\nEndereco: " + getEndereco() +
            "\nSeguradora: " + seguradora.getNome() +
            "\nPlaca do veículo: " + veiculo.getplaca();
        }
        else{
            return "ID: " + Integer.toString(getid()) + 
            "\nCliente: " + ((ClientePJ)cliente).toString()+
            "\nData: " + getData() + 
            "\nEndereco: " + getEndereco() +
            "\nSeguradora: " + seguradora.getNome() +
            "\nPlaca do veículo: " + veiculo.getplaca();
        }
        
    }  
}