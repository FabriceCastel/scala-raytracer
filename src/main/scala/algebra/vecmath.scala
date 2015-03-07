package com.fcastel.raytracer.algebra

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
	def cross(other: Vector3D): Vector3D = {
		var P = new Vector3D(0,0,0)
		P.cross(this, other) // assigns val to P
		P // not sure this line is needed to return the newly assigned P
	}
	def *(s: Double): Vector3D = new Vector3D(x*s, y*s, z*s)
	def ==(v: Vector3D): Boolean = x == v.x && y == v.y && z == v.z
}

class Point3D(a: Double, b: Double, c: Double) extends Point3d(a, b, c){
	def -(other: Point3D) = new Vector3D(x - other.x, y - other.y, z - other.z)
	def +(other: Vector3D) = new Point3D(x + other.x, y + other.y, z + other.z)
	def -(other: Vector3D) = this + (other * -1)
}

class Point2D(a: Double, b: Double) extends Point2d(a, b){
	def +(other: Point2D) = new Point2D(x + other.x, y + other.y)
	def *(s: Double) = new Point2D(x*s, y*s)
	def /(s: Double) = new Point2D(x/s, y/s)
}

class Matrix4D() extends Matrix4d(){

}