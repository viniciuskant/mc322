public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;

    // construtor
    public SeguroPF(Date dataInicio, Date dataFim, Seguradora seguradora, ClientePF cliente) {
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
