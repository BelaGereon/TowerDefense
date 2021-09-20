package Game;

import GameEngine.AbstractGame;
import GameEngine.GameContainer;
import GameEngine.Renderer;
import GameEngine.gfx.Image;

public class GameManager extends AbstractGame {

    private final Image image;

    public GameManager() {
        image = new Image("/testObject.png");
    }

    @Override
    public void update(GameContainer gameContainer, float dt) {

    }

    @Override
    public void render(GameContainer gameContainer, Renderer renderer) {
        renderer.drawImage(
                image,
                gameContainer.getInput().getMouseX() - (image.getWidth() / 2),
                gameContainer.getInput().getMouseY() - (image.getHeight() / 2));
    }

    public static void main(String[] args) {
        GameContainer gameContainer = new GameContainer(new GameManager());
        gameContainer.start();
    }
}
