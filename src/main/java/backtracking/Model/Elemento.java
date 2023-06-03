package backtracking.Model;

public class Elemento {
    private int peso;
    private int beneficio;

    public Elemento(int peso, int beneficio) {
        this.peso = peso;
        this.beneficio = beneficio;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(int beneficio) {
        this.beneficio = beneficio;
    }


    @Override
    public String toString(){
        return "Peso:"+peso+","+" beneficio:"+beneficio;
    }


}
