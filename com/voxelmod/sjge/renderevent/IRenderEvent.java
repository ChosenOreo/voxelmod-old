package com.voxelmod.sjge.renderevent;

public interface IRenderEvent {
	public boolean preRender();
	public boolean postRender();
	public boolean releaseObjects();
}
