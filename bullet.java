import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
    private int x, y;
    private double angle;
    private final int SPEED = 10;
    private final int SIZE = 5;

    public Bullet(int x, int y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public void move() {
        x += (int) (SPEED * Math.cos(angle));
        y += (int) (SPEED * Math.sin(angle));
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, SIZE, SIZE);
    }
}
