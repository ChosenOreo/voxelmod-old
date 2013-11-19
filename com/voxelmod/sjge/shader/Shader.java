package com.voxelmod.sjge.shader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;

import com.voxelmod2.util.FileLocation;

public class Shader {
	public final ShaderType Type;
	private int shaderID;
	public Shader(String shaderPath, ShaderType shaderType) {
		Type = shaderType;
		
		int GLShaderType = 0;
		if (Type == ShaderType.VERTEX) {
			GLShaderType = GL20.GL_VERTEX_SHADER;
		} else if (Type == ShaderType.FRAGMENT) {
			GLShaderType = GL20.GL_FRAGMENT_SHADER;
		} else if (Type == ShaderType.GEOMETRY) {
			GLShaderType = GL32.GL_GEOMETRY_SHADER;
		}
		
		StringBuilder shaderSource = new StringBuilder();
		shaderID = 0;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(FileLocation.getMainDirectory(), "/assets/voxelmod/shaders/" + shaderPath)));
			String line;
			while ((line = reader.readLine()) != null) {
				shaderSource.append(line).append("\n");
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Could not read shader file at \"" + shaderPath + "\".");
			e.printStackTrace();
			System.exit(-2);
		}
		
		shaderID = GL20.glCreateShader(GLShaderType);
		GL20.glShaderSource(shaderID, shaderSource);
		GL20.glCompileShader(shaderID);
		
		if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Could not compile shader file at \"" + shaderPath + "\".");
			System.exit(-3);
		}
		
		int errorValue = GL11.glGetError();
		if (errorValue != GL11.GL_NO_ERROR){
			System.err.println("GL ERROR CODE: " + errorValue);
			System.exit(-4);
		}
	}
	
	public void destroy() {
		GL20.glDeleteShader(shaderID);
	}
	
	public int getShaderID() {
		return shaderID;
	}
	
	public enum ShaderType {
		VERTEX,
		FRAGMENT,
		GEOMETRY
	}
}