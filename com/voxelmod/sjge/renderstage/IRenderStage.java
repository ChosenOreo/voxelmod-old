package com.voxelmod.sjge.renderstage;

import com.voxelmod.sjge.renderevent.IRenderEvent;

public interface IRenderStage {
	public boolean render(IRenderEvent event);
}
