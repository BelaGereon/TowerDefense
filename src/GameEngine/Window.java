package GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {

    private BufferedImage image;
    private Canvas canvas;
    private JFrame jFrame;
    private Graphics graphics;
    private BufferStrategy bufferStrategy;

    public Window(GameContainer gameContainer) {
        image = new BufferedImage(
                gameContainer.getWidth(),
                gameContainer.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        Dimension dimension = new Dimension(
                (int) (gameContainer.getWidth() * gameContainer.getScale()),
                (int) (gameContainer.getHeight() * gameContainer.getScale()));

        canvas = new Canvas();
        canvas.setPreferredSize(dimension);
        canvas.setMaximumSize(dimension);
        canvas.setMinimumSize(dimension);

        // creates the game window
        jFrame = new JFrame(gameContainer.getTitle());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new BorderLayout());
        jFrame.add(canvas, BorderLayout.CENTER);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
    }

    public void update() {
        graphics.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
    }

}
