package raytracer.Ray

import raytracer.vecmath._

class Ray(val p: Point3D, val v: Vector3D){
	require(v.length != 0, "Cannot instantiate a Ray object with the zero vector")
}