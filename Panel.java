import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Panel extends JPanel implements KeyListener {

    public static final int ROZMIAR_PLANSZY_X = 20;
    public int skok = 0;
    public int licznikMonet = 0;
    public int moneta = 23;
    public int ending = 0;

    int igrek = 0;
    int iks = 0;

    private Mapa mapa;

    ImageIcon tiles = new ImageIcon("tileset.png");

    public Panel(Mapa mapa) {
        addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.mapa = mapa;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        drawHero(graphics);

        drawMonsters(graphics);



        Integer[] background = mapa.getBackground();
        if (background == null) {
            ending++;
            TheEndScreen.drawTheEnd(graphics,ending);
            return;
        }
        for (int i = 0; i < 400; i++) {
            int id = background[i] - 1;
            int Y = id / 4;
            int X = id % 4;
            graphics.drawImage(tiles.getImage(),
                    i % 20 * 32, i / 20 * 32, i % 20 * 32 + 32, i / 20 * 32 + 32,
                    0 + X * 32, 0 + Y * 32, 32 + X * 32, 32 + Y * 32, null);
            zbierzMonete(iks,igrek,graphics);
        }


    }

    private void drawHero(Graphics graphics) {
        graphics.drawOval(iks * 32, igrek * 32, 32, 32);
    }

    private void drawMonsters(Graphics graphics) {
        Color color = graphics.getColor();

        Stage stage = mapa.getStage();
        if(stage == null) {
            ending++;
            TheEndScreen.drawTheEnd(getGraphics(),ending);
            iks=400;
            igrek=400;
            return;
        }
        List<Monster> monsters = stage.getMonsters();
        for (int i = 0; i < monsters.size(); i++) {
            Monster monster = monsters.get(i);
            Position monsterPosition = monster.getPosition();
            graphics.setColor(new Color(255, 0, 255));
            graphics.fillRect(32 * monsterPosition.getX(), 32 * monsterPosition.getY(), 32, 32);
            graphics.setColor(color);
            monster.changePosition();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && iks > 0 && czyMoznaRuszyc(iks - 1, igrek)) {
            iks -= 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && iks < 19 && czyMoznaRuszyc(iks + 1, igrek)) {
            iks += 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && czyMoznaRuszyc(iks, igrek - 1)) {
            if (skok < 2) {
                igrek -= 1;
                skok++;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // System.out.println(".");
    }

    public boolean czyWolnePole(int x, int y) {
        Integer[] background = mapa.getBackground();
        if (background == null) {
            return false;
        }
        return (background[y * ROZMIAR_PLANSZY_X + x] == 0);
    }

    public boolean czyZabijacz(int x, int y) {
        Stage stage = mapa.getStage();
        if(stage == null) {
            return false;
        }
        List<Monster> monsters = stage.getMonsters();
        for (int i = 0; i < monsters.size(); i++) {
            Monster monster = monsters.get(i);
            int monsterX = monster.getPosition().getX();
            int monsterY = monster.getPosition().getY();
            if (x == monsterX && y == monsterY) {
                return true;
            }
        }
        return false;
    }

    public boolean czyMoneta(int x, int y) {
        Integer[] background = mapa.getBackground();
        if (background == null) {
            return false;
        }
        return (background[y * ROZMIAR_PLANSZY_X + x] == moneta);
    }

    public boolean czyMoznaRuszyc(int x, int y) {
        return (czyWolnePole(x, y) || czyZabijacz(x, y) || czyMoneta(x, y));
    }

    public void zbierzMonete(int x, int y, Graphics graphics) {
        if(czyMoneta(x,y)) {
            licznikMonet++;
        }

        Integer[] background = mapa.getBackground();
        background[y * ROZMIAR_PLANSZY_X + x] = 0;
        graphics.drawString("Liczba monet: ", 255, 60);
        graphics.drawString(String.valueOf(licznikMonet),340,60);
    }

}