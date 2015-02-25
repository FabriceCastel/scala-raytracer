package raytracer.Ray

import javax.vecmath.{Point3d}
import javax.vecmath.{Vector3d}

class Ray(val p: Point3d, val v: Vector3d){
	require(v.length != 0, "Cannot instantiate a Ray object with the zero vector")
}