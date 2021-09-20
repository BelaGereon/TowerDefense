package GameEngine;

import GameEngine.gfx.Image;

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

    public void setPixel(int x, int y, int value) {
        if ((x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) || value == 0x0ffff00ff) {
            return;
        }

        pixels[x + y * pixelWidth] = value;
    }

    public void drawImage(Image image, int offX, int offY) {
        for(int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < image.getWidth(); x++) {
                setPixel(x + offX, y + offY, image.getPixels()[x + y * image.getWidth()]);
            }
        }
    }

}
