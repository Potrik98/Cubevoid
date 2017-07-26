#version 430 core

layout (location = 0) in vec4 vertex;
layout (location = 1) in vec4 color;

out vec4 position;
out vec4 in_color;

uniform mat4 pr_matrix;
uniform mat4 vw_matrix = mat4(1.0);
uniform mat4 ml_matrix = mat4(1.0);

void main() {
    position = pr_matrix * vw_matrix * ml_matrix * vertex;
    in_color = color;
    gl_Position = position;
}