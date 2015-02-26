package raytracer.BasicIntersection

import raytracer.vecmath._

// Intersection returned by Primitive subclasses

class BasicIntersection(val point: Point3D, val vector: Vector3D, val t: Double, val uv: Point2D, val isValid: Boolean){
	def this(point: Point3D, vector: Vector3D, t: Double, uv: Point2D){
		this(point, vector, t, uv, true)
		require(uv.x >= 0 && uv.y >= 0 && uv.x <= 1 && uv.y <= 1, "UV lies outside of expected range")
		require(vector.length != 0, "Cannot instantiate a BasicIntersection object with the zero vector")
	}

	def this(point: Point3D, vector: Vector3D, t: Double){
		this(point, vector, t, new Point2D(0,0))
	}

	def this(){
		this(null, null, 0, null, false)
	}

}