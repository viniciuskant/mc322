import java.util.ArrayList;

public class Frota {
    private String code;
    private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

    // construtor
    public Frota(ArrayList<Veiculo> listaVeiculos) {
        int codigo = super.hashCode();
        this.code = Integer.toString(codigo);
        this.listaVeiculos = listaVeiculos;
    }

    public Frota(){
        int codigo = super.hashCode();
        this.code = Integer.toString(codigo);
    }

    // getters e setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    private String printVeiculos() {
        String info = "";
        for (Veiculo veiculo : listaVeiculos) {
            info += "\n\t" + veiculo.toStringBasico();
        }
        return info;
    }

    // Métodos
    public String toString() {
        return "Codigo:" + getCode() +
                "\nLista veiculos: " + printVeiculos();

    }

    public boolean addVeiculo(Veiculo veiculo) {
        return listaVeiculos.add(veiculo);
    }

    private int indexVeiculo(String placa) {
        for (int i = 0; i < listaVeiculos.size(); i++)
            if(listaVeiculos.get(i).getplaca() == placa)    
                return i;
        return -1;
    }

    public Boolean removerVeiculo(String placa) {
        int i = indexVeiculo(placa);

        if (i == -1)
            return false;

        listaVeiculos.remove(i);
        return true;
    }
}
