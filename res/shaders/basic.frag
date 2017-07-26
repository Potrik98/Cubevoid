#version 430 core

layout (location = 0) out vec4 color;

in vec4 position;
in vec4 in_color;

void main() {
    color = in_color;
}