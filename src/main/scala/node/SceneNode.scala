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

	def intersect(params: RenderParameters, ray: Ray): ShadeableIntersection = {
		val col = new Colour(0xff000000)
		val sinter = new ShadeableIntersection(0, false, (s, l) => {col})
		if(children.length > 0){
			val hit = children.map{e => e.intersect(params, ray)}.reduceLeft {
				(base, cur) => {
					if(cur.isValid && (!base.isValid || cur.t < base.t)) cur else base
				}
			}
			if(!hit.isValid){
				sinter
			} else {
				hit
			}
		} else {
			sinter
		}
	}

	def addChild(node: SceneNode): SceneNode = {
		val updatedChildren = children :+ node
		val updatedNode = new SceneNode(name, updatedChildren)
		updatedNode
	}

	def flatten(): List[FlattenedGeometryNode] = {
		children.foldLeft(List[FlattenedGeometryNode]())
			{(flatList, curNode) => flatList ::: curNode.flatten()}
	}

	override def toString: String = {
		genString("")
	}

	protected def genString(sp: String): String = {
		children.foldLeft(sp + "-" + name + "\n")((base, c) => base + c.genString(sp + "  "))
	}
}