import javax.swing.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Animation");
        Mapa mapa = new Mapa();
        Panel panel = new Panel(mapa);
        System.out.println(panel.isFocusable());
        frame.add(panel);
        frame.setBounds(500, 100, 700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel.iks = 1;
        panel.igrek = 0;

        int ostatniSkok = panel.skok;
        while (panel.igrek < 19) {
            if (panel.igrek < 0) {
                panel.igrek = 0;
            }

            if (panel.czyZabijacz(panel.iks, panel.igrek)) {
                panel.igrek = 10;
                panel.iks = 10;
                TheEndScreen.drawTheEnd(panel.getGraphics(), panel.ending,panel.licznikMonet);
                return;
            }

            if (ostatniSkok == panel.skok) {

                if (panel.czyMoznaRuszyc(panel.iks, panel.igrek + 1)) {
                    panel.igrek++;
                } else {
                    panel.skok = 0;
                }
            }
            ostatniSkok = panel.skok;

            ustawPlansze(panel, mapa);
            panel.repaint();
            Thread.sleep(175);
        }
        panel.repaint();
    }

    static void ustawPlansze(Panel panel, Mapa mapa) {
        int x = panel.iks;

        boolean czyPoprzedniaPlansza = mapa.getNumerPlanszy() > 0 && x == 0;
        if(czyPoprzedniaPlansza) {
            mapa.setNumerPlanszy(mapa.getNumerPlanszy() - 1);
            panel.iks = Panel.ROZMIAR_PLANSZY_X - 2;
        }
        boolean czyNastepnaPlansza = x >= Panel.ROZMIAR_PLANSZY_X - 1;
        if (czyNastepnaPlansza) {
            panel.iks = 1;
            mapa.setNumerPlanszy(mapa.getNumerPlanszy() + 1);
        }
    }

}
