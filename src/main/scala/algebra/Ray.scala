package com.fcastel.raytracer.algebra

class Ray(val p: Point3D, val v: Vector3D){
	require(v.length != 0, "Cannot instantiate a Ray object with the zero vector")
	v.normalize()

	def *(mat4: Matrix4D): Ray = {
		new Ray(p*mat4, v*mat4)
	}

	override def toString(): String = {
		"(p:<" + p.x + ", " + p.y + ", " + p.z + "> - v:<" + v.x + ", " + v.y + ", " + v.z + ">)" 
	}
}