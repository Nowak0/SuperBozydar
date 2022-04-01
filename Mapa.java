import java.util.List;

public class Mapa {

    private int numerPlanszy = 0;
    private ListaPlansz listaPlansz = new ListaPlansz();

    public void setNumerPlanszy(int numerPlanszy) {
        this.numerPlanszy = numerPlanszy;
    }

    public int getNumerPlanszy() {
        return numerPlanszy;
    }

    public Integer[] getPlansza() {
        if(numerPlanszy > 2) {
            return null;
        }
        List<Integer[]> listaPlansz = this.listaPlansz.getListaPlansz();
        return listaPlansz.get(numerPlanszy);
    }

}
