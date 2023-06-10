import java.util.ArrayList;
import java.util.Scanner;

public class Frota {
    private String code;
    private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

    // construtor
    public Frota(ArrayList<Veiculo> listaVeiculos) {
        int codigo = super.hashCode();
        this.code = Integer.toString(codigo);
        this.listaVeiculos = listaVeiculos;
    }

    public Frota(){
        int codigo = super.hashCode();
        this.code = Integer.toString(codigo);
    }

    // getters e setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    private String printVeiculos() {
        String info = "";
        for (Veiculo veiculo : listaVeiculos) {
            info += "\n\t" + veiculo.toStringBasico();
        }
        return info;
    }

    // Métodos
    public String toString() {
        return "Codigo: " + getCode() +
                "\nLista veiculos: " + printVeiculos();

    }

    public boolean addVeiculo(Veiculo veiculo) {
        return listaVeiculos.add(veiculo);
    }

    public boolean addVeiculo(){
        return addVeiculo(Veiculo.lerVeiculo());
    }

    private int indexVeiculo(String placa) {
        for (int i = 0; i < listaVeiculos.size(); i++)
            if(listaVeiculos.get(i).getplaca() == placa)    
                return i;
        return -1;
    }

    public Boolean removerVeiculo(String placa) {
        int i = indexVeiculo(placa);

        if (i == -1)
            return false;

        listaVeiculos.remove(i);
        return true;
    }

    public static Frota lerFrota(){
        String resposta;
        Frota frota = new Frota();
        if(frota.addVeiculo()){
            Veiculo veiculo =  frota.getListaVeiculos().get(frota.getListaVeiculos().size() - 1);
            System.out.println("Veiculo " + veiculo.getModelo() + " (" + veiculo.getplaca() + ") adicionado com sucesso");
        } 
        
        else{
            System.out.println("Falha. Veiculo não adicionado.");
        }

        System.out.println("Cadatrar mais um veiculo:\n\t1-Nao\n\t2-Sim");
        resposta = lerString();

        while(true){
            if(resposta.equals("1")){
                frota.addVeiculo();
                if(frota.addVeiculo()){
                    Veiculo veiculo =  frota.getListaVeiculos().get(frota.getListaVeiculos().size() - 1);
                    System.out.println("Veiculo " + veiculo.getModelo() + " (" + veiculo.getplaca() + ") adicionado com sucesso");
                } 
                else{
                    System.out.println("Falha. Veiculo não adicionado.");
                }
                System.out.println("Cadatrar mais um veiculo:\n\t1-Nao\n\t2-Sim");
                resposta = lerString();
            }
            
            else if(resposta.equals("2")){
                System.out.println("Frota " + frota.getCode() + "cadastrado com sucesso.");
                break;
            }

            else{
                System.out.println("Indice invalido. Escolha novamente: ");
                resposta = lerString();

            }
        }
        return frota;
    }

    // Metodo que le o Terminal
    private static String lerString() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

}
