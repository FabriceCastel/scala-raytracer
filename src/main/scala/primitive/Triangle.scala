package com.fcastel.raytracer.primitive

import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.BasicIntersection
import com.fcastel.raytracer.utils.Utils

class Triangle(vertices: List[Point3D], uv: List[Point2D], hasUV: Boolean) extends Primitive(){
	require(vertices.length == 3, "Triangles must have three vertices")

	def this(vertices: List[Point3D]){
		this(vertices, null, false)
	}

	def this(vertices: List[Point3D], uv: List[Point2D]){
		this(vertices, uv, true)
		require(uv.length == 3, "Invalid UV parameters for triangle")
	}

	override def intersect(ray: Ray): BasicIntersection = {
		val intersection: Option[BasicIntersection] = for{
			edge1 <- (vertices(1) - vertices(0))
			edge2 <- (vertices(2) - vertices(0))
			P = ray.v cross edge2
			determinant = edge1 dot P
			if !(determinant > -Utils.EPSILON && determinant < Utils.EPSILON) // fail
			inv_determinant = 1.0/determinant
			T = ray.p - vertices(0)
			u = (T dot P) * inv_determinant
			if !(u < 0 || u > 1) // fail
			Q = T cross edge1
			v = (ray.v dot Q) * inv_determinant
			if !(v < 0 || u + v > 1) // fail
			t = (edge2 dot Q) * inv_determinant
			if !(t < Utils.EPSILON) // fail
			hit = ray.p + ray.v*t
		} yield {
			if(hasUV){
				val d0 = Math.abs((hit - vertices(0)).length)
				val d1 = Math.abs((hit - vertices(1)).length)
				val d2 = Math.abs((hit - vertices(2)).length)
				val uvAvg = (uv(0)*d0 + uv(1)*d1 + uv(2)*d2) / (d0 + d1 + d2)
				new BasicIntersection(hit, edge1 cross edge2, t, uvAvg)
			} else {
				new BasicIntersection(hit, edge1 cross edge2, t)
			}
		}
		intersection.getOrElse(new BasicIntersection())
	}
}