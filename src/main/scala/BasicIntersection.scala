package com.fcastel.raytracer

import com.fcastel.raytracer.algebra._

// Intersection returned by Primitive subclasses

class BasicIntersection(val point: Point3D, val normal: Vector3D, val t: Double, val uv: Point2D, val isValid: Boolean){
	def this(point: Point3D, normal: Vector3D, t: Double, uv: Point2D){
		this(point, normal, t, uv, true)
		require(uv.x >= 0 && uv.y >= 0 && uv.x <= 1 && uv.y <= 1, "UV lies outside of expected range in (" + uv.x + ", " + uv.y + ")")
		require(normal.length != 0, "Cannot instantiate a BasicIntersection object with the zero vector")
		normal.normalize()
	}

	def this(point: Point3D, normal: Vector3D, t: Double){
		this(point, normal, t, new Point2D(0,0))
	}

	def this(){
		this(null, null, 0, null, false)
	}

}