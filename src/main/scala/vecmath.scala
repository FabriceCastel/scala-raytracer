package raytracer.vecmath

import javax.vecmath.{Vector3d}
import javax.vecmath.{Point3d}
import javax.vecmath.{Point2d}
import javax.vecmath.{Matrix4d}

// Note: the IDs in the constructor CANNOT be x, y, z because that would override
// the getter methods in the javax vecmath library
// TODO find a cleaner way to do this?

class Vector3D(a: Double, b: Double, c: Double) extends Vector3d(a, b, c){
	def -(other: Vector3D) = new Vector3D(x - other.x, y - other.y, z - other.z)
	def +(other: Vector3D) = new Vector3D(x + other.x, y + other.y, z + other.z)
	def +(other: Point3D) = new Point3D(x + other.x, y + other.y, z + other.z)
}

class Point3D(a: Double, b: Double, c: Double) extends Point3d(a, b, c){
	def -(other: Point3D) = new Vector3D(x - other.x, y - other.y, z - other.z)
	def +(other: Vector3D) = new Point3D(x + other.x, y + other.y, z + other.z)
}

class Point2D(a: Double, b: Double) extends Point2d(a, b){
}

class Matrix4D() extends Matrix4d(){

}