import java.awt.*;

public class TheEndScreen {
    public static void drawTheEnd(Graphics graphics, int ending) {
        graphics.drawRect(100, 20, 400, 400);
        if(ending == 0) {
            graphics.drawString("Game over", 270, 200);
        }
        else {
            graphics.drawString("You win!", 270, 200);
        }
    }
}