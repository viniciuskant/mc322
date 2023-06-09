import java.util.ArrayList;

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
    }
    ;
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
        return "ID: " + getIdSeguro() +
                "Data de inicio: " + getDataInicio().toString() +
                "Data final: " + getDataFim().toString() +
                "Seguradora: " + seguradora.getNome() +
                "Valor mensal: " + Double.toString(idSeguro);
        // Talvez adiconar mais coisas
    }

    public abstract boolean desautorizarCondutor(Condutor condutor);

    public abstract boolean autorizarCondutor(Condutor condutor);

    public abstract double calcularValor();

    public abstract void gerarSinistro(Sinistro sinistro);
}