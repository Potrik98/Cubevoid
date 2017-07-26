package com.cubevoid.game.main;

import com.cubevoid.core.data.IndexBuffer;
import com.cubevoid.core.data.VertexArray;
import com.cubevoid.core.data.VertexArrayObject;
import com.cubevoid.core.data.VertexBuffer;
import com.cubevoid.core.gfx.Shader;
import com.cubevoid.core.gfx.Window;
import com.cubevoid.core.logic.GameLogicComponent;
import com.cubevoid.core.logic.GameLoop;
import com.cubevoid.core.math.Transformation;
import com.cubevoid.core.util.FileUtils;

import static org.lwjgl.opengl.GL11.glClearColor;

public class Game implements GameLogicComponent {
    private static final String title = "Cubevoid v0.1";
    private static final int width = 800;
    private static final int height = 600;

    private Window window;
    private GameLoop gameLoop;

    private Shader shader;
    private VertexArrayObject vao;

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

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        shader = new Shader(
                FileUtils.loadResource("res/shaders/basic.vert"),
                FileUtils.loadResource("res/shaders/basic.frag")
        );

        float[] vertices = {
                -0.5f, -0.5f, 1.0f,
                -0.5f,  0.5f, 1.0f,
                 0.5f,  0.5f, 1.0f,
                 0.5f, -0.5f, 1.0f
        };

        float[] colors = {
                1.0f, 0.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                1.0f, 1.0f, 0.0f, 1.0f
        };

        int[] indices = {
                0, 1, 2,
                2, 3, 0
        };

        vao = new VertexArrayObject(
                new IndexBuffer(indices),
                new VertexBuffer(vertices, 3),
                new VertexBuffer(colors, 4));
    }

    public void start() {
        gameLoop.loop();
    }

    public void update() {
        if (window.shouldClose()) gameLoop.stop();
    }

    public void render() {
        window.clear();
        shader.enable();
        shader.setUniformMat4(shader.locationProjectionMatrix, Transformation.getOrthographicMatrix(
                -1.0f,
                1.0f,
                -1.0f,
                1.0f,
                -1.0f,
                1.0f
        ));

        vao.render();

        window.update();
    }
}
