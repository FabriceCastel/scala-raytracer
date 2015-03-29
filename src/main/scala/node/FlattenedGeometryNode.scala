package com.fcastel.raytracer.node

import java.awt.{Color}

import com.fcastel.raytracer.ShadeableIntersection
import com.fcastel.raytracer.RenderParameters
import com.fcastel.raytracer.primitive.Primitive
import com.fcastel.raytracer.algebra.Ray
import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.material.Material
import com.fcastel.raytracer.shader.Shader

class FlattenedGeometryNode(name: String, val primitive: Primitive, mat: Material, shader: Shader, trans: Matrix4D) extends GeometryNode(name, primitive, mat, shader){
	val inv = new Matrix4D()
	inv.invert(trans)

	// intersect method needs to be overriden to calculate intersection WITH its trans
	// matrix taken into account, unlike parent GeometryNode class

	override def intersect(params: RenderParameters, ray: Ray): ShadeableIntersection = {
		// is this even right?
		val transformedRay = ray * inv
		val tHit = super.intersect(params, transformedRay)
		val t = tHit.t
		val isValid = tHit.isValid
		new ShadeableIntersection(t, isValid, (a, b) => tHit.shadeIntersection(a, b))
	}

	def intersectFast(ray: Ray): Boolean = {
		true
	}

	def transform(newTrans: Matrix4D): FlattenedGeometryNode = {
		var updatedMatrix = trans
		updatedMatrix.mul(newTrans)
		val transformedNode = new FlattenedGeometryNode(name, primitive, mat, shader, updatedMatrix)
		transformedNode
	}
}