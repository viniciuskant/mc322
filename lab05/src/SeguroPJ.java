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

    public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora);
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

    public boolean autorizarCondutor(){
        String nome = Leitura.lerNome("Nome");
        String cpf = Leitura.lerCPF();

        System.out.print("Telefone: ");
        String telefone = Leitura.lerString();

        System.out.print("Endereco: ");
        String endereco = Leitura.lerString();

        System.out.print("Email: ");
        String email = Leitura.lerString();

        Date dataNascimento = Leitura.lerData("Data de nascimento");

        return getListaCondutores().add(new Condutor(nome, cpf, telefone, endereco, email, dataNascimento));
    }

    public boolean desautorizarCondutor() {
        Condutor condutor = escolheCodutor();
        return getListaCondutores().remove(condutor);
    }

    public void gerarSinistro() {
        Date dataSinistro;
        String endereco, data, dataSeparada[];

        Condutor condutor = escolheCodutor();


        System.out.print("Data do Sinistro(dd/MM/aaaa): ");
        data = Leitura.lerString();
        while(!Validacao.validarData(data)){ //Não sai do loop enquanto a entrada não for valida
            System.out.print("Data invalida!\nData do Sinistro(dd/MM/aaaa): ");
            data = Leitura.lerString();
        }
        
        dataSeparada = data.split("/");
        dataSinistro = new Date(dataSeparada[0], dataSeparada[1], dataSeparada[2]);

        System.out.print("Endereco: ");
        endereco = Leitura.lerString();

        getListaSinistros().add(new Sinistro(dataSinistro, endereco, this, condutor));
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
