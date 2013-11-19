package com.voxelmod.sjge.renderevent;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.voxelmod.sjge.shader.ShaderProgram;
import com.voxelmod.sjge.vertex.PositionColorVertex;

public class ILColoredVertexVBORenderEvent implements VBORenderEvent {
	final int VBO;
	final int VAO;
	final int PID;
	final int NumVertices;
	final ShaderProgram program;
	
	public ILColoredVertexVBORenderEvent(PositionColorVertex[] vertices, ShaderProgram sProgram) {
		NumVertices = vertices.length;
		FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length * PositionColorVertex.ELEMENT_COUNT);
		for (int i = 0; i < vertices.length; i++) {
			verticesBuffer.put(vertices[i].getElements());
		}
		verticesBuffer.flip();
		
		VAO = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(VAO);
		
		VBO = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBO);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesBuffer, GL15.GL_STATIC_DRAW);
		
		GL20.glVertexAttribPointer(0, PositionColorVertex.POSITION_ELEMENT_COUNT, GL11.GL_FLOAT, false, PositionColorVertex.STRIDE, PositionColorVertex.POSITION_BYTE_OFFSET);
		GL20.glVertexAttribPointer(1, PositionColorVertex.COLOR_ELEMENT_COUNT, GL11.GL_FLOAT, false, PositionColorVertex.STRIDE, PositionColorVertex.COLOR_BYTE_OFFSET);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
		
		program = sProgram;
		PID = program.getProgramID();
	}
	
	@Override
	public boolean releaseObjects() {
		GL30.glBindVertexArray(VAO);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glDeleteBuffers(VBO);
		
		GL30.glBindVertexArray(0);
		GL30.glDeleteVertexArrays(VAO);
		
		return true;
	}

	@Override
	public boolean preRender() {
		GL20.glUseProgram(PID);
		GL30.glBindVertexArray(VAO);
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		return true;
	}
	
	@Override
	public boolean postRender() {
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
		GL20.glUseProgram(0);
		return true;
	}
	
	@Override
	public int getNumVertices() {
		return NumVertices;
	}
}
