package com.voxelmod.sjge;

import java.util.LinkedList;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.voxelmod.sjge.renderevent.IRenderEvent;
import com.voxelmod.sjge.renderstage.IRenderStage;

public class RenderManager {
	static LinkedList<IRenderStage> renderStages;
	static LinkedList<IRenderEvent> renderEvents;
	
	static {
		renderStages = new LinkedList<IRenderStage>();
		renderEvents = new LinkedList<IRenderEvent>();
	}
	
	public static boolean addRenderEvent(IRenderEvent event) {
		if (!renderEvents.contains(event)) {
			renderEvents.add(event);
			return true;
		}
		return false;
	}
	
	public static boolean removeRenderEvent(IRenderEvent event) {
		return renderEvents.remove(event);
	}
	
	public static boolean addRenderStage(IRenderStage stage) {
		if (!renderStages.contains(stage)) {
			renderStages.add(stage);
			return true;
		}
		return false;
	}
	
	public static boolean removeRenderStage(IRenderStage stage) {
		return renderStages.remove(stage);
	}
	
	public static void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		for (IRenderStage renderStage : renderStages) {
			for (IRenderEvent renderEvent : renderEvents) {
				renderStage.render(renderEvent);
			}
		}
		Display.update();
	}
}
