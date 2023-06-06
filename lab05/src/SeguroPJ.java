public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ cliente;

    // construtor
    public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora, Frota frota, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
        this.setValorMensal(calcularValor());
    }

    // getters e setters
    public Frota getFrota() {
        return frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getCliente() {
        return cliente;
    }

    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
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
        int quantidadeFunc = getListaCondutores().size();
        int quantidadeVeiculos = frota.getListaVeiculos().size();
        int AnosPosFundacao = cliente.getdataFundacao().idade().getAno();
        int quantidadeSinistrosCliente = getListaSinistros().size();
        int quantidadeSinistrosCondutor = 0;
        for(Condutor condutor: getListaCondutores())
            quantidadeSinistrosCondutor += condutor.getListaSinistros().size();

        return (CalcSeguro.VALOR_BASE.getConstante() * (10 + ( quantidadeFunc ) /10) * (1 + 1/( quantidadeVeiculos +2) ) * (1 + 1/( AnosPosFundacao +2) ) * (2 + quantidadeSinistrosCliente /10) * (5 + quantidadeSinistrosCondutor /10));
    }
}
