import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements KeyListener {
    /**
     *
     */
    public static final int ROZMIAR_PLANSZY_X = 20;
    public int skok = 0;
    public int licznikMonet = 0;
    public int zabijacz = 16;
    public int moneta = 23;

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

        graphics.drawOval(32 * iks, igrek * 32, 32, 32);

        Integer[] plansza = mapa.getPlansza();

        if(plansza == null) {
            graphics.drawRect(0,0,200,200);
            graphics.drawString("The end", 100, 100);
            return;
        }

        for (int i = 0; i < 400; i++) {
            int id = plansza[i] - 1;
            int Y = id / 4;
            int X = id % 4;
            graphics.drawImage(tiles.getImage(), i % 20 * 32, i / 20 * 32, i % 20 * 32 + 32, i / 20 * 32 + 32,
                    0 + X * 32, 0 + Y * 32, 32 + X * 32, 32 + Y * 32, null);
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
        Integer[] plansza = mapa.getPlansza();
        if (plansza == null) {
            return false;
        }
        return (plansza[y * ROZMIAR_PLANSZY_X + x] == 0);
    }

    public boolean czyZabijacz(int x, int y) {
        Integer[] plansza = mapa.getPlansza();
        if (plansza == null) {
            return false;
        }
        return (plansza[y * ROZMIAR_PLANSZY_X + x] == zabijacz);
    }

    public boolean czyMoneta(int x, int y) {
        Integer[] plansza = mapa.getPlansza();
        if (plansza == null) {
            return false;
        }
        return (plansza[y * ROZMIAR_PLANSZY_X + x] == moneta);
    }

    public boolean czyMoznaRuszyc(int x, int y) {
        return (czyWolnePole(x, y) || czyZabijacz(x, y) || czyMoneta(x, y));
    }

    public void zbierzMonete(int x, int y) {
        licznikMonet++;
        Integer[] plansza = mapa.getPlansza();
        plansza[y * ROZMIAR_PLANSZY_X + x] = 0;
        System.out.print("Liczba monet: ");
        System.out.println(licznikMonet);
    }

}