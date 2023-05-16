public class ClientePJ extends Cliente{
    private final String cnpj;
    private Date dataFundacao;
    private int qtdeFuncionarios;

    //construtor
    public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao, int qtdeFuncionarios) {
        super(nome, endereco);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    //getters e setters

    public String getCnpj() {
        return cnpj;
    }

    public Date getdataFundacao() {
        return dataFundacao;
    }

    public void setdataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    public void addFuncionario(int n){
        this.qtdeFuncionarios += n;
    }

    public boolean removeFuncionario(int n){
        if (qtdeFuncionarios < n){
            qtdeFuncionarios -= n;
            return true;
        }

        return false;
    }

    @Override
    public String toString(){
        return "\nCNPJ: " + getCnpj() +
               "\nData de fundação: " + getdataFundacao() +
               "\nQuantidade de funcionários: " + getQtdeFuncionarios() +
               super.toString();
    }

    @Override
    public double calculaScore() {
        return CalcSeguro.VALOR_BASE.getConstante() * (1 + qtdeFuncionarios/100) * this.getQuantidadeVeiculos(); 
    }
}