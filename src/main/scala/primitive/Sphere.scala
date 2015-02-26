package raytracer.primitive.Sphere

import javax.vecmath.{Point3d}
import javax.vecmath.{Vector3d}

import raytracer.primitive.Primitive._
import raytracer.BasicIntersection._
import raytracer.Ray._

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