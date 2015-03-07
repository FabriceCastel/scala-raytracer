package com.fcastel.raytracer.primitive

import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.BasicIntersection
import com.fcastel.raytracer.algebra.Ray

class Sphere(radius: Double) extends Primitive(){
	override def intersect(ray: Ray): BasicIntersection = {
		//
		// ******* TODO ********
		//
		// port and clean up intersection code from nhsphere in C++
		// needs a polynomial solver
		//
		val valid = ray.v.x < 0
		val b = new BasicIntersection(null, null, 0, null, valid)
		b
	}

	override def intersectFast(ray: Ray): Boolean = {
		false
	}
}