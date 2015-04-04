package com.fcastel.raytracer.primitive

import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.BasicIntersection
import com.fcastel.raytracer.utils.Utils

class Triangle(vertices: List[Point3D], uv: List[Point2D], normals: List[Vector3D]) extends Primitive(){
	require(vertices.length == 3, "Triangles must have three vertices")

	def this(vertices: List[Point3D]){
		this(vertices, Nil, Nil)
	}

	def this(v1: Point3D, v2: Point3D, v3: Point3D){
		this(List(v1, v2, v3))
	}

	private val fail = new BasicIntersection()
	private val edge1 = vertices(1) - vertices(0)
	private val edge2 = vertices(2) - vertices(0)

	override def intersect(ray: Ray): BasicIntersection = {
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
						val (iNorm, iUV) = interpolateNormalAndUV(hit, defNormal)
						new BasicIntersection(hit, iNorm, t, iUV)
					}
				}
			}
		}
	}

	private def interpolateNormalAndUV(p: Point3D, defaultNormal: Vector3D): (Vector3D, Point2D) = {
		// interpolate between the triangle vertices w/ baycentric coordinates
	    val w = p - vertices(0)

	    val vCrossW = edge2 cross w
	    val vCrossU = edge2 cross edge1
	    val uCrossW = edge1 cross w
	    val uCrossV = edge1 cross edge2

	    val denom = uCrossV.length();
	    if(denom != 0){
		    val b1 = vCrossW.length() / denom;
		    val b2 = uCrossW.length() / denom;
		    val b0 = 1.0 - b1 - b2

			val fu = if(uv.length >= 2) b0 * uv(0).x + b1 * uv(1).x + b2 * uv(2).x else 0;
			val fv = if(uv.length >= 2) b0 * uv(0).y + b1 * uv(1).y + b2 * uv(2).y else 0;
			val fuv = new Point2D(Math.min(Math.max(0, fu), 1), Math.min(Math.max(0, fv), 1))

			val fnorm = if(normals.length < 3) defaultNormal else {
				(normals(0) * b0 + normals(1) * b1 + normals(2) * b2) * (1.0/3)
			}
			(fnorm, fuv)
		} else {
			(defaultNormal, new Point2D(0,0))
		}
	}

	override def intersectFast(ray: Ray): Boolean = {
		intersect(ray).isValid
	}
}