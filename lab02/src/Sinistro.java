public class Sinistro {
    private String data;
    private String endereco;
    private int id;

    //construtor
    public Sinistro(String data, String endereco){
        this.data = data;
        this.endereco = endereco;
        this.id = Integer.parseInt(super.toString().replace("Sinistro@", ""), 16);
    }

    //getters e setters
    public String getdata(){
        return data;
    }

    public void setdata(String data){
        this.data = data;
    }

    
    public String getEndereco(){
        return endereco;
    }
    
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    
    public int getid(){
        return id;
    }
    public String toString() {
        String info;
        info =  "Data: " + getdata() + "\nEndereco: " + getEndereco() + "\nid: " + Integer.toString(getid());
        return info;
    }     
}