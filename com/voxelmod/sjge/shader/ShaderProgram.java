package com.voxelmod.sjge.shader;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class ShaderProgram {
	private List<Shader> shaders;
	private List<Attribute> attributes;
	private int programID;
	
	public ShaderProgram() {
		shaders = new ArrayList<Shader>();
		attributes = new ArrayList<Attribute>();
		programID = 0;
	}
	
	public void AttachShader(Shader shader) {
		shaders.add(shader);
	}
	
	public Attribute BindAttribute(String attributeName) {
		Attribute attribute = new Attribute(attributeName, this);
		attributes.add(attribute);
		return attribute;
	}
	
	public void CompileProgram() {
		programID = GL20.glCreateProgram();
		for (Shader s : shaders) {
			GL20.glAttachShader(programID, s.getShaderID());
		}
		for (Attribute a : attributes) {
			GL20.glBindAttribLocation(programID, a.ID, a.AttributeName);
		}
		
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		
		int errorValue = GL11.glGetError();
		if (errorValue != GL11.GL_NO_ERROR){
			System.err.println("GL ERROR CODE: " + errorValue);
			System.exit(-4);
		}
	}
	
	public int getProgramID() {
		return programID;
	}
	
	public int getNumberOfShaders() {
		return shaders.size();
	}
	
	public int getNumberOfAttributes() {
		return attributes.size();
	}
}

