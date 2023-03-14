public class Cliente {
    private String nome;
    private String dataNascimento;
    private int idade;
    private String endereco;

    //construtor
    public Cliente(String nome, String dataNascimento, String idade, String endereco){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

    //getters e setters
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

    public int getidade(){
        return idade;
    }

    public void setidade(int idade){
        this.idade = idade;
    }

    public String getEndereco(){
        return endereco;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
}