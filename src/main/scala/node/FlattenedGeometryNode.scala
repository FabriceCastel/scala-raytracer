package com.fcastel.raytracer.node

import java.awt.{Color}

import com.fcastel.raytracer.ShadeableIntersection
import com.fcastel.raytracer.RenderParameters
import com.fcastel.raytracer.primitive.Primitive
import com.fcastel.raytracer.algebra.Ray
import com.fcastel.raytracer.algebra._

class FlattenedGeometryNode(name: String, primitive: Primitive, trans: Matrix4D) extends GeometryNode(name, primitive){
	
	// intersect method needs to be overriden to calculate intersection WITH its trans
	// matrix taken into account, unlike parent GeometryNode class

	override def intersect(params: RenderParameters, ray: Ray): ShadeableIntersection = {
		super.intersect(params, ray)
	}

	def intersectFast(ray: Ray): Boolean = {
		true
	}

	def applyTransform(trans: Matrix4D): FlattenedGeometryNode = {
		var updatedMatrix = trans
		updatedMatrix.mul(trans)
		val transformedNode = new FlattenedGeometryNode(name, primitive, updatedMatrix)
		transformedNode
	}
}