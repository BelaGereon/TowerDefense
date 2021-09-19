package GameEngine;

import java.awt.image.DataBufferInt;
import java.util.stream.IntStream;

public class Renderer {

    private final int pixelWidth;
    private final int pixelHeight;
    private final int[] pixels;

    public Renderer(GameContainer gameContainer) {
        pixelWidth = gameContainer.getWidth();
        pixelHeight = gameContainer.getHeight();

        // Gives IntArray direct access to the pixel data of the window
        pixels = ((DataBufferInt) gameContainer
                .getWindow()
                .getImage()
                .getRaster()
                .getDataBuffer())
                .getData();
    }

    public void clear() {
        // clears the screen
        IntStream.range(0, pixels.length).forEach(i -> pixels[i] = 0);
    }

}
