package com.voxelmod.sjge.shader;

public class Attribute {
	public final String AttributeName;
	public final int ID;
	
	public Attribute(String attributeName, ShaderProgram programID) {
		AttributeName = attributeName;
		ID = programID.getNumberOfAttributes();
	}
}
