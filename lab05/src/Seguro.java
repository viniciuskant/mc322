import java.util.ArrayList;
import java.util.Scanner;

public abstract class Seguro {
    private final int idSeguro;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
    private ArrayList<Condutor> listaCondutores = new ArrayList<Condutor>();
    private double valorMensal;

    // construtor
    public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora) {
        this.idSeguro = super.hashCode();
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
    }

    // getters e setters
    public int getIdSeguro() {
        return idSeguro;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public ArrayList<Condutor> getListaCondutores() {
        return listaCondutores;
    };

    public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public String toString() {
        return "ID: " + getIdSeguro() + "\n" +
                "Data de inicio: " + getDataInicio().toString() + "\n" +
                "Data final: " + getDataFim().toString() + "\n" +
                "Seguradora: " + seguradora.getNome() + "\n" +
                "Valor mensal: " + Double.toString(idSeguro);
        // Talvez adiconar mais coisas
    }

    public String toStringBasico(){
        return getIdSeguro() + " - R$ " + getValorMensal(); 
    }

    // Lista todos os clientes e retorna o cliente escolhido
    public Condutor escolheCodutor() {
        System.out.println("Condutores:");

        // Imprimi todos os clientes
        for (int i = 0; i < getListaCondutores().size(); i++) {
            System.out.println(Integer.toString(i) + " - " + getListaCondutores().get(i).getNome());
        }
        System.out.println("Escolha uma opcao:");
        String indice = lerString();

        // Enquanto o indice não for um inteiro e não estiver no intervalo de 0 até o tamanho da lista
        while (true) {
            try {
                return getListaCondutores().get(Integer.parseInt(indice));
            } catch (Exception e) {
                System.out.println("Escolha uma opcao valida:");
                indice = lerString();
            }
        }
    }

    public abstract boolean desautorizarCondutor();

    public abstract boolean autorizarCondutor(Condutor condutor);

    public abstract double calcularValor();

    public abstract void gerarSinistro();

    public abstract Cliente getCliente();

    // Metodo que le o Terminal
    private static String lerString() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    
}