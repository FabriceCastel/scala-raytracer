package raytracer.Ray

import javax.vecmath.{Point3d}
import javax.vecmath.{Vector3d}

class Ray(val origin: Point3d, val direction: Vector3d){
	require(direction.length != 0, "Cannot instantiate a Ray object with the zero vector")
}