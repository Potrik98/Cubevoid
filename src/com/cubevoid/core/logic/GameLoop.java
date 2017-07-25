package com.cubevoid.core.logic;

public class GameLoop {
    private final GameLogicComponent gameLogicComponent;
    private boolean running = true;

    public GameLoop(GameLogicComponent gameLogicComponent) {
        this.gameLogicComponent = gameLogicComponent;
    }

    public void stop() {
        running = false;
    }

    public void loop() {
        long lastTime = System.nanoTime();
        double delta = 0.0;
        double ns = 1000000000.0 / gameLogicComponent.TARGET_UPS;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1.0) {
                gameLogicComponent.update();
                updates++;
                delta--;
            }
            gameLogicComponent.render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
    }
}
