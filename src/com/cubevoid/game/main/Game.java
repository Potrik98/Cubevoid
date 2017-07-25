package com.cubevoid.game.main;

import com.cubevoid.core.gfx.Window;
import com.cubevoid.core.logic.GameLogicComponent;
import com.cubevoid.core.logic.GameLoop;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

public class Game implements GameLogicComponent {
    private static final String title = "Cubevoid v0.1";
    private static final int width = 800;
    private static final int height = 600;

    private Window window;
    private GameLoop gameLoop;

    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.start();
    }

    public Game() {
        window = new Window(title, width, height);
        gameLoop = new GameLoop(this);
    }

    public void init() {
        window.init();
    }

    public void start() {
        gameLoop.loop();
    }

    public void update() {
        if (window.shouldClose()) gameLoop.stop();
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        window.update();
    }
}
