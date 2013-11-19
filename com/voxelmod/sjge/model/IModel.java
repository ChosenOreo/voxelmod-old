package com.voxelmod.sjge.model;

import com.voxelmod.sjge.vertex.IVertex;

public interface IModel<T extends IVertex> {
	public T[] getVertices();
}
