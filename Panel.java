import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements KeyListener
{
    public int skok = 0;
    public int licznikMonet = 0; 
    public int zabijacz = 16;
    public int moneta = 4;
    int igrek = 0;
    int iks = 0;
    Integer[] mapa = new Integer[]
    {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4,
        0, 0, 0, 0, 0, 3,16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3,
        0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0,16, 0, 0, 0, 0, 2, 2, 2,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
    };
    ImageIcon tiles = new ImageIcon("tileset.png");

    public Panel()
    {
        addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

        graphics.drawOval(32 * iks, igrek * 32, 32, 32);

        for (int i = 0; i < 400; i++)
        {
            int id = mapa[i] - 1;
            int Y = id / 4;
            int X = id % 4;
            graphics.drawImage(tiles.getImage(), i % 20 * 32, i / 20 * 32, i % 20 * 32 + 32, i / 20 * 32 + 32, 0 + X * 32, 0 + Y * 32, 32 + X * 32, 32 + Y * 32, null);
        }

    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && iks > 0 && CzyMoznaRuszyc(iks - 1, igrek))
        {
            iks -= 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && iks < 19 && CzyMoznaRuszyc(iks + 1, igrek))
        {
            iks += 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && CzyMoznaRuszyc(iks, igrek - 1))
        {
            if (skok < 2)
            {
                igrek -= 1;
                skok++;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        //System.out.println(".");
    }
  
    public boolean CzyWolnePole(int x, int y)
    {
        return (mapa[y * 20 + x] == 0);
    }
  
    public boolean CzyZabijacz(int x, int y)
    {
        return (mapa[y * 20 + x] == zabijacz);
    }
  
    public boolean CzyMoneta(int x, int y)
    {
        return (mapa[y * 20 + x] == moneta);
    }
  
    public boolean CzyMoznaRuszyc(int x, int y)
    {
        return (CzyWolnePole(x, y) || CzyZabijacz(x, y) || CzyMoneta(x, y));
    }

    public void ZbierzMonete(int x, int y){
      licznikMonet++;
      mapa[y * 20 + x] = 0;
      System.out.print("Liczba monet: " );
      System.out.println(licznikMonet);
    }


}