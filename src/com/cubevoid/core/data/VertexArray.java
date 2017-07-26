package com.cubevoid.core.data;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class VertexArray {
    private final int vertexArrayId;
    private List<VertexBuffer> buffers;

    public VertexArray() {
        vertexArrayId = glGenVertexArrays();
        buffers = new ArrayList<>();
    }

    public void addBuffer(VertexBuffer buffer, int location) {
        bind();
        buffer.bind();
        glEnableVertexAttribArray(location);
        glVertexAttribPointer(location, buffer.componentCount, GL_FLOAT, false, 0, 0);
        buffer.unbind();
        unbind();
    }

    public void bind() {
        glBindVertexArray(vertexArrayId);
    }

    public void unbind() {
        glBindVertexArray(0);
    }

    public void render() {
        glBindVertexArray(vertexArrayId);
        glDrawElements(GL_TRIANGLES, 0, GL_UNSIGNED_INT, 0);
        glBindVertexArray(0);
    }
}
