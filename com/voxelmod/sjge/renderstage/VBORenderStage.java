package com.voxelmod.sjge.renderstage;

import org.lwjgl.opengl.GL11;

import com.voxelmod.sjge.renderevent.IRenderEvent;
import com.voxelmod.sjge.renderevent.VBORenderEvent;

public class VBORenderStage implements IRenderStage {
	@Override
	public boolean render(IRenderEvent event) {
		if (event instanceof VBORenderEvent) {
			VBORenderEvent evt = (VBORenderEvent)event;
			evt.preRender();
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, evt.getNumVertices());
			evt.postRender();
			return true;
		}
		return false;
	}
	
}
