package raytracer.primitive.Primitive

import raytracer.Ray._
import raytracer.BasicIntersection._

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