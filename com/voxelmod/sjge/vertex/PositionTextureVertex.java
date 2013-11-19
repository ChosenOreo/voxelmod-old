package com.voxelmod.sjge.vertex;

public class PositionTextureVertex implements IVertex {
	private float[] xyzw;
	private float[] st;
	private float textureID;
	
	public static final int BYTES_PER_ELEMENT = 4;
	
	public static final int POSITION_ELEMENT_COUNT = 4;
	public static final int TEXTURE_ELEMENT_COUNT = 4;
	
	public static final int POSITION_BYTE_COUNT = POSITION_ELEMENT_COUNT * BYTES_PER_ELEMENT;
	public static final int TEXTURE_BYTE_COUNT = TEXTURE_ELEMENT_COUNT * BYTES_PER_ELEMENT;
	
	public static final int POSITION_BYTE_OFFSET = 0;
	public static final int TEXTURE_BYTE_OFFSET = POSITION_BYTE_OFFSET + POSITION_BYTE_COUNT;
	
	public static final int ELEMENT_COUNT = POSITION_ELEMENT_COUNT + TEXTURE_ELEMENT_COUNT;
	public static final int STRIDE = POSITION_BYTE_COUNT + TEXTURE_BYTE_COUNT;
	
	public PositionTextureVertex() {
		this.xyzw = new float[] {0.0F, 0.0F, 0.0F, 1.0F};
		this.st = new float[] {0.0F, 0.0F};
		this.textureID = 0.0F;
	}
	
	public PositionTextureVertex(PositionTextureVertex copy) {
		this.xyzw = new float[] {copy.xyzw[0], copy.xyzw[1], copy.xyzw[2], copy.xyzw[3]};
		this.st = new float[] {copy.st[0], copy.st[1]};
		this.textureID = copy.textureID;
	}
	
	public PositionTextureVertex setXYZ(float x, float y, float z) {
		return this.setXYZW(x, y, z, 1.0F);
	}
	
	public PositionTextureVertex setXYZW(float x, float y, float z, float w) {
		this.xyzw = new float[] {x, y, z, w};
		return this;
	}
	
	public PositionTextureVertex setST(float s, float t) {
		this.st = new float[] {s, t};
		return this;
	}
	
	public PositionTextureVertex setTextureID(float textureID) {
		this.textureID = textureID;
		return this;
	}
	
	public float[] getElements() {
		float[] out = new float[PositionTextureVertex.ELEMENT_COUNT];
		int i = 0;
		
		out[i++] = this.xyzw[0];
		out[i++] = this.xyzw[1];
		out[i++] = this.xyzw[2];
		out[i++] = this.xyzw[3];
		
		out[i++] = this.st[0];
		out[i++] = this.st[1];
		
		out[i++] = this.textureID;
		
		return out;
	}
	
	public float[] getXYZ() {
		return new float[] {this.xyzw[0], this.xyzw[1], this.xyzw[2]};
	}
	
	public float[] getXYZW() {
		return new float[] {this.xyzw[0], this.xyzw[1], this.xyzw[2], this.xyzw[3]};
	}
	
	public float[] getST() {
		return new float[] {this.st[0], this.st[1]};
	}
	
	public float getTextureID() {
		return this.textureID;
	}
}
