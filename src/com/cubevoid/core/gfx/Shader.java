package com.cubevoid.core.gfx;

import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VALIDATE_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glValidateProgram;

public class Shader {
    private final int programId;

    public final int locationProjectionMatrix;
    public final int locationViewMatrix;
    public final int locationModelMatrix;

    public Shader(String vertexSource, String fragmentSource) {
        programId = glCreateProgram();
        enable();
        createShader(vertexSource, GL_VERTEX_SHADER);
        createShader(fragmentSource, GL_FRAGMENT_SHADER);
        link();

        locationModelMatrix = glGetUniformLocation(programId, "ml_matrix");
        locationProjectionMatrix = glGetUniformLocation(programId, "pr_matrix");
        locationViewMatrix = glGetUniformLocation(programId, "vw_matrix");
    }

    private int createShader(String shaderCode, int shaderType)  {
        int shaderId = glCreateShader(shaderType);
        if (shaderId == 0) {
            throw new RuntimeException("Error creating shader. Code: " + shaderId);
        }

        glShaderSource(shaderId, shaderCode);
        glCompileShader(shaderId);

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
            throw new RuntimeException("Error compiling Shader code: " + glGetShaderInfoLog(shaderId, 1024));
        }

        glAttachShader(programId, shaderId);

        return shaderId;
    }

    private void link()  {
        glLinkProgram(programId);
        if (glGetProgrami(programId, GL_LINK_STATUS) == 0) {
            throw new RuntimeException("Error linking Shader code: " + glGetShaderInfoLog(programId, 1024));
        }

        glValidateProgram(programId);
        if (glGetProgrami(programId, GL_VALIDATE_STATUS) == 0) {
            throw new RuntimeException("Error validating Shader code: " + glGetShaderInfoLog(programId, 1024));
        }
    }

    public void enable() {
        glUseProgram(programId);
    }

    public void disable() {
        glUseProgram(0);
    }

    public void setUniformMat4(int location, Matrix4f value) {
        float[] data = new float[16];
        value.get(data);
        glUniformMatrix4fv(location, false, data);
    }
}
