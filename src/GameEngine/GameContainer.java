package GameEngine;

/*
 * This class will handle the gameloop and user input
 */

public class GameContainer implements Runnable {
    private Thread thread;
    private Window window;
    private Renderer renderer;
    private Input input;
    private AbstractGame game;

    private final double UPDATE_CAP = 1.0 / 60.0; // update 60 times per second
    private final double nanoSecToMilliSec = 1000000000.0;

    private int width = 320;
    private int height = 240;
    private float scale = 4f;
    private String title = "TowerDefense Engine v1.0";

    private boolean running = false;

    public GameContainer(AbstractGame game) {
        this.game = game;
    }

    public void start() {
        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);

        thread = new Thread(this);
        //noinspection CallToThreadRun
        thread.run();
    }

    public void stop() {

    }

    public void run() {
        running = true;

        boolean render;
        double firstTime;
        double lastTime = System.nanoTime() / nanoSecToMilliSec;
        double passedTime;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps;

        while (running) {

            render = false;

            firstTime = System.nanoTime() / nanoSecToMilliSec;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            // ensures correct updating even when code above freezes or sth
            while (unprocessedTime >= UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP;
                render = true;

                game.update(this, (float)UPDATE_CAP);

                //TODO: Update game
                input.update();

                if (frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    System.out.println("FPS: " + fps);
                }
            }

            if (render) {
                renderer.clear();
                game.render(this, renderer);
                window.update();
                frames++;
            } else {
                try {
                    // if there was no change and nothing was rendered the threat will sleep for 1 millisec to lower CPU usage
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            dispose();
        }
    }

    private void dispose() {

    }

    public Window getWindow() {
        return window;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Input getInput() {
        return input;
    }
}
