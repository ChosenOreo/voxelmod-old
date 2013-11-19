package com.voxelmod.sjge.texture;

import java.io.File;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class Texture implements ITexture {
	Image textureImage;
	int textureID;
	
	public Texture(File textureFile) {
		textureImage = new Image(textureFile);
		textureID = GL11.glGenTextures();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		
		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, textureImage.getWidth(), textureImage.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, textureImage.getImageBuffer());
		
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
	}
	
	public int getTextureID() {
		return textureID;
	}
}
