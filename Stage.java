import java.util.List;

public class Stage {
    private Integer[] background;
    private List<Monster> monsters;

    public Stage(Integer[] background, List<Monster> monsters) {
        this.background = background;
        this.monsters = monsters;
    }

    public Integer[] getBackground() {
        return background;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }
}
