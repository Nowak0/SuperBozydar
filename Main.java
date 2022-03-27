import javax.swing.*;


public class Main
{


    public static void main(String[] args) throws InterruptedException
    {

        JFrame frame = new JFrame("Animation");
        Panel panel = new Panel();
        System.out.println(panel.isFocusable());
        frame.add(panel);
        frame.setBounds(100, 100, 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel.iks = 9;
        panel.igrek = 0;
        int ostatniSkok = panel.skok;
        while (panel.igrek < 19)
        {
            if(panel.igrek < 0)
            {
                panel.igrek = 0;
            }
            if(panel.CzyZabijacz(panel.iks, panel.igrek))
            {
                panel.igrek = 40;
                panel.iks=1;
                System.out.println("You died!");
                break;
            }
            if(panel.CzyMoneta(panel.iks, panel.igrek))
            {
              panel.ZbierzMonete(panel.iks, panel.igrek);
            }

            if(ostatniSkok == panel.skok)
            {

                if(panel.CzyMoznaRuszyc(panel.iks, panel.igrek + 1))
                {
                    panel.igrek++;
                }
                else
                {
                    panel.skok = 0;
                }
            }
            ostatniSkok = panel.skok;

            if(panel.CzyZabijacz(panel.iks, panel.igrek))
            {
                panel.igrek = 20;
            }

            panel.repaint();
            Thread.sleep(175);
        }
        panel.repaint();

    }
}
