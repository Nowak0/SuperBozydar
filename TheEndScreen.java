import java.awt.*;

public class TheEndScreen {
    public static void drawTheEnd(Graphics graphics, int ending, int licznikMonet) {
        graphics.drawRect(100, 20, 400, 400);
        if(ending == 0) {
            graphics.drawString("Game over", 270, 200);
        }
        else {
            graphics.drawString("Liczba monet: ", 255, 60);
            graphics.drawString(String.valueOf(licznikMonet), 340, 60);
            graphics.drawString("You win!", 270, 200);
        }
    }
}