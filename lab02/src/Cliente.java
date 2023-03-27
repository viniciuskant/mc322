import java.util.Scanner;

public class Cliente {
    private String nome;
    private String dataNascimento;
    private int idade;
    private String endereco;
    private String cpf;

    //construtor
    public Cliente(String nome, String dataNascimento, String idade, String endereco, String cpf){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.idade = Integer.parseInt(idade);
        this.endereco = endereco;
        this.cpf = cpf;

        //Enquanto o cpf for inválido é socilitado um novo cpf
        while(!validarCPF()){
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("\nCPF INVALIDO!\nFavor inserir novamente o CPF: ");
            cpf = scanner.nextLine();
            this.cpf = cpf;
        }
    }

    //getters e setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;

        //Enquanto o cpf for inválido é socilitado um novo cpf
        while(!validarCPF()){
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("\nCPF INVALIDO!\nFavor inserir novamente o CPF: ");
            String cpf_ = scanner.nextLine();
            this.cpf = cpf_;
        }
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getdataNascimento(){
        return dataNascimento;
    }

    public void setdataNascimento(String dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    public int getIdade(){
        return idade;
    }

    public void setidade(String idade){
        this.idade = Integer.parseInt(idade);
    }

    public String getEndereco(){
        return endereco;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public String toString(){
        String info;
        info =  "Nome: " + getNome() + "\nData de nascimento: " + getdataNascimento() + "\nIdade: " + getIdade() + "\nEndereco: " +  getEndereco() + "\nCPF: " + getCpf();
        return info;
    }

    private boolean numeroIguais(){
        int i, numeroInteiro, primeiroNumero = cpf.charAt(0) - 48, repeticao = 0;
        for (i = 0; i < 9; i++){
            numeroInteiro = cpf.charAt(i) -  48; //faco a convercao da char para int 
            if (primeiroNumero == numeroInteiro)
                repeticao++;
        }

        //Testo se todos os numeros são iguais
        if (repeticao == 9)
            return true;

        return false;
    }

    private boolean validarCPF(){
        int somatorio , restoDivisao, k, i, numeroInteiro, multiplicador, primeiroNumero, penultimoDigito, ultimoDigito;
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        if (cpf.length() != 11) //Verifica se o tamanho está correto
            return false;

        if(numeroIguais()) //Verifica se os digitos são iguais6
            return false;


        penultimoDigito = cpf.charAt(9) - 48;
        ultimoDigito = cpf.charAt(10) - 48;
        
        //Primeiro digito
        somatorio = 0;
        for (i = 0; i < 9; i++){
            numeroInteiro = cpf.charAt(i) -  48; //faco a convercao da char para int 
            multiplicador = 10 - i;
            somatorio += numeroInteiro * multiplicador; 

            if (numeroInteiro < 0 || numeroInteiro > 9) //testo se o carcater era ou não um representante de um string de um numero
                return false;
        }

        restoDivisao = somatorio % 11;
        
        //Começo a verificar o digito           
        if (restoDivisao <= 1){
            if (penultimoDigito != 0){ //significa que o primeiro verificador é zero
                return false;
            }
        }
        
        else if((11 - restoDivisao) != penultimoDigito){
            return false;
        }

        //Segundo Digito
        somatorio = 0;
        for (i = 0; i < 10; i++){
            numeroInteiro = cpf.charAt(i) - 48; //faco a convercao da char para int 
            multiplicador = 11 - i;
            somatorio += numeroInteiro * multiplicador; 

            if (numeroInteiro < 0 || numeroInteiro > 9) //testo se o carcater era ou não um representante de um string de um numero
                return false;
        }

        restoDivisao = somatorio % 11;

        //Verificar o ultimo digito            
        if (restoDivisao == 0 || restoDivisao == 1){
            if (ultimoDigito != 0){ //significa que o primeiro verificador é zero
                return false;
            }
        }

        else if((11 - restoDivisao) != ultimoDigito){
            return false;
        }

            
        return true;
    }

    
}