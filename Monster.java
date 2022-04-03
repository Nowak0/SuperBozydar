public class Monster {
    private Position position;
    private int minX;
    private int maxX;
    private int direction = 1;
    private int speed;


    public Monster(Position position) {
        this.position = position;
        this.minX = position.getX() - 1;
        this.maxX = position.getX() + 1;
    }

    public Position getPosition() {
        return position;
    }

    public void changePosition() {
        int x = position.getX();
        if (x == maxX) {
            direction = -1;
        }
        if (x == minX) {
            direction = 1;
        }
        if (speed == 5) {
            position.setX(x + direction);
            speed = 0;
        }
        speed++;
    }
}