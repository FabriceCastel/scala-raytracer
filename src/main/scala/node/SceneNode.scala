package com.fcastel.raytracer.node

import com.fcastel.raytracer.utils.Colour
import com.fcastel.raytracer.RenderParameters
import com.fcastel.raytracer.ShadeableIntersection
import com.fcastel.raytracer.algebra.Ray

class SceneNode(val name: String, val children: List[SceneNode]){
	def this(name: String){
		this(name, List())
	}

	def this(){
		this("UNNAMED NODE")
	}

	def addChild(node: SceneNode): SceneNode = {
		val updatedChildren = children :+ node
		val updatedNode = new SceneNode(name, updatedChildren)
		updatedNode
	}

	def flatten(): List[FlattenedGeometryNode] = {
		children.foldLeft(List[FlattenedGeometryNode]())
			{(flatList, curNode) => println("FLATTEN:\n" + curNode.genString("")); flatList ::: curNode.flatten()}
	}

	override def toString: String = {
		genString("")
	}

	protected def genString(sp: String): String = {
		children.foldLeft(sp + "- " + this.getClass.getSimpleName + ": " + name + "\n")((base, c) => base + c.genString(sp + "  "))
	}
}