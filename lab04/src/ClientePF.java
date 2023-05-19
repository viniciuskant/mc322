public class ClientePF extends Cliente{
    private final String cpf;
    private String genero;
    private Date dataLicenca;
    private String educacao;
    private Date dataNascimento;
    private String classeEconomica;

    //construtor
    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero, String classeEconomica, String cpf, Date dataNascimento) {
        super(nome, endereco);

        this.cpf = cpf;
        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.genero = genero;
        this.classeEconomica = classeEconomica;
        this.dataNascimento = dataNascimento;
    }

    //getters e setters

    public Date getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }
    
    public String getCpf() {
        return cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double calculaScore(){
        int idade = dataNascimento.idade().getAno();
        double fatorIdade;

        if (18 <= idade && idade < 30){
            fatorIdade = CalcSeguro.FATOR_18_30.getConstante();
        }
        else if (30 <= idade && idade < 60){
            fatorIdade = CalcSeguro.FATOR_30_60.getConstante();
        }
        else if (60 <= idade && idade < 80){
            fatorIdade = CalcSeguro.FATOR_60_90.getConstante();
        }
        
        else{
            return Double.MAX_VALUE;
        }

        return fatorIdade * this.getQuantidadeVeiculos();
    }

    @Override
    public String toString(){
        return  "\nCPF: " + getCpf() +
                "\nData de nascimento: " + getDataNascimento() +
                "\nData de licenca: " + getDataLicenca() +
                "\nEducação: " + getEducacao() +
                "\nGênero: " + getGenero() +
                "\nClasse enconômica: " + getClasseEconomica() +
                super.toString();
    }
    
}