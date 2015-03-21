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
						if(hasUV){
							// val d0 = Math.abs((hit - vertices(0)).length)
							// val d1 = Math.abs((hit - vertices(1)).length)
							// val d2 = Math.abs((hit - vertices(2)).length)
							// val uvAvg = (uv(0)*d0 + uv(1)*d1 + uv(2)*d2) / (d0 + d1 + d2)
							new BasicIntersection(hit, edge1 cross edge2, t, findUV(hit))
						} else {
							new BasicIntersection(hit, edge1 cross edge2, t, new Point2D(0,0))
						}
					}
				}
			}
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