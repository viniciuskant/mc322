import java.util.Date;

public class ClientePJ extends Cliente{
    private final String cnpj;
    private Date dataFundacao;

    //construtor
    public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao){

        super(nome, endereco);

        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;

    }

    public ClientePJ(){
        super();
        cnpj = "";
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

    static boolean validarCNPJ(String cnpjTeste){
        int somatorio, i, primeiroDigito, segundoDigito, resto, digito;
        cnpjTeste = cnpjTeste.replace(".", "");
        cnpjTeste = cnpjTeste.replace("-", "");

        if (cnpjTeste.length() != 14) //Verifica se o tamanho está correto
            return false;

        int[] digitos = new int[14];
        for (i = 0; i < 14; i++) {
            if (cnpjTeste.charAt(i) - 48 < 0 || cnpjTeste.charAt(i) - 48 > 9)
                return false;
            digitos[i] = cnpjTeste.charAt(i) - 48;
        }
        

        primeiroDigito = cnpjTeste.charAt(12) - 48;
        segundoDigito = cnpjTeste.charAt(13) - 48;
        
        //Primeiro digito    
        somatorio = 0;
        for (i = 0; i < 4; i++) {
            somatorio += digitos[i] * (5 - i);
        }
        for (i = 4; i < 12; i++) {
            somatorio += digitos[i] * (13 - i);
        }

        resto = somatorio % 11;
        digito = resto < 2 ? 0 : 11 - resto;

        if (digito != primeiroDigito)
            return false;

        //Segundo Digito
        somatorio = 0;
        for (i = 0; i < 5; i++) {
            somatorio += digitos[i] * (6 - i);
        }
        for (i = 5; i < 13; i++) {
            somatorio += digitos[i] * (14- i);
        }

        resto = somatorio % 11;
        digito = resto < 2 ? 0 : 11 - resto;

        if (digito != segundoDigito)
            return false;

        else
            return true;
    }

    @Override
    public String toString(){
        return "\nCNPJ: " + getCnpj() +
               "\nData de fundação: " + getdataFundacao() +
               super.toString();
    }
}