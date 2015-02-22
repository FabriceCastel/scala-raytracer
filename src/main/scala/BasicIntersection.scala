package raytracer.BasicIntersection

import javax.vecmath.{Vector3d}
import javax.vecmath.{Point3d}

// Intersection returned by Primitive subclasses

class BasicIntersection(val point: Point3d, val vector: Vector3d, val isValid: Boolean){
	def this(){
		this(null, null, false)
	}

	def this(point: Point3d, vector: Vector3d){
		this(point, vector, true)
	}
}