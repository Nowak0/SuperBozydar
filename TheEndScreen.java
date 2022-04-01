import java.awt.*;

public class TheEndScreen {
    public static void drawTheEnd(Graphics graphics) {
        graphics.drawRect(100, 20, 400, 400);
        graphics.drawString("Game over", 270, 200);
    }
}