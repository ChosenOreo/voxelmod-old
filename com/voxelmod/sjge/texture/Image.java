package com.voxelmod.sjge.texture;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class Image {
	private ByteBuffer imageBuffer;
	private int imageWidth;
	private int imageHeight;
	
	public Image(File imageFile) {
		imageBuffer = null;
		try {
			if (!imageFile.exists()) {
				throw new Exception();
			}
			InputStream input = new FileInputStream(imageFile);
			PNGDecoder decoder = new PNGDecoder(input);
			
			imageBuffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
			decoder.decode(imageBuffer, decoder.getWidth() * 4, Format.RGBA);
			imageBuffer.flip();
			
			imageWidth = decoder.getWidth();
			imageHeight = decoder.getHeight();
			
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
			imageBuffer = ByteBuffer.allocateDirect(1024);
			
			for (int i = 0; i < 8; i++) {
				for (int k = 0; k < 16; k++) {
					imageBuffer.put((byte)153);
					imageBuffer.put((byte)0);
				}
				for (int k = 0; k < 32; k++) {
					imageBuffer.put((byte)0);
				}
			}
			for (int i = 0; i < 8; i++) {
				for (int k = 0; k < 32; k++) {
					imageBuffer.put((byte)0);
				}
				for (int k = 0; k < 16; k++) {
					imageBuffer.put((byte)153);
					imageBuffer.put((byte)0);
				}
			}
			
			imageBuffer.flip();
			
			imageWidth = 16;
			imageHeight = 16;
		}
	}
	
	public ByteBuffer getImageBuffer() {
		return imageBuffer;
	}
	
	public int getWidth() {
		return imageWidth;
	}
	
	public int getHeight() {
		return imageHeight;
	}
}
