package com.fcastel.raytracer.node

import com.fcastel.raytracer.algebra._

class JointNode(name: String, trans: Matrix4D) extends SceneNode(name){
	override def flatten(): List[FlattenedGeometryNode] = {
		var fl = children.foldLeft(List[FlattenedGeometryNode]())
			{(flatList, curNode) => flatList ::: curNode.flatten()}
		fl.map({node => node.applyTransform(trans)})
	}
}