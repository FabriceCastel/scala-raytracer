package com.fcastel.raytracer.node

import com.fcastel.raytracer.utils.Colour
import com.fcastel.raytracer.primitive.Primitive
import com.fcastel.raytracer.RenderParameters
import com.fcastel.raytracer.ShadeableIntersection
import com.fcastel.raytracer.algebra.Ray
import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.material._
import com.fcastel.raytracer.shader._
import com.fcastel.raytracer.acceleration.AccelerationStructure
import com.fcastel.raytracer.Light

class GeometryNode(name: String, primitive: Primitive, mat: Material, shader: Shader) extends SceneNode(name){
	def this(name: String, primitive: Primitive, mat: Material){
		this(name, primitive, mat, new BlinnPhongShader(30))
	}

	def this(name: String, primitive: Primitive){
		this(name, primitive, new FlatMaterial(new Colour(255, 30, 255)), new BumpmapShader())
	}

	def intersect(params: RenderParameters, ray: Ray): ShadeableIntersection = {
		val primitiveIntersection = primitive.intersect(ray)
		if(primitiveIntersection.isValid){
			val col = new Colour(0xfff220b0)
			new ShadeableIntersection(0, true, (s, l) => {
				shader.apply(ray, primitiveIntersection, mat)(s, l) })
		} else {
			val col = new Colour(0xffff0000)
			new ShadeableIntersection(0, false, (s, l) => {col})
		}
	}

	override def flatten(): List[FlattenedGeometryNode] = {
		var id = new Matrix4D()
		id.setIdentity()
		val flatNode = new FlattenedGeometryNode(name, primitive, mat, shader, id)
		List(flatNode)
	}

	override def addChild(node: SceneNode): SceneNode = {
		throw new UnsupportedOperationException("Cannot add child to Geometry Node object")
	}
}