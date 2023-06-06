import java.util.ArrayList;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;

    // construtor
    public SeguroPF(Date dataInicio, Date dataFim, Seguradora seguradora,
            ArrayList<Sinistro> listaSinistros,
            ClientePF cliente) {
        super(dataInicio, dataFim, seguradora);
        this.cliente = cliente;
        this.setValorMensal(calcularValor());
    }

    // getters e setters

    public ClientePF getCliente() {
        return cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String toString() {
        return "Cliente: " + cliente.getNome() + " - " + cliente.getCpf() +
                "\n" + super.toString() +
                "\nVeiculo: " + veiculo.toStringBasico();
    }

    @Override
    public double getValorMensal() {
        this.setValorMensal(calcularValor());
        return super.getValorMensal();
    }

    // metodos
    public boolean autorizarCondutor(Condutor condutor) {
        return getListaCondutores().add(condutor);
    }

    public boolean desautorizarCondutor(Condutor condutor) {
        return getListaCondutores().remove(condutor);
    }

    public void gerarSinistro(Sinistro sinistro) {
        getListaSinistros().add(sinistro);
    }

    public double calcularValor() {
        int idade = cliente.getDataNascimento().idade().getAno();

        double fatorIdade;

        if (18 <= idade && idade < 30) {
            fatorIdade = CalcSeguro.FATOR_18_30.getConstante();
        } else if (30 <= idade && idade < 60) {
            fatorIdade = CalcSeguro.FATOR_30_60.getConstante();
        } else if (60 <= idade && idade < 80) {
            fatorIdade = CalcSeguro.FATOR_60_90.getConstante();
        }

        else {
            return Double.MAX_VALUE;
        }

        return CalcSeguro.VALOR_BASE.getConstante() * fatorIdade * (1 + 1 / (cliente.getListaVeiculos().size()));
    }
}
