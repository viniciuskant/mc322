public abstract class Cliente{
    private String nome;
    private String telefone;
    private String endereco;
    private String email;

    //construtor
    public Cliente(String nome, String endereco, String telefone, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    //getters e setters      
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String toString() {
        return "\nNome: " + getNome() +
               "\nEndereço: " + getEndereco() +
               "\nTelefone: " + getTelefone() +
               "\nEmail: " + getEmail();
    }

    public abstract String toStringBasico();
}