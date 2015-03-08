package com.fcastel.raytracer.node

import com.fcastel.raytracer.algebra._

class JointNode(name: String, children: List[SceneNode], trans: Matrix4D) extends SceneNode(name, children){
	def this(name: String, trans: Matrix4D){
		this(name, Nil, trans)
	}

	override def flatten(): List[FlattenedGeometryNode] = {
		var fl = children.foldLeft(List[FlattenedGeometryNode]())
			{(flatList, curNode) => flatList ::: curNode.flatten()}
		fl.map({node => node.transform(trans)})
	}

	override def addChild(node: SceneNode): SceneNode = {
		val updatedChildren = children :+ node
		val updatedNode = new JointNode(name, updatedChildren, trans)
		updatedNode
	}
}