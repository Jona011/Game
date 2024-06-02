import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Spaceship {
    private int x, y, dx, dy;
    private double angle;
    private final int SPEED = 5;
    private Image image;
    private BufferedImage bufferedImage;

    public Spaceship() {
        x = 400;
        y = 300;
        angle = 0;
        loadImage();
    }

    private void loadImage() {
        image = Toolkit.getDefaultToolkit().getImage("assets/spaceship.png");
        bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }

    public void draw(Graphics g) {
        AffineTransform tx = new AffineTransform();
        tx.rotate(angle, bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        g.drawImage(op.filter(bufferedImage, null), x, y, null);
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            angle -= 0.1;
        }
        if (key == KeyEvent.VK_RIGHT) {
            angle += 0.1;
        }
        if (key == KeyEvent.VK_UP) {
            dx = (int) (SPEED * Math.cos(angle));
            dy = (int) (SPEED * Math.sin(angle));
        }
        if (key == KeyEvent.VK_DOWN) {
            dx = -(int) (SPEED * Math.cos(angle));
            dy = -(int) (SPEED * Math.sin(angle));
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dx = 0;
            dy = 0;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }
}
