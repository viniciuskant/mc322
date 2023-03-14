public class Sinistro {
    private String data;
    private int id;
    private String endereco;

    //construtor
    public Sinistro(String data, String dataNascimento, String id, String endereco){
        this.data = data;
        this.id = id;
        this.endereco = endereco;
    }

    //getters e setters
    public String getdata(){
        return data;
    }

    public void setdata(String data){
        this.data = data;
    }

    public int getid(){
        return id;
    }

    public void setid(int id){
        this.id = id;
    }

    public String getEndereco(){
        return endereco;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
}