public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;

    //construtor
    public Veiculo(String placa, String marca, String modelo, int anoFabricacao){
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
    }

    //getters e setters
    public String getplaca(){
        return placa;
    }

    public void setplaca(String placa){
        this.placa = placa;
    }

    public String getMarca(){
        return marca;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public String getModelo(){
        return modelo;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public int getAnoFabricacao(){
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao){
        this.anoFabricacao = anoFabricacao;
    }

    public String toString() {
        return  "Placa: " + getplaca() + 
                "\nMarca: " + getMarca() + 
                "\nModelo: " + getModelo() +
                "\nAno de Fabricacao: " + Integer.toString(getAnoFabricacao());
    }

}