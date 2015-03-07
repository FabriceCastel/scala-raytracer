package com.fcastel.raytracer.node

import com.fcastel.raytracer.utils.Colour
import com.fcastel.raytracer.primitive.Primitive
import com.fcastel.raytracer.RenderParameters
import com.fcastel.raytracer.ShadeableIntersection
import com.fcastel.raytracer.algebra.Ray
import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.Material
import com.fcastel.raytracer.shader.Shader
import com.fcastel.raytracer.shader.BlinnPhongShader
import com.fcastel.raytracer.AccelerationStructure
import com.fcastel.raytracer.Light

class GeometryNode(name: String, primitive: Primitive, mat: Material, shader: Shader) extends SceneNode(name){
	def this(name: String, primitive: Primitive){
		this(name, primitive, new Material(new Colour(0xff2255ff), new Colour(0xffffffff), 50), new BlinnPhongShader())
	}

	override def intersect(params: RenderParameters, ray: Ray): ShadeableIntersection = {
		val primitiveIntersection = primitive.intersect(ray)
		if(primitiveIntersection.isValid){
			val col = new Colour(0xfff220b0)
			val sinter = new ShadeableIntersection(0, true, (s, l) => {
				shader.apply(ray, primitiveIntersection, mat)(s, l)
				})
			sinter
		} else {
			val col = new Colour(0xffff0000)
			val sinter = new ShadeableIntersection(0, false, (s, l) => {col})
			sinter
		}
	}

	override def flatten(): List[FlattenedGeometryNode] = {
		var id = new Matrix4D()
		id.setIdentity()
		val flatNode = new FlattenedGeometryNode(name, primitive, id)
		List(flatNode)
	}

	override def addChild(node: SceneNode): SceneNode = {
		throw new UnsupportedOperationException("Cannot add child to Geometry Node object")
	}
}