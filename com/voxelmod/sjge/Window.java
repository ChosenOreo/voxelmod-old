package com.voxelmod.sjge;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class Window {
	private int width;
	private int height;
	private GLVersion version;
	private String title;
	
	public Window(int width, int height, String title, GLVersion version) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.version = version;
	}
	
	public Window create() {
		try {
			PixelFormat pixelFormat = new PixelFormat();
			ContextAttribs contextAttribs = new ContextAttribs(version.getMajorVersion(), version.getMinorVersion());
			contextAttribs.withForwardCompatible(version.getForwardCompatible());
			contextAttribs.withProfileCore(version.getCoreProfile());
			
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.create(pixelFormat, contextAttribs);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return this;
	}
	
	public enum GLVersion {
		GL3(3, 2, true, true);
		
		private final int majorVersion;
		private final int minorVersion;
		private final boolean forwardCompatible;
		private final boolean coreProfile;
		
		GLVersion(int Major, int Minor, boolean Forward, boolean Core) {
			majorVersion = Major;
			minorVersion = Minor;
			forwardCompatible = Forward;
			coreProfile = Core;
		}
		
		public int getMajorVersion() {
			return majorVersion;
		}
		
		public int getMinorVersion() {
			return minorVersion;
		}
		
		public boolean getForwardCompatible() {
			return forwardCompatible;
		}
		
		public boolean getCoreProfile() {
			return coreProfile;
		}
	}
}
