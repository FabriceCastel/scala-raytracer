package com.fcastel.raytracer.primitive

import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.BasicIntersection
import com.fcastel.raytracer.utils.Utils

class Triangle(vertices: List[Point3D], normals: List[Vector3D], uv: List[Point2D]) extends Primitive(){
	require(vertices.length == 3, "Triangles must have three vertices")

	def this(vertices: List[Point3D]){
		this(vertices, Nil, Nil)
	}

	override def intersect(ray: Ray): BasicIntersection = {
		val fail = new BasicIntersection()
		val edge1 = vertices(1) - vertices(0)
		val edge2 = vertices(2) - vertices(0)
		val P = ray.v cross edge2
		val determinant = edge1 dot P
		if(determinant > -Utils.EPSILON && determinant < Utils.EPSILON) fail
		else {
			val inv_determinant = 1.0/determinant
			val T = ray.p - vertices(0)
			val u = (T dot P) * inv_determinant
			if(u < 0 || u > 1) fail
			else {
				val Q = T cross edge1
				val v = (ray.v dot Q) * inv_determinant
				if(v < 0 || u + v > 1) fail
				else {
					val t = (edge2 dot Q) * inv_determinant
					if(t < Utils.EPSILON) fail
					else {
						val hit = ray.p + ray.v*t;
						var defNormal = edge1 cross edge2
						if(uv.length > 2){
							new BasicIntersection(hit, getNormal(hit, defNormal), t, findUV(hit))
						} else {
							new BasicIntersection(hit, getNormal(hit, defNormal), t, new Point2D(0,0))
						}
					}
				}
			}
		}
	}

	private def getNormal(p: Point3D, n: Vector3D): Vector3D = {
		if(normals.length < 3)
			n
		else {
			val w1 = (p - vertices(0)).length
			val w2 = (p - vertices(1)).length
			val w3 = (p - vertices(2)).length
			((normals(0) * w1) +
			(normals(1) * w2) +
			(normals(2) * w3)) * (1.0 / (w1 + w2 + w3))
		}
	}

	private def findUV(p: Point3D): Point2D = {
	    val u = vertices(1) - vertices(0)
	    val v = vertices(2) - vertices(0)
	    val w = p - vertices(0)

	    val vCrossW = v cross w
	    val vCrossU = v cross u

	    val uCrossW = u cross w
	    val uCrossV = u cross v

	    val denom = uCrossV.length();
	    if(denom != 0){
		    val b1 = vCrossW.length() / denom;
		    val b2 = uCrossW.length() / denom;
		    val b0 = 1.0 - b1 - b2

			val fu = b0 * uv(0).x + b1 * uv(1).x + b2 * uv(2).x;
			val fv = b0 * uv(0).y + b1 * uv(1).y + b2 * uv(2).y;
		    new Point2D(fu, fv)
	    } else {
	 		new Point2D(0,0)   	
	    }
	}

	override def intersectFast(ray: Ray): Boolean = {
		intersect(ray).isValid
	}
}