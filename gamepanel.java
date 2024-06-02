import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {
    private Timer timer;
    private Spaceship spaceship;
    private List<Bullet> bullets;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true);
        spaceship = new Spaceship();
        bullets = new ArrayList<>();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                spaceship.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    bullets.add(new Bullet(spaceship.getX(), spaceship.getY(), spaceship.getAngle()));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                spaceship.keyReleased(e);
            }
        });
        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        spaceship.draw(g);
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        spaceship.move();
        for (Bullet bullet : bullets) {
            bullet.move();
        }
        repaint();
    }
}
