package com.fcastel.raytracer.primitive

import com.fcastel.raytracer.algebra.Ray
import com.fcastel.raytracer.BasicIntersection

abstract class Primitive(){
	// base abstract class to be implemented for spheres, cones, cubes, mesh...
	def intersect(ray: Ray): BasicIntersection = {
		val b = new BasicIntersection()
		b
	}

	def intersectFast(ray: Ray): Boolean = {
		this.intersect(ray).isValid
	}
}