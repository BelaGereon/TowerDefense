package Game;

import GameEngine.AbstractGame;
import GameEngine.GameContainer;
import GameEngine.Renderer;

public class GameManager extends AbstractGame {
    @Override
    public void update(GameContainer gameContainer, float dt) {

    }

    @Override
    public void render(GameContainer gameContainer, Renderer renderer) {

    }

    public static void main(String[] args) {
        GameContainer gameContainer = new GameContainer(new GameManager());
        gameContainer.start();
    }
}
