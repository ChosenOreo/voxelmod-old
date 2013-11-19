package com.voxelmod.sjge.vertex;

public class PositionColorVertex implements IVertex {
	private float[] xyzw;
	private float[] rgba;
	
	public static final int BYTES_PER_ELEMENT = 4;
	public static final int POSITION_ELEMENT_COUNT = 4;
	public static final int COLOR_ELEMENT_COUNT = 4;
	
	public static final int POSITION_BYTE_COUNT = POSITION_ELEMENT_COUNT * BYTES_PER_ELEMENT;
	public static final int COLOR_BYTE_COUNT = COLOR_ELEMENT_COUNT * BYTES_PER_ELEMENT;
	public static final int POSITION_BYTE_OFFSET = 0;
	public static final int COLOR_BYTE_OFFSET = POSITION_BYTE_OFFSET + POSITION_BYTE_COUNT;
	
	public static final int ELEMENT_COUNT = POSITION_ELEMENT_COUNT+ COLOR_ELEMENT_COUNT;
	public static final int STRIDE = POSITION_BYTE_COUNT + COLOR_BYTE_COUNT;
	
	public PositionColorVertex() {
		xyzw = new float[] {0.0F, 0.0F, 0.0F, 1.0F};
		rgba = new float[] {1.0F, 1.0F, 1.0F, 1.0F};
	}
	
	public PositionColorVertex setXYZ(float x, float y, float z) {
		return this.setXYZW(x, y, z, 1.0F);
	}
	
	public PositionColorVertex setRGB(float r, float g, float b) {
		return this.setRGBA(r, g, b, 1.0F);
	}
	
	public PositionColorVertex setXYZW(float x, float y, float z, float w) {
		this.xyzw = new float[] {x, y, z, w};
		return this;
	}
	
	public PositionColorVertex setRGBA(float r, float g, float b, float a) {
		this.rgba = new float[] {r, g, b, a};
		return this;
	}
	
	@Override
	public float[] getElements() {
		float[] out = new float[PositionColorVertex.ELEMENT_COUNT];
		int i = 0;
		
		out[i++] = this.xyzw[0];
		out[i++] = this.xyzw[1];
		out[i++] = this.xyzw[2];
		out[i++] = this.xyzw[3];
		
		out[i++] = this.rgba[0];
		out[i++] = this.rgba[1];
		out[i++] = this.rgba[2];
		out[i++] = this.rgba[3];
		
		return out;
	}
	
	public float[] getXYZW() {
		return new float[] {this.xyzw[0], this.xyzw[1], this.xyzw[2], this.xyzw[3]};
	}
	
	public float[] getRGBA() {
		return new float[] {this.rgba[0], this.rgba[1], this.rgba[2], this.rgba[3]};
	}
}
