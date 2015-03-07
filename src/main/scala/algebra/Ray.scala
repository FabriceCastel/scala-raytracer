package com.fcastel.raytracer.algebra

class Ray(val p: Point3D, val v: Vector3D){
	require(v.length != 0, "Cannot instantiate a Ray object with the zero vector")
}