import java.util.Date;

public class ClientePF extends Cliente{
    private Date dataLicenca;
    private String educacao;
    private String genero;
    private String classeEconomica;
    private final String cpf;
    private Date dataNascimento;

    //construtor
    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero, String classeEconomica, String cpf, Date dataNascimento) {
        super(nome, endereco);

        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.genero = genero;
        this.classeEconomica = classeEconomica;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public ClientePF(){
        super();
        cpf = "";
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

    private static boolean numeroIguais(String cpfTeste){
        int i, primeiroNumero = cpfTeste.charAt(0);
        for (i = 0; i < 9  && primeiroNumero == cpfTeste.charAt(i) ; i++)

        //Testo se todos os numeros são iguais
        if (i == 9)
            return true;

        return false;
    }

    static boolean validarCPF(String cpfTeste){
        int somatorio , resto, i, digito, primeiroDigito, segundoDigito;
        cpfTeste = cpfTeste.replace(".", "");
        cpfTeste = cpfTeste.replace("-", "");

        if (cpfTeste.length() != 11) //Verifica se o tamanho está correto
            return false;

        if(numeroIguais(cpfTeste)) //Verifica se os digitos são iguais
            return false;

        int[] digitos = new int[11];
        for (i = 0; i < 11; i++) {
            if (cpfTeste.charAt(i) < 48 || cpfTeste.charAt(i) > 57)
                return false;
            digitos[i] = cpfTeste.charAt(i) - 48;
        }
            

        primeiroDigito = digitos[9];
        segundoDigito = digitos[10];
        
        //Primeiro digito
        somatorio = 0;
        for (i = 0; i < 9; i++)
            somatorio += digitos[i] * (10 - i);

        resto = somatorio % 11;
        digito = resto < 2 ? 0 : 11 - resto;
        
        if (digito != primeiroDigito)
            return false;

        //Segundo Digito
        somatorio = 0;
        for (i = 0; i < 10; i++)
            somatorio += digitos[i] * (11 - i); 

        resto = somatorio % 11;
        digito = resto < 2 ? 0 : 11 - resto;

        if (digito != segundoDigito)
            return false;
       
        return true;
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