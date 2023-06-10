import java.util.Scanner;

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

    public String toStringBasico(){
        return getModelo() + " - " + getplaca();
    }

    // Metodo que interage com o usuário e retorna um Veiculo
    public static Veiculo lerVeiculo() {
        String placa, marca, modelo, anoFabricacao;

        System.out.print("Placa do veículo: ");
        placa = lerString();

        System.out.print("Marca: ");
        marca = lerString();

        System.out.print("Modelo: ");
        modelo = lerString();

        System.out.print("Ano de fabricação do veículo: ");
        anoFabricacao = lerString();
        while (!Validacao.ehInteiro(anoFabricacao)) { // Não sai do loop enquanto a entrada não for valida
            System.out.print("Ano de fabricação invalido!\nAno de fabricação:");
            anoFabricacao = lerString();
        }

        return new Veiculo(placa, marca, modelo, Integer.parseInt(anoFabricacao));
    }

    // Metodo que le o Terminal
    private static String lerString() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

}