
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

    public Integer[] getBackground() {
        Stage stage = getStage();
        if (stage == null) {
            return null;
        }
        return stage.getBackground();
    }

    public Stage getStage() {
        if (numerPlanszy > 2) {
            return null;
        }
        List<Stage> stages = this.listaPlansz.getStages();
        return stages.get(numerPlanszy);
    }

}
